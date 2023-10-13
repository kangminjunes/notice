<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>
    <table border="1">
        <thead>
            <tr>
                <td>구분</td>
                <td>제목</td>
                <td>내용</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${contactList}" var="c">
                <tr>
                    <td><c:out value="${c.notice_no}" /></td>
                    <td><a href="${contextPath}/notice/detail.do?notice_no=${c.notice_no}"><c:out value="${c.title}" /></a></td>
                    <td><a href="${c.content}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
      
      <hr>
      
      <div>
        <button type="submit">편집</button>
        <button type="delete">삭제</button>
        <button type="button" onclick="window.location.href='${contextPath}/notice/list.do'">목록</button>
      </div>
    </form>
  </div>

</body>
</html>