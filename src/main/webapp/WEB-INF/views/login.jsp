<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <sec:csrfMetaTags />
    <title>로그인</title>


    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/signin.css" rel="stylesheet">
  </head>
  <body class="text-center">
  <form class="form-signin" action="/login" method="post" id="signin" name="signin">
    <sec:csrfInput />
    <img class="mb-4" src="/assets/img/logo.svg" alt="" width="300" height="144">
    <h1 class="h3 mb-3 font-weight-normal">로그인</h1>

    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" name="username" class="form-control" placeholder="Email address" required autofocus>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
    <c:if test="${not empty loginFailMsg}">
      <span style="color: #ff0000; ">
        <p>${loginFailMsg}</p>
      </span>
    </c:if>
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-bottom: 5px;" id="signinbutton">Sign in</button>

    <button type="button" class="btn btn-secondary btn-large form-control"
            id="btn_joinForm" onClick="location.href='/signup'">Sign Up</button>

    <p class="mt-5 mb-3 text-muted">&copy; 2020 Project: Board Recognition</p>
  </form>
  </body>
</html>
