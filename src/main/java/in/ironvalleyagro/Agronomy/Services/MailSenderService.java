package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Model.OrderDetails;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@ironvalleyagro.in");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    public void sendWelcomeEmail(String to, String customerName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Load HTML template as a string
        String content;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WelcomeTemplate.html")) {
            if (inputStream == null) {
                throw new MessagingException("Could not find email template in resources");
            }
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MessagingException("Could not read email template", e);
        }

        // Replace placeholders with actual values
        content = content.replace("{{customerName}}", customerName);
        content = content.replace("{{link_to_your_site}}", "https://ironvalleyagro.in");

        helper.setTo(to);
        helper.setFrom("info@ironvalleyagro.in");
        helper.setSubject("Welcome to IronValley Agronomy!");
        helper.setText(content, true);  // true indicates HTML content

        mailSender.send(message);
    }


    public void sentOrderMail(Order order) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(order.getEmail());
        // String[] bcc = {};
        helper.setFrom("info@ironvalleyagro.in");
        helper.setSubject("Order Confirmation - IronValley Agronomy");

        // Load and populate HTML template
        String htmlContent = loadAndPopulateTemplate(order);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    private String loadAndPopulateTemplate(Order order) throws MessagingException
    {
        // Load HTML template as a string
        String htmlContent;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("order-confirmation.html")) {
            if (inputStream == null) {
                throw new MessagingException("Could not find email template in resources");
            }
            htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException | MessagingException e) {
            throw new MessagingException("Could not read email template", e);
        }

        // Create order items table rows
        StringBuilder itemsBuilder = new StringBuilder();
        for (OrderDetails order1 : order.getOrderDetails()) {
            itemsBuilder.append("<tr>")
                    .append("<td>").append(order1.getItemName()).append("</td>")
                    .append("<td>").append(order1.getItemQuantity()).append("</td>")
                    .append("<td>").append(order1.getItemGrams()).append("</td>")
                    .append("</tr>");
        }
        String content = htmlContent.replace("<!-- ORDER_ID_PLACEHOLDER -->",String.valueOf(order.getOrderId()));
        String amountPaidContent = content.replace("<!-- AMOUNT_PAID_PLACEHOLDER -->",String.valueOf(order.getAmountPaid()));
        // Replace placeholder with actual table rows
        return amountPaidContent.replace("<!-- ORDER_ITEMS_PLACEHOLDER -->", itemsBuilder.toString());
    }

    public Response sendOtp(String mail){
        Response res = new Response();
        try{
            if(!userRepository.existsByMail(mail)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            String otp = generateOtp();

            // Store OTP with email as key
            otpStorage.put(mail, otp);

            // Set OTP to expire in 5 minutes
            scheduler.schedule(() -> otpStorage.remove(mail), 5, TimeUnit.MINUTES);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail);
            message.setFrom("info@ironvalleyagro.in");
            message.setSubject("OTP FOR IRONVALLEY AGRONOMY");
            message.setText("Your OTP code is: " + otp);

            // Send email
            mailSender.send(message);
            res.setFlag(true);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            System.out.println("OTP sent to " + mail + ": " + otp);
        }catch (Exception e){
            res.setStatusCode(ResponseCode.CONFLICT);
            e.printStackTrace();
        }
        return res;
    }

    public Response verifyOtp(String email, String otp) {
        Response res = new Response();
        String storedOtp = otpStorage.get(email);
        // Verify if the OTP matches and remove it once verified
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(email);
            res.setFlag(true);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            res.setData(userRepository.findByMail(email));
            return res;
        }
        res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
        return res;
    }

    public String generateOtp() {
        // Generate a random 6-digit OTP
        int otp = 100000 + new Random().nextInt(900000);
        return String.valueOf(otp);
    }





}
