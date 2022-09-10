package pl.adamd.crm_ui.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.adamd.crm_ui.ui.MainApplication;
import pl.adamd.crm_ui.ui.controllers.common.Dialog;
import pl.adamd.crm_ui.ui.utils.Menu;


@Controller
public class MainFrame {


    @FXML
    private VBox sideBar;
    @FXML
    private StackPane contentView;

    @FXML
    private void initialize() {
        loadView(Menu.Home);
    }


    private void loadView(Menu menu) {

        try {
            for (Node node : sideBar.getChildren()) {
                node.getStyleClass()
                    .remove("active");
                if (node.getId()
                        .equals(menu.name())) {
                    node.getStyleClass()
                        .add("active");
                }
            }
            contentView.getChildren()
                       .clear();

            FXMLLoader fxmlLoader = new FXMLLoader(MainFrame.class.getResource(menu.getFxml()));
            fxmlLoader.setControllerFactory(MainApplication.getApplicationContext()::getBean);
            Parent view = fxmlLoader.load();

            AbstractController controller = fxmlLoader.getController();
            controller.setTitle(menu);

            contentView.getChildren()
                       .add(view);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void show() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainFrame.class.getResource("MainFrame.fxml"));
            fxmlLoader.setControllerFactory(MainApplication.getApplicationContext()::getBean);
            Parent parent = fxmlLoader.load();
            stage.setScene(new Scene(parent));
            //            stage.setFullScreen(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void clickMenu(javafx.scene.input.MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();

        if (node.getId()
                .equals("Exit")) {
            Dialog.DialogBuilder.builder()
                                .title("Wyjście")
                                .message("Czy chcesz opuścić aplikację?")
                                .okActionListener(() -> sideBar.getScene()
                                                               .getWindow()
                                                               .hide())
                                .build()
                                .show();

        }
        else {
            Menu menu = Menu.valueOf(node.getId());
            loadView(menu);

        }
    }
}
