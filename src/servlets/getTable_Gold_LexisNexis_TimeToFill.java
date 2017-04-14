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

public class getTable_Gold_LexisNexis_TimeToFill extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_TimeToFill.");

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
                    + "                                            <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Month</th>\n"
                    + "                                                    <th style=\"text-align: center\">Time To Fill</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("Select MONTHNAME(STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')),YEAR(STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')),ROUND(SUM(workingdaysopen)/COUNT(jobtitle)) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "' GROUP BY MONTHNAME(STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')) ORDER BY STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')");

                while (rs.next()) {
                    out.println("                                                <tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + " " + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(3) + "</font></span></td>\n"
                            + "                                              </tr>");
                }
                out.println("                                          </tbody>"
                        + "                                            <tfoot>");

                rs = st.executeQuery("Select ROUND(SUM(workingdaysopen)/COUNT(jobtitle)) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "'");

                while (rs.next()) {
                    out.println("                                                <tr class=\"warning\">\n"
                            + "                                                    <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>Average</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>"
                            + "                                              </tr>");
                }
                out.println("                                          </tfoot>");
                rs.close();
                st.close();
                co.close();
            } else {

                ResultSet rs = st.executeQuery("Select MONTHNAME(STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')),YEAR(STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')),ROUND(SUM(workingdaysopen)/COUNT(jobtitle)) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND reportdate = '" + reportdate + "' GROUP BY MONTHNAME(STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')) ORDER BY STR_TO_DATE(acceptcanceldate_offeraccepted,'%m/%d/%Y')");

                while (rs.next()) {
                    out.println("                                                <tr>\n"
                            + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + " " + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(3) + "</font></span></td>\n"
                            + "                                              </tr>");
                }
                out.println("                                          </tbody>"
                        + "                                            <tfoot>");

                rs = st.executeQuery("Select ROUND(SUM(workingdaysopen)/COUNT(jobtitle)) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND reportdate = '" + reportdate + "'");

                while (rs.next()) {
                    out.println("                                                <tr class=\"warning\">\n"
                            + "                                                    <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>Average</b></span></td>\n"
                            + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>"
                            + "                                              </tr>");
                }
                out.println("                                          </tfoot>");
                rs.close();
                st.close();
                co.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_TimeToFill.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
