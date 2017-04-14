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

public class getTable_Gold_Yougov_AgeOfOpenRoles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Bronze_OpenRequisitions.");

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

            out.println("<thead>\n"
                    + "                                            <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Age in Days</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Openings</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage != 'Offer' AND stage != 'Filled' AND stage!='On hold' AND stage != 'Cancelled' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) < 30");

            out.println("                                                <tr>\n"
                    + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>< 30 Days</b></span></td>\n");
            while (rs.next()) {
                out.println("                                              <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n"
                        + "                                              </tr>");
            }
            rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage != 'Offer' AND stage != 'Filled' AND stage!='On hold' AND stage != 'Cancelled' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) >= 30 AND  ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) <= 40");

            out.println("                                                <tr>\n"
                    + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>30 - 40 Days</b></span></td>\n");
            while (rs.next()) {
                out.println("                                              <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n"
                        + "                                              </tr>");
            }
            rs = st.executeQuery("Select COUNT(jobtitle) from bronze_yougov_tracker WHERE reportdate = '" + reportdate + "' AND stage != 'Offer' AND stage != 'Filled' AND stage!='On hold' AND stage != 'Cancelled' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%d/%m/%Y'))) > 40");

            out.println("                                                <tr>\n"
                    + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>> 40 Days</b></span></td>\n");
            while (rs.next()) {
                out.println("                                              <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n"
                        + "                                              </tr>");
            }

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_Yougov_AgeOfOpenRoles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
