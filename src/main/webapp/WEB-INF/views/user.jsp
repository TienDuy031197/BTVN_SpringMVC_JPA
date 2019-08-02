<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>User</title>

<!-- Custom styles for this template -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link href="<c:url value='/assets/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/starter-template.css'/>"
	rel="stylesheet"></link>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="tile" style="text-align: center;">
					<h2>User</h2>
				</div>
				<form action="user" method="post">
					<select name="select" class="browser-default custom-select"
						style="width: 150px; float: left; margin-right: 30px;">
						<option selected>Lựa Chọn</option>
						<option value="1">Anh-Việt</option>
						<option value="2">Việt-Anh</option>
					</select> <input name="seach" type="text" class="form-control"
						style="width: 345px;" placeholder="Tìm kiếm" aria-label="Search" />
					<button hidden="hidden" type="submit">submit</button>
				</form>
				<div class="container">
					<div class="bao"
						style="border: 1px solid #c1c5ca; border-radius: 5px; margin-left: -15px; margin-top: 25px;">
						<div class="row">
							<div class="col-md-6">
								<p style="text-align: center;">Từ</p>
								<%
									String tienga = (String) request.getAttribute("tienga");
									String tiengv = (String) request.getAttribute("tiengv");
									String check = (String) request.getAttribute("stt");
									if (check != null)
										if (check.equals("ta")) {
											if (tienga != null) {
								%>
								<p style="text-align: center;"><%=request.getAttribute("tienga")%></p>
								<%
									}
										} else {
											if (tiengv != null) {
								%>
								<p style="text-align: center;"><%=request.getAttribute("tiengv")%></p>
								<%
									}
										}
								%>

							</div>
							<div class="col-md-6">
								<p style="text-align: center;">Nghĩa</p>
								<%
									if (check != null)
										if (check.equals("ta")) {
											if (tienga != null) {
								%>
								<p style="text-align: center;"><%=request.getAttribute("tiengv")%></p>
								<%
									}
										} else {
											if (tiengv != null) {
								%>
								<p style="text-align: center;"><%=request.getAttribute("tienga")%></p>
								<%
									}
										}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<a href="http://localhost:8080/springmvc/logout">
					<button class="btn btn-primary btn-block"
						style="margin-top: 47px; width: 80px;">Logout</button>
				</a>
			</div>
		</div>
	</div>
</body>
</html>