 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <%@ page
language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<!--
	ustora by freshdesignweb.com
	Twitter: https://twitter.com/freshdesignweb
	URL: https://www.freshdesignweb.com/ustora/
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ustora Demo</title>

<!-- Google Fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100'
	rel='stylesheet' type='text/css'>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css">

<!-- Custom CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.carousel.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/responsive.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.1/css/all.css"
	integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ"
	crossorigin="anonymous">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="header-area">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<div class="user-menu">
						<ul>
							<li><a href="/admin/logout"><i class="fa fa-user"></i>
									Log out</a></li>
						</ul>
					</div>
				</div>


			</div>
		</div>
	</div>
	<!-- End header area -->

	<div class="site-branding-area">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="logo">

						<h1>
							<a href="/index"><img style="height: 100px"
								src="${pageContext.request.contextPath}/img/LogoFPT.jpg"></a>
						</h1>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- End site branding area -->

	<div class="mainmenu-area">
		<div class="container">
			<div class="row">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/admin/user">Quản Lí Người
								Dùng</a></li>
						<li><a href="/admin/product">Quản Lí Sản Phẩm</a></li>
						<li><a href="/admin/categories">Quản Lí Danh Mục</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End mainmenu area -->


	<section class="row">


		<div class="header-bottom">
			<div class="header-right w3agile">
				<div style="margin-left: 500px" class="header-left-bottom agileinfo">
					<form:form method="Post" action="/admin/user/add1"
						modelAttribute="acount" enctype="multipart/form-data">
						<c:if test="${ acount.isEdit }">
							<h4>${ validation }</h4>
							<c:forEach items="${ list }" var="list">

								<a style="color: black; font-size: 15px">Username</a>
								<br>
								<form:input style="width:455px" value="${ list.username }"
									readonly="true" path="username" />
								<form:errors path="username">
									<span style="color: black">*Vui lòng nhập user name !</span>
								</form:errors>
								<br>
								<a style="color: black; font-size: 15px">Email Adress</a>
								<br>
								<form:input value="${ list.email }" style="width: 455px"
									path="fullname" />						
								<form:errors path="email" style="color:black"></form:errors>
								<br>
								<a style="color: black; font-size: 15px">Full Name</a>
								<br>
								<form:input value="${ list.fullname }" style="width: 455px"
									path="email" />
								<br>
								<form:errors path="fullname">
									<span style="color: black;">*Vui lòng nhập họ tên đầy đủ
										của bạn !</span>
								</form:errors>
								<a style="color: black; font-size: 15px">Password</a>
								<br>
								<form:password value="${ list.password }" style="width: 455px"
									path="password" />
								<br>
								<form:errors path="password"></form:errors>
								<br>
								Role:<br>
								<form:select path="admin">

									<form:option value="0">User</form:option>
									<form:option value="1">Admin</form:option>
									<form:option value="${ list.admin }">${ list.admin==0?"User":"Admin" }</form:option>
								</form:select>
								<br>
								<a style="color: black; font-size: 15px">Photo</a>
								<br>
								<img alt="" width="30%"
									src="${pageContext.request.contextPath}/images/${ list.photo }">
								<br>
								<input style="width: 455px" name="image-file" id="photo"
									onfocus="this.value = '';"
									onblur="if (this.value == '') {this.value = 'photo';}"
									type='file'>
								<form:errors path="photo">
									<span>Khong Duoc De Trong</span>
								</form:errors>
								Địa chỉ:<br>
								<form:input style="width: 455px;height:50px"
									value="${ list.address }" type="textarea" path="address" />

								<div class="remember"></div>

							</c:forEach>
						</c:if>
						<c:if test="${ !acount.isEdit }">
							<h4>${ validation }</h4>
							<a style="color: black; font-size: 15px">User Name</a>
							<br>
							<form:input style="width: 455px" name="username" path="username" />
							<br>
							<form:errors path="username">
								<span style="color: black">*Vui lòng nhập user name !</span>
								<span style="color: black">${ tinNhan }</span>
							</form:errors>
							<br>
							<a style="color: black; font-size: 15px">Full Name</a>
							<br>
							<form:input style="width: 455px" path="email" />
							<br>
							<form:errors path="fullname">
								<span style="color: black;">*Vui lòng nhập họ tên đầy đủ
									của bạn !</span>
							</form:errors>
							<br>
							<a style="color: black; font-size: 15px">Email Adress</a>
							<br>
							<form:input style="width: 455px" path="fullname" />
							<br>
							<form:errors path="email" style="color:black"></form:errors>
							<br>
							<a style="color: black; font-size: 15px">Password</a>
							<br>
							<form:password style="width: 455px" path="password" />
							<br>
							<form:errors path="password"></form:errors>
							<br>
							<form:select path="admin">
								<form:option value="0">User</form:option>
								<form:option value="1">Admin</form:option>
							</form:select>
							<a style="color: black; font-size: 15px">Photo</a>
							<br>
							<img alt="" width="30%"
								src="${pageContext.request.contextPath}/images/${ list.photo }">
							<br>
							<input style="width: 455px" name="image-file" id="photo"
								onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'photo';}"
								type='file'>
							<form:errors path="photo">
								<span>Khong Duoc De Trong</span>
							</form:errors>
							Địa chỉ:<br>
							<form:input style="width: 455px;height:50px" type="textarea"
								path="address" />
							<div class="remember"></div>
							<br>
						</c:if>
						<c:if test="${ layId!=null }">
							<a class="btn btn-secondary" href="/admin/user/add"
								style="border: 1px solid black; color: black">Reset</a>
						</c:if>
						<c:if test="${ !acount.isEdit }">
							<input class='animated' type='submit' value='Save'>
							<br>
						</c:if>
						<c:if test="${ acount.isEdit }">
							<input class='animated' type='submit' value='Update'>
							<br>
						</c:if>
						<br>
					</form:form>
				</div>

			</div>

		</div>
	</section>








	<div class="footer-top-area">
		<div class="zigzag-bottom"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-3 col-sm-6">
					<div class="footer-about-us">
						<h2>
							<span> Book Shop</span>
						</h2>
						<p>Học tập khác biệt Môi trường giàu trải nghiệm Đa dạng sắc
							màu quốc tế Thành công sau tốt nghiệp FPT Edu - Chuyện đất &
							người FPT Edu - Tin tức chung Bàn chuyện Giáo dục Trải nghiệm FPT
							Edu Trường học trải nghiệm FPT Edu - Nghiên cứu khoa học</p>
						<div class="footer-social">
							<a href="#" target="_blank"><i class="fa fa-facebook"></i></a> <a
								href="#" target="_blank"><i class="fa fa-twitter"></i></a> <a
								href="#" target="_blank"><i class="fa fa-youtube"></i></a> <a
								href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="footer-menu">
						<h2 class="footer-wid-title">Tuyển sinh</h2>
						<ul>
							<li><a href="#">Đại học FPT </a></li>
							<li><a href="#">Đại học Greenwich (Việt Nam)</a></li>
							<li><a href="#">Đại học Công nghệ Swinburne (Việt Nam)</a></li>
							<li><a href="#">Cao đẳng FPT Polytechnic</a></li>
							<li><a href="#">Viện Đào tạo Quốc tế FPT </a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="footer-menu">
						<h2 class="footer-wid-title">Đào tạo</h2>
						<ul>
							<li><a href="#">Đại học FPT</a></li>
							<li><a href="#">Đại học Greenwich (Việt Nam)</a></li>
							<li><a href="#">Cao đẳng FPT Polytechnic</a></li>
							<li><a href="#">FPT Aptech</a></li>
							<li><a href="#">Trường THPT FPT</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="footer-newsletter">
						<h2 class="footer-wid-title">FPT Education</h2>
						<p>Hoa Lac High Tech Park, Hanoi, Vietnam Email:
							fpteducation@fe.edu.vn</p>
						<div class="newsletter-form">
							<form action="#">
								<input type="email" placeholder="Type your email"> <input
									type="submit" value="Subscribe">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="footer-bottom-area">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<div class="copyright">
						<p>Copyright © 2017 FPT Education</p>
					</div>
				</div>

				<div class="col-md-4">
					<div class="footer-card-icon">
						<i class="fa fa-cc-discover"></i> <i class="fa fa-cc-mastercard"></i>
						<i class="fa fa-cc-paypal"></i> <i class="fa fa-cc-visa"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End footer bottom area -->

	<!-- Latest jQuery form server -->
	<script src="https://code.jquery.com/jquery.min.js"></script>

	<!-- Bootstrap JS form CDN -->
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

	<!-- jQuery sticky menu -->
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.sticky.js"></script>

	<!-- jQuery easing -->
	<script
		src="${pageContext.request.contextPath}/js/jquery.easing.1.3.min.js"></script>

	<!-- Main Script -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>

	<!-- Slider -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bxslider.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/script.slider.js"></script>
</body>
</html>