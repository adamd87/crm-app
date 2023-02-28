package pl.adamd.crm_ui.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.adamd.crm_ui.model.CustomerGetUI;
import pl.adamd.crm_ui.model.CustomerUI;
import pl.adamd.crm_ui.ui.controllers.popups.CustomerEdit;
import pl.adamd.crm_ui.web.service.customer.CustomerClientService;

import java.util.List;

@Controller
public class Customers extends AbstractController {

    @FXML
    private TextField name;

    @Autowired
    private CustomerClientService customerClientService;

    @FXML
    private TableView<CustomerGetUI> tableView;
    @FXML
    private TableColumn<CustomerGetUI, String> clientId;
    @FXML
    private TableColumn<CustomerGetUI, String> fullName;
    @FXML
    private TableColumn<CustomerGetUI, String> phone;
    @FXML
    private TableColumn<CustomerGetUI, String> email;
    @FXML
    private TableColumn<CustomerGetUI, Boolean> agreement;
    @FXML
    private TableColumn<CustomerGetUI, Boolean> business;
    @FXML
    private TableColumn<CustomerGetUI, String> address;
    @FXML
    private TableColumn<CustomerGetUI, String> info;

    @Autowired
    Login login;

    public List<CustomerGetUI> getCustomerGetUIList() {
        return customerClientService.getAllCustomers(login.loggedUser.getAccessToken());
    }

    @FXML
    private void initialize() {
        ObservableList<CustomerGetUI> customerGetUIObservableList =
                FXCollections.observableList(getCustomerGetUIList());

        clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        info.setCellValueFactory(new PropertyValueFactory<>("Info"));
        business.setCellValueFactory(new PropertyValueFactory<>("business"));
        agreement.setCellValueFactory(new PropertyValueFactory<>("agreement"));

        tableView.setItems(customerGetUIObservableList);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                CustomerGetUI customer = tableView.getSelectionModel()
                                                  .getSelectedItem();
                if (null != customer) {
                    CustomerUI customerUI =
                            customerClientService.getCustomerById(login.loggedUser.getAccessToken(), customer.getId());
                    CustomerEdit.edit(customerUI, this::save);
                }
            }
        });
    }

    @FXML
    private void search() {
        tableView.getItems()
                 .clear();
        List<CustomerGetUI> customerUIListByName =
                customerClientService.getByName(login.loggedUser.getAccessToken(), name.getText());

        tableView.getItems()
                 .addAll(customerUIListByName);
    }

    @FXML
    private void clear() {
        name.clear();
        initialize();
    }

    @FXML
    private void addNew() {
        CustomerEdit.addNew(this::save);
    }

    private void save(CustomerUI customerUI) {
        customerClientService.addNewCustomer(login.loggedUser.getAccessToken(), customerUI);
        name.setText(customerUI.getFullName());
        search();
    }
}
