package pl.adamd.crm_ui.web.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.adamd.crm_ui.model.auth.request.LoginAccountRequestUI;
import pl.adamd.crm_ui.model.auth.response.LoggedUser;

@Service
public class AuthorizationClientServiceImpl implements AuthorizationClientService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<LoggedUser> login(LoginAccountRequestUI loginUser) {
        String postUrl = "http://localhost:8080/api/auth/login";

        return restTemplate.postForEntity(postUrl, loginUser, LoggedUser.class);

    }

}
