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
public class getTable_Gold_LexisNexis_Tracker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_Tracker.");

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

            out.println("<thead>\n"
                    + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\" width=\"20%\">Job Title</th>\n"
                    + "                                                    <th style=\"text-align: center\">Hiring Manager</th>\n"
                    + "                                                    <th style=\"text-align: center\">Status</th>\n"
                    + "                                                    <th style=\"text-align: center\">Country</th>\n"
                    + "                                                    <th style=\"text-align: center\">Opening Date</th>\n"
                    + "                                                    <th style=\"text-align: center\">Working Days Open</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of CVs Submitted</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 1st Rnd Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 2nd Rnd Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 3rd Rnd Interviews</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            if (country.equals("United Kingdom")) {

                ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,country,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");
                //ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround,notes from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country = '" + country + "'");
                while (rs.next()) {

                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n");
                    switch (rs.getString(3).trim()) {
                        case "Plan":
                        case "Source":
                        case "Sourcing":
                        case "Approved":
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
                        case "To Be Approved":
                            out.println("                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                        default:
                            out.println("                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                    }
                    out.println("                                                  <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"  width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(10) + "</b></span></td>\n");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();
            } else if (country.equals("Malsing")) {

                ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,country,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country IN( 'Malaysia','Singapore')");
                //ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround,notes from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country = '" + country + "'");
                while (rs.next()) {

                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n");
                    switch (rs.getString(3).trim()) {
                        case "Plan":
                        case "Source":
                        case "Sourcing":
                        case "Approved":
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
                        case "To Be Approved":
                            out.println("                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                        default:
                            out.println("                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                    }
                    out.println("                                                  <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"  width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(10) + "</b></span></td>\n");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();
            } else if (country.equals("Asia")) {

                ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,country,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country IN ('India','Japan','Malaysia','Singapore')");
                //ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround,notes from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country = '" + country + "'");
                while (rs.next()) {

                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n");
                    switch (rs.getString(3).trim()) {
                        case "Plan":
                        case "Source":
                        case "Sourcing":
                        case "Approved":
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
                        case "To Be Approved":
                            out.println("                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                        default:
                            out.println("                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                    }
                    out.println("                                                  <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"  width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(10) + "</b></span></td>\n");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();
            } else {

                ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,country,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country = '" + country + "'");
                //ResultSet rs = st.executeQuery("select jobtitle,hiringmanager,status,openingdate,workingdaysopen,noofcandidates,noof1stround,noof2ndround,noof3rdround,notes from gold_ln_tracker where reportdate = '" + reportdate + "' AND status != 'On Hold' AND status !='To Be Approved' AND country = '" + country + "'");
                while (rs.next()) {

                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n");
                    switch (rs.getString(3).trim()) {
                        case "Plan":
                        case "Source":
                        case "Sourcing":
                        case "Approved":
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
                        case "To Be Approved":
                            out.println("                                                    <td align=\"center\" class=\"active\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                        default:
                            out.println("                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n");
                            break;
                    }
                    out.println("                                                  <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"  width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\" width=\"5%\"><span class=\"info-box-text\"><b>" + rs.getString(10) + "</b></span></td>\n");

                }
                out.println("                                            </tbody>");

                rs.close();
                st.close();
                co.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_Tracker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
