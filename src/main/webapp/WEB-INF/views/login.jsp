<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%--    <script>--%>
<%--      $("body > form > button.btn.btn-lg.btn-primary.btn-block").keyup(function(){--%>
<%--        var name = $("#inputEmail").val();--%>
<%--        var pass = $("#inputPassword").val();--%>
<%--        $.post("/login",{username:name,password:pass},"json");--%>
<%--      });--%>
<%--    </script>--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Signin</title>

    <!-- Bootstrap core CSS -->
<link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="../../css/signin.css" rel="stylesheet">
  </head>
  <body class="text-center">
    <form class="form-signin" action="/login" method="post">
        <sec:csrfInput />
  <img class="mb-4" src="../../bootstrap/brand/AWS-logo.svg" alt="" width="144" height="144">
  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
  <label for="inputEmail" class="sr-only">Email address</label>
  <input type="email" id="inputEmail" name="username" class="form-control" placeholder="Email address" required autofocus>
  <label for="inputPassword" class="sr-only">Password</label>
  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required><br>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button><br>
   <button type="button" class="btn btn-secondary btn-large form-control"
            id="btn_joinForm" onClick="location.href='/signup'">Sign Up</button>
  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
</form>
</body>
</html>
