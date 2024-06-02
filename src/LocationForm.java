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
        loadLocations();
        setVisible(true);
    }
    public void loadLocations() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = new DatabaseConnector().connect();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM visits";
            rs = stmt.executeQuery(sql);
            tableModel.setRowCount(0);
            while(rs.next()) {
                int visitId = rs.getInt("visitID");
                String countryName = rs.getString("country_Name");
                String cityName = rs.getString("city_Name");
                String yearVisited = rs.getString("year_Visited");
                String seasonVisited = rs.getString("season_Visited");
                String bestFeature = rs.getString("best_Feature");
                String comment = rs.getString("comment");
                int rating = rs.getInt("rating");
                tableModel.addRow(new Object[]{visitId, countryName, cityName, yearVisited, seasonVisited, bestFeature, comment, rating});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Display the country names whose best feature is food in sorted order with respect to their rating
    public void filterCountryNameWhoseBestFeatureIsFood(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("filterCountryNameWhoseBestFeatureIsFood");
        mf.setSize(1000,1000);
        JTable table = new JTable(tableModel);
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT country_Name, rating, best_feature FROM visits WHERE best_feature = 'Food' ORDER BY rating DESC";
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
            table.setModel(model);
            mf.add(table);
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
    public void displayLocationImage(int visitID){

    }

    // Display the information of all visits done in the given year

    public void displayInfoVisits(int visitYear){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayInfoVisits");
        mf.setSize(1000,1000);
        JTable table = new JTable(tableModel);
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
            table.setModel(model);
            mf.add(table);
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


    }
    // Display the country name(s) that are visited only in spring
    public void displayCountryNamesVisitedOnlyInSpring(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        JFrame mf = new JFrame("displayCountryNamesVisitedOnlyInSpring");
        mf.setSize(1000,1000);
        JTable table = new JTable(tableModel);
        try{
            connection = new DatabaseConnector().connect();
            String sql = "SELECT country_name,season_visited FROM visits WHERE season_Visited NOT IN ('Autumn','Winter','Summer');";
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
            table.setModel(model);
            mf.add(table);
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

    public static void main(String[] args) {
        LocationForm dgfsfdg = new LocationForm();
        dgfsfdg.filterCountryNameWhoseBestFeatureIsFood();
        dgfsfdg.displayInfoVisits(2011);
        dgfsfdg.displayCountryNamesVisitedOnlyInSpring();
    }
}
