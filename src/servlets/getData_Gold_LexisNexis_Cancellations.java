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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 *
 * @author tanuj_000
 */
public class getData_Gold_LexisNexis_Cancellations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            JSONArray jarray = new JSONArray();

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);

            Statement st = co.createStatement();
            if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y'))),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) FROM gold_ln_tracker3 WHERE country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "' AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = YEAR('" + reportdate + "') ORDER BY STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')");
                while (rs.next()) {
                    String acceptcanceldatemonth = rs.getString(1);
                    String acceptcanceldateyear = rs.getString(2);

                    JSONObject jobject = new JSONObject();
                    jobject = jobject.put("Category", rs.getString(1) + " " + rs.getString(2));

                    Statement st2 = co.createStatement();
                    ResultSet rs2 = st2.executeQuery("Select MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')),COUNT(jobtitle) FROM gold_ln_tracker3 WHERE country IN ('Malaysia','Singapore') AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldatemonth + "' AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldateyear + "' AND reportdate = '" + reportdate + "'");

                    while (rs2.next()) {
                        //System.out.println(filleddate + " - " + rs2.getString(1) + "," + rs2.getString(2));
                        jobject.put("Grand Total", rs2.getString(3));
                    }

                    jarray.put(jobject);
                    //System.out.println(jarray.toString());

                }
                rs.close();
                st.close();
                co.close();
                out.println(jarray.toString());

            } else {

                ResultSet rs = st.executeQuery("Select DISTINCT(MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y'))),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) FROM gold_ln_tracker3 WHERE country = '" + country + "' AND reportdate = '" + reportdate + "' AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = YEAR('" + reportdate + "') ORDER BY STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')");
                while (rs.next()) {
                    String acceptcanceldatemonth = rs.getString(1);
                    String acceptcanceldateyear = rs.getString(2);

                    JSONObject jobject = new JSONObject();
                    jobject = jobject.put("Category", rs.getString(1) + " " + rs.getString(2));

                    Statement st2 = co.createStatement();
                    ResultSet rs2 = st2.executeQuery("Select MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')),YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')),COUNT(jobtitle) FROM gold_ln_tracker3 WHERE country = '" + country + "' AND MONTHNAME(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldatemonth + "' AND YEAR(STR_TO_DATE(acceptcanceldate,'%m/%d/%Y')) = '" + acceptcanceldateyear + "' AND reportdate = '" + reportdate + "'");

                    while (rs2.next()) {
                        //System.out.println(filleddate + " - " + rs2.getString(1) + "," + rs2.getString(2));
                        jobject.put("Grand Total", rs2.getString(3));
                    }

                    jarray.put(jobject);
                    //System.out.println(jarray.toString());

                }
                rs.close();
                st.close();
                co.close();
                out.println(jarray.toString());
            }
            //System.out.println("jarray at getData_Gold_LexisNexis_Cancellations: " + jarray.toString());

        } catch (JSONException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_Cancellations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_Cancellations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
