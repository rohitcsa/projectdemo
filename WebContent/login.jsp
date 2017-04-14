<%-- 
    Document   : login
    Created on : 5 Jul, 2015, 9:18:24 PM
    Author     : tanuj_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            <title>REDHOLT Report | Log In</title>
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
                    <font style="color:#E20D0D;font-size:95px;font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;padding-bottom: 10px"><b>RED</b>HOLT</font> 
                <center> <font style="color:#E20D0D;font-size:2vw; ">Search & Advisory Services</font></center>
                </div><!-- /.login-logo -->
               
                <div class="login-box-body" style="border: solid black; border-width: 1px">
                    <p class="login-box-msg" id="loginboxMsg">Sign In to Start your Session</p>
                    <form action="verifyLogin" method="post">
                        <div class="form-group has-feedback" id="box1">
                            <input type="email" class="form-control" placeholder="Email or User ID" id="emailid" name="emailid"/>
                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback" id="box2">
                            <input type="password" class="form-control" placeholder="Password" id="password" name="password"/>
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback" id="box3" hidden>
                            <input type="email" class="form-control" placeholder="Please enter assigned email or user-ID" id="emailid2" name="emailid2"/>
                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                        </div>
                        <div class="row" id="row1">
                            <div class="col-xs-8">    
                                <div class="checkbox icheck">
                                    <label>
                                        <input type="checkbox"> Remember Me
                                    </label>
                                </div>                        
                            </div><!-- /.col -->
                            <div class="col-xs-4">
                                <button type="submit" class="btn btn-default btn-block btn-flat">Sign In</button>
                            </div><!-- /.col -->
                        </div>
                    </form>
                    <div class="row" id="row2" hidden>
                        <div class="col-xs-8">    
                        </div><!-- /.col -->
                        <div class="col-xs-4">
                            <button id="sendEmail" class="btn btn-default btn-block btn-flat">Continue</button>
                        </div><!-- /.col -->
                    </div>
                    <a href="#" onclick="event.preventDefault();" id="showForgotPasswordContent">Reset Password</a><br>
                </div><!-- /.login-box-body -->
            </div><!-- /.login-box -->

            <!-- Alert Modal -->
            <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button id="modalClose1" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Forgot Password</h4>
                        </div>
                        <div class="modal-body">
                            <h4 id="alertModalContent"></h4>
                        </div>
                        <div class="modal-footer">
                            <button id="modalClose2" type="button" class="btn btn-outline btn-default pull-left" data-dismiss="modal" style="background-color: #000000" hidden>Close</button>
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

                $("#showForgotPasswordContent").click(function () {
                    var emailid = $("#emailid").val();
                    if (emailid.indexOf('@client') >= 0 || emailid.indexOf('demouser@') >= 0) {
                        var content = "Sorry, but you dont have the privilege to reset your password. Mail us at reporting@groupnp.com to get in touch.";
                        $("#alertModalContent").html(content);
                        $("#modalClose1").show();
                        $("#modalClose2").show();
                        $("#alertModal").modal('show');
                    }
                    else {
                        $("#showForgotPasswordContent").hide();
                        $("#loginboxMsg").hide();
                        $("#box1").hide();
                        $("#box2").hide();
                        $("#row1").hide();
                        $("#loginboxMsg").html("Enter your email address that you use to login.");
                        $("#emailid2").val(emailid);
                        $("#loginboxMsg").fadeIn();
                        $("#row2").fadeIn();
                        $("#box3").fadeIn();
                    }
                });
            </script>

            <script>
                $("#sendEmail").click(function () {

                    var email = $("#emailid2").val();
                    var re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
                    if (re.test(email) === true) { //Valid Email
                        var content = "<div align=\"center\">Sending Reset Code to your email..</div><br><div align=\"center\"><i class=\"fa fa-refresh fa-spin fa-2x\"></i></div>";
                        $("#alertModalContent").html(content);
                        $("#modalClose1").hide();
                        $("#modalClose2").hide();
                        $("#alertModal").modal('show');

                        new Ajax.Request('mail_ResetPassword',
                                {method: 'post',
                                    parameters: {email: email},
                                    onSuccess: function () {

                                        var content = "A <b>reset code</b> (along with reset link) has been sent on your email. Please follow that link and enter your reset code to reset your password.";
                                        $("#alertModalContent").html(content);
                                        $("#modalClose1").show();
                                        $("#modalClose2").show();

                                        $("#emailLoading").hide();

                                        $("#showForgotPasswordContent").show();
                                        $("#loginboxMsg").show();
                                        $("#box1").show();
                                        $("#box2").show();
                                        $("#row1").show();
                                        $("#loginboxMsg").html("Sign In to Start your Session");
                                        $("#loginboxMsg").fadeIn();
                                        $("#row2").hide();
                                        $("#box3").hide();
                                    },
                                    onFailure: function () {
                                        alert('Something went wrong.');
                                    }
                                });

                    }
                    else {
                        var content = "<div align=\"center\">Invalid Email Address. Kindly check and re-enter.</div>";
                        $("#alertModalContent").html(content);
                        $("#modalClose1").show();
                        $("#modalClose2").show();
                        $("#alertModal").modal('show');
                    }

                });
            </script>
        </body>
    </html>