import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameTextField,passwordTextField;
    private JButton loginButton,registerButton;
    public LoginFrame(){
        setTitle("Login Page");
        setSize(420,420);
        setLayout(new GridLayout(3,3));
        usernameLabel = new JLabel("Username: ");
        usernameTextField = new JTextField();
        passwordLabel = new JLabel("Password: ");
        passwordTextField = new JTextField();
        loginButton = new JButton("LOGIN");
        registerButton = new JButton("Register?");
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(loginButton);
        add(registerButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                if(login(username,password)){
                    JOptionPane.showMessageDialog(rootPane,"Login successful");
                    new MenuFrame();
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(rootPane,"Incorrect username or password");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });
        setVisible(true);
    }
    private boolean login(String username,String password){
        boolean canLogin;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = new DatabaseConnector().connect();
            String sql = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            canLogin = rs.next();
        } catch (SQLException e) {
            canLogin = false;
            JOptionPane.showMessageDialog(rootPane, "Error while trying to login?");
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
        return canLogin;
    }
}
