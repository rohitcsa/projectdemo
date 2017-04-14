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
public class getTable_Gold_LexisNexis_MonthlySummary extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_MonthlySummary.");

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

        try {
            if (country.equals("South Africa")) {
                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Week Ending On</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles At Week Beginning</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Opened This Week</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled</th>\n"
                        + "                                                    <th style=\"text-align: center\">#  Offers Pending</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Candidates Screened</th>\n"
                        + "                                                    <th style=\"text-align: center\"># CV’s Sent To HM</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Interviews By The Hiring Managers</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Cancelled Or On Hold</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled From The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles at Week End</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                Class.forName(driver);
                co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery("Select DATE_FORMAT(reportdate,'%d/%m/%y'),noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofactiverolesweekend from gold_ln_monthlysummary where country = '" + country + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n");
                    out.println("</tr>");
                }

                String mindate = "";
                String maxdate = "";
                rs = st.executeQuery("Select MIN(reportdate),MAX(reportdate) from gold_ln_monthlysummary WHERE country = '" + country + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    mindate = rs.getString(1);
                    maxdate = rs.getString(2);
                }

                rs = st.executeQuery("Select noofactiveroles from gold_ln_monthlysummary where country = '" + country + "' AND reportdate = '" + mindate + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    out.println("<tr class =\"warning\">\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>MONTH</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n");
                }

                rs = st.executeQuery("Select SUM(noofrolesopened),SUM(noofrolesfilled),SUM(noofofferspending),SUM(noofcandidatesscreened),SUM(noofcvssent),SUM(noofinterviews),SUM(noofrolescancelledonhold) from gold_ln_monthlysummary where country = '" + country + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n");
                }

                rs = st.executeQuery("Select noofrolesfilledmonth, noofactiverolesweekend from gold_ln_monthlysummary where country = '" + country + "' AND reportdate = '" + maxdate + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n");
                    out.println("</tr>");
                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();

            } else if (country.equals("France")) {
                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Week Ending On</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles At Week Beginning</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Opened This Week</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled</th>\n"
                        + "                                                    <th style=\"text-align: center\">#  Offers Pending</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Candidates Screened</th>\n"
                        + "                                                    <th style=\"text-align: center\"># CV’s Sent To HM</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Interviews By The Hiring Managers</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Cancelled Or On Hold</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled From The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles at Week End</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                Class.forName(driver);
                co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery("Select DATE_FORMAT(reportdate,'%d/%m/%y'),noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofactiverolesweekend from gold_ln_monthlysummary where country = '" + country + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n");
                    out.println("</tr>");
                }

                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();

            } else {
                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Week Ending On</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles At Week Beginning</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Opened This Week</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled</th>\n"
                        + "                                                    <th style=\"text-align: center\">#  Offers Pending</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Candidates Screened</th>\n"
                        + "                                                    <th style=\"text-align: center\"># CV’s Sent To HM</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Interviews By The Hiring Managers</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Cancelled Or On Hold</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled From The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Sales Roles filled from The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles At Week End</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles at Week End</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                Class.forName(driver);
                co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery("Select DATE_FORMAT(reportdate,'%d/%m/%y'),noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofsalesrolesmonth,noofsalesrolesweekend,noofactiverolesweekend from gold_ln_monthlysummary where country = '" + country + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(13) + "</b></span></td>\n");
                    out.println("</tr>");
                }

                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_MonthlySummary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
