<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-4">
        <h2>User Profile</h2>
        <c:if test="${not empty user}">
            <p>Username: ${user.username}</p>
            <p>Email: ${user.email}</p>
    
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">
                    <p>${successMessage}</p>
                </div>
            </c:if>
            <form action="/update" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">New Email:</label>
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">New Password:</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Update Profile</button>
            </form>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>