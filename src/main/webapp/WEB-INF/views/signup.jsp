<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <sec:csrfMetaTags />
    <title>회원가입</title>

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <script src="/js/signup.js" charset="utf-8"></script>
    <form class="form-signin" accept-charset="utf-8" th:action="@{/signup}" method="post" id="signup" name="signup" onsubmit="return false;">
      <sec:csrfInput />
      <img class="mb-4" src="/assets/img/logo.svg" alt="" width="300" height="144">
      <h1 class="h3 mb-3 font-weight-normal">회원가입</h1>

      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputEmail" name="eID" class="form-control" placeholder="Email address" required autofocus>

      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required><br>

      <label for="inputName" class="sr-only">Name</label>
      <input type="name" id="inputName" name="name" class="form-control" placeholder="Name" required>

      <label for="inputNumber" class="sr-only">phonenumber</label>
      <input type="text" id="inputNumber" name="phonenumber" class="form-control" placeholder="PhoneNumber" required numberOnly><br>

      <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-bottom: 5px;" id="signup-button">Sign Up</button>
      <button type="button" class="btn btn-secondary btn-large form-control"
              id="btn_joinForm" onClick="location.href='/login'">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
    </form>
  </body>
</html>
