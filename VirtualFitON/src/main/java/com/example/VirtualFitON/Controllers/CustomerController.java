package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Service.BrandMeasurementService;
import com.example.VirtualFitON.Service.CartService;
import com.example.VirtualFitON.Service.CustomerMeasurementService;
import com.example.VirtualFitON.Service.CustomerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/customer")

public class CustomerController {
    private final CustomerService customerService;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BrandMeasurementService brandMeasurementService;

    @Autowired
    private CustomerMeasurementService customerMeasurementService;

    @Autowired
    private CartService cartService;




    String API_KEY = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJuSnVpYzVwbXk1T1hGSjVmY1RIQTdUNVktRHZVbVVOR2xxVHBqS0hDVnU4In0.eyJleHAiOjE3MTkyNzY5OTYsImlhdCI6MTcxOTI0MDk5NywiYXV0aF90aW1lIjoxNzE5MjQwOTk2LCJqdGkiOiIzMTM0MWJjNS0zY2RhLTRhMjQtYjdhZS02ZDhlNjBlMTg2ZDciLCJpc3MiOiJodHRwczovL2F1dGgubWVzaGNhcGFkZS5jb20vcmVhbG1zL21lc2hjYXBhZGUtbWUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiYzFjMjgxMGQtOTdkYy00ZjlkLWIzYmQtNDljZGY5OTIyOTdhIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoibWVzaGNhcGFkZS1tZSIsIm5vbmNlIjoiMmVmZTE3OTUtMDE0MS00ODlkLTgxZDItYzFhMzU1YzQzYmU1Iiwic2Vzc2lvbl9zdGF0ZSI6IjAyNTgyMzQ3LTYwMjktNDk5Yi05ZmQwLTRjY2E3YTEzMWE0YSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cHM6Ly9tZXNoY2FwYWRlLmNvbSIsImh0dHBzOi8vbWUubWVzaGNhcGFkZS5jb20iLCJodHRwczovL21lc2hjYXBhZGUubWUiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1nY21jIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiMDI1ODIzNDctNjAyOS00OTliLTlmZDAtNGNjYTdhMTMxYTRhIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJPc2hhbiBEZXZpbmRhIiwicHJlZmVycmVkX3VzZXJuYW1lIjoib3NoYW4uZGV2aW5kYTM1QGdtYWlsLmNvbSIsImdpdmVuX25hbWUiOiJPc2hhbiIsImZhbWlseV9uYW1lIjoiRGV2aW5kYSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NJTDVRUzF4Z1hlSm5nNk1fZFM2WGlvcnQ0cnpuVVlLbkpYWVJBQ0lxV2pfb05FUkJjPXM5Ni1jIiwiZW1haWwiOiJvc2hhbi5kZXZpbmRhMzVAZ21haWwuY29tIn0.aFJ0uHSPDyMd2bEj53ZCOWYj0ZGsZzaRAvGjQs3WK7dw085uz8SMqdBXbBLCtItUnXzzuIaRuUd9SJZa2p3vm72QLkEsAXRieIdvF-2g7zCAnvznlLxSqxJ-z0uYBhuqn8QNK2tF6cge04GY4KrDJzbLzKCPM0D6vE2LUSoFUN3tD1TRzt6IVh__Im_ze1DdQ4P-7ZsXvUoVl79R56qwioxX8vbiiMIV4GFu63jzpXWGPV_q75FNp29q5Qcy4wD8uci0p4WtgQrMkrXtnAb5-c9d05iGjLFkt9rgT5BOG2azMWUNXp_9p0Cc0QBZCDX9-Xh-FioebH5ghi8XLBPrqA";

    @Autowired
    public CustomerController(CustomerService customerService, RedisTemplate<String, String> redisTemplate) {
        this.customerService = customerService;
        this.redisTemplate = redisTemplate;
    }


    @GetMapping("/getCustomerId")
    public ResponseEntity<String> getCustomerId(HttpServletRequest request) {


        String authorizationHeader = request.getHeader("Authorization");

        String sessionId = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            sessionId = authorizationHeader.substring(7);
        }

        if (sessionId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing session ID");
        }
        System.out.println("auth head:"+ authorizationHeader);

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String customerId = hashOperations.get( sessionId, "customerId");

