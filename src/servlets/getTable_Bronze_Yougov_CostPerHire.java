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
public class getTable_Bronze_Yougov_CostPerHire extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Bronze_Ypugov_CostPerHire.");

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

        try {

            out.println("<thead>\n"
                    + "                                            <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Month</th>\n"
                    + "                                                    <th style=\"text-align: center\">Count of Job Title</th>\n"
                    + "                                                    <th style=\"text-align: center\">Average of Salary</th>\n"
                    + "                                                    <th style=\"text-align: center\">Total Fee</th>\n"
                    + "                                                    <th style=\"text-align: center\">Retainer</th>\n"
                    + "                                                    <th style=\"text-align: center\">Cost per Hire</th>\n"
                    + "                                                    <th style=\"text-align: center\">% Fee / Placement</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("select month,countofjobtitle,avgofsalary,totalfee,retainer,costperhire,percentfeeperplacement from bronze_yougov_costperhire where reportdate = '2016-08-18' AND client = '" + client + "' AND domain = '" + domain + "' AND month != 'Grand Total';");

            while (rs.next()) {
                out.println("                                                <tr>\n"
                        + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(6) + "</font></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                        + "                                                </tr>");
            }
            out.println("                                            </tbody>\n"
                    + "                                            <tfoot>");

            rs = st.executeQuery("select month,countofjobtitle,avgofsalary,totalfee,retainer,costperhire,percentfeeperplacement from bronze_yougov_costperhire where reportdate = '" + reportdate + "' AND client = '" + client + "' AND domain = '" + domain + "' AND month = 'Grand Total';");
            while (rs.next()) {
                out.println("                                                <tr class=\"warning\">\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(2) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(4) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(5) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(6) + "</font></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                        + "                                                </tr>");
            }
            out.println("                                            </tfoot>");

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Bronze_Yougov_CostPerHire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
