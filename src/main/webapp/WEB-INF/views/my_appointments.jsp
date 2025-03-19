<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Appointments</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4 text-center">My Appointments</h2>

    <div class="table-responsive">
        <table class="table table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Car</th>
                    <th>Appointment Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>
                            <strong>${appointment.car.make} ${appointment.car.model}</strong> 
                            <br>
                            <small class="text-muted">(${appointment.car.year})</small>
                        </td>
                        <td>${appointment.appointmentDateTime}</td>
                        <td>
                            <c:choose>
                                <c:when test="${appointment.status.name() eq 'APPROVED'}">
                                    <span class="badge bg-success"><i class="bi bi-check-circle-fill"></i> APPROVED</span>
                                </c:when>
                                <c:when test="${appointment.status.name() eq 'DENIED'}">
                                    <span class="badge bg-danger"><i class="bi bi-x-circle-fill"></i> DENIED</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-warning text-dark"><i class="bi bi-hourglass-split"></i> PENDING</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <button class="btn btn-primary btn-sm"><i class="bi bi-eye"></i> View</button>
                            <button class="btn btn-danger btn-sm"><i class="bi bi-trash"></i> Cancel</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap 5 JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
