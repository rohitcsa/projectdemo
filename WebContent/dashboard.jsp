<%-- 
    Document   : dashboard
    Created on : 15 Jul, 2015, 11:21:43 AM
    Author     : tanuj_000
--%>
<!DOCTYPE html>
<html>
    <%
        String username = session.getAttribute("user").toString();
        System.out.println("Employee Active : " + username + " @ " + request.getRequestURI());
    %>
    <head>
        <!-- Eager Plugins Script-->
        <script src="//fast.eager.io/TWczeyfqCXgH.js"></script>
        <meta charset="UTF-8">
        <title>REDHOLT| Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.4 -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- FontAwesome 4.3.0 -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons 2.0.0 -->
        <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- daterange picker -->
        <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap slider -->
        <link href="plugins/bootstrap-slider/slider.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins 
             folder instead of downloading all of them to reduce the load. -->
        <link href="dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
        <!-- iCheck -->
        <link href="plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
        <!-- DATA TABLES -->
        <link href="plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Switch -->
        <link rel="stylesheet" href="plugins/switch/static/stylesheets/bootstrap-switch.css" />
        <!-- Chosen -->
        <link href="plugins/chosen/chosen.min.css" rel="stylesheet" type="text/css" />
        <!-- Tags Input -->
        <link href="plugins/tagsinput/jquery.tagsinput.css" rel="stylesheet" type="text/css" />
        <!-- Smartmenus -->
        <link href="plugins/smartmenus/css/sm-core-css.css" rel="stylesheet" type="text/css">
        <link href="plugins/smartmenus/css/sm-simple/sm-simple.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-black sidebar-mini sidebar-collapse">
        <div class="wrapper">
            <header class="main-header">
                <!-- Logo -->
                <a href="/" onclick="event.preventDefault();" class="logo">
                   
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <span class="logo-lg"><font style="color:#E20D0D;font-size:35px;font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;"><b>RED</b>HOLT</font></span>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="dist/img/user-160x160.jpg" class="user-image" alt="User Image"/>
                                    <span class="hidden-xs"><%=username%></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="dist/img/user-160x160.jpg" class="img-circle" alt="User Image" />
                                        <p>
                                            <%=username%> -REDHOLT
                                            <small>REDHOLT (Bronze)</small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->
                            <li>
                                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user-160x160.jpg" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p><%=username%></p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" id="reportList">
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper"  style="background-color: #f2f2f2">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                        <small>Select Report to View</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active"><a href="#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content" id="contentBlock">
                </section><!-- /.content -->

                <a id="dlink"  style="display:none;"></a> <!-- For Download Data  -->
                <input type="hidden" id="username" value="<%=username%>" />


                <!-- Alert Modal -->
                <div class="modal modal-primary fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Alert</h4>
                            </div>
                            <div class="modal-body" style="background-color: white">
                                <h4 id="alertModalContent"></h4>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div><!-- /.Alert Modal -->

            </div><!-- /.content-wrapper -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 0.1b
                </div>
                <strong>Copyright &copy; 2017-2018 <a href="http://www.groupnp.com">REDHOLT</a>.</strong> All rights reserved.
            </footer>

        </div><!-- ./wrapper -->

        <!-- Prototype Script -->
        <script src="plugins/prototype/prototype.js" type="text/javascript"></script>
        <!-- jQuery 2.1.4 -->
        <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>

        <script>
                    (function () {
                        var isBootstrapEvent = false;
                        if (window.jQuery) {
                            var all = jQuery('*');
                            jQuery.each(['hide.bs.dropdown',
                                'hide.bs.collapse',
                                'hide.bs.modal',
                                'hide.bs.tooltip',
                                'hide.bs.popover'], function (index, eventName) {
                                all.on(eventName, function (event) {
                                    isBootstrapEvent = true;
                                });
                            });
                        }
                        var originalHide = Element.hide;
                        Element.addMethods({
                            hide: function (element) {
                                if (isBootstrapEvent) {
                                    isBootstrapEvent = false;
                                    return element;
                                }
                                return originalHide(element);
                            }
                        });
                    })();
        </script>
        <!-- jQuery UI 1.11.2 -->
        <script src="https://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
                    $.widget.bridge('uibutton', $.ui.button);

        </script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>
        <script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!-- Slimscroll -->
        <script src="plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <!-- FastClick -->
        <script src='plugins/fastclick/fastclick.min.js'></script>
        <!-- AdminLTE App -->
        <script src="dist/js/app.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.js" type="text/javascript"></script>
        <!-- Bootstrap slider -->
        <script src="plugins/bootstrap-slider/bootstrap-slider.js" type="text/javascript"></script>
        <!-- Switch -->
        <script src="plugins/switch/static/js/bootstrap-switch.min.js"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>

        <!-- Chosen Script -->
        <script src="plugins/chosen/chosen.jquery.min.js" type="text/javascript"></script>
        <!-- Tags Input Script -->
        <script src="plugins/tagsinput/jquery.tagsinput.js"></script>
        <!-- hc-sticky -->
        <script src="plugins/hc-sticky/hc-sticky.min.js"></script>
        <!-- smartmenus -->
        <script src="plugins/smartmenus/jquery.smartmenus.js"></script>

        <script>
                    //To enable 'on click' instead of 'hover'
                    $.SmartMenus.prototype.isTouchMode = function () {
                        return true;
                    };

                    var $mainMenu = $('#optionsDiv');
                    $mainMenu.bind('activate.smapi', function (e, item) {
                        var obj = $mainMenu.data('smartmenus'),
                                $item = $(item),
                                $sub = $item.parent().dataSM('sub'),
                                level = $sub ? $sub.dataSM('level') : -1;
                        if (!obj.isTouchMode() && level > 2 && !$item.dataSM('arrow-activated')) {
                            return false;
                        }
                        // unflag
                        $item.removeDataSM('arrow-activated');
                    })
                            .delegate('span.sub-arrow', 'mouseenter', function (e) {
                                var obj = $mainMenu.data('smartmenus'),
                                        $item = $(this).parent(),
                                        $sub = $item.parent().dataSM('sub'),
                                        level = $sub ? $sub.dataSM('level') : -1;
                                if (!obj.isTouchMode() && level > 2) {
                                    // flag it so that we don't cancel the event on activate.smapi
                                    $item.dataSM('arrow-activated', true);
                                    $mainMenu.smartmenus('itemActivate', $item);
                                }
                            });

        </script>

        <script>
            $(document).ready(function () {

                var username = $("#username").val();

                new Ajax.Request('fetchReportList', {
                    method: 'get', parameters: {username: username},
                    onSuccess: function (transport) {

                        var data = transport.responseText;
                        $('#reportList').html(data);

                    },
                    onFailure: function () {
                        alert('Something went wrong while getting client information..');
                    }
                });

                new Ajax.Request('fetchReports', {
                    method: 'get', parameters: {username: username},
                    onSuccess: function (transport) {

                        var data = transport.responseText;
                        $('#contentBlock').html(data);

                    },
                    onFailure: function () {
                        alert('Something went getting client information..');
                    }
                });
            });
        </script>

    </body>
</html>