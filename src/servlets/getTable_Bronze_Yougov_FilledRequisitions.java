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
public class getTable_Bronze_Yougov_FilledRequisitions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Bronze_FilledRequisitions.");

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

        List<String> countryList = new ArrayList<>();

        try {

            out.println("                                                <thead>\n"
                    + "                                                    <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                        <th style=\"text-align: center\">Month</th>\n");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("select DISTINCT(country) from bronze_yougov_tracker WHERE stage = 'Filled' AND reportdate = '" + reportdate + "' GROUP BY country");

            while (rs.next()) {
                countryList.add(rs.getString(1));

                out.println("                                                        <th style=\"text-align: center\">" + rs.getString(1) + "</th>\n");
            }

            out.println("                                                        <th style=\"text-align: center\">Grand Total</th>\n"
                    + "                                                    </tr>\n"
                    + "                                                </thead>"
                    + "                                                  <tbody>");

            rs = st.executeQuery("select DISTINCT(MONTHNAME(STR_TO_DATE(acceptcanceldate,'%d/%m/%Y'))),YEAR(STR_TO_DATE(acceptcanceldate,'%d/%m/%Y')) FROM bronze_yougov_tracker WHERE stage = 'Filled' AND reportdate = '" + reportdate + "' ORDER BY STR_TO_DATE(acceptcanceldate,'%d/%m/%Y')");
            while (rs.next()) {
                String acceptcanceldatemonth = rs.getString(1);

                out.println("                                                  <tr>\n"
                        + "                                                        <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + " " + rs.getString(2) + "</b></span></td>\n");

                Statement st2 = co.createStatement();
                for (String country : countryList) {
                    ResultSet rs2 = st2.executeQuery("Select count(jobtitle) FROM bronze_yougov_tracker WHERE stage = 'Filled' AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%d/%m/%Y')) = '" + acceptcanceldatemonth + "' AND country = '" + country + "' AND reportdate = '" + reportdate + "'");
                    while (rs2.next()) {
                        out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>" + rs2.getString(1) + "</b></span></td>\n");
                    }
                    rs2.close();
                }

                ResultSet rs2 = st2.executeQuery("Select COUNT(jobtitle) FROM bronze_yougov_tracker WHERE stage = 'Filled' AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%d/%m/%Y')) = '" + acceptcanceldatemonth + "' AND reportdate = '" + reportdate + "'");
                while (rs2.next()) {
                    out.println("                                                        <td align=\"center\" class=\"warning\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs2.getString(1) + "</font></span></td>");
                }
                st2.close();

                out.println("                                                  </tr>\n");
            }

            out.println("                                            </tbody>\n"
                    + "                                            <tfoot>");

            out.println("                                                    <tr class=\"warning\">\n"
                    + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n");

            for (String country : countryList) {
                rs = st.executeQuery("Select count(jobtitle) FROM bronze_yougov_tracker WHERE stage = 'Filled' AND country = '" + country + "' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("                                                        <td align=\"center\"><span class=\"text-bold\"><b>" + rs.getString(1) + "</b></span></td>\n");
                }
                rs.close();
            }
            rs = st.executeQuery("Select count(jobtitle) FROM bronze_yougov_tracker WHERE stage = 'Filled' AND reportdate = '" + reportdate + "'");
            while (rs.next()) {
                out.println("                                                            <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>");
            }
            out.println("                                                     </tr>");
            out.println("                                            </tfoot>");

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Bronze_Yougov_FilledRequisitions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
