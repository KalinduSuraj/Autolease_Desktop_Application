import controller.UserLoginController;
import view.UserLoginView;

public class Main {

    public static void main(String[] args) {
        new UserLoginController(new UserLoginView());
    }
}