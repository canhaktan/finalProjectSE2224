import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayFrame extends JFrame implements ActionListener{
    // Delete a visit -done
    // Add a location -done
    // Display-Edit-Update visit
    // Display the country names whose best feature is food in sorted order with respect to their rating -done
    // Display the image of a location with the visit id TODO
    // Display the information of all visits done in the given year -done
    // Display the country name(s) that the user visited the most -doneALMOST
    // Display the country name(s) that are visited only in spring -done
    // Share your favorite visit id with your friend. (friendUsername,visitIdShared,yourUsername) -doneMEH
    // Display the visit information shared with me. (In this func, from sharedvisits ......) -done

    private JButton countryNamesFeatureByFoodB,locationImageB,informationOfAllVisitsInGivenYearB,countryNamesUserVisitedMostB,countryNamesVisitedOnlySpringB,visitInfoSharedWithMeB;
    private JLabel displayLocationImageL,informationOfAllVisitsInGivenYearL,countryNamesUserVisitedMostL,visitInfoSharedWithMeL;
    private JTextField visitIdImageF,infoVisitGivenYearF,countryNamesUserVisitedMostF,sharedVisitInfoWithMeF;
     public DisplayFrame(){
         setTitle("Display Frame");
         setSize(1000,1000);
         setLayout(new GridLayout(6,3));
         countryNamesFeatureByFoodB = new JButton("Country Names Feature By Food");
         locationImageB = new JButton("Location Image");
         informationOfAllVisitsInGivenYearB = new JButton("Information Of All Visits In Given Year");
         countryNamesUserVisitedMostB = new JButton("Country Names User Visited Most");
         countryNamesVisitedOnlySpringB = new JButton("Country Names Visited Only Spring");
         visitInfoSharedWithMeB = new JButton("Visit Info Shared Wit hMe");

         displayLocationImageL = new JLabel("Image of visitID loc       (visitID)");
         informationOfAllVisitsInGivenYearL = new JLabel("Info of visits in given year      (year)");
         countryNamesUserVisitedMostL = new JLabel("Country names user visited most     (username)");
         visitInfoSharedWithMeL = new JLabel("Visit info shared with me     (yourUsername)");

         visitIdImageF = new JTextField();
         infoVisitGivenYearF = new JTextField();
         countryNamesUserVisitedMostF = new JTextField();
         sharedVisitInfoWithMeF = new JTextField();

         add(displayLocationImageL);
         add(visitIdImageF);
         add(locationImageB);

         add(informationOfAllVisitsInGivenYearL);
         add(infoVisitGivenYearF);
         add(informationOfAllVisitsInGivenYearB);

         add(countryNamesUserVisitedMostL);
         add(countryNamesUserVisitedMostF);
         add(countryNamesUserVisitedMostB);

         add(visitInfoSharedWithMeL);
         add(sharedVisitInfoWithMeF);
         add(visitInfoSharedWithMeB);



         add(countryNamesFeatureByFoodB);
         add(countryNamesVisitedOnlySpringB);

         setVisible(true);

         countryNamesFeatureByFoodB.addActionListener(this);
         locationImageB.addActionListener(this);
         informationOfAllVisitsInGivenYearB.addActionListener(this);
         countryNamesUserVisitedMostB.addActionListener(this);
         countryNamesVisitedOnlySpringB.addActionListener(this);
         visitInfoSharedWithMeB.addActionListener(this);

     }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == countryNamesFeatureByFoodB){
            new LocationForm().filterCountryNameWhoseBestFeatureIsFood();
        }
        if(e.getSource() == locationImageB){
            showImage();
            String visitIdText = visitIdImageF.getText();
            try{
                int visitID = Integer.parseInt(visitIdText);
                if(BooleanMethodsClass.isVisitIdExists(visitID)){
                    new LocationForm().displayVisitInfoVisitIdGiven(visitID);
                }
            }catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(rootPane, "Please enter a valid visit ID.");
            }
        }
        if(e.getSource() == informationOfAllVisitsInGivenYearB){
            String givenYearText = infoVisitGivenYearF.getText();
            try {
                int givenYear = Integer.parseInt(givenYearText);
                new LocationForm().displayInfoVisits(givenYear);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(rootPane, "Please enter a valid year.");
            }
        }
        if(e.getSource() == countryNamesUserVisitedMostB){
            String user = countryNamesUserVisitedMostF.getText();
            if(BooleanMethodsClass.isUsernameExists(user)){
                new LocationForm().displayCountryNameThatUserVisitedMost(user);
            }
            else{
                JOptionPane.showMessageDialog(rootPane,"Username does not exists");
            }
        }
        if(e.getSource() == countryNamesVisitedOnlySpringB){
            new LocationForm().displayCountryNamesVisitedOnlyInSpring();
        }
        if(e.getSource() == visitInfoSharedWithMeB){
            String myUsername = sharedVisitInfoWithMeF.getText();
            if(BooleanMethodsClass.isUsernameExists(myUsername)){
                new LocationForm().displayVisitInfoSharedWithMe(myUsername);
            }
            else{
                JOptionPane.showMessageDialog(rootPane,"Username does not exists");
            }

        }
    }
    // couldn't do it :( not working
    private void showImage() {
        String visitId = visitIdImageF.getText();
        if (visitId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a visit ID");
            return;
        }

        String imagePath = "C://Users//canha//IdeaProjects//finalProjectSE2224//src//images//" + visitId + ".png";
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            JOptionPane.showMessageDialog(this, "Image not found for Visit ID: " + visitId);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseConnector().connect();
            String sql = "SELECT * FROM visits WHERE visitID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(visitId));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String countryName = rs.getString("country_name");
                String cityName = rs.getString("city_name");
                String yearVisited = rs.getString("year_visited");
                String seasonVisited = rs.getString("season_visited");
                String bestFeature = rs.getString("best_feature");
                String comment = rs.getString("comment");
                int rating = rs.getInt("rating");

                BufferedImage image = ImageIO.read(imageFile);

                JFrame imageFrame = new JFrame("Image and Info for Visit ID: " + visitId);
                imageFrame.setSize(600, 800);
                imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                imageFrame.setLocationRelativeTo(null);

                JPanel panel = new JPanel(new BorderLayout());
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                JTextArea infoArea = new JTextArea();
                infoArea.setEditable(false);
                infoArea.setText("Country Name: " + countryName + "\n" +
                        "City Name: " + cityName + "\n" +
                        "Year Visited: " + yearVisited + "\n" +
                        "Season Visited: " + seasonVisited + "\n" +
                        "Best Feature: " + bestFeature + "\n" +
                        "Comment: " + comment + "\n" +
                        "Rating: " + rating);

                panel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
                panel.add(new JScrollPane(infoArea), BorderLayout.SOUTH);
                imageFrame.add(panel);
                imageFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No data found for Visit ID: " + visitId);
            }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image or data");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
