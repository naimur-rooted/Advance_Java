import java.sql.*;
import java.util.*;

public class StudentResultSystem1 {
    public static void main(String[] args) {
        setupDatabase();

         String[] names = {"Naimur Rahman", "Afrima Sultana", "Abu Shaid"};

        HashMap<Integer, Integer[]> marksMap = new HashMap<>();
        marksMap.put(1, new Integer[]{85, 90, 78});
        marksMap.put(2, new Integer[]{70, 65, 80});
        marksMap.put(3, new Integer[]{95, 88, 92});

        for (int i = 0; i < names.length; i++) {
            int id = i + 1;
            Integer[] marks = marksMap.get(id);

            int total = marks[0] + marks[1] + marks[2];
            String grade = assignGrade(total);

            insertIntoDB(id, names[i], marks, total, grade);
        }
        displayRecords();
    }
     
    //create database 
    public static void setupDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", "root", "admin");

            Statement st = con.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS student_db");
            st.executeUpdate("USE student_db");
           String createTable = "CREATE TABLE IF NOT EXISTS results (" +
                      "id INT PRIMARY KEY, " +
                      "name VARCHAR(50), " +
                      "artificial_intelligence INT, " +
                      "advance_enterprise_java INT, " +
                      "software_engineering INT, " +
                      "total INT, " +
                      "grade VARCHAR(2))";

            st.executeUpdate(createTable);

            con.close();
            System.out.println("Database and table ready!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Grade calculation logic
    public static String assignGrade(int total) {
        if (total >= 240) return "A";
        else if (total >= 180) return "B";
        else return "C";
    }

    //JDBC insertion method
    public static void insertIntoDB(int id, String name, Integer[] marks, int total, String grade) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db", "root", "admin");

            String sql = "INSERT INTO results VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks[0]);
            ps.setInt(4, marks[1]);
            ps.setInt(5, marks[2]);
            ps.setInt(6, total);
            ps.setString(7, grade);

            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Display all records method
    public static void displayRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db", "root", "admin");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM results");

           System.out.println("ID | Name | AI | Java | SE | Total | Grade");
                while (rs.next()) {
                System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("name") + " | " +
                rs.getInt("artificial_intelligence") + " | " +
                rs.getInt("advance_enterprise_java") + " | " +
                rs.getInt("software_engineering") + " | " +
                rs.getInt("total") + " | " +
                rs.getString("grade")
            );
        }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
