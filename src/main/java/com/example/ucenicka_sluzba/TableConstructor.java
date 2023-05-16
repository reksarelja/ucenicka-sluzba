package com.example.ucenicka_sluzba;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TableConstructor {
    private static TableConstructor instance = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    Connector connector = Connector.getInstance();

    String ucenik = "Create table if not exists ucenik (ucenik_id integer not null," +
            "ucenik_ime text not null, " +
            "ucenik_prezime text not null, " +
            "ucenik_adresa text not null, " +
            "ucenik_datum_rodjenja text not null, " +
            "ucenik_majka text not null, " +
            "ucenik_otac text not null, " +
            "ucenik_prosek real not null, " +
            "ucenik_pol text not null, " +
            "ucenik_razred text not null, " +
            "ucenik_razredni_staresina text not null, " +
            "ucenik_uspeh real not null, " +
            "primary key (ucenik_id) );";
    String predmet = "Create table if not exists predmet (predmet_id integer not null, " +
            "predmet_ime text not null, " +
            "predmet_ima_razred text not null, " +
            "primary key (predmet_id) );";
    String potvrda = "Create table if not exists potvrda (potvrda_id integer not null, " +
            "potvrda_tip text not null, " +
            "potvrda_datum text not null, " +
            "primary key (potvrda_id) );";
    public static TableConstructor getInstance() {
        if(instance == null) {
            instance = new TableConstructor();
        }
        return instance;
    }

    public TableConstructor() {
        try{
        stmt = connector.getConn().createStatement();
        stmt.execute(ucenik);
        stmt.execute(predmet);
        stmt.execute(potvrda);
       /* pstmt = connector.getConn().prepareStatement("insert into potvrda values (0, 'POT', 'xx-xx-xxxx');");
        pstmt.executeUpdate();*/
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    public Statement getStmt() {
        return stmt;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }
}
