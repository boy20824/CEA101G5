package com.memberrecharge.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemRecharDAO implements MemRecharDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MEMBER_RECHARGE (RECHARGE_ID, MEM_PHONE, RECHARGE_CHANGE) VALUES (('RECHAR'||LPAD(SEQ_RECHARGE_ID.nextval,6,'0'), ?,  ?)";
	private static final String GET_ALL_STMT = "SELECT RECHARGE_ID, MEM_PHONE, RECHARGE_DATE ,RECHARGE_CHANGE FROM MEMBER_RECHARGE";
	private static final String GET_ONE_STMT = "SELECT RECHARGE_ID, MEM_PHONE, RECHARGE_DATE ,RECHARGE_CHANGE WHERE RECHARGE_ID= ?";
	private static final String GET_ALL_BYMEMPHONE = "SELECT RECHARGE_ID, MEM_PHONE, RECHARGE_DATE ,RECHARGE_CHANGE WHERE MEM_PHONE= ?";
	
	private static final String DELETE_MEMRECHARs = "DELETE FROM MEMBER_RECHARGE WHERE RECHARGE_ID = ?";

	@Override
	public void insert(MemRecharVO memRecharVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memRecharVO.getMemPhone());
			pstmt.setInt(2, memRecharVO.getRechargeChange());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String rechargeId) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 刪除會員
			pstmt = con.prepareStatement(DELETE_MEMRECHARs);
			pstmt.setString(1, rechargeId);
			updateCount_EMPs = pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除儲值序號" + rechargeId + "時,共有儲值紀錄" + updateCount_EMPs + "筆同時被刪除");

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemRecharVO findByPrimaryKey(String rechargeId) {

		MemRecharVO memRecharVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rechargeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				memRecharVO = new MemRecharVO();
				memRecharVO.setMemPhone(rs.getString("RECHARGE_ID"));
				memRecharVO.setRechargeId(rs.getString("MEM_PHONE"));
				memRecharVO.setRechargeDate(rs.getTimestamp("RECHARGE_DATE"));
				memRecharVO.setRechargeChange(rs.getInt("RECHARGE_CHANGE"));

			}

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
		return memRecharVO;
	}
	
	@Override
	public List<MemRecharVO> getAll() {
		List<MemRecharVO> list = new ArrayList<MemRecharVO>();
		MemRecharVO memRecharVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memRecharVO = new MemRecharVO();
				memRecharVO.setMemPhone(rs.getString("RECHARGE_ID"));
				memRecharVO.setRechargeId(rs.getString("MEM_PHONE"));
				memRecharVO.setRechargeDate(rs.getTimestamp("RECHARGE_DATE"));
				memRecharVO.setRechargeChange(rs.getInt("RECHARGE_CHANGE"));
				list.add(memRecharVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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


	@Override
	public List<MemRecharVO> findByMemPhone(String memPhone) {
		// TODO Auto-generated method stub
		List<MemRecharVO> list = new ArrayList<MemRecharVO>();
		MemRecharVO memRecharVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BYMEMPHONE);
			
			pstmt.setString(1, memPhone);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memRecharVO = new MemRecharVO();
				memRecharVO.setMemPhone(rs.getString("MEM_PHONE"));
				memRecharVO.setRechargeId(rs.getString("RECHARGE_ID"));
				memRecharVO.setRechargeDate(rs.getTimestamp("RECHARGE_DATE"));
				memRecharVO.setRechargeChange(rs.getInt("RECHARGE_CHANGE"));
				list.add(memRecharVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
