import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
public class DeleteLocationForm extends JFrame {
    private JLabel delete,option;
    private JTextField deleteF;
    private JButton deleteButton,backButton;
    private JTable displayTable;
    private DefaultTableModel tableModel;

    public DeleteLocationForm() {
        setTitle("Delete Location");
        setSize(800, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(2);
        option = new JLabel("Enter the location's visit ID that you want to delete");
        delete = new JLabel("Enter: ");
        deleteF = new JTextField();
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back to Main Menu");
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(option);
        inputPanel.add(new JLabel(""));
        inputPanel.add(delete);
        inputPanel.add(deleteF);
        inputPanel.add(deleteButton);
        inputPanel.add(backButton);
        tableModel = new DefaultTableModel(new String[]{"Visit ID", "Country Name", "City Name", "Year Visited", "Season Visited", "Best Feature", "Comment", "Rating"}, 0);
        displayTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(displayTable);
        add(inputPanel, "North");
        add(scrollPane, "Center");
        add(backButton, "South");
        new LocationForm().loadLocations();

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame();
                dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String visitIdText = deleteF.getText();
                try {
                    int visitId = Integer.parseInt(visitIdText);
                    if (deleteLocation(visitId)) {
                        JOptionPane.showMessageDialog(rootPane, "Location deleted.");
                        new LocationForm().loadLocations();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Location ID doesn't exist.");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(rootPane, "Please enter a valid visit ID.");
                }
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String visitIdText = deleteF.getText();
            try {
                int visitId = Integer.parseInt(visitIdText);
                if (deleteLocation(visitId)) {
                    JOptionPane.showMessageDialog(rootPane, "Location deleted.");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Location ID doesn't exist.");
                }
            } catch (NumberFormatException var4) {
                JOptionPane.showMessageDialog(rootPane, "Please enter a valid visit ID.");
            }
        }
    }

    public boolean deleteLocation(int visitId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new DatabaseConnector().connect();
            String sql = "DELETE FROM visits WHERE visitID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, visitId);
            int rowsAffected = stmt.executeUpdate();
            boolean canDelete = rowsAffected > 0;
            return canDelete;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
        return false;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            (new DeleteLocationForm()).setVisible(true);
        });
    }
}
