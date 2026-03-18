package Controller;

import Modal.User;
import Service.ActivityService;
import Service.AuthService;
import Service.FitnessGoalService;
import Service.WorkoutPlanService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class FinessController {
    AuthService auth = new AuthService();

    public void run(Connection conn, Scanner s) throws SQLException {
        while (true) {

            System.out.println("1. Login ");
            System.out.println("2. SignUp");
            System.out.println("3. Exit");

            System.out.print("Enter a Option  :  ");
            int n = s.nextInt();
            s.nextLine();
            System.out.println();
            switch (n) {
                case 1:
                    User user = auth.login(conn, s);
                    if (user != null) {
                        userAccess(conn, s, user);
                    }
                    break;
                case 2:
                    auth.signUp(conn, s);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter a valid Option :: ");
                    break;
            }
        }
    }

    public void userAccess(Connection conn, Scanner s, User user) throws SQLException {
        ActivityService ac = new ActivityService();
        WorkoutPlanService wrkot = new WorkoutPlanService();
        FitnessGoalService goal = new FitnessGoalService();
        while (true) {
            System.out.println("1. View Activity ");
            System.out.println("2. View Workout Plan");
            System.out.println("3. View Fitness Goal");
            System.out.println("4. Add Activity ");
            System.out.println("5. Add Workout Plan");
            System.out.println("6. Add Fitness Goal ");
            System.out.println("7. Get AI help ");
            System.out.println("8. Logout");
            System.out.println("9. Exit Application");
            System.out.print("Enter a Option : ");
            int n = s.nextInt();
            s.nextLine();
            System.out.println();
            switch (n) {
                case 1:
                    ac.viewActivities(conn, user);
                    break;
                case 2:
                    wrkot.viewWorkoutPlans(conn, user);
                    break;
                case 3:
                    goal.viewFitnessGoals(conn, user);
                    break;
                case 4:
                    ac.addActivity(conn, s, user);
                    break;
                case 5:
                    wrkot.addWorkoutPlan(conn, s, user);
                    break;
                case 6:
                    goal.addFitnessGoal(conn, s, user);
                    break;
                case 7:
                    System.out.println("This feature will be added in Future\n\n");
                    break;
                case 8:
                    return;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid Option :: ");
                    break;
            }
        }
    }

}