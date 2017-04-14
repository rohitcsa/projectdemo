/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
/*
 *
 * @author tanuj_000
 */

public class mailPDF_Gold_CitrixChina extends HttpServlet {

    public byte[] decode(String s) {
        return Base64.decodeBase64(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("Reached @ mailPDF_Gold_CitrixChina.");

            String pdfBase64 = request.getParameter("pdfBase64");
            String reportdate = request.getParameter("reportdate");
            String emailaddresses = request.getParameter("emailList");
            String emailMessage = request.getParameter("emailMessage");
            String filename = request.getParameter("filename");

            List<String> emailList = Arrays.asList(emailaddresses.split("\\s*,\\s*"));

            System.out.println("pdfBase64 is --------------\n" + pdfBase64 + "\n-----------------End");
            System.out.println(reportdate);
            System.out.println(emailaddresses);

            byte[] decodedBytes = decode(pdfBase64.substring(28));
            MultiPartEmail email = new MultiPartEmail();
            email.setSmtpPort(587);
            email.setDebug(false);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication("reporting@groupnp.com", "D3sign2015");
            email.setTLS(true);

            for (String emailAddress : emailList) {

                email.addTo(emailAddress);
            }

            email.setFrom("reporting@groupnp.com");
            email.setSubject(filename + " (" + reportdate + ")");

            MimeMultipart part1 = new MimeMultipart();
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent(emailMessage, "text/html; charset=utf-8");
            part1.addBodyPart(messageBodyPart1);
            email.addPart(part1);

            MimeMultipart part2 = new MimeMultipart();
            BodyPart messageBodyPart2 = new MimeBodyPart();
            messageBodyPart2.setDataHandler(new DataHandler(new ByteArrayDataSource(decodedBytes, "application/pdf")));
            messageBodyPart2.removeHeader("Content-Transfer-Encoding");
            messageBodyPart2.addHeader("Content-Transfer-Encoding", "base64");
            messageBodyPart2.setFileName(filename + " " + reportdate + ".pdf");
            part2.addBodyPart(messageBodyPart2);
            email.addPart(part2);

            email.send();

        } catch (EmailException | MessagingException ex) {
            Logger.getLogger(mailPDF_Gold_CitrixChina.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
