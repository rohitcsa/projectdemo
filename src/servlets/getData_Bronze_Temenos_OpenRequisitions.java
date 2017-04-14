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
public class getData_Bronze_Temenos_OpenRequisitions extends HttpServlet {

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
            JSONObject jobject1 = new JSONObject();

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("select DISTINCT(region),COUNT(jobtitle) from bronze_temenos_tracker WHERE stage!='Filled' AND stage!='Filled (NP)' AND stage!='Filled (Temenos)' AND stage!='Filled (3rd Party)' AND stage!='On hold' AND stage!='Cancelled' AND reportdate='" + reportdate + "' GROUP BY region ORDER BY region");

            jobject1.put("Category", "No. of Roles");
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                jobject1.put(rs.getString(1), rs.getString(2));
            }

            rs.close();
            st.close();
            co.close();
            jarray.put(jobject1);
            //System.out.println(jarray.toString());

            out.println(jarray.toString());
            //System.out.println("jarray at getData_Bronze_OpenRequisitions: " + jarray.toString());

        } catch (JSONException ex) {
            Logger.getLogger(getData_Bronze_Temenos_OpenRequisitions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getData_Bronze_Temenos_OpenRequisitions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
