<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upcoming Child Vaccines (Next 30 Days)</title>
    <style>
        body { font-family: Arial; }
        h2 { text-align: center; margin-top: 30px; }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            border: 1px solid #999;
            text-align: center;
        }
        th {
            background-color: #f3f3f3;
        }
    </style>
</head>
<body>

<h2>Upcoming Child Vaccines (Next 30 Days)</h2>

<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.jdbc.Driver"); // Use com.mysql.cj.jdbc.Driver for newer versions
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");

        String sql = "SELECT * FROM vaccinelogs WHERE date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY)";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        if (!rs.isBeforeFirst()) {
            out.println("<p style='text-align:center;'>No upcoming vaccines in the next 30 days.</p>");
        } else {
%>
<table>
    <tr>
        <th>ID</th>
        <th>Child ID</th>
        <th>Vaccine Name</th>
        <th>Scheduled Date</th>
        <th>Age (Months)</th>
    </tr>
<%
            while (rs.next()) {
%>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getInt("childID") %></td>
        <td><%= rs.getString("vaccineName") %></td>
        <td><%= rs.getDate("date") %></td>
        <td><%= rs.getInt("ageMonths") %></td>
    </tr>
<%
            }
%>
</table>
<%
        }
    } catch (Exception e) {
        out.println("<p style='color:red; text-align:center;'>Error: " + e.getMessage() + "</p>");
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
%>

</body>
</html>
