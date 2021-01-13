package com.reservesituation.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReserveSituationDAO implements ReserveSituationDAO_interface {

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

	private static final String INSERT_STMT = 
		"INSERT INTO RESERVE_SITUATION (RESERV_SITUATION_DATE,STORE_ID,PERIOD_ID,ACCEPT_GROUPS,RESERVED_GROUPS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT RESERV_SITUATION_DATE,STORE_ID,PERIOD_ID,ACCEPT_GROUPS,RESERVED_GROUPS FROM RESERVE_SITUATION";
	private static final String GET_ONE_STMT = 
		"SELECT RESERV_SITUATION_DATE,STORE_ID,PERIOD_ID,ACCEPT_GROUPS,RESERVED_GROUPS FROM RESERVE_SITUATION WHERE RESERV_SITUATION_DATE = ? AND STORE_ID = ? AND PERIOD_ID = ?";
	private static final String DELETE = 
		"DELETE FROM RESERVE_SITUATION WHERE RESERV_SITUATION_DATE > ? AND STORE_ID = ? AND PERIOD_ID = ?";
	private static final String UPDATE = 
		"UPDATE RESERVE_SITUATION SET ACCEPT_GROUPS = ?,RESERVED_GROUPS = ? WHERE RESERV_SITUATION_DATE = ? AND STORE_ID = ? AND PERIOD_ID = ?";
	private static final String SEARCH = "SELECT RESERV_SITUATION_DATE,STORE_ID,PERIOD_ID,ACCEPT_GROUPS,RESERVED_GROUPS FROM RESERVE_SITUATION WHERE RESERV_SITUATION_DATE = ? AND STORE_ID = ? ORDER BY PERIOD_ID";
	private static final String FORS = "SELECT RESERV_SITUATION_DATE,STORE_ID,PERIOD_ID,ACCEPT_GROUPS,RESERVED_GROUPS FROM RESERVE_SITUATION WHERE STORE_ID = ? ORDER BY RESERV_SITUATION_DATE";
	private static final String DELETEBYDATE = 
			"DELETE FROM RESERVE_SITUATION WHERE RESERV_SITUATION_DATE < ?";
	private static final String SCHEDULE2 = "SELECT RESERV_SITUATION_DATE FROM RESERVE_SITUATION WHERE STORE_ID = ? AND PERIOD_ID = ? ORDER BY RESERV_SITUATION_DATE DESC";
	@Override
	public List<ReserveSituationVO> schedule2(String storeId, Integer periodId) {
		List<ReserveSituationVO> list = new ArrayList<ReserveSituationVO>();
		ReserveSituationVO rsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(SCHEDULE2);
			pstmt.setString(1,storeId);
			pstmt.setInt(2,periodId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// rsVO 也稱為 Domain objects
				rsVO = new ReserveSituationVO();
				rsVO.setReserveSituationDate(rs.getDate("RESERV_SITUATION_DATE"));
				list.add(rsVO); // Store the row in the list
			}
		}
		catch (SQLException se) {
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
		return list;
	}
	
	@Override
	public List<ReserveSituationVO> fors(String storeId) {
		List<ReserveSituationVO> list = new ArrayList<ReserveSituationVO>();
		ReserveSituationVO rsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(FORS);
			pstmt.setString(1,storeId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// rsVO 也稱為 Domain objects
				rsVO = new ReserveSituationVO();
				rsVO.setReserveSituationDate(rs.getDate("RESERV_SITUATION_DATE"));
				rsVO.setStoreId(rs.getString("STORE_ID"));
				rsVO.setPeriodId(rs.getInt("PERIOD_ID"));
				rsVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				rsVO.setReservedGroups(rs.getInt("RESERVED_GROUPS"));
				list.add(rsVO); // Store the row in the list
			}
		}
		catch (SQLException se) {
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
		return list;
	}
	@Override
	public List<ReserveSituationVO> search(Date reserveSituationDate, String storeId) {
		List<ReserveSituationVO> list = new ArrayList<ReserveSituationVO>();
		ReserveSituationVO rsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setDate(1,reserveSituationDate);
			pstmt.setString(2,storeId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// rsVO 也稱為 Domain objects
				rsVO = new ReserveSituationVO();
				rsVO.setReserveSituationDate(rs.getDate("RESERV_SITUATION_DATE"));
				rsVO.setStoreId(rs.getString("STORE_ID"));
				rsVO.setPeriodId(rs.getInt("PERIOD_ID"));
				rsVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				rsVO.setReservedGroups(rs.getInt("RESERVED_GROUPS"));
				list.add(rsVO); // Store the row in the list
			}
		}
		catch (SQLException se) {
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
		return list;
	}

	@Override
	public void insert(ReserveSituationVO rsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//注意JDBC的prepareStatement是從1開始
			pstmt.setDate(1, rsVO.getReserveSituationDate());
			pstmt.setString(2, rsVO.getStoreId());
			pstmt.setInt(3, rsVO.getPeriodId());
			pstmt.setInt(4, rsVO.getAcceptGroups());
			pstmt.setInt(5, rsVO.getReservedGroups());
			

			pstmt.executeUpdate();

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
	public void update(ReserveSituationVO rsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setInt(1, rsVO.getAcceptGroups());
			pstmt.setInt(2, rsVO.getReservedGroups());
			pstmt.setDate(3, rsVO.getReserveSituationDate());
			pstmt.setString(4, rsVO.getStoreId());
			pstmt.setInt(5, rsVO.getPeriodId());
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ReserveSituationVO findByPrimaryKey(Date reserveSituationDate, String storeId, Integer periodId) {

		ReserveSituationVO rsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setDate(1,reserveSituationDate);
			pstmt.setString(2,storeId);
			pstmt.setInt(3,periodId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rsVO = new ReserveSituationVO();
				rsVO.setReserveSituationDate(rs.getDate("RESERV_SITUATION_DATE"));
				rsVO.setStoreId(rs.getString("STORE_ID"));
				rsVO.setPeriodId(rs.getInt("PERIOD_ID"));
				rsVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				rsVO.setReservedGroups(rs.getInt("RESERVED_GROUPS"));
			}

			// Handle any driver errors
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
		return rsVO;
	}
	//他這邊getAll 就是把 Select * 用List接(一筆一行, 可單獨取出?)
	@Override
	public List<ReserveSituationVO> getAll() {
		List<ReserveSituationVO> list = new ArrayList<ReserveSituationVO>();
		ReserveSituationVO rsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// rsVO 也稱為 Domain objects
				rsVO = new ReserveSituationVO();
				rsVO.setReserveSituationDate(rs.getDate("RESERV_SITUATION_DATE"));
				rsVO.setStoreId(rs.getString("STORE_ID"));
				rsVO.setPeriodId(rs.getInt("PERIOD_ID"));
				rsVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				rsVO.setReservedGroups(rs.getInt("RESERVED_GROUPS"));
				list.add(rsVO); // Store the row in the list
			}

			// Handle any driver errors
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

	@Override
	public void delete(Date reserveSituationDate, String storeId, Integer periodId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setDate(1, reserveSituationDate);
			pstmt.setString(2, storeId);
			pstmt.setInt(3, periodId);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void deleteByDate(Date reserveSituationDate) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEBYDATE);

			pstmt.setDate(1, reserveSituationDate);

			pstmt.executeUpdate();

			// Handle any driver errors
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
}