package com.practise.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.*;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	
	public EmployeeDAOImpl(DataSource dataSourse) {
		this.dataSourse = dataSourse;
	}

	@Override
	public void setSalay(int empno, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSalaty(int empno) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
		
		try
		{
			
			con=dataSourse.getConnection();
			pstmt=con.prepareStatement("select ccno from ccmaster where ccno=757");
	        rs=pstmt.executeQuery();
	        if(rs.next())
			return rs.getInt(1);
			
		}
	
		catch(Exception e)
		{
		System.out.println(e);
		}
		      
		
		
		return 0;
	
	
	}

	
	
	private DataSource dataSourse;



	
	
}
