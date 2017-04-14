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
import java.util.Arrays;
import java.util.LinkedList;
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
public class getJobPostingsData_Admin extends HttpServlet {

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

            if (request.getParameter("queryFlag").equals("1")) {

                String code = request.getParameter("code");
                String title = request.getParameter("title");

                JSONArray jarray = new JSONArray();

                Class.forName(driver);
                co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list WHERE code = '" + code + "' AND title='" + title + "'");
                if (!rs.isBeforeFirst()) {
                    out.println("Empty");
                } else {
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
                        jobject.put("status", rs.getString(12));
                        jarray.put(jobject);
                    }
                    rs.close();
                    st.close();
                    co.close();

                    out.println(jarray.toString());
                }

            }
            if (request.getParameter("queryFlag").equals("2")) { //FIlter applied

                String skillList = "", skillQuery = "";
                String countryList = "", countryQuery = "";
                String orderBy = "";
                List<String> skills = null;
                List<String> countries = null;

                String sortBy = request.getParameter("sortBy");
                switch (sortBy) {
                    case "1":
                        orderBy = " ORDER BY title";
                        break;
                    case "2":
                        orderBy = "ORDER BY title DESC";
                        break;
                }

                if (request.getParameter("skillsList").equals("") == false) {

                    skillList = request.getParameter("skillsList");
                    skillList = skillList.substring(1, skillList.length() - 1);
                    skills = new LinkedList<>(Arrays.asList(skillList.split("\\s*,\\s*")));

                    if (skills.size() == 1) {
                        skillQuery = " skills REGEXP '" + skills.get(0) + "'";
                    } else {
                        for (int i = 0; i < skills.size(); i++) {
                            if (skillQuery.equals("")) {
                                skillQuery = " skills REGEXP '" + skills.get(i);
                            } else if (i == skills.size() - 1) {
                                skillQuery = skillQuery + "|" + skills.get(i) + "'";
                            } else {
                                skillQuery = skillQuery + "|" + skills.get(i);
                            }
                        }
                    }

                }

                if (request.getParameter("countriesList").equals("") == false) {

                    countryList = request.getParameter("countriesList");
                    countryList = countryList.substring(1, countryList.length() - 1);
                    countries = new LinkedList<>(Arrays.asList(countryList.split("\\s*,\\s*")));

                    if (countries.size() == 1) {
                        countryQuery = " country IN ('" + countries.get(0) + "')";
                    } else {
                        for (int i = 0; i < countries.size(); i++) {
                            if (countryQuery.equals("")) {
                                countryQuery = " country IN ('" + countries.get(i) + "'";
                            } else if (i == countries.size() - 1) {
                                countryQuery = countryQuery + ",'" + countries.get(i) + "')";
                            } else {
                                countryQuery = countryQuery + ",'" + countries.get(i) + "'";
                            }
                        }
                    }
                }

                Class.forName(driver);
                co = DriverManager.getConnection(host, userName, password);

                JSONArray jarray = new JSONArray();

                Statement st = co.createStatement();
                ResultSet rs = null;
                if (skills != null && countries != null) {
                    rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list WHERE " + skillQuery + " AND " + countryQuery + orderBy);

                } else if (skills == null && countries != null) {
                    rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list WHERE " + countryQuery + orderBy);

                } else if (skills != null && countries == null) {
                    rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list WHERE " + skillQuery + orderBy);

                } else {
                    if (orderBy.equals("0")) {
                        rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list");
                    } else {
                        rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list " + orderBy);
                    }
                }
                if (!rs.isBeforeFirst()) {
                    out.println("Empty");
                } else {
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
                        jobject.put("status", rs.getString(12));
                        jarray.put(jobject);
                    }
                    rs.close();
                    st.close();
                    co.close();

                    out.println(jarray.toString());
                }

            } else {

                JSONArray jarray = new JSONArray();

                Class.forName(driver);
                co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery("SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,status FROM job_list");

                if (!rs.isBeforeFirst()) {
                    out.println("Empty");
                } else {
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
                        jobject.put("status", rs.getString(12));
                        jarray.put(jobject);
                    }
                    rs.close();
                    st.close();
                    co.close();

                    out.println(jarray.toString());
                }
            }
        } catch (ClassNotFoundException ex) {
            out.println("Error: ClassNotFoundException");
            out.println(ex.getMessage());
            Logger.getLogger(getJobPostingsData_Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("Error: SQLException");
            out.println(ex.getMessage());
            Logger.getLogger(getJobPostingsData_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(getJobPostingsData_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(getJobPostingsData_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";

    }// </editor-fold>

}
