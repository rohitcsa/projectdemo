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
public class uploadUserData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String Name = request.getParameter("Name");
            List<String> Location = Arrays.asList(request.getParameter("Location").split("\\s*,\\s*"));
            String Email = request.getParameter("Email");
            String DpURL = request.getParameter("DpURL");
            String Title = request.getParameter("Title");
            String Company = request.getParameter("Company");
            String Industry = request.getParameter("Industry");
            String StartDate = request.getParameter("StartMonth") + "/" + request.getParameter("StartYear");
            String Phone = request.getParameter("CountryCode") + "," + request.getParameter("Phone");
            String Skills = request.getParameter("Skills");
            if (Skills.equals("--") == false) {
                Skills = Skills.substring(1, request.getParameter("Skills").length() - 1);
            }
            String Connections = request.getParameter("Connections");
            String ProfileURL = request.getParameter("ProfileURL");
            String VideoStatus = request.getParameter("VideoStatus");
            String EditFlag = request.getParameter("EditFlag");

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

            if (EditFlag.equals("1")) {
                Connection co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                int rs = st.executeUpdate("UPDATE user_list SET phone = '" + Phone + "',skills = '" + Skills + "',videostatus = '" + VideoStatus + "',lasteditedon = NOW() WHERE name = '" + Name + "' AND email = '" + Email + "'");

                if (rs == 1) {
                    out.println("Error: Data edited successfully!");
                } else {
                    out.println("Error: Data edit failed.");
                }
                st.close();
                co.close();

            } else {

                Connection co = DriverManager.getConnection(host, userName, password);
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery("Select MAX(id) from user_list");
                while (rs.next()) {

                    if (rs.getString(1) == null) {

                        Connection co2 = DriverManager.getConnection(host, userName, password);
                        Statement st2 = co2.createStatement();

                        int rs2 = st2.executeUpdate("INSERT INTO user_list VALUES('1','" + Name + "','" + Location.get(0) + "','" + Location.get(1) + "','" + Email + "','" + DpURL + "'"
                                + ",'" + Title + "','" + Company + "','" + Industry + "','" + StartDate + "','" + Phone + "',"
                                + "'" + Skills + "','--','--','--','--','" + Connections + "','" + ProfileURL + "','" + VideoStatus + "', NOW(), null)");

                        if (rs2 == 1) {
                            out.println("Data uploaded successfully!");
                        } else {
                            out.println("Error: Data upload failed.");
                        }

                        co2.close();
                        st2.close();

                    } else {

                        String id = String.valueOf(Integer.valueOf(rs.getString(1)) + 1);

                        Connection co2 = DriverManager.getConnection(host, userName, password);
                        Statement st2 = co2.createStatement();

                        int rsInt = st2.executeUpdate("INSERT INTO user_list VALUES('" + id + "','" + Name + "','" + Location.get(0) + "','" + Location.get(1) + "','" + Email + "','" + DpURL + "'"
                                + ",'" + Title + "','" + Company + "','" + Industry + "','" + StartDate + "','" + Phone + "',"
                                + "'" + Skills + "','--','--','--','--','" + Connections + "','" + ProfileURL + "','" + VideoStatus + "', NOW(), null)");

                        if (rsInt == 1) {
                            out.println("Data uploaded successfully!");
                        } else {
                            out.println("Error : Data upload failed.");
                        }

                        co2.close();
                        st2.close();

                    }

                }
                rs.close();

                st.close();
                co.close();
            }

        } catch (ClassNotFoundException ex) {
            out.println("Error: ClassNotFoundException");
            out.println(ex.getMessage());
            Logger.getLogger(uploadUserData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("Error: SQLException");
            out.println(ex.getMessage());
            Logger.getLogger(uploadUserData.class.getName()).log(Level.SEVERE, null, ex);
        }
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
