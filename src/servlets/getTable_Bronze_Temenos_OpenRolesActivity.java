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
public class getTable_Bronze_Temenos_OpenRolesActivity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Bronze_OpenRolesActivity.");

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
                    + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                    <th style=\"text-align: center\">Grand Total</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 1st Round Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of 2nd Round Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Final Round Interviews</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Offers</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Offers Accepted</th>\n"
                    + "                                                    <th style=\"text-align: center\">No. of Offers Rejected</th>\n"
                    + "                                                    <th style=\"text-align: center\">Sum of Fee</th>\n"
                    + "                                                </tr>\n"
                    + "                                            </thead>\n"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("Select recruiter,noof1stround,noof2ndround,noof3rdround,noofoffer,noofofferaccept,noofofferreject,fee from bronze_temenos_activity  WHERE recruiter = 'Grand Total' AND reportdate = '" + reportdate + "'");
            while (rs.next()) {
                out.println("                                                <tr class=\"warning\">\n"
                        + "                                                    <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                        + "                                                    <td align=\"center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                        + "                                                  </tr>");
            }

            out.println("                                            </tbody>");

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Bronze_Temenos_OpenRolesActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
