package Service;

import Modal.User;
import Modal.WorkoutPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class WorkoutPlanService {
    public void viewWorkoutPlans(Connection conn, User user) throws SQLException, SQLException {

        String sql = "SELECT * FROM workout WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, user.getUser_id());

        ResultSet rs = ps.executeQuery();

        System.out.println("\nYour Workout Plans");

        System.out.printf("%-12s %-18s %-15s %-10s %-18s %-12s%n",
                "WorkoutID", "Workout Name", "Type", "Duration", "Difficulty", "Date");

        System.out.println("--------------------------------------------------------------------------");

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.printf("%-12d %-18s %-15s %-10d %-18s %-12s%n",
                    rs.getInt("workout_id"),
                    rs.getString("workout_name"),
                    rs.getString("workout_type"),
                    rs.getInt("duration"),
                    rs.getString("difficulty_level"),
                    rs.getDate("workout_date"));
        }
        System.out.println();
        if (!found) {
            System.out.println("No workout plans found.");
        }
        System.out.println();
    }
    public void addWorkoutPlan(Connection conn, Scanner s, User user) throws SQLException {

        System.out.println("Enter Workout Plan Details : ");

        System.out.print("Workout Name : ");
        String workout_name = s.nextLine();

        System.out.print("Workout Type (Cardio / Strength / Flexibility) : ");
        String workout_type = s.nextLine();

        System.out.print("Duration in minutes : ");
        int duration = s.nextInt();

        s.nextLine(); // clear buffer

        System.out.print("Difficulty Level (Beginner / Intermediate / Advanced) : ");
        String difficulty = s.nextLine();

        int workout_id = generateWPId(conn);

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

        WorkoutPlan workout = new WorkoutPlan();

        workout.setWorkout_id(workout_id);
        workout.setUser_id(user.getUser_id());
        workout.setWorkout_name(workout_name);
        workout.setWorkout_type(workout_type);
        workout.setDuration(duration);
        workout.setDifficulty_level(difficulty);
        workout.setWorkout_date(date);

        String sql = "INSERT INTO workout VALUES(?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, workout_id);
        ps.setInt(2, user.getUser_id());
        ps.setString(3, workout_name);
        ps.setString(4, workout_type);
        ps.setInt(5, duration);
        ps.setString(6, difficulty);
        ps.setDate(7, date);

        ps.executeUpdate();

        System.out.println("Workout Plan Added Successfully !!");
        System.out.println();
    }
    public int generateWPId(Connection conn) throws SQLException {
        Random rand = new Random();
        int id;

        while (true) {
            id = 1000 + rand.nextInt(9000);

            String sql = "SELECT 1 FROM activity WHERE activity_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                break; // if ID not used
            }
        }
        return id;
    }
}
