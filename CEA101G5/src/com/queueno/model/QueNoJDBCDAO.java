package com.queueno.model;

import java.sql.*;
import java.util.*;

import com.queueno.model.QueNoVO;

public class QueNoJDBCDAO implements QueNoDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO queue_no (queue_num, mem_phone, party, queue_no_time, store_id, queue_period_id, queue_line_no, queue_table_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE queue_no set queue_no_time=?, party=?, store_id=?, queue_period_id=?, queue_line_no=?, queue_table_id=? where queue_num=? AND mem_phone=?";
	private static final String DELETE = "DELETE FROM queue_no where queue_num=?";
	private static final String GET_ONE_STMT = "SELECT queue_num, mem_phone, party, queue_no_time, store_id, queue_period_id, queue_line_no, queue_table_id From queue_no where queue_num=? AND mem_phone=?";
	private static final String GET_ALL_STMT = "SELECT * From queue_no order by queue_num";
	private static final String GET_PART_STMT = "SELECT queue_num, mem_phone, party, queue_no_time, store_id, queue_period_id, queue_line_no, queue_table_id FROM queue_no where store_id = ?";
	private static final String GET_NUM_BY_PHONE = "SELECT * FROM queue_no where mem_phone=?";
	private static final String GET_NUM_BY_STORE_AND_TABLE = "SELECT queue_num FROM queue_no where store_id = ? AND queue_table_id=? order by queue_num";

	
	// ?���?
	@Override
	public void insert(QueNoVO quenoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	// 修改
	@Override
	public void update(QueNoVO quenoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	// ?��
	@Override
	public void delete(String memphone) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memphone);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	// 主鍵?���?
	@Override
	public QueNoVO findByPrimaryKey(Integer queuenum, String memphone) {
		QueNoVO quenoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, queuenum);
			pstmt.setString(2, memphone);

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

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_STMT);
			
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

	// ??��?�查�?
	@Override
	public QueNoVO findByPhone(String memphone) {
		QueNoVO quenoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NUM_BY_PHONE);

			pstmt.setString(1, memphone);

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

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return quenoVO;
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

//	@Override
//	public List<QueNoVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	public static void main(String[] args) {

		QueNoJDBCDAO dao = new QueNoJDBCDAO();
		
//		// ?���?
//		QueNoVO quenoVO1 = new QueNoVO();
//		quenoVO1.setQueuenum(9);
//		quenoVO1.setMemphone("0921842852");
//		quenoVO1.setParty(2);
//		quenoVO1.setQueuenotime(new Timestamp(System.currentTimeMillis()));
//		quenoVO1.setStoreid("S000001");
//		quenoVO1.setQueueperiodid(1);
//		quenoVO1.setQueuelineno(1);
//		quenoVO1.setQueuetableid(1);
//		dao.insert(quenoVO1);

//		 修改 (�D��queuenum+memphone�A�~��memephone�Bstoreid�Bqueueperiodid�Bqueuetableid)
//		QueNoVO quenoVO2 = new QueNoVO();
//		quenoVO2.setQueuenum(1);
//		quenoVO2.setMemphone("0921842852");
//		quenoVO2.setParty(3);
//		quenoVO2.setQueuenotime(new Timestamp(System.currentTimeMillis()));
//		quenoVO2.setStoreid("S000001");
//		quenoVO2.setQueueperiodid(3);
//		quenoVO2.setQueuelineno(2);
//		quenoVO2.setQueuetableid(1);		
//		dao.update(quenoVO2);

		// ?��
//		dao.delete(3);

		// ?��主鍵
//		QueNoVO quenoVO3 = dao.findByPrimaryKey(4,"0921842855");
//		System.out.print(quenoVO3.getQueuenum()+",");
//		System.out.print(quenoVO3.getMemphone()+",");
//		System.out.print(quenoVO3.getParty()+",");
//		System.out.print(quenoVO3.getQueuenotime()+",");
//		System.out.print(quenoVO3.getStoreid()+",");
//		System.out.print(quenoVO3.getQueueperiodid()+",");
//		System.out.print(quenoVO3.getQueuelineno()+",");
//		System.out.print(quenoVO3.getQueuetableid());
		
//		QueNoVO quenoVO3 = dao.findByPhone("0921842855");
//		System.out.print(quenoVO3.getQueuenum()+",");
//		System.out.print(quenoVO3.getMemphone()+",");
//		System.out.print(quenoVO3.getParty()+",");
//		System.out.print(quenoVO3.getQueuenotime()+",");
//		System.out.print(quenoVO3.getStoreid()+",");
//		System.out.print(quenoVO3.getQueueperiodid()+",");
//		System.out.print(quenoVO3.getQueuelineno()+",");
//		System.out.print(quenoVO3.getQueuetableid());
		
		

		// ?��?���?
//		List<QueNoVO> list = dao.getAll();
//		for(QueNoVO aQue :list) {
//			System.out.print(aQue.getQueuenum()+",");
//			System.out.print(aQue.getMemphone()+",");
//			System.out.print(aQue.getParty()+",");
//			System.out.print(aQue.getQueuenotime()+",");
//			System.out.print(aQue.getStoreid()+",");
//			System.out.print(aQue.getQueueperiodid()+",");
//			System.out.print(aQue.getQueuelineno()+",");
//			System.out.print(aQue.getQueuetableid());
//			System.out.println();
//		}

//		 依�?��?�查�?
		List<QueNoVO> list = dao.findByStoreid("S000001");
		for (QueNoVO mQue : list) {
			System.out.print(mQue.getQueuenum() + ",");
			System.out.print(mQue.getMemphone() + ",");
			System.out.print(mQue.getParty() + ",");
			System.out.print(mQue.getQueuenotime() + ",");
			System.out.print(mQue.getStoreid() + ",");
			System.out.print(mQue.getQueueperiodid() + ",");
			System.out.print(mQue.getQueuelineno() + ",");
			System.out.print(mQue.getQueuetableid());
			System.out.println();
		}
		
//		List<QueNoVO> list = dao.findByStoreidAndTableid("S000001", 1);
//		for (QueNoVO mQue : list) {
//			System.out.print(mQue.getQueuenum() + ",");
//			System.out.println();
//		}

	}

	@Override
	public QueNoVO findByPhoneAndStore(String memphone, String storeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QueNoVO> findByStoreidAndTableid(String storeid, Integer queuetableid) {
		List<QueNoVO> list = new ArrayList<QueNoVO>();
		QueNoVO quenoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NUM_BY_STORE_AND_TABLE);
			
			pstmt.setString(1,storeid);
			pstmt.setInt(2, queuetableid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quenoVO = new QueNoVO();
				quenoVO.setQueuenum(rs.getInt("queue_num"));
//				quenoVO.setParty(rs.getInt("party"));
//				quenoVO.setQueuenotime(rs.getTimestamp("queue_no_time"));
//				quenoVO.setStoreid(rs.getString("store_id"));
//				quenoVO.setQueueperiodid(rs.getInt("queue_period_id"));
//				quenoVO.setQueuelineno(rs.getInt("queue_line_no"));
//				quenoVO.setQueuetableid(rs.getInt("queue_table_id"));
//				quenoVO.setMemphone(rs.getString("mem_phone"));
				list.add(quenoVO);
			}

			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
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
	

}