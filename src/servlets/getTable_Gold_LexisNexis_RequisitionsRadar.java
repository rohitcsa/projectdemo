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
public class getTable_Gold_LexisNexis_RequisitionsRadar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexis_RequisitionsRadar.");

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

            out.println("                                            <thead>"
                    + "                                                <tr>"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Recruiter</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Plan</th>\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"success\">Sourcing/Approved<br>(<45 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"warning\">Sourcing/Approved<br>(45 - 60 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"danger\">Sourcing/Approved<br>(>60 Days)</th>\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"success\">Interview<br>(<45 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"warning\">Interview<br>(45 - 60 Days)</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"danger\">Interview<br>(>60 Days)</th>\n"
                    + "\n"
                    + "\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Open<br>Positions</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">On hold<br>Positions</th>\n"
                    + "                                                    <th style=\"text-align: center\" class=\"info\">Offers<br>Pending</th>\n"
                    + "                                                </tr>"
                    + "                                            </thead>"
                    + "                                            <tbody>");

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            if (country.equals("United Kingdom")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay') GROUP BY recruiter");

                List<String> recruiterList = new ArrayList<>();

                while (rs.next()) {
                    recruiterList.add(rs.getString(1));
                }

                for (String recruiter : recruiterList) {
                    out.println("                                                <tr align=\"center\">"
                            + "                                                    <td class=\"warning\"><span class=\"info-box-text\"><b>" + recruiter + "</b></span></td>\n");

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'To Be Approved' AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'On Hold' AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                }
                out.println("                                              </tr>");

                out.println("                                            </tbody>"
                        + "                                            <tfoot>");

                out.println("                                               <tr class=\"warning\" align=\"center\">"
                        + "                                                    <td><span class=\"info-box-text\"><b>GRAND TOTAL</b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "");

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND status IN ('Sourcing', 'Approved') AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND status = 'On Hold' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--') AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }

                out.println("                                                </tr>");

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') GROUP BY recruiter");

                List<String> recruiterList = new ArrayList<>();

                while (rs.next()) {
                    recruiterList.add(rs.getString(1));
                }

                for (String recruiter : recruiterList) {
                    out.println("                                                <tr align=\"center\">"
                            + "                                                    <td class=\"warning\"><span class=\"info-box-text\"><b>" + recruiter + "</b></span></td>\n");

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'To Be Approved' AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'On Hold' AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                }
                out.println("                                              </tr>");

                out.println("                                            </tbody>"
                        + "                                            <tfoot>");

                out.println("                                               <tr class=\"warning\" align=\"center\">"
                        + "                                                    <td><span class=\"info-box-text\"><b>GRAND TOTAL</b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "");

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') AND status IN ('Sourcing', 'Approved')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') AND status = 'On Hold'");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('Malaysia','Singapore') AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }

                out.println("                                                </tr>");

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else if (country.equals("Asia")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                List<String> recruiterList = new ArrayList<>();

                while (rs.next()) {
                    recruiterList.add(rs.getString(1));
                }

                for (String recruiter : recruiterList) {
                    out.println("                                                <tr align=\"center\">"
                            + "                                                    <td class=\"warning\"><span class=\"info-box-text\"><b>" + recruiter + "</b></span></td>\n");

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'To Be Approved' AND recruiter = '" + recruiter + "' AND country IN ('India','Japan','Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND noofcandidates NOT IN ('0','--') AND country IN ('India','Japan','Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND noofcandidates NOT IN ('0','--') AND country IN ('India','Japan','Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND noofcandidates NOT IN ('0','--') AND country IN ('India','Japan','Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND recruiter = '" + recruiter + "' AND noof1stround NOT IN ('0','--') AND country IN ('India','Japan','Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND recruiter = '" + recruiter + "' AND noof1stround NOT IN ('0','--') AND country IN ('India','Japan','Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND recruiter = '" + recruiter + "' AND noof1stround NOT IN ('0','--') AND country IN ('India','Japan','Malaysia','Singapore') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND country IN ('India','Japan','Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'On Hold' AND recruiter = '" + recruiter + "' AND country IN ('India','Japan','Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country IN ('India','Japan','Malaysia','Singapore')");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                }
                out.println("                                              </tr>");

                out.println("                                            </tbody>"
                        + "                                            <tfoot>");

                out.println("                                               <tr class=\"warning\" align=\"center\">"
                        + "                                                    <td><span class=\"info-box-text\"><b>GRAND TOTAL</b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "");

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('India','Japan','Malaysia','Singapore') AND status IN ('Sourcing', 'Approved')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('India','Japan','Malaysia','Singapore') AND status = 'On Hold'");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country IN ('India','Japan','Malaysia','Singapore') AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }

                out.println("                                                </tr>");

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            } else {
                ResultSet rs = st.executeQuery("Select DISTINCT(recruiter) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                List<String> recruiterList = new ArrayList<>();

                while (rs.next()) {
                    recruiterList.add(rs.getString(1));
                }

                for (String recruiter : recruiterList) {
                    out.println("                                                <tr align=\"center\">"
                            + "                                                    <td class=\"warning\"><span class=\"info-box-text\"><b>" + recruiter + "</b></span></td>\n");

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'To Be Approved' AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND noofcandidates NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 45");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"success\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 45 AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Interview') AND noof1stround NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 60");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td class=\"danger\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved') AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status = 'On Hold' AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }

                    rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--') AND recruiter = '" + recruiter + "' AND country = '" + country + "'");
                    while (rs.next()) {
                        if (rs.getString(1).equals("0")) {
                            out.println("                                              <td><span class=\"text-muted\"><b>--</b></span></td>\n");
                        } else {
                            out.println("                                              <td><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                        }
                    }
                }
                out.println("                                              </tr>");

                out.println("                                            </tbody>"
                        + "                                            <tfoot>");

                out.println("                                               <tr class=\"warning\" align=\"center\">"
                        + "                                                    <td><span class=\"info-box-text\"><b>GRAND TOTAL</b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + ""
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "                                                    <td><span class=\"info-box-text\"><b></b></span></td>\n"
                        + "");

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND status IN ('Sourcing', 'Approved')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND status = 'On Hold'");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }
                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND country = '" + country + "' AND status IN ('Sourcing','Approved','Offer') AND noofoffers NOT IN ('0','--')");
                while (rs.next()) {
                    out.println("                                               <td><span class=\"badge\"><font style=\"font-size: 13px\">" + rs.getString(1) + "</font></span></td>\n");
                }

                out.println("                                                </tr>");

                out.println("                                            </tfoot>");

                rs.close();
                st.close();
                co.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexis_RequisitionsRadar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
