<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.example.www.MemoBean" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
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

 <%
     ArrayList<MemoBean> list = (ArrayList<MemoBean>) request
             .getAttribute("memo_list");

     Iterator<MemoBean> i = list.iterator();
     while (i.hasNext()) {
         MemoBean memo = i.next();
         out.println("<hr/>");
         out.println("<div>" + memo.getTitle() + "</div>");
         out.println("<div>" + memo.getModify() + "</div>");
         out.println("<div>" + memo.getMemo().replace("\n", "<br/>") + "</div>");
     }
 %>

</body>
</html>