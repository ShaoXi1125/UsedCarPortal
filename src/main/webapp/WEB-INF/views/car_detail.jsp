<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car Details - Second-hand Car Sales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .car-details-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .car-image {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .back-btn {
            margin-top: 20px;
        }
        .card-body {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-4">
        <div class="car-details-container">
            <h2 class="text-center mb-4">Car Details</h2>
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${success}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${not empty car}">
                <div class="card">
                    <c:if test="${not empty car.imageUrl}">
                        <img src="${car.imageUrl}" alt="Car Image" class="card-img-top car-image">
                    </c:if>
                    <div class="card-body">
                        <h4 class="card-title">${car.make} ${car.model}</h4>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>Year:</strong> ${car.year}</li>
                            <li class="list-group-item"><strong>Price:</strong> RM ${car.price}</li>
                            <li class="list-group-item"><strong>Description:</strong> ${car.description}</li>
                            <p><strong>Status:</strong> <c:if test="${car.status}">For Sale</c:if><c:if test="${!car.status}">Sold/Deactivated</c:if></p>
                        </ul>
                        
                        <c:if test="${not empty sessionScope.user}">
                            <a href="appointments/book/${car.id}" class="btn btn-success back-btn">Book a test drive</a>
                            <a href="/" class="btn btn-primary back-btn">Back to Home</a>
                        </c:if>
                        <c:if test="${empty sessionScope.user}">
                            <a href="/login" class="btn btn-secondary back-btn">Login to Continue</a>
                        </c:if>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty car}">
                <p class="text-center text-danger">Car not found.</p>
            </c:if>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>