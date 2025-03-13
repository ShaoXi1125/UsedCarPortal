<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .dashboard-container {
            max-width: 800px;
            margin: 0 auto;
        }
    </style>
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
                    <c:if test="${not empty sessionScope.user}">
                        <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                        <li class="nav-item"><a class="nav-link" href="/profile">Profile</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="dashboard-container">
            <h2 class="text-center">Admin Dashboard</h2>
            <c:if test="${not empty admin}">
                <div class="alert alert-info">
                    Welcome, ${admin.username}! You are logged in as an Admin.
                </div>
            </c:if>
            <div class="card mb-4">
                <div class="card-body">
                   
                   
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>