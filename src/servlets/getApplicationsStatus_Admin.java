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
import java.util.ArrayList;
import java.util.List;
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
public class getApplicationsStatus_Admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.json.JSONException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

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

            switch (request.getParameter("queryFlag")) {
                case "1": { //userid received
                    String id = request.getParameter("id");
                    //MakeShift Arrangement
                    if (id.length() > 1) {
                        id = id.substring(1);
                    }
                    JSONArray jarray = new JSONArray();
                    Class.forName(driver);
                    co = DriverManager.getConnection(host, userName, password);
                    Statement st = co.createStatement();
                    ResultSet rs = st.executeQuery("SELECT jobid,DATE_FORMAT(applydate,'%D %b %Y'),status FROM application_list where userid = '" + id + "'");
                    if (!rs.isBeforeFirst()) {
                        out.println("Empty");
                    } else {
                        while (rs.next()) {

                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("SELECT code,title,company,location,exp,skills FROM job_list WHERE code = '" + rs.getString(1) + "'");
                            while (rs2.next()) {
                                JSONObject jobject = new JSONObject();
                                jobject.put("id", rs2.getString(1));
                                jobject.put("title", rs2.getString(2));
                                jobject.put("company", rs2.getString(3));
                                jobject.put("location", rs2.getString(4));
                                jobject.put("exp", rs2.getString(5));
                                jobject.put("skills", rs2.getString(6));
                                jobject.put("applydate", rs.getString(2));
                                jobject.put("status", rs.getString(3));
                                jarray.put(jobject);
                            }
                            rs2.close();
                        }
                        out.println(jarray.toString());
                    }
                    rs.close();
                    break;
                }
                case "2": { //no parameter, all applications
                    JSONArray jarray = new JSONArray();
                    Class.forName(driver);
                    co = DriverManager.getConnection(host, userName, password);
                    Statement st = co.createStatement();
                    ResultSet rs = st.executeQuery("SELECT code,title,company,location,exp,skills FROM job_list WHERE code IN (Select jobid FROM application_list)");
                    if (!rs.isBeforeFirst()) {
                        out.println("Empty");
                    } else {
                        while (rs.next()) {
                            JSONObject jobject = new JSONObject();

                            jobject.put("jobid", rs.getString(1));
                            jobject.put("jobtitle", rs.getString(2));
                            jobject.put("jobclient", rs.getString(3));
                            jobject.put("joblocation", rs.getString(4));
                            jobject.put("jobexp", rs.getString(5));
                            jobject.put("jobskills", rs.getString(6));

                            JSONArray jarray2 = new JSONArray();
                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("SELECT id,name,location,country,curtitle,curcompany,email,phone,dpurl,skills FROM user_list WHERE id IN(SELECT userid FROM application_list WHERE jobid = '" + rs.getString(1) + "')");
                            while (rs2.next()) {
                                JSONObject jobject2 = new JSONObject();

                                jobject2.put("userid", rs2.getString(1));
                                jobject2.put("username", rs2.getString(2));
                                jobject2.put("userlocation", rs2.getString(3) + ", " + rs2.getString(4));
                                jobject2.put("userjobtitle", rs2.getString(5));
                                jobject2.put("usercompany", rs2.getString(6));
                                jobject2.put("useremail", rs2.getString(7));
                                jobject2.put("userphone", rs2.getString(8));
                                jobject2.put("userdpurl", rs2.getString(9));
                                jobject2.put("userskills", rs2.getString(10));

                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("SELECT id,DATE_FORMAT(applydate,'%D %b %Y'),status FROM application_list WHERE jobid = '" + rs.getString(1) + "' and userid = '" + rs2.getString(1) + "'");
                                while (rs3.next()) {

                                    jobject2.put("appid", rs3.getString(1));
                                    jobject2.put("applydate", rs3.getString(2));
                                    jobject2.put("status", rs3.getString(3));
                                }
                                rs3.close();
                                st3.close();

                                jarray2.put(jobject2);
                            }
                            rs2.close();
                            st2.close();

                            jobject.put("applicants", jarray2);

                            jarray.put(jobject);
                        }
                        rs.close();
                        st.close();
                        out.println(jarray.toString());
                    }

                    break;
                }
                case "3": { //jobid received
                    String code = request.getParameter("id");
                    JSONArray jarray = new JSONArray();
                    Class.forName(driver);
                    co = DriverManager.getConnection(host, userName, password);
                    Statement st = co.createStatement();
                    ResultSet rs = st.executeQuery("SELECT code,title,company,location,exp,skills FROM job_list WHERE code IN (Select jobid FROM application_list  WHERE code = '" + code + "')");
                    if (!rs.isBeforeFirst()) {
                        out.println("Empty");
                    } else {
                        while (rs.next()) {
                            JSONObject jobject = new JSONObject();

                            jobject.put("jobid", rs.getString(1));
                            jobject.put("jobtitle", rs.getString(2));
                            jobject.put("jobclient", rs.getString(3));
                            jobject.put("joblocation", rs.getString(4));
                            jobject.put("jobexp", rs.getString(5));
                            jobject.put("jobskills", rs.getString(6));

                            JSONArray jarray2 = new JSONArray();
                            Statement st2 = co.createStatement();
                            ResultSet rs2 = st2.executeQuery("SELECT id,name,location,country,curtitle,curcompany,email,phone,dpurl,skills FROM user_list WHERE id IN(SELECT userid FROM application_list WHERE jobid = '" + rs.getString(1) + "')");
                            while (rs2.next()) {
                                JSONObject jobject2 = new JSONObject();

                                jobject2.put("userid", rs2.getString(1));
                                jobject2.put("username", rs2.getString(2));
                                jobject2.put("userlocation", rs2.getString(3) + ", " + rs2.getString(4));
                                jobject2.put("userjobtitle", rs2.getString(5));
                                jobject2.put("usercompany", rs2.getString(6));
                                jobject2.put("useremail", rs2.getString(7));
                                jobject2.put("userphone", rs2.getString(8));
                                jobject2.put("userdpurl", rs2.getString(9));
                                jobject2.put("userskills", rs2.getString(10));

                                Statement st3 = co.createStatement();
                                ResultSet rs3 = st3.executeQuery("SELECT id,DATE_FORMAT(applydate,'%D %b %Y'),status FROM application_list WHERE jobid = '" + rs.getString(1) + "' and userid = '" + rs2.getString(1) + "'");
                                while (rs3.next()) {

                                    jobject2.put("appid", rs3.getString(1));
                                    jobject2.put("applydate", rs3.getString(2));
                                    jobject2.put("status", rs3.getString(3));
                                }
                                rs3.close();
                                st3.close();

                                jarray2.put(jobject2);
                            }
                            rs2.close();
                            st2.close();

                            jobject.put("applicants", jarray2);

                            jarray.put(jobject);
                        }
                        rs.close();
                        st.close();
                        out.println(jarray.toString());
                    }

                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            out.println("Error: ClassNotFoundException");
            out.println(ex.getMessage());
            Logger.getLogger(getApplicationsStatus_Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("Error: SQLException");
            out.println(ex.getMessage());
            Logger.getLogger(getApplicationsStatus_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(getApplicationsStatus_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(getApplicationsStatus_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
