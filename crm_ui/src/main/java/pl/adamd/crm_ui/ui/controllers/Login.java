package pl.adamd.crm_ui.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import pl.adamd.crm_ui.model.auth.request.LoginAccountRequestUI;
import pl.adamd.crm_ui.model.auth.response.LoggedUser;
import pl.adamd.crm_ui.ui.MainApplication;
import pl.adamd.crm_ui.web.service.auth.AuthorizationClientService;

import java.io.IOException;
import java.util.Objects;

@Controller
public class Login {

    @Autowired
    AuthorizationClientService authorizationClientService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MainFrame mainFrame;

    LoggedUser loggedUser;

    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Label message;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private void attachEvent(){

        username.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (closeBtn.isFocused()){
                    close();
                }
                if (loginBtn.isFocused()){
                    login();
                }
            }
        });
    }

    @FXML
    private void close() {
        loginBtn.getScene().getWindow().hide();
    }


    @FXML
    private void login() {

        if (username != null && !Objects.equals(username.getText(), "")
                && password != null && !Objects.equals(password.getText(), "")) {
            LoginAccountRequestUI loginAccountRequest = new LoginAccountRequestUI();

            loginAccountRequest.setEmployeeName(username.getText());
            loginAccountRequest.setPassword(password.getText());

            try {
                ResponseEntity<?> responseEntity = authorizationClientService.login(loginAccountRequest);
                loggedUser = (LoggedUser) responseEntity.getBody();
                mainFrame.show();
                close();
            } catch (HttpClientErrorException e) {
                this.message.setText("Wprowadź poprawne dane!");
            } catch (Exception e) {
                e.printStackTrace();
                this.message.setText("Brak połączenia z bazą!");
            }
        }
    }


    public void loadView(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
            fxmlLoader.setControllerFactory(MainApplication.getApplicationContext()::getBean);

            Parent parent = fxmlLoader.load();
            stage.setScene(new Scene(parent));

            Login controller = fxmlLoader.getController();
            controller.attachEvent();

            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
