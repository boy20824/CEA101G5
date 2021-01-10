package com.queueno.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QueNoDAO implements QueNoDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO queue_no (queue_num, mem_phone, party, queue_no_time, store_id, queue_period_id, queue_line_no, queue_table_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE queue_no set queue_no_time=?, party=?, store_id=?, queue_period_id=?, queue_line_no=?, queue_table_id=? where queue_num=? AND mem_phone=?";
	private static final String DELETE = "DELETE FROM queue_no where mem_phone=?";
	private static final String GET_ONE_STMT = "SELECT queue_num, mem_phone, party, queue_no_time, store_id, queue_period_id, queue_line_no, queue_table_id From queue_no where queue_num=? AND mem_phone=?";
	private static final String GET_ALL_STMT = "SELECT * FROM queue_no order by queue_num";
	private static final String GET_NUM_BY_STORE = "SELECT queue_num, mem_phone, party, queue_no_time, store_id, queue_period_id, queue_line_no, queue_table_id FROM queue_no where store_id = ? order by queue_num";
	private static final String GET_NUM_BY_PHONE = "SELECT * FROM queue_no where mem_phone=?";
	private static final String GET_NUM_BY_PHONE_AND_STORE = "SELECT * FROM queue_no where mem_phone=? AND store_id = ?";
	private static final String GET_NUM_BY_STORE_AND_TABLE = "SELECT * FROM queue_no where store_id = ? AND queue_table_id=?";

	
	// ?���?
	@Override
	public void insert(QueNoVO quenoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, quenoVO.getQueuenum());
			pstmt.setString(2, quenoVO.getMemphone());
			pstmt.setInt(3, quenoVO.getParty());
			pstmt.setTimestamp(4, quenoVO.getQueuenotime());
			pstmt.setString(5, quenoVO.getStoreid());
			pstmt.setInt(6, quenoVO.getQueueperiodid());
			pstmt.setInt(7, quenoVO.getQueuelineno());
			pstmt.setInt(8, quenoVO.getQueuetableid());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	// 修改
	@Override
	public void update(QueNoVO quenoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, quenoVO.getQueuenotime());
			pstmt.setInt(2, quenoVO.getParty());
			pstmt.setString(3, quenoVO.getStoreid());
			pstmt.setInt(4, quenoVO.getQueueperiodid());
			pstmt.setInt(5, quenoVO.getQueuelineno());
			pstmt.setInt(6, quenoVO.getQueuetableid());
			pstmt.setInt(7, quenoVO.getQueuenum());
			pstmt.setString(8, quenoVO.getMemphone());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	// ?��
	@Override
	public void delete(String memphone) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memphone);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	// 主鍵?���?
	@Override
	public QueNoVO findByPrimaryKey(Integer queuenum, String memphone) {
		QueNoVO quenoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, queuenum);
			pstmt.setString(2, memphone);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				quenoVO = new QueNoVO();
				quenoVO.setQueuenum(rs.getInt("queue_num"));
				quenoVO.setMemphone(rs.getString("mem_phone"));
				quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
				quenoVO.setStoreid(rs.getString("store_id"));
				quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
				quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
				quenoVO.setQueuetableid(rs.getInt("queue_table_id"));

			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return quenoVO;
	}

	// ??��?�查�?
		@Override
		public QueNoVO findByPhone(String memphone) {
			QueNoVO quenoVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_NUM_BY_PHONE);

				pstmt.setString(1, memphone);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					quenoVO = new QueNoVO();
					quenoVO.setQueuenum(rs.getInt("queue_num"));
					quenoVO.setMemphone(rs.getString("mem_phone"));
					quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
					quenoVO.setStoreid(rs.getString("store_id"));
					quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
					quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
					quenoVO.setQueuetableid(rs.getInt("queue_table_id"));

				}

				// Handle any driver errors
			}  catch (SQLException se) {
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
			return quenoVO;
		}
		
		// ??��??+店�?�查�?
		@Override
		public QueNoVO findByPhoneAndStore(String memphone, String storeid) {
			QueNoVO quenoVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_NUM_BY_PHONE_AND_STORE);
				
				pstmt.setString(1, memphone);
				pstmt.setString(2, storeid);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					quenoVO = new QueNoVO();
					quenoVO.setQueuenum(rs.getInt("queue_num"));
					quenoVO.setMemphone(rs.getString("mem_phone"));
					quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
					quenoVO.setStoreid(rs.getString("store_id"));
					quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
					quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
					quenoVO.setQueuetableid(rs.getInt("queue_table_id"));
					
				}
				
				// Handle any driver errors
			}  catch (SQLException se) {
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
			return quenoVO;
		}
	
	// 店�?�查�?
	@Override
	public List<QueNoVO> findByStoreid(String storeid) {
		List<QueNoVO> list = new ArrayList<QueNoVO>();
		QueNoVO quenoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NUM_BY_STORE);
			
			pstmt.setString(1,storeid);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quenoVO = new QueNoVO();
				quenoVO.setQueuenum(rs.getInt("queue_num"));
				quenoVO.setMemphone(rs.getString("mem_phone"));
				quenoVO.setParty(rs.getInt("party"));
				quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
				quenoVO.setStoreid(rs.getString("store_id"));
				quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
				quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
				quenoVO.setQueuetableid(rs.getInt("queue_table_id"));
				list.add(quenoVO);
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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

	// ?��?���?
	@Override
	public List<QueNoVO> getAll() {
		List<QueNoVO> list = new ArrayList<QueNoVO>();
		QueNoVO quenoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quenoVO = new QueNoVO();
				quenoVO.setQueuenum(rs.getInt("queue_num"));
				quenoVO.setMemphone(rs.getString("mem_phone"));
				quenoVO.setParty(rs.getInt("party"));
				quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
				quenoVO.setStoreid(rs.getString("store_id"));
				quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
				quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
				quenoVO.setQueuetableid(rs.getInt("queue_table_id"));
				list.add(quenoVO);
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
//
//	@Override
//	public List<QueNoVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<QueNoVO> findByStoreidAndTableid(String storeid, Integer queuetableid) {
		List<QueNoVO> list = new ArrayList<QueNoVO>();
		QueNoVO quenoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NUM_BY_STORE_AND_TABLE);
			
			pstmt.setString(1,storeid);
			pstmt.setInt(2, queuetableid);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quenoVO = new QueNoVO();
				quenoVO.setQueuenum(rs.getInt("queue_num"));
				quenoVO.setMemphone(rs.getString("mem_phone"));
				quenoVO.setParty(rs.getInt("party"));
				quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
//				quenoVO.setStoreid(rs.getString("store_id"));
				quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
				quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
//				quenoVO.setQueuetableid(rs.getInt("queue_table_id"));
				list.add(quenoVO);
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	
}
