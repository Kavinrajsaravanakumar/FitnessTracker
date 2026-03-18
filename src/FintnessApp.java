import Controller.FinessController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class FintnessApp {
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/fitness";
        String user = "root";
        String password = "Kavinraj@mysql";

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Database Connection Successfull !\n");
            System.out.println("Welcome to FitnessTracker\n\n");
            FinessController f = new FinessController();
            f.run(conn,s);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}