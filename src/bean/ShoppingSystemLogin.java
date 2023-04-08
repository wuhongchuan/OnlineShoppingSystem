package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ShoppingSystemLogin {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/database?serverTimezone = GMT";
    static final String USER = "root";
    static final String PASSWORD = "crocodile";


    static JFrame loginFrame = new JFrame("Login");
    static JTextField loginUsernameField = new JTextField(20);
    static JPasswordField loginPasswordField = new JPasswordField(20);
    static JButton loginButton = new JButton("Login");
    static JButton loginRegistrationButton = new JButton("Register");

    // 创建注册界面
    static JFrame registrationFrame = new JFrame("Registration");
    static JTextField registrationUsernameField = new JTextField(20);
    static JPasswordField registrationPasswordField = new JPasswordField(20);
    static JTextField registrationCustomerIdField = new JTextField(20);
    static JTextField registrationPhoneNumberField = new JTextField(20);
    static JTextField registrationAddressField = new JTextField(20);
    static JButton registrationButton = new JButton("Register");

    public static void login(){
        // login frame
        JPanel loginPanel = new JPanel(new GridLayout(4, 2));
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUsernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPasswordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);
        loginPanel.add(loginRegistrationButton);
        loginFrame.add(loginPanel);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginUsernameField.getText();
                String password = new String(loginPasswordField.getPassword());
                if (login(username, password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful.");
                    Integer cid = shopItemShow(username);

                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    loginFrame.setVisible(false);
                    new ShopMain(cid);

                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.");
                }
            }
        });

        loginRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
                register();
            }
        });


    }

    public static void register() {
        // register frame
        JPanel registrationPanel = new JPanel(new GridLayout(6, 2));
        registrationPanel.add(new JLabel("Username:"));
        registrationPanel.add(registrationUsernameField);
        registrationPanel.add(new JLabel("Password:"));
        registrationPanel.add(registrationPasswordField);
        registrationPanel.add(new JLabel("Customer ID:"));
        registrationPanel.add(registrationCustomerIdField);
        registrationPanel.add(new JLabel("Phone Number:"));
        registrationPanel.add(registrationPhoneNumberField);
        registrationPanel.add(new JLabel("Address:"));
        registrationPanel.add(registrationAddressField);
        registrationPanel.add(new JLabel(""));
        registrationPanel.add(registrationButton);
        registrationFrame.add(registrationPanel);
        registrationFrame.setSize(400, 200);
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setVisible(true);


        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = registrationUsernameField.getText();
                String password = new String(registrationPasswordField.getPassword());
                String customerId = registrationCustomerIdField.getText();
                String phoneNumber = registrationPhoneNumberField.getText();
                String address = registrationAddressField.getText();
                if (register(username, password, customerId, phoneNumber, address)) {
                    JOptionPane.showMessageDialog(registrationFrame, "Registration successful.");
                    registrationFrame.setVisible(false);
                    login();
                } else {                JOptionPane.showMessageDialog(registrationFrame, "Registration failed.");
                }
            }
        });

    }


    public static void main(String[] args) {
        login();
    }

    public static Integer shopItemShow(String username) {
        Connection conn = null;
        Statement stmt = null;
        Integer customerId = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT customerId FROM users where username='" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customerId = rs.getInt(1);
            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return customerId;
    }

    static boolean login(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    static boolean register(String username, String password, String customerId, String phoneNumber, String address) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO users (username, password, customerId, telephone, address) VALUES ('" +
                    username + "', '" + password + "', '" + customerId + "', '" + phoneNumber + "', '" + address + "')";
            int count = stmt.executeUpdate(sql);

            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}