package com.reserveorder.model;
//這是JDBC寫法, 之後會改成DAO(連線池寫法)
//目前新增查詢修改都OK
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class ReserveOrderJDBCDAO implements ReserveOrderDAO_interface {

	@Override
	public void schedule3(Date reserveTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReserveOrderVO> fors(String storeId, Integer reserveStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReserveOrderVO> forsold(String storeId, Integer reserveStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	@Override
	public List<ReserveOrderVO> forcold(String memPhone, Integer reserveStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String INSERT_STMT = 
		"INSERT INTO RESERVE_ORDER(RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,"
		+ "RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE,RESERV_CREATE_TIME)"
		+ "VALUES('RI' || LPAD(SEQ_RESERVE_ORDER_ID.NEXTVAL,10,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,"
		+ "RESERV_NOTE,RESERV_CREATE_TIME FROM RESERVE_ORDER ORDER BY RESERV_ID";
	private static final String GET_ONE_STMT = "SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE," + 
			"RESERV_CREATE_TIME FROM RESERVE_ORDER WHERE RESERV_ID = ?";
	private static final String DELETE = 
		"DELETE FROM RESERVE_ORDER WHERE RESERV_ID = ?";
	private static final String UPDATE = 
		"UPDATE RESERVE_ORDER SET RESERV_STATUS = ? WHERE RESERV_ID = ?";

	@Override
	public void insert(ReserveOrderVO roVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roVO.getStoreId());
			pstmt.setString(2, roVO.getMemPhone());
			pstmt.setInt(3, roVO.getPeriodId());
			pstmt.setDate(4, roVO.getReserveTime());
			pstmt.setInt(5, roVO.getReserveAdult());
			pstmt.setInt(6, roVO.getReserveChild());
			pstmt.setInt(7, roVO.getReserveStatus());
			pstmt.setString(8, roVO.getReserveNote());
			pstmt.setTimestamp(9, roVO.getReserveCreateTime());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(ReserveOrderVO roVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roVO.getReserveStatus());
			pstmt.setString(2, roVO.getReserveId());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String reservId) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<ReserveOrderVO> findByPrimaryKey(String memPhone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReserveOrderVO> getAll() {
		List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
		ReserveOrderVO roVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// arVO 也稱為 Domain objects
				roVO = new ReserveOrderVO();
				roVO.setReserveId(rs.getString("RESERV_ID"));
				roVO.setStoreId(rs.getString("STORE_ID"));
				roVO.setMemPhone(rs.getString("MEM_PHONE"));
				roVO.setPeriodId(rs.getInt("PERIOD_ID"));
				roVO.setReserveTime(rs.getDate("RESERV_TIME"));
				roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
				roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
				roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
				roVO.setReserveNote(rs.getString("RESERV_NOTE"));
				roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
				list.add(roVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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

		ReserveOrderJDBCDAO dao = new ReserveOrderJDBCDAO();

		// 新增
//		ReserveOrderVO roVO1 = new ReserveOrderVO();
//		roVO1.setStoreId("S000001");
//		roVO1.setMemPhone("0921842853");
//		roVO1.setPeriodId(1);
//		roVO1.setReserveTime(java.sql.Date.valueOf("2020-12-15"));
//		roVO1.setReserveAdult(2);
//		roVO1.setReserveChild(0);
//		roVO1.setReserveStatus(0);
//		roVO1.setReserveNote("需求11");
//		roVO1.setReserveCreateTime(java.sql.Timestamp.valueOf("2020-12-10 12:00:00"));
//		dao.insert(roVO1);

		// 修改
//		ReserveOrderVO roVO2 = new ReserveOrderVO();
//		roVO2.setReserveStatus(0);
//		roVO2.setReserveId("RI0000000010");
//		dao.update(roVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		ReserveOrderVO roVO3 = dao.findByPrimaryKey("RI0000000008");
//		System.out.print(roVO3.getReserveId() + ",");
//		System.out.print(roVO3.getStoreId() + ",");
//		System.out.print(roVO3.getMemPhone() + ",");
//		System.out.print(roVO3.getPeriodId() + ",");
//		System.out.print(roVO3.getReserveTime() + ",");
//		System.out.print(roVO3.getReserveAdult() + ",");
//		System.out.print(roVO3.getReserveChild() + ",");
//		System.out.print(roVO3.getReserveStatus() + ",");
//		System.out.print(roVO3.getReserveNote() + ",");
//		System.out.print(roVO3.getReserveCreateTime() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
		List<ReserveOrderVO> list = dao.getAll();
		for (ReserveOrderVO aar : list) {
			System.out.print(aar.getReserveId() + ",");
			System.out.print(aar.getStoreId() + ",");
			System.out.print(aar.getMemPhone() + ",");
			System.out.print(aar.getPeriodId() + ",");
			System.out.print(aar.getReserveTime() + ",");
			System.out.print(aar.getReserveAdult() + ",");
			System.out.print(aar.getReserveChild() + ",");
			System.out.print(aar.getReserveStatus() + ",");
			System.out.print(aar.getReserveNote() + ",");
			System.out.print(aar.getReserveCreateTime() + ",");
//			
//			System.out.println();
		}
	}

	@Override
	public List<ReserveOrderVO> forc(String storeId,Integer reserveStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}