<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Used Car Portal</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <!-- Home -->
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>

                <!-- 判断是否是管理员 -->
                <c:choose>
                    <c:when test="${sessionScope.user != null && sessionScope.user.role == 'ADMIN'}">
                        <!-- Admin 导航栏 -->
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/cars">Car List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/appointments">Appointment List</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <!-- User 导航栏 -->
                        <li class="nav-item">
                            <a class="nav-link" href="/car/sell">Sell Car</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/my_cars">My Car</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="car/appointments/my_appointments">My Appointment</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <!-- About Us & Contact Us -->
                <li class="nav-item">
                    <a class="nav-link" href="/about">About Us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/contact">Contact Us</a>
                </li>

                <!-- 登录/登出 -->
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <li class="nav-item">
                             <a class="nav-link btn btn-warning text-white" href="/profile"><c:out value="${sessionScope.user.username}" /></a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link btn btn-danger text-white" href="/logout">Logout</a>
                        </li>
                        
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary text-white" href="/login">Login</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
