import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class RegisterFrame extends JFrame {
    // Registration frame.
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameTextField,passwordTextField;
    private JButton registerButton;
    public RegisterFrame(){
        setTitle("Register Page");
        setSize(420,420);
        setLayout(new GridLayout(3,3));
        usernameLabel = new JLabel("Username: ");
        usernameTextField = new JTextField();
        passwordLabel = new JLabel("Password: ");
        passwordTextField = new JTextField();
        registerButton = new JButton("REGISTER");
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                if(!isUserExists(username)){
                    register(username,password);
                    dispose(); // closes the RegisterFrame
                }
                else{
                    JOptionPane.showMessageDialog(rootPane,"Username exists bro");
                }
            }
        });
        setVisible(true);
    }
    public void register(String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = new DatabaseConnector().connect();
            String sql = "INSERT INTO userinfo (username, password) VALUES (?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) { // This is how to control if the query returns a result or not!
                JOptionPane.showMessageDialog(rootPane, "User is now registered!"); // I changed the display message xD
                usernameTextField.setText(""); // setting it blank if user wants register again, completely optional
                passwordTextField.setText(""); // setting it blank if user wants register again, completely optional
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { // Closing ResultSet, PreparedStatement and Connection objects.
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
    public boolean isUserExists(String username) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean isExists = false;
        try {
            connection = new DatabaseConnector().connect();
            String sql = "SELECT username FROM userinfo WHERE username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            isExists = rs.next();
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(rootPane,"Some SQL related error happened");
        } finally {
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
        return isExists;
    }
}
