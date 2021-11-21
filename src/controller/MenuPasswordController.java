package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sun.security.util.Password;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class
MenuPasswordController {

    public TextField txtuserName;
    public PasswordField txtPassword;
    public javafx.scene.control.Button Button;

    // button deseble
public void initialize(){
    Button.setDisable(true);
}

    public void logingButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
         String name=txtuserName.getText();
        String password=txtPassword.getText();

        PreparedStatement stm = DbConnection.getInstance().
        getConnection().prepareStatement("SELECT*FROM manageloging WHERE Name=? and Password=?");
        stm.setString(1,name);
        stm.setString(2, password);

        //name=market;
        //password=123;


        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Parent load = FXMLLoader.load(getClass().getResource("../view/MenuForm.fxml"));
            Scene scene=new Scene(load);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        }else {
            JOptionPane.showMessageDialog(null, "loging fail");

        }
    }
    //button disable
    public void keyReleasedProperty(KeyEvent keyEvent) {
        String name=txtuserName.getText();
        String password=txtPassword.getText();

        boolean inDisabled=(name.isEmpty() || name.trim().isEmpty()) || (password.isEmpty() || password.trim().isEmpty());
        Button.setDisable(inDisabled);
    }
}
