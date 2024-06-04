import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AddLocationForm extends JFrame {
    private JLabel countryNameLabel, cityNameLabel, yearVisitedLabel,seasonVisitedLabel, bestFeatureLabel, commentLabel, ratingLabel;
    private JTextField countryNameField,cityNameField,yearVisitedField,bestFeatureField,commentField;
    private JComboBox<String>seasonVisitedBox;
    private JComboBox<Integer>ratingBox;
    private JButton addLocButton;
    public AddLocationForm(){
        setTitle("Add Location Form");
        setSize(300,550);
        setLayout(new GridLayout(9,2)); // can change?
        countryNameLabel = new JLabel("Country Name: ");
        countryNameField = new JTextField();

        cityNameLabel = new JLabel("City Name: ");
        cityNameField = new JTextField();

        yearVisitedLabel = new JLabel("Year Visited: ");
        yearVisitedField = new JTextField();

        String[] seasons = {"Autumn", "Winter", "Spring", "Summer"};
        seasonVisitedLabel = new JLabel("Season Visited: ");
        seasonVisitedBox = new JComboBox<>(seasons);

        bestFeatureLabel = new JLabel("Feature: ");
        bestFeatureField = new JTextField();

        commentLabel = new JLabel("Comment: ");
        commentField = new JTextField();

        Integer[] ratings = {1,2,3,4,5};
        ratingLabel = new JLabel("Rating: ");
        ratingBox = new JComboBox<>(ratings);

        addLocButton = new JButton("ADD");

        add(countryNameLabel);
        add(countryNameField);
        add(cityNameLabel);
        add(cityNameField);
        add(yearVisitedLabel);
        add(yearVisitedField);
        add(seasonVisitedLabel);
        add(seasonVisitedBox);
        add(bestFeatureLabel);
        add(bestFeatureField);
        add(commentLabel);
        add(commentField);
        add(ratingLabel);
        add(ratingBox);
        add(addLocButton);
        new LocationForm().loadLocations();
        addLocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLocation();
                dispose();
            }
        });
        setVisible(true);
    }
    public void addLocation(){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = new DatabaseConnector().connect();
            String sql = "INSERT INTO visits (country_Name, city_Name, year_Visited, season_Visited, best_Feature, comment, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            String contN = countryNameField.getText();
            String ciN = cityNameField.getText();
            String yV = yearVisitedField.getText();
            String sV = (String) seasonVisitedBox.getSelectedItem();
            String bF = bestFeatureField.getText();
            String com = commentField.getText();
            String rating = String.valueOf(ratingBox.getSelectedItem());
            ps.setString(1,contN);
            ps.setString(2,ciN);
            ps.setString(3,yV);
            ps.setString(4,sV);
            ps.setString(5,bF);
            ps.setString(6,com);
            ps.setString(7,rating);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane,"Location added successfully! :)");
            new LocationForm().loadLocations();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Error happened: " + e.getMessage());
        }
        finally {
            try { // Closing PreparedStatement and Connection objects.
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
}
