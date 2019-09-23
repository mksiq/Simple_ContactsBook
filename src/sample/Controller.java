package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainScene;
    @FXML
    private TableView contactsTable;
    private ContactData data;
    public void initialize(){
        data = new ContactData();
        data.loadContacts();
        contactsTable.setItems(data.getContacts());
    }
    @FXML
    public void showAddContactDialog(){
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainScene.getScene().getWindow());
        dialog.setTitle("New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ContactController contactController = fxmlLoader.getController();
            Contact newContact = contactController.getNewContact();
            data.addContact(newContact);
            data.saveContacts();
        }
    }


    @FXML
    public void showEditContactDialog(){
        Contact selectedContact = (Contact) contactsTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(("No Contact Selected."));
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want do edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainScene.getScene().getWindow());
        dialog.setTitle("Edit Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactController contactController = fxmlLoader.getController();
        contactController.editContact(selectedContact);

        Optional<ButtonType> result=dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactController.updateContact(selectedContact);
            data.saveContacts();
        }

    }

    @FXML
    public void showDeleteContactDialog(){
        Contact selectedContact = (Contact) contactsTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(("No Contact Selected."));
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want do delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
        alert.setTitle("Delete Contact");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this contact: " + selectedContact.getFirstName()+" ?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.deleteContact(selectedContact);
            data.saveContacts();
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent){
        Contact selectedItem = (Contact) contactsTable.getSelectionModel().getSelectedItem();
        if(keyEvent.getCode().equals(KeyCode.DELETE)){
            Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
            alert.setTitle("Delete Contact");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this contact: " + selectedItem.getFirstName()+" ?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                data.deleteContact(selectedItem);
                data.saveContacts();
            }
        }

    }

//    @FXML
//    public void orderAscAlpha(){
//        contactsTable.getItems().clear();
//        data = new ContactData();
//        data.loadContacts();
//
//        ObservableList<Contact> sortedList = FXCollections.observableArrayList();
//        sortedList = data.getContacts().sorted();
//
//
//
//
//
//        contactsTable.setItems(sortedList);
//
//    }
}
