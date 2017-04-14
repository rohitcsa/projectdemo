<%-- 
    Document   : resetPassword
    Created on : 14 Sep, 2015, 11:46:01 AM
    Author     : tanuj_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .login-page:before {
                content: "";
                position: fixed;
                left: 0;
                right: 0;
                z-index: -1;

                display: block;
                background-image: url('dist/img/background1.jpg');
                width: 100%;
                height: 100%;

                -webkit-filter: blur(5px);
                -moz-filter: blur(5px);
                -o-filter: blur(5px);
                -ms-filter: blur(5px);
                filter: blur(5px);
            }

            .login-page {
                position: fixed;
                left: 0;
                right: 0;
                z-index: 0;
                margin-left: 20px;
                margin-right: 20px;
            }

        </style>
        <meta charset="UTF-8">
        <title>NPGroup ReportDeck | Log In</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.4 -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Font Awesome Icons -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <!-- iCheck -->
        <link href="plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="login-logo" style="font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif">
                <img src="dist/img/NP_icon3.png" alt="NP" style="padding-bottom: 10px"/>
                <b>NPGroup</b>ReportDeck
            </div><!-- /.login-logo -->
            <div class="login-box-body" style="border: solid black; border-width: 1px">
                <p class="login-box-msg" id="loginboxMsg">Enter your Email & Reset Code to set new password</p>
                <div class="form-group has-feedback" id="row1">
                    <input type="email" class="form-control" placeholder="Email Address" id="emailid" name="emailid"/>
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback" id="row2">
                    <input class="form-control" placeholder="Reset Code" id="resetcode" name="resetcode"/>
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback"  id="row3" hidden>
                    <input type="password" class="form-control" placeholder="Enter New Password" id="newpassword" name="newpassword"/>
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                    <div class="col-xs-12" id="continueDiv">
                        <button class="btn btn-default btn-block btn-flat" id="checkResetCode">Continue</button>
                    </div><!-- /.col -->
                    <div class="col-xs-12" hidden id="setpasswordDiv">
                        <button class="btn btn-default btn-block btn-flat" id="setPassword" hidden>Set Password</button>
                    </div><!-- /.col -->
                </div>
            </div><!-- /.login-box-body -->
        </div><!-- /.login-box -->

        <!-- Alert Modal -->
        <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button id="modalClose1" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Reset Password</h4>
                    </div>
                    <div class="modal-body">
                        <h4 id="alertModalContent"></h4>
                    </div>
                    <div class="modal-footer">
                        <button id="modalClose2" type="button" class="btn btn-outline btn-default pull-left" data-dismiss="modal" style="background-color: #000000" hidden>Close</button>
                        <button id="modalGoToLogin" type="button" class="btn btn-outline btn-default pull-left" style="background-color: #000000" onclick="location.href = 'http://npreportingsuite.com/NPReportDeck/login.jsp';" hidden>Go to Login</button>
                    </div>
                </div>
            </div>
        </div><!-- /.Alert Modal -->

        <!-- Prototype Script -->
        <script src="plugins/prototype/prototype.js" type="text/javascript"></script>
        <!-- jQuery 2.1.4 -->
        <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <script>
                            $(function () {
                                $('input').iCheck({
                                    checkboxClass: 'icheckbox_square-blue',
                                    radioClass: 'iradio_square-blue',
                                    increaseArea: '20%' // optional
                                });
                            });
        </script>

        <script>
            $("#checkResetCode").click(function () {
                var content = "<div align=\"center\">Verifying Reset Code..</div><br><div align=\"center\"><i class=\"fa fa-refresh fa-spin fa-2x\"></i></div>";
                $("#alertModalContent").html(content);
                $("#modalClose1").hide();
                $("#modalClose2").hide();
                $("#alertModal").modal('show');

                var emailid = $("#emailid").val();
                var resetcode = $("#resetcode").val();
                new Ajax.Request('checkResetCode',
                        {method: 'post',
                            parameters: {emailid: emailid, resetcode: resetcode},
                            onSuccess: function (transport) {
                                var flag = transport.responseText.trim();
                                if (flag === "1") {
                                    $("#alertModal").modal('hide');

                                    $("#loginboxMsg").html("Set new password");
                                    $("#continueDiv").hide();
                                    $("#setpasswordDiv").fadeIn();
                                    $("#row1").hide();
                                    $("#row2").hide();
                                    $("#row3").fadeIn();
                                }
                                else if (flag === "0") {
                                    var content = "<div align=\"center\">Oops! Its seems you have entered incorrect Email/Reset Code. Please check the entered details.</div>";
                                    $("#alertModalContent").html(content);
                                    $("#modalClose2").show();
                                    $("#alertModal").modal('show');
                                }

                            },
                            onFailure: function () {
                                $("#alertModal").modal('hide');
                                alert('Something went wrong.');
                            }
                        });

            });
        </script>

        <script>
            $("#setPassword").click(function () {
                var content = "<div align=\"center\">Setting new password..</div><br><div align=\"center\"><i class=\"fa fa-refresh fa-spin fa-2x\"></i></div>";
                $("#alertModalContent").html(content);
                $("#modalClose1").hide();
                $("#modalClose2").hide();
                $("#alertModal").modal('show');

                var emailid = $("#emailid").val();
                var newpassword = $("#newpassword").val();
                new Ajax.Request('resetPassword',
                        {method: 'post',
                            parameters: {emailid: emailid, newpassword: newpassword},
                            onSuccess: function () {

                                var content = "<div align=\"center\"><b>Done!</b> New password has been set.</div>";
                                $("#alertModalContent").html(content);
                                $("#modalClose1").hide();
                                $("#modalClose2").hide();
                                $("#modalGoToLogin").show();


                            },
                            onFailure: function () {
                                $("#alertModal").modal('hide');
                                alert('Something went wrong.');
                            }
                        });

            });
        </script>
    </body>
</html>