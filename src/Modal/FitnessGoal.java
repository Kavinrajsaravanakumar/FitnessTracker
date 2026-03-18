package Modal;

public class FitnessGoal {

    private int goal_id;
    private int user_id;
    private String goal_type;
    private float target_value;
    private java.sql.Date deadline;

    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGoal_type() {
        return goal_type;
    }

    public void setGoal_type(String goal_type) {
        this.goal_type = goal_type;
    }

    public float getTarget_value() {
        return target_value;
    }

    public void setTarget_value(float target_value) {
        this.target_value = target_value;
    }

    public java.sql.Date getDeadline() {
        return deadline;
    }

    public void setDeadline(java.sql.Date deadline) {
        this.deadline = deadline;
    }
}