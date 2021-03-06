<%-- 
    Document   : Demo_Gold_Yearly
    Created on : 7 Sep, 2015, 11:21:06 AM
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
        <title>REDHOLT  | MICROSOFT Annual Report</title>
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
                <a href="/" onclick="event.preventDefault();" class="logo">
                    
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
                                            <%=username%> - REDHOLT
                                            <small>MICROSOFT (Gold - Annual)</small>
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
                        <li class="active"> MICROSOFT Annual</li>
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
                                <!--(<font style="font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif" id="reportDateLabel"></font>)-->
                                (<font style="font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif">Year: 2016</font>)
                                &nbsp;
                                <img src="dist/img/goldIcon.png" data-original-title="Gold - Annual" data-toggle="tooltip" style="padding-bottom: 0.2%"/>
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
                                    <icon class="fa fa-bar-chart"></icon>
                                    <h3 class="box-title"><b>Monthly Hires</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" align="center">
                                        <div class="col-lg-12">
                                            <div id="MonthlyHiresChart" style="width: 60%; height: 400px; background-color: #FFFFFF;" ></div>
                                        </div><!-- /.col-lg-6 -->
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8" id="MonthlyHiresTableDiv">
                                            <br><br><br><br>
                                            <table id="MonthlyHiresTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                                <thead>
                                                    <tr style="background-color: #EEEEEE">
                                                        <th style="text-align: center">Month</th>
                                                        <th style="text-align: center">Nick</th>
                                                        <th style="text-align: center">Will</th>
                                                        <th style="text-align: center">Alex</th>
                                                        <th style="text-align: center">Ray</th>
                                                        <th style="text-align: center">Grand Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Jan</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>3</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Feb</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>6</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Mar</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>5</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Apr</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>4</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>5</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>9</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>May</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>2</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Jun</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>5</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Jul</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>4</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Aug</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>3</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Sep</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>5</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>5</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Oct</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>5</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>8</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Nov</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>3</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Dec</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>6</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                        <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>7</b></span></td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr class="warning">
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Grand Total</b></span></td>
                                                        <td align="center"><span class="badge"><b>14</b></span></td>
                                                        <td align="center"><span class="badge"><b>19</b></span></td>
                                                        <td align="center"><span class="badge"><b>6</b></span></td>
                                                        <td align="center"><span class="badge"><b>21</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>60</b></span></td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                            <br>
                                        </div><!-- /.col-lg-8 -->
                                        <div class="col-lg-2"></div>
                                    </div>
                                </div><!-- /.box-body -->          
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div2Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-bar-chart"></icon>
                                    <h3 class="box-title"><b>Time To Fill</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" align="center">
                                        <div class="col-lg-12">
                                            <div id="TimeToFillChart" style="width: 60%; height: 400px; background-color: #FFFFFF;" ></div>
                                        </div><!-- /.col-lg-6 -->
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8" id="TimeToFillChartTableDiv">
                                            <br><br><br><br>
                                            <table id="TimeToFillChartTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                                <thead>
                                                    <tr style="background-color: #EEEEEE">
                                                        <th style="text-align: center">Month</th>
                                                        <th style="text-align: center">Time To Offer (Days)</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Jan</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>43</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Feb</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>25</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Mar</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>9</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Apr</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>13</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>May</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>72</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Jun</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>24</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Jul</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>43</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Aug</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>41</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Sep</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>68</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Oct</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>28</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Nov</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>29</b></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Dec</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>34</b></span></td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr class="warning">
                                                        <td align="center" class="warning"><span class="info-box-text"><b>Average</b></span></td>
                                                        <td align="center" class="warning"><span class="badge"><b>35</b></span></td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                            <br>
                                        </div><!-- /.col-lg-8 -->
                                        <div class="col-lg-2"></div>
                                    </div>
                                </div><!-- /.box-body -->          
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div2Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-table"></icon>
                                    <h3 class="box-title"><b>Cost Per Hire</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" id="CostPerHireTableDiv" style="overflow:auto;">
                                        <br>
                                        <table id="CostPerHireTable" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            <thead>
                                                <tr style="background-color: #EEEEEE">
                                                    <th style="text-align: center"># of Hires</th>
                                                    <th style="text-align: center">Average of Salary</th>
                                                    <th style="text-align: center">Total Fee</th>
                                                    <th style="text-align: center">Retainer</th>
                                                    <th style="text-align: center">Cost per Hire</th>
                                                    <th style="text-align: center">% Fee / Placement</th>
                                                </tr>
                                            </thead>
                                            <tbody> 
                                                <tr>
                                                    <td align="center"><span class="info-box-text"><b>60</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>�42,050</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>�60,000</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>�100,000</b></span></td>
                                                    <td align="center"><span class="badge"><font style="font-size: 13px">�2,667</font></span></td>
                                                    <td align="center"><span class="info-box-text"><b>6%</b></span></td>
                                                </tr>
                                            </tbody>
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

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-table"></icon>
                                    <h3 class="box-title"><b>Hires Vs Demand</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <br>
                                    <div class="col-lg-12" id="CostPerHireTableDiv" style="overflow:auto;">
                                        <br>
                                        <table id="CostPerHireTable" align="center" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            <thead>
                                                <tr style="background-color: #EEEEEE">
                                                    <th style="text-align: center">SoW Start Date</th>
                                                    <th style="text-align: center">Total Demand Plan in SoW</th>
                                                    <th style="text-align: center">Monthly Demand Plan (SoW forecast/duration of deal in months)</th>
                                                    <th style="text-align: center">Target YTD</th>
                                                    <th style="text-align: center">Current Total Open Roles</th>
                                                    <th style="text-align: center"># Roles Filled YTD</th>
                                                    <th style="text-align: center">SoW Demand Plan VS Roles Filled (%) YTD</th>
                                                    <th style="text-align: center">SoW Team Size</th>
                                                    <th style="text-align: center">Actual Team Size</th>
                                                    <th style="text-align: center">Vacancies Per Recruiter</th>
                                                    <th style="text-align: center">Team Variance</th>
                                                    <th style="text-align: center">Ahead / On Target / Behind on SoW?</th>
                                                    <th style="text-align: center">Variance</th>
                                                </tr>
                                            </thead>
                                            <tbody> 
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>01-Jan-2016</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>50</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>4.17</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>50</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>25</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>60</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>120%</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>4</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>4</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>6.25</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>0</b></span></td>
                                                    <td align="center" class="success"><span class="info-box-text"><b>Ahead</b></span></td>
                                                    <td align="center" class="success"><span class="info-box-text"><b>10</b></span></td>
                                                </tr>
                                            </tbody>
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
                                    <icon class="fa fa-pie-chart"></icon>
                                    <h3 class="box-title"><b>Source Of Hires</b>
                                    </h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding" align="center">
                                    <br>
                                    <div class="col-lg-4">
                                        <div id="SourceOfHiresPieChart" style="width: 100%; height: 500px; background-color: #FFFFFF;" >
                                        </div>
                                    </div><!-- /.col-lg-6 -->
                                    <div class="col-lg-8" id="SourceOfHiresPieTableDiv">
                                        <br><br><br>
                                        <table id="SourceOfHiresPieTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            <thead>
                                                <tr style="background-color: #EEEEEE">
                                                    <th style="text-align: center">Source of Hires</th>
                                                    <th style="text-align: center">Jan</th>
                                                    <th style="text-align: center">Feb</th>
                                                    <th style="text-align: center">Mar</th>
                                                    <th style="text-align: center">Apr</th>
                                                    <th style="text-align: center">May</th>
                                                    <th style="text-align: center">Jun</th>
                                                    <th style="text-align: center">Jul</th>
                                                    <th style="text-align: center">Aug</th>
                                                    <th style="text-align: center">Sep</th>
                                                    <th style="text-align: center">Oct</th>
                                                    <th style="text-align: center">Nov</th>
                                                    <th style="text-align: center">Dec</th>
                                                    <th style="text-align: center">Grand Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Advert</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>4</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>7</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>23</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Referral</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>3</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>7</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Vsource</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>3</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Agency</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>2</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Social Media</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>5</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>7</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>5</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>2</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>22</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>SourceEdge</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>3</b></span></td>
                                                </tr>
                                            </tbody>
                                            <tfoot>
                                                <tr class="danger">
                                                    <td align="center"><span class="text-bold"><font style="font-size: 16px">Cancellation Rate : 6%</font></span></td>
                                                </tr>
                                            </tfoot>
                                            <tfoot>
                                                <tr class="warning">
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Grand Total</b></span></td>
                                                    <td align="center"><span class="badge"><b>3</b></span></td>
                                                    <td align="center"><span class="badge"><b>6</b></span></td>
                                                    <td align="center"><span class="badge"><b>5</b></span></td>
                                                    <td align="center"><span class="badge"><b>9</b></span></td>
                                                    <td align="center"><span class="badge"><b>2</b></span></td>
                                                    <td align="center"><span class="badge"><b>5</b></span></td>
                                                    <td align="center"><span class="badge"><b>4</b></span></td>
                                                    <td align="center"><span class="badge"><b>3</b></span></td>
                                                    <td align="center"><span class="badge"><b>5</b></span></td>
                                                    <td align="center"><span class="badge"><b>8</b></span></td>
                                                    <td align="center"><span class="badge"><b>3</b></span></td>
                                                    <td align="center"><span class="badge"><b>7</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>60</b></span></td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-6 -->
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div3Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

                        <section class="col-lg-12 connectedSortable">
                            <div class="box box-default box-solid" style="border: solid #243038">
                                <div class="box-header" style="background-color: #243038; color: #EEEEEE"><!--  style="background-color: #434b56" -->
                                    <icon class="fa fa-pie-chart"></icon>
                                    <h3 class="box-title"><b>Cancellations</b></h3>
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus" style="color: #EEEEEE"></i></button>
                                    </div>
                                </div><!-- /.box-header -->
                                <div class="box-body no-padding" align="center">
                                    <br>
                                    <div class="col-lg-6">
                                        <div id="CancellationsPieChart" style="width: 100%; height: 500px; background-color: #FFFFFF;" >
                                        </div>
                                    </div><!-- /.col-lg-6 -->
                                    <div class="col-lg-6" id="CancellationsPieTableDiv">
                                        <br><br><br>
                                        <table id="CancellationsPieTable" class="table table-bordered table-hover" style="background-color: #FFFFFF">
                                            <thead>
                                                <tr style="background-color: #EEEEEE">
                                                    <th style="text-align: center">Reason For Cancellations</th>
                                                    <th style="text-align: center">Jan</th>
                                                    <th style="text-align: center">Feb</th>
                                                    <th style="text-align: center">Mar</th>
                                                    <th style="text-align: center">Apr</th>
                                                    <th style="text-align: center">May</th>
                                                    <th style="text-align: center">Jul</th>
                                                    <th style="text-align: center">Grand Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Change In Business Needs</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>2</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Duplicate</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>1</b></span></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Filled Internally</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>--</b></span></td>
                                                    <td align="center"><span class="info-box-text"><b>1</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>4</b></span></td>
                                                </tr>
                                            </tbody>
                                            <tfoot>
                                                <tr class="warning">
                                                    <td align="center" class="warning"><span class="info-box-text"><b>Grand Total</b></span></td>
                                                    <td align="center"><span class="badge"><b>1</b></span></td>
                                                    <td align="center"><span class="badge"><b>2</b></span></td>
                                                    <td align="center"><span class="badge"><b>1</b></span></td>
                                                    <td align="center"><span class="badge"><b>1</b></span></td>
                                                    <td align="center"><span class="badge"><b>1</b></span></td>
                                                    <td align="center"><span class="badge"><b>1</b></span></td>
                                                    <td align="center" class="warning"><span class="badge"><b>7</b></span></td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                        <br>
                                    </div><!-- /.col-lg-6 -->
                                </div><!-- /.box-body -->
                                <!-- Loading (remove the following to stop the loading)-->
                                <div class="overlay" id="div5Loading" hidden>
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                            </div><!-- /.box -->
                        </section>

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
                                            })();</script>
        <!-- jQuery UI 1.11.2 -->
        <script src="https://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
                                            $.widget.bridge('uibutton', $.ui.button);</script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
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
                                            $.fn.dataTable.moment('MMM');</script>
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
                                                        if (!obj.isTouchMode() && level > 2) {
                                                            // flag it so that we don't cancel the event on activate.smapi
                                                            $item.dataSM('arrow-activated', true);
                                                            $mainMenu.smartmenus('itemActivate', $item);
                                                        }
                                                    });</script>

        <script>
            var optionsFlag = 1;
            var reportfileDate = "";
            var reportdate = "";
            var charts = [];
            var chart1, chart2, chart3;
            var npIconURL;</script>

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

                //reportfileDate = $('[name=lastReportDate]').text().replace(/ /g, '');
                //reportdate = $('[name=lastReportDate]').attr('id');
                reportfileDate = '2016';
                //reportdate = '1992-03-20';
                $("#reportDateLabel").html("2016");
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

                $('#MonthlyHiresTable').dataTable({
                    "bPaginate": false,
                    "bLengthChange": true,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": false, "bAutoWidth": true,
                    "order": [],
                    "columnDefs": [{
                            "targets": 'no-sort',
                            "orderable": false
                        }]
                });
                $('#SourceOfHiresPieTable').dataTable({
                    "bPaginate": false,
                    "bLengthChange": true,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": false, "bAutoWidth": true,
                    "order": [],
                    "columnDefs": [{
                            "targets": 'no-sort',
                            "orderable": false
                        }]
                });
                $('#CostPerHireTable').dataTable({
                    "bPaginate": false,
                    "bLengthChange": true,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": false, "bAutoWidth": true,
                    "order": [],
                    "columnDefs": [{
                            "targets": 'no-sort',
                            "orderable": false
                        }]
                });
                $('#CancellationsPieTable').dataTable({
                    "bPaginate": false,
                    "bLengthChange": true,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": false, "bAutoWidth": true,
                    "order": [],
                    "columnDefs": [{
                            "targets": 'no-sort',
                            "orderable": false
                        }]
                });
                var img = new Image();
                img.src = 'dist/img/np.png';
                img.onload = function () {
                    var canvas = document.createElement("canvas");
                    ctx.drawImage(this, 0, 0);
                    npIconURL = canvas.toDataURL("image/png");
                };
                canvas.width = this.width;
                canvas.height = this.height;
                var ctx = canvas.getContext("2d");

            });
        </script>

        <script type="text/javascript">
            AmCharts.makeChart("MonthlyHiresChart",
                    {
                        "type": "serial",
                        "categoryField": "category",
                        "startDuration": 1,
                        "theme": "light",
                        "categoryAxis": {
                            "gridPosition": "start",
                            "autoWrap": true
                        },
                        "trendLines": [],
                        "graphs": [
                            {
                                "balloonText": "[[title]] of [[category]]:[[value]]",
                                "fillAlphas": 1,
                                "id": "AmGraph-1",
                                "labelText": "[[value]]",
                                "fontSize": "16",
                                "title": "Nick",
                                "type": "column",
                                "valueField": "column-1"
                            },
                            {
                                "balloonText": "[[title]] of [[category]]:[[value]]",
                                "fillAlphas": 1,
                                "id": "AmGraph-2",
                                "labelText": "[[value]]",
                                "fontSize": "16",
                                "title": "Will",
                                "type": "column",
                                "valueField": "column-2"
                            },
                            {
                                "balloonText": "[[title]] of [[category]]:[[value]]",
                                "fillAlphas": 1,
                                "id": "AmGraph-3",
                                "labelText": "[[value]]",
                                "fontSize": "16",
                                "title": "Alex",
                                "type": "column",
                                "valueField": "column-3"
                            },
                            {
                                "balloonText": "[[title]] of [[category]]:[[value]]",
                                "fillAlphas": 1,
                                "id": "AmGraph-4",
                                "labelText": "[[value]]",
                                "fontSize": "16",
                                "title": "Ray",
                                "type": "column",
                                "valueField": "column-4"
                            }
                        ],
                        "guides": [],
                        "valueAxes": [
                            {
                                "id": "ValueAxis-1",
                                "stackType": "regular",
                                "title": "No. of Hires"
                            }
                        ], "allLabels": [],
                        "balloon": {},
                        "legend": {
                            "useGraphSettings": true
                        },
                        "dataProvider": [
                            {
                                "category": "Jan",
                                "column-1": 0,
                                "column-2": 1,
                                "column-3": 0,
                                "column-4": 2
                            },
                            {
                                "category": "Feb",
                                "column-1": 2,
                                "column-2": 2,
                                "column-3": 0,
                                "column-4": 2
                            },
                            {
                                "category": "Mar",
                                "column-1": 3,
                                "column-2": 2,
                                "column-3": 0,
                                "column-4": 0
                            },
                            {
                                "category": "Apr",
                                "column-1": 4,
                                "column-2": 5,
                                "column-3": 0,
                                "column-4": 0
                            },
                            {
                                "category": "May",
                                "column-1": 0,
                                "column-2": 0,
                                "column-3": 1,
                                "column-4": 1
                            },
                            {
                                "category": "Jun",
                                "column-1": 3,
                                "column-2": 1,
                                "column-3": 0,
                                "column-4": 1
                            },
                            {
                                "category": "Jul",
                                "column-1": 2,
                                "column-2": 2,
                                "column-3": 0,
                                "column-4": 0
                            },
                            {
                                "category": "Aug",
                                "column-1": 0,
                                "column-2": 0,
                                "column-3": 0,
                                "column-4": 3
                            },
                            {
                                "category": "Sep",
                                "column-1": 0,
                                "column-2": 0,
                                "column-3": 0,
                                "column-4": 5
                            },
                            {
                                "category": "Oct",
                                "column-1": 0,
                                "column-2": 0,
                                "column-3": 5,
                                "column-4": 3
                            },
                            {
                                "category": "Nov",
                                "column-1": 0,
                                "column-2": 0,
                                "column-3": 0,
                                "column-4": 3
                            },
                            {
                                "category": "Dec",
                                "column-1": 0,
                                "column-2": 6,
                                "column-3": 0,
                                "column-4": 1
                            }
                        ],
                        "export": {"enabled": true
                        }
                    }
            );</script>

        <script type="text/javascript">
            AmCharts.makeChart("TimeToFillChart",
                    {
                        "type": "serial",
                        "categoryField": "category",
                        "startDuration": 1,
                        "export": {
                            "enabled": true
                        },
                        "theme": "light",
                        "categoryAxis": {
                            "autoWrap": true,
                            "gridPosition": "start"
                        },
                        "trendLines": [],
                        "graphs": [
                            {
                                "balloonText": "[[title]] of [[category]]:[[value]]",
                                "fillAlphas": 1,
                                "fontSize": "16",
                                "id": "AmGraph-1",
                                "labelText": "[[value]]",
                                "title": "Time To Offer",
                                "type": "column",
                                "valueField": "column-1"
                            }
                        ],
                        "guides": [],
                        "valueAxes": [
                            {
                                "id": "ValueAxis-1",
                                "stackType": "regular",
                                "title": "Time To Offer (Days)"
                            }
                        ],
                        "allLabels": [],
                        "balloon": {},
                        "titles": [],
                        "dataProvider": [
                            {
                                "category": "Jan",
                                "column-1": "43"
                            },
                            {
                                "category": "Feb",
                                "column-1": "25"
                            },
                            {
                                "category": "Mar",
                                "column-1": "9"
                            },
                            {
                                "category": "Apr",
                                "column-1": "13"
                            },
                            {
                                "category": "May",
                                "column-1": "72"
                            },
                            {
                                "category": "Jun",
                                "column-1": "24"
                            },
                            {
                                "category": "July",
                                "column-1": "43"
                            },
                            {
                                "category": "Aug",
                                "column-1": "41"
                            },
                            {
                                "category": "Sep",
                                "column-1": "68"
                            },
                            {
                                "category": "Oct",
                                "column-1": "28"
                            },
                            {
                                "category": "Nov",
                                "column-1": "29"
                            },
                            {
                                "category": "Dec",
                                "column-1": "34"
                            }
                        ]
                    }
            );</script>

        <script type="text/javascript">
            AmCharts.makeChart("SourceOfHiresPieChart",
                    {
                        "type": "pie",
                        "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                        "labelText": "[[title]]: [[value]]",
                        "titleField": "category",
                        "valueField": "column-1",
                        "theme": "light",
                        "allLabels": [],
                        "balloon": {},
                        "legend": {
                            "align": "center",
                            "markerType": "circle"
                        },
                        "titles": [],
                        "dataProvider": [
                            {
                                "category": "Advert",
                                "column-1": "23"
                            },
                            {
                                "category": "Referral",
                                "column-1": "7"
                            },
                            {
                                "category": "Vsource",
                                "column-1": "3"
                            },
                            {
                                "category": "Agency",
                                "column-1": "2"
                            },
                            {
                                "category": "Social Media",
                                "column-1": "22"
                            },
                            {
                                "category": "SourceEdge",
                                "column-1": "3"
                            }
                        ]
                    }
            );
        </script>

        <script type="text/javascript">
            AmCharts.makeChart("CancellationsPieChart",
                    {
                        "type": "pie",
                        "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                        "labelText": "[[title]]: [[value]]",
                        "titleField": "category",
                        "valueField": "column-1",
                        "theme": "light",
                        "allLabels": [],
                        "balloon": {},
                        "legend": {
                            "align": "center",
                            "markerType": "circle"
                        },
                        "titles": [],
                        "dataProvider": [
                            {
                                "category": "Change In Business Needs",
                                "column-1": "2"
                            },
                            {
                                "category": "Duplicate",
                                "column-1": "1"
                            },
                            {
                                "category": "Filled Internally",
                                "column-1": "4"
                            }
                        ]
                    }
            );
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
            });</script>

        <script>
            $("#showEmailOptionsDiv").click(function () {
                $("#emailOptionsDiv").slideDown("fast");
                $('html, body').animate({
                    scrollTop: $("#emailOptionsDiv").offset().top - 200
                }, 1000);
            });</script>

        <script>
            function hideEmailOptionsDiv() {
                $("#emailOptionsDiv").slideUp("fast");
            }
        </script>

    </body>
</html>