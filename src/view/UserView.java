package view;

import model.User;
import controller.UserController;

public class UserView {
    private UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void displayUserInfo(String id) {
        User user = userController.getUserById(id);
        if (user != null) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        } else {
            System.out.println("User not found.");
        }
    }
}