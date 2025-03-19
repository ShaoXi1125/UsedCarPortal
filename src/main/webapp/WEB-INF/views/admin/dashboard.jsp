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
            max-width: 900px;
            margin: 0 auto;
        }
        .table thead {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-4">
        <div class="dashboard-container">
            <h2 class="text-center mb-4">Admin Dashboard</h2>
            <c:if test="${not empty admin}">
                <div class="alert alert-info text-center">
                    Welcome, <strong>${admin.username}</strong>! You are logged in as an Admin.
                </div>
            </c:if>
            <div class="card shadow">
                <div class="card-body">
                    <h4 class="mb-3">User Management</h4>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Role</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.username}</td>
                                        <td>${user.email}</td>
                                        <td>${user.role}</td>
                                        <td>
                                            <c:if test="${user.role != 'ADMIN'}">
                                                <form action="/admin/set-admin" method="post" class="d-inline">
                                                    <input type="hidden" name="userId" value="${user.id}">
                                                    <button type="submit" class="btn btn-sm btn-primary">Make Admin</button>
                                                </form>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>