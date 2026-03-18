package Service;

import Modal.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class AuthService {

    public User signUp(Connection conn,Scanner s) throws SQLException {

        System.out.println("Enter UserName  : ");       String u_name = s.nextLine();
        System.out.println("Enter Password  : ");       String password = s.nextLine();

        if(checkUserNameExist(conn, u_name)){
            System.out.println("This Modal.User is Already Exist Please Login !!");
            return null;
        }
        System.out.println("Enter Age  : ");            int age = s.nextInt();
        System.out.println("Enter Height  : ");         float height = s.nextFloat();
        System.out.println("Enter Weight  : ");         float weight = s.nextFloat();
        s.nextLine();
        int id = generateUserId(conn);

        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, u_name);
        ps.setString(3, password);
        ps.setInt(4,age);
        ps.setFloat(5, height);
        ps.setFloat(6, weight);
        ps.executeUpdate();

        System.out.println("SignUp successful - Now u can Login !!");
        System.out.println();
        return null;
    }
    public User login(Connection conn, Scanner s) throws SQLException {
        System.out.print("Enter UserName  : ");       String u_name = s.nextLine();
        System.out.print("Enter Password  : ");       String password = s.nextLine();
        if(checkUserExist(conn,u_name,password)) {
            String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u_name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                int id = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String pass = rs.getString("password");
                int age = rs.getInt("Age");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");

                user.setUser_id(id);
                user.setUser_name(username);
                user.setPassword(pass);
                user.setAge(age);
                user.setHeight(height);
                user.setWeight(weight);

                System.out.println("Login Successfull!!");
                System.out.println();
                return user;

            }
        }

        System.out.println("Username or Password is Wrong ");
        return null;
    }

    public boolean checkUserNameExist(Connection conn, String u_name) throws SQLException {
        String sql = "SELECT 1 FROM user WHERE user_name = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u_name);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public boolean checkUserExist(Connection conn,String u_name,String pass) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1,u_name);
        ps.setString(2,pass);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public int generateUserId(Connection conn) throws SQLException {
        Random rand = new Random();
        int id;

        while(true){
            id = 10000 + rand.nextInt(90000);

            String sql = "SELECT 1 FROM user WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                break; // if ID not used
            }
        }
        return id;
    }
}
