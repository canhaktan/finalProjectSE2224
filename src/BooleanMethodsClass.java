import java.sql.*;

public class BooleanMethodsClass {
    // I wanted to create a separate class that holds boolean methods.
    // For easy to use.
    public static boolean isUsernameExists(String username) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean isExists = false;
        try {
            connection = new DatabaseConnector().connect();
            String sql = "SELECT username FROM userinfo WHERE username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            isExists = rs.next();
        } catch (SQLException e) {
            System.out.println("Error happened: " + e.getMessage());
        } finally {
            try { // Closing ResultSet, PreparedStatement and Connection objects.
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isExists;
    }
    public static boolean isVisitIdExists(int visitId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean isExists = false;
        try {
            connection = new DatabaseConnector().connect();
            String sql = "SELECT visitID FROM visits WHERE visitID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, visitId);
            rs = ps.executeQuery();
            isExists = rs.next();
        } catch (SQLException e) {
            System.out.println("Error happened: " + e.getMessage());
        } finally {
            try { // Closing ResultSet, PreparedStatement and Connection objects.
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isExists;
    }
}
