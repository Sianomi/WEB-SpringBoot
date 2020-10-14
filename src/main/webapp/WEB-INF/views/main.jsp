<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:th="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8">
    <title>main</title>
  </head>
  <body>
    <h2>회원 전용 페이지</h2>
    ID : ${name} <br>
       소유 권한 : ${auth}</span> <br>

    <form id="logout" action="/logout" method="POST">
      <sec:csrfInput />
      <input type="submit" value="로그아웃"/>
    </form>

  </body>
</html>