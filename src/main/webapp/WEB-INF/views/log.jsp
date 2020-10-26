<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>Log</title>
    <sec:csrfMetaTags />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Dosis:300,400,500,,600,700,700i|Lato:300,300i,400,400i,700,700i" rel="stylesheet">


    <script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Vendor CSS Files -->
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
    <link href="/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="/assets/vendor/venobox/venobox.css" rel="stylesheet">
    <link href="/assets/vendor/line-awesome/css/line-awesome.min.css" rel="stylesheet">
    <link href="/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">


    <!-- Template Main CSS File -->
    <link href="/assets/css/style.css" rel="stylesheet">
    <link href="/css/log.css" rel="stylesheet">
</head>
<body id="logbody">
    <header id="header" class="fixed-top">
        <div class="container d-flex align-items-center">
            <a href="/" class="logo mr-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>
            <nav class="nav-menu d-none d-lg-block">
                <ul>
                    <li class=""><a href="/">Home</a></li>
                    <li class=""><a href="/infer">Inference</a></li>
                    <li class="active"><a href="/log">Log</a></li>
                    <li class=""><a href="/logout">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <section></section>
    <div class="container">
        <div class="row">
            <table class="table table-striped table-bordered" id="logtable">
                <thead class="bg-light">
                    <th>E-mail ID</th>
                    <th>Inference Image</th>
                    <th>Original Image</th>
                    <th>Used Service</th>
                    <th>Result</th>
                    <th>Used Time</th>
                </thead>
                <tbody>
                <c:forEach var="log" items="${logList}">
                    <tr class="bg-info text-white">
                        <td>${log.getEID()}</td>
                        <td><a class="btn" s3path="${log.inferimagepath}">Click</a></td>
                        <td><a class="btn" s3path="${log.originalimagepath}">Click</a></td>
                        <td>${log.usedservice}</td>
                        <td>${log.result}</td>
                        <td>${log.timestamp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <form id="logForm">
        <input type="hidden" name="s3path" id="inputs3path">
    </form>

    <div class="modal fade" id="ModalLog" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" data-backdrop="static" data-keyboard="false" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document" id="ModalSize">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="ModalLogTitle"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="ModalLogText"></div>
                    <img id="LogImage" src="">
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
    <script src="/js/log.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
</body>
</html>
