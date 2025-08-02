<%@ page import="java.sql.*, java.util.*" %>
<html>
<head>
    <title>View Vaccine Logs</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: auto; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }
        th { background-color: #f2f2f2; }
        body { font-family: Arial; background: #e9f0f7; }
        h2 { text-align: center; }
    </style>
</head>
<body>
<h2>All Vaccine Logs</h2>
<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");
        stmt = conn.createStatement();

        // Corrected column name: childName (case-sensitive)
        String sql = "SELECT v.id, c.childName, v.vaccineName, v.date " +
                     "FROM vaccinelogs v " +
                     "JOIN child c ON v.childID = c.id";

        rs = stmt.executeQuery(sql);
%>
    <table>
        <tr>
            <th>ID</th>
            <th>Child Name</th>
            <th>Vaccine Name</th>
            <th>Vaccine Date</th>
        </tr>
<%
        while (rs.next()) {
%>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("childName") %></td>
            <td><%= rs.getString("vaccineName") %></td>
            <td><%= rs.getDate("date") %></td>
        </tr>
<%
        }
    } catch (Exception e) {
        out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
    } finally {
        try { if (rs != null) rs.close(); } catch(Exception e) {}
        try { if (stmt != null) stmt.close(); } catch(Exception e) {}
        try { if (conn != null) conn.close(); } catch(Exception e) {}
    }
%>
    </table>
</body>
</html>
