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
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tanuj_000
 */
public class verifyLogin extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String emailid = request.getParameter("emailid");
            String pass = request.getParameter("password");
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
            Class.forName(driver).newInstance();
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM user_accounts WHERE emailid = '" + emailid + "'");

            if (rs.next()) {
                String pass1 = rs.getString("password");//this fetches the 'pswd' column value and inserts it into pass1
                String empname = rs.getString("username");
                String client = rs.getString("client");
                if (pass1.equals(pass)) {
                    HttpSession sess = request.getSession();
                    sess.setAttribute("user", empname);
                    sess.setAttribute("email", emailid);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                    dispatcher.forward(request, response);

                } else {

                    out.print("<br><br><br><br><br><div class=\"col-lg-12\" align=\"center\"><span class=\"badge\">INVALID PASSWORD. PLEASE TRY AGAIN.</span></div>");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.include(request, response);
                }
            } else {
                out.print("<br><br><br><br><br><div class=\"col-lg-12\" align=\"center\"><span class=\"badge\">INVALID EMAIL. PLEASE TRY AGAIN.</span></div>");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e.getMessage());
            //RequestDispatcher dispatcher = request.getRequestDispatcher("Loginagain.jsp");
            //dispatcher.forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
