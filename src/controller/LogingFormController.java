package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class LogingFormController {
    public AnchorPane logingContext;
    public PasswordField Password;
    public TextField txtName;


    public void logingForm(ActionEvent actionEvent) throws IOException {
  String uname = txtName.getText();
        String pass = Password.getText();


           try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                    "SELECT*FROM logingForm WHERE User_Name=? and Password=? ");
            stm.setString(1, uname);
            stm.setString(2, pass);

            ResultSet rst = stm.executeQuery();

            if (rst.next()) {
                Parent load = FXMLLoader.load(getClass().getResource("../view/DetailAddForm.fxml"));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            }else {
                JOptionPane.showMessageDialog(null, "loging fail");
                txtName.setText("");
                Password.setText("");
                txtName.requestFocus();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
