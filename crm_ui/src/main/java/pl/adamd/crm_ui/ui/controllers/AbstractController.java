package pl.adamd.crm_ui.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.adamd.crm_ui.ui.utils.Menu;


public abstract class AbstractController {

    @FXML
    private Label title;

    public void setTitle(Menu menu){
        this.title.setText(menu.getTitle());
    }
}
