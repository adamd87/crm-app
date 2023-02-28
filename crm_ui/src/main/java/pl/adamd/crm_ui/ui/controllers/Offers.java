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
import pl.adamd.crm_ui.model.MaterialUI;
import pl.adamd.crm_ui.model.OfferUI;
import pl.adamd.crm_ui.model.OfferUIViewToList;
import pl.adamd.crm_ui.ui.controllers.popups.OfferEdit;
import pl.adamd.crm_ui.web.service.material.MaterialClientService;
import pl.adamd.crm_ui.web.service.offer.OfferClientService;

import java.util.List;

@Controller
public class Offers extends AbstractController {

    @FXML
    private TextField name;
    @FXML
    private TableView<OfferUIViewToList> tableView;
    @FXML
    private TableColumn<OfferUIViewToList, String> clientId;
    @FXML
    private TableColumn<OfferUIViewToList, String> clientFullName;
    @FXML
    private TableColumn<OfferUIViewToList, String> costOfMaterials;
    @FXML
    private TableColumn<OfferUIViewToList, String> myWarmthACost;
    @FXML
    private TableColumn<OfferUIViewToList, String> myWarmthBCost;
    @FXML
    private TableColumn<OfferUIViewToList, String> cleanAirA;
    @FXML
    private TableColumn<OfferUIViewToList, String> cleanAirB;
    @FXML
    private TableColumn<OfferUIViewToList, String> comment;

    @Autowired
    Login login;

    @Autowired
    private OfferClientService offerClientService;

    @Autowired
    private MaterialClientService materialClientService;

    public List<OfferUIViewToList> getOffersToList() {
        return offerClientService.getAllOffers(login.loggedUser.getAccessToken());
    }

    public List<MaterialUI> getMaterialsList() {
        return materialClientService.getAllMaterials(login.loggedUser.getAccessToken());
    }

    @FXML
    private void initialize() {
        ObservableList<OfferUIViewToList> customerGetUIObservableList = FXCollections.observableList(getOffersToList());

        clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        clientFullName.setCellValueFactory(new PropertyValueFactory<>("clientFullName"));
        costOfMaterials.setCellValueFactory(new PropertyValueFactory<>("costOfMaterials"));
        myWarmthACost.setCellValueFactory(new PropertyValueFactory<>("myWarmthACost"));
        myWarmthBCost.setCellValueFactory(new PropertyValueFactory<>("myWarmthBCost"));
        cleanAirA.setCellValueFactory(new PropertyValueFactory<>("cleanAirA"));
        cleanAirB.setCellValueFactory(new PropertyValueFactory<>("cleanAirB"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        tableView.setItems(customerGetUIObservableList);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                OfferUIViewToList offer = tableView.getSelectionModel()
                                                   .getSelectedItem();
                if (null != offer) {
                    OfferUI offerUI = offerClientService.getOfferById(login.loggedUser.getAccessToken(), offer.getId());
                    List<MaterialUI> materialUIList = getMaterialsList();
                    OfferEdit.edit(offerUI, this::save, materialUIList);
                }
            }
        });
    }

    @FXML
    private void search() {
        tableView.getItems()
                 .clear();
        List<OfferUIViewToList> customerUIListByName =
                offerClientService.getByName(login.loggedUser.getAccessToken(), name.getText());

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
        OfferEdit.addNew(this::save, getMaterialsList());
    }

    private void save(OfferUI offerUI) {
        offerClientService.addOffer(login.loggedUser.getAccessToken(), offerUI);
        name.setText(String.valueOf(offerUI.getClientId()));
        search();
    }

}
