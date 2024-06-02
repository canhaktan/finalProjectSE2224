import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

public class DisplayForm extends JFrame {
    private JTable displayTable;
    private DefaultTableModel tableModel;
    private JLabel Choose;
    private JLabel EnterID;
    private JLabel Filter;
    private JTextField filterField;
    private JTextField IDField;
    private JPanel inputPanel;
    private JButton byYear;
    private JButton byBF;
    private JButton bySeason;
    private JButton byRating;
    private JButton byName;
    private JButton backButton;

    public DisplayForm() {
        setTitle("Display, Edit and Filter Location");
        setSize(900, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(2);
        Choose = new JLabel("Choose Options");
        EnterID = new JLabel("Enter ID for edit:");
        Filter = new JLabel("Enter Input for Search");
        IDField = new JTextField();
        filterField = new JTextField();
        byYear = new JButton("Search by Year");
        byBF = new JButton("Search by Best Feature");
        bySeason = new JButton("Search by Season");
        byRating = new JButton("Search by Rating");
        byName = new JButton("Search by Name;");
        backButton = new JButton("Back to Main Menu");
        add(Choose);
        inputPanel = new JPanel(new GridLayout(10, 10));
        inputPanel.add(EnterID);
        inputPanel.add(IDField);
        inputPanel.add(Filter);
        inputPanel.add(filterField);
        inputPanel.add(byYear);
        inputPanel.add(bySeason);
        inputPanel.add(byName);
        inputPanel.add(byBF);
        inputPanel.add(byRating);
        inputPanel.add(backButton);
        tableModel = new DefaultTableModel(new String[]{"Visit ID", "Country Name", "City Name", "Year Visited", "Season Visited", "Best Feature", "Comment", "Rating"}, 0);
        displayTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(displayTable);
        add(inputPanel, "East");
        add(scrollPane, "Center");
        add(backButton, "North");
        ShowData();
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DisplayForm.this.dispose();
                new MenuFrame();
            }
        });
    }

    public void ShowData() {
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
                int visitId = rs.getInt("ID");
                String countryName = rs.getString("countryName");
                String cityName = rs.getString("cityName");
                String yearVisited = rs.getString("yearVisited");
                String seasonVisited = rs.getString("seasonVisited");
                String bestFeature = rs.getString("bestFeature");
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

    public void filterbyName() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            (new DisplayForm()).setVisible(true);
        });
    }
}
