package pl.adamd.crm_ui.web.service.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.adamd.crm_ui.model.CustomerUI;

import java.util.List;

@Service
public class CustomerClientServiceImpl implements CustomerClientService {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final RestTemplate restTemplate = restTemplate();

    private static HttpEntity<?> getHttpEntity(String token, CustomerUI customerUI) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(customerUI, headers);
    }

    @Override
    public List<CustomerUI> getAllCustomers(String token) {
        HttpEntity<?> entityReq = getHttpEntity(token, null);
        String defaultUrl = "http://localhost:8080/api/auth/customer/all";
        ResponseEntity<List<CustomerUI>> responseEntity =
                restTemplate.exchange(defaultUrl, HttpMethod.GET, entityReq, new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    @Override
    public List<CustomerUI> getByName(String token, String name) {
        HttpEntity<?> entityReq = getHttpEntity(token, null);
        String defaultUrl = "http://localhost:8080/api/auth/customer/by-name?name=" + name;
        ResponseEntity<List<CustomerUI>> responseEntity =
                restTemplate.exchange(defaultUrl, HttpMethod.GET, entityReq, new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    @Override
    public CustomerUI getCustomerById(String token, Long id) {
        HttpEntity<?> entityReq = getHttpEntity(token, null);
        String defaultUrl = "https://localhost:8080/api/auth/customer/by-id/{id}";
        String url = defaultUrl.replace("{id}", String.valueOf(id));

        return restTemplate.exchange(url, HttpMethod.GET, entityReq, CustomerUI.class)
                           .getBody();

    }

    @Override
    public CustomerUI addNewCustomer(String token, CustomerUI customer) {
        HttpEntity<?> entityReq = getHttpEntity(token, customer);
        String postUrl = "http://localhost:8080/api/auth/customer/add";

        return restTemplate.exchange(postUrl, HttpMethod.PUT, entityReq, CustomerUI.class)
                           .getBody();
    }

    @Override
    public CustomerUI updateCustomer(String token, Long id, CustomerUI customer) {
        HttpEntity<?> entityReq = getHttpEntity(token, customer);
        String defaultUrl = "http://localhost:8080/api/auth/customer/update/{id}";
        String patchUrl = defaultUrl.replace("{id}", String.valueOf(id));

        return restTemplate.exchange(patchUrl, HttpMethod.PATCH, entityReq, CustomerUI.class)
                           .getBody();
    }


}
