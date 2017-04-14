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
public class getTable_Gold_LexisNexis_Cancellations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_Cancellations.");

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

            out.println("                                                <thead>\n"
                    + "                                                    <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                        <th style=\"text-align: center\">Month</th>\n");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            out.println("                                                        <th style=\"text-align: center\">Grand Total</th>\n"
                    + "                                                    </tr>\n"
                    + "                                                </thead>"
                    + "                                                  <tbody>");

            if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("select DISTINCT(MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y'))),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) FROM gold_ln_tracker3 WHERE reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = YEAR('" + reportdate + "') ORDER BY STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')");
                while (rs.next()) {
                    String acceptcanceldatemonth = rs.getString(1);
                    String acceptcanceldateyear = rs.getString(2);

                    out.println("                                                  <tr>\n"
                            + "                                                        <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + " " + rs.getString(2) + "</b></span></td>\n");

                    Statement st2 = co.createStatement();

                    ResultSet rs2 = st2.executeQuery("Select COUNT(jobtitle) FROM gold_ln_tracker3 WHERE MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldatemonth + "' AND  YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldateyear + "' AND reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore')");
                    while (rs2.next()) {
                        out.println("                                                        <td align=\"center\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs2.getString(1) + "</font></span></td>");
                    }
                    st2.close();

                    out.println("                                                  </tr>\n");
                }

                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                out.println("                                                    <tr class=\"warning\">\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n");

                rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker3 WHERE reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = YEAR('" + reportdate + "')");
                while (rs.next()) {
                    out.println("                                                            <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>");
                }
                out.println("                                                     </tr>");
                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();

            } else {

                ResultSet rs = st.executeQuery("select DISTINCT(MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y'))),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) FROM gold_ln_tracker3 WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = YEAR('" + reportdate + "') ORDER BY STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')");
                while (rs.next()) {
                    String acceptcanceldatemonth = rs.getString(1);
                    String acceptcanceldateyear = rs.getString(2);

                    out.println("                                                  <tr>\n"
                            + "                                                        <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + " " + rs.getString(2) + "</b></span></td>\n");

                    Statement st2 = co.createStatement();

                    ResultSet rs2 = st2.executeQuery("Select COUNT(jobtitle) FROM gold_ln_tracker3 WHERE MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldatemonth + "' AND  YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldateyear + "' AND reportdate = '" + reportdate + "' AND country = '" + country + "'");
                    while (rs2.next()) {
                        out.println("                                                        <td align=\"center\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs2.getString(1) + "</font></span></td>");
                    }
                    st2.close();

                    out.println("                                                  </tr>\n");
                }

                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                out.println("                                                    <tr class=\"warning\">\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n");

                rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker3 WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = YEAR('" + reportdate + "')");
                while (rs.next()) {
                    out.println("                                                            <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>");
                }
                out.println("                                                     </tr>");
                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_Cancellations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
