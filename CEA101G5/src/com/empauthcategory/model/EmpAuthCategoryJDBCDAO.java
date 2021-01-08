package com.empauthcategory.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;

public class EmpAuthCategoryJDBCDAO implements EmpAuthCategoryDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT ="INSERT INTO EMP_AUTH_CATEGORY(AUTH_NO,AUTH_NAME)"
			+ "VALUES(?,?)";
	private static final String GET_ALL_STMT ="SELECT AUTH_NO,AUTH_NAME FROM EMP_AUTH_CATEGORY order by auth_no";
	private static final String GET_ONE_STMT ="SELECT AUTH_NO,AUTH_NAME FROM EMP_AUTH_CATEGORY where auth_no=?";
	private static final String DELETE ="DELETE FROM EMP_AUTH_CATEGORY WHERE AUTH_NO=?";
	private static final String UPDATE ="UPDATE EMP_AUTH_CATEGORY set AUTH_NAME=? where AUTH_NO=? " ;
	@Override
	public void insert(EmpAuthCategoryVO eacVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,eacVO.getAuth_no());
			pstmt.setString(2,eacVO.getAuth_name());
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
	public void update(EmpAuthCategoryVO eacVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,eacVO.getAuth_name());
			pstmt.setString(2,eacVO.getAuth_no());
			
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
	public void delete(String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, auth_no);
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
	public EmpAuthCategoryVO findByPrimaryKey(String auth_no) {
		EmpAuthCategoryVO eacVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, auth_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eacVO=new EmpAuthCategoryVO();
				eacVO.setAuth_no(rs.getString("auth_no"));
				eacVO.setAuth_name(rs.getString("auth_name"));
			
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
	return eacVO;
	}

	@Override
	public List<EmpAuthCategoryVO> getAll() {
		List<EmpAuthCategoryVO> list = new ArrayList<EmpAuthCategoryVO>();
		EmpAuthCategoryVO eacVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eacVO=new EmpAuthCategoryVO();
				eacVO.setAuth_no(rs.getString("auth_no"));
				eacVO.setAuth_name(rs.getString("auth_name"));
				list.add(eacVO);
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
		
		EmpAuthCategoryJDBCDAO eacdao=new EmpAuthCategoryJDBCDAO();
		//新增
//		EmpAuthCategoryVO eacVO1=new EmpAuthCategoryVO();
//		eacVO1.setAuth_no("7");
//		eacVO1.setAuth_name("無權限");
//		eacdao.insert(eacVO1);
//		System.out.println("新增成功");	
		//更新
//		EmpAuthCategoryVO eacVO2=new EmpAuthCategoryVO();
//		eacVO2.setAuth_no("7");//where條件
//		eacVO2.setAuth_name("Tasddsaddd");
//		eacdao.update(eacVO2);
	
		//刪除
//		eacdao.delete("7");
		
		//查詢
		EmpAuthCategoryVO eacVO3=eacdao.findByPrimaryKey("3");
		System.out.print(eacVO3.getAuth_no()+",");
		System.out.println(eacVO3.getAuth_name());
		System.out.println("---------------------");
		//查全部
		List<EmpAuthCategoryVO> list = eacdao.getAll();
		for(EmpAuthCategoryVO aEac:list) {
			System.out.print(aEac.getAuth_no());
			System.out.println(aEac.getAuth_name());
		}
	}
	
}
