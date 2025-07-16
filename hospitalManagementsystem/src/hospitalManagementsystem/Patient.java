//package hospitalManagementsystem;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Patient {
//    private Connection connection;
//    private Scanner scanner;
//
//    public Patient(Connection connection) {
//        this.connection = connection;
//        this.scanner = scanner;
//    }
//
//    public void addPatient() {
//        System.out.println("Enter Patient Name:");
//        scanner.nextLine(); 
//        String name = scanner.nextLine(); 
//
//        System.out.println("Enter Patient Age:");
//        int age = scanner.nextInt();
//
//        System.out.println("Enter Patient Gender:");
//        scanner.nextLine(); 
//        String gender = scanner.nextLine();
//
//        String query = "INSERT INTO patient(name, age, gender) VALUES (?, ?, ?)";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setInt(2, age);
//            preparedStatement.setString(3, gender);
//
//            int affectedRows = preparedStatement.executeUpdate();
//
//            if (affectedRows > 0) {
//                System.out.println("Patient Added Successfully");
//            } else {
//                System.out.println("Failed to add Patient");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void viewPatients() {
//        String query = "SELECT * FROM patient";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            System.out.println("Patients:");
//            System.out.println("+------------+----------------------+-----+--------+");
//            System.out.printf("| %-10s | %-20s | %-3s | %-6s |\n", "PatientId", "Name", "Age", "Gender");
//            System.out.println("+------------+----------------------+-----+--------+");
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                String gender = resultSet.getString("gender");
//
//                System.out.printf("| %-10d | %-20s | %-3d | %-6s |\n", id, name, age, gender);
//            }
//
//            System.out.println("+------------+----------------------+-----+--------+");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean getPatientById(int id) {
//        String query = "SELECT * FROM patient WHERE id = ?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                return resultSet.next(); // true if exists, false otherwise
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//}



package hospitalManagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    // ✅ Correct constructor with Scanner parameter
    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.println("Enter Patient Name:");
        scanner.nextLine();  // consume leftover newline
        String name = scanner.nextLine();

        System.out.println("Enter Patient Age:");
        int age = scanner.nextInt();

        System.out.println("Enter Patient Gender:");
        scanner.nextLine();  // consume leftover newline
        String gender = scanner.nextLine();

        String query = "INSERT INTO patient(name, age, gender) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("✅ Patient Added Successfully");
            } else {
                System.out.println("❌ Failed to add Patient");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatients() {
        String query = "SELECT * FROM patient";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Patients:");
            System.out.println("+------------+----------------------+-----+--------+");
            System.out.printf("| %-10s | %-20s | %-3s | %-6s |\n", "PatientId", "Name", "Age", "Gender");
            System.out.println("+------------+----------------------+-----+--------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.printf("| %-10d | %-20s | %-3d | %-6s |\n", id, name, age, gender);
            }

            System.out.println("+------------+----------------------+-----+--------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patient WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // true if exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

