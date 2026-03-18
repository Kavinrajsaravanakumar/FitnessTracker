package Service;

import Modal.FitnessGoal;
import Modal.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FitnessGoalService {
    public void viewFitnessGoals(Connection conn, User user) throws SQLException {

        String sql = "SELECT * FROM fitness_goal WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, user.getUser_id());

        ResultSet rs = ps.executeQuery();

        System.out.println("\nYour Fitness Goals");

        System.out.printf("%-10s %-20s %-15s %-12s%n",
                "GoalID", "Goal Type", "Target", "Deadline");

        System.out.println("------------------------------------------------");

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.printf("%-10d %-20s %-15.2f %-12s%n",
                    rs.getInt("goal_id"),
                    rs.getString("goal_type"),
                    rs.getFloat("target_value"),
                    rs.getDate("deadline"));
        }
        System.out.println();
        if (!found) {
            System.out.println("No goals found.");
        }
    }
    public void addFitnessGoal(Connection conn, Scanner s, User user) throws SQLException {

        System.out.println("Enter Fitness Goal Details : ");

        System.out.print("Goal Type (Weight Loss / Distance / Calories) : ");
        String type = s.nextLine();

        System.out.print("Target Value : ");
        float target = s.nextFloat();

        s.nextLine();

        int goal_id = generateGoalId(conn);

        java.sql.Date deadline = new java.sql.Date(System.currentTimeMillis());

        FitnessGoal goal = new FitnessGoal();

        goal.setGoal_id(goal_id);
        goal.setUser_id(user.getUser_id());
        goal.setGoal_type(type);
        goal.setTarget_value(target);
        goal.setDeadline(deadline);

        String sql = "INSERT INTO fitness_goal VALUES(?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, goal_id);
        ps.setInt(2, user.getUser_id());
        ps.setString(3, type);
        ps.setFloat(4, target);
        ps.setDate(5, deadline);

        ps.executeUpdate();

        System.out.println("Fitness Goal Added Successfully !!");
        System.out.println();
    }
    public int generateGoalId(Connection conn) throws SQLException {

        Random rand = new Random();
        int id;

        while(true){
            id = 2000 + rand.nextInt(8000);

            String sql = "SELECT 1 FROM fitness_goal WHERE goal_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                break;
            }
        }

        return id;
    }
}
