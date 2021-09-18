
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    
    <style type="text/css">
    
    label {
    display: block;
    font: 1rem 'Fira Sans', sans-serif;
}

input,
label {
    margin: .4rem 0;
}
    </style>
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
						<li ><a href="/admin/user">Quản Lí Người Dùng</a></li>
						<li class="active"><a href="/admin/product">Quản Lí Sản Phẩm</a></li>
						<li ><a href="/admin/categories">Quản Lí Danh Mục</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End mainmenu area -->

	<main class="container">
	 <section class="row">
            <div class="col offset-3 mt-4" style="margin-left: 355px">
              <form:form action="/admin/product/add/save" method="post" modelAttribute="products" enctype="multipart/form-data">
          		 <c:if test="${ product.isEdit }">
					Product ID:<br>
						<form:input style="width:255px" value="${ layId }" readonly="true" path="idProduct"/>
						<c:forEach items="${ list }" var="list">
					
					Name <br><form:input style="width:255px" value="${ list.name }" path="name"/><br>
              		<form:errors path="name"></form:errors><br>
              		Image <br> <img alt="" width="20%" src="${pageContext.request.contextPath}/images/${ list.image }"> <input type="file" value="${ list.image }" name="image-file" id="image"><br>
              		Price <br> <form:input style="width:255px" min="1" value="${ list.price }" type="number" path="price"/><br>
              		<form:errors path="price"><span> Tối thiểu phải là 1 !</span> </form:errors><br>
              		Quantity <br> <form:input type="number" style="width:255px" min="1" value="${ list.quantity }" path="quantity"/><br>
              		<form:errors path="quantity"><span>Tối thiểu phải là 1 !</span> </form:errors><br>
              		CategoryID <br><form:select style="width:255px" path="category"  >
              		<form:option value="${ list.category.idCategory }" label="${ list.category.name }"></form:option>
              		<form:options items="${ listCategory }" /> 
              		</form:select> <br>
			 		Descreption <br> <form:textarea style="width:255px"  value="${ list.descreption }" path="descreption"/> <br> 
              		<div class="card-footer text-muted" style="margin-bottom: 30px"><br></div>
					
					</c:forEach>
					</c:if> <br>
					
              		<c:if test="${ !product.isEdit }">
					Name <br><form:input style="width:255px" path="name"/><br>
              		<form:errors path="name"></form:errors><br>
              		Image <br> <img alt="" width="20%" src="${pageContext.request.contextPath}/img/thanhshop1.png"> <input type="file" value="${ list.image }" name="image-file" id="image"><br>
              		Price <br> <form:input style="width:255px" value="${ list.price }" min="1" type="number" path="price"/><br>
              		<form:errors path="price"><span> Tối thiểu phải là 1 !</span> </form:errors><br>
              		Quantity <br> <form:input type="number" style="width:255px" value="${ list.quantity }" min="1" path="quantity"/><br>
              		<form:errors path="quantity"><span>Tối thiểu phải là 1 !</span> </form:errors><br>
              		CategoryID <br><form:select style="width:255px" path="category" > 	
                    <form:option value="" label="Select"></form:option>
              		<form:options items="${ listCategory }" /> 
              		</form:select> <br>
					Descreption <br> <form:textarea style="width:255px" path="descreption"/> <br> 
              		<div class="card-footer text-muted" style="margin-bottom: 30px"><br></div>		
					</c:if>
                    <c:if test="${ layId!=null }"> <a class="btn btn-secondary" href="/admin/product/add" style="border: 1px solid black;color: black">Reset</a> </c:if>
                    <button class="btn btn-success">
                      <c:if test="${ product.isEdit }">
								<span>Update</span>
							</c:if>
							<c:if test="${ !product.isEdit }">
								<span>Save</span>
							</c:if>
                    </button>
                    <div class="btn btn-danger">
                      List Product
                    </div>
                      </form:form>
                </div>
              
            
                

              
             
            </div>
            
        </section>

            
        </main>  

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