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
import java.util.ArrayList;
import java.util.List;
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
public class getTable_Gold_Yougov_RequisitionsRadar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Bronze_RequisitionsRadar.");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String reportdate = request.getParameter("reportdate");

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

            out.println("                                            <thead>"
                    + "                                                <tr>"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Country</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Plan</th>\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"success\">Source<br>(<30 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"warning\">Source<br>(30 - 40 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"danger\">Source<br>(>40 Days)</th>\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"success\">Select<br>(<30 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"warning\">Select<br>(30 - 40 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"danger\">Select<br>(>40 Days)</th>\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"success\">Interview<br>(<30 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"warning\">Interview<br>(30 - 40 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"danger\">Interview<br>(>40 Days)</th>\n"
                    + "\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Open<br>Positions</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">On hold<br>Positions</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Offers<br>Pending</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Filled<br>Positions</th>\n"
                    + "                                                </tr>"
                    + "                                            </thead>"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("Select DISTINCT(country) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' GROUP BY country");

            List<String> countryList = new ArrayList<>();

            while (rs.next()) {
                countryList.add(rs.getString(1));
            }

            for (String country : countryList) {
                out.println("                                                <tr align=\"center\">"
                        + "                                                    <td class=\"warning\"><span class=\"info-box-text\"><b>" + country + "</b></span></td>\n");

                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Plan' AND country = '" + country + "'");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }

                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Source' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) < 30");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Source' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) >= 30 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) <= 40");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Source' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) > 40");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }

                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Select' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) < 30");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Select' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) >= 30 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) <= 40");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Select' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) > 40");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }

                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Interview' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) < 30");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Interview' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) >= 30 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) <= 40");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Interview' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) > 40");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }

                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage != 'Offer' AND stage != 'Filled' AND stage!='On hold' AND stage != 'Cancelled' AND country = '" + country + "'");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'On hold' AND country = '" + country + "'");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Offer' AND country = '" + country + "'");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Filled' AND country = '" + country + "'");
                while (rs.next()) {
                    if (rs.getString(1).equals("0")) {
                        out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                    } else {
                        out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    }
                }
                out.println("                                              </tr>");
            }
            out.println("                                            </tbody>"
                    + "                                            <tfoot>");

            out.println("                                               <tr class=\"warning\" align=\"center\">"
                    + "                                                    <td><span class=\"info-box-text\"><b>GRAND TOTAL</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + ""
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + ""
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + ""
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "                                                    <td><span class=\"info-box-text\"><b>--</b></span></td>\n"
                    + "");

            rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage != 'Offer' AND stage != 'Filled' AND stage!='On hold' AND stage != 'Cancelled'");
            while (rs.next()) {
                out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
            }
            rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'On hold'");
            while (rs.next()) {
                out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
            }
            rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Offer'");
            while (rs.next()) {
                out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
            }
            rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage = 'Filled'");
            while (rs.next()) {
                out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
            }
            out.println("                                                </tr>");

            out.println("                                            </tfoot>");

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_Yougov_RequisitionsRadar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
