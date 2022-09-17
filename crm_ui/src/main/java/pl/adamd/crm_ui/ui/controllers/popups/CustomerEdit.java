package pl.adamd.crm_ui.ui.controllers.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.web.client.HttpClientErrorException;
import pl.adamd.crm_ui.model.CustomerUI;

import java.util.function.Consumer;

public class CustomerEdit {

    private CustomerUI customerUI;
    private Consumer<CustomerUI> saveHandler;

    @FXML
    private Label title;
    @FXML
    private Label message;
    @FXML
    private TextField fullName;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField nip;
    @FXML
    private CheckBox agreement;
    @FXML
    private CheckBox business;
    @FXML
    private TextField street;
    @FXML
    private TextField buildingNumber;
    @FXML
    private TextField apartmentNumber;
    @FXML
    private TextField postCode;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private TextField info;

    public static void addNew(Consumer<CustomerUI> saveHandler) {
        edit(null, saveHandler);
    }

    public static void edit(CustomerUI customerUI, Consumer<CustomerUI> saveHandler) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader fxmlLoader = new FXMLLoader(CustomerEdit.class.getResource("CustomerEdit.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));

            CustomerEdit edit = fxmlLoader.getController();
            edit.init(customerUI, saveHandler);


            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(CustomerUI customerUI, Consumer<CustomerUI> saveHandler) {
        this.saveHandler = saveHandler;

        if (customerUI == null) {
            this.customerUI = new CustomerUI();
            this.title.setText("Dodaj nowego klienta");
        }
        else {
            this.customerUI = customerUI;
            this.title.setText("Edytuj dane klietna");
            this.fullName.setText(customerUI.getFullName());
            this.phone.setText(customerUI.getPhone());
            this.email.setText(customerUI.getEmail());
            this.info.setText(customerUI.getInfo());
            this.agreement.setSelected(customerUI.getAgreement());
            this.business.setSelected(customerUI.getBusiness());
            this.street.setText(customerUI.getStreet());
            this.buildingNumber.setText(customerUI.getBuildingNumber());
            this.apartmentNumber.setText(customerUI.getApartmentNumber());
            this.postCode.setText(customerUI.getPostCode());
            this.city.setText(customerUI.getCity());
            this.country.setText(customerUI.getCountry());
        }
    }

    @FXML
    private void close() {
        fullName.getScene()
                .getWindow()
                .hide();
    }

    @FXML
    private void save() {

        try {
            customerUI.setFullName(fullName.getText());
            customerUI.setEmail(email.getText());
            customerUI.setPhone(phone.getText());
            customerUI.setNip(nip.getText());
            customerUI.setAgreement(agreement.isSelected());
            customerUI.setBusiness(business.isSelected());
            customerUI.setStreet(street.getText());
            customerUI.setBuildingNumber(buildingNumber.getText());
            customerUI.setApartmentNumber(apartmentNumber.getText());
            customerUI.setPostCode(postCode.getText());
            customerUI.setCity(city.getText());
            customerUI.setCountry(country.getText());
            customerUI.setInfo(info.getText());

            saveHandler.accept(customerUI);
            close();
        } catch (HttpClientErrorException e) {
            if (e.contains(HttpClientErrorException.class)) {
                message.setText("Błąd danych");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Błąd!");
        }

    }


}
