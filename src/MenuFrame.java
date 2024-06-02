import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener { // implements ActionListener

    // Delete a visit -done
    // Add a location -done
    // Display-Edit-Update visit
    // Display the country names whose best feature is food in sorted order with respect to their rating
    // Display the image of a location with the visit id
    // Display the information of all visits done in the given year
    // Display the country name(s) that the user visited the most
    // Display the country name(s) that are visited only in spring
    // Share your favorite visit id with your friend. (friendUsername,visitIdShared,yourUsername) +|+
    // Display the visit information shared with me. (In this func, from sharedvisits ......) +|+
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

        }
        if(e.getSource() == editB){

        }
        if(e.getSource() == shareVisitB){

        }
    }
    public static void main(String[] args) {
        MenuFrame mesfdgfd = new MenuFrame();
    }
}
