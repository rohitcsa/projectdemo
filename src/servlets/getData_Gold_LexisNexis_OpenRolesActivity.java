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
public class getData_Gold_LexisNexis_OpenRolesActivity extends HttpServlet {

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
            JSONObject jobject4 = new JSONObject();
            JSONObject jobject5 = new JSONObject();

            Statement st = co.createStatement();
            ResultSet rs = null;
            if (country.equals("United Kingdom")) {

                rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "'  AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay') GROUP BY recruiter");

                jobject1.put("Category", "No. of Roles");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put(rs.getString(1), rs.getString(2));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noofcandidates) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "'  AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay') GROUP BY recruiter");

                jobject2.put("Category", "No. of CVs Sent");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof1stround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')  GROUP BY recruiter");

                jobject3.put("Category", "No. of 1st Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof2ndround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')  GROUP BY recruiter");

                jobject4.put("Category", "No. of 2nd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject4.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject4);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' AND recruiter IN( 'Garba, Anthony','Pollard, Luke','Glover, Tom','Leviton, Laura','Ross, Fay')  GROUP BY recruiter");

                jobject5.put("Category", "No. of 3rd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject5.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                st.close();
                co.close();
                jarray.put(jobject5);
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_OpenRolesActivity: " + jarray.toString());
            } else if (country.equals("Malsing")) {

                rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore') GROUP BY recruiter");

                jobject1.put("Category", "No. of Roles");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put(rs.getString(1), rs.getString(2));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noofcandidates) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore') GROUP BY recruiter");

                jobject2.put("Category", "No. of CVs Sent");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof1stround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore') GROUP BY recruiter");

                jobject3.put("Category", "No. of 1st Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof2ndround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore') GROUP BY recruiter");

                jobject4.put("Category", "No. of 2nd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject4.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject4);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('Malaysia','Singapore') GROUP BY recruiter");

                jobject5.put("Category", "No. of 3rd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject5.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                st.close();
                co.close();
                jarray.put(jobject5);
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_OpenRolesActivity: " + jarray.toString());
            } else if (country.equals("Asia")) {

                rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                jobject1.put("Category", "No. of Roles");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put(rs.getString(1), rs.getString(2));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noofcandidates) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                jobject2.put("Category", "No. of CVs Sent");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof1stround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                jobject3.put("Category", "No. of 1st Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof2ndround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                jobject4.put("Category", "No. of 2nd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject4.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject4);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country IN('India','Japan','Malaysia','Singapore') GROUP BY recruiter");

                jobject5.put("Category", "No. of 3rd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject5.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                st.close();
                co.close();
                jarray.put(jobject5);
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_OpenRolesActivity: " + jarray.toString());
            } else {

                rs = st.executeQuery("Select DISTINCT(recruiter),COUNT(jobtitle) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                jobject1.put("Category", "No. of Roles");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject1.put(rs.getString(1), rs.getString(2));
                }

                rs.close();
                jarray.put(jobject1);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noofcandidates) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                jobject2.put("Category", "No. of CVs Sent");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject2.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject2);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof1stround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                jobject3.put("Category", "No. of 1st Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject3.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject3);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof2ndround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                jobject4.put("Category", "No. of 2nd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject4.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                jarray.put(jobject4);
                //System.out.println(jarray.toString());

                rs = st.executeQuery("Select DISTINCT(recruiter),SUM(noof3rdround) FROM gold_ln_tracker WHERE status!='On Hold' AND status !='To Be Approved' AND reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY recruiter");

                jobject5.put("Category", "No. of 3rd Round Interviews");
                while (rs.next()) {
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    jobject5.put(rs.getString(1), rs.getString(2));
                }
                rs.close();
                st.close();
                co.close();
                jarray.put(jobject5);
                //System.out.println(jarray.toString());

                out.println(jarray.toString());
                //System.out.println("jarray at getData_Bronze_OpenRolesActivity: " + jarray.toString());
            }
        } catch (JSONException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_OpenRolesActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_OpenRolesActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
