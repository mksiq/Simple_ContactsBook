package sample;

import javafx.beans.property.SimpleStringProperty;

public class Contact implements Comparable<Contact>{

    private SimpleStringProperty firstName = new SimpleStringProperty("");
    private SimpleStringProperty lastName = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");
    private SimpleStringProperty notes = new SimpleStringProperty("");

    public Contact(String firstName, String lastName, String phoneName, String notes) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phone.set(phoneName);
        this.notes.set(notes);
    }

    public Contact() {
        super();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneNameProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    @Override
    public int compareTo(Contact con) {
        if(this.getFirstName().compareToIgnoreCase(con.getFirstName()) > 0){
            return 1;
        } else if (this.getFirstName().compareToIgnoreCase(con.getFirstName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
