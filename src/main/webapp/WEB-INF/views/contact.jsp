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
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

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