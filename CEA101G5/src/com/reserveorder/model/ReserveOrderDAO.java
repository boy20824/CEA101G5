package com.reserveorder.model;
//DAO是JDBCDAO的連線池寫法
import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.acceptreserve.model.AcceptReserveVO;
//DAO測試就直接main方法
public class ReserveOrderDAO implements ReserveOrderDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5"); //記得要去Server的context.xml註冊DB
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO RESERVE_ORDER(RESERV_ID,STORE_ID,PERIOD_ID,MEM_PHONE,RESERV_TIME,"
			+ "RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE)"
			+ "VALUES('RI' || LPAD(SEQ_RESERVE_ORDER_ID.NEXTVAL,10,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT RESERV_ID,STORE_ID,PERIOD_ID,MEM_PHONE,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,"
			+ "RESERV_NOTE,RESERV_CREATE_TIME FROM RESERVE_ORDER ORDER BY RESERV_TIME DESC";
		private static final String GET_ONE_STMT = "SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE,\r\n" + 
				"RESERV_CREATE_TIME FROM RESERVE_ORDER WHERE MEM_PHONE = ? ORDER BY RESERV_TIME DESC";
		private static final String DELETE = 
			"DELETE FROM RESERVE_ORDER WHERE RESERV_ID = ?";
		private static final String UPDATE = 
			"UPDATE RESERVE_ORDER SET RESERV_STATUS = ? WHERE RESERV_ID = ?";
		
		private static final String SCHEDULE3 = 
				"UPDATE RESERVE_ORDER SET RESERV_STATUS = 1 WHERE RESERV_TIME <= ? AND RESERV_STATUS = 0";

		
		private static final String FORS = "SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE,\r\n" + 
				"RESERV_CREATE_TIME FROM RESERVE_ORDER WHERE STORE_ID = ? AND RESERV_STATUS = ? ORDER BY RESERV_TIME";
		
		private static final String FORSOLD = "SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE,\r\n" + 
				"RESERV_CREATE_TIME FROM RESERVE_ORDER WHERE STORE_ID = ? AND RESERV_STATUS != ? ORDER BY RESERV_TIME DESC";
		
		
		private static final String FORCNEW = "SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE,\r\n" + 
				"RESERV_CREATE_TIME FROM RESERVE_ORDER WHERE MEM_PHONE = ? AND RESERV_STATUS = ? ORDER BY RESERV_TIME";
		
		private static final String FORCOLD = "SELECT RESERV_ID,STORE_ID,MEM_PHONE,PERIOD_ID,RESERV_TIME,RESERV_ADULT,RESERV_CHILD,RESERV_STATUS,RESERV_NOTE,\r\n" + 
				"RESERV_CREATE_TIME FROM RESERVE_ORDER WHERE MEM_PHONE = ? AND RESERV_STATUS != ? ORDER BY RESERV_TIME DESC";
		
		@Override
		public void schedule3(Date reserveTime) {
			

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SCHEDULE3);

				pstmt.setDate(1, reserveTime);

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
		public List<ReserveOrderVO> findByPrimaryKey(String memPhone) {
			List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
			ReserveOrderVO roVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1,memPhone);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					roVO = new ReserveOrderVO();
					roVO.setReserveId(rs.getString("RESERV_ID"));
					roVO.setStoreId(rs.getString("STORE_ID"));
					roVO.setPeriodId(rs.getInt("PERIOD_ID"));
					roVO.setMemPhone(rs.getString("MEM_PHONE"));
					roVO.setReserveTime(rs.getDate("RESERV_TIME"));
					roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
					roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
					roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
					roVO.setReserveNote(rs.getString("RESERV_NOTE"));
					roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
					list.add(roVO);
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
		public List<ReserveOrderVO> forcold(String memPhone,Integer reserveStatus) {
			List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
			ReserveOrderVO roVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(FORCOLD);
				pstmt.setString(1,memPhone);
				pstmt.setInt(2,reserveStatus);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					roVO = new ReserveOrderVO();
					roVO.setReserveId(rs.getString("RESERV_ID"));
					roVO.setStoreId(rs.getString("STORE_ID"));
					roVO.setPeriodId(rs.getInt("PERIOD_ID"));
					roVO.setMemPhone(rs.getString("MEM_PHONE"));
					roVO.setReserveTime(rs.getDate("RESERV_TIME"));
					roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
					roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
					roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
					roVO.setReserveNote(rs.getString("RESERV_NOTE"));
					roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
					list.add(roVO);
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
		public List<ReserveOrderVO> forc(String memPhone,Integer reserveStatus) {
			List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
			ReserveOrderVO roVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(FORCNEW);
				pstmt.setString(1,memPhone);
				pstmt.setInt(2,reserveStatus);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					roVO = new ReserveOrderVO();
					roVO.setReserveId(rs.getString("RESERV_ID"));
					roVO.setStoreId(rs.getString("STORE_ID"));
					roVO.setPeriodId(rs.getInt("PERIOD_ID"));
					roVO.setMemPhone(rs.getString("MEM_PHONE"));
					roVO.setReserveTime(rs.getDate("RESERV_TIME"));
					roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
					roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
					roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
					roVO.setReserveNote(rs.getString("RESERV_NOTE"));
					roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
					list.add(roVO);
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
		public List<ReserveOrderVO> forsold(String storeId,Integer reserveStatus) {
			List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
			ReserveOrderVO roVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(FORSOLD);
				pstmt.setString(1,storeId);
				pstmt.setInt(2,reserveStatus);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					roVO = new ReserveOrderVO();
					roVO.setReserveId(rs.getString("RESERV_ID"));
					roVO.setStoreId(rs.getString("STORE_ID"));
					roVO.setPeriodId(rs.getInt("PERIOD_ID"));
					roVO.setMemPhone(rs.getString("MEM_PHONE"));
					roVO.setReserveTime(rs.getDate("RESERV_TIME"));
					roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
					roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
					roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
					roVO.setReserveNote(rs.getString("RESERV_NOTE"));
					roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
					list.add(roVO);
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
		public List<ReserveOrderVO> fors(String storeId,Integer reserveStatus) {
			List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
			ReserveOrderVO roVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(FORS);
				pstmt.setString(1,storeId);
				pstmt.setInt(2,reserveStatus);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					roVO = new ReserveOrderVO();
					roVO.setReserveId(rs.getString("RESERV_ID"));
					roVO.setStoreId(rs.getString("STORE_ID"));
					roVO.setPeriodId(rs.getInt("PERIOD_ID"));
					roVO.setMemPhone(rs.getString("MEM_PHONE"));
					roVO.setReserveTime(rs.getDate("RESERV_TIME"));
					roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
					roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
					roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
					roVO.setReserveNote(rs.getString("RESERV_NOTE"));
					roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
					list.add(roVO);
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
	public void insert(ReserveOrderVO roVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, roVO.getStoreId());
			pstmt.setInt(2, roVO.getPeriodId());
			pstmt.setString(3, roVO.getMemPhone());
			pstmt.setDate(4, roVO.getReserveTime());
			pstmt.setInt(5, roVO.getReserveAdult());
			pstmt.setInt(6, roVO.getReserveChild());
			pstmt.setInt(7, roVO.getReserveStatus());
			pstmt.setString(8, roVO.getReserveNote());

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
	public void update(ReserveOrderVO roVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roVO.getReserveStatus());
			pstmt.setString(2, roVO.getReserveId());

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
	public void delete(String reservId) {
	}


	@Override
	public List<ReserveOrderVO> getAll() {
		List<ReserveOrderVO> list = new ArrayList<ReserveOrderVO>();
		ReserveOrderVO roVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roVO = new ReserveOrderVO();
				roVO.setReserveId(rs.getString("RESERV_ID"));
				roVO.setStoreId(rs.getString("STORE_ID"));
				roVO.setPeriodId(rs.getInt("PERIOD_ID"));
				roVO.setMemPhone(rs.getString("MEM_PHONE"));
				roVO.setReserveTime(rs.getDate("RESERV_TIME"));
				roVO.setReserveAdult(rs.getInt("RESERV_ADULT"));
				roVO.setReserveChild(rs.getInt("RESERV_CHILD"));
				roVO.setReserveStatus(rs.getInt("RESERV_STATUS"));
				roVO.setReserveNote(rs.getString("RESERV_NOTE"));
				roVO.setReserveCreateTime(rs.getTimestamp("RESERV_CREATE_TIME"));
				list.add(roVO);
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
}