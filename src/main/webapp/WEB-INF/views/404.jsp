<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 - Not Found</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error-container {
            max-width: 600px;
            margin: 0 auto;
            text-align: center;
            padding-top: 50px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-4">
        <div class="error-container">
            <h1 class="text-danger">404 - Not Found</h1>
            <p class="lead">Oops! The page you're looking for does not exist.</p>
            <p>It looks like you may have mistyped the URL or the page has been removed.</p>
            <div class="mt-4">
                <a href="/" class="btn btn-primary">Return to Home</a>
                <c:if test="${empty sessionScope.user}">
                    <a href="/login" class="btn btn-secondary">Login</a>
                </c:if>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>