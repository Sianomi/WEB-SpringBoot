<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Jekyll v4.1.1">
  <title>Signin</title>

  <!-- Bootstrap core CSS -->
  <link href="/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" th:action="@{/signup}" method="post" accept-charset="utf-8">
  <sec:csrfInput />
  <img class="mb-4" src="/bootstrap/brand/AWS-logo.svg" alt="" width="144" height="144">
  <h1 class="h3 mb-3 font-weight-normal">Go sign Up</h1>

  <label for="inputEmail" class="sr-only">Email address</label>
  <input type="email" id="inputEmail" name="eID" class="form-control" placeholder="Email address" required autofocus>

  <label for="inputPassword" class="sr-only">Password</label>
  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required><br>

  <label for="inputName" class="sr-only">Name</label>
  <label for="inputName" class="sr-only">Name</label>
  <input type="name" id="inputName" name="name" class="form-control" placeholder="Name" required>

  <label for="inputNumber" class="sr-only">Name</label>
  <input type="text" id="inputNumber" name="phonenumber" class="form-control" placeholder="PhoneNumber" required><br>

  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button><br>
  <button type="button" class="btn btn-secondary btn-large form-control"
          id="btn_joinForm" onClick="location.href='/login'">Sign in</button>
  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
</form>
</body>
</html>
