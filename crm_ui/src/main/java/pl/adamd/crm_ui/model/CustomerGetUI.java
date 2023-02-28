package pl.adamd.crm_ui.model;

import lombok.Data;

@Data
public class CustomerGetUI {
    private Long id;
    private String clientId;
    private String fullName;
    private String phone;
    private String email;
    private String info;
    private boolean agreement;
    private boolean business;
    private String nip;
    private String address;
}
