<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@taglib
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
					<c:if test="${ present==null }">
						<div class="user-menu">
							<ul>
								<li><a href="/admin/login"><i class="fa fa-user"></i>
										My Account</a></li>
								<li><a href="/shopping-cart/views"><i
										class="fa fa-user"></i> My Cart</a></li>
								<li><a href="/admin/login"><i class="fa fa-user"></i>
										Login</a></li>
							</ul>
						</div>
					</c:if>
					<c:if test="${ present!=null }">
						<div class="user-menu">
							<ul>
								<li><a href="#"><i class="fa fa-user"></i> ${ user }</a></li>
								<li><a href="/shopping-cart/views"><i
										class="fa fa-user"></i> My Cart</a></li>
								<li><a href="/admin/logout"><i class="fa fa-user"></i>
										Logout</a></li>
							</ul>
						</div>
					</c:if>

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

				<div class="col-sm-6">
					<div class="shopping-item">
						<a href="/shopping-cart/views">Cart - <span class="cart-amunt">$
								${ amount }</span> <i class="fa fa-shopping-cart"></i> <span
							class="product-count">${ soluong }</span></a>
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
						<li class="active"><a href="/index">Home</a></li>
						<li><a href="/shop">Shop page</a></li>
						<li><a href="/shopping-cart/views">Cart</a></li>
						<li><a href="/orders">Order</a></li>

					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End mainmenu area -->

	<div class="slider-area">
		<!-- Slider -->

		<div class="block-slider block-slider4">
			<ul class="" id="bxslider-home4">
				<c:forEach items="${ listSP }" var="sp">
					<li><img style="width: 20%; margin-left: 300px"
						src="${pageContext.request.contextPath}/images/${sp.image}"
						alt="Slide">
						<div class="caption-group">
							<h2 class="caption title">
								<span class="primary"><strong>${ sp.name }</strong></span>
							</h2>
							<h4 class="caption subtitle"> ${ sp.price } VNĐ</h4>
							<a class="caption button-radius"
								href="/shopping-cart/single-product/${ sp.idProduct }"><span
								class="icon"></span>Shop now</a>
						</div></li>
				</c:forEach>
			</ul>

		</div>
		<!-- ./Slider -->
	</div>
	<!-- End slider area -->



	<div class="maincontent-area">
		<div class="zigzag-bottom"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="latest-product">
						<div class="product-carousel">
							<c:forEach items="${ listSP }" var="sp">
								<div class="single-product">
									<div class="product-f-image">
										<img src="images/${ sp.image }" alt="" class="img-fluid"
											style="height: 250px">
										<div class="product-hover">
											<a href="/shopping-cart/add/${sp.idProduct	}"
												class="add-to-cart-link"><i class="fa fa-shopping-cart"></i>
												Add to cart</a> <a
												href="/shopping-cart/single-product/${ sp.idProduct }"
												class="view-details-link"><i class="fa fa-link"></i> See
												details</a>
										</div>
									</div>

									<h2>
										<a href="/shopping-cart/single-product/${ sp.idProduct }">${
											sp.name }</a>
									</h2>

									<div class="product-carousel-price">
										<ins> ${ sp.price }VNĐ</ins>
									</div>
								</div>

							</c:forEach>


						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End main content area -->

	<div class="brands-area">
		<div class="zigzag-bottom"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="brand-wrapper">
						<div class="brand-list">
							<img src="${pageContext.request.contextPath}/img/brand1.png"
								alt=""> <img
								src="${pageContext.request.contextPath}/img/brand2.png" alt="">
							<img src="${pageContext.request.contextPath}/img/brand3.png"
								alt=""> <img
								src="${pageContext.request.contextPath}/img/brand4.png" alt="">
							<img src="${pageContext.request.contextPath}/img/brand5.png"
								alt=""> <img
								src="${pageContext.request.contextPath}/img/brand6.png" alt="">
							<img src="${pageContext.request.contextPath}/img/brand1.png"
								alt=""> <img
								src="${pageContext.request.contextPath}/img/brand2.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End brands area -->

	<div class="product-widget-area">
		<div class="zigzag-bottom"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="single-product-widget">
						<h2 class="product-wid-title">Top Sellers</h2>
						<a href="" class="wid-view-more">View All</a>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/onepiece1.jpg"
								alt="" class="product-thumb"></a>
							<h2>
								<a href="single-product.html">One Piece</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/onepiece1.jpg"
								alt="" class="product-thumb"></a>
							<h2>
								<a href="single-product.html">One Piece</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/onepiece.jpg" alt=""
								class="product-thumb"></a>
							<h2>
								<a href="single-product.html">One Piece</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="single-product-widget">
						<h2 class="product-wid-title">Recently Viewed</h2>
						<a href="#" class="wid-view-more">View All</a>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/naruto.jpg" alt=""
								class="product-thumb"></a>
							<h2>
								<a href="single-product.html">Naruto</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/naruto1.jpg" alt=""
								class="product-thumb"></a>
							<h2>
								<a href="single-product.html">Naruto</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/naruto2.jpg" alt=""
								class="product-thumb"></a>
							<h2>
								<a href="single-product.html">Naruto</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="single-product-widget">
						<h2 class="product-wid-title">Top New</h2>
						<a href="#" class="wid-view-more">View All</a>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/doraemon.jpg" alt=""
								class="product-thumb"></a>
							<h2>
								<a href="single-product.html">Doraemon</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/doraemon1.jpg"
								alt="" class="product-thumb"></a>
							<h2>
								<a href="single-product.html">Doraemon</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
						<div class="single-wid-product">
							<a href="single-product.html"><img
								src="${pageContext.request.contextPath}/img/doraemon2.jpg"
								alt="" class="product-thumb"></a>
							<h2>
								<a href="single-product.html">Doraemon</a>
							</h2>
							<div class="product-wid-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-wid-price">
								<ins>18.000VNĐ</ins>
								<del>$16.000VNĐ</del>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End product widget area -->

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