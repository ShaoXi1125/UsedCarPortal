<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Cars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-5">
        <h2 class="mb-4">My Cars</h2>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr class="text-center">
                    <th>ID</th>
                    <th>Car Name</th>
                    <th>Price (RM)</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty myCars}">
                        <tr>
                            <td colspan="5" class="text-center text-muted">No cars found.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="car" items="${myCars}">
                            <tr class="text-center">
                                <td>${car.id}</td>
                                <td>${car.make} ${car.model}</td>
                                <td>${car.price}</td>
                                <td>
                                    <span class="badge ${car.status ? 'bg-success' : 'bg-secondary'}">
                                        ${car.status ? "Active" : "Inactive"}
                                    </span>
                                </td>
                                <td>
                                    <div class="d-flex justify-content-center gap-2">
                                        <a href="/car/edit/${car.id}" class="btn btn-sm btn-primary">Edit</a>
                                        <c:choose>
                                            <c:when test="${car.status}">
                                                <a href="/car/deactivate/${car.id}" class="btn btn-sm btn-danger">Deactivate</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/car/reactivate/${car.id}" class="btn btn-sm btn-success">Reactivate</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
