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
public class getTable_Gold_LexisNexis_OpenRolesActivity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_OpenRolesActivity.");

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

            out.println("<thead>\n"
                    + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Recuiter</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Roles</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Candidates</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 1st Round Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 2nd Round Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 3rd Round Interviews</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            if (country.equals("United Kingdom")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay') GROUP BY recruiter");

                while (rs.next()) {
                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(3) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                </tr>");
                }
                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                rs = st.executeQuery("Select COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) from gold_ln_tracker  WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");
                while (rs.next()) {
                    out.println("                                                <tr class=\"warning\">\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(2) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                </tr>");
                }

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore') GROUP BY recruiter");

                while (rs.next()) {
                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(3) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                </tr>");
                }
                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                rs = st.executeQuery("Select COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) from gold_ln_tracker  WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore')");
                while (rs.next()) {
                    out.println("                                                <tr class=\"warning\">\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(2) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                </tr>");
                }

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else if (country.equals("Asia")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                while (rs.next()) {
                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(3) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                </tr>");
                }
                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                rs = st.executeQuery("Select COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) from gold_ln_tracker  WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore')");
                while (rs.next()) {
                    out.println("                                                <tr class=\"warning\">\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(2) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                </tr>");
                }

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                while (rs.next()) {
                    out.println("<tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(3) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                </tr>");
                }
                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                rs = st.executeQuery("Select COUNT(jobtitle),SUM(noofcandidates),SUM(noof1stround),SUM(noof2ndround),SUM(noof3rdround) from gold_ln_tracker  WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "'");
                while (rs.next()) {
                    out.println("                                                <tr class=\"warning\">\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(1) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(2) + "</font></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                </tr>");
                }

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_OpenRolesActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
