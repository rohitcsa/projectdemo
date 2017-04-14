<%-- 
    Document   : Demo_Gold_Weekly
    Created on : 14 Aug, 2015, 11:41:46 AM
    Author     : tanuj_000
--%>

<!DOCTYPE html>
<html>
    <%
        String username = session.getAttribute("user").toString();
        String email = session.getAttribute("email").toString();
        System.out.println("Employee Active : " + username + " @ " + request.getRequestURI());
    %>
    <head>
        <!-- Eager Plugins Script-->
        <script src="//fast.eager.io/TWczeyfqCXgH.js"></script>
        <meta charset="UTF-8">
        <title>REDHOLT  | MICROSOFT Weekly Report</title>
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
        <!-- Amcharts Export -->
        <link href="https://rawgit.com/amcharts/export/master/export.css" rel="stylesheet" type="text/css">
        <style>
            #optionsDivMain{
                background-color: #D8E6FF;
            }
        </style>
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
                <a href="index.html" class="logo">
                   
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <span class="logo-lg"> <font style="color:#E20D0D;font-size:35px;font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;"><b>RED</b>HOLT</font></span>
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
                                            <small>MICROSOFT Weekly (Gold)</small>
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
                    <ol class="breadcrumb">
                        <li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                        <li class="active"> MICROSOFT - Weekly</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content" id="contentBlock">
                    <!-- Main row -->
                    <div class="row">
                        <div class="col-lg-12" align="center">
                             <font style="color:#E20D0D;font-size:45px;font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;"><b>RED</b>HOLT</font>
                            <h3 style="font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif">
                               MICROSOFT Tracker
                                &nbsp;
                                (<font style="font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif" id="reportDateLabel"></font>)
                                &nbsp;
                                <img src="dist/img/goldIcon.png" data-original-title="Gold - Weekly" data-toggle="tooltip" style="padding-bottom: 0.2%"/>
                            </h3>
                            <br>
                        </div>
                        <div class="col-lg-12" style="padding-top: 9px; padding-bottom: 9px; background-color: #243038; color: #FFFFFF" align="center">DEMO PURPOSE</div>
                        <div class="col-lg-12" style="padding-top: 9px; padding-bottom: 9px; background-color: #243038" hidden>
                            <div class="col-lg-l2" id="floatDiv" id="optionsDivMain" align="center" style="z-index: 100; display: table;">
                                <ul  id="optionsDiv" class="sm sm-simple"  style="z-index: 101">
                                    <li><a href="#" onclick="downloadReport()">DOWNLOAD REPORT</a></li>
                                    <li><a href="#" id="downloadData" onclick="event.preventDefault();">DOWNLOAD DATA</a></li>
                                    <li><a href="#">SELECT WEEK</a>
                                        <ul id="reportDates">
                                        </ul>
                                    </li>
                                    <li><a href="#" onclick="event.preventDefault();" id="showEmailOptionsDiv">EMAIL REPORT</a></li>
                                </ul>
                            </div>
                        </div><!-- ./float Div -->

                        <section class="col-lg-12" id="emailOptionsDiv" hidden>
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-envelope-o"></icon>
                                    <h3 class="box-title"><b>Email Report</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12">
                                        <div class="col-lg-2" align="right"><br><b>Enter Email-ID(s):</b></div>
                                        <div class="col-lg-8">
                                            <input id="emailList" value="" class="form-control"/>
                                            <br>
                                        </div>
                                        <div class="col-lg-2"></div>
                                    </div>

                                    <div class="col-lg-12">
                                        <div class="col-lg-2" align="right"><br><b>Enter Email Message:</b></div>
                                        <div class="col-lg-9">
                                            <textarea id="emailMessage" placeholder="Enter you email message content here.." style="width: 100%;">
                                                <p>Hi!&nbsp;Please find the report attached with this mail.</p>
                                                <hr />
                                                <p>You can view this report online by going to <a href="http://npreportingsuite.com"></a>&nbsp;and login using following account details:<br />
                                                <br />
                                                User ID:&nbsp;<strong></strong><br />
                                                Password:&nbsp;<strong></strong>
                                                </p>
                                                <p>Cheers!
                                                    <br />
                                                    <%=username%>
                                                    <br />
                                                    <em><%=email%></em>
                                                    <br />
                                                </p>
                                            </textarea>
                                            <br>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                    <div class="col-lg-12" align="center">
                                        <button class="btn btn-default" onclick="emailReport()" data-original-title="Email Report" data-toggle="tooltip"><icon class="fa fa-envelope-o fa-2x"/></button>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button class="btn btn-default" onclick="hideEmailOptionsDiv()" data-original-title="Cancel and Hide Email Options" data-toggle="tooltip"><icon class="fa fa-times fa-2x"/></button>
                                        <br><br>
                                    </div>
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="emailLoading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-table"></icon>
                                    <h3 class="box-title"><b>Weekly Summary</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <div class="col-lg-12" id="WeeklySummaryTableDiv" style="overflow:auto;">
                                        <br>
                                        <table id="WeeklySummaryTable" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-12 -->
                                </div><!-- /.box-body -->          
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div1Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-7 connectedSortable">
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-bar-chart"></icon>
                                    <h3 class="box-title"><b>Open Roles Activity</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" align="center">
                                        <div id="OpenRolesActivityChart" style="width: 100%; height: 500px; background-color: #FFFFFF;" >
                                            <br><br><br>
                                        </div>
                                    </div><!-- /.col-lg-12 -->
                                    <div class="col-lg-12" id="OpenRolesActivityTableDiv">
                                        <br><br>
                                        <table id="OpenRolesActivityTable" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-12 -->
                                </div><!-- /.box-body -->          
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div2Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-5 connectedSortable">
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-bar-chart"></icon>
                                    <h3 class="box-title"><b>Open Requisitions</b>
                                    </h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br><br><br><br><br><br><br><br><br><br>
                                    <div class="col-lg-12">
                                        <div id="OpenRequisitionsChart" style="width: 100%; height: 300px; background-color: #FFFFFF;">
                                            <br><br>
                                        </div>
                                    </div><!-- /.col-lg-12 -->
                                    <div class="col-lg-12" id="OpenRequisitionsTableDiv">
                                        <br><br><br><br>
                                        <table id="OpenRequisitionsTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-12 -->
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div3Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-table"></icon>
                                    <h3 class="box-title"><b>Requisitions Radar</b></h3>  
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <div class="col-lg-12" id="RequisitionsRadarTableDiv" style="overflow:auto;">
                                        <br>
                                        <table id="RequisitionsRadarTable" class="table table-bordered table-hover">
                                        </table>
                                        <br>
                                    </div>
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div4Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-pie-chart"></icon>
                                    <h3 class="box-title"><b>Age of Open Roles</b>
                                    </h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" align="center">
                                        <div class="col-lg-6">
                                            <div id="AgeOfOpenRolesPieChart" style="width: 100%; height: 450px; background-color: #FFFFFF;" ></div>
                                        </div><!-- /.col-lg-12 -->
                                        <div class="col-lg-6" id="AgeOfOpenRolesPieTableDiv">
                                            <br><br><br><br>
                                            <table id="AgeOfOpenRolesPieTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            </table>
                                            <br>
                                        </div><!-- /.col-lg-12 -->
                                    </div>
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div5Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-pie-chart"></icon>
                                    <h3 class="box-title"><b>Trends</b>
                                    </h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" align="center">
                                        <div class="col-lg-6">
                                            <div id="TrendsChart" style="width: 100%; height: 450px; background-color: #FFFFFF;" ></div>
                                        </div><!-- /.col-lg-12 -->
                                        <div class="col-lg-6" id="TrendsTableDiv">
                                            <br><br><br><br>
                                            <table id="TrendsTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            </table>
                                            <br>
                                        </div><!-- /.col-lg-12 -->
                                    </div>
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div6Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-table"></icon>
                                    <h3 class="box-title"><b>Tracker - Filled Roles Of The Week</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" id="Tracker2TableDiv" style="overflow:auto;">
                                        <br>
                                        <table id="Tracker2Table" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-12 -->
                                </div><!-- /.box-body -->          
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div7Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-bar-chart"></icon>
                                    <h3 class="box-title"><b>Filled Requisitions</b>
                                    </h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" align="center">
                                        <div class="col-lg-12">
                                            <div id="FilledRequisitionsChart" style="width: 60%; height: 300px; background-color: #FFFFFF;" ></div>
                                        </div><!-- /.col-lg-12 -->
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8" id="FilledRequisitionsTableDiv">
                                            <br><br><br><br>
                                            <table id="FilledRequisitionsTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            </table>
                                            <br>
                                        </div><!-- /.col-lg-8 -->
                                        <div class="col-lg-2"></div>
                                    </div>
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div8Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <br><br>
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-table"></icon>
                                    <h3 class="box-title"><b>Tracker - Cancelled/On Hold Roles Of The Week</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" id="Tracker3TableDiv" style="overflow:auto;">
                                        <br>
                                        <table id="Tracker3Table" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-12 -->
                                </div><!-- /.box-body -->          
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div9Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>
                        <!--
                                                <section class="col-lg-12 connectedSortable">
                                                    <div class="box box-primary">
                                                        <div class="box-header with-border">
                                                            <icon class="fa fa-bar-chart"></icon>
                                                            <h3 class="box-title">Cost Per Hire</h3>
                                                            <div class="box-tools pull-right">
                                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                            </div>
                                                        </div>
                                                        <div class="box-body no-padding">
                                                            <br>
                                                            <div class="col-lg-12" id="CostPerHireTableDiv" style="overflow:auto;">
                                                                <table id="CostPerHireTable" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                                                </table>
                                                                <br>
                                                            </div>
                                                        </div>     
                                                        <div class="overlay" id="div7Loading" hidden>
                                                            <i class="fa fa-refresh fa-spin"></i>
                                                        </div>
                                                    </div>
                                                </section>
                        -->
                    </div><!-- /.row (main row) -->
                </section><!-- /.content -->


                <a id="dlink"  style="display:none;"></a> <!-- For Download Data -->
                <input type="hidden" id="username" value="<%=username%>" />

                <!-- Alert Modal -->
                <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button id="modalClose1" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">REDHOLT - Notification</h4>
                            </div>
                            <div class="modal-body">
                                <h4 id="alertModalContent"></h4>
                            </div>
                            <div class="modal-footer">
                                <button id="modalClose2" type="button" class="btn btn-outline btn-default pull-left" data-dismiss="modal" style="background-color: #000000" hidden>close</button>
                            </div>
                        </div>
                    </div>
                </div><!-- /.Alert Modal -->

            </div><!-- /.content-wrapper -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 0.1b
                </div>
                <strong>Copyright &copy; 2017-2018 <a href="https://www.groupnp.com">REDHOLT</a>.</strong> All rights reserved.
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
        <!-- CK Editor -->
        <script src="plugins/ckeditor/ckeditor.js"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.js" type="text/javascript"></script>
        <!-- Bootstrap slider -->
        <script src="plugins/bootstrap-slider/bootstrap-slider.js" type="text/javascript"></script>
        <!-- Switch -->
        <script src="plugins/switch/static/js/bootstrap-switch.min.js"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/plug-ins/1.10.8/sorting/datetime-moment.js" type="text/javascript"></script>
        <script>
                                            $.fn.dataTable.moment('DD/MM/YY');
                                            $.fn.dataTable.moment('DD/MM/YYYY');
                                            $.fn.dataTable.moment('DD-MM-YYYY');
                                            $.fn.dataTable.moment('DD-MMM-YY');
                                            $.fn.dataTable.moment('MMMM YYYY');
        </script>
        <!-- amCharts -->
        <script type="text/javascript" src="https://www.amcharts.com/lib/3/amcharts.js"></script>
        <script type="text/javascript" src="https://www.amcharts.com/lib/3/serial.js"></script>
        <script type="text/javascript" src="https://www.amcharts.com/lib/3/pie.js"></script>
        <script type="text/javascript" src="https://www.amcharts.com/lib/3/themes/light.js"></script>
        <script src="plugins/amcharts/export.js" type="text/javascript"></script>
        <!-- html2canvas Script -->
        <script type="text/javascript" src="https://rawgit.com/niklasvh/html2canvas/master/dist/html2canvas.js"></script>
        <!-- Chosen Script -->
        <script src="plugins/chosen/chosen.jquery.min.js" type="text/javascript"></script>
        <!-- Tags Input Script -->
        <script src="plugins/tagsinput/jquery.tagsinput.js"></script>
        <!-- hc-sticky -->
        <script src="plugins/hc-sticky/hc-sticky.min.js"></script>
        <!-- smartmenus -->
        <script src="plugins/smartmenus/jquery.smartmenus.js"></script>
        <!-- pdfmakeTable -->
        <script src="plugins/pdfmake/pdfmakeTable.js"></script>
        <script src="plugins/pdfmake/pdfmakeTable2.js"></script>
        <!-- tableToexcel -->
        <script src="plugins/tabletoexcel/tableToexcel.js"></script>
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
                                                        if (!obj.isTouchMode() && level > 2) {                                                             // flag it so that we don't cancel the event on activate.smapi
                                                            $item.dataSM('arrow-activated', true);
                                                            $mainMenu.smartmenus('itemActivate', $item);
                                                        }
                                                    });

        </script>

        <script>
            var optionsFlag = 1;
            var reportfileDate = "";
            var reportdate = "";
            var charts = [];
            var chart1, chart2, chart3;
            var npIconURL;
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
                        alert('Something went getting client information..');
                    }
                });

                new Ajax.Request('fetchReportDates', {
                    method: 'get',
                    parameters: {client: 'Gold_LexisNexisIndia_Weekly'},
                    onSuccess: function (transport) {

                        $('#div1Loading').show();
                        $('#div2Loading').show();
                        $('#div3Loading').show();
                        $('#div4Loading').show();
                        $('#div5Loading').show();
                        $('#div6Loading').show();
                        $('#div7Loading').show();
                        $('#div8Loading').show();
                        $('#div9Loading').show();

                        var tabledata = transport.responseText;
                        $('#reportDates').html(tabledata);

                        reportfileDate = "31July2016";
                        reportdate = "1992-03-20";
                        $("#reportDateLabel").html("31 July 2016");


                        $('#optionsDiv').smartmenus();

                        $("#emailList").tagsInput({
                            'height': '80px',
                            'width': '100%',
                            'interactive': true,
                            'defaultText': 'Press Tab to Add Email-IDs',
                            'removeWithBackspace': true,
                            'allowDuplicates': false
                        });

                        CKEDITOR.replace('emailMessage');

                        $('#floatDiv').hcSticky({
                            stickTo: document,
                            responsive: true,
                            noContainer: true
                        });
                        $(".connectedSortable").sortable({
                            placeholder: "sort-highlight",
                            connectWith: ".connectedSortable",
                            handle: ".box-header, .nav-tabs",
                            forcePlaceholderSize: true,
                            zIndex: 999999
                        });
                        $(".connectedSortable .box-header, .connectedSortable .nav-tabs-custom").css("cursor", "move");

                        new Ajax.Request('getTable_Gold_LexisNexisIndia_WeeklySummary', {
                            method: 'get',
                            parameters: {reportdate: "2016-07-31"},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#WeeklySummaryTable').html(tabledata);

                                $('#WeeklySummaryTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });
                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>Weekly Summary Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable2_Gold_LexisNexisIndia_Tracker', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#Tracker2Table').html(tabledata);

                                $('#Tracker2Table').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });
                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Tracker Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable3_Gold_LexisNexisIndia_Tracker', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#Tracker3Table').html(tabledata);

                                $('#Tracker3Table').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });
                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Tracker Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable_Gold_LexisNexisIndia_RequisitionsRadar', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#RequisitionsRadarTable').html(tabledata);

                                $('#RequisitionsRadarTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });

                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Requisitions Radar Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable_Gold_LexisNexisIndia_OpenRolesActivity', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#OpenRolesActivityTable').html(tabledata);

                                $('#OpenRolesActivityTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });

                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Open Roles Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable_Gold_LexisNexisIndia_OpenRequisitions', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#OpenRequisitionsTable').html(tabledata);

                                $('#OpenRequisitionsTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });

                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Open Requisitions Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable_Gold_LexisNexisIndia_AgeOfOpenRoles', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#AgeOfOpenRolesPieTable').html(tabledata);

                                $('#AgeOfOpenRolesPieTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });

                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Age of Open Roles Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });
                        new Ajax.Request('getTable_Gold_LexisNexisIndia_Trends', {
                            method: 'get',
                            parameters: {reportdate: "2016-07-31"},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#TrendsTable').html(tabledata);
                                $('#TrendsTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });
                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>Trends Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });
                        new Ajax.Request('getTable_Gold_LexisNexisIndia_FilledRequisitions', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#FilledRequisitionsTable').html(tabledata);

                                $('#FilledRequisitionsTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });
                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Filled Requisitions Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getTable_Bronze_Yougov_CostPerHire', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                var tabledata = transport.responseText;
                                $('#CostPerHireTable').html(tabledata);


                                $('#CostPerHireTable').dataTable({
                                    "bPaginate": false,
                                    "bLengthChange": true,
                                    "bFilter": false,
                                    "bSort": true,
                                    "bInfo": false,
                                    "bAutoWidth": true,
                                    "order": [],
                                    "columnDefs": [{
                                            "targets": 'no-sort',
                                            "orderable": false
                                        }]
                                });
                            },
                            onFailure: function () {
                                var content = "<b>Snap!</b> Something went wrong while <b>fetching Cost Per Hire Table</b>.";
                                $("#alertModalContent").html(content);
                                $("#alertModal").modal('show');
                            }
                        });

                        new Ajax.Request('getData_Gold_LexisNexisIndia_OpenRolesActivity', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                data = transport.responseText.evalJSON();
                                datajson = JSON.stringify(data);

                                new Ajax.Request('getGraph_Gold_LexisNexisIndia_OpenRolesActivity',
                                        {method: 'get',
                                            parameters: {reportdate: reportdate},
                                            onSuccess: function (transport) {
                                                graphs = transport.responseText.evalJSON();
                                                graphsjson = JSON.stringify(graphs);
                                                OpenRolesActivityChart();
                                            },
                                            onFailure: function () {
                                                alert('Something went wrong while getting graphs...');
                                            }
                                        });
                            },
                            onFailure: function () {
                                alert('Something went wrong while getting the data...');
                            }
                        });

                        new Ajax.Request('getData_Gold_LexisNexisIndia_OpenRequisitions', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                data2 = transport.responseText.evalJSON();
                                datajson2 = JSON.stringify(data2);

                                new Ajax.Request('getGraph_Gold_LexisNexisIndia_OpenRequisitions',
                                        {method: 'get',
                                            parameters: {reportdate: reportdate},
                                            onSuccess: function (transport) {
                                                graphs2 = transport.responseText.evalJSON();
                                                graphsjson2 = JSON.stringify(graphs2);
                                                OpenRequistionsChart();
                                            },
                                            onFailure: function () {
                                                alert('Something went wrong while getting graphs...');
                                            }
                                        });
                            },
                            onFailure: function () {
                                alert('Something went wrong while getting the data...');
                            }
                        });

                        new Ajax.Request('getData_Gold_LexisNexisIndia_AgeOfOpenRoles', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                data4 = transport.responseText.evalJSON();
                                datajson4 = JSON.stringify(data4);
                                AgeOfOpenRolesPieChart();
                            },
                            onFailure: function () {
                                alert('Something went wrong while getting the data...');
                            }
                        });
                        new Ajax.Request('getData_Gold_LexisNexisIndia_Trends', {
                            method: 'get',
                            parameters: {reportdate: "2016-07-31"},
                            onSuccess: function (transport) {
                                data5 = transport.responseText.evalJSON();
                                datajson5 = JSON.stringify(data5);

                                new Ajax.Request('getGraph_Gold_LexisNexisIndia_Trends', {
                                    method: 'get',
                                    parameters: {reportdate: "2016-07-31"},
                                    onSuccess: function (transport) {
                                        graphs5 = transport.responseText.evalJSON();
                                        graphsjson5 = JSON.stringify(graphs5);
                                        TrendsChart();

                                    },
                                    onFailure: function () {
                                        alert('Something went wrong while getting graphs...');
                                    }
                                });
                            },
                            onFailure: function () {
                                alert('Something went wrong while getting the data...');
                            }
                        });
                        new Ajax.Request('getData_Gold_LexisNexisIndia_FilledRequisitions', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                data3 = transport.responseText.evalJSON();
                                datajson3 = JSON.stringify(data3);

                                new Ajax.Request('getGraph_Gold_LexisNexisIndia_FilledRequisitions', {
                                    method: 'get',
                                    parameters: {reportdate: reportdate},
                                    onSuccess: function (transport) {
                                        graphs3 = transport.responseText.evalJSON();
                                        graphsjson3 = JSON.stringify(graphs3);
                                        FilledRequisitionsChart();

                                    },
                                    onFailure: function () {
                                        alert('Something went wrong while getting graphs...');
                                    }
                                });
                            },
                            onFailure: function () {
                                alert('Something went wrong while getting the data...');
                            }
                        });

                        var img = new Image();
                        img.src = 'dist/img/np.png';
                        img.onload = function () {
                            var canvas = document.createElement("canvas");
                            canvas.width = this.width;
                            canvas.height = this.height;
                            var ctx = canvas.getContext("2d");
                            ctx.drawImage(this, 0, 0);
                            npIconURL = canvas.toDataURL("image/png");
                        };

                        $('#div1Loading').hide();
                        $('#div2Loading').hide();
                        $('#div3Loading').hide();
                        $('#div4Loading').hide();
                        $('#div5Loading').hide();
                        $('#div6Loading').hide();
                        $('#div7Loading').hide();
                        $('#div8Loading').hide();
                        $('#div9Loading').hide();

                    },
                    onFailure: function () {

                        $('#div1Loading').hide();
                        $('#div2Loading').hide();
                        $('#div3Loading').hide();
                        $('#div4Loading').hide();
                        $('#div5Loading').hide();
                        $('#div6Loading').hide();
                        $('#div7Loading').hide();
                        $('#div8Loading').hide();
                        $('#div9Loading').hide();

                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Report Dates Info</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

            });
        </script>


        <script type = "text/javascript">
            function OpenRolesActivityChart() {
                charts = [];
                chart1 = AmCharts.makeChart("OpenRolesActivityChart",
                        {
                            "type": "serial",
                            "categoryField": "Category",
                            "startDuration": 0,
                            "theme": "light",
                            "categoryAxis": {"gridPosition": "start",
                                "autoWrap": true
                            },
                            "trendLines": [],
                            "graphs": graphs,
                            "guides": [],
                            "valueAxes": [{
                                    "id": "ValueAxis-1",
                                    "stackType": "regular",
                                    "title": "No. Of CVs Sent"
                                }
                            ],
                            "allLabels": [],
                            "balloon": {},
                            "legend": {"useGraphSettings": true
                            },
                            //"titles": [
                            //    {
                            //        "id": "Title-1",
                            //        "text": "Open Roles Activity Graph"
                            //    }
                            //],
                            "dataProvider": data,
                            "export": {"enabled": true
                            }
                        }
                );
                charts.push(chart1);
            }
        </script>

        <script type = "text/javascript">
            function OpenRequistionsChart() {
                chart2 = AmCharts.makeChart("OpenRequisitionsChart",
                        {
                            "type": "serial",
                            "categoryField": "Category",
                            "startDuration": 0,
                            "theme": "light",
                            "columnWidth": 0.3,
                            "categoryAxis": {"gridPosition": "start",
                                "autoWrap": true
                            },
                            "trendLines": [],
                            "graphs": graphs2,
                            "guides": [],
                            "valueAxes": [{
                                    "id": "ValueAxis-1",
                                    "stackType": "regular",
                                    "title": "No. Of Open Roles"
                                }
                            ],
                            "allLabels": [],
                            "balloon": {},
                            "legend": {"useGraphSettings": true
                            },
                            //"titles": [
                            //    {
                            //        "id": "Title-1",
                            //        "text": "Open Requisitions Graph"
                            //   }
                            //],
                            "dataProvider": data2,
                            "export": {"enabled": true
                            }
                        }
                );
                charts.push(chart2);
            }
        </script>

        <script type="text/javascript">
            function AgeOfOpenRolesPieChart() {
                pie1 = AmCharts.makeChart("AgeOfOpenRolesPieChart",
                        {
                            "type": "pie",
                            "theme": "light",
                            "colors": [
                                "#74c374",
                                "#fdd400",
                                "#e86363"
                            ],
                            "balloonText": "[[title]]<br><b>[[value]]</b> ([[percents]]%)",
                            "labelText": "[[title]]: [[value]] ",
                            "titleField": "Category",
                            "valueField": "roles",
                            "allLabels": [],
                            "balloon": {},
                            "legend": {
                                "align": "center",
                                "markerType": "circle"
                            },
                            "titles": [],
                            "dataProvider": data4,
                            "export": {"enabled": true
                            }
                        }
                );

                charts.push(pie1);
            }
        </script>
        <script type = "text/javascript">
            function TrendsChart() {
                chart5 = AmCharts.makeChart("TrendsChart",
                        {
                            "type": "serial",
                            "categoryField": "Category",
                            "startDuration": 0,
                            "theme": "light",
                            "categoryAxis": {"gridPosition": "start",
                                "autoWrap": true
                            },
                            "trendLines": [],
                            "graphs": graphs5,
                            "guides": [],
                            "valueAxes": [{
                                    "id": "ValueAxis-1",
                                    "stackType": "regular",
                                    "title": "Trend"
                                }
                            ],
                            "allLabels": [],
                            "balloon": {},
                            "legend": {"useGraphSettings": true
                            },
                            //"titles": [
                            //    {
                            //        "id": "Title-1",
                            //        "text": "Filled Requisitions Graph"
                            //    }
                            //],
                            "dataProvider": data5,
                            "export": {"enabled": true
                            }
                        }
                );
                charts.push(chart5);
            }
        </script>

        <script type = "text/javascript">
            function FilledRequisitionsChart() {
                chart3 = AmCharts.makeChart("FilledRequisitionsChart",
                        {
                            "type": "serial",
                            "categoryField": "Category",
                            "startDuration": 0,
                            "theme": "light",
                            "categoryAxis": {"gridPosition": "start",
                                "autoWrap": true
                            },
                            "trendLines": [],
                            "graphs": graphs3,
                            "guides": [],
                            "valueAxes": [{
                                    "id": "ValueAxis-1",
                                    "stackType": "regular",
                                    "title": "No. Of Filled Roles"
                                }
                            ],
                            "allLabels": [],
                            "balloon": {},
                            "legend": {"useGraphSettings": true
                            },
                            //"titles": [
                            //    {
                            //        "id": "Title-1",
                            //        "text": "Filled Requisitions Graph"
                            //    }
                            //],
                            "dataProvider": data3,
                            "export": {"enabled": true
                            }
                        }
                );
                charts.push(chart3);
            }
        </script>

        <script>
            $(document).on('click', '#reportDates a', function () {
                $("#div1Loading").show();
                $("#div2Loading").show();
                $("#div3Loading").show();
                $("#div4Loading").show();
                $("#div5Loading").show();
                $('#div6Loading').show();

                reportdate = this.id;
                $("#reportDateLabel").html($(this).text());
                reportfileDate = $(this).text().replace(/\s/g, '');

                new Ajax.Request('getTable_Gold_LexisNexisIndia_WeeklySummary', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#WeeklySummaryTable').dataTable().fnDestroy();
                        $('#WeeklySummaryTable').html(tabledata);
                        $('#WeeklySummaryTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });
                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>Weekly Summary Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable2_Gold_LexisNexisIndia_Tracker', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#Tracker2Table').dataTable().fnDestroy();
                        $('#Tracker2Table').html(tabledata);
                        $('#Tracker2Table').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });
                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Tracker Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable3_Gold_LexisNexisIndia_Tracker', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#Tracker3Table').dataTable().fnDestroy();
                        $('#Tracker3Table').html(tabledata);
                        $('#Tracker3Table').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });
                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Tracker Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable_Gold_LexisNexisIndia_RequisitionsRadar', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#RequisitionsRadarTable').dataTable().fnDestroy();
                        $('#RequisitionsRadarTable').html(tabledata);

                        $('#RequisitionsRadarTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });

                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Requisitions Radar Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable_Gold_LexisNexisIndia_OpenRolesActivity', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#OpenRolesActivityTable').dataTable().fnDestroy();
                        $('#OpenRolesActivityTable').html(tabledata);

                        $('#OpenRolesActivityTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });

                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Open Roles Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable_Gold_LexisNexisIndia_OpenRequisitions', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#OpenRequisitionsTable').dataTable().fnDestroy();
                        $('#OpenRequisitionsTable').html(tabledata);

                        $('#OpenRequisitionsTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });

                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Open Requisitions Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable_Gold_LexisNexisIndia_AgeOfOpenRoles', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#AgeOfOpenRolesPieTable').dataTable().fnDestroy();
                        $('#AgeOfOpenRolesPieTable').html(tabledata);

                        $('#AgeOfOpenRolesPieTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });

                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Age of Open Roles Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });
                new Ajax.Request('getTable_Gold_LexisNexisIndia_Trends', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#TrendsTable').dataTable().fnDestroy();
                        $('#TrendsTable').html(tabledata);
                        $('#TrendsTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });
                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>Trends Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });
                new Ajax.Request('getTable_Gold_LexisNexisIndia_FilledRequisitions', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#FilledRequisitionsTable').dataTable().fnDestroy();
                        $('#FilledRequisitionsTable').html(tabledata);

                        $('#FilledRequisitionsTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });
                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Filled Requisitions Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getTable_Bronze_Yougov_CostPerHire', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        var tabledata = transport.responseText;
                        $('#CostPerHireTable').dataTable().fnDestroy();
                        $('#CostPerHireTable').html(tabledata);


                        $('#CostPerHireTable').dataTable({
                            "bPaginate": false,
                            "bLengthChange": true,
                            "bFilter": false,
                            "bSort": true,
                            "bInfo": false,
                            "bAutoWidth": true,
                            "order": [],
                            "columnDefs": [{
                                    "targets": 'no-sort',
                                    "orderable": false
                                }]
                        });
                    },
                    onFailure: function () {
                        var content = "<b>Snap!</b> Something went wrong while <b>fetching Cost Per Hire Table</b>.";
                        $("#alertModalContent").html(content);
                        $("#alertModal").modal('show');
                    }
                });

                new Ajax.Request('getData_Gold_LexisNexisIndia_OpenRolesActivity', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        data = transport.responseText.evalJSON();
                        datajson = JSON.stringify(data);

                        new Ajax.Request('getGraph_Gold_LexisNexisIndia_OpenRolesActivity',
                                {method: 'get',
                                    parameters: {reportdate: reportdate},
                                    onSuccess: function (transport) {
                                        graphs = transport.responseText.evalJSON();
                                        graphsjson = JSON.stringify(graphs);
                                        OpenRolesActivityChart();
                                    },
                                    onFailure: function () {
                                        alert('Something went wrong while getting graphs...');
                                    }
                                });
                    },
                    onFailure: function () {
                        alert('Something went wrong while getting the data...');
                    }
                });

                new Ajax.Request('getData_Gold_LexisNexisIndia_OpenRequisitions', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        data2 = transport.responseText.evalJSON();
                        datajson2 = JSON.stringify(data2);

                        new Ajax.Request('getGraph_Gold_LexisNexisIndia_OpenRequisitions',
                                {method: 'get',
                                    parameters: {reportdate: reportdate},
                                    onSuccess: function (transport) {
                                        graphs2 = transport.responseText.evalJSON();
                                        graphsjson2 = JSON.stringify(graphs2);
                                        OpenRequistionsChart();
                                    },
                                    onFailure: function () {
                                        alert('Something went wrong while getting graphs...');
                                    }
                                });
                    },
                    onFailure: function () {
                        alert('Something went wrong while getting the data...');
                    }
                });

                new Ajax.Request('getData_Gold_LexisNexisIndia_AgeOfOpenRoles', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        data4 = transport.responseText.evalJSON();
                        datajson4 = JSON.stringify(data4);
                        AgeOfOpenRolesPieChart();
                    },
                    onFailure: function () {
                        alert('Something went wrong while getting the data...');
                    }
                });

                new Ajax.Request('getData_Gold_LexisNexisIndia_FilledRequisitions', {
                    method: 'get',
                    parameters: {reportdate: reportdate},
                    onSuccess: function (transport) {
                        data3 = transport.responseText.evalJSON();
                        datajson3 = JSON.stringify(data3);

                        new Ajax.Request('getGraph_Gold_LexisNexisIndia_FilledRequisitions', {
                            method: 'get',
                            parameters: {reportdate: reportdate},
                            onSuccess: function (transport) {
                                graphs3 = transport.responseText.evalJSON();
                                graphsjson3 = JSON.stringify(graphs3);
                                FilledRequisitionsChart();

                            },
                            onFailure: function () {
                                alert('Something went wrong while getting graphs...');
                            }
                        });
                    },
                    onFailure: function () {
                        alert('Something went wrong while getting the data...');
                    }
                });

            });
        </script>

        <script>
            $("#showOptionsDiv").click(function () {
                if (optionsFlag === 0) {
                    $("#optionsDivMain").slideDown("fast");
                    $("#optionsDivBar").slideDown("fast");
                    $("#optionsIcon").attr('class', 'fa fa-arrow-circle-up');
                    $("#optionsLabel").html("Hide Options");
                    optionsFlag = 1;
                } else {
                    $("#optionsDivMain").slideUp("fast");
                    $("#optionsDivBar").slideUp("fast");
                    $("#optionsIcon").attr('class', 'fa fa-arrow-circle-down');
                    $("#optionsLabel").html("Show Options");
                    optionsFlag = 0;
                }
            });
        </script>

        <script>
            $("#showEmailOptionsDiv").click(function () {
                $("#emailOptionsDiv").slideDown("fast");
                $('html, body').animate({
                    scrollTop: $("#emailOptionsDiv").offset().top - 200
                }, 1000);
            });
        </script>

        <script>
            function hideEmailOptionsDiv() {
                $("#emailOptionsDiv").slideUp("fast");
            }
        </script>
        <script>
            function WIP() {
                var content = "Please be patient, it a <b>Work In Progress</b>. :)";
                $("#alertModalContent").html(content);
                $("#alertModal").modal('show');
            }
        </script>

        <script>
            $('#downloadData').click(function () {
                tableToExcel('TrackerTable', 'name', 'Tracker_NonFilled' + reportfileDate + '.xls');
                tableToExcel('OpenRolesActivityTable', 'name', 'OpenRolesActivity' + reportfileDate + '.xls');
                tableToExcel('OpenRequisitionsTable', 'name', 'OpenRequisitions' + reportfileDate + '.xls');
                tableToExcel('RequisitionsRadarTable', 'name', 'RequisitionsRadar' + reportfileDate + '.xls');
                tableToExcel('AgeOfOpenRolesPieTable', 'name', 'AgeOfOpenRoles' + reportfileDate + '.xls');
                tableToExcel('Tracker2Table', 'name', 'Tracker_Filled' + reportfileDate + '.xls');
                tableToExcel('FilledRequisitionsTable', 'name', 'FilledRequisitions' + reportfileDate + '.xls');
                tableToExcel('Tracker3Table', 'name', 'Tracker_Cancelled' + reportfileDate + '.xls');
                //tableToExcel('CostPerHireTable', 'name', 'CostPerHire' + reportfileDate + '.xls');
            });
        </script>

        <script>
            function downloadReport() {

                $("#modalClose1").hide();
                $("#modalClose2").hide();
                var msg = "<div align=\"center\">Preparing Report for Download..</div><br><div align=\"center\"><i class=\"fa fa-refresh fa-spin fa-2x\"></i></div>";
                $("#alertModalContent").html(msg);
                $("#alertModal").modal('show');

                var content = [];
                var date = $("#reportDateLabel").text();
                content.push({
                    text: '\n\n\n\n\n\n\n\n\n\n\n\n\n\n'
                });
                content.push({
                    "image": npIconURL,
                    alignment: 'center',
                    width: 80,
                    height: 54
                });

                content.push({
                    text: 'EddyStone India Tracker (Gold - Weekly)',
                    style: 'header',
                    alignment: 'center'
                });
                content.push({
                    text: date,
                    style: 'subheader',
                    alignment: 'center'
                });
                content.push({
                    text: '\n\n\n\n\n\n\n',
                    pageBreak: 'after'
                });

                //////////////////////////////////////////Tracker Table 1

                if ($('#TrackerTable').dataTable().fnSettings().aoData.length > 0) {
                    content.push({
                        text: 'Tracker Table - Open Roles',
                        style: 'miniheader',
                        alignment: 'center'
                    });
                    content.push({
                        table: {
                            widths: ['*'],
                            body: [[" "], [" "]]
                        }, layout: {
                            hLineWidth: function (i, node) {
                                return (i === 0 || i === node.table.body.length) ? 0 : 2;
                            },
                            vLineWidth: function (i, node) {
                                return 0;
                            }
                        }});

                    var tablecontent = [];
                    $('#TrackerTable').dataTable().fnSetColumnVis(14, false, false);
                    var simpleHtm = $('#TrackerTable').prop('outerHTML');
                    $('#TrackerTable').dataTable().fnSetColumnVis(14, true, false);
                    ParseHtml(tablecontent, simpleHtm);
                    content.push(tablecontent);

                    content.push({
                        text: '',
                        pageBreak: 'after'
                    });
                }
                ////////////////////////// Open Roles Activity
                chart1.export.capture({}, function () {

                    // GENERATE IMAGE
                    this.toJPG({}, function (data) {
                        if ($('#OpenRolesActivityTable').dataTable().fnSettings().aoData.length > 0) {
                            //HEADING
                            content.push({
                                text: 'Open Roles Activity\n',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]
                                },
                                layout: {
                                    hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    }, vLineWidth: function (i, node) {
                                        return 0;
                                    }}
                            });
                            // ADD IMAGE
                            content.push({
                                "image": data,
                                "fit": [523.28, 769.89]
                            });

                            //ADD TABLE
                            var tablecontent = [];
                            var simpleHtm = $('#OpenRolesActivityTable').prop('outerHTML');
                            ParseHtml2(tablecontent, simpleHtm);
                            content.push(tablecontent);

                            content.push({
                                text: '',
                                pageBreak: 'after'
                            });
                        }
                    });

                });

                //////////////////////////// Open Requisitions
                chart2.export.capture({}, function () {
                    this.toJPG({}, function (data) {
                        if ($('#OpenRequisitionsTable').dataTable().fnSettings().aoData.length > 0) {
                            //HEADING
                            content.push({
                                text: 'Open Requisitions\n',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]
                                },
                                layout: {
                                    hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    },
                                    vLineWidth: function (i, node) {
                                        return 0;
                                    }
                                }
                            });
                            // ADD IMAGE
                            content.push({
                                "image": data,
                                "fit": [523.28, 769.89]
                            });

                            // ADD TABLE
                            var tablecontent = [];
                            var simpleHtm = $('#OpenRequisitionsTable').prop('outerHTML');
                            ParseHtml2(tablecontent, simpleHtm);
                            content.push(tablecontent);

                            content.push({text: '',
                                pageBreak: 'after'
                            });
                        }
                    });

                    //////////////////////////////////////Requisitions Radar
                    if ($('#RequisitionsRadarTable').dataTable().fnSettings().aoData.length > 0) {
                        content.push({
                            text: 'Requisitions Radar',
                            style: 'miniheader',
                            alignment: 'center'
                        });

                        content.push({
                            table: {
                                widths: ['*'],
                                body: [[" "], [" "]]},
                            layout: {hLineWidth: function (i, node) {
                                    return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                },
                                vLineWidth: function (i, node) {
                                    return 0;
                                }
                            }
                        });

                        var tablecontent = [];
                        var simpleHtm = $('#RequisitionsRadarTable').prop('outerHTML');
                        ParseHtml(tablecontent, simpleHtm);
                        content.push(tablecontent);

                        content.push({
                            text: '',
                            pageBreak: 'after'
                        });
                    }

                });

                //////////////////////////// Age Of Open Roles
                pie1.export.capture({}, function () {
                    this.toJPG({}, function (data) {
                        if ($('#AgeOfOpenRolesPieTable').dataTable().fnSettings().aoData.length > 0) {
                            //HEADING
                            content.push({
                                text: 'Age Of Open Roles\n',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]
                                },
                                layout: {
                                    hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    },
                                    vLineWidth: function (i, node) {
                                        return 0;
                                    }
                                }
                            });
                            // ADD IMAGE
                            content.push({
                                "image": data,
                                "fit": [523.28, 769.89]
                            });

                            // ADD TABLE
                            var tablecontent = [];
                            var simpleHtm = $('#AgeOfOpenRolesPieTable').prop('outerHTML');
                            ParseHtml2(tablecontent, simpleHtm);
                            content.push(tablecontent);

                            content.push({text: '',
                                pageBreak: 'after'
                            });
                        }

                        //////////////////////////////////////Tracker Table 2
                        if ($('#Tracker2Table').dataTable().fnSettings().aoData.length > 0) {
                            content.push({
                                text: 'Tracker Table - Filled Roles',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]},
                                layout: {hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    },
                                    vLineWidth: function (i, node) {
                                        return 0;
                                    }
                                }
                            });

                            var tablecontent = [];
                            $('#Tracker2Table').dataTable().fnSetColumnVis(14, false, false);
                            var simpleHtm = $('#Tracker2Table').prop('outerHTML');
                            $('#Tracker2Table').dataTable().fnSetColumnVis(14, true, false);
                            ParseHtml(tablecontent, simpleHtm);
                            content.push(tablecontent);

                            content.push({
                                text: '',
                                pageBreak: 'after'
                            });
                        }
                    });

                });

                ///////////////////////// Filled Requisitions
                chart3.export.capture({}, function () {
                    // GENERATE IMAGE
                    this.toJPG({}, function (data) {
                        if ($('#FilledRequisitionsTable').dataTable().fnSettings().aoData.length > 0) {
                            //HEADING
                            content.push({text: 'Filled Requisitions\n',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]
                                },
                                layout: {hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    },
                                    vLineWidth: function (i, node) {
                                        return 0;
                                    }
                                }
                            });
                            // ADD IMAGE
                            content.push({
                                "image": data,
                                "fit": [523.28, 769.89]
                            });

                            // ADD TABLE
                            var tablecontent = [];
                            var simpleHtm = $('#FilledRequisitionsTable').prop('outerHTML');
                            ParseHtml2(tablecontent, simpleHtm);
                            content.push(tablecontent);

                            content.push({
                                text: '',
                                pageBreak: 'after'
                            });
                        }
                        //////////////////////////////////////Tracker Table 2

                        if ($('#Tracker3Table').dataTable().fnSettings().aoData.length > 0) {
                            content.push({
                                text: 'Tracker Table - Cancelled/On Hold Roles',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]
                                },
                                layout: {
                                    hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    },
                                    vLineWidth: function (i, node) {
                                        return 0;
                                    }
                                }
                            });


                            var tablecontent = [];
                            $('#Tracker3Table').dataTable().fnSetColumnVis(14, false, false);
                            var simpleHtm = $('#Tracker3Table').prop('outerHTML');
                            $('#Tracker3Table').dataTable().fnSetColumnVis(14, true, false);
                            ParseHtml(tablecontent, simpleHtm);
                            content.push(tablecontent);
                        }
                        // all done - construct PDF
                        chart3.export.toPDF({
                            content: content,
                            styles: {
                                header: {
                                    fontSize: 22, bold: true,
                                    alignment: 'justify'
                                },
                                subheader: {fontSize: 18, bold: true
                                },
                                miniheader: {
                                    fontSize: 16, bold: true
                                },
                                tableFont: {
                                    fontSize: 13
                                }
                            }
                            //pageMargins: [40, 100, 40, 50]
                        }, function (data) {

                            this.download(data, "application/pdf", "LNIndia_Weekly-Gold_Tracker" + reportfileDate + ".pdf");

                            $("#alertModal").modal('hide');
                            $("#modalClose1").show();
                            $("#modalClose2").show();

                        });
                    });
                });

            }
        </script>

        <script>
            function emailReport() {

                $('html, body').animate({
                    scrollTop: $("#emailLoading").offset().top - 200
                }, 1000);

                // iterate through all of the charts and prepare their images for export
                var emailFlag = 0;
                var emailList = $("#emailList").val();
                var emailMessage = CKEDITOR.instances['emailMessage'].getData();
                if (emailList.length <= 0) {
                    $("#emailLoading").hide();
                    var content1 = "Oops! No E-mail Addresses Entered!";
                    $("#alertModalContent").html(content1);
                    $("#alertModal").modal('show');
                    emailFlag = 1;

                }
                else {
                    var emailArray = emailList.split(',');
                    var re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
                    for (var i = 0; i < emailArray.length; i++) {
                        if (re.test(emailArray[i]) === true) {
                            //Valid Email.
                        }
                        else {
                            $("#emailLoading").hide();
                            var content1 = "Oops! Invalid email address format: <b>" + emailArray[i] + "</b>";
                            $("#alertModalContent").html(content1);
                            $("#alertModal").modal('show');
                            emailFlag = 1;
                        }
                    }
                }
                if (emailFlag === 0) {

                    $("#modalClose1").hide();
                    $("#modalClose2").hide();
                    var msg = "<div align=\"center\">Sending Report to the E-mail Address(es)..</div><br><div align=\"center\"><i class=\"fa fa-refresh fa-spin fa-2x\"></i></div>";
                    $("#alertModalContent").html(msg);
                    $("#alertModal").modal('show');
                    $("#emailLoading").show();

                    var content = [];
                    var date = $("#reportDateLabel").text();
                    content.push({
                        text: '\n\n\n\n\n\n\n\n\n\n\n\n\n\n'
                    });
                    content.push({
                        "image": npIconURL,
                        alignment: 'center',
                        width: 80,
                        height: 54
                    });

                    content.push({
                        text: 'EddyStone Tracker (Gold - Weekly)',
                        style: 'header',
                        alignment: 'center'
                    });
                    content.push({
                        text: date,
                        style: 'subheader',
                        alignment: 'center'
                    });
                    content.push({
                        text: '\n\n\n\n\n\n\n',
                        pageBreak: 'after'
                    });

                    //////////////////////////////////////////Tracker Table 1

                    if ($('#TrackerTable').dataTable().fnSettings().aoData.length > 0) {
                        content.push({
                            text: 'Tracker Table - Open Roles',
                            style: 'miniheader',
                            alignment: 'center'
                        });
                        content.push({
                            table: {
                                widths: ['*'],
                                body: [[" "], [" "]]
                            }, layout: {
                                hLineWidth: function (i, node) {
                                    return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                },
                                vLineWidth: function (i, node) {
                                    return 0;
                                }
                            }});

                        var tablecontent = [];
                        $('#TrackerTable').dataTable().fnSetColumnVis(14, false, false);
                        var simpleHtm = $('#TrackerTable').prop('outerHTML');
                        $('#TrackerTable').dataTable().fnSetColumnVis(14, true, false);
                        ParseHtml(tablecontent, simpleHtm);
                        content.push(tablecontent);

                        content.push({
                            text: '',
                            pageBreak: 'after'
                        });
                    }
                    ////////////////////////// Open Roles Activity
                    chart1.export.capture({}, function () {

                        // GENERATE IMAGE
                        this.toJPG({}, function (data) {
                            if ($('#OpenRolesActivityTable').dataTable().fnSettings().aoData.length > 0) {
                                //HEADING
                                content.push({
                                    text: 'Open Roles Activity\n',
                                    style: 'miniheader',
                                    alignment: 'center'
                                });

                                content.push({
                                    table: {
                                        widths: ['*'],
                                        body: [[" "], [" "]]
                                    },
                                    layout: {
                                        hLineWidth: function (i, node) {
                                            return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                        }, vLineWidth: function (i, node) {
                                            return 0;
                                        }}
                                });
                                // ADD IMAGE
                                content.push({
                                    "image": data,
                                    "fit": [523.28, 769.89]
                                });

                                //ADD TABLE
                                var tablecontent = [];
                                var simpleHtm = $('#OpenRolesActivityTable').prop('outerHTML');
                                ParseHtml2(tablecontent, simpleHtm);
                                content.push(tablecontent);

                                content.push({
                                    text: '',
                                    pageBreak: 'after'
                                });
                            }
                        });

                    });

                    //////////////////////////// Open Requisitions
                    chart2.export.capture({}, function () {
                        this.toJPG({}, function (data) {
                            if ($('#OpenRequisitionsTable').dataTable().fnSettings().aoData.length > 0) {
                                //HEADING
                                content.push({
                                    text: 'Open Requisitions\n',
                                    style: 'miniheader',
                                    alignment: 'center'
                                });

                                content.push({
                                    table: {
                                        widths: ['*'],
                                        body: [[" "], [" "]]
                                    },
                                    layout: {
                                        hLineWidth: function (i, node) {
                                            return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                        },
                                        vLineWidth: function (i, node) {
                                            return 0;
                                        }
                                    }
                                });
                                // ADD IMAGE
                                content.push({
                                    "image": data,
                                    "fit": [523.28, 769.89]
                                });

                                // ADD TABLE
                                var tablecontent = [];
                                var simpleHtm = $('#OpenRequisitionsTable').prop('outerHTML');
                                ParseHtml2(tablecontent, simpleHtm);
                                content.push(tablecontent);

                                content.push({text: '',
                                    pageBreak: 'after'
                                });
                            }
                        });

                        //////////////////////////////////////Requisitions Radar
                        if ($('#RequisitionsRadarTable').dataTable().fnSettings().aoData.length > 0) {
                            content.push({
                                text: 'Requisitions Radar',
                                style: 'miniheader',
                                alignment: 'center'
                            });

                            content.push({
                                table: {
                                    widths: ['*'],
                                    body: [[" "], [" "]]},
                                layout: {hLineWidth: function (i, node) {
                                        return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                    },
                                    vLineWidth: function (i, node) {
                                        return 0;
                                    }
                                }
                            });

                            var tablecontent = [];
                            var simpleHtm = $('#RequisitionsRadarTable').prop('outerHTML');
                            ParseHtml(tablecontent, simpleHtm);
                            content.push(tablecontent);

                            content.push({
                                text: '',
                                pageBreak: 'after'
                            });
                        }

                    });

                    //////////////////////////// Age Of Open Roles
                    pie1.export.capture({}, function () {
                        this.toJPG({}, function (data) {
                            if ($('#AgeOfOpenRolesPieTable').dataTable().fnSettings().aoData.length > 0) {
                                //HEADING
                                content.push({
                                    text: 'Age Of Open Roles\n',
                                    style: 'miniheader',
                                    alignment: 'center'
                                });

                                content.push({
                                    table: {
                                        widths: ['*'],
                                        body: [[" "], [" "]]
                                    },
                                    layout: {
                                        hLineWidth: function (i, node) {
                                            return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                        },
                                        vLineWidth: function (i, node) {
                                            return 0;
                                        }
                                    }
                                });
                                // ADD IMAGE
                                content.push({
                                    "image": data,
                                    "fit": [523.28, 769.89]
                                });

                                // ADD TABLE
                                var tablecontent = [];
                                var simpleHtm = $('#AgeOfOpenRolesPieTable').prop('outerHTML');
                                ParseHtml2(tablecontent, simpleHtm);
                                content.push(tablecontent);

                                content.push({text: '',
                                    pageBreak: 'after'
                                });
                            }

                            //////////////////////////////////////Tracker Table 2
                            if ($('#Tracker2Table').dataTable().fnSettings().aoData.length > 0) {
                                content.push({
                                    text: 'Tracker Table - Filled Roles',
                                    style: 'miniheader',
                                    alignment: 'center'
                                });

                                content.push({
                                    table: {
                                        widths: ['*'],
                                        body: [[" "], [" "]]},
                                    layout: {hLineWidth: function (i, node) {
                                            return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                        },
                                        vLineWidth: function (i, node) {
                                            return 0;
                                        }
                                    }
                                });

                                var tablecontent = [];
                                $('#Tracker2Table').dataTable().fnSetColumnVis(14, false, false);
                                var simpleHtm = $('#Tracker2Table').prop('outerHTML');
                                $('#Tracker2Table').dataTable().fnSetColumnVis(14, true, false);
                                ParseHtml(tablecontent, simpleHtm);
                                content.push(tablecontent);

                                content.push({
                                    text: '',
                                    pageBreak: 'after'
                                });
                            }
                        });

                    });

                    ///////////////////////// Filled Requisitions
                    chart3.export.capture({}, function () {
                        // GENERATE IMAGE
                        this.toJPG({}, function (data) {
                            if ($('#FilledRequisitionsTable').dataTable().fnSettings().aoData.length > 0) {
                                //HEADING
                                content.push({text: 'Filled Requisitions\n',
                                    style: 'miniheader',
                                    alignment: 'center'
                                });

                                content.push({
                                    table: {
                                        widths: ['*'],
                                        body: [[" "], [" "]]
                                    },
                                    layout: {hLineWidth: function (i, node) {
                                            return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                        },
                                        vLineWidth: function (i, node) {
                                            return 0;
                                        }
                                    }
                                });
                                // ADD IMAGE
                                content.push({
                                    "image": data,
                                    "fit": [523.28, 769.89]
                                });

                                // ADD TABLE
                                var tablecontent = [];
                                var simpleHtm = $('#FilledRequisitionsTable').prop('outerHTML');
                                ParseHtml2(tablecontent, simpleHtm);
                                content.push(tablecontent);

                                content.push({
                                    text: '',
                                    pageBreak: 'after'
                                });
                            }
                            //////////////////////////////////////Tracker Table 2

                            if ($('#Tracker3Table').dataTable().fnSettings().aoData.length > 0) {
                                content.push({
                                    text: 'Tracker Table - Cancelled/On Hold Roles',
                                    style: 'miniheader',
                                    alignment: 'center'
                                });

                                content.push({
                                    table: {
                                        widths: ['*'],
                                        body: [[" "], [" "]]
                                    },
                                    layout: {
                                        hLineWidth: function (i, node) {
                                            return (i === 0 || i === node.table.body.length) ? 0 : 2;
                                        },
                                        vLineWidth: function (i, node) {
                                            return 0;
                                        }
                                    }
                                });


                                var tablecontent = [];
                                $('#Tracker3Table').dataTable().fnSetColumnVis(14, false, false);
                                var simpleHtm = $('#Tracker3Table').prop('outerHTML');
                                $('#Tracker3Table').dataTable().fnSetColumnVis(14, true, false);
                                ParseHtml(tablecontent, simpleHtm);
                                content.push(tablecontent);
                            }
                            // all done - construct PDF
                            chart3.export.toPDF({
                                content: content,
                                styles: {
                                    header: {
                                        fontSize: 22, bold: true,
                                        alignment: 'justify'
                                    },
                                    subheader: {fontSize: 18, bold: true
                                    },
                                    miniheader: {
                                        fontSize: 16, bold: true
                                    },
                                    tableFont: {
                                        fontSize: 13
                                    }
                                }
                                //pageMargins: [40, 100, 40, 50]
                            }, function (data) {

                                new Ajax.Request('mailPDF_Bronze_Yougov',
                                        {method: 'post',
                                            parameters: {pdfBase64: data, reportdate: reportfileDate, emailList: emailList, emailMessage: emailMessage},
                                            onSuccess: function () {

                                                var content = "<b>Success!</b> Mail(s) sent successfully. :)";
                                                $("#alertModalContent").html(content);
                                                $("#modalClose1").show();
                                                $("#modalClose2").show();

                                                $("#emailLoading").hide();
                                                $("#emailOptionsDiv").slideUp("fast");
                                            },
                                            onFailure: function () {
                                                alert('Something went wrong.');
                                            }
                                        });

                            });
                        });
                    });

                }
            }
        </script>

    </body>
</html>
