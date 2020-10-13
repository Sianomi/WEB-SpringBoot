<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8">
    <title>login</title>
  </head>
  <body>
    <h1>Login</h1> <hr>

    <form action="/login" method="POST">
      <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
      email : <input type="text" name="username"> <br>
      password : <input type="password" name="password"> <br>
      <button type="submit">Login</button>
    </form> <br>

    <div th:if="${session['SPRING_SECURITY_LAST_EXCEPTION']} != null">
      <span th:text="${session['SPRING_SECURITY_LAST_EXCEPTION'].message}"></span>
    </div>

    <a href="/signup">Go to join!</a>
  </body>
</html>