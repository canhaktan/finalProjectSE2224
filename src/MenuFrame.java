import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener { // implements ActionListener
    // Delete a visit -done
    // Add a location -done
    // Display-Edit-Update visit -done
    // Display the country names whose best feature is food in sorted order with respect to their rating -done
    // Display the image of a location with the visit id -done
    // Display the information of all visits done in the given year -done
    // Display the country name(s) that the user visited the most -done
    // Display the country name(s) that are visited only in spring -done
    // Share your favorite visit id with your friend. (friendUsername,visitIdShared,yourUsername) -done
    // Display the visit information shared with me. (In this func, from sharedvisits ......) -done
    private JButton deleteVisitB,addLocationB,displayB,editB,shareVisitB;
    public MenuFrame(){
        setTitle("Menu");
        setSize(1000,1000);
        setLayout(new GridLayout(1,5));
        deleteVisitB = new JButton("Delete visit");
        addLocationB = new JButton("Add visit");
        displayB = new JButton("Display");
        editB = new JButton("Edit");
        shareVisitB = new JButton("Share visit");
        add(deleteVisitB);
        add(addLocationB);
        add(displayB);
        add(editB);
        add(shareVisitB);
        deleteVisitB.addActionListener(this);
        addLocationB.addActionListener(this);
        displayB.addActionListener(this);
        editB.addActionListener(this);
        shareVisitB.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == deleteVisitB){
            new DeleteLocationForm();
        }
        if(e.getSource() == addLocationB){
            new AddLocationForm();
        }
        if(e.getSource() == displayB){
            new DisplayFrame();
        }
        if(e.getSource() == editB){
            new LocationForm().loadLocations();
            new EditFrame().setVisible(true);
        }
        if(e.getSource() == shareVisitB){
            JFrame jf = new JFrame("Share a new visit with your friend page");
            jf.setSize(300,300);
            jf.setLayout(new GridLayout(4,2));
            JLabel friendUsernameL,visitIdL,yourUsernameL;
            JTextField friendUsernameF,visitIdF,yourUsernameF;
            friendUsernameL = new JLabel("Enter friend's username");
            visitIdL = new JLabel("Enter visit ID");
            yourUsernameL = new JLabel("Enter your username");
            friendUsernameF = new JTextField();
            visitIdF = new JTextField();
            yourUsernameF = new JTextField();
            JButton share = new JButton("SHARE");

            jf.add(friendUsernameL);
            jf.add(friendUsernameF);
            jf.add(visitIdL);
            jf.add(visitIdF);
            jf.add(yourUsernameL);
            jf.add(yourUsernameF);
            jf.add(share);
            jf.setVisible(true);
            share.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fu = friendUsernameF.getText();
                    String yu = yourUsernameF.getText();
                    String visitIdText = visitIdF.getText();
                    try {
                        int visitId = Integer.parseInt(visitIdText);
                        if(BooleanMethodsClass.isUsernameExists(fu) && BooleanMethodsClass.isVisitIdExists(visitId) && BooleanMethodsClass.isUsernameExists(yu)){
                            new LocationForm().shareFavouriteVisitIdWithFriend(fu,visitId,yu);
                        }
                        else{
                            JOptionPane.showMessageDialog(rootPane,"Either your username or friend username doesn't exists OR visitID does not exists.");
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(rootPane, "Please enter a valid visit ID.");
                    }
                }
            });
        }
    }
}
