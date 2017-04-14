/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
/*
 *
 * @author tanuj_000
 */

public class mail_ResetPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("Reached @ mail_ResetPassword.");
            String emailid = request.getParameter("email");

            Random r = new Random();
            int Low = 1000;
            int High = 9999;
            int R = r.nextInt(High - Low) + Low;

            String ResetCode = "RC" + String.valueOf(R);

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

            st.executeUpdate("UPDATE user_accounts SET passwordresetcode ='" + ResetCode + "' WHERE emailid = '" + emailid + "'");

            String emailMessage = "Hi!"
                    + "<br>"
                    + "Follow this <a href=\"https://npreportingsuite.com/NPReportDeck/resetPassword.jsp\">link</a> and enter the following Reset Code to reset your password."
                    + "<br><br>"
                    + "Reset Code : <b>" + ResetCode + "</b>"
                    + "<br><br>"
                    + "Warm Regards,"
                    + "<br>"
                    + "NPReportingSuite Team"
                    + "<br><br>";

            MultiPartEmail email = new MultiPartEmail();
            email.setSmtpPort(587);
            email.setDebug(false);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication("reporting@groupnp.com", "D3sign2015");
            email.setTLS(true);

            email.addTo(emailid);

            email.setFrom("reporting@groupnp.com");
            email.setSubject("NPReportingSuite : Reset Password Request Follow-up");

            MimeMultipart part1 = new MimeMultipart();
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent(emailMessage, "text/html; charset=utf-8");
            part1.addBodyPart(messageBodyPart1);
            email.addPart(part1);

            email.send();

        } catch (EmailException | MessagingException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(mail_ResetPassword.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
