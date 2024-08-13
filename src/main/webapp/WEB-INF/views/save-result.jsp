<%--
  Created by IntelliJ IDEA.
  User: kimjinbeom
  Date: 8/13/24
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
</head>
<body>
성공
<ul>
<%--  request.getAttribute("member") 대신 JSP 문법(${}) 으로 request의 attribute에 담긴 데이터를 편리하게 조회--%>
  <li>id=${member.id}</li>
  <li>username=${member.username}</li>
  <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>