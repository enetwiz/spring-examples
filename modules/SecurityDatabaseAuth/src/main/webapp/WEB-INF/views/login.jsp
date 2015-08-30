<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Page</title>
    </head>
    <body>
        <h1>Spring Security login page</h1>
        
        <form id="form" action="<c:url value='/login.do'/>" method="POST">
         
            <c:if test="${not empty param.err}">
                <div><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
            </c:if>
            <c:if test="${not empty param.out}">
                <div>You've logged out successfully.</div>
            </c:if>
            <c:if test="${not empty param.time}">
                <div>You've been logged out due to inactivity.</div>
            </c:if>
             
            Username:<br>
            <input type="text" name="username" value=""/><br><br>
            Password:<br>
            <input type="password" name="password" value=""/>
             
            <input value="Login" name="submit" type="submit"/>
            
        </form>
        
    </body>
</html>
