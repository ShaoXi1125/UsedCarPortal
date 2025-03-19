<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Edit Car</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Edit Car Details</h4>
                    </div>
                    <div class="card-body">
                        <form action="/car/update" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${car.id}">

                            <!-- Make -->
                            <div class="mb-3">
                                <label class="form-label">Make:</label>
                                <input type="text" name="make" value="${car.make}" class="form-control" required>
                            </div>

                            <!-- Model -->
                            <div class="mb-3">
                                <label class="form-label">Model:</label>
                                <input type="text" name="model" value="${car.model}" class="form-control" required>
                            </div>

                            <!-- Year -->
                            <div class="mb-3">
                                <label class="form-label">Year:</label>
                                <input type="number" name="year" value="${car.year}" class="form-control" required>
                            </div>

                            <!-- Price -->
                            <div class="mb-3">
                                <label class="form-label">Price:</label>
                                <input type="number" step="0.01" name="price" value="${car.price}" class="form-control" required>
                            </div>

                            <!-- Description -->
                            <div class="mb-3">
                                <label class="form-label">Description:</label>
                                <textarea name="description" class="form-control" rows="3">${car.description}</textarea>
                            </div>

                            <!-- Current Image -->
                            <div class="mb-3 text-center">
                                <label class="form-label d-block">Current Image:</label>
                                <img src="${car.imageUrl}" alt="Car Image" class="img-thumbnail" width="200">
                            </div>

                            <!-- Upload New Image -->
                            <div class="mb-3">
                                <label class="form-label">Upload New Image:</label>
                                <input type="file" name="image" class="form-control">
                            </div>

                            <!-- Buttons -->
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Update Car</button>
                                <a href="/my_cars" class="btn btn-secondary">Back to My Cars</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
