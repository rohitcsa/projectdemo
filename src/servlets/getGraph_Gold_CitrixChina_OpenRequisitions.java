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
public class getGraph_Gold_CitrixChina_OpenRequisitions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //String reportdate = request.getParameter("reportdate");
        //Connection co = null;
        //String host = getServletContext().getInitParameter("host");
        //String driver = getServletContext().getInitParameter("driver");
        //String userName = getServletContext().getInitParameter("userName");
        //String password = getServletContext().getInitParameter("password");
        try {

            //Class.forName(driver);
            //co = DriverManager.getConnection(host, userName, password);
            //Statement st = co.createStatement();
            //ResultSet rs = st.executeQuery("Select DISTINCT(recruiter) from gold_ln_tracker WHERE status!='Filled' AND status!='On hold' AND status!='Cancelled' AND status!='Hired' AND status!='On Hold' AND reportdate='" + reportdate + "' GROUP BY recruiter");
            JSONArray jarray = new JSONArray();

            //while (rs.next()) {
            String country = "Grand Total"; //get the Employee
            JSONObject jobject = new JSONObject();
            jobject.put("balloonText", "[[title]] - [[Category]] : [[value]]");
            jobject.put("fillAlphas", "1");
            jobject.put("labelText", "[[value]]");
            jobject.put("fontSize", "16");
            jobject.put("title", country);
            jobject.put("type", "column");
            jobject.put("valueField", country);
            jarray.put(jobject);

            //}
            //rs.close();
            //st.close();
            //co.close();
            out.println(jarray.toString());
            //System.out.println("jarray at getGraph_Gold_CitrixChina_OpenRequisitions: " + jarray.toString());

        } catch (JSONException ex) {
            Logger.getLogger(getGraph_Gold_CitrixChina_OpenRequisitions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
