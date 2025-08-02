package com.connection;

import java.sql.*;
public class ConnectDB 
{
	public static Connection con=null;
	public static Connection dbcon()
	{
		try{
			if(con==null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb","root","");
				System.out.println("Connection Established - "+con);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	

}
