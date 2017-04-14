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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
/*
 *
 * @author tanuj_000
 */

public class resetPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            response.setContentType("text/html;charset=UTF-8");

            System.out.println("Reached @ resetPassword.");
            String emailid = request.getParameter("emailid");
            String newpassword = request.getParameter("newpassword");

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

            st.executeUpdate("UPDATE user_accounts SET password = '" + newpassword + "',passwordresetcode = '--' WHERE emailid = '" + emailid + "'");

            st.close();
            co.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(resetPassword.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
