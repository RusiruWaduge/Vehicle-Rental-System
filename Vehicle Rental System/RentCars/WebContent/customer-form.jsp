<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <style>
        body {
            background-color: #E5E1DA !important;
        }

        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #333333;
            color: white;
            text-align: center;
            padding: 10px 0;
        }

        .card-body {
            background-color: #F2F1EB;
            padding: 20px;
        }

        .form-group {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #333333">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> Customer Management App </a>
            </div>

            
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${customer != null}">
                    <form action="updateCustomer" method="post">
                </c:if>
                <c:if test="${customer == null}">
                    <form action="insertCustomer" method="post">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="${customer != null}">
                            Edit Customer
                        </c:if>
                        <c:if test="${customer == null}">
                            Add New Customer
                        </c:if>
                    </h2>
                </caption>

                <c:if test="${customer != null}">
                    <input type="hidden" name="cusID" value="<c:out value='${customer.cusID}' />" />
                </c:if>

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" value="<c:out value='${customer.cusName}' />" class="form-control"
                        name="cusName" required="required">
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" value="<c:out value='${customer.email}' />" class="form-control" name="email"
                        required="required">
                </div>

                <div class="form-group">
                    <label>Phone Number</label>
                    <input type="text" value="<c:out value='${customer.phoneNumber}' />" class="form-control"
                        name="phoneNumber" required="required" pattern="[0-9]{10}"
                        title="Please enter a 10-digit phone number">
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <input type="text" value="<c:out value='${customer.address}' />" class="form-control" name="address"
                        required="required">
                </div>

                <div class="form-group">
                    <label>User Name</label>
                    <input type="text" value="<c:out value='${customer.userName}' />" class="form-control" name="userName"
                        required="required">
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input type="password" value="<c:out value='${customer.password}' />" class="form-control"
                        name="password" required="required" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{1,6}$"
                        title="Password must contain at least one letter, one number, and be at most 6 characters long">
                </div>

                <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
    <footer>
        <!-- Footer content here -->
        <p>&copy; 2024 User Management</p>
    </footer>
</body>
</html>
