package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nick.pack.model.Users;
import nick.pack.service.EntityList;

import java.io.IOException;

public class AuthenticationController {
    private ObservableList<Users> observableList;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField loginTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Label errorLabel;
    @FXML
    private Button signInButton;

    @FXML
    void initialize(){
        observableList = FXCollections.observableArrayList(EntityList.getUsersList());
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        if (!(loginTxt.getText().isEmpty() || passwordTxt.getText().isEmpty())) {
            boolean isFalse = false;
            for (int i = 0; i < EntityList.getUsersList().size(); i++) {
                if (EntityList.getUsersList().get(i).getUsername().equals(loginTxt.getText()) && EntityList.getUsersList().get(i).getPassword().equals(passwordTxt.getText())){
                    isFalse = true;
                }
            }
            if (isFalse){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/magazine.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Салон красоты");
                stage.getIcons().add(new Image("/logo/beauty_logo.ico"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();
                signInButton.getScene().getWindow().hide();
            } else
                errorLabel.setText("Неверный логин или пароль");
        } else
            errorLabel.setText("Введите все данные");
    }
    @FXML
    void signUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/registration.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    public ObservableList<Users> getObservableList(){
        return observableList;
    }
}

