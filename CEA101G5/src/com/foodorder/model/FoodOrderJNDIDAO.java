package com.foodorder.model;

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

import com.foodorderdetail.model.FoodOrderDetailDAO;
import com.foodorderdetail.model.FoodOrderDetailVO;

public class FoodOrderJNDIDAO implements FoodOrder_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		
		static {
			try {
				Context ctx = new InitialContext();  //初始化註冊 javax.naming.InitialContext;
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5"); //javax.naming.InitialContext 尋找連線池
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		private static final String INSERT_STMT = "INSERT INTO FOODORDER (FOODORDER_ID,MEM_PHONE,STORE_ID,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_CMT_STATUS) VALUES (('OD'||LPAD(SEQ_FOODORDER_ID.NEXTVAL,6,'0')), ?, ?, ?, ?,0)";
		private static final String GET_ALL_STMT = "SELECT FOODORDER_ID,MEM_PHONE,STORE_ID,to_char(FOODORDER_TIME,'yyyy-mm-dd hh24:mi:ss')FOODORDER_TIME,to_char(FOODORDER_COMPLETE_TIME,'yyyy-mm-dd hh:mi:ss')FOODORDER_COMPLETE_TIME,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_STATUS FROM FoodOrder where MEM_PHONE = ? and FOODORDER_STATUS= 0 order by FOODORDER_ID DESC";
		private static final String GET_ALL_BY_STATUS_2 = "SELECT FOODORDER_ID,MEM_PHONE,STORE_ID,to_char(FOODORDER_TIME,'yyyy-mm-dd hh24:mi:ss')FOODORDER_TIME,to_char(FOODORDER_COMPLETE_TIME,'yyyy-mm-dd hh:mi:ss')FOODORDER_COMPLETE_TIME,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_STATUS,FOODORDER_CMT_STATUS FROM FoodOrder where MEM_PHONE = ? order by FOODORDER_ID DESC";
		private static final String GET_ONE_STMT = "SELECT FOODORDER_ID,MEM_PHONE,STORE_ID,to_char(FOODORDER_TIME,'yyyy-mm-dd hh24:mi:ss')FOODORDER_TIME,to_char(FOODORDER_COMPLETE_TIME,'yyyy-mm-dd hh:mi:ss')FOODORDER_COMPLETE_TIME,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_STATUS FROM FOODORDER where FOODORDER_ID = ?";
		private static final String DELETE = "DELETE FROM FOODORDER where FOODORDER_ID = ?";
		private static final String UPDATE = "UPDATE FOODORDER set FOODORDER_COMPLETE_TIME=?, FOODORDER_STATUS=? where FOODORDER_ID = ?";
		private static final String GET_ALL_By_STORE_ID = "SELECT FOODORDER_ID,MEM_PHONE,STORE_ID,to_char(FOODORDER_TIME,'yyyy-mm-dd hh24:mi:ss')FOODORDER_TIME,to_char(FOODORDER_COMPLETE_TIME,'yyyy-mm-dd hh:mi:ss')FOODORDER_COMPLETE_TIME,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_STATUS FROM FoodOrder where STORE_ID = ? order by FOODORDER_ID DESC";
		private static final String UPDATE_BY_FOODORDER_ID = "UPDATE FOODORDER set FOODORDER_CMT_STATUS=1 where FOODORDER_ID = ?";
		
		@Override
		public ResultSet insert(FoodOrderVO foodOrderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs;
			try {

				con = ds.getConnection();
				String[] cols = { "FOODORDER_ID" };
				pstmt = con.prepareStatement(INSERT_STMT, cols);

				pstmt.setString(1, foodOrderVO.getMemPhone());
				pstmt.setString(2, foodOrderVO.getStoreId());
				pstmt.setInt(3, foodOrderVO.getFoodOrderTotalPrice());
				pstmt.setString(4, foodOrderVO.getFoodOrderNote());

				pstmt.executeUpdate();

				rs = pstmt.getGeneratedKeys();

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
			return rs;
		}
		
		@Override
		 public void insertWithFoodOrderDetail(FoodOrderVO foodOrderVO , List<FoodOrderDetailVO> list) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				
				// 1●設定於 pstm.executeUpdate()之前
	    		con.setAutoCommit(false); //關閉自動交易功能
				
	    		// 先新增訂單
				String cols[] = {"FOODORDER_ID"};
				pstmt = con.prepareStatement(INSERT_STMT , cols);			
				pstmt.setString(1, foodOrderVO.getMemPhone());
				pstmt.setString(2, foodOrderVO.getStoreId());
				pstmt.setInt(3, foodOrderVO.getFoodOrderTotalPrice());
				pstmt.setString(4, foodOrderVO.getFoodOrderNote());
				pstmt.executeUpdate();
				
				//擷取對應的自增主鍵值
				String next_foodorder_id = null;
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					next_foodorder_id = rs.getString(1);
//					System.out.println("自增主鍵值= " + next_foodorder_id +"(剛新增成功訂單編號)");
				} else {
//					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				// 再同時新增訂單明細
				FoodOrderDetailDAO dao = new FoodOrderDetailDAO();
//				System.out.println("list.size()-A="+list.size());
				
				for (FoodOrderDetailVO foodOrderDetailVO : list) {
					foodOrderDetailVO.setFoodOrderId(next_foodorder_id) ;
					dao.insert2(foodOrderDetailVO,con);
				}

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
//				System.out.println("list.size()-B="+list.size());
//				System.out.println("新增訂單編號" + next_foodorder_id + "時,共有訂單明細" + list.size()
//						+ "筆同時被新增");
				
			}catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-dept");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
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
		public void update(FoodOrderVO foodOrderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setTimestamp(1, foodOrderVO.getFoodOrderCompleteTime());
				pstmt.setInt(2, foodOrderVO.getFoodOrderStatus());
				pstmt.setString(3, foodOrderVO.getFoodOrderId());

				pstmt.executeUpdate();

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
		public void updateOneByFoodOrderId(String foodOrderId) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_BY_FOODORDER_ID);

				pstmt.setString(1, foodOrderId);

				pstmt.executeUpdate();

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
		public void delete(String foodOrderId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, foodOrderId);

				pstmt.executeUpdate();

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
		public FoodOrderVO findByPrimaryKey(String foodorderId) {

			FoodOrderVO foodOrderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, foodorderId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					foodOrderVO = new FoodOrderVO();
					foodOrderVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
					foodOrderVO.setMemPhone(rs.getString("MEM_PHONE"));
					foodOrderVO.setStoreId(rs.getString("STORE_ID"));
					foodOrderVO.setFoodOrderTime(rs.getTimestamp("FOODORDER_TIME"));
					foodOrderVO.setFoodOrderCompleteTime(rs.getTimestamp("FOODORDER_COMPLETE_TIME"));
					foodOrderVO.setFoodOrderTotalPrice(rs.getInt("FOODORDER_TOTALPRICE"));
					foodOrderVO.setFoodOrderNote(rs.getString("FOODORDER_NOTE"));
					foodOrderVO.setFoodOrderStatus(rs.getInt("FOODORDER_STATUS"));
				}

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
			return foodOrderVO;
		}

		@Override
		public List<FoodOrderVO> getAll(String memPhone) {
			List<FoodOrderVO> list = new ArrayList<FoodOrderVO>();
			FoodOrderVO foodOrderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				pstmt.setString(1,memPhone);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// // foodOrderVO 也稱為 Domain objects
					foodOrderVO = new FoodOrderVO();
					foodOrderVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
					foodOrderVO.setMemPhone(rs.getString("MEM_PHONE"));
					foodOrderVO.setStoreId(rs.getString("STORE_ID"));
					foodOrderVO.setFoodOrderTime(rs.getTimestamp("FOODORDER_TIME"));
					foodOrderVO.setFoodOrderCompleteTime(rs.getTimestamp("FOODORDER_COMPLETE_TIME"));
					foodOrderVO.setFoodOrderTotalPrice(rs.getInt("FOODORDER_TOTALPRICE"));
					foodOrderVO.setFoodOrderNote(rs.getString("FOODORDER_NOTE"));
					foodOrderVO.setFoodOrderStatus(rs.getInt("FOODORDER_STATUS"));
					list.add(foodOrderVO); // Store the row in the list
				}

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
		
		
		@Override
		public List<FoodOrderVO> getAllByMemberPhoneStatus2(String memPhone){
			List<FoodOrderVO> list = new ArrayList<FoodOrderVO>();
			FoodOrderVO foodOrderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_BY_STATUS_2);
				pstmt.setString(1,memPhone);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// // foodOrderVO 也稱為 Domain objects
					foodOrderVO = new FoodOrderVO();
					foodOrderVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
					foodOrderVO.setMemPhone(rs.getString("MEM_PHONE"));
					foodOrderVO.setStoreId(rs.getString("STORE_ID"));
					foodOrderVO.setFoodOrderTime(rs.getTimestamp("FOODORDER_TIME"));
					foodOrderVO.setFoodOrderCompleteTime(rs.getTimestamp("FOODORDER_COMPLETE_TIME"));
					foodOrderVO.setFoodOrderTotalPrice(rs.getInt("FOODORDER_TOTALPRICE"));
					foodOrderVO.setFoodOrderNote(rs.getString("FOODORDER_NOTE"));
					foodOrderVO.setFoodOrderStatus(rs.getInt("FOODORDER_STATUS"));
					foodOrderVO.setFoodOrderCmtStatus(rs.getInt("FOODORDER_CMT_STATUS"));
					list.add(foodOrderVO); // Store the row in the list
				}

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
		
		@Override
		public List<FoodOrderVO> getAllByStoreId(String storeId) {
			List<FoodOrderVO> list = new ArrayList<FoodOrderVO>();
			FoodOrderVO foodOrderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_By_STORE_ID);
				pstmt.setString(1,storeId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// // foodOrderVO 也稱為 Domain objects
					foodOrderVO = new FoodOrderVO();
					foodOrderVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
					foodOrderVO.setMemPhone(rs.getString("MEM_PHONE"));
					foodOrderVO.setStoreId(rs.getString("STORE_ID"));
					foodOrderVO.setFoodOrderTime(rs.getTimestamp("FOODORDER_TIME"));
					foodOrderVO.setFoodOrderCompleteTime(rs.getTimestamp("FOODORDER_COMPLETE_TIME"));
					foodOrderVO.setFoodOrderTotalPrice(rs.getInt("FOODORDER_TOTALPRICE"));
					foodOrderVO.setFoodOrderNote(rs.getString("FOODORDER_NOTE"));
					foodOrderVO.setFoodOrderStatus(rs.getInt("FOODORDER_STATUS"));
					list.add(foodOrderVO); // Store the row in the list
				}

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
