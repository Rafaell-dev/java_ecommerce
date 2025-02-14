package view;

import controller.AdminController;

public class AdminView {
    private AdminController adminController;

    public AdminView(AdminController adminController) {
        this.adminController = adminController;
    }

    public void displayAdminOptions() {
        System.out.println("1. Add Product");
        System.out.println("2. Update Product Stock");
        System.out.println("3. Update User Password");
    }
}