<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sell a Car</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/fragments/navbar.jsp" %>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Post Your Car for Sale</h4>
                    </div>
                    <div class="card-body">
                        <!-- 成功/错误信息 -->
                        <c:if test="${not empty success}">
                            <div class="alert alert-success">${success}</div>
                        </c:if>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="/car/sell" method="post" enctype="multipart/form-data">
                            <!-- Make -->
                            <div class="mb-3">
                                <label class="form-label">Make:</label>
                                <input type="text" name="make" class="form-control" required>
                            </div>

                            <!-- Model -->
                            <div class="mb-3">
                                <label class="form-label">Model:</label>
                                <input type="text" name="model" class="form-control" required>
                            </div>

                            <!-- Year -->
                            <div class="mb-3">
                                <label class="form-label">Year:</label>
                                <input type="number" name="year" class="form-control" required>
                            </div>

                            <!-- Price -->
                            <div class="mb-3">
                                <label class="form-label">Price:</label>
                                <input type="number" step="0.01" name="price" class="form-control" required>
                            </div>

                            <!-- Description -->
                            <div class="mb-3">
                                <label class="form-label">Description:</label>
                                <textarea name="description" class="form-control" rows="3"></textarea>
                            </div>

                            <!-- Image Upload -->
                            <div class="mb-3">
                                <label class="form-label">Upload Car Image:</label>
                                <input type="file" name="image" accept="image/*" class="form-control" required>
                            </div>

                            <!-- 提交按钮 -->
                            <button type="submit" class="btn btn-primary w-100">Post Car</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
