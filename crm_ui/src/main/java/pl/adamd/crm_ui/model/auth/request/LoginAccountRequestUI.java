package pl.adamd.crm_ui.model.auth.request;

import lombok.Data;

@Data
public class LoginAccountRequestUI {
    private String employeeName;
    private String password;
}
