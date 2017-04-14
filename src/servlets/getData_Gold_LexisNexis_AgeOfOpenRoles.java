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
public class getData_Gold_LexisNexis_AgeOfOpenRoles extends HttpServlet {

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

            JSONObject jobject1 = new JSONObject();
            JSONObject jobject2 = new JSONObject();
            JSONObject jobject3 = new JSONObject();

            Statement st = co.createStatement();

            if (country.equals("United Kingdom")) {
                ResultSet rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 30 AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");

                jobject1.put("Category", "< 30 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put("roles", rs.getString(1));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 30 AND  ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 40 AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");

                jobject2.put("Category", "30 - 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put("roles", rs.getString(1));
                }
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 40 AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')");

                jobject3.put("Category", "> 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put("roles", rs.getString(1));
                }
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs.close();
                st.close();
                co.close();
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_Yougov_AgeOfOpenRoles : " + jarray.toString());
            } else if (country.equals("Asia")) {
                ResultSet rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status != 'On Hold' AND status != 'To Be Approved' AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 30 AND country IN('India','Japan','Malaysia','Singapore')");

                jobject1.put("Category", "< 30 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put("roles", rs.getString(1));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 30 AND  ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 40 AND country IN('India','Japan','Malaysia','Singapore')");

                jobject2.put("Category", "30 - 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put("roles", rs.getString(1));
                }
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 40 AND country IN('India','Japan','Malaysia','Singapore')");

                jobject3.put("Category", "> 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put("roles", rs.getString(1));
                }
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs.close();
                st.close();
                co.close();
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_Yougov_AgeOfOpenRoles : " + jarray.toString());
            } else if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 30 AND country IN('Malaysia','Singapore')");

                jobject1.put("Category", "< 30 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put("roles", rs.getString(1));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 30 AND  ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 40 AND country IN('Malaysia','Singapore')");

                jobject2.put("Category", "30 - 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put("roles", rs.getString(1));
                }
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 40 AND country IN('Malaysia','Singapore')");

                jobject3.put("Category", "> 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put("roles", rs.getString(1));
                }
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs.close();
                st.close();
                co.close();
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_Yougov_AgeOfOpenRoles : " + jarray.toString());
            } else {
                ResultSet rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) < 30 AND country = '" + country + "'");

                jobject1.put("Category", "< 30 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put("roles", rs.getString(1));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) >= 30 AND  ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) <= 40 AND country = '" + country + "'");

                jobject2.put("Category", "30 - 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put("roles", rs.getString(1));
                }
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select COUNT(jobtitle) from gold_ln_tracker WHERE reportdate = '" + reportdate + "' AND status IN ('Approved','Sourcing') AND ABS(DATEDIFF('" + reportdate + "',STR_TO_DATE(openingdate,'%m/%d/%Y'))) > 40 AND country = '" + country + "'");

                jobject3.put("Category", "> 40 Days");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put("roles", rs.getString(1));
                }
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs.close();
                st.close();
                co.close();
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_Yougov_AgeOfOpenRoles : " + jarray.toString());
            }
        } catch (JSONException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_AgeOfOpenRoles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_AgeOfOpenRoles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
