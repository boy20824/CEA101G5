package com.member.model;

import java.sql.*;
import java.util.*;

public class MemJDBCDAO implements MemDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEM_PHONE, MEM_PWD, MEM_NAME, MEM_ADDRESS, MEM_SEX, MEM_EMAIL, MEM_IDENTITY, MEM_BIRTHDAY, MEM_NICKNAME,MEM_LICENSE,MEM_CARDNUMBER,MEM_CARDHOLDER,MEM_CARDEXPIRATIONDATE,MEM_CARDCCV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEM_PHONE, MEM_PWD, MEM_NAME, MEM_ADDRESS, MEM_SEX, MEM_EMAIL, MEM_IDENTITY, to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY, MEM_NICKNAME, MEM_LICENSE, MEM_CONDITION, MEM_AUTHORITY, RECHARGE_TOTAL,MEM_PHOTO,MEM_CARDNUMBER,MEM_CARDHOLDER,MEM_CARDEXPIRATIONDATE,MEM_CARDCCV FROM MEMBER";
	private static final String GET_ONE_STMT = "SELECT MEM_PHONE, MEM_PWD, MEM_NAME, MEM_ADDRESS, MEM_SEX, MEM_EMAIL, MEM_IDENTITY, to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY, MEM_NICKNAME, MEM_LICENSE, MEM_CONDITION, MEM_AUTHORITY, RECHARGE_TOTAL,MEM_PHOTO,MEM_CARDNUMBER,MEM_CARDHOLDER,MEM_CARDEXPIRATIONDATE,MEM_CARDCCV FROM MEMBER WHERE MEM_PHONE = ?";

	private static final String DELETE_MEMs = "DELETE FROM MEMBER WHERE MEM_PHONE = ?";

	private static final String UPDATE = "UPDATE MEMBER SET MEM_PWD=?, MEM_NAME=?, MEM_ADDRESS=?,MEM_SEX=?, MEM_EMAIL=?,MEM_IDENTITY=?, MEM_BIRTHDAY=?,MEM_NICKNAME=?, MEM_LICENSE=?, MEM_CONDITION=?, MEM_AUTHORITY=?,RECHARGE_TOTAL=? ,MEM_PHOTO=?,MEM_CARDNUMBER=?,MEM_CARDHOLDER=?,MEM_CARDEXPIRATIONDATE=?,MEM_CARDCCV=? WHERE MEM_PHONE = ?";
	private static final String UPDATE_TOTALRECHARGE = "UPDATE MEMBER SET RECHARGE_TOTAL=? WHERE MEM_PHONE = ?";

	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMemPhone());
			pstmt.setString(2, memVO.getMemPwd());
			pstmt.setString(3, memVO.getMemName());
			pstmt.setString(4, memVO.getMemAddress());
			pstmt.setInt(5, memVO.getMemSex());
			pstmt.setString(6, memVO.getMemEmail());
			pstmt.setString(7, memVO.getMemIdentity());
			pstmt.setDate(8, memVO.getMemBirth());
			pstmt.setString(9, memVO.getMemNick());
			pstmt.setInt(10, memVO.getMemLice());
//			pstmt.setBytes(11, memVO.getMemPhoto());
			pstmt.setString(11, memVO.getMemCardNumber());
			pstmt.setString(12, memVO.getMemCardHolder());
			pstmt.setString(13, memVO.getMemCardExpirationDate());
			pstmt.setString(14, memVO.getMemCardCCV());

			pstmt.executeUpdate();
			con.commit();
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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMemPwd());
			pstmt.setString(2, memVO.getMemName());
			pstmt.setString(3, memVO.getMemAddress());
			pstmt.setInt(4,memVO.getMemSex());
			pstmt.setString(5, memVO.getMemEmail());
			pstmt.setString(6, memVO.getMemIdentity());
			pstmt.setDate(7, memVO.getMemBirth());
			pstmt.setString(8, memVO.getMemNick());
			pstmt.setInt(9, memVO.getMemLice());
			pstmt.setInt(10, memVO.getMemCondition());
			pstmt.setString(11, memVO.getMemAuth());
			pstmt.setInt(12, memVO.getMemTotalRechar());
