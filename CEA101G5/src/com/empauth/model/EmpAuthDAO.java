package com.empauth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpAuthDAO implements EmpAuthDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT ="INSERT INTO EMP_AUTH (EMP_ID,AUTH_NO)VALUES(?,?)";
	private static final String GET_ALL_STMT ="SELECT EMP_ID,AUTH_NO FROM EMP_AUTH ORDER BY EMP_ID";
	private static final String GET_ONE_STMT ="SELECT EMP_ID,AUTH_NO FROM EMP_AUTH WHERE EMP_ID=? ";	
	private static final String DELETE ="DELETE FROM EMP_AUTH WHERE EMP_ID=? AND AUTH_NO=?";		
	private static final String UPDATE ="UPDATE EMP_AUTH set AUTH_NO=? WHERE EMP_ID=?";		
			
	
	@Override
	public void insert(EmpAuthVO empaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,empaVO.getEmp_id());
			pstmt.setString(2,empaVO.getAuth_no());
			
			pstmt.executeUpdate();
	}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,empaVO.getAuth_no());
			pstmt.setString(2,empaVO.getEmp_id());
			pstmt.executeUpdate();
		
	}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, emp_id);
			pstmt.setString(2, auth_no);
			pstmt.executeUpdate();
		
	}catch (SQLException se) {
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
		EmpAuthVO eaVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, emp_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eaVO=new EmpAuthVO();
				eaVO.setEmp_id(rs.getString("emp_id"));
				eaVO.setAuth_no(rs.getString("auth_no"));
			}
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
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				eaVO=new EmpAuthVO();
//				eaVO.setEmp_id(rs.getString("emp_id"));
//				eaVO.setAuth_no(rs.getString("auth_no"));
//				list.add(eaVO);
//			}
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	@Override
	public List<EmpAuthVO> getAll() {
		List<EmpAuthVO> list=new ArrayList<EmpAuthVO>();
		EmpAuthVO eaVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eaVO=new EmpAuthVO();
				eaVO.setEmp_id(rs.getString("emp_id"));
				eaVO.setAuth_no(rs.getString("auth_no"));
				list.add(eaVO);
			}
		}catch (SQLException se) {
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

	
	



}
