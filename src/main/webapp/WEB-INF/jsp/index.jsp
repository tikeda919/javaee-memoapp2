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

 <h1>メモアプリ12</h1>

 <form method="post">
  <input type="text" name="title" size="50" /> <br />
  <textarea rows="5" cols="80" name="memo"></textarea>
  <br /> <input type="submit" />
 </form>

<% pageContext.setAttribute("newLineChar", "\n"); %>
  <c:forEach var="data" items="${memo_list}">
    <hr/>
    <div><c:out value="${data.title}"/></div>
    <div><c:out value="${data.modify}"/></div>
    <div>
    <c:forEach var="line" items="${fn:split(data.memo, newLineChar)}">
    <c:out value="${line}"/><br/>
    </c:forEach>
    </div>
  </c:forEach>

</body>
</html>