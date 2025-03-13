<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sell a Car</title>
</head>
<body>
    <h2>Post Your Car for Sale</h2>

    <c:if test="${not empty success}">
        <p style="color:green">${success}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="/car/sell" method="post" enctype="multipart/form-data">
        <label>Make: <input type="text" name="make" required></label><br>
        <label>Model: <input type="text" name="model" required></label><br>
        <label>Year: <input type="number" name="year" required></label><br>
        <label>Price: <input type="number" step="0.01" name="price" required></label><br>
        <label>Description: <textarea name="description"></textarea></label><br>
        <label>Image: <input type="file" name="image" accept="image/*" required></label><br>
        <button type="submit">Post Car</button>
    </form>
</body>
</html>
