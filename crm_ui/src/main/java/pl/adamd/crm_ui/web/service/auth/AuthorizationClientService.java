package pl.adamd.crm_ui.web.service.auth;

import org.springframework.http.ResponseEntity;
import pl.adamd.crm_ui.model.auth.request.LoginAccountRequestUI;
import pl.adamd.crm_ui.model.auth.response.LoggedUser;

public interface AuthorizationClientService {
    ResponseEntity<LoggedUser> login(LoginAccountRequestUI loginUser);


}
