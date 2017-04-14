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

public class fetchReportMonths extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached @ fetchReportMonths.");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String client = request.getParameter("client");

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

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            if (client.equals("Gold_LexisNexisRisk_Monthly")) {

                ResultSet rs = st.executeQuery("Select DISTINCT(MONTHNAME(reportdate)),MAX(reportdate) FROM bronze_openrolestracker where MONTHNAME(reportdate) !=  MONTHNAME(CURRENT_DATE) GROUP BY MONTHNAME(reportdate)");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportMonth\" value=\"lastReportMonth\" class=\"setReportMonthHref\" id=\"" + rs.getString(2) + "\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportMonthHref\" id=\"" + rs.getString(2) + "\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a></li>");
                    }
                }
                rs.close();
            }
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(fetchReportMonths.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
