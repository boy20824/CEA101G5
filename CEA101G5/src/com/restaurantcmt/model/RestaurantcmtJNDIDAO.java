package com.restaurantcmt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RestaurantcmtJNDIDAO implements RestaurantCmt_interface  {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RESTAURANT_CMT (STORE_CMT_ID,STORE_ID,MEM_PHONE,STORE_CMT_CONTENT,STORE_RATING,STORE_CMT_STATUS) VALUES (('SCMT'||LPAD(SEQ_STORE_CMT_ID.NEXTVAL,6,'0')), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT STORE_CMT_ID,STORE_ID,MEM_PHONE,STORE_CMT_CONTENT,STORE_RATING,STORE_CMT_STATUS from RESTAURANT_CMT where STORE_ID = ? order by STORE_CMT_ID desc";
	private static final String GET_ONE_STMT = "SELECT STORE_CMT_ID,STORE_ID,MEM_PHONE,STORE_CMT_CONTENT,STORE_RATING,STORE_CMT_STATUS from RESTAURANT_CMT where STORE_ID = ? and MEM_PHONE = ?";
	private static final String DELETE = "DELETE FROM RESTAURANT_CMT where STORE_CMT_ID = ?";
	private static final String UPDATE = "UPDATE RESTAURANT_CMT set STORE_CMT_CONTENT=?, STORE_RATING=? ,STORE_CMT_STATUS=? where STORE_CMT_ID = ?";

				@Override
				public void insert(RestaurantCmtVO restaurantCmtVO) {

					Connection con = null;
					PreparedStatement pstmt = null;
					try {
					
						con = ds.getConnection();
						pstmt = con.prepareStatement(INSERT_STMT);
						
						System.out.println(restaurantCmtVO.getStoreId());
						System.out.println(restaurantCmtVO.getMemPhone());
						System.out.println(restaurantCmtVO.getStoreCmtContent());
						System.out.println(restaurantCmtVO.getStoreRating());
						System.out.println(restaurantCmtVO.getStoreCmtStatus());
						
						
						pstmt.setString(1, restaurantCmtVO.getStoreId());
						pstmt.setString(2, restaurantCmtVO.getMemPhone());
						pstmt.setString(3, restaurantCmtVO.getStoreCmtContent());
						pstmt.setInt(4, restaurantCmtVO.getStoreRating());
						pstmt.setInt(5, restaurantCmtVO.getStoreCmtStatus());

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

	@Override
	public void update(RestaurantCmtVO restaurantCmtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurantCmtVO.getStoreCmtContent());
			pstmt.setInt(2, restaurantCmtVO.getStoreRating());
			pstmt.setInt(3, restaurantCmtVO.getStoreCmtStatus());
			pstmt.setString(4, restaurantCmtVO.getStoreCmtId());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public void delete(String storeCmtId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, storeCmtId);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public RestaurantCmtVO findByPrimaryKey(String storeId ,String memPhone) {

		RestaurantCmtVO restaurantCmtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeId);
			pstmt.setString(2, memPhone);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantCmtVO = new RestaurantCmtVO();
				restaurantCmtVO.setStoreCmtId(rs.getString("STORE_CMT_ID"));
				restaurantCmtVO.setStoreId(rs.getString("STORE_ID"));
				restaurantCmtVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantCmtVO.setStoreCmtContent(rs.getString("STORE_CMT_CONTENT"));
				restaurantCmtVO.setStoreRating(rs.getInt("STORE_RATING"));
				restaurantCmtVO.setStoreCmtStatus(rs.getInt("STORE_CMT_STATUS"));

			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return restaurantCmtVO;
	}

	@Override
	public List<RestaurantCmtVO> getAll(String storeId) {
		List<RestaurantCmtVO> list = new ArrayList<RestaurantCmtVO>();
		RestaurantCmtVO restaurantCmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			pstmt.setString(1, storeId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurantCmtVO = new RestaurantCmtVO();
				restaurantCmtVO.setStoreCmtId(rs.getString("STORE_CMT_ID"));
				restaurantCmtVO.setStoreId(rs.getString("STORE_ID"));
				restaurantCmtVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantCmtVO.setStoreCmtContent(rs.getString("STORE_CMT_CONTENT"));
				restaurantCmtVO.setStoreRating(rs.getInt("STORE_RATING"));
				restaurantCmtVO.setStoreCmtStatus(rs.getInt("STORE_CMT_STATUS"));
				list.add(restaurantCmtVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
