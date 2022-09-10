package pl.adamd.crm_ui.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.adamd.crm_ui.model.CustomerUI;
import pl.adamd.crm_ui.ui.controllers.popups.CustomerEdit;
import pl.adamd.crm_ui.web.service.customer.CustomerClientService;

import java.util.List;

@Controller
public class Customers extends AbstractController {

    @FXML
    private ComboBox<CustomerUI> customerUIComboBox;
    @FXML
    private TextField name;

    @Autowired
    private CustomerClientService customerClientService;

    @Autowired
    Login login;

    @FXML
    private void initialize() {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                CustomerUI customerUI = tableView.getSelectionModel()
                                                 .getSelectedItem();
                if (null != customerUI) {
                    CustomerEdit.edit(customerUI, this::save);
                }
            }
        });

    }

    @FXML
    private TableView<CustomerUI> tableView;

    @FXML
    private void search() {
        tableView.getItems()
                 .clear();
        List<CustomerUI> customerUIListByName =
                customerClientService.getByName(login.loggedUser.getAccessToken(), name.getText());

        tableView.getItems()
                 .addAll(customerUIListByName);
    }

    @FXML
    private void clear() {
        name.clear();
        tableView.getItems()
                 .clear();
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
