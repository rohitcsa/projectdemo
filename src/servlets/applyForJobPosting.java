/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tanuj_000
 */
public class applyForJobPosting extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("Reached @ applyForJobPosting");

            String UserId = request.getParameter("UserId");
            //MakeShift Arrangement
            if (UserId.length() == 2) {
                UserId = UserId.substring(1);
            }
            String UserName = request.getParameter("UserName");
            String JobId = request.getParameter("JobId");
            String JobName = request.getParameter("JobName");
            String Answers = request.getParameter("Answers");

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

            Class.forName(driver);

            Connection co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery("Select MAX(id) from application_list");
            while (rs.next()) {

                if (rs.getString(1) == null) {

                    Connection co2 = DriverManager.getConnection(host, userName, password);
                    Statement st2 = co2.createStatement();

                    int rs2 = st2.executeUpdate("INSERT INTO application_list VALUES('1','" + JobId + "','" + JobName + "','" + UserId + "','" + UserName + "',NOW(),null,'In Process')");

                    if (rs2 == 1) {
                        out.println("Applied successfully!");
                    } else {
                        out.println("Application failed.");
                    }

                    co2.close();
                    st2.close();

                } else {

                    String id = String.valueOf(Integer.valueOf(rs.getString(1)) + 1);

                    Connection co2 = DriverManager.getConnection(host, userName, password);
                    Statement st2 = co2.createStatement();
                    int rsInt = st2.executeUpdate("INSERT INTO application_list VALUES('" + id + "','" + JobId + "','" + JobName + "','" + UserId + "','" + UserName + "',NOW(),null,'In Process')");

                    if (rsInt == 1) {
                        out.println("Applied successfully!");
                    } else {
                        out.println("Application failed.");
                    }

                    co2.close();
                    st2.close();
                }

            }
            st.close();
            co.close();
        } catch (ClassNotFoundException ex) {
            out.println("Error: ClassNotFoundException");
            out.println(ex.getMessage());
            Logger.getLogger(applyForJobPosting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("Error: SQLException");
            out.println(ex.getMessage());
            Logger.getLogger(applyForJobPosting.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
