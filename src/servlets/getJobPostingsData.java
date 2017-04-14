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

/**
 *
 * @author tanuj_000
 */
public class getJobPostingsData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        PrintWriter out = response.getWriter();
        String UserId = request.getParameter("UserId");
        //MakeShift Arrangement
        if (UserId.length() > 1) {
            UserId = UserId.substring(1);
        }

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

        response.setContentType("text/html;charset=UTF-8");
        try {
            JSONArray jarray = new JSONArray();

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,questions FROM job_list WHERE status = 'Active' AND code NOT IN (SELECT jobid from application_list where userid = '" + UserId + "')");

            while (rs.next()) {
                //System.out.println(rs.getString(1) + "," + rs.getString(2));
                JSONObject jobject = new JSONObject();
                jobject.put("code", rs.getString(1));
                jobject.put("title", rs.getString(2));
                jobject.put("industry", rs.getString(3));
                jobject.put("company", rs.getString(4));
                jobject.put("location", rs.getString(5));
                jobject.put("country", rs.getString(6));
                jobject.put("pincode", rs.getString(7));
                jobject.put("exp", rs.getString(8));
                jobject.put("skills", rs.getString(9));
                jobject.put("brief", rs.getString(10));
                jobject.put("companybrief", rs.getString(11));
                jobject.put("questionnaire", rs.getString(12));
                jarray.put(jobject);
            }

            out.println(jarray.toString());

        } catch (ClassNotFoundException ex) {
            out.println("Error: ClassNotFoundException");
            out.println(ex.getMessage());
            Logger.getLogger(getJobPostingsData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("Error: SQLException");
            out.println(ex.getMessage());
            Logger.getLogger(getJobPostingsData.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(getJobPostingsData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(getJobPostingsData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
