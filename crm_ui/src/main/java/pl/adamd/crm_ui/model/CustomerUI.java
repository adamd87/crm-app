package pl.adamd.crm_ui.model;

import lombok.Data;

@Data
public class CustomerUI {

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String info;
    private Boolean agreement;
    private Boolean business;
    private String nip;

    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private String postCode;
    private String city;
    private String country;

}