//			pstmt.setBytes(13, memVO.getMemPhoto());
			pstmt.setString(13, memVO.getMemPhone());
			pstmt.setString(14, memVO.getMemCardNumber());
			pstmt.setString(15, memVO.getMemCardHolder());
			pstmt.setString(16, memVO.getMemCardExpirationDate());
			pstmt.setString(17, memVO.getMemCardCCV());
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
	public void updateMemRecharBymemPhone(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_TOTALRECHARGE);

			pstmt.setInt(1, memVO.getMemTotalRechar());
			pstmt.setString(2, memVO.getMemPhone());

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
	public void delete(String memPhone) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 刪除會員
			pstmt = con.prepareStatement(DELETE_MEMs);
			pstmt.setString(1, memPhone);
			updateCount_EMPs = pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除會員手機號碼" + memPhone + "時,共有會員" + updateCount_EMPs + "位同時被刪除");

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
	public MemVO findByPrimaryKey(String memPhone) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memPhone);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memVO.setMemSex(rs.getInt("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBirth(rs.getDate("MEM_BIRTHDAY"));
				memVO.setMemNick(rs.getString("MEM_NICKNAME"));
				memVO.setMemLice(rs.getInt("MEM_LICENSE"));
				memVO.setMemCondition(rs.getInt("MEM_CONDITION"));
				memVO.setMemAuth(rs.getString("MEM_AUTHORITY"));
				memVO.setMemTotalRechar(rs.getInt("RECHARGE_TOTAL"));
//				memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				memVO.setMemCardNumber(rs.getString("MEM_CARDNUMBER"));
				memVO.setMemCardHolder(rs.getString("MEM_CARDHOLDER"));
				memVO.setMemCardExpirationDate(rs.getString("MEM_CARDEXPIRATIONDATE"));
				memVO.setMemCardCCV(rs.getString("MEM_CARDCCV"));
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemPhone(rs.getString("MEM_PHONE"));
				memVO.setMemPwd(rs.getString("MEM_PWD"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemAddress(rs.getString("MEM_ADDRESS"));
				memVO.setMemSex(rs.getInt("MEM_SEX"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemIdentity(rs.getString("MEM_IDENTITY"));
				memVO.setMemBirth(rs.getDate("MEM_BIRTHDAY"));
				memVO.setMemNick(rs.getString("MEM_NICKNAME"));
				memVO.setMemLice(rs.getInt("MEM_LICENSE"));
				memVO.setMemCondition(rs.getInt("MEM_CONDITION"));
				memVO.setMemAuth(rs.getString("MEM_AUTHORITY"));
				memVO.setMemTotalRechar(rs.getInt("RECHARGE_TOTAL"));
//				memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				memVO.setMemCardNumber(rs.getString("MEM_CARDNUMBER"));
				memVO.setMemCardHolder(rs.getString("MEM_CARDHOLDER"));
				memVO.setMemCardExpirationDate(rs.getString("MEM_CARDEXPIRATIONDATE"));
				memVO.setMemCardCCV(rs.getString("MEM_CARDCCV"));
				list.add(memVO); // Store the row in the list
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

		MemJDBCDAO dao = new MemJDBCDAO();

		// 新增
		MemVO memVO1 = new MemVO();
		memVO1.setMemPhone("0921842859");
		memVO1.setMemPwd("abc123456");
		memVO1.setMemName("小橙子");
		memVO1.setMemAddress("桃園市中壢區復興路");
		memVO1.setMemSex(new Integer(0));
		memVO1.setMemEmail("oakleylee518@gmail.com");
		memVO1.setMemIdentity("G123456789");
		memVO1.setMemBirth(java.sql.Date.valueOf("1995-05-18"));
		memVO1.setMemNick("橙子");
		memVO1.setMemLice(new Integer(22099131));
		memVO1.setMemCardNumber("2124124214");
		memVO1.setMemCardHolder("1231313");
		memVO1.setMemCardExpirationDate("1232132131");
		memVO1.setMemCardCCV("12441421");
		dao.insert(memVO1);

		
	}

	@Override
	public void updateMemLiceBymemPhone(MemVO memVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMemConditionBymemPhone(MemVO memVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void easyInsert(MemVO memVO) {
		// TODO Auto-generated method stub
		
	}
}
