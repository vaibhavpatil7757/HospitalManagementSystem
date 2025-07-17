
package hospitalManagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    private Scanner scanner;

    // ✅ Correct constructor with Scanner parameter
    public Doctor(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addDoctor() {
        System.out.println("Enter Doctor Name:");
        scanner.nextLine();  // consume leftover newline
        String name = scanner.nextLine();

        System.out.println("Enter Doctor Specialization:");
        String specialization = scanner.nextLine();

        String query = "INSERT INTO doctor(name, specialization) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialization);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✅ Doctor added successfully.");
            } else {
                System.out.println("❌ Failed to add doctor.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctor";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\nDoctors:");
            System.out.println("+-----------+----------------------+----------------------+");
            System.out.printf("| %-9s | %-20s | %-20s |\n", "Doctor ID", "Name", "Specialization");
            System.out.println("+-----------+----------------------+----------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // ✅ Correct column name
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                System.out.printf("| %-9d | %-20s | %-20s |\n", id, name, specialization);
            }

            System.out.println("+-----------+----------------------+----------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int doctorId) {
        String query = "SELECT * FROM doctor WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // ✅ true if doctor exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

