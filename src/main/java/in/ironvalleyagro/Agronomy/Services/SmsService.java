package in.ironvalleyagro.Agronomy.Services;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    public String sendSms(String to, String body) {
        Message message = Message.creator(
                new PhoneNumber(to),      // To number
                new PhoneNumber(twilioPhoneNumber), // From number
                body                      // Message content
        ).create();

        return message.getSid();  // Returns message SID if sent successfully
    }
}

