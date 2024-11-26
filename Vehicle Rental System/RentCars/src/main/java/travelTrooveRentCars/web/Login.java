package travelTrooveRentCars.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travelTrooveRentCars.dao.DBConnect;
import travelTrooveRentCars.model.Customer;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from customer where userName = ? and password = ?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("cusID"),
                    rs.getString("cusName"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("userName"),
                    rs.getString("password")
                );
                session.setAttribute("customer", customer);
                dispatcher = request.getRequestDispatcher("dash.jsp");
                System.out.println("User Found!!: " + rs.getString("userName"));
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
                System.out.println("User Not Found!!");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
