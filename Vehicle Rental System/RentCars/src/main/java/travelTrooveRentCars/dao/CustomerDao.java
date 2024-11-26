package travelTrooveRentCars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travelTrooveRentCars.model.Customer;


public class CustomerDao {

	private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer" +
            "  (cusName, address, email, phoneNumber, userName, password) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT cusID, cusName, address, email, phoneNumber, userName, password FROM customer WHERE cusID = ?";
    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM customer";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE cusID = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET cusName = ?, address = ?,email = ?, phoneNumber = ?, userName = ?, password = ? WHERE cusID = ?";

    //insert
    public void insertCustomer(Customer customer) {
        System.out.println(INSERT_CUSTOMER_SQL);

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getCusName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getUserName());
            preparedStatement.setString(6, customer.getPassword());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update
    public boolean updateCustomer(Customer customer) {
        boolean rowUpdated = false;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
        	statement.setString(1, customer.getCusName());
        	statement.setString(2, customer.getAddress());
        	statement.setString(3, customer.getEmail());
        	statement.setString(4, customer.getPhoneNumber());
        	statement.setString(5, customer.getUserName());
        	statement.setString(6, customer.getPassword());
            statement.setInt(7, customer.getCusID());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    //select by id
    public Customer selectCustomer(int userID) {
    	Customer customer = null;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, userID);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String cusName = rs.getString("cusName");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String userName = rs.getString("userName");
                String password = rs.getString("password");

                customer = new Customer(userID, cusName, address, email, phoneNumber, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    //select all
    public List<Customer> selectAllCustomer() {
        List<Customer> customer = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int cusID = rs.getInt("cusID");
                String cusName = rs.getString("cusName");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String userName = rs.getString("userName");
                String password = rs.getString("password");

                customer.add(new Customer(cusID, cusName, address, email, phoneNumber, userName, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    //delete
    public boolean deleteUser(int userID) {
        boolean rowDeleted = false;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            statement.setInt(1, userID);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
    
}