        if (customerId != null) {
            return ResponseEntity.ok( customerId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer ID not found for the session ID");
        }
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpDTO signUpDTO, HttpServletRequest request) {
        try {
            Customer customer=customerService.signUpCustomer(signUpDTO);
            if (customer != null) {
                HttpSession session = request.getSession(false);
                if (session == null) {
                    session = request.getSession(true);
                    session.setMaxInactiveInterval(30 * 60);
                } else {
                    request.changeSessionId();
                }

                HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
                hashOperations.put(session.getId(), "customerId", String.valueOf(customer.getCustomerId()));

                Map<String, String> response = new HashMap<>();
                response.put("sessionId", session.getId());

                return ResponseEntity.ok(response);         }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }

         }
        catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login: " + e.getMessage());
    }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            Customer customer = customerService.LoginCustomer(loginDTO);
            if (customer != null) {
                HttpSession session = request.getSession(false);
                if (session == null) {
                    session = request.getSession(true);
                    session.setMaxInactiveInterval(30 * 60);
                } else {
                    request.changeSessionId();
                }


                HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
                hashOperations.put(session.getId(), "customerId", String.valueOf(customer.getCustomerId()));

                Map<String, String> response = new HashMap<>();
                response.put("sessionId", session.getId());

                return ResponseEntity.ok(response);            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login: " + e.getMessage());
        }
    }

    @GetMapping("/cart/{customerId}")
    public ResponseEntity<?> getCartItems(@PathVariable int customerId) {
        try {
            List<CartItemDTO> products = customerService.getCustomerCartItems(customerId);
            System.out.println("cart items count at controller"+ products.size());
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PostMapping("/getmeasurements")
    public ResponseEntity<?> getMeasurements(@RequestBody GetMeasurementDTO requestBody) {

        String measurementsUrl = "http://bodymeasurements-service:6000/measurements?param1={param1}&param2={param2}&param3={param3}";
        System.out.println("Inside get measurements..");

        Map<String, String> params = new HashMap<>();
        params.put("param1", requestBody.getGender());
        params.put("param2", String.valueOf(requestBody.getHeight()/100));
        params.put("param3", String.valueOf(requestBody.getWeight()));
        System.out.println("TEST 02");
        try {
            // Fetch measurements
            ResponseEntity<String> response = restTemplate.getForEntity(measurementsUrl, String.class, params);
            String responseBody = response.getBody();
            System.out.println("[+] got the output from api call");

            // Convert response body to Java Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            System.out.println("[+] output map: "+responseMap.toString());

            Map<String, Object> meshcapadeMap = customerMeasurementService.mapCustomerMeasurements(responseMap);
            System.out.println("[+] output meshcapadeMap : "+meshcapadeMap.toString());

            String modelUrl = "Test";
            //String modelUrl = createBodyModelUsingMeshcapade(meshcapadeMap, requestBody.getGender()); // Replace this with the actual model URL logic
            System.out.println("[+] got the body mesh url : "+modelUrl);

            customerMeasurementService.saveCustomerMeasurements(requestBody.getCustomerId(), responseMap, modelUrl);
            System.out.println("[+] customer measurement saved.");

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("customerId", requestBody.getCustomerId());
            responseData.put("measurements", responseMap);
            responseData.put("modelUrl", modelUrl);
            System.out.println("[+] created response object: "+ responseData.toString());


            return ResponseEntity.ok().body(responseData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching measurements for Customer ID " + requestBody.getCustomerId() + ": " + e.getMessage());
        }
    }
    private String createBodyModelUsingMeshcapade(Map<String, Object> measurements, String gender) throws Exception {
        String meshcapadeUrl = "https://api.meshcapade.com/avatars/create/from-measurements";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", API_KEY); // Replace with your actual API key

        Map<String, Object> body = new HashMap<>();
        body.put("gender", gender);
        body.put("measurements", measurements);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(meshcapadeUrl, requestEntity, Map.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = responseEntity.getBody();
            return (String) responseBody.get("model_url"); // Assuming the response contains a `model_url` field
        } else {
            throw new Exception("Failed to create body model: " + responseEntity.getStatusCode());
        }
    }
    @GetMapping("/getMatchingSize")
    public ResponseEntity<?> getMatchingSize(@RequestParam int customerId, @RequestParam String productId) {
        String url = "http://bodymeasurements-service:6000/sizematch";

        try {
            Map<String, Map<String, Double>> brandMeasurements = brandMeasurementService.getBrandMeasurementsByBrandId(productId);
            System.out.println("[+] got brand measuremnets: "+ brandMeasurements.toString());
            Map<String, Double> customerMeasurements = customerMeasurementService.getCustomerMeasurementObject(customerId);
            System.out.println("[+] got customer measuremnets: "+ customerMeasurements.toString());

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("product_measurements", brandMeasurements);
            requestBody.put("customer_measurements", customerMeasurements);
            System.out.println("[+] request body: "+ requestBody.toString());


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(new ObjectMapper().writeValueAsString(requestBody), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("[+] response recived "+response.getStatusCode()+ " "+ response.getBody());

            return ResponseEntity.ok(response.getBody());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching matching size: " + e.getMessage());
        }
    }
    @PostMapping("/saveMeasurements")
    public ResponseEntity<?> saveMeasurements(@RequestBody CustomerMeasurementDTO responseBody) {
        try {
            customerMeasurementService.updateCustomerMeasurement(responseBody);
            return ResponseEntity.ok("Measurements saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving measurements: " + e.getMessage());
        }
    }

    @PostMapping("cart/clear/{customerId}")
    public ResponseEntity<String> clearCart(@PathVariable int customerId) {
        try {
            cartService.clearCart(customerId);
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to clear the cart");
        }
    }
}
