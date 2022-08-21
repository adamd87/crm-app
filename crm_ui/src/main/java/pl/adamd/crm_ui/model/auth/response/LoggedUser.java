package pl.adamd.crm_ui.model.auth.response;

import java.util.List;

public class LoggedUser {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String employeeName;
    private String email;
    private List<String> roles;

    public LoggedUser() {
    }

    public LoggedUser(String accessToken,
                      Long id, String employeeName,
                      String email, List<String> roles) {

        this.token = accessToken;
        this.id = id;
        this.employeeName = employeeName;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
