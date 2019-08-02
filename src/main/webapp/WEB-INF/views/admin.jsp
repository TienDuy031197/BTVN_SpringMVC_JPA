<%@page import="java.util.List"%>
<%@page import="com.onrugi.entity.Dictionary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Admin</title>

<!-- Custom styles for this template -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link href="<c:url value='/assets/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/starter-template.css'/>"
	rel="stylesheet"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="tile" style="text-align: center;">
					<h2>Admin</h2>
				</div>
				<a href="http://localhost:8080/springmvc/add"
					class="btn btn-primary" style="float: left; margin-right: 5px;">Add</a>
				<form action="listword" method="get">
					<select name="selected" class="browser-default custom-select"
						style="width: 150px; float: left; margin-right: 30px;">
						<option selected>Lựa Chọn</option>
						<option value="1">Anh-Việt</option>
						<option value="2">Việt-Anh</option>
					</select>
					<!-- Search form -->
					<input name="search" class="form-control" type="text"
						placeholder="Tìm kiếm" aria-label="Search"
						style="width: 299px; display: initial;">
					<button hidden="hidden" type="submit">submit</button>
				</form>
				<div class="container"
					style="border: 1px solid #ced4da; margin-top: 10px;">
					<div class="row">
						<div class="col-md-4">
							<p>STT</p>
						</div>
						<div class="col-md-4">
							<p>Từ</p>
						</div>
						<div class="col-md-4">
							<p>Thao tác</p>
						</div>
					</div>
					<%
						List<Dictionary> lis = (List<Dictionary>) request.getAttribute("listDic");
						String check = (String) request.getAttribute("check");
						String select = (String) request.getAttribute("select");
						String search = (String) request.getAttribute("search");

						int count = 1;
						if (lis != null)
							for (Dictionary dic : lis) {
					%>
					<div class="row">
						<div class="col-md-4">
							<p><%=count%></p>
						</div>
						<div class="col-md-4">
							<p>
								<%
									if (check.equals("1")) {
								%><%=dic.getTienganh()%>
								<%
									} else {
								%><%=dic.getTiengviet()%>
								<%
									}
								%>
							</p>
						</div>
						<div class="col-md-4">
							<form action="delete" method="get">
								<input hidden="hidden" name="selected" value="<%=select%>" /> <input
									hidden="hidden" name="search" value="<%=search%>" /> <input
									hidden="hidden" name="id_word" value="<%=dic.getId()%>" />

								<button class="btn btn-primary btn-block" name="delete"
									style="width: 51px; float: left; margin-right: 10px;">Del</button>
							</form>

							<input hidden="hidden" name="selected" value="<%=select%>" /> <input
								hidden="hidden" name="search" value="<%=search%>" /> <input
								hidden="hidden" name="id_word" value="<%=dic.getId()%>" />
							<button class="btn btn-primary btn-block" data-toggle="modal"
								data-target="#myModal" <%if (check.equals("1")) {%>
								onclick="printwork('<%=dic.getTienganh()%>','<%=dic.getTiengviet()%>')"
								<%} else {%>
								onclick="printwork('<%=dic.getTiengviet()%>','<%=dic.getTienganh()%>')"
								<%}%>
								style="width: 54px; margin-top: 1px; display: -webkit-inline-box;">Edit</button>
						</div>

						<script>
							function printwork(one, second) {
								document.getElementById("one").value = one;
								document.getElementById("second").value = second;
							}
							function submit_edit(id, selected, search) {
								var tieng = document.getElementById("second").value;
								window.location = "http://localhost:8080/springmvc/edit?id="
										+ id
										+ "&selected="
										+ selected
										+ "&search="
										+ search
										+ "&tieng="
										+ tieng;
							}
						</script>
						<div class="container">
							<!-- The Modal -->
							<div class="modal" id="myModal">
								<div class="modal-dialog">
									<div class="modal-content">

										<!-- Modal Header -->
										<div class="modal-header">
											<h4 class="modal-title" style="margin-left: 185px;">Cập
												Nhật</h4>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<!-- Modal body -->
										<div class="modal-body">
											<input name="search" class="form-control" type="text"
												id="one" disabled="disabled" placeholder=""
												aria-label="Search" style="width: 299px; display: initial;">
											<label
												style="margin-left: 90px; font-weight: 700; font-size: 20px;">Từ</label>
										</div>


										<!-- Modal body -->
										<div class="modal-body">
											<input name="search" class="form-control" type="text"
												id="second" placeholder="" aria-label="Search"
												style="width: 299px; display: initial;"> <label
												style="margin-left: 90px; font-weight: 700; font-size: 20px;">Nghĩa</label>
										</div>

										<!-- Modal footer -->
										<div class="modal-footer">
											<button
												onclick="submit_edit('<%=dic.getId()%>','<%=select%>','<%=search%>')"
												type="button" class="btn btn-danger" name="edit"
												data-dismiss="modal">Đồng ý</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%
						count++;
							}
					%>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4"></div>
						<div class="col-md-4"></div>
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