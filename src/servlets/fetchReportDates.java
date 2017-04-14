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

public class fetchReportDates extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Reached @ fetchReportDates.");
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
            if (client.equals("Gold_Yougov_Weekly")) {

                ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM bronze_yougov_tracker WHERE reportdate != '2016-08-18'");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Bronze_Yougov_Weekly")) {

                ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM bronze_yougov_tracker WHERE reportdate != '2016-08-18'");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Bronze_Demo_Weekly")) {

                ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM bronze_yougov_tracker WHERE reportdate = '2016-08-18'");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Bronze_LexisNexis_Weekly")) {

                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM bronze_openrolestracker");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM bronze_openrolestracker");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM bronze_openrolestracker where YEAR(reportdate) = '" + rs.getString(1) + "'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM bronze_openrolestracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM bronze_openrolestracker");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }

            } else if (client.equals("Bronze_Temenos_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM bronze_temenos_tracker");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM bronze_temenos_tracker");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM bronze_temenos_tracker where YEAR(reportdate) = '" + rs.getString(1) + "'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM bronze_temenos_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM bronze_temenos_tracker");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Bronze_ACS_Weekly")) {

                ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM bronze_acs_tracker");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Gold_LexisNexisAsia_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country IN ('India','Japan','Malaysia','Singapore')");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker  where  country IN ('India','Japan','Malaysia','Singapore')");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND country IN ('India','Japan','Malaysia','Singapore')");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "' AND  country IN ('India','Japan','Malaysia','Singapore')");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country IN ('India','Japan','Malaysia','Singapore')");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisFrance_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country = 'France'");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker where country = 'France'");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND country = 'France'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "' AND country = 'France'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country = 'France'");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisIndia_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country = 'India'");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker where country = 'India'");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND country = 'India'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "' AND country = 'India'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country = 'India'");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisJapan_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country = 'Japan'");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker where country = 'Japan'");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND country = 'Japan'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "' AND country = 'Japan'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country = 'Japan'");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisMalSing_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country IN ('Malaysia','Singapore')");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker where country IN ('Malaysia','Singapore')");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND country IN ('Malaysia','Singapore')");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "' AND country IN ('Malaysia','Singapore')");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country IN ('Malaysia','Singapore')");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisSA_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country = 'South Africa'");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker where country = 'South Africa'");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "'  AND country = 'South Africa'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "' AND country = 'South Africa'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country = 'South Africa'");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisUK_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_ln_tracker where country = 'United Kingdom'");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_ln_tracker where country = 'United Kingdom'");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND country = 'United Kingdom'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_ln_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "'  AND country = 'United Kingdom'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_ln_tracker where country = 'United Kingdom'");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_CitrixChina_Weekly")) {
                Statement stCheck = co.createStatement();
                ResultSet rsCheck = stCheck.executeQuery("Select COUNT(DISTINCT(reportdate)) FROM gold_citrixchina_tracker");
                while (rsCheck.next()) {
                    if (Integer.parseInt(rsCheck.getString(1)) > 10) {
                        ResultSet rs = st.executeQuery("Select DISTINCT(YEAR(reportdate)) FROM gold_citrixchina_tracker");
                        while (rs.next()) {
                            out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs.getString(1) + "</a>");
                            out.println("<ul>");

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("Select DISTINCT(MONTHNAME(reportdate)) FROM gold_citrixchina_tracker where YEAR(reportdate) = '" + rs.getString(1) + "'");
                            while (rs2.next()) {
                                out.println("<li><a href=\"#\" onclick=\"event.preventDefault();\">" + rs2.getString(1) + "</a>");
                                out.println("<ul>");
                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %b %Y') FROM gold_citrixchina_tracker where YEAR(reportdate) = '" + rs.getString(1) + "' AND MONTHNAME(reportdate) = '" + rs2.getString(1) + "'");
                                while (rs3.next()) {
                                    if (rs.isLast()) {
                                        if (rs2.isLast()) {
                                            if (rs3.isLast()) {
                                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            } else {
                                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                            }
                                        } else {
                                            out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                        }
                                    } else {
                                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs3.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs3.getString(2) + "</a></li>");
                                    }
                                }
                                rs3.close();
                                st3.close();
                                out.println("</ul>");
                                out.println("</li>");
                            }
                            rs2.close();
                            st2.close();
                            out.println("</ul>");
                            out.println("</li>");
                        }
                        rs.close();

                    } else {

                        ResultSet rs = st.executeQuery("Select DISTINCT(reportdate),DATE_FORMAT(reportdate, '%d %M %Y') FROM gold_citrixchina_tracker");
                        while (rs.next()) {
                            if (rs.isLast()) {
                                out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            } else {
                                out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + "</a></li>");
                            }
                        }
                        rs.close();
                    }
                }
                rsCheck.close();
                stCheck.close();

            } else if (client.equals("Gold_LexisNexisFrance_Monthly")) {

                ResultSet rs = st.executeQuery("Select MAX(reportdate),MONTHNAME(reportdate),YEAR(reportdate) FROM gold_ln_tracker WHERE country = 'France' AND DATE_FORMAT(reportdate,'%M %Y') != DATE_FORMAT(NOW(),'%M %Y') GROUP BY MONTHNAME(reportdate),YEAR(reportdate) ORDER BY reportdate");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Gold_LexisNexisIndia_Monthly")) {

                ResultSet rs = st.executeQuery("Select MAX(reportdate),MONTHNAME(reportdate),YEAR(reportdate) FROM gold_ln_tracker WHERE country = 'India' AND DATE_FORMAT(reportdate,'%M %Y') != DATE_FORMAT(NOW(),'%M %Y')  GROUP BY MONTHNAME(reportdate),YEAR(reportdate) ORDER BY reportdate");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Gold_LexisNexisJapan_Monthly")) {

                ResultSet rs = st.executeQuery("Select MAX(reportdate),MONTHNAME(reportdate),YEAR(reportdate) FROM gold_ln_tracker WHERE country = 'Japan' AND DATE_FORMAT(reportdate,'%M %Y') != DATE_FORMAT(NOW(),'%M %Y') GROUP BY MONTHNAME(reportdate),YEAR(reportdate) ORDER BY reportdate");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Gold_LexisNexisMalSing_Monthly")) {

                ResultSet rs = st.executeQuery("Select MAX(reportdate),MONTHNAME(reportdate),YEAR(reportdate) FROM gold_ln_tracker WHERE country IN ('Malaysia','Singapore') AND DATE_FORMAT(reportdate,'%M %Y') != DATE_FORMAT(NOW(),'%M %Y') GROUP BY MONTHNAME(reportdate),YEAR(reportdate) ORDER BY reportdate");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Gold_LexisNexisSA_Monthly")) {

                ResultSet rs = st.executeQuery("Select MAX(reportdate),MONTHNAME(reportdate),YEAR(reportdate) FROM gold_ln_tracker  WHERE country = 'South Africa' AND DATE_FORMAT(reportdate,'%M %Y') != DATE_FORMAT(NOW(),'%M %Y') GROUP BY MONTHNAME(reportdate),YEAR(reportdate) ORDER BY reportdate");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    }
                }
                rs.close();

            } else if (client.equals("Gold_LexisNexisUK_Monthly")) {

                ResultSet rs = st.executeQuery("Select MAX(reportdate),MONTHNAME(reportdate),YEAR(reportdate) FROM gold_ln_tracker  WHERE country = 'United Kingdom' AND DATE_FORMAT(reportdate,'%M %Y') != DATE_FORMAT(NOW(),'%M %Y') GROUP BY MONTHNAME(reportdate),YEAR(reportdate) ORDER BY reportdate");
                while (rs.next()) {
                    if (rs.isLast()) {
                        out.println("<li><a href=\"#\" name=\"lastReportDate\" value=\"lastReportDate\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    } else {
                        out.println("<li><a href=\"#\" class=\"setReportDateHref\" id=\"" + rs.getString(1) + "\" onclick=\"event.preventDefault();\">" + rs.getString(2) + " " + rs.getString(3) + "</a></li>");
                    }
                }
                rs.close();

            }
            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(fetchReportDates.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
