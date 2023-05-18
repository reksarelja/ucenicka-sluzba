module com.example.ucenicka_sluzba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    exports com.example.ucenicka_sluzba;
    opens com.example.ucenicka_sluzba to javafx.fxml;
}