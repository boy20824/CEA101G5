package com.queuetable.model;

import java.sql.*;
import java.util.*;

public class QueTableJDBCDAO implements QueTableDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO queue_table (queue_table_id,  queue_table_type,store_id,queue_table_ttl, queue_table_usable, queue_table_occ) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE queue_table set queue_table_type=?, queue_table_ttl=?, queue_table_usable=?, queue_table_occ=? where queue_table_id=? AND store_id=?";
	private static final String DELETE = "DELETE FROM queue_table where queue_table_id = ? AND store_id = ?";
	private static final String GET_ONE_STMT = "SELECT queue_table_id, store_id, queue_table_type, queue_table_ttl, queue_table_usable, queue_table_occ FROM queue_table where queue_table_id = ? AND store_id = ?";
	private static final String GET_ALL_STMT = "SELECT queue_table_id, store_id, queue_table_type, queue_table_ttl, queue_table_usable, queue_table_occ FROM queue_table order by queue_table_id";
	private static final String GET_PART_STMT = "SELECT queue_table_id, store_id, queue_table_type, queue_table_ttl, queue_table_usable, queue_table_occ FROM queue_table where store_id = ?";

	// �s�W
	@Override
	public void insert(QueTableVO quetableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, quetableVO.getQueuetableid());
			pstmt.setString(2, quetableVO.getQueuetabletype());
			pstmt.setString(3, quetableVO.getStoreid());
			pstmt.setInt(4, quetableVO.getQueuetablettl());
			pstmt.setInt(5, quetableVO.getQueuetableusable());
			pstmt.setInt(6, quetableVO.getQueuetableocc());

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

	// �ק�
	@Override
	public void update(QueTableVO quetableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, quetableVO.getQueuetabletype());
			pstmt.setInt(2, quetableVO.getQueuetablettl());
			pstmt.setInt(3, quetableVO.getQueuetableusable());
			pstmt.setInt(4, quetableVO.getQueuetableocc());
			pstmt.setInt(5, quetableVO.getQueuetableid());
			pstmt.setString(6, quetableVO.getStoreid());

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

	// �R���A�����p�L�k�R
	@Override
	public void delete(Integer quetableid, String storeid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, quetableid);
			pstmt.setString(2, storeid);

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

	// �̥D��d��(queuetableid+orderid)
	@Override
	public QueTableVO findByPrimaryKey(Integer quetableid, String storeid) {

		QueTableVO quetableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, quetableid);
			pstmt.setString(2, storeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				quetableVO = new QueTableVO();
				quetableVO.setQueuetableid(rs.getInt("queue_table_id"));
				quetableVO.setStoreid(rs.getString("store_id"));
				quetableVO.setQueuetabletype(rs.getString("queue_table_type"));
				quetableVO.setQueuetablettl(rs.getInt("queue_table_ttl"));
				quetableVO.setQueuetableusable(rs.getInt("queue_table_usable"));
				quetableVO.setQueuetableocc(rs.getInt("queue_table_occ"));
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
		return quetableVO;
	}
	
	// �d����
	@Override
	public List<QueTableVO> getAll() {
		List<QueTableVO> list = new ArrayList<QueTableVO>();
		QueTableVO quetableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quetableVO = new QueTableVO();
				quetableVO.setQueuetableid(rs.getInt("queue_table_id"));
				quetableVO.setStoreid(rs.getString("store_id"));
				quetableVO.setQueuetabletype(rs.getString("queue_table_type"));
				quetableVO.setQueuetablettl(rs.getInt("queue_table_ttl"));
				quetableVO.setQueuetableusable(rs.getInt("queue_table_usable"));
				quetableVO.setQueuetableocc(rs.getInt("queue_table_occ"));
				list.add(quetableVO);
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

	// �̩����d��
	@Override
	public List<QueTableVO> findByStoreid(String storeid) {
		List<QueTableVO> list = new ArrayList<QueTableVO>();
		QueTableVO quetableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_STMT);

			pstmt.setString(1, storeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				quetableVO = new QueTableVO();
				quetableVO.setQueuetableid(rs.getInt("queue_table_id"));
				quetableVO.setStoreid(rs.getString("store_id"));
				quetableVO.setQueuetabletype(rs.getString("queue_table_type"));
				quetableVO.setQueuetablettl(rs.getInt("queue_table_ttl"));
				quetableVO.setQueuetableusable(rs.getInt("queue_table_usable"));
				quetableVO.setQueuetableocc(rs.getInt("queue_table_occ"));
				list.add(quetableVO);
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
		return list;
	}

	public static void main(String[] args) {
		QueTableJDBCDAO dao = new QueTableJDBCDAO();

		// �s�W
//		QuetableVO quetVO1 = new QuetableVO();
//		quetVO1.setQueuetableid(4);
//		quetVO1.setStoreid("S002");
//		quetVO1.setQueuetabletype("10P");
//		quetVO1.setQueuetablettl(15);
//		quetVO1.setQueuetableusable(15);
//		quetVO1.setQueuetableocc(10);
//		dao.insert(quetVO1);

		 //�ק�
//		QuetableVO quepVO2 = new QuetableVO();
//		quepVO2.setQueuetableid(1);
//		quepVO2.setStoreid("S001");
//		quepVO2.setQueuetabletype("5P");
//		quepVO2.setQueuetablettl(5);
//		quepVO2.setQueuetableusable(2);
//		quepVO2.setQueuetableocc(3);
//		dao.update(quepVO2);

		// �R��,�����p�L�k�R
//		dao.delete(1, "S001	");

		// �H�D��d��
//		QuetableVO quetableVO3 = dao.findByPrimaryKey(1, "S001");
//		System.out.print(quetableVO3.getQueuetableid() + ",");
//		System.out.print(quetableVO3.getStoreid() + ",");
//		System.out.print(quetableVO3.getQueuetabletype() + ",");
//		System.out.print(quetableVO3.getQueuetablettl() + ",");
//		System.out.print(quetableVO3.getQueuetableusable() + ",");
//		System.out.println(quetableVO3.getQueuetableocc());
//		System.out.println("-----------------------");

		// �d�ߥ���
//		List<QuetableVO> list = dao.getAll();
//		for(QuetableVO aQue : list) {
//			System.out.print(aQue.getQueuetableid() + ",");
//			System.out.print(aQue.getStoreid() + ",");
//			System.out.print(aQue.getQueuetabletype() + ",");
//			System.out.print(aQue.getQueuetablettl() + ",");
//			System.out.print(aQue.getQueuetableusable() + ",");
//			System.out.println(aQue.getQueuetableocc());
//			System.out.println();
//	}
	
		// �̩����d��
		List<QueTableVO> list = dao.findByStoreid("S000001");
		for (QueTableVO sQue : list) {
			System.out.print(sQue.getQueuetableid() + ",");
			System.out.print(sQue.getStoreid() + ",");
			System.out.print(sQue.getQueuetabletype() + ",");
			System.out.print(sQue.getQueuetablettl() + ",");
			System.out.print(sQue.getQueuetableusable() + ",");
			System.out.println(sQue.getQueuetableocc());
		
		}
	}

}
