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
public class getTable_Gold_LexisNexis_HiresVsDemand extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_HiresVsDemand.");

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

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            out.println("                                                <thead>\n"
                    + "                                                    <tr style=\"background-color: #EEEEEE\">\n"
                    + "                                                        <th style=\"text-align: center\">SoW Start Date</th>\n"
                    + "                                                        <th style=\"text-align: center\">Total Demand Plan in SoW</th>\n"
                    + "                                                        <th style=\"text-align: center\">Monthly Demand Plan (SoW forecast/duration of deal in months)</th>\n"
                    + "                                                        <th style=\"text-align: center\">Target YTD</th>\n"
                    + "                                                        <th style=\"text-align: center\">Current Total Open Roles</th>\n"
                    + "                                                        <th style=\"text-align: center\"># Roles Filled YTD</th>\n"
                    + "                                                        <th style=\"text-align: center\">SoW Demand Plan VS Roles Filled (%) YTD</th>\n"
                    + "                                                        <th style=\"text-align: center\">SoW Team Size</th>\n"
                    + "                                                        <th style=\"text-align: center\">Actual Team Size</th>\n"
                    + "                                                        <th style=\"text-align: center\">Vacancies Per Recruiter</th>\n"
                    + "                                                        <th style=\"text-align: center\">Team Variance</th>\n"
                    + "                                                        <th style=\"text-align: center\">Ahead / On Target / Behind on SoW?</th>\n"
                    + "                                                        <th style=\"text-align: center\">Variance</th>\n"
                    + "                                                    </tr>\n"
                    + "                                                </thead>"
                    + "                                                  <tbody>");

            ResultSet rs = st.executeQuery("select startdate,totaldemand,monthlydemand,targetytd,noofopenroles,noofrolesfilledytd,demandvsrolesfilled,teamsize,actualteamsize,vacanciesperrecruiter,teamvariance,aheadorbehind,variance FROM gold_ln_hiresvsdemand WHERE country = '" + country + "' AND MONTHNAME(reportdate) = MONTHNAME('" + reportdate + "')");
            while (rs.next()) {
                out.println("                                                  <tr>\n"
                        + "                                                        <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(2) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(3) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(4) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(5) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(6) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(7) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(8) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(9) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(10) + "</b></span></td>\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>" + rs.getString(11) + "</b></span></td>\n");
                if (rs.getString(12).equals("Behind")) {
                    out.println("                                                   <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                     <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(13) + "</b></span></td>\n");
                } else if (rs.getString(12).equals("Ahead")) {
                    out.println("                                                   <td align=\"center\" class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                     <td align=\"center\" class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(13) + "</b></span></td>\n");
                } else {
                    out.println("                                                   <td align=\"center\" class=\"info\"><span class=\"info-box-text\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                     <td align=\"center\" class=\"info\"><span class=\"info-box-text\"><b>" + rs.getString(13) + "</b></span></td>\n");
                }
                out.println("                                                  </tr>\n");
            }

            out.println("                                            </tbody>");

            rs.close();
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_HiresVsDemand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
