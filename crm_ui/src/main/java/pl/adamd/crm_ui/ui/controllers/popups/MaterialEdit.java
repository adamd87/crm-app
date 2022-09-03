package pl.adamd.crm_ui.ui.controllers.popups;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.adamd.crm_ui.model.MaterialUI;

import java.util.function.Consumer;

public class MaterialEdit {

    @FXML
    private Label title;
    @FXML
    private Label message;
    @FXML
    private TextField name;
    @FXML
    private TextField producer;
    @FXML
    private TextField power;
    @FXML
    private TextField category;
    @FXML
    private TextField price;

    private Consumer<MaterialUI> saveHandler;
    private MaterialUI materialUI;

    public static void showView(Consumer<MaterialUI> saveHandler) {
        showView(null, saveHandler);
    }

    public static void showView(MaterialUI materialUI, Consumer<MaterialUI> saveHandler){
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader fxmlLoader = new FXMLLoader(MaterialEdit.class.getResource("MaterialEdit.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));

            MaterialEdit edit = fxmlLoader.getController();
            edit.init(materialUI, saveHandler);


            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void init(MaterialUI materialUI, Consumer<MaterialUI> saveHandler) {
        this.saveHandler = saveHandler;

        if (materialUI == null) {
            this.materialUI = new MaterialUI();
            this.title.setText("Dodaj nowy asortyment");
        } else {
            this.materialUI = materialUI;
            this.title.setText("Edytuj asotyment");
            this.name.setText(materialUI.getName());
            this.producer.setText(materialUI.getProducer());
            this.power.setText(materialUI.getPower());
            this.category.setText(materialUI.getCategory());
            this.price.setText(materialUI.getPrice());
        }
    }


    @FXML
    private void close(){
        name.getScene().getWindow().hide();
    }

    @FXML
    private void save(){

        try{
            materialUI.setName(name.getText());
            materialUI.setProducer(producer.getText());
            materialUI.setPower(power.getText());
            materialUI.setCategory(category.getText());
            materialUI.setPrice(price.getText());


            saveHandler.accept(materialUI);
            close();

        } catch (Exception e){
            e.printStackTrace();
            message.setText("Błąd!");
        }

    }

}
