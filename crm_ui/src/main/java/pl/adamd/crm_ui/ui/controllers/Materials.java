package pl.adamd.crm_ui.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.SVGPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.adamd.crm_ui.model.MaterialUI;
import pl.adamd.crm_ui.ui.controllers.popups.MaterialEdit;
import pl.adamd.crm_ui.web.service.material.MaterialClientService;


@Controller
public class Materials extends AbstractController {

    @FXML
    private TilePane container;

    @Autowired
    Login login;

    @Autowired
    MaterialClientService materialClientService;

    @FXML
    private void initialize() {
        container.getChildren().clear();
        materialClientService.getAllMaterials(login.getLoggedUser().getAccessToken())
                .forEach(materialUI -> container.getChildren().add(new MaterialItem(materialUI)));

    }

    @FXML
    private void addNew() {
        MaterialEdit.showView(this::save);
    }

    private void save(MaterialUI materialUI) {
        materialClientService.addNewMaterial(login.getLoggedUser().getAccessToken(), materialUI);
        reload();
    }

    private void reload() {
        container.getChildren().clear();
        materialClientService.getAllMaterials(login.getLoggedUser().getAccessToken())
                .forEach(materialUI -> container.getChildren().add(new MaterialItem(materialUI)));

    }



    private class MaterialItem extends HBox {

        private SVGPath icon;
        private Label name;

        private MaterialUI materialUI;

        public MaterialItem(MaterialUI materialUI) {

            icon = new SVGPath();
            icon.setContent("M30.5 0h-12c-0.825 0-1.977 0.477-2.561 1.061l-14.879 14.879c-0.583 0.583-0.583 1.538 0 2.121l12.879 12.879c0.583 0.583 1.538 0.583 2.121 0l14.879-14.879c0.583-0.583 1.061-1.736 1.061-2.561v-12c0-0.825-0.675-1.5-1.5-1.5zM23 12c-1.657 0-3-1.343-3-3s1.343-3 3-3 3 1.343 3 3-1.343 3-3 3z");
            name = new Label();
            name.setText(materialUI.getName());

            getChildren().addAll(icon, name);
            getStyleClass().add("materials-item");

            setOnMouseClicked(event -> {


                if (event.getClickCount() == 2){

                }
            });
        }
    }
}
