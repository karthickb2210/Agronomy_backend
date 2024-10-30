package in.ironvalleyagro.Agronomy.Services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//
//
//@Service
public class SmsService {
//    @Autowired
//    private final RestTemplate restTemplate;
//
//    // Inject properties from application.properties
//    @Value("${messagecentral.api.url}")
//    private String apiUrl;
//
//    @Value("${messagecentral.api.authToken}")
//    private String authToken;
//
//    public SmsService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public String sendSms(String countryCode, String customerId, String flowType, String mobileNumber) {
//        // Build URL with parameters
//        String url = String.format("%s/verification/v3/send?countryCode=%s&customerId=%s&flowType=%s&mobileNumber=%s",
//                apiUrl, countryCode, customerId, flowType, mobileNumber);
//
//        // Set headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        headers.set("authToken", authToken);
//
//        // Create an empty body for POST request
//        HttpEntity<String> entity = new HttpEntity<>("", headers);
//
//        // Make the request
//        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
//
//
//    }
//
//    public String sendOrderSms(String countryCode, String customerId, String senderId, String type, String flowType, String mobileNumber, String message) {
//        // Construct URL with query parameters
//        String url = String.format("%s/verification/v3/send?countryCode=%s&customerId=%s&senderId=%s&type=%s&flowType=%s&mobileNumber=%s&message=%s",
//                apiUrl, countryCode, customerId, senderId, type, flowType, mobileNumber, message);
//
//        // Set headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        headers.set("authToken", authToken);
//
//        // Create an empty body for the POST request
//        HttpEntity<String> entity = new HttpEntity<>("", headers);
//
//        // Make the request
//        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
//    }
}
//
