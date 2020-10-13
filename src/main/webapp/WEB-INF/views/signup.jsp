<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="UTF-8">
    <title>sign up</title>
  </head>
  <body>
    <h1>Sign Up</h1> <hr>

    <form th:action="@{/signup}" method="post">
      email : <input type="text" name="eID"> <br>
      password : <input type="password" name="password"> <br>
      name : <input type="text" name="name"> <br>
      phonenumber : <input type="text" name="phonenumber"> <br>
      <button type="submit">Join</button>
    </form> <br>

    <a href="/login">Go to login</a>
  </body>
</html>