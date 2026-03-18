package Service;

import Modal.Activity;
import Modal.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class ActivityService {
    public void viewActivities(Connection conn,User user) throws SQLException {
        String sql = "SELECT * FROM activity where user_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,user.getUser_id());
        ResultSet rs = ps.executeQuery();

        System.out.println("\nYout Activities");

        System.out.printf("%-12s %-15s %-10s %-10s %-12s %-8s %-10s%n",
                "ActivityID", "Type", "Duration", "Distance", "Calories", "Pace", "HeartRate");

        System.out.println("---------------------------------------------------------------");

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.printf("%-12d %-15s %-10d %-10.2f %-12d %-8.2f %-10d%n",
                    rs.getInt("activity_id"),
                    rs.getString("activity_type"),
                    rs.getInt("duration"),
                    rs.getFloat("distance"),
                    rs.getInt("calories_burned"),
                    rs.getFloat("pace"),
                    rs.getInt("heart_rate"));
        }
        System.out.println();
        if (!found) {
            System.out.println("No activities found.");
        }
        System.out.println();
    }
    public void addActivity(Connection conn, Scanner s, User user) throws SQLException {
        System.out.println("Enter your Modal.Activity Details : ");
        System.out.print("Modal.Activity Name  : "  );   String ac_name = s.nextLine();
        System.out.print("Duration in minutes  : "  );   int duration = s.nextInt();
        System.out.print("Distance in meter  : "  );   float dist= s.nextFloat();
        System.out.print("pace  : "  );   float pace = s.nextFloat();
        System.out.print("Heart Rate  : "  );   int heart = s.nextInt();

        int ac_id = generateAcId(conn);
        float cal_burn = calculateCalories(ac_name,duration,user.getWeight());
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());


        Activity ac = new Activity();

        ac.setUser_id(user.getUser_id());
        ac.setActivity_id(ac_id);
        ac.setActivity_type(ac_name);
        ac.setDuration(duration);
        ac.setDistance(dist);
        ac.setPace(pace);
        ac.setCal_burn(cal_burn);
        ac.setHeart_rate(heart);

        String sql = "INSERT INTO activity VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,ac_id);
        ps.setInt(2,user.getUser_id());
        ps.setString(3,ac_name);
        ps.setInt(4,duration);
        ps.setFloat(5,dist);
        ps.setFloat(6,cal_burn);
        ps.setFloat(7,pace);
        ps.setInt(8,heart);
        ps.setDate(9,date);

        ps.executeUpdate();
        System.out.println("Modal.Activity Added Successfully !!");
        System.out.println();
    }
    public int generateAcId(Connection conn) throws SQLException {
        Random rand = new Random();
        int id;

        while(true){
            id = 1000 + rand.nextInt(9000);

            String sql = "SELECT 1 FROM activity WHERE activity_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                break; // if ID not used
            }
        }
        return id;
    }
    public float calculateCalories(String activityType, int durationMinutes, float weight) {

        float met = 0;

        switch(activityType.toLowerCase()) {
            case "walking":
                met = 3.5f;
                break;
            case "running":
                met = 7.0f;
                break;
            case "cycling":
                met = 6.0f;
                break;
            case "swimming":
                met = 8.0f;
                break;
            default:
                met = 4.0f;
        }

        float hours = durationMinutes / 60.0f;

        return (met * weight * hours);
    }
}
