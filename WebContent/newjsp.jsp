<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Insert title here</title>
        </head>
        <body>

            <%
                String url = null;
                Connection conn = null;
                if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
                    url = System.getProperty("ae-cloudsql.cloudsql-database-url");
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                    conn = DriverManager.getConnection(url);

                } else {
                    url = System.getProperty("ae-cloudsql.local-database-url");
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url, "root", "root");
                }
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT code,title,industry,company,location,country,pincode,exp,skills,brief,companybrief,tags,createdby,status,DATE_FORMAT(startdate,'%d %b, %Y') FROM job_list");
                if (!rs.isBeforeFirst()) {
                } else {
                    while (rs.next()) {
            %>

            <table>
                <tr><%=rs.getString(1)%></tr>
                <tr><%=rs.getString(2)%></tr>
                <tr><%=rs.getString(3)%></tr>
                <tr><%=rs.getString(4)%></tr>
                <tr><%=rs.getString(5)%></tr>
                <tr><%=rs.getString(6)%></tr>
                <tr><%=rs.getString(7)%></tr>
                <tr><%=rs.getString(8)%></tr>
                <tr><%=rs.getString(9)%></tr>
                <tr><%=rs.getString(10)%></tr>
                <tr><%=rs.getString(11)%></tr>
                <tr><%=rs.getString(12)%></tr>
                <tr><%=rs.getString(13)%></tr>
                <tr><%=rs.getString(14)%></tr>
                <tr><%=rs.getString(15)%></tr>
            </table>

            <%
                    }
                }
            %>

        </body>
    </html>