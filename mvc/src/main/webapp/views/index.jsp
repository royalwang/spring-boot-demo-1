<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Hello Spring MVC</title>
    <link rel="stylesheet" type="text/css" href="../statics/css/index.css">
</head>
<body>
<h2>${message}</h2>
<form action="${pageContext.request.contextPath}/param" role="form">
    username: <input type="text" name="username"> <br/>
    password: <input type="password" name="password"> <br/>
    <input type="submit" value="submit">
</form>
</body>
</html>
