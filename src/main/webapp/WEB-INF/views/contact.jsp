<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Second-hand Car Sales</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="/cars">Cars</a></li>
                </ul>
                <ul class="navbar-nav">
                    <c:if test="${empty sessionScope.user}">
                        <li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="/register">Register</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                        <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                        <li class="nav-item"><a class="nav-link" href="/profile">Profile</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h1>Contact Us</h1>
        <p>If you have any questions or need assistance, feel free to reach out to us!</p>
        <ul>
            <li>Email: support@usedcarsales.com</li>
            <li>Phone: +1-123-456-7890</li>
            <li>Address: 123 Car Lane, Auto City, AC 12345</li>
        </ul>
        <p>We'd love to hear from you!</p>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>