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
<html lang="en" xmlns:th="http://www.thymeleaf.org"> 
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
							<li><a href="/admin/logout"><i class="fa fa-user"></i> Log out</a></li>
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
						<li ><a href="/admin/user">Qu???n L?? Ng?????i D??ng</a></li>
						<li><a href="/admin/product">Qu???n L?? S???n Ph???m</a></li>
						<li ><a href="/admin/categories">Qu???n L?? Danh M???c</a></li>
						<li class="active"><a href="/admin/orders/bills">Qu???n L?? ????n H??ng</a></li>
						<li ><a href="/admin/product/thongKe">Th???ng K??</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End mainmenu area -->

	<main class="container">
            <header> 
            <h1 align="center">List Of Orders</h1>
				<div class="row" >
					<div class="col" style="margin-top: 10px;" >
						<form action="" >
						
							<div class="form-inline float-left" >
							  <input style="width: 300px;" type="text" class="form-control" name="address" id="address" aria-describedby="helpId" placeholder="Nh???p t??n c???n t??m!">
							  <button class="btn btn-outline" style="background-color: cyan;">Search</button>					
							</div>	
							<div align="right" class="form-inline">
							  <form action="">
								<label for="sort">Sort By :</label>
								<select style="width: 200px" name="sort" id="sort" class="form-control" onchange="this.form.submit()">
									<option  value="createDate">Create Date</option>
									<option  value="total">Total</option>
									<option value="idOrder">Id Oder</option>
									<option selected="selected" value="">${ sort }</option>
								</select>
							</form>		
							</div>								
						</form>
					</div>
				</div>
            </header>
            <section class="row" >
                 <div  class="col offset-3 mt-4" >
                    <table  class="table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Userame</th>
                                <th>Address</th>
                                <th>CreateDate</th>
                                <th>Total</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                       
                        <tbody>
                         <c:forEach items="${ orderPage.content }" var="list">
                         <tr>
                                <td scope="row">${ list.idOrder }</td>
                                <td>${ list.acount.username }</td>
                                <td>${ list.address }</td>
                                <td>${ list.createDate }</td>
                                <td> ${ list.total } VN??</td>
                                <c:if test="${ list.status==0 }">
                                <td>??ang ch??? x??c nh???n</td>
                                </c:if>
                                <c:if test="${ list.status==1 }">
                                <td>???? x??c nh???n</td>
                                </c:if>
                                <c:if test="${ list.status==2 }">
                                <td>???? thanh to??n</td>
                                </c:if>
                                <td>
                                    <a href="/admin/orders/bills/billDetails/${ list.idOrder }" class="btn btn-outline-info"><i class="fas fa-info    "></i></a>
                                    <a href="/admin/orders/bills/edit/${ list.idOrder }" class="btn btn-outline-warning"><i class="fas fa-edit    "></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                            
                        </tbody>
                    </table>
                    						<div class="form-inline float-left">
							<form action="">
								<label for="size">Page Size</label>
								<select name="size" id="size" class="form-control" onchange="this.form.submit()">
									<option  value="5">5</option>
									<option  value="10">10</option>
									<option  value="15">15</option>
									<option  value="20">20</option>
									<option  value="25">25</option>
									<option selected>${orderPage.size}</option>
								</select>
							</form>
						</div>
                    <div align="center" class="card-footer text-muted">
                        <nav aria-label="Page navigation">
                          <ul class="pagination justify-content-center">
                            <li class="page-item">
                            
                              <a class="page-link "
								href="/admin/orders/bills?page=${ orderPage.number==orderPage.totalPages?orderPage.number-1 : 0 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
							</a>
							</li>
                            <li class="page-item active"><a class="page-link" href="/admin/orders/bills?page=${ orderPage.number-1 }">${orderPage.number}</a></li>
                            <li class="page-item"><a class="page-link" href="/admin/orders/bills?page=${ orderPage.number+1 }">${orderPage.number+1}</a></li>
                            <li class="page-item">
                              <a class="page-link" href="/admin/orders/bills?page=${ orderPage.number+1 }" aria-label="Next">
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

	<div class="footer-top-area">
		<div class="zigzag-bottom"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-3 col-sm-6">
					<div class="footer-about-us">
						<h2>
							<span> Book Shop</span>
						</h2>
						<p>H???c t???p kh??c bi???t M??i tr?????ng gi??u tr???i nghi???m ??a d???ng s???c
							m??u qu???c t??? Th??nh c??ng sau t???t nghi???p FPT Edu - Chuy???n ?????t &
							ng?????i FPT Edu - Tin t???c chung B??n chuy???n Gi??o d???c Tr???i nghi???m FPT
							Edu Tr?????ng h???c tr???i nghi???m FPT Edu - Nghi??n c???u khoa h???c</p>
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
						<h2 class="footer-wid-title">Tuy???n sinh</h2>
						<ul>
							<li><a href="#">?????i h???c FPT </a></li>
							<li><a href="#">?????i h???c Greenwich (Vi???t Nam)</a></li>
							<li><a href="#">?????i h???c C??ng ngh??? Swinburne (Vi???t Nam)</a></li>
							<li><a href="#">Cao ?????ng FPT Polytechnic</a></li>
							<li><a href="#">Vi???n ????o t???o Qu???c t??? FPT </a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="footer-menu">
						<h2 class="footer-wid-title">????o t???o</h2>
						<ul>
							<li><a href="#">?????i h???c FPT</a></li>
							<li><a href="#">?????i h???c Greenwich (Vi???t Nam)</a></li>
							<li><a href="#">Cao ?????ng FPT Polytechnic</a></li>
							<li><a href="#">FPT Aptech</a></li>
							<li><a href="#">Tr?????ng THPT FPT</a></li>
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
						<p>Copyright ?? 2017 FPT Education</p>
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