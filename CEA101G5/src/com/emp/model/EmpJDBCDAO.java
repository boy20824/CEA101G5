package com.emp.model;

import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT ="INSERT INTO EMP_DATA(EMP_ID,EMP_ACCOUNT,EMP_PWD,EMP_NAME,EMP_DATE,EMP_STATUS,EMP_IMAGE) "
			+ "VALUES ( 'EMP' || LPAD(SEQ_EMP_ID.NEXTVAL,4,'0'),?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT = 
			"SELECT EMP_ID,EMP_ACCOUNT,EMP_PWD,to_char(EMP_DATE,'yyyy-mm-dd') EMP_DATE,EMP_NAME,EMP_STATUS,EMP_IMAGE FROM EMP_DATA order by emp_id";
	private static final String GET_ONE_STMT = 
			"SELECT EMP_ID,EMP_ACCOUNT,EMP_PWD,to_char(EMP_DATE,'yyyy-mm-dd') EMP_DATE,EMP_NAME,EMP_STATUS,EMP_IMAGE FROM EMP_DATA  where emp_id=?";
	private static final String DELETE = 
			"DELETE FROM EMP_DATA where EMP_ID = ?";
	private static final String UPDATE = 
			"UPDATE EMP_DATA set emp_account=?,emp_pwd=?, emp_name=?,  emp_date=?,  emp_status=?, emp_image=? where emp_id = ?";
	private static final String GET_ONE_ACCOUNT = "SELECT * FROM EMP_DATA WHERE EMP_ACCOUNT=?";
	
	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,empVO.getEmp_account());
			pstmt.setString(2,empVO.getEmp_password());
			pstmt.setString(3,empVO.getEmp_name());
			pstmt.setDate(4,empVO.getEmp_date());
			pstmt.setInt(5,empVO.getEmp_status());
			pstmt.setBytes(6, empVO.getEmp_image());
			
			pstmt.executeUpdate();
			
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

}

	@Override
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,empVO.getEmp_account());
			pstmt.setString(2,empVO.getEmp_password());
			pstmt.setString(3,empVO.getEmp_name());
			pstmt.setDate(4,empVO.getEmp_date());
			pstmt.setInt(5,empVO.getEmp_status());
			pstmt.setBytes(6, empVO.getEmp_image());
			pstmt.setString(7,empVO.getEmp_id());
			pstmt.executeUpdate();
			
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

	@Override
	public void delete(String emp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_id);
			pstmt.executeUpdate();
			
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}
	@Override
	public EmpVO findByAccount(String emp_account) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, emp_account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO=new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_account(rs.getString("emp_account"));
				empVO.setEmp_password(rs.getString("emp_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				empVO.setEmp_image(rs.getBytes("emp_image"));
				
				
			}
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
		return empVO;
	}
	@Override
	public EmpVO findByPrimaryKey(String emp_id) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO=new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_account(rs.getString("emp_account"));
				empVO.setEmp_password(rs.getString("emp_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				empVO.setEmp_image(rs.getBytes("emp_image"));
				
				
			}
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
		return empVO;
		}
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO=new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_account(rs.getString("emp_account"));
				empVO.setEmp_password(rs.getString("emp_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				empVO.setEmp_image(rs.getBytes("emp_image"));
				list.add(empVO);
			}
		
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return list;
}
	
	public static void main(String[] args) {
		
		EmpJDBCDAO dao = new EmpJDBCDAO();
		//新增
		byte[] pic = null;
//		EmpVO empVO1 =new EmpVO();
//		empVO1.setEmp_account("abc");
//		empVO1.setEmp_password("asd1234");
//		empVO1.setEmp_name("大吳大吳");
//		empVO1.setEmp_date(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setEmp_status(0);
//			try {
//				pic = getPictureByteArray("C:/img/2.jpg");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		empVO1.setEmp_image(pic);
//		dao.insert(empVO1);
//		System.out.println("新增");
//
//		//更新
//		EmpVO empVO2 =new EmpVO();
//		empVO2.setEmp_id("EMP0015"); //where條件
//		empVO2.setEmp_account("asdqwe");
//		empVO2.setEmp_password("a12334");
//		empVO2.setEmp_name("大");
//		empVO2.setEmp_date(java.sql.Date.valueOf("2002-11-22"));
//		empVO2.setEmp_status(2);
//		try {
//			pic = getPictureByteArray("C:/img/2.jpg");
//		} catch (IOException e) {
//			// 
//			e.printStackTrace();
//		}
//		empVO2.setEmp_image(pic);
//		dao.update(empVO2);

		
		//刪除
//		dao.delete("EMP0021");

		
		
		System.out.println("---------------------");
		
		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey("EMP0001");
//		System.out.print(empVO3.getEmp_id()+",");
//		System.out.print(empVO3.getEmp_account()+",");
//		System.out.print(empVO3.getEmp_password()+",");
//		System.out.print(empVO3.getEmp_name()+",");
//		System.out.print(empVO3.getEmp_date()+",");
//		System.out.print(empVO3.getEmp_status()+",");
//		System.out.println(empVO3.getEmp_image());
//		System.out.println("---------------------");
		//查帳號
		EmpVO empVO4 = dao.findByAccount("B2");
		System.out.print(empVO4.getEmp_id()+",");
		System.out.print(empVO4.getEmp_account()+",");
		System.out.print(empVO4.getEmp_password()+",");
		System.out.print(empVO4.getEmp_name()+",");
		System.out.print(empVO4.getEmp_date()+",");
		System.out.print(empVO4.getEmp_status()+",");
		System.out.println(empVO4.getEmp_image());
		System.out.println("---------------------");
		// 查全部
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmp_id()+",");
			System.out.print(aEmp.getEmp_account()+",");
			System.out.print(aEmp.getEmp_password()+",");
			System.out.print(aEmp.getEmp_name()+",");
			System.out.print(aEmp.getEmp_date()+",");
			System.out.print(aEmp.getEmp_status()+",");
			System.out.print(aEmp.getEmp_image());
			System.out.println();	
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	
}


	
