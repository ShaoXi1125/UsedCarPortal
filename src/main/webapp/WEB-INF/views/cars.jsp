<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Used Car Sales - Cars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Second-hand Car Sales</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                    <li class="nav-item"><a class="nav-link active" href="/cars">Cars</a></li>
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
        <h1>Available Cars</h1>
        <p>Browse all vehicles here!</p>
        
        <!-- 搜索表单 -->
        <form action="/search" method="get" class="row g-3 mb-4">
            <div class="col-md-3">
                <input type="text" class="form-control" name="make" placeholder="Make (e.g., Toyota)">
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" name="model" placeholder="Model (e.g., Camry)">
            </div>
            <div class="col-md-2">
                <input type="number" class="form-control" name="year" placeholder="Year">
            </div>
            <div class="col-md-2">
                <input type="number" step="0.01" class="form-control" name="minPrice" placeholder="Min Price">
            </div>
            <div class="col-md-2">
                <input type="number" step="0.01" class="form-control" name="maxPrice" placeholder="Max Price">
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </form>
    
        <div class="row">
            <c:forEach var="car" items="${carList}">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <img src="${car.imageUrl}" class="card-img-top" alt="${car.make} ${car.model}" style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${car.make} ${car.model}</h5>
                            <p class="card-text">Year: ${car.year}<br>Price: $${car.price}</p>
                            <c:if test="${not empty sessionScope.user}">
                                <a href="/car/${car.id}" class="btn btn-primary">More Info</a>
                            </c:if>
                            <c:if test="${empty sessionScope.user}">
                                <a href="/login" class="btn btn-secondary">Login to View Details</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty carList}">
                <p class="text-center">There are no vehicles to show.</p>
            </c:if>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>