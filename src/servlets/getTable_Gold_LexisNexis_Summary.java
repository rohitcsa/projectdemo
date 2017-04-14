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

/*
 *
 * @author tanuj_000
 */
public class getTable_Gold_LexisNexis_Summary extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Reached @ getTable_Gold_LexisNexisIndia_MonthlySummary.");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String reportdate = request.getParameter("reportdate");
        String country = request.getParameter("country");

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

            Class.forName(driver);
            co = DriverManager.getConnection(host, userName, password);
            Statement st = co.createStatement();

            if (country.contentEquals("Asia")) {

                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Region</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles At Week Beginning</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Opened This Week</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled</th>\n"
                        + "                                                    <th style=\"text-align: center\">#  Offers Pending</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Candidates Screened</th>\n"
                        + "                                                    <th style=\"text-align: center\"># CV’s Sent To HM</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Interviews By The Hiring Managers</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Cancelled Or On Hold</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled From The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Sales Roles filled from The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles At Week End</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles at Week End</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                ResultSet rs = st.executeQuery("Select region,noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofsalesrolesmonth,noofsalesrolesweekend,noofactiverolesweekend from gold_ln_summary where country = 'Malsing' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(13) + "</b></span></td>\n");
                    out.println("</tr>");
                }
                out.println("<tr class=\"default\">\n");
                out.println("                                                  <td></td>\n");
                out.println("                                                  <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n"
                        + "                                                    <td></td>\n");
                out.println("</tr>");
                rs = st.executeQuery("Select region,noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofsalesrolesmonth,noofsalesrolesweekend,noofactiverolesweekend from gold_ln_summary where country = 'India' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(13) + "</b></span></td>\n");
                    out.println("</tr>");
                }

                rs.close();
            } else if (country.contentEquals("South Africa")) {

                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Region</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles At Week Beginning</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Opened This Week</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled</th>\n"
                        + "                                                    <th style=\"text-align: center\">#  Offers Pending</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Candidates Screened</th>\n"
                        + "                                                    <th style=\"text-align: center\"># CV’s Sent To HM</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Interviews By The Hiring Managers</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Cancelled Or On Hold</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled From The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles at Week End</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                ResultSet rs = st.executeQuery("Select region,noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofactiverolesweekend from gold_ln_summary where country = '" + country + "' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n");
                    out.println("</tr>");
                }
                rs.close();
            } else if (country.contentEquals("France")) {

                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Region</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De Postes Ouverts</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De Postes Ouverts Pendant La Semaine</th>\n"
                        + "                                                    <th style=\"text-align: center\">Postes Pourvus Cette Semaine</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombres D’offres En Cours</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De Candidats Interviewés</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De CVs Envoyés Aux HM</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre D’interviews Par Les HM</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De Postes Suspendus Ou Annulés</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De Postes Pourvus Depuis Le Début Du Mois</th>\n"
                        + "                                                    <th style=\"text-align: center\">Nombre De Postes Ouverts (Fin De Semaine Précédente)</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                ResultSet rs = st.executeQuery("Select region,noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofactiverolesweekend from gold_ln_summary where country = '" + country + "' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n");
                    out.println("</tr>");
                }
                rs.close();
            } else {

                out.println("<thead>\n"
                        + "                                                <tr style=\"background-color: #EEEEEE\">\n"
                        + "                                                    <th style=\"text-align: center\">Region</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles At Week Beginning</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Opened This Week</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled</th>\n"
                        + "                                                    <th style=\"text-align: center\">#  Offers Pending</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Candidates Screened</th>\n"
                        + "                                                    <th style=\"text-align: center\"># CV’s Sent To HM</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Interviews By The Hiring Managers</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Cancelled Or On Hold</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles Filled From The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Sales Roles filled from The Beginning Of The Month</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Roles At Week End</th>\n"
                        + "                                                    <th style=\"text-align: center\"># Active Roles at Week End</th>\n"
                        + "                                                </tr>\n"
                        + "                                            </thead>\n"
                        + "                                            <tbody>");

                ResultSet rs = st.executeQuery("Select region,noofactiveroles,noofrolesopened,noofrolesfilled,noofofferspending,noofcandidatesscreened,noofcvssent,noofinterviews,noofrolescancelledonhold,noofrolesfilledmonth,noofsalesrolesmonth,noofsalesrolesweekend,noofactiverolesweekend from gold_ln_summary where country = '" + country + "' AND reportdate = '" + reportdate + "'");
                while (rs.next()) {
                    out.println("<tr>\n");
                    out.println("                                                  <td style=\"text-align: center\" class=\"warning\"><span class=\"info-box-text\"><b>" + rs.getString(1) + "</b></span></td>\n");
                    out.println("                                                  <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(2) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(3) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(4) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(5) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(6) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(7) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(8) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(9) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(10) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(11) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(12) + "</b></span></td>\n"
                            + "                                                    <td style=\"text-align: center\"><span class=\"text-muted\"><b>" + rs.getString(13) + "</b></span></td>\n");
                    out.println("</tr>");
                }
                rs.close();
            }

            out.println("                                            </tbody>");

            st.close();
            co.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(getTable_Gold_LexisNexisIndia_MonthlySummary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
