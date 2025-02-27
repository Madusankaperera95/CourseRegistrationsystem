package lk.cmjd.coursework.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogOutUtil {
    public  void logout(ActionEvent actionEvent){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/cmjd/coursework/views/login.fxml")); // Change to your view file
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Course Management System");
            stage.show();

            // Close the current login window
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
