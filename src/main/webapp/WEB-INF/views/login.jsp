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


<title>Welcome To sprring MVC</title>



<!-- Custom styles for this template -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="<c:url value='/assets/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/assets/css/starter-template.css'/>"
	rel="stylesheet"></link>

</head>


<body>
	<div class="container">
		<h1 class="text-center">Đăng nhập</h1>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-3 col-md-offset-4">
				<form action="login" method="post">
					<div class="login-form">
						<div class="form-group">
							<input class="form-control" name="name" value="" placeholder="Name"
								id="login-name" type="text" style="width: 325px">
						</div>
						<div class="form-group">
							<input class="form-control" name="password" value="" placeholder="Password"
								id="login-pass" type="password" style="width: 325px">
						</div>
						<div class="checkbox">
							<label><input type="checkbox"> Giữ tôi luôn đăng
								nhập </label>
						</div>
						<br> <input class="form-control" name="submit" value="Đăng nhập"
							id="login-submit" type="submit" style="width: 105px;">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>