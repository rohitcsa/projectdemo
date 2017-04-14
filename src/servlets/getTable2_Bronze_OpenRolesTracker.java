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
public class getTable2_Bronze_OpenRolesTracker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Bronze_OpenRolesTracker.");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String reportdate = request.getParameter("reportdate");
        String client = request.getParameter("client");
        String domain = request.getParameter("domain");

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

            out.println("<thead>\n"
                    + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Job Title</th>\n"
                    + "                                                    <th style=\"text-align: center\">Hiring Manager</th>\n"
                    + "                                                    <th style=\"text-align: center\">Stage</th>\n"
                    + "                                                    <th style=\"text-align: center\">Briefing Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Opening Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Working Days Open</th>\n"
                    + "                                                    <th style=\"text-align: center\"># CVs Submitted</th>\n"
                    + "                                                    <th style=\"text-align: center\"># 1st Rnd Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\"># 2nd Rnd Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\"># 3rd Rnd Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\"># Offer Acceptance Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Hiring Details</th>\n"
                    + "                                                    <th style=\"text-align: center\">Notes</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,stage,briefingdate,openingdate,workingdaysopen,noofcvssubmitted,noof1stround,noof2ndround,noof3rdround,acceptcanceldate,salaryusd,startdate,nameofcandidatehired,candidatesource,notes from bronze_openrolestracker where reportdate = '" + reportdate + "' AND client = '" + client + "' AND domain = '" + domain + "' AND stage = 'Filled'");

            while (rs.next()) {

                out.println("<tr>\n"
                        + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n");
                switch (rs.getString(3).trim()) {
                    case "Plan":
                    case "Source":
                        out.println("                                                    <td align=\"center\" class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                        break;
                    case "Select":
                        out.println("                                                    <td align=\"center\" class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                        break;
                    case "Interview":
                        out.println("                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                        break;
                    case "Offer":
                        out.println("                                                    <td align=\"center\" class=\"info\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                        break;
                    case "Filled":
                    case "Cancelled":
                    case "On Hold":
                        out.println("                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                        break;
                    default:
                        out.println("                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                        break;
                }
                out.println("                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(6) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(8) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"  width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(9) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(10) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n");
                if (rs.getString(11).trim().length() > 0 == false) {
                    out.println("                                                    <td align=\"center\"><span class=\"text-muted\"><b>--</b></span></td>");

                } else {
                    out.println("                                                    <td align=\"center\">"
                            + "                                                                                                       Canidate Name: <b>" + rs.getString(14) + "</b><br>"
                            + "                                                                                                       Salary: <b>" + rs.getString(12) + "</b><br>"
                            + "                                                                                                       Start Date: <b>" + rs.getString(13) + "</b><br>"
                            + "                                                                                                       Source Channel: <b>" + rs.getString(15) + "</b>"
                            + "                                                      </td>");
                }
                if (rs.getString(16).trim().length() > 0) {
                    if (placementFlag == 0) {
                        out.println("                                                     <td align=\"center\" width=\"5%\"><span class=\"text-muted\"><icon class=\"fa fa-comment\" data-container=\"body\" data-placement=\"bottom\" data-html=\"true\" data-original-title=\"<b>" + rs.getString(16) + "</b>\" data-toggle=\"tooltip\"></icon>"
                                + "                                                       </td>"
                                + "                                                       </tr>");
                        placementFlag = 1;
                    } else {
                        out.println("                                                     <td align=\"center\" width=\"5%\"><span class=\"text-muted\"><icon class=\"fa fa-comment\" data-container=\"body\" data-placement=\"top\" data-html=\"true\" data-original-title=\"<b>" + rs.getString(16) + "</b>\" data-toggle=\"tooltip\"></icon>"
                                + "                                                       </td>"
                                + "                                                       </tr>");
                    }
                } else {

                    out.println("                                                       <td align=\"center\" width=\"5%\"><span class=\"text-muted\"><icon class=\"fa fa-minus\"></icon>"
                            + "                                                         </td>\n"
                            + "                                                         </tr>");
                }

            }
            out.println("                                            </tbody>");

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable2_Bronze_OpenRolesTracker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
