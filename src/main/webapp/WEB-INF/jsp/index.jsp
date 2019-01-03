<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.example.www.MemoBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memo App</title>
</head>
<body>

 <h1>メモアプリ</h1>

 <form method="post">
  <input type="text" name="title" size="50" /> <br />
  <textarea rows="5" cols="80" name="memo"></textarea>
  <br /> <input type="submit" />
 </form>

  <c:forEach var="data" items="${memo_list}">
    <hr/>
    <div><c:out value="${data.getTitle()}"/></div>
    <div><c:out value="${data.getModify()}"/></div>
    <div><c:out value="${data.getMemo()}"/></div>
  </c:forEach>

</body>
</html>