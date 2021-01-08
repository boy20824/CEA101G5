package com.memberrecharge.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemRecharJDBCDAO implements MemRecharDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO MEMBER_RECHARGE (RECHARGE_ID, MEM_PHONE, RECHARGE_CHANGE) VALUES ('RECHAR'||LPAD(SEQ_RECHARGE_ID.nextval,6,'0'), ?,  ?)";
	private static final String GET_ALL_STMT = "SELECT RECHARGE_ID, MEM_PHONE, to_char(RECHARGE_DATE,'yyyy-mm-dd HH24:mi:ss')RECHARGE_DATE ,RECHARGE_CHANGE FROM MEMBER_RECHARGE";
	private static final String GET_ONE_STMT = "SELECT RECHARGE_ID, MEM_PHONE, to_char(RECHARGE_DATE,'yyyy-mm-dd HH24:mi:ss')RECHARGE_DATE ,RECHARGE_CHANGE FROM MEMBER_RECHARGE WHERE RECHARGE_ID= ?";
	private static final String GET_ALL_BYMEMPHONE = "SELECT RECHARGE_ID, MEM_PHONE, RECHARGE_DATE ,RECHARGE_CHANGE FROM MEMBER_RECHARGE WHERE MEM_PHONE= ?";

	private static final String DELETE_MEMRECHARs = "DELETE FROM MEMBER_RECHARGE WHERE RECHARGE_ID = ?";

	@Override
	public void insert(MemRecharVO memRecharVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memRecharVO.getMemPhone());
			pstmt.setInt(2, memRecharVO.getRechargeChange());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public MemRecharVO findByPrimaryKey(String rechargeId) {

		MemRecharVO memRecharVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<MemRecharVO> findByMemPhone(String memPhone) {
		// TODO Auto-generated method stub
		List<MemRecharVO> list = new ArrayList<MemRecharVO>();
		MemRecharVO memRecharVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		MemRecharJDBCDAO dao = new MemRecharJDBCDAO();

		// 新增
		MemRecharVO memRecharVO1 = new MemRecharVO();
		memRecharVO1.setMemPhone("0921842851");
		memRecharVO1.setRechargeChange(new Integer(100));
		dao.insert(memRecharVO1);

		MemRecharVO memRecharVO2 = new MemRecharVO();
		memRecharVO2.setMemPhone("0921842851");
		memRecharVO2.setRechargeChange(new Integer(500));
		dao.insert(memRecharVO2);

		MemRecharVO memRecharVO3 = new MemRecharVO();
		memRecharVO3.setMemPhone("0921842851");
		memRecharVO3.setRechargeChange(new Integer(5000));
		dao.insert(memRecharVO3);
		// 刪除
		dao.delete("RECHAR000001");

		// 查詢
		MemRecharVO memRecharVO4 = dao.findByPrimaryKey("RECHAR000002");
		System.out.print(memRecharVO4.getRechargeId() + ",");
		System.out.print(memRecharVO4.getMemPhone() + ",");
		System.out.print(memRecharVO4.getRechargeDate() + ",");
		System.out.print(memRecharVO4.getRechargeChange() + ",");
		System.out.println("---------------------");

		// 查詢
		List<MemRecharVO> list = dao.getAll();
		for (MemRecharVO aMemRechar : list) {
			System.out.print(aMemRechar.getRechargeId() + ",");
			System.out.print(aMemRechar.getMemPhone() + ",");
			System.out.print(aMemRechar.getRechargeDate() + ",");
			System.out.print(aMemRechar.getRechargeChange() + ",");
		}
		System.out.println("---------------------");

		// 查詢
		List<MemRecharVO> list2 = dao.findByMemPhone("0921842851");
		for (MemRecharVO aMemRechar : list2) {
			System.out.print(aMemRechar.getRechargeId() + ",");
			System.out.print(aMemRechar.getMemPhone() + ",");
			System.out.print(aMemRechar.getRechargeDate() + ",");
			System.out.print(aMemRechar.getRechargeChange() + ",");
		}
		System.out.println("---------------------");
	}
}
