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
public class getTable_Gold_LexisNexis_MonthlySourceOfHires extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_MonthlySourceOfHires.");

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

        List<String> monthList = new ArrayList<>();
        try {

            out.println("                                                <thead>\n"
                    + "                                                    <tr style=\"background-color: #EEEEEE\">\n");

            out.println("                                                        <th style=\"text-align: center\">Source</th>\n");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("select MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) from gold_ln_tracker2 where country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "' GROUP BY MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) ORDER BY STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')");

                while (rs.next()) {
                    if (rs.getString(1) == null) {
                        monthList.add("Missing Dates");
                        out.println("                                                        <th style=\"text-align: center\">Missing Dates</th>\n");

                    } else {
                        monthList.add(rs.getString(1));
                        out.println("                                                        <th style=\"text-align: center\">" + rs.getString(1) + " " + rs.getString(2) + "</th>\n");

                    }
                }

                out.println("                                                        <th style=\"text-align: center\">Grand Total</th>\n"
                        + "                                                    </tr>\n"
                        + "                                                </thead>"
                        + "                                                  <tbody>");

                rs = st.executeQuery("select DISTINCT(sourcecancellation) from gold_ln_tracker2 where country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    String source;
                    source = rs.getString(1);

                    out.println("                                                  <tr>\n"
                            + "                                                        <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + source + "</b></span></td>\n");

                    Statement st2 = co.createStatement();
                    for (String month : monthList) {
                        if (month.equals("Missing Dates")) {
                            ResultSet rs2 = st2.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND sourcecancellation = '" + rs.getString(1) + "' AND acceptcanceldate = '--' AND reportdate  = '" + reportdate + "'");
                            while (rs2.next()) {
                                if (rs2.getString(1).equals("0")) {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>--</b></span></td>\n");

                                } else {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>" + rs2.getString(1) + "</b></span></td>\n");
                                }
                            }
                        } else {

                            ResultSet rs2 = st2.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND sourcecancellation = '" + rs.getString(1) + "' AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + month + "' AND reportdate  = '" + reportdate + "'");
                            while (rs2.next()) {
                                if (rs2.getString(1).equals("0")) {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>--</b></span></td>\n");

                                } else {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>" + rs2.getString(1) + "</b></span></td>\n");
                                }
                            }
                        }
                    }

                    ResultSet rs3 = st2.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country  IN ('Malaysia','Singapore') AND sourcecancellation = '" + rs.getString(1) + "' AND reportdate = '" + reportdate + "'");
                    while (rs3.next()) {
                        if (rs3.getString(1).equals("0")) {
                            out.println("                                                        <td align=\"center\" class=\"warning\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">--</font></span></td>");
                        } else {
                            out.println("                                                        <td align=\"center\" class=\"warning\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs3.getString(1) + "</font></span></td>");

                        }
                    }

                    st2.close();

                    out.println("                                                  </tr>\n");
                }

                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                out.println("                                                    <tr class=\"warning\">\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n");

                for (String month : monthList) {
                    if (month.equals("Missing Dates")) {
                        rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND acceptcanceldate = '--' AND reportdate  = '" + reportdate + "'");
                        while (rs.next()) {
                            out.println("                                                        <td align=\"center\"><span class=\"text-bold\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                        rs.close();
                    } else {
                        rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + month + "' AND reportdate  = '" + reportdate + "'");
                        while (rs.next()) {
                            out.println("                                                        <td align=\"center\"><span class=\"text-bold\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                        rs.close();
                    }
                }
                rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("                                                            <td align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>");
                }
                out.println("                                                     </tr>");
                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else {

                ResultSet rs = st.executeQuery("select MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) from gold_ln_tracker2 where country = '" + country + "' AND reportdate = '" + reportdate + "' GROUP BY MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) ORDER BY STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')");

                while (rs.next()) {
                    if (rs.getString(1) == null) {
                        monthList.add("Missing Dates");
                        out.println("                                                        <th style=\"text-align: center\">Missing Dates</th>\n");

                    } else {
                        monthList.add(rs.getString(1));
                        out.println("                                                        <th style=\"text-align: center\">" + rs.getString(1) + " " + rs.getString(2) + "</th>\n");

                    }
                }

                out.println("                                                        <th style=\"text-align: center\">Grand Total</th>\n"
                        + "                                                    </tr>\n"
                        + "                                                </thead>"
                        + "                                                  <tbody>");

                rs = st.executeQuery("select DISTINCT(sourcecancellation) from gold_ln_tracker2 where country = '" + country + "' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    String source;
                    source = rs.getString(1);

                    out.println("                                                  <tr>\n"
                            + "                                                        <td align=\"center\" class=\"warning\"><span class=\"info-box-text\"><b>" + source + "</b></span></td>\n");

                    Statement st2 = co.createStatement();
                    for (String month : monthList) {
                        if (month.equals("Missing Dates")) {
                            ResultSet rs2 = st2.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND sourcecancellation = '" + rs.getString(1) + "' AND acceptcanceldate = '--' AND reportdate  = '" + reportdate + "'");
                            while (rs2.next()) {
                                if (rs2.getString(1).equals("0")) {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>--</b></span></td>\n");

                                } else {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>" + rs2.getString(1) + "</b></span></td>\n");
                                }
                            }
                        } else {

                            ResultSet rs2 = st2.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND sourcecancellation = '" + rs.getString(1) + "' AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + month + "' AND reportdate  = '" + reportdate + "'");
                            while (rs2.next()) {
                                if (rs2.getString(1).equals("0")) {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>--</b></span></td>\n");

                                } else {
                                    out.println("                                                        <td align=\"center\"><span class=\"text-muted\"><b>" + rs2.getString(1) + "</b></span></td>\n");
                                }
                            }
                        }
                    }

                    ResultSet rs3 = st2.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND sourcecancellation = '" + rs.getString(1) + "' AND reportdate = '" + reportdate + "'");
                    while (rs3.next()) {
                        if (rs3.getString(1).equals("0")) {
                            out.println("                                                        <td align=\"center\" class=\"warning\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">--</font></span></td>");
                        } else {
                            out.println("                                                        <td align=\"center\" class=\"warning\" align=\"center\"><span class=\"badge\"><font style=\"font-size: 13px\">" + rs3.getString(1) + "</font></span></td>");

                        }
                    }

                    st2.close();

                    out.println("                                                  </tr>\n");
                }

                out.println("                                            </tbody>\n"
                        + "                                            <tfoot>");

                out.println("                                                    <tr class=\"warning\">\n"
                        + "                                                        <td align=\"center\"><span class=\"info-box-text\"><b>Grand Total</b></span></td>\n");

                for (String month : monthList) {
                    if (month.equals("Missing Dates")) {
                        rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND acceptcanceldate = '--' AND reportdate  = '" + reportdate + "'");
                        while (rs.next()) {
                            out.println("                                                        <td align=\"center\"><span class=\"text-bold\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                        rs.close();
                    } else {
                        rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + month + "' AND reportdate  = '" + reportdate + "'");
                        while (rs.next()) {
                            out.println("                                                        <td align=\"center\"><span class=\"text-bold\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                        rs.close();
                    }
                }
                rs = st.executeQuery("Select count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND reportdate = '" + reportdate + "'");
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
            Logger.getLogger(getTable_Gold_LexisNexis_MonthlySourceOfHires.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
