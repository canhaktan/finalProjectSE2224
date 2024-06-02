import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void main(String[] args) {
        LocationForm dgfsfdg = new LocationForm();
    }
}
