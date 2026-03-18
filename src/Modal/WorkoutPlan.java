package Modal;

public class WorkoutPlan {

        private int workout_id;
        private int user_id;
        private String workout_name;
        private String workout_type;
        private int duration;
        private String difficulty_level;
        private java.sql.Date workout_date;


        public int getWorkout_id() {
            return workout_id;
        }

        public void setWorkout_id(int workout_id) {
            this.workout_id = workout_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getWorkout_name() {
            return workout_name;
        }

        public void setWorkout_name(String workout_name) {
            this.workout_name = workout_name;
        }

        public String getWorkout_type() {
            return workout_type;
        }

        public void setWorkout_type(String workout_type) {
            this.workout_type = workout_type;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getDifficulty_level() {
            return difficulty_level;
        }

        public void setDifficulty_level(String difficulty_level) {
            this.difficulty_level = difficulty_level;
        }

        public java.sql.Date getWorkout_date() {
            return workout_date;
        }

        public void setWorkout_date(java.sql.Date workout_date) {
            this.workout_date = workout_date;
        }
}
