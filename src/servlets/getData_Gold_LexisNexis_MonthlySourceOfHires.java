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
public class getData_Gold_LexisNexis_MonthlySourceOfHires extends HttpServlet {

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
                ResultSet rs = st.executeQuery("Select sourcecancellation,count(jobtitle) FROM gold_ln_tracker2 WHERE country IN ('Malaysia','Singapore') AND reportdate = '" + reportdate + "' GROUP BY sourcecancellation");

                while (rs.next()) {
                    JSONObject jobject1 = new JSONObject();
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    if (rs.getString(1).equals("--")) {
                        jobject1.put("Category", "Unknown");
                    } else {
                        jobject1.put("Category", rs.getString(1));
                    }
                    jobject1.put("count", rs.getString(2));
                    jarray.put(jobject1);
                }
                rs.close();
                st.close();
                co.close();

                out.println(jarray.toString());
            } else {

                ResultSet rs = st.executeQuery("Select sourcecancellation,count(jobtitle) FROM gold_ln_tracker2 WHERE country = '" + country + "' AND reportdate = '" + reportdate + "' GROUP BY sourcecancellation");

                while (rs.next()) {
                    JSONObject jobject1 = new JSONObject();
                    //System.out.println(rs.getString(1) + "," + rs.getString(2));
                    if (rs.getString(1).equals("--")) {
                        jobject1.put("Category", "Unknown");
                    } else {
                        jobject1.put("Category", rs.getString(1));
                    }
                    jobject1.put("count", rs.getString(2));
                    jarray.put(jobject1);
                }
                rs.close();
                st.close();
                co.close();
                out.println(jarray.toString());
            }
            //System.out.println("jarray at getData_Gold_LexisNexis_MonthlySourceOfHires : " + jarray.toString());

        } catch (JSONException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_MonthlySourceOfHires.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getData_Gold_LexisNexis_MonthlySourceOfHires.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
