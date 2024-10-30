package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Model.OrderDetails;
import in.ironvalleyagro.Agronomy.Model.Response;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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


    public void sentOrderMail(Order order) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(order.getEmail());
        String[] bcc = {"karthickb2210@gmail.com"};
        helper.setBcc(bcc);
        helper.setSubject("Order Confirmation");
        helper.setFrom("ironvalleysolutionsllp@gmail.com");

        // Create HTML content with order details
        String content = buildOrderTemplate(order);
        helper.setText(content, true);  // true indicates HTML content

        mailSender.send(message);
    }

    private String buildOrderTemplate(Order order) {
        // Build an HTML template for the order confirmation email
        String itemsHtml = generateItemsHtml(order.getOrderDetails());
        return "<html>"
                + "<body>"
                + "<h1>Order Confirmation</h1>"
                + "<p>Thank you for your order!</p>"
                + "<h2>Order Details</h2>"
                + "<p><strong>Order ID:</strong> " + order.getOrderId() + "</p>"
                + "<p><strong>Order Date:</strong> " + order.getCreatedAt().toString().substring(0,10) + "</p>"
                + "<p><strong>Total Amount:</strong> ₹" + order.getAmountPaid() + "</p>"
                + "<h3>Items:</h3>"
                + "<ul>" + itemsHtml + "</ul>"
                + "<p>We appreciate your business and hope you enjoy your purchase!</p>"
                + "<p>Thanks and Regards, </p>"
                + "<p>IronValley Agronomy </p>"
                + "</body>"
                + "</html>";
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
