package com.queueline.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QueLineDAO implements QueLineDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, quelineVO.getQueuelineno());
			pstmt.setInt(2, quelineVO.getQueuenocall());
			pstmt.setString(3, quelineVO.getStoreid());
			pstmt.setInt(4, quelineVO.getQueuetableid());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, quelineVO.getQueuenocall());
			pstmt.setInt(2, quelineVO.getQueuelineno());
			pstmt.setString(3, quelineVO.getStoreid());
			pstmt.setInt(4, quelineVO.getQueuetableid());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, quelineno);
			pstmt.setString(2, storeid);
			pstmt.setInt(3, quetableid);

			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

			con = ds.getConnection();
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