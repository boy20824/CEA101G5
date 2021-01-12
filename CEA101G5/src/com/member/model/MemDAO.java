package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemDAO implements MemDAO_interface {
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

		private static final String INSERT_STMT = "INSERT INTO MEMBER (MEM_PHONE, MEM_PWD, MEM_NAME, MEM_ADDRESS, MEM_SEX, MEM_EMAIL, MEM_IDENTITY, MEM_BIRTHDAY, MEM_NICKNAME,MEM_LICENSE,MEM_PHOTO,MEM_CARDNUMBER,MEM_CARDHOLDER,MEM_CARDEXPIRATIONDATE,MEM_CARDCCV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String EASY_INSERT_STMT = "INSERT INTO MEMBER (MEM_PHONE, MEM_PWD, MEM_NAME) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = "SELECT MEM_PHONE, MEM_PWD, MEM_NAME, MEM_ADDRESS, MEM_SEX, MEM_EMAIL, MEM_IDENTITY, to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY, MEM_NICKNAME, MEM_LICENSE, MEM_CONDITION, MEM_AUTHORITY, RECHARGE_TOTAL,MEM_PHOTO,MEM_CARDNUMBER,MEM_CARDHOLDER,MEM_CARDEXPIRATIONDATE,MEM_CARDCCV FROM MEMBER";
		private static final String GET_ONE_STMT = "SELECT MEM_PHONE, MEM_PWD, MEM_NAME, MEM_ADDRESS, MEM_SEX, MEM_EMAIL, MEM_IDENTITY, to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY, MEM_NICKNAME, MEM_LICENSE, MEM_CONDITION, MEM_AUTHORITY, RECHARGE_TOTAL,MEM_PHOTO,MEM_CARDNUMBER,MEM_CARDHOLDER,MEM_CARDEXPIRATIONDATE,MEM_CARDCCV FROM MEMBER WHERE MEM_PHONE = ?";

		private static final String DELETE_MEMs = "DELETE FROM MEMBER WHERE MEM_PHONE = ?";

		private static final String UPDATE = "UPDATE MEMBER SET MEM_PWD=?, MEM_NAME=?, MEM_ADDRESS=?,MEM_SEX=?, MEM_EMAIL=?,MEM_IDENTITY=?, MEM_BIRTHDAY=?,MEM_NICKNAME=?, MEM_LICENSE=?, MEM_CONDITION=?, MEM_AUTHORITY=?,RECHARGE_TOTAL=? ,MEM_PHOTO=?,MEM_CARDNUMBER=?,MEM_CARDHOLDER=?,MEM_CARDEXPIRATIONDATE=?,MEM_CARDCCV=? WHERE MEM_PHONE = ?";
		private static final String UPDATE_TOTALRECHARGE = "UPDATE MEMBER SET RECHARGE_TOTAL=? WHERE MEM_PHONE = ?";
		private static final String UPDATE_LICENSE = "UPDATE MEMBER SET MEM_LICENSE=?, MEM_CONDITION=? WHERE MEM_PHONE = ?";
		private static final String UPDATE_CONDITION = "UPDATE MEMBER SET MEM_CONDITION=? WHERE MEM_PHONE = ?";

		
		public void easyInsert(MemVO memVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(EASY_INSERT_STMT);
				
				pstmt.setString(1, memVO.getMemPhone());
				pstmt.setString(2, memVO.getMemPwd());
				pstmt.setString(3, memVO.getMemName());
			
				
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
		public void insert(MemVO memVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
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
				pstmt.setBytes(11, memVO.getMemPhoto());
				pstmt.setString(12, memVO.getMemCardNumber());
				pstmt.setString(13, memVO.getMemCardHolder());
				pstmt.setString(14, memVO.getMemCardExpirationDate());
				pstmt.setString(15, memVO.getMemCardCCV());

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
		public void update(MemVO memVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
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
				pstmt.setBytes(13, memVO.getMemPhoto());
				pstmt.setString(14, memVO.getMemCardNumber());
				pstmt.setString(15, memVO.getMemCardHolder());
				pstmt.setString(16, memVO.getMemCardExpirationDate());
				pstmt.setString(17, memVO.getMemCardCCV());
				pstmt.setString(18, memVO.getMemPhone());
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
		public void updateMemRecharBymemPhone(MemVO memVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_TOTALRECHARGE);

				pstmt.setInt(1, memVO.getMemTotalRechar());
				pstmt.setString(2, memVO.getMemPhone());

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
		public void updateMemLiceBymemPhone(MemVO memVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_LICENSE);

				pstmt.setInt(1, memVO.getMemLice());
				pstmt.setInt(2, memVO.getMemCondition());
				pstmt.setString(3, memVO.getMemPhone());

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
		
		public void updateMemConditionBymemPhone(MemVO memVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_CONDITION);
				
				pstmt.setInt(1, memVO.getMemCondition());
				pstmt.setString(2, memVO.getMemPhone());
				
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
		public void delete(String memPhone) {
			int updateCount_MEMs = 0;

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();

				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);

				// 刪除會員
				pstmt = con.prepareStatement(DELETE_MEMs);
				pstmt.setString(1, memPhone);
				updateCount_MEMs = pstmt.executeUpdate();

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("刪除會員手機號碼" + memPhone + "時,共有會員" + updateCount_MEMs + "位同時被刪除");
				
				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

				con = ds.getConnection();
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
					memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
					memVO.setMemCardNumber(rs.getString("MEM_CARDNUMBER"));
					memVO.setMemCardHolder(rs.getString("MEM_CARDHOLDER"));
					memVO.setMemCardExpirationDate(rs.getString("MEM_CARDEXPIRATIONDATE"));
					memVO.setMemCardCCV(rs.getString("MEM_CARDCCV"));
					
				}
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

				con = ds.getConnection();
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
					memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
					memVO.setMemCardNumber(rs.getString("MEM_CARDNUMBER"));
					memVO.setMemCardHolder(rs.getString("MEM_CARDHOLDER"));
					memVO.setMemCardExpirationDate(rs.getString("MEM_CARDEXPIRATIONDATE"));
					memVO.setMemCardCCV(rs.getString("MEM_CARDCCV"));
					list.add(memVO); // Store the row in the list
				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
