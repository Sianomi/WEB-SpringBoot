<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
  <head>
    <meta charset="UTF-8">
    <title>admin</title>
  </head>
  <body>
    <h2>관리자 전용 페이지</h2>
    ID : <li sec:authorize="isAuthenticated()"><span sec:authentication="principal.username"></span></li> <br>
       소유 권한 : <span sec:authentication="authorities"></span> <br>

    <form id="logout" action="/logout" method="POST">
      <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
      <input type="submit" value="로그아웃"/>
    </form>
  </body>
</html>