package com.queueline.model;

import java.sql.*;
import java.util.*;

import com.queueline.model.QueLineJDBCDAO;
import com.queueline.model.QueLineVO;
import com.queuetable.model.QueTableVO;

public class QueLineJDBCDAO implements QueLineDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO queue_line (queue_line_no,  queue_no_call ,store_id, queue_table_id) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE queue_line set queue_no_call=? where queue_line_no=? AND store_id=? AND queue_table_id=?";
	private static final String DELETE = "DELETE FROM queue_line where queue_line_no=? AND store_id=? AND queue_table_id=?";
	private static final String GET_ONE_STMT = "SELECT queue_line_no, queue_no_call, store_id, queue_table_id FROM queue_line where queue_line_no=? AND store_id=? AND queue_table_id=?";
	private static final String GET_ALL_STMT = "SELECT * FROM queue_line order by queue_line_no";
	private static final String GET_PART_STMT = "SELECT queue_line_no, queue_no_call, store_id, queue_table_id FROM queue_line where store_id=?";

	// ?���?
	@Override
	public void insert(QueLineVO quelineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, quelineVO.getQueuelineno());
			pstmt.setInt(2, quelineVO.getQueuenocall());
			pstmt.setString(3, quelineVO.getStoreid());
			pstmt.setInt(4, quelineVO.getQueuetableid());

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
	public void update(QueLineVO quelineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, quelineVO.getQueuenocall());
			pstmt.setInt(2, quelineVO.getQueuelineno());
			pstmt.setString(3, quelineVO.getStoreid());
			pstmt.setInt(4, quelineVO.getQueuetableid());

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
	public void delete(Integer quelineno, String storeid, Integer quetableid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, quelineno);
			pstmt.setString(2, storeid);
			pstmt.setInt(3, quetableid);

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

	// 主鍵??��??(quelineno+storeid+quetableid)
	@Override
	public QueLineVO findByPrimaryKey(Integer quelineno, String storeid, Integer quetableid) {
		QueLineVO quelineVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, quelineno);
			pstmt.setString(2, storeid);
			pstmt.setInt(3, quetableid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				quelineVO = new QueLineVO();
				quelineVO.setQueuetableid(rs.getInt("queue_table_id"));
				quelineVO.setStoreid(rs.getString("store_id"));
				quelineVO.setQueuelineno(rs.getInt("queue_line_no"));
				quelineVO.setQueuenocall(rs.getInt("queue_no_call"));
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
		return quelineVO;
	}

	// 店�?��?��??
	@Override
	public List<QueLineVO> findByStoreid(String storeid) {
		List<QueLineVO> list = new ArrayList<QueLineVO>();
		QueLineVO quelineVO = new QueLineVO();

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
				quelineVO = new QueLineVO();
				quelineVO.setQueuelineno(rs.getInt("queue_line_no"));
				quelineVO.setQueuenocall(rs.getInt("queue_no_call"));
				quelineVO.setStoreid(rs.getString("store_id"));
				quelineVO.setQueuetableid(rs.getInt("queue_table_id"));
				list.add(quelineVO);
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

	// ?��?���?
	@Override
	public List<QueLineVO> getAll() {

		List<QueLineVO> list = new ArrayList<QueLineVO>();
		QueLineVO quelineVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				quelineVO = new QueLineVO();
				quelineVO.setQueuelineno(rs.getInt("queue_line_no"));
				quelineVO.setQueuenocall(rs.getInt("queue_no_call"));
				quelineVO.setStoreid(rs.getString("store_id"));
				quelineVO.setQueuetableid(rs.getInt("queue_table_id"));
				list.add(quelineVO);
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

	public static void main(String[] args) {
		QueLineJDBCDAO dao = new QueLineJDBCDAO();

		// ?���?
//		QuelineVO quetVO1 = new QuelineVO();
//		quetVO1.setQueuelineno(17);
//		quetVO1.setStoreid("S001");
//		quetVO1.setQueuenocall(17);
//		quetVO1.setQueuetableid(1);
//		dao.insert(quetVO1);

		// 修改
//		QuelineVO quelVO2 = new QuelineVO();
//		quelVO2.setQueuelineno(1);
//		quelVO2.setQueuenocall(20);
//		quelVO2.setStoreid("S001");
//		quelVO2.setQueuetableid(1);
//		dao.update(quelVO2);

		// ?��?��，�?��?�聯?��法刪
//		dao.delete(3, "S000001", 3);

		// 依主?��?���?
//		QuelineVO quelineVO3 = dao.findByPrimaryKey(1, "S001",1);
//		System.out.print(quelineVO3.getQueuelineno() + ",");
//		System.out.print(quelineVO3.getQueuenocall() + ",");
//		System.out.print(quelineVO3.getStoreid() + ",");
//		System.out.println(quelineVO3.getQueuetableid());
//		System.out.println("-----------------------");

		// ?��?���?
//		List<QueLineVO> list = dao.getAll();
//		for (QueLineVO aQue : list) {
//			System.out.print(aQue.getQueuelineno() + ",");
//			System.out.print(aQue.getQueuenocall() + ",");
//			System.out.print(aQue.getStoreid() + ",");
//			System.out.println(aQue.getQueuetableid());
//			System.out.println();
//		}
		// 依�?��?�查�?
		List<QueLineVO> list = dao.findByStoreid("S000001");
		for (QueLineVO sQue : list) {
			System.out.print(sQue.getQueuelineno() + ",");
			System.out.print(sQue.getQueuenocall() + ",");
			System.out.print(sQue.getStoreid() + ",");
			System.out.println(sQue.getQueuetableid());
		}
	}

}
