module com.example.ucenicka_sluzba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.ucenicka_sluzba to javafx.fxml;
    exports com.example.ucenicka_sluzba;
}