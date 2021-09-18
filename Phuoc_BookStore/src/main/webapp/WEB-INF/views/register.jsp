 <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700'
	rel='stylesheet' type='text/css'>
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
	href="${pageContext.request.contextPath}/css/responsive.css">

<script type="text/javascript">
	function validate() {
		var a = document.getElementById("photo");
		var b = document.getElementById("username");
		var e = document.getElementById("fullname ");
		var c = document.getElementById("email");
		var d = document.getElementById("password");
		var valid = true;
		if (a.value.length <= 0) {
			alert("Photo khong duoc de trong!");
			valid = false;
		} else if (b.value.length == 0) {
			alert("Username Khong duoc de trong!");
			valid = false;
		} else if (e.value.length == 0) {
			alert("fullname Khong duoc de trong!");
			valid = false;
		} else if (c.value.length == 0) {
			alert("Email khong duoc de trong!")
			valid = false;
		} else if (d.value.length == 0) {
			alert("Password khong duoc de trong!")
			valid = false;
		} else if (d.value.length == 0) {
			alert("Password khong duoc de trong!")
			valid = false;
		} else if (d.value.length == 0) {
			alert("Password khong duoc de trong!")
			valid = false;
		} else {
			alert("Them moi thanh cong !")
		}
		return valid;
	};
</script>
</head>
<body>

	<link
		href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
		rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Sofia'
		rel='stylesheet' type='text/css'>
	<div class='header'>
		<div class="header-main">
			<h1>Register</h1>

			<div class="header-bottom">
				<div class="header-right w3agile">
					<div class="header-left-bottom agileinfo">
						<form action="/admin/register/add" method="post"
							onsubmit="return validate();" enctype="multipart/form-data">
							<input style="width: 455px" name='username' value="Username"
								id="username" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Username';}"
								type='text'> <input style="width: 455px" name='photo'
								id="photo" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'photo';}"
								type='file'> <input style="width: 455px" name='fullname'
								value="Full Name" id="fullname" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Full Name';}"
								type='text'> <input style="width: 455px" name='email'
								value="Email" id="email" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Email';}"
								type='text'> <input style="width: 455px" type="password"
								id="password" name="password" placeholder="Password" />

							<div class="remember">
								<input id='agree' name='agree' type='checkbox'> <label
									for='agree'></label><a style="">Accept rules and conditions</a>
							</div>
							<input class='animated' type='submit' value='Register'> <a
								class='header-left-top' href='/admin/login'>Already have an
								account?</a>
					</div>

				</div>

			</div>


		</div>
	</div>
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