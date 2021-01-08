package com.empauth.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class EmpAuthJDBCDAO implements EmpAuthDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	private static final String INSERT_STMT ="INSERT INTO EMP_AUTH (EMP_ID,AUTH_NO)VALUES(?,?)";
	private static final String GET_ALL_STMT ="SELECT EMP_ID,AUTH_NO FROM EMP_AUTH ORDER BY EMP_ID";
	private static final String GET_ONE_STMT ="SELECT EMP_ID,AUTH_NO FROM EMP_AUTH WHERE EMP_ID=? ";	
	private static final String DELETE ="DELETE FROM  EMP_AUTH where EMP_ID=? AND AUTH_NO=?";		
	private static final String UPDATE ="UPDATE EMP_AUTH SET AUTH_NO=? WHERE EMP_ID=? ";		
			
	@Override
	public void insert(EmpAuthVO empaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,empaVO.getEmp_id());
			pstmt.setString(2,empaVO.getAuth_no());
			
			
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
	public void update(EmpAuthVO empaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,empaVO.getAuth_no());
			pstmt.setString(2,empaVO.getEmp_id());
			
			
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
	public void delete(String emp_id,String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_id);
			pstmt.setString(2, auth_no);
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
	public EmpAuthVO findByPrimaryKey(String emp_id) {
		// TODO Auto-generated method stub
		EmpAuthVO eaVO=null;
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
				eaVO=new EmpAuthVO();
				eaVO.setEmp_id(rs.getString("emp_id"));
				eaVO.setAuth_no(rs.getString("auth_no"));
			
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
	return eaVO;
	}
//	@Override
//	public List<EmpAuthVO> getOneEmpAuth(String emp_id) {
//		List<EmpAuthVO> list=new ArrayList<EmpAuthVO>();
//		EmpAuthVO eaVO=null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//	try {
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		pstmt = con.prepareStatement(GET_ALL_STMT);
//		rs = pstmt.executeQuery();
//		while (rs.next()) {
//			eaVO=new EmpAuthVO();
//			eaVO.setEmp_id(rs.getString("emp_id"));
//			eaVO.setAuth_no(rs.getString("auth_no"));
//			list.add(eaVO);
//	}
//	}catch (ClassNotFoundException e) {
//		throw new RuntimeException("Couldn't load database driver. "
//				+ e.getMessage());
//		// Handle any SQL errors
//	} catch (SQLException se) {
//		throw new RuntimeException("A database error occured. "
//				+ se.getMessage());
//		// Clean up JDBC resources
//	} finally {
//		if (rs != null) {
//			try {
//				rs.close();
//			} catch (SQLException se) {
//				se.printStackTrace(System.err);
//			}
//		}
//		if (pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (SQLException se) {
//				se.printStackTrace(System.err);
//			}
//		}
//		if (con != null) {
//			try {
//				con.close();
//			} catch (Exception e) {
//				e.printStackTrace(System.err);
//			}
//		}
//	}
//	return list;
//	}
		
	@Override
	public List<EmpAuthVO> getAll() {
		List<EmpAuthVO> list=new ArrayList<EmpAuthVO>();
		EmpAuthVO eaVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			eaVO=new EmpAuthVO();
			eaVO.setEmp_id(rs.getString("emp_id"));
			eaVO.setAuth_no(rs.getString("auth_no"));
			list.add(eaVO);
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
	
		//新增
		EmpAuthJDBCDAO eadao=new EmpAuthJDBCDAO();
//		EmpAuthVO empauthVO1=new EmpAuthVO();
//		empauthVO1.setEmp_id("EMP0015");
//		empauthVO1.setAuth_no("8");
//		eadao.insert(empauthVO1);
		
		//更新
		EmpAuthVO empauthVO2=new EmpAuthVO();
		empauthVO2.setEmp_id("EMP0007");//where 條件
		empauthVO2.setAuth_no("4");
		eadao.update(empauthVO2);
		//刪除
		eadao.delete("EMP0015","6");
		
		//查詢
		EmpAuthVO empauthVO3=eadao.findByPrimaryKey("EMP0001");
		System.out.print(empauthVO3.getEmp_id()+",");
		System.out.println(empauthVO3.getAuth_no());
		System.out.println("-----------------------------------");
		
		//查全部
		List<EmpAuthVO> list=eadao.getAll();
		for(EmpAuthVO aEmpA:list) {
			System.out.print(aEmpA.getEmp_id()+",");
			System.out.println(aEmpA.getAuth_no());
		}
		

	}

	

	
}
