import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class EditFrame extends JFrame{

    private JLabel CountryName, cityName, year, seasonVisited, bestFeature, Comments, rate, editID;
    private JTextField CountryNameF, cityNameF, yearF, bestFeatureF, CommentsF, editIDF;
    private JButton editButton;
    private JComboBox<Integer> ratingBox;
    private JComboBox<String> seasonVisitedBox;

    public EditFrame() {
        setTitle("Edit Location");
        setSize(300, 500);
        JPanel panel = new JPanel(new GridLayout(9, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        editID = new JLabel("Edit ID: ");
        editIDF = new JTextField();

        CountryName = new JLabel("Country Name: ");
        CountryNameF = new JTextField();

        cityName = new JLabel("City Name: ");
        cityNameF = new JTextField();

        year = new JLabel("Year: ");
        yearF = new JTextField();

        seasonVisited = new JLabel("Visited Season:");
        String[] seasons = {"Autumn", "Winter", "Spring", "Summer"};
        seasonVisitedBox = new JComboBox<>(seasons);

        bestFeature = new JLabel("Best Feature: ");
        bestFeatureF = new JTextField();

        Comments = new JLabel("Comments: ");
        CommentsF = new JTextField();

        rate = new JLabel("Rate: ");
        Integer[] rates = {1, 2, 3, 4, 5};
        ratingBox = new JComboBox<>(rates);

        editButton = new JButton("Edit");

        panel.add(editID);
        panel.add(editIDF);
        panel.add(CountryName);
        panel.add(CountryNameF);
        panel.add(cityName);
        panel.add(cityNameF);
        panel.add(year);
        panel.add(yearF);
        panel.add(seasonVisited);
        panel.add(seasonVisitedBox);
        panel.add(bestFeature);
        panel.add(bestFeatureF);
        panel.add(Comments);
        panel.add(CommentsF);
        panel.add(rate);
        panel.add(ratingBox);
        panel.add(editButton);
        add(panel);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

    }

    public void edit() {
        String CountryName = CountryNameF.getText();
        String CityName = cityNameF.getText();
        String Year = yearF.getText();
        String Season = seasonVisitedBox.getSelectedItem().toString();
        String BestFeature = bestFeatureF.getText();
        String Comments = CommentsF.getText();
        int Rating = (int) ratingBox.getSelectedItem();
        int EditID = Integer.parseInt(editIDF.getText());

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = new DatabaseConnector().connect();
            String sql = "UPDATE visits SET country_name = ?, city_name = ?, year_visited = ?, season_visited = ?, best_feature = ?, comment = ?, rating = ? WHERE visitID = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, CountryName);
            pst.setString(2, CityName);
            pst.setString(3, Year);
            pst.setString(4, Season);
            pst.setString(5, BestFeature);
            pst.setString(6, Comments);
            pst.setInt(7, Rating);
            pst.setInt(8, EditID);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Visit updated successfully!");
                new LocationForm().loadLocations();
            } else {
                JOptionPane.showMessageDialog(null, "No visit found with the specified ID.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        } finally {
            try { // Closing PreparedStatement and Connection objects.
                if (pst != null) {
                    pst.close();
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
        SwingUtilities.invokeLater(() -> {
            new EditFrame().setVisible(true);
        });
    }
}
