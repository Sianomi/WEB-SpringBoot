<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>롤 프로게이머</title>
</head>

<body>
    <table border="1">
        <thead>
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>닉네임</th>
                <th>포지션</th>
            </tr>
        </thead>
        <tbody>
                <tr>
                    <th>${user.eID}</th>
                    <th>${user.password}</th>
                    <th>${user.name}</th>
                    <th>${user.phonenumber}</th>
                    <th>${user.isAdmin}</th>
                </tr>
        </tbody>
    </table>
</body>

</html>