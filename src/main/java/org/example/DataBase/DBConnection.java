package org.example.DataBase;

import org.example.Model.CabModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public final class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/Cab_Booking";
    private static final String UserName = "postgres";
    private static final String Password = "1079283";

    // SignIn
    public boolean insertUserIntoDB(String username, String password, String email) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, UserName, Password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while inserting user: " + e.getMessage());
            return false;
        }
    }

    //LogIn
    public boolean checkCredentials(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, Password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error during login check: " + e.getMessage());
            return false;
        }
    }

    //Get Destination from database
    public int[] getRouteDetails(String from, String To) {
        String sql = "SELECT distance_km, price FROM routes WHERE source = ? AND destination = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, Password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, from);
            stmt.setString(2, To);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int distance = rs.getInt("distance_km");
                int price = rs.getInt("price");
                return new int[]{
                        distance, price
                };
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching route details: " + e.getMessage());
        }

        return null;
    }

    //Get CabDetails from DataBase
    public Map<Integer, CabModel> loadCabsFromDatabase() {
        final String sql = "SELECT * FROM cabs";
        Map<Integer, CabModel> CabOptions = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(URL, UserName, Password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int index = 1;
            while (rs.next()) {
                CabModel cab = new CabModel(
                        rs.getString("cab_name"),
                        rs.getInt("seats"),
                        rs.getBoolean("is_ac"),
                        rs.getInt("available_count")
                );
                CabOptions.put(index++, cab);
            }

        } catch (Exception e) {
            System.out.println("Error loading cabs from database: " + e.getMessage());
        }
        return CabOptions;
    }

    // Update cab availability in database
    public boolean updateCabAvailability(String cabName, int newAvailableCount) {
        final String sql = "UPDATE cabs SET available_count = ? WHERE cab_name = ?";

        try (Connection conn = DriverManager.getConnection(URL, UserName, Password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newAvailableCount);
            stmt.setString(2, cabName);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating cab availability: " + e.getMessage());
            return false;
        }
    }


}
