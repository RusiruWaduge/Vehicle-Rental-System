package travelTrooveRentCars.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelTrooveRentCars.dao.CustomerDao;
import travelTrooveRentCars.model.Customer;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDao customerDao;

    public CustomerServlet() {
        this.customerDao = new CustomerDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newCustomer":
                    showNewFormCustomer(request, response);
                    break;
                case "/insertCustomer":
                    insertCustomer(request, response);
                    break;
                case "/deleteCustomer":
                    deleteCustomer(request, response);
                    break;
                case "/editCustomer":
                    showEditFormCustomer(request, response);
                    break;
                case "/updateCustomer":
                    updateCustomer(request, response);
                    break;
                default:
                    listCustomers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewFormCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String cusName = request.getParameter("cusName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Customer newCustomer = new Customer(cusName, address, email, phoneNumber, userName, password);
        customerDao.insertCustomer(newCustomer);
        response.sendRedirect("login.jsp");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int cusID = Integer.parseInt(request.getParameter("cusID"));
        customerDao.deleteUser(cusID);
        response.sendRedirect("register.jsp");
    }

    private void showEditFormCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int cusID = Integer.parseInt(request.getParameter("cusID"));
        Customer existingCustomer = customerDao.selectCustomer(cusID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int cusID = Integer.parseInt(request.getParameter("cusID"));
        String cusName = request.getParameter("cusName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Customer customer = new Customer(cusID, cusName, address, email, phoneNumber, userName, password);
        customerDao.updateCustomer(customer);
        
        // Retrieve the updated customer details from the database
        Customer updatedCustomer = customerDao.selectCustomer(cusID);
        
        // Update the session attribute with the updated customer details
        request.getSession().setAttribute("customer", updatedCustomer);
        
        response.sendRedirect("dash.jsp");
    }


    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Customer> listCustomers = customerDao.selectAllCustomer();
        request.setAttribute("listCustomers", listCustomers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
        dispatcher.forward(request, response);
    }
}
