package pl.adamd.crm_ui.model.auth.request;

import lombok.Data;

import java.util.Set;

@Data
public class CreateAccountUI {

    private String employeeName;
    private String password;
    private String email;
    private Set<String> roles;

}
