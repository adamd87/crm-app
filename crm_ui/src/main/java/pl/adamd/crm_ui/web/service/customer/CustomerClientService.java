package pl.adamd.crm_ui.web.service.customer;

import pl.adamd.crm_ui.model.CustomerUI;

import java.util.List;

public interface CustomerClientService {

    List<CustomerUI> getAllCustomers(String token);

    List<CustomerUI> getByName(String token, String name);

    CustomerUI getCustomerById(String token, Long id);

    CustomerUI addNewCustomer(String token, CustomerUI customer);

    CustomerUI updateCustomer(String token, Long id, CustomerUI customer);

}
