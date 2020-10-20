<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html> 
<head> 
    <meta charset="UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>Home</title>
    <sec:csrfMetaTags />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Dosis:300,400,500,,600,700,700i|Lato:300,300i,400,400i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
    <link href="/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="/assets/vendor/venobox/venobox.css" rel="stylesheet">
    <link href="/assets/vendor/line-awesome/css/line-awesome.min.css" rel="stylesheet">
    <link href="/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
    <header id="header" class="fixed-top">
        <div class="container d-flex align-items-center">

            <a href="/" class="logo mr-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>
            <nav class="nav-menu d-none d-lg-block">
                <ul>
                    <li class="active"><a href="/">Home</a></li>
                    <li class=""><a href="/infer">Inference</a></li>
                    <li class=""><a href="/log">Log</a></li>
                    <li class=""><a href="/logout">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <section id="hero" class="d-flex align-items-center">

        <div class="container">
            <div class="row">
                <div class="col-lg-6 order-1 order-lg-1 hero-img">
                    <img src="assets/img/hero-img.png" class="img-fluid" alt="">
                </div>
                <div class="col-lg-6 pt-4 pt-lg-0 order-2 order-lg-2 d-flex flex-column justify-content-center">
                    <h1>Simple AWS</h1>
                    <h1> Object-Detection</h1>
                    <h2>${name}님, 안녕하십니까. 오늘도 좋은 하루 되세요!</h2>
                    <div><a href="/logout" class="btn-get-started scrollto">logout</a></div>
                </div>
            </div>
        </div>
    </section>
    <main>
        <section id="about" class="about">
            <div class="container">

                <div class="row">
                    <div class="col-xl-5 col-lg-6 d-flex justify-content-center video-box align-items-stretch">
                        <a href="https://www.youtube.com/watch?v=zIkBYwdkuTk" class="venobox play-btn mb-4 vbox-item" data-vbtype="video" data-autoplay="true"></a>
                    </div>

                    <div class="col-xl-7 col-lg-6 icon-boxes d-flex flex-column align-items-stretch justify-content-center py-5 px-lg-5">
                        <h3>AWS 클라우드 기반의 Object-Detection AI 서비스</h3>
                        <p>Object-Detection: 객체 탐지는 이미지 또는 비디오에서 객체의 클래스와 위치를 찾는 컴퓨터 비전 기술과 이미지 처리에 관련된 컴퓨터 기술</p>

                        <div class="icon-box">
                            <div class="icon"><i class="bx bx-fingerprint"></i></div>
                            <h4 class="title"><a href="">Object-Detection</a></h4>
                            <p class="description">여러가지 물체에 대한 Classification + <br>물체의 위치 정보를 파악하는 Localization</p>
                        </div>

                        <div class="icon-box">
                            <div class="icon"><i class="bx bx-gift"></i></div>
                            <h4 class="title"><a href="">Amazon Web Services(AWS)</a></h4>
                            <p class="description">아마존 닷컴에서 제공하는 클라우드 서비스.<br>최근엔 Deep Learning, AR, VR, 로보틱스 등의 분야로 진출하고 있다.</p>
                        </div>

                        <div class="icon-box">
                            <div class="icon"><i class="bx bx-atom"></i></div>
                            <h4 class="title"><a href="">AWS SageMaker</a></h4>
                            <p class="description">Deep Learning 환경구축부터 학습, 배포까지<br>원스톱으로 처리가능한 AWS 서비스. Python Jupyter를 기반으로 하고 있다.</p>
                        </div>

                    </div>
                </div>

            </div>
        </section>
    </main>
    <!-- Vendor JS Files -->
    <script src="/assets/vendor/jquery/jquery.min.js"></script>
    <script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
    <script src="/assets/vendor/php-email-form/validate.js"></script>
    <script src="/assets/vendor/venobox/venobox.min.js"></script>
    <script src="/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
    <script src="/assets/vendor/counterup/counterup.min.js"></script>
    <script src="/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
    <script src="/assets/js/main.js"></script>
</body> 
</html>
