package pl.adamd.crm_ui.ui.controllers.popups;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import pl.adamd.crm_ui.model.MaterialUI;
import pl.adamd.crm_ui.model.OfferUI;
import pl.adamd.crm_ui.web.service.material.MaterialClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class OfferEdit {

    private OfferUI offerUI;

    private Consumer<OfferUI> saveHandler;
    @FXML
    private Label title;
    @FXML
    private Label message;
    @FXML
    private ListView<String> materialUIListView;
    @FXML
    private Label clientFullName;
    @FXML
    private TextField clientId;
    @FXML
    private TextField comment;

    @Autowired
    MaterialClientService materialClientService;

    List<MaterialUI> resultMaterialList = new ArrayList<>();


    public static void addNew(Consumer<OfferUI> saveHandler, List<MaterialUI> materialUIList) {
        edit(null, saveHandler, materialUIList);
    }


    public List<String> generateListOfMaterial(List<MaterialUI> materialUIList) {
        List<String> result = new ArrayList<>();
        try {
            for (MaterialUI material : materialUIList) {
                String item = "[" + material.getId() + "] Nazwa: " + material.getName() + " / Kategoria: " +
                        material.getCategory() + " / Cena: " + material.getPrice();
                result.add(item);
            }
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }

        System.out.println(result);
        return result;
    }


    public static void edit(OfferUI offerUI, Consumer<OfferUI> saveHandler, List<MaterialUI> materialUIList) {
        try {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader fxmlLoader = new FXMLLoader(OfferEdit.class.getResource("OfferEdit.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));

            OfferEdit edit = fxmlLoader.getController();
            edit.init(offerUI, saveHandler, materialUIList);


            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(OfferUI offerUI, Consumer<OfferUI> saveHandler, List<MaterialUI> materialUIList) {
        this.saveHandler = saveHandler;
        ObservableList<String> observableList =
                FXCollections.observableArrayList(generateListOfMaterial(materialUIList));
        this.materialUIListView.setItems(observableList);

        if (offerUI == null) {
            this.offerUI = new OfferUI();
            this.title.setText("Dodaj nową ofertę");
        }
        else {
            this.offerUI = offerUI;
            this.title.setText("Edytuj ofertę");
            this.clientId.setText(String.valueOf(offerUI.getClientId()));
            this.clientFullName.setText(offerUI.getClientFullName());
            this.comment.setText(offerUI.getComment());
        }


        materialUIListView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                ObservableList<String> selectedItems = materialUIListView.getSelectionModel()
                                                                         .getSelectedItems();

                for (String s : selectedItems) {
                    String materialId = s.substring(1, s.indexOf("]") - 1);
                    System.out.println(materialId);
                    Optional<MaterialUI> materialUI = materialUIList.stream()
                                                                    .filter(materialUI1 -> materialUI1.getId()
                                                                                                      .equals(Long.valueOf(
                                                                                                              materialId)))
                                                                    .findAny();
                    materialUI.ifPresent(item -> resultMaterialList.add(item));

                    System.out.println("selected item " + s);
                }

            }

        });

    }

    @FXML
    private void close() {
        clientFullName.getScene()
                      .getWindow()
                      .hide();
    }

    @FXML
    private void save() {

        try {
            offerUI.setMaterialList(resultMaterialList);
            offerUI.setClientId(Long.valueOf(clientId.getText()));
            offerUI.setComment(comment.getText());

            saveHandler.accept(offerUI);
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
