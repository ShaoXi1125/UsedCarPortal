<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Appointments List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-4">
        <h2 class="mb-3">All Appointments</h2>
        <table class="table table-striped table-hover table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>User</th>
                    <th>Car</th>
                    <th>Date & Time</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.id}</td>
                        <td>${appointment.user.username}</td>
                        <td>${appointment.car.make} ${appointment.car.model}</td>
                        <td>${appointment.appointmentDateTime}</td>
                        <td>
                            <c:choose>
                                <c:when test="${appointment.status == 'PENDING'}">
                                    <span class="badge bg-warning text-dark">Pending</span>
                                </c:when>
                                <c:when test="${appointment.status == 'APPROVED'}">
                                    <span class="badge bg-success">Approved</span>
                                </c:when>
                                <c:when test="${appointment.status == 'DENIED'}">
                                    <span class="badge bg-danger">Denied</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-secondary">Unknown</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${appointment.status == 'PENDING'}">
                                <form action="/car/appointments/${appointment.id}/approve" method="post" class="d-inline-block">
                                    <button type="submit" class="btn btn-success btn-sm">Approve</button>
                                </form>
                                <form action="/car/appointments/${appointment.id}/deny" method="post" class="d-inline-block">
                                    <button type="submit" class="btn btn-danger btn-sm">Deny</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
