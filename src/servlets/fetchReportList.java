/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *
 * @author tanuj_000
 */

public class fetchReportList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached @ fetchReportList.");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        System.out.println("username is : " + username);

        Connection co = null;
        String OSflag = getServletContext().getInitParameter("OSflag");
        String host = null, driver = null, userName = null, password = null;
        switch (OSflag) {
            case "0": {
                host = getServletContext().getInitParameter("host");
                driver = getServletContext().getInitParameter("driver");
                userName = getServletContext().getInitParameter("userName");
                password = getServletContext().getInitParameter("password");
                break;
            }
            case "1": {
                host = "jdbc:mysql://" + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/npreportingsuite";
                userName = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
                driver = getServletContext().getInitParameter("driver");
                break;
            }
        }

        try {

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("Select client FROM user_accounts WHERE username = '" + username + "'");
            while (rs.next()) {
            	/*if (rs.getString(1).equals("All")) {
                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Bronze Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexis.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LexisNexis Risk</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Yougov.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Yougov</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Temenos.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Temenos</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"ACS.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>ACS</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"CitrixChina_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Citrix China</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Yougov_Gold.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Yougov</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisUK_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN UK (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisUK_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN UK (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisFrance_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN France (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisFrance_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN France (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisSA_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN S.Africa (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisSA_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN S.Africa (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisIndia_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN India (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisIndia_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN India (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisJapan_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN Japan (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisJapan_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN Japan (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisMalSing_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN SEA (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisMalSing_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN SEA (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                }
                if (rs.getString(1).equals("LexisNexis Global")) {
                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <!-- <li>\n"
                            + "                                    <a href=\"LexisNexisUK_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN UK (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n -->"
                            + "                                <!-- <li>\n"
                            + "                                    <a href=\"LexisNexisUK_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN UK (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n -->"
                            + "                                <!-- <li>\n"
                            + "                                    <a href=\"LexisNexisFrance_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN France (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n -->"
                            + "                                <!-- <li>\n"
                            + "                                    <a href=\"LexisNexisFrance_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN France (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n -->"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisSA_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN S.Africa (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisSA_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN S.Africa (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisIndia_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN India (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisIndia_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN India (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisJapan_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN Japan (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisJapan_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN Japan (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisMalSing_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN SEA (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisMalSing_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN SEA (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Bronze Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexis.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LexisNexis</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("Yougov")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Bronze Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Yougov.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Yougov</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("Temenos")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Bronze Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Temenos.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Temenos</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis India")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisIndia_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN India (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("Citrix China")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"CitrixChina_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>Citrix China (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("ACS")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Bronze Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"ACS.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>ACS</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis UK")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisUK_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN UK (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis France")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisFrance_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN France (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis SA")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisSA_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN S.Africa (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis Japan")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisJapan_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN Japan (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis Malsing")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisMalSing_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN SEA (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

                } else if (rs.getString(1).equals("LexisNexis Asia")) {

                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisJapan_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN Japan (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisMalSing_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN SEA (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"LexisNexisIndia_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>LN India (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

               } */   if (rs.getString(1).equals("Demo")||rs.getString(1).equals("chris")||rs.getString(1).equals("gautam")) {
                    out.println("<li class=\"header\">MAIN NAVIGATION</li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Bronze Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Bronze_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>UNILEVER (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Silver Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Silver_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>ORACLE (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Silver_Quarterly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>ORACLE (Quarterly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Silver_Yearly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>ORACLE (Annual)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li class=\"treeview\">\n"
                            + "                            <a href=\"#\">\n"
                            + "                                <i class=\"fa fa-bar-chart\"></i> <span>Gold Reports</span> <i class=\"fa fa-angle-left pull-right\"></i>\n"
                            + "                            </a>\n"
                            + "                            <ul class=\"treeview-menu\">\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Gold_Weekly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>MICROSOFT (Weekly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Gold_Monthly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>MICROSOFT (Monthly)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "                                <li>\n"
                            + "                                    <a href=\"Demo_Gold_Yearly.jsp\">\n"
                            + "                                        <i class=\"fa fa-bar-chart\"></i> <span>MICROSOFT (Annual)</span>\n"
                            + "                                    </a>\n"
                            + "                                </li>\n"
                            + "\n"
                            + "                            </ul>\n"
                            + "                        </li>\n"
                            + "                        <li>\n"
                            + "                            <a href=\"login.jsp\">\n"
                            + "                                <i class=\"fa fa-sign-out\"></i> <span>Log Out</span>\n"
                            + "                            </a>\n"
                            + "                        </li>");

               } 
               
               
            }
        
            st.close();
            co.close();

            } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(fetchReportList.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

        

