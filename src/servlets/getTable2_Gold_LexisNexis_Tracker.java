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
public class getTable2_Gold_LexisNexis_Tracker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable2_Gold_LexisNexis_Tracker.");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String reportdate = request.getParameter("reportdate");
        String country = request.getParameter("country");

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

        int placementFlag = 0;

        try {

            out.println("                                          <thead>\n"
                    + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Job Title</th>\n"
                    + "                                                    <th style=\"text-align: center\">Recruiter</th>\n"
                    + "                                                    <th style=\"text-align: center\">Hiring Manager</th>\n"
                    + "                                                    <th style=\"text-align: center\">Status</th>\n"
                    + "                                                    <th style=\"text-align: center\">Country</th>\n"
                    + "                                                    <th style=\"text-align: center\">Opening Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Working Days Open</th>\n"
                    + "                                                    <th style=\"text-align: center\">Filled Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Offer Acceptance Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Hiring Details</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            if (country.equals("Malsing")) {

                ResultSet rs = st.executeQuery("Select jobtitle,recruiter,hiringmanager,status,country,openingdate,workingdaysopen,acceptcanceldate,acceptcanceldate_offeraccepted,salary,startdate,nameofcandidatehired,sourcecancellation from gold_ln_tracker2 where reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') AND STR_TO_DATE(acceptcanceldate,'%m/%d/%Y') BETWEEN DATE_SUB('" + reportdate + "', INTERVAL 5 DAY) AND '" + reportdate + "'");

                while (rs.next()) {

                    out.println("                                                       <tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n");

                    out.println("                                                    <td align=\"center\">"
                            + "                                                                                                       Canidate Name: <b>" + rs.getString(12) + "</b><br>"
                            + "                                                                                                       Salary: <b>" + rs.getString(10) + "</b><br>"
                            + "                                                                                                       Start Date: <b>" + rs.getString(11) + "</b><br>"
                            + "                                                                                                       Source Channel: <b>" + rs.getString(13) + "</b>"
                            + "                                                      </td>");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();

            } else if (country.equals("Asia")) {

                ResultSet rs = st.executeQuery("Select jobtitle,recruiter,hiringmanager,status,country,openingdate,workingdaysopen,acceptcanceldate,acceptcanceldate_offeraccepted,salary,startdate,nameofcandidatehired,sourcecancellation from gold_ln_tracker2 where reportdate = '" + reportdate + "' AND country IN ('India','Japan','Malaysia','Singapore') AND STR_TO_DATE(acceptcanceldate,'%m/%d/%Y') BETWEEN DATE_SUB('" + reportdate + "', INTERVAL 5 DAY) AND '" + reportdate + "'");

                while (rs.next()) {

                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n");

                    out.println("                                                    <td align=\"center\">"
                            + "                                                                                                       Canidate Name: <b>" + rs.getString(12) + "</b><br>"
                            + "                                                                                                       Salary: <b>" + rs.getString(10) + "</b><br>"
                            + "                                                                                                       Start Date: <b>" + rs.getString(11) + "</b><br>"
                            + "                                                                                                       Source Channel: <b>" + rs.getString(13) + "</b>"
                            + "                                                      </td>");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();

            } else {

                ResultSet rs = st.executeQuery("Select jobtitle,recruiter,hiringmanager,status,country,openingdate,workingdaysopen,acceptcanceldate,acceptcanceldate_offeraccepted,salary,startdate,nameofcandidatehired,sourcecancellation from gold_ln_tracker2 where reportdate = '" + reportdate + "' AND country = '" + country + "'  AND STR_TO_DATE(acceptcanceldate,'%m/%d/%Y') BETWEEN DATE_SUB('" + reportdate + "', INTERVAL 5 DAY) AND '" + reportdate + "'");

                while (rs.next()) {

                    out.println("                                                   <tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n");

                    out.println("                                                    <td align=\"center\">"
                            + "                                                                                                       Canidate Name: <b>" + rs.getString(12) + "</b><br>"
                            + "                                                                                                       Salary: <b>" + rs.getString(10) + "</b><br>"
                            + "                                                                                                       Start Date: <b>" + rs.getString(11) + "</b><br>"
                            + "                                                                                                       Source Channel: <b>" + rs.getString(13) + "</b>"
                            + "                                                      </td>");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable2_Gold_LexisNexis_Tracker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
