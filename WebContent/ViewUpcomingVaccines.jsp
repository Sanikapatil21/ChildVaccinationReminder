<%@ page import="java.sql.*, java.util.*, java.text.*" %>
<%@ page import="com.connection.ChildPOJO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upcoming Vaccines</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
        }
        h2 {
            text-align: center;
            color: #0077b6;
        }
        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 0 10px #ccc;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #0077b6;
            color: white;
        }
    </style>
</head>
<body>

<h2>Upcoming Vaccines for <%= ChildPOJO.getChildName() %> (Next 30 Days)</h2>

<%
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Get current and next month names
    Calendar cal = Calendar.getInstance();
    String currentMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(cal.getTime());

    cal.add(Calendar.MONTH, 1);
    String nextMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(cal.getTime());

    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");

        String query = "SELECT * FROM vaccine WHERE LOWER(months) IN (?, ?)";
        ps = con.prepareStatement(query);
        ps.setString(1, currentMonth.toLowerCase());
        ps.setString(2, nextMonth.toLowerCase());

        rs = ps.executeQuery();
%>

<table>
    <tr>
        <th>ID</th>
        <th>Vaccine Name</th>
        <th>Details</th>
        <th>Price</th>
        <th>Month</th>
    </tr>

<%
        boolean found = false;
        while (rs.next()) {
            found = true;
%>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("vaccineName") %></td>
        <td><%= rs.getString("vaccineDetails") %></td>
        <td><%= rs.getString("price") %></td>
        <td><%= rs.getString("months") %></td>
    </tr>
<%
        }
        if (!found) {
%>
    <tr>
        <td colspan="5">No upcoming vaccines for <%= currentMonth %> or <%= nextMonth %>.</td>
    </tr>
<%
        }
    } catch (Exception e) {
        out.println("<tr><td colspan='5' style='color:red;'>Error: " + e.getMessage() + "</td></tr>");
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
</table>

</body>
</html>
