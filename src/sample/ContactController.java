package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactController {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextArea txtNotes;

    public Contact getNewContact(){
        String firstName = txtFirstName.getText();
        if(txtFirstName.getText().isEmpty()){
            firstName=" ";
        }
        String lastName = txtLastName.getText();
        if(txtLastName.getText().isEmpty()){
            lastName=" ";
        }
        String phone = txtPhone.getText();
        if(txtPhone.getText().isEmpty()){
            phone=" ";
        }
        String notes = txtNotes.getText();
        if(txtNotes.getText().isEmpty()){
            notes=" ";
        }

        Contact newContact = new Contact(firstName, lastName, phone, notes);
        return newContact;
    }

    public void editContact(Contact selectedContact) {
       txtFirstName.setText(selectedContact.getFirstName());
       txtLastName.setText(selectedContact.getLastName());
       txtPhone.setText(selectedContact.getPhone());
       txtNotes.setText(selectedContact.getNotes());
    }

    public void updateContact(Contact selectedContact) {
        selectedContact.setFirstName(txtFirstName.getText());
        selectedContact.setLastName(txtLastName.getText());
        selectedContact.setPhone(txtPhone.getText());
        selectedContact.setNotes(txtNotes.getText());
    }
}
