package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nick.pack.model.Users;
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;
import nick.pack.service.UsersService;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

public class RegistrationController {
    private ObservableList<Users> observableList;
    private ArrayList<Character> chars;
    private ArrayList<Character> phoneChars;

    @FXML
    private TextField addressField;

    @FXML
    private Label errorMassage;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button button;


    @FXML
    void initialize(){
        initChars();
        initPhoneChars();
        observableList = FXCollections.observableArrayList(EntityList.getUsersList());
        System.out.println(observableList);
    }
    @FXML
    void signUp(ActionEvent event) {
        if (!(loginField.getText().isEmpty() || passwordField.getText().isEmpty() ||
        phoneField.getText().isEmpty() || addressField.getText().isEmpty())){
            registration(loginField.getText(), passwordField.getText(), phoneField.getText(), addressField.getText());

        } else
            errorMassage.setText("Одно из полей пустое");
    }




    private void registration(String login, String password, String phone, String address){
        boolean a = true;
        boolean b = true;
        boolean c = true;
        if (login.length() <= 20 && password.length() <= 20){
            if (phone.length() <=12) {
                char[] phoneCheckChar = phone.toCharArray();
                for (int i = 0; i < phoneCheckChar.length; i++) {
                    if (!phoneChars.contains(phoneCheckChar[i])) {
                        c = false;
                        errorMassage.setText("Поле телефон содержит недопустимые символы");
                        break;
                    }
                }
                if (c) {
                    for (int i = 0; i < observableList.size(); i++) {
                        if (observableList.get(i).getUsername().equals(login)) {
                            a = false;
                        }
                    }
                    if (a) {
                        char[] passwordChars = password.toCharArray();
                        for (int i = 0; i < passwordChars.length; i++) {
                            if (!chars.contains(passwordChars[i])) {
                                errorMassage.setText("Пароль должен содержать только латинские буквы и цифры");
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            Users users = new Users();
                            users.setUsername(login);
                            users.setPassword(password);
                            users.setPhone(phone);
                            users.setAddress(address);
                            CRUD<Users> crud = new UsersService();
                            crud.create(users);
                            EntityList.getUsersList().add(users);

                            button.getScene().getWindow().hide();
                        }
                    } else
                        errorMassage.setText("Данный логин уже существует");
                }
            }else
                errorMassage.setText("Поле телефон должно быть не больше 12 символов");
        } else
            errorMassage.setText("Логин и Пароль должны быть не больше 20 символов");
    }
    private void initChars(){
        chars = new ArrayList<>();
        chars.add('Q');
        chars.add('q');
        chars.add('W');
        chars.add('w');
        chars.add('E');
        chars.add('e');
        chars.add('R');
        chars.add('r');
        chars.add('T');
        chars.add('t');
        chars.add('Y');
        chars.add('y');
        chars.add('U');
        chars.add('u');
        chars.add('I');
        chars.add('i');
        chars.add('O');
        chars.add('o');
        chars.add('P');
        chars.add('p');
        chars.add('A');
        chars.add('a');
        chars.add('S');
        chars.add('s');
        chars.add('D');
        chars.add('d');
        chars.add('F');
        chars.add('f');
        chars.add('G');
        chars.add('g');
        chars.add('H');
        chars.add('h');
        chars.add('J');
        chars.add('j');
        chars.add('K');
        chars.add('k');
        chars.add('L');
        chars.add('l');
        chars.add('Z');
        chars.add('z');
        chars.add('X');
        chars.add('x');
        chars.add('C');
        chars.add('c');
        chars.add('V');
        chars.add('v');
        chars.add('B');
        chars.add('b');
        chars.add('N');
        chars.add('n');
        chars.add('M');
        chars.add('1');
        chars.add('2');
        chars.add('3');
        chars.add('4');
        chars.add('5');
        chars.add('6');
        chars.add('7');
        chars.add('8');
        chars.add('9');
        chars.add('-');
        chars.add('_');
    }
    private void initPhoneChars(){
        phoneChars = new ArrayList<>();
        phoneChars.add('1');
        phoneChars.add('2');
        phoneChars.add('3');
        phoneChars.add('4');
        phoneChars.add('5');
        phoneChars.add('6');
        phoneChars.add('7');
        phoneChars.add('8');
        phoneChars.add('9');
        phoneChars.add('0');
        phoneChars.add('(');
        phoneChars.add(')');
        phoneChars.add('-');
        phoneChars.add(' ');
    }
}
