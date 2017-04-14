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

public class checkResetCode extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            System.out.println("Reached @ checkResetCode.");
            String emailid = request.getParameter("emailid");
            String ResetCode = request.getParameter("resetcode");

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

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM user_accounts WHERE emailid = '" + emailid + "' AND passwordresetcode = '" + ResetCode + "'");
            if (!rs.isBeforeFirst()) {
                System.out.println("Incorrect Details.");
                out.println("0");
            } else {
                System.out.println("Details Matched!");
                out.println("1");
            }
            rs.close();
            st.close();
            co.close();
            out.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(checkResetCode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
