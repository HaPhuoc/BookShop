<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<li><a href="/login"><i class="fa fa-user"></i> Log out</a></li>
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

				<div class="col-sm-6">
					<div class="shopping-item">
						<a href="cart.html">Cart - <span class="cart-amunt">$100</span>
							<i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
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
						<li><a href="index">Quản Lí Người Dùng</a></li>
						<li><a href="shop">Quản Lí Sản Phẩm</a></li>
						<li  class="active"><a href="single-product">Quản Lí Danh Mục</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End mainmenu area -->

	<main class="container">
            <header class="row"> 
    
            </header>
            <section class="row" >
                <div  class="col offset-3 mt-4" >
                    <table  class="table">
                        <thead>
                            <tr>
                                <th>Category ID</th>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                       
                        <tbody>
                         <c:forEach items="${ page.content }" var="list">
                         <tr>
                                <td scope="row">${ list.idCategory }</td>
                                <td>${ list.name }</td>
                                <td>
                                    <a href="/admin/categories/edit/${list.idCategory}" class="btn btn-outline-info"><i class="fas fa-info    "></i></a>
                                    <a href="/admin/categories/edit/${list.idCategory}" class="btn btn-outline-warning"><i class="fas fa-edit    "></i></a>
                                    <a href="/admin/categories/delete/${list.idCategory}" class="btn btn-outline-danger"><i class="fas fa-recycle    "></i> </a>
                                </td>
                            </tr>
                        </c:forEach>
                            
                        </tbody>
                    </table>
                    <div align="center" class="card-footer text-muted">
                        <nav aria-label="Page navigation">
                          <ul class="pagination justify-content-center">
                            <li class="page-item disabled">
                              <a class="page-link" href="/admin/categories?page=${ page.number-1 }" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                              </a>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item">
                              <a class="page-link" href="/admin/categories?page=${ page.number+1 }" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                              </a>
                            </li>
                          </ul>
                        </nav>
                    </div>
                </div>
                
            </section>
            <footer class="row">
                
            </footer>
        </main>  

	<<div class="footer-top-area">
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