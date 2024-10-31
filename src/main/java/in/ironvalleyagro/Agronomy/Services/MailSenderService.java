package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Model.OrderDetails;
import in.ironvalleyagro.Agronomy.Model.Response;
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

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ironvalleysolutionsllp@gmail.com");
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
        helper.setSubject("Welcome to IronValley Agronomy!");
        helper.setText(content, true);  // true indicates HTML content

        mailSender.send(message);
    }


    public void sentOrderMail(Order order) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(order.getEmail());
        helper.setSubject("Order Confirmation - IronValley Agronomy");

        // Load and populate HTML template
        String htmlContent = loadAndPopulateTemplate(order);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    private String loadAndPopulateTemplate(Order order) throws IOException {
        // Load HTML template as a string
        Path templatePath = new ClassPathResource("order-confirmation.html").getFile().toPath();
        String htmlContent = Files.readString(templatePath, StandardCharsets.UTF_8);

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

    private String generateItemsHtml(List<OrderDetails> orders) {
        StringBuilder itemsHtml = new StringBuilder();
        for (OrderDetails item : orders) {
            itemsHtml.append("<li>")
                    .append(item.getItemName()).append(" - Qty: ").append(item.getItemQuantity())
                    .append(" - Weight: ").append(item.getItemGrams()).append(" grams")
                    .append("</li>");
        }
        return itemsHtml.toString();
    }


}
