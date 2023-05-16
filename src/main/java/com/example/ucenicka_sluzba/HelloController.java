package com.example.ucenicka_sluzba;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.FloatStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.IntBuffer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    Connector database = null;
    TableConstructor tableConstructor = null;
    @FXML
    TableView<Ucenik> tabelaUcenika;
    @FXML
    TableColumn<Ucenik, String> ucenikIme;
    @FXML
    TableColumn<Ucenik, String> ucenikPrezime;
    @FXML
    TableColumn<Ucenik, String> ucenikAdresa;
    @FXML
    TableColumn<Ucenik, String> ucenikDatum;
    @FXML
    TableColumn<Ucenik, String> ucenikMajka;
    @FXML
    TableColumn<Ucenik, String> ucenikOtac;
    @FXML
    TableColumn<Ucenik, Float> ucenikProsek;
    @FXML
    TableColumn<Ucenik, String> ucenikPol;
    @FXML
    TableColumn<Ucenik, String> ucenikRazred;
    @FXML
    TableColumn<Ucenik, String> ucenikRazredni;
    @FXML
    TableColumn<Ucenik, Float> ucenikUspeh;
    @FXML
    TextField imeDodavanje;
    @FXML
    TextField prezimeDodavanje;
    @FXML
    TextField datumDodavanje;
    @FXML
    TextField prosekDodavanje;
    @FXML
    TextField uspehDodavanje;
    @FXML
    TextField otacDodavanje;
    @FXML
    TextField majkaDodavanje;
    @FXML
    TextField razredDodavanje;
    @FXML
    TextField razredniDodavanje;
    @FXML
    TextField adresaDodavanje;
    @FXML
    ComboBox<String> polDodavanje;
    @FXML
    Button unosBtn;
    @FXML
    ComboBox<String> polPretraga;
    @FXML
    TextField imePretraga;
    @FXML
    TextField prezimePretraga;
    @FXML
    TextField adresaPretraga;
    @FXML
    TextField uspehPretraga;
    @FXML
    ComboBox<String> cBoxRazreda;
    @FXML
    TextField imePredmeta;
    @FXML
    TextField razredPredmeta;
    @FXML
    TableView<Predmet> tabelaPredmeta;
    @FXML
    TableColumn<Predmet, String> imePredmetaT;
    @FXML
    TableColumn<Predmet, String> razredPredmetaT;
    @FXML
    Button unosBtn2;
    @FXML
    Button generisiBtn;
    @FXML
    TextField imePrezime;
    @FXML
    TextField sinKci;
    @FXML
    TextField rodjen;
    @FXML
    TextField godine;
    @FXML
    TextField opstina;
    @FXML
    TextField drzava;
    @FXML
    TextField nazivSkole;
    @FXML
    TextField mesto;
    @FXML
    TextField gimSmer;
    @FXML
    TextField obrazProf;
    @FXML
    TextField od;
    @FXML
    TextField doo;
    @FXML
    TextField razred;
    @FXML
    TextField delBrojPotvrde;
    @FXML
    TextField datumPotvrde;
    @FXML
    Button izmeniBtn;
    @FXML
    AnchorPane potvrdaPane;
    ObservableList<Ucenik> lista_ucenika = FXCollections.observableArrayList();
    ObservableList<String> lista_polova = FXCollections.observableArrayList();
    ObservableList<Predmet> lista_predmeta = FXCollections.observableArrayList();
    ObservableList<String> lista_razreda = FXCollections.observableArrayList();

    static int maxID = 0;
    static int maxID2 = 0;
    static int delId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lista_polova.addAll("М", "Ж", "");
        polDodavanje.setItems(lista_polova);
        polPretraga.setItems(lista_polova);
        polDodavanje.setValue("М");

        lista_razreda.addAll("IV-1", "IV-2", "IV-3", "");
        cBoxRazreda.setItems(lista_razreda);

        imeDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        prezimeDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        majkaDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        otacDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        datumDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        adresaDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        prosekDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        uspehDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        razredDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());
        razredniDodavanje.textProperty().addListener((observableValue, s, t1) -> checkIfEmpty());

        tabelaUcenika.getSelectionModel().selectedItemProperty().addListener((observableValue, ucenik, t1) -> tabelaUcenika.setOnKeyPressed(e -> {
            delete(e, t1.getUcenik_id());
        }));
        tabelaUcenika.getSelectionModel().selectedItemProperty().addListener((observableValue, ucenik, t1) ->
            ifSelected()
        );
        imePredmeta.textProperty().addListener(((observableValue, s, t1) -> checkIfEmpty2()));
        razredPredmeta.textProperty().addListener(((observableValue, s, t1) -> checkIfEmpty2()));

        tabelaPredmeta.getSelectionModel().selectedItemProperty().addListener((observableValue, predmet, t1) -> tabelaPredmeta.setOnKeyPressed(e -> {
            deleteP(e, t1.getPredmet_id());
        }));

            ucenikIme.setCellValueFactory(new PropertyValueFactory<>("ucenik_ime"));
            ucenikPrezime.setCellValueFactory(new PropertyValueFactory<>("ucenik_prezime"));
            ucenikAdresa.setCellValueFactory(new PropertyValueFactory<>("ucenik_adresa"));
            ucenikDatum.setCellValueFactory(new PropertyValueFactory<>("ucenik_datum_rodjenja"));
            ucenikMajka.setCellValueFactory(new PropertyValueFactory<>("ucenik_majka"));
            ucenikOtac.setCellValueFactory(new PropertyValueFactory<>("ucenik_otac"));
            ucenikProsek.setCellValueFactory(new PropertyValueFactory<>("ucenik_prosek"));
            ucenikPol.setCellValueFactory(new PropertyValueFactory<>("ucenik_pol"));
            ucenikRazred.setCellValueFactory(new PropertyValueFactory<>("ucenik_razred"));
            ucenikRazredni.setCellValueFactory(new PropertyValueFactory<>("ucenik_razredni_staresina"));
            ucenikUspeh.setCellValueFactory(new PropertyValueFactory<>("ucenik_uspeh"));

            imePredmetaT.setCellValueFactory(new PropertyValueFactory<>("predmet_ime"));
            razredPredmetaT.setCellValueFactory(new PropertyValueFactory<>("predmet_razred"));

        ucenikIme.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikIme.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_ime(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_ime = '" + ucenik.ucenik_ime + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            });
            ucenikPrezime.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikPrezime.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_prezime(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_prezime = '" + ucenik.ucenik_prezime + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikDatum.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikDatum.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_datum_rodjenja(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_datum_rodjenja = '" + ucenik.ucenik_datum_rodjenja + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikAdresa.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikAdresa.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_adresa(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_adresa = '" + ucenik.ucenik_adresa + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikPol.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikPol.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_pol(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_pol = '" + ucenik.ucenik_pol + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikProsek.setCellFactory(TextFieldTableCell.<Ucenik, Float>forTableColumn(new FloatStringConverter()));
            ucenikProsek.setOnEditCommit(event -> {
                try {
                    if(event.getNewValue() <= 5) {
                        Ucenik ucenik = event.getRowValue();
                        ucenik.setUcenik_prosek(event.getNewValue());
                        PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_prosek = '" + ucenik.ucenik_prosek + "' where ucenik_id = " + ucenik.ucenik_id + "");
                        pstmt.executeUpdate();
                    }
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikRazred.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikRazred.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_razred(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_razred = '" + ucenik.ucenik_razred + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikRazredni.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikRazredni.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_razredni_staresina(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_razredni_staresina = '" + ucenik.ucenik_razredni_staresina + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikOtac.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikOtac.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_otac(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_otac = '" + ucenik.ucenik_otac + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikMajka.setCellFactory(TextFieldTableCell.forTableColumn());
            ucenikMajka.setOnEditCommit(event -> {
                try {
                    Ucenik ucenik = event.getRowValue();
                    ucenik.setUcenik_majka(event.getNewValue());
                    PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_majka = '" + ucenik.ucenik_majka + "' where ucenik_id = " + ucenik.ucenik_id + "");
                    pstmt.executeUpdate();
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });
            ucenikUspeh.setCellFactory(TextFieldTableCell.<Ucenik, Float>forTableColumn(new FloatStringConverter()));
            ucenikUspeh.setOnEditCommit(event -> {
                try {
                    if(event.getNewValue() <= 5) {
                        Ucenik ucenik = event.getRowValue();
                        ucenik.setUcenik_uspeh(event.getNewValue());
                        PreparedStatement pstmt = database.conn.prepareStatement("update ucenik set ucenik_prosek = '" + ucenik.ucenik_prosek + "' where ucenik_id = " + ucenik.ucenik_id + "");
                        pstmt.executeUpdate();
                    }
                }catch(SQLException se){
                    se.printStackTrace();

                }
            });

        try{
            database = Connector.getInstance();
            database.checkIfExists("");

            tableConstructor = TableConstructor.getInstance();

            Statement stmt = database.getConn().createStatement();
            stmt.execute("select * from ucenik");

            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                lista_ucenika.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                maxID = Math.max(rs.getInt(1), maxID) + 1;
            }
            tabelaUcenika.getItems().setAll(lista_ucenika);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        try{
            database = Connector.getInstance();
            Statement stmt = database.conn.createStatement();
            stmt.execute("select * from predmet");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                lista_predmeta.add(new Predmet(rs.getInt(1), rs.getString(2), rs.getString(3)));
                maxID2 = Math.max(rs.getInt(1), maxID2) + 1;
            }
            tabelaPredmeta.setItems(lista_predmeta);
        }
        catch(SQLException se){
            se.printStackTrace();
        }

    }
    public void clearLabels(){
        imeDodavanje.setText("");
        prezimeDodavanje.setText("");
        datumDodavanje.setText("");
        otacDodavanje.setText("");
        majkaDodavanje.setText("");
        adresaDodavanje.setText("");
        razredniDodavanje.setText("");
        razredDodavanje.setText("");
        prosekDodavanje.setText("");
        uspehDodavanje.setText("");
    }
    public void checkIfEmpty(){
        unosBtn.setDisable(imeDodavanje.getText().equals("") || prezimeDodavanje.getText().equals("") || datumDodavanje.getText().equals("") || adresaDodavanje.getText().equals("") || otacDodavanje.getText().equals("") || majkaDodavanje.getText().equals("") || razredniDodavanje.getText().equals("") || razredDodavanje.getText().equals("") || prosekDodavanje.getText().equals("") || uspehDodavanje.getText().equals("") || Float.parseFloat(uspehDodavanje.getText()) > 5 || Float.parseFloat(prosekDodavanje.getText()) > 5);
    }
    public void add(){
        try {
            PreparedStatement pstmt = database.getConn().prepareStatement("Insert into ucenik values ("+maxID+", '"+imeDodavanje.getText()+"', '"+prezimeDodavanje.getText()+"', '"+adresaDodavanje.getText()+"', '"+datumDodavanje.getText()+"', '"+majkaDodavanje.getText()+"', '"+otacDodavanje.getText()+"', "+Float.parseFloat(prosekDodavanje.getText())+", '"+polDodavanje.getValue()+"', '"+razredDodavanje.getText()+"', '"+razredniDodavanje.getText()+"', "+Float.parseFloat(uspehDodavanje.getText())+")");
            pstmt.executeUpdate();

            Statement stmt = database.getConn().createStatement();
            stmt.execute("select * from ucenik");

            ResultSet rs = stmt.getResultSet();
            lista_ucenika.clear();
            while(rs.next()){
                lista_ucenika.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                maxID = Math.max(rs.getInt(1), maxID) + 1;
            }
            tabelaUcenika.setItems(lista_ucenika);
            clearLabels();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void delete(KeyEvent e, int id){
        if(e.getCode() == KeyCode.DELETE){
            try {
                Statement stmt = database.getConn().createStatement();
                stmt.execute("delete from ucenik where ucenik_id = "+id+"");
                for(Ucenik ucenik : lista_ucenika){
                    if(ucenik.getUcenik_id() == id){
                        Platform.runLater(() -> lista_ucenika.remove(ucenik));
                    }
                }
                tabelaUcenika.setItems(lista_ucenika);
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    public void deleteP(KeyEvent e, int id){
        if(e.getCode() == KeyCode.DELETE){
            try {
                Statement stmt = database.getConn().createStatement();
                stmt.execute("delete from predmet where predmet_id = "+id+"");
                for(Predmet predmet : lista_predmeta){
                    if(predmet.getPredmet_id() == id){
                        Platform.runLater(() -> lista_predmeta.remove(predmet));
                    }
                }
                tabelaPredmeta.setItems(lista_predmeta);
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    public void edit(){
        tabelaUcenika.setEditable(!tabelaUcenika.isEditable());
        if(izmeniBtn.getOpacity() == 1){
            izmeniBtn.setOpacity(0.5);
        }
        else{
        izmeniBtn.setOpacity(1);
        }
    }
    public void searchName(){
        try {
            if(!imePretraga.getText().equals("")) {
                ObservableList<Ucenik> tempList = FXCollections.observableArrayList();
                Statement stmt = database.getConn().createStatement();
                stmt.execute("select * from ucenik where ucenik_ime like '" + imePretraga.getText() + "%'");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    tempList.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                }
                tabelaUcenika.setItems(tempList);
            }
            else if(imePretraga.getText().equals("")){
                tabelaUcenika.setItems(lista_ucenika);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void searchSurname(){
        try {
            if(!prezimePretraga.getText().equals("")) {
                ObservableList<Ucenik> tempList = FXCollections.observableArrayList();
                Statement stmt = database.getConn().createStatement();
                stmt.execute("select * from ucenik where ucenik_prezime like '" + prezimePretraga.getText() + "%'");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    tempList.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                }
                tabelaUcenika.setItems(tempList);
            }
            else if(prezimePretraga.getText().equals("")){
                tabelaUcenika.setItems(lista_ucenika);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void searchAddress(){
        try {
            if(!adresaPretraga.getText().equals("")) {
                ObservableList<Ucenik> tempList = FXCollections.observableArrayList();
                Statement stmt = database.getConn().createStatement();
                stmt.execute("select * from ucenik where ucenik_adresa like '" + adresaPretraga.getText() + "%'");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    tempList.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                }
                tabelaUcenika.setItems(tempList);
            }
            else if(adresaPretraga.getText().equals("")){
                tabelaUcenika.setItems(lista_ucenika);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void searchSuccess(){
        try {
            if(!uspehPretraga.getText().equals("")) {
                ObservableList<Ucenik> tempList = FXCollections.observableArrayList();
                Statement stmt = database.getConn().createStatement();
                stmt.execute("select * from ucenik where ucenik_uspeh = " + Float.parseFloat(uspehPretraga.getText()) + "");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    tempList.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                }
                tabelaUcenika.setItems(tempList);
            }
            else if(uspehPretraga.getText().equals("")){
                tabelaUcenika.setItems(lista_ucenika);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void searchGender(){
        try {
            if(!polPretraga.getValue().equals("")) {
                ObservableList<Ucenik> tempList = FXCollections.observableArrayList();
                Statement stmt = database.getConn().createStatement();
                stmt.execute("select * from ucenik where ucenik_pol = '" + polPretraga.getValue() + "'");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    tempList.add(new Ucenik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getFloat(12)));
                }
                tabelaUcenika.setItems(tempList);
            }
            else if(polPretraga.getValue().equals("")){
                tabelaUcenika.setItems(lista_ucenika);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void save(){
        try {
            PreparedStatement pstmt = database.getConn().prepareStatement("Insert into predmet values ("+maxID2+", '"+imePredmeta.getText()+"', '"+razredPredmeta.getText()+"')");
            pstmt.executeUpdate();

            Statement stmt = database.getConn().createStatement();
            stmt.execute("select * from predmet");

            ResultSet rs = stmt.getResultSet();
            lista_predmeta.clear();
            while(rs.next()){
                lista_predmeta.add(new Predmet(rs.getInt(1), rs.getString(2), rs.getString(3)));
                maxID2 = Math.max(rs.getInt(1), maxID2) + 1;
            }
            tabelaPredmeta.setItems(lista_predmeta);
            imePredmeta.setText("");
            razredPredmeta.setText("");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void byClass(){
        try {
            if(!cBoxRazreda.getValue().equals("")) {
                ObservableList<Predmet> tempList = FXCollections.observableArrayList();
                Statement stmt = database.getConn().createStatement();
                stmt.execute("select * from predmet where predmet_ima_razred = '" + cBoxRazreda.getValue() + "'");
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    tempList.add(new Predmet(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
                tabelaPredmeta.setItems(tempList);
            }
            else{
                tabelaPredmeta.setItems(lista_predmeta);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public void checkIfEmpty2(){
        unosBtn2.setDisable(imePredmeta.getText().equals("") || razredPredmeta.getText().equals(""));
    }
    public void generate(){
        try {
            if(delBrojPotvrde.getText().equals("") || delBrojPotvrde.getText().equals("")) {
                Statement stmt = database.conn.createStatement();
                stmt.execute("select date('now')");
                ResultSet rs = stmt.getResultSet();
                String curDate = rs.getString(1);

                stmt.execute("select max(potvrda_id) from potvrda");
                rs = stmt.getResultSet();

                delId = rs.getInt(1) + 1;
                String year = curDate.substring(0, 4);
                String month = curDate.substring(5, 7);
                String day = curDate.substring(8);
                curDate = day + "-" + month + "-" + year;
                PreparedStatement pstmt = database.conn.prepareStatement("insert into potvrda values (" + delId + ", 'ПОТ', '" + curDate + "')");
                pstmt.executeUpdate();

                stmt.execute("select potvrda_id, potvrda_tip, potvrda_datum from potvrda where potvrda_id = " + delId + "");
                rs = stmt.getResultSet();

                delBrojPotvrde.setText(rs.getString(2) + "-" +Integer.toString(rs.getInt(1)) + "/" +year.substring(2));
                datumPotvrde.setText(rs.getString(3));

                Ucenik selected = tabelaUcenika.getSelectionModel().getSelectedItem();

                imePrezime.setText(selected.getUcenik_prezime() +" "+ selected.getUcenik_ime());
                if(selected.getUcenik_otac().equals("/")){
                    sinKci.setText(selected.getUcenik_majka());
                }
                else{
                    sinKci.setText(selected.getUcenik_otac());
                }
                if(Integer.parseInt(month) < 9){
                    od.setText(Integer.toString(Integer.parseInt(year)-1));
                    doo.setText(Integer.toString(Integer.parseInt(year)));
                }
                else{
                    od.setText(Integer.toString(Integer.parseInt(year)));
                    doo.setText(Integer.toString(Integer.parseInt(year)+1));
                }
                if(selected.getUcenik_razred().startsWith("IV")){
                    razred.setText("четврти");
                }
                else if(selected.getUcenik_razred().startsWith("III")){
                    razred.setText("трећи");
                }
                else if(selected.getUcenik_razred().startsWith("II")){
                    razred.setText("други");
                }else if(selected.getUcenik_razred().startsWith("I")){
                    razred.setText("први");
                }

                if(selected.getUcenik_razred().endsWith("1")){
                    obrazProf.setText("електротехничар информационих технологија");
                }
                else if(selected.getUcenik_razred().endsWith("2")){
                    obrazProf.setText("администратор рачунарских мрежа");
                }
                else if(selected.getUcenik_razred().endsWith("3")){
                    obrazProf.setText("техничар мултимедије");
                }else if(selected.getUcenik_razred().endsWith("4")){
                    obrazProf.setText("техничар за компјутерско управлјање (CNC) машина");
                }
                else if(selected.getUcenik_razred().endsWith("5")){
                    obrazProf.setText("механичар за моторна возила");
                }
                else if(selected.getUcenik_razred().endsWith("6")){
                    obrazProf.setText("бравар - заваривач");
                }
                gimSmer.setText("/");
                nazivSkole.setText("Техничке школе \"9. мај\"");
                mesto.setText("Бачкој Паланци");
                rodjen.setText(selected.getUcenik_datum_rodjenja());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void ifSelected(){
        generisiBtn.setDisable(tabelaUcenika.getSelectionModel().getSelectedItem() == null);
    }
    public void saveAsImage(){
        File file = new File(""+delBrojPotvrde.getText().replace("/", "-")+".png");
        WritableImage writableImage = potvrdaPane.snapshot(new SnapshotParameters(), null);
        int width = (int) writableImage.getWidth();
        int height = (int) writableImage.getHeight();
        int[] pixels = new int[width * height];

        writableImage.getPixelReader().getPixels(0, 0, width, height,(WritablePixelFormat<IntBuffer>) writableImage.getPixelReader().getPixelFormat(), pixels, 0, width);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var pixel = pixels[y * width + x];
                int r = (pixel & 0xFF0000) >> 16;
                int g = (pixel & 0xFF00) >> 8;
                int b = pixel & 0xFF;
                bufferedImage.getRaster().setPixel(x, y, new int[]{r, g, b});
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}