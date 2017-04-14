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
public class getGraph_Gold_LexisNexis_FilledRequisitions extends HttpServlet {

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

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            if (country.equals("Malsing")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(sourcecancellation) from gold_ln_tracker2 WHERE reportdate='" + reportdate + "' AND country IN ('Malaysia','Singapore') GROUP BY sourcecancellation");

                JSONArray jarray = new JSONArray();

                while (rs.next()) {
                    String source = rs.getString(1); //get the Employee
                    JSONObject jobject = new JSONObject();
                    jobject.put("balloonText", "[[title]] - [[Category]] : [[value]]");
                    jobject.put("fillAlphas", "1");
                    jobject.put("labelText", "[[value]]");
                    jobject.put("fontSize", "16");
                    jobject.put("title", source);
                    jobject.put("type", "column");
                    jobject.put("valueField", source);
                    jarray.put(jobject);

                }
                rs.close();
                st.close();
                co.close();

                out.println(jarray.toString());
                //System.out.println("jarray at getGraph_Gold_LexisNexisIndia_FilledRequisitions: " + jarray.toString());
            } else if (country.equals("Asia")) {
                ResultSet rs = st.executeQuery("Select DISTINCT(sourcecancellation) from gold_ln_tracker2 WHERE reportdate='" + reportdate + "' AND country IN ('India','Japan','Malaysia','Singapore') GROUP BY sourcecancellation");

                JSONArray jarray = new JSONArray();

                while (rs.next()) {
                    String source = rs.getString(1); //get the Employee
                    JSONObject jobject = new JSONObject();
                    jobject.put("balloonText", "[[title]] - [[Category]] : [[value]]");
                    jobject.put("fillAlphas", "1");
                    jobject.put("labelText", "[[value]]");
                    jobject.put("fontSize", "16");
                    jobject.put("title", source);
                    jobject.put("type", "column");
                    jobject.put("valueField", source);
                    jarray.put(jobject);

                }
                rs.close();
                st.close();
                co.close();

                out.println(jarray.toString());
                //System.out.println("jarray at getGraph_Gold_LexisNexisIndia_FilledRequisitions: " + jarray.toString());
            } else {
                ResultSet rs = st.executeQuery("Select DISTINCT(sourcecancellation) from gold_ln_tracker2 WHERE reportdate='" + reportdate + "' AND country = '" + country + "' GROUP BY sourcecancellation");

                JSONArray jarray = new JSONArray();

                while (rs.next()) {
                    String source = rs.getString(1); //get the Employee
                    JSONObject jobject = new JSONObject();
                    jobject.put("balloonText", "[[title]] - [[Category]] : [[value]]");
                    jobject.put("fillAlphas", "1");
                    jobject.put("labelText", "[[value]]");
                    jobject.put("fontSize", "16");
                    jobject.put("title", source);
                    jobject.put("type", "column");
                    jobject.put("valueField", source);
                    jarray.put(jobject);

                }
                rs.close();
                st.close();
                co.close();

                out.println(jarray.toString());
                //System.out.println("jarray at getGraph_Gold_LexisNexisIndia_FilledRequisitions: " + jarray.toString());
            }
        } catch (JSONException ex) {
            Logger.getLogger(getGraph_Gold_LexisNexis_FilledRequisitions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getGraph_Gold_LexisNexis_FilledRequisitions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
