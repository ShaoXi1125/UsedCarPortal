<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book a Test Drive</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Book a Test Drive for ${car.model}</h4>
                    </div>
                    <div class="card-body">
                        <form action="/car/appointments/book" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                            <c:if test="${not empty car}">
                                <input type="hidden" name="carId" value="${car.id}">
                            </c:if>

                            <!-- 日期时间选择 -->
                            <div class="mb-3">
                                <label for="dateTime" class="form-label">Select Date & Time:</label>
                                <input type="datetime-local" id="dateTime" name="dateTime" class="form-control" required>
                            </div>

                            <!-- 额外备注 -->
                            <div class="mb-3">
                                <label for="notes" class="form-label">Additional Notes:</label>
                                <textarea id="notes" name="notes" class="form-control" rows="3"></textarea>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Book Appointment</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
