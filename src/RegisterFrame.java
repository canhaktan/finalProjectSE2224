import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterFrame extends JFrame {
    private final Connection connection = new DatabaseConnector().connect();
    private PreparedStatement ps = null;
    private ResultSet rs = null;
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
                }
                else{
                    JOptionPane.showMessageDialog(rootPane,"Username exists bro");
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void register(String username, String password) {
        try {
            String sql = "INSERT INTO userinfo (username, password) VALUES (?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    public boolean isUserExists(String username) {
        boolean isExists = false;
        try {
            String sql = "SELECT username FROM userinfo WHERE username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            isExists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("username exists, try something else fam");
        } finally {
            closeResources();
        }
        return isExists;
    }
    private void closeResources() { // Closing ResultSet, PreparedStatement and Connection objects.
        try {
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
}
