package controller;

import Model.User;
import Model.UserDAO;
import view.AdminHomeView;
import view.HomeScreenView;
import view.UserLoginView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class UserLoginController implements KeyListener {
    UserLoginView view ;
    UserDAO userDAO;

    public static User logedUser;

    public UserLoginController(UserLoginView view){
        this.view=view;
        this.userDAO = new UserDAO();
        view.addLoginListener(e -> loginButtonClick());
        view.addKeyListenerToComponents(this);
    }

    public static User getLogedUserObj(){
        return logedUser;
    }

    private void loginButtonClick() {

        String UserName = view.getUsername();
        String Password = view.getPassword();

        boolean check = false;
        if(!(Objects.equals(UserName, "")) && !(Objects.equals(Password, ""))){
            logedUser = new User();
            logedUser.setUserName(UserName);
            logedUser.setPassword(Password);
            check = userDAO.userLogin(logedUser);
            if(check){
                showMsg("Login Successful","Success");
                if (Objects.equals(UserName, "admin")) {
                    new AdminHomeController(new AdminHomeView());
                    view.dispose();
                } else {
                    new HomeScreenController(new HomeScreenView());
                    view.dispose();
                }
            }else {
                showMsg("Check User Name and Password","Login Failed");
            }


        }else{
            showMsg("User Name and Password is requeue","Error");
        }

    }

    public void showMsg(String msg,String title){
        JOptionPane.showConfirmDialog(
                view,
                msg,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane. INFORMATION_MESSAGE
        );
    }


    //key events
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ENTER){
            loginButtonClick();
        }

        if(key == KeyEvent.VK_ESCAPE){
            view.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
