<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>Inference</title>
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
    <link href="/assets/css/style.css" rel="stylesheet">
    <link href="/css/inference.css" rel="stylesheet">
</head>
<body>
<script src="/js/inference.js"></script>
<body id="inference_body">
<header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

        <a href="/" class="logo mr-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>
        <nav class="nav-menu d-none d-lg-block">
            <ul>
                <li class=""><a href="/">Home</a></li>
                <li class="active"><a href="/infer">Inference</a></li>
                <li class=""><a href="/log">Log</a></li>
                <li class=""><a href="/logout">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>
<section>
    <section class="banner-area relative">
        <div class="overlay overlay-bg"></div>
        <div class="container">
            <div id="inference_section" class="row fullscreen align-items-center d-flex">
                <div class="col-lg-4 col-md-12 col-sm-12">
                    <div class="banner-content">
                        <form id="uploadForm" enctype="multipart/form-data" class="">
                            <input type="file" id="fileId" class="fileselect col-lg-12" name="filedata" accept="image/jpeg" onchange="setThumbnail(event);"/>
                            <select id="solution" name="solution" class="col-lg-7 col-md-12">
                                <option value=1>SageMaker</option>
                                <option value=2>Rekognition</option>
                                <option value=3>Both</option>
                            </select>
                        </form>
                        <input type="button" value="추론" id="file" class="btn btn-primary col-lg-2 col-sm-12"><br>
                        <p id="explaintext">Detect Our New Image.</p>
                    </div>
                </div>
                <div class="col-lg-7 col-md-12 col-sm-12">
                    <h1 id="Oritext"></h1>
                    <img src="" alt="" class="img-fluid img-thumbnail" id="originalImage">
                </div>
            </div>
        </div>
    </section>
</section>
<main></main>

<!-- Modal -->
<div class="modal fade" id="ModalUpload" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" data-backdrop="static" data-keyboard="false" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalUploadTitle">Inference..</h5>
            </div>
            <div class="modal-body">
                추론중입니다. 잠시만 기다려 주십시오..
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="ModalInference" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" data-backdrop="static" data-keyboard="false" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document" id="ModalSize">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalInferenceTitle">Result</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div>
                    <h3 id="sageText"></h3>
                    <img id="sageImage" src="">
                </div>
                <div>
                    <h3 id="rekogText"></h3>
                    <img id="rekogImage" src="">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
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
