import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationForm extends JFrame {

    private JTable displayTable;
    private DefaultTableModel tableModel;


    public LocationForm(){ // Show
        setSize(1000,1000);
        tableModel = new DefaultTableModel(new String[]{"Visit ID", "Country Name", "City Name", "Year Visited", "Season Visited", "Best Feature", "Comment", "Rating"}, 0);
        displayTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(displayTable);
        add(scrollPane, "Center");
    }
    // Prints the visits table to screen.
    public void loadLocations(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("loadVisitLocations");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM visits";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }
    public void sharedvisitsLoadLocations(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("sharedvisitsLoadLocations");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM sharedvisits";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }

    // Display the country names whose best feature is food in sorted order with respect to their rating
    public void filterCountryNameWhoseBestFeatureIsFood(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("filterCountryNameWhoseBestFeatureIsFood");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM visits WHERE best_feature = 'Food' ORDER BY rating DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }
    // Display the information of all visits done in the given year
    public void displayInfoVisits(int visitYear){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayInfoVisits");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM visits WHERE year_visited = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,visitYear);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }
    public void displayVisitInfoVisitIdGiven(int visitID){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayVisitInfoVisitIdGiven");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM visits WHERE visitID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,visitID);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }

    // Display the country name(s) that the user visited the most
    public void displayCountryNameThatUserVisitedMost(String username){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayCountryNameThatUserVisitedMost");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT MAX(count) AS timesOfVisited, t.country_name AS Country_name FROM (SELECT country_name, count(*) AS count FROM visits where visitID in (SELECT visitIDshared FROM sharedvisits WHERE yourUsername = ?) GROUP BY country_name) t GROUP BY country_name ORDER BY timesOfVisited DESC";
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }

    // Display the country name(s) that are visited ONLY in spring
    public void displayCountryNamesVisitedOnlyInSpring(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayCountryNamesVisitedOnlyInSpring");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM visits WHERE country_name NOT IN (SELECT country_name FROM visits WHERE season_visited IN ('Autumn','Summer','Winter'));";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data

            DefaultTableModel model = new DefaultTableModel();



            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");

        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }

    // Share your favorite visit id with your friend. (friendUsername,visitIdShared,yourUsername)
    public void shareFavouriteVisitIdWithFriend(String friendUsername,int visitID,String yourUsername){
        sharedvisitsLoadLocations();
        Connection connection = null;
        PreparedStatement ps = null;

        JFrame mf = new JFrame("shareFavouriteVisitIdWithFriend");
        mf.setSize(1000,1000);
        try{
            connection = new DatabaseConnector().connect();
            String sql = "INSERT INTO sharedvisits (friendUsername, visitIDshared, yourUsername) VALUES (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,friendUsername);
            ps.setInt(2,visitID);
            ps.setString(3,yourUsername);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane,"Visit is shared!");
            sharedvisitsLoadLocations();
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try { // PreparedStatement and Connection objects.
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
    }

    // Display the visit information shared with me. (In this func, from sharedvisits ......)
    public void displayVisitInfoSharedWithMe(String myUsername){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayVisitInfoSharedWithMe");
        mf.setSize(1000,1000);
        JTable table;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT sh.friendUsername,sh.yourUsername, sh.visitIDshared,country_name,city_name,year_visited,season_visited,best_feature,comment,rating FROM visits v INNER JOIN sharedvisits sh ON v.visitID = sh.visitIDshared WHERE friendUsername = ? OR yourUsername = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,myUsername);
            ps.setString(2,myUsername);
            rs = ps.executeQuery();
            // Get metadata about the result set
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get number of columns
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            mf.add(scrollPane, "Center");
        } catch (SQLException ex) {
            Logger.getLogger(LocationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
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
        mf.setVisible(true);
    }
}
