<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Car List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>
    <div class="container mt-4">
        <h2 class="mb-4">Admin Car Management</h2>
        
        <form action="/admin/cars/search" method="GET" class="row g-3 mb-4">
            <div class="col-md-2">
                <input type="text" name="make" class="form-control" placeholder="Make">
            </div>
            <div class="col-md-2">
                <input type="text" name="model" class="form-control" placeholder="Model">
            </div>
            <div class="col-md-2">
                <input type="number" name="year" class="form-control" placeholder="Year">
            </div>
            <div class="col-md-2">
                <input type="number" name="minPrice" class="form-control" placeholder="Min Price" step="0.01">
            </div>
            <div class="col-md-2">
                <input type="number" name="maxPrice" class="form-control" placeholder="Max Price" step="0.01">
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </form>
        
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="car" items="${allCars}">
                    <tr>
                        <td>${car.id}</td>
                        <td>${car.make}</td>
                        <td>${car.model}</td>
                        <td>${car.year}</td>
                        <td>${car.price}</td>
                        <td>
                            <span class="badge ${car.status ? 'bg-success' : 'bg-danger'}">
                                ${car.status ? "Active" : "Deactivated"}
                            </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${car.status}">
                                    <a href="/admin/car/deactivate/${car.id}" class="btn btn-danger btn-sm">Deactivate</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/admin/car/reactivate/${car.id}" class="btn btn-success btn-sm">Reactivate</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
