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
public class getData_Bronze_Temenos_OpenRolesActivity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String reportdate = request.getParameter("reportdate");

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
            JSONObject jobject6 = new JSONObject();

            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("Select noof1stround FROM bronze_temenos_activity WHERE recruiter = 'Grand Total' AND reportdate='" + reportdate + "'");

            jobject1.put("Category", "No. of 1st Round Interviews");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject1.put("Grand Total", rs.getString(1));
            }

            rs.close();
            jarray.put(jobject1);
            //System.out.println(jarray.toString());

            rs = st.executeQuery("Select noof2ndround FROM bronze_temenos_activity WHERE recruiter = 'Grand Total' AND reportdate='" + reportdate + "'");

            jobject2.put("Category", "No. of 2nd Round Interviews");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject2.put("Grand Total", rs.getString(1));
            }
            rs.close();
            jarray.put(jobject2);
            //System.out.println(jarray.toString());

            rs = st.executeQuery("Select noof3rdround FROM bronze_temenos_activity WHERE recruiter = 'Grand Total' AND reportdate='" + reportdate + "'");

            jobject3.put("Category", "No. of Final Round Interviews");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject3.put("Grand Total", rs.getString(1));
            }
            rs.close();
            jarray.put(jobject3);
            //System.out.println(jarray.toString());

            rs = st.executeQuery("Select noofoffer FROM bronze_temenos_activity WHERE recruiter = 'Grand Total' AND reportdate='" + reportdate + "'");

            jobject4.put("Category", "No. of Offers");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject4.put("Grand Total", rs.getString(1));
            }
            rs.close();
            jarray.put(jobject4);
            //System.out.println(jarray.toString());

            rs = st.executeQuery("Select noofofferaccept FROM bronze_temenos_activity WHERE recruiter = 'Grand Total' AND reportdate='" + reportdate + "'");

            jobject5.put("Category", "No. of Offers Accepted");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject5.put("Grand Total", rs.getString(1));
            }
            rs.close();
            jarray.put(jobject5);
            //System.out.println(jarray.toString());

            rs = st.executeQuery("Select noofofferreject FROM bronze_temenos_activity WHERE recruiter = 'Grand Total' AND reportdate='" + reportdate + "'");

            jobject6.put("Category", "No. of Offers Rejected");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject6.put("Grand Total", rs.getString(1));
            }
            rs.close();
            jarray.put(jobject6);
            //System.out.println(jarray.toString());

            rs.close();
            st.close();
            co.close();
            //System.out.println(jarray.toString());

            out.println(jarray.toString());
            //System.out.println("jarray at getData_Bronze_OpenRolesActivity: " + jarray.toString());

        } catch (JSONException ex) {
            Logger.getLogger(getData_Bronze_Temenos_OpenRolesActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getData_Bronze_Temenos_OpenRolesActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
