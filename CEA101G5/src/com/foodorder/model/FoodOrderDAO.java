package com.foodorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodorderdetail.model.FoodOrderDetailDAO;
import com.foodorderdetail.model.FoodOrderDetailVO;

public class FoodOrderDAO implements FoodOrder_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FOODORDER (FOODORDER_ID,MEM_PHONE,STORE_ID,FOODORDER_TOTALPRICE,FOODORDER_NOTE) VALUES (('OD'||LPAD(SEQ_FOODORDER_ID.NEXTVAL,6,'0')), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT FOODORDER_ID,MEM_PHONE,STORE_ID,to_char(FOODORDER_TIME,'yyyy-mm-dd hh24:mm:ss')FOODORDER_TIME,to_char(FOODORDER_COMPLETE_TIME,'yyyy-mm-dd hh:mm:ss')FOODORDER_COMPLETE_TIME,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_STATUS FROM FoodOrder order by FOODORDER_ID";
	private static final String GET_ONE_STMT = "SELECT FOODORDER_ID,MEM_PHONE,STORE_ID,to_char(FOODORDER_TIME,'yyyy-mm-dd hh24:mm:ss')FOODORDER_TIME,to_char(FOODORDER_COMPLETE_TIME,'yyyy-mm-dd hh:mm:ss')FOODORDER_COMPLETE_TIME,FOODORDER_TOTALPRICE,FOODORDER_NOTE,FOODORDER_STATUS FROM FOODORDER where FOODORDER_ID = ?";
	private static final String DELETE = "DELETE FROM FOODORDER where FOODORDER_ID = ?";
	private static final String UPDATE = "UPDATE FOODORDER set FOODORDER_COMPLETE_TIME=?, FOODORDER_STATUS=? where FOODORDER_ID = ?";

	@Override
	public ResultSet insert(FoodOrderVO foodOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] cols = { "FOODORDER_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, foodOrderVO.getMemPhone());
			pstmt.setString(2, foodOrderVO.getStoreId());
			pstmt.setInt(3, foodOrderVO.getFoodOrderTotalPrice());
			pstmt.setString(4, foodOrderVO.getFoodOrderNote());

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

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
		return rs;
	}
	
	
	@Override
	 public void insertWithFoodOrderDetail(FoodOrderVO foodOrderVO , List<FoodOrderDetailVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
				System.out.println("自增主鍵值= " + next_foodorder_id +"(剛新增成功訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			FoodOrderDetailDAO dao = new FoodOrderDetailDAO();
			System.out.println("list.size()-A="+list.size());
			
			for (FoodOrderDetailVO foodOrderDetailVO : list) {
				foodOrderDetailVO.setFoodOrderId(next_foodorder_id) ;
				dao.insert2(foodOrderDetailVO,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_foodorder_id + "時,共有訂單明細" + list.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, foodOrderVO.getFoodOrderCompleteTime());
			pstmt.setInt(2, foodOrderVO.getFoodOrderStatus());
			pstmt.setString(3, foodOrderVO.getFoodOrderId());

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

	@Override
	public void delete(String foodOrderId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, foodOrderId);

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

	@Override
	public FoodOrderVO findByPrimaryKey(String foodorderId) {

		FoodOrderVO foodOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
	public List<FoodOrderVO> getAllByStoreId(String storeId) {
		return new ArrayList<FoodOrderVO>();
	}
	@Override
	public List<FoodOrderVO> getAllByMemberPhoneStatus2(String memPhone){
		return new ArrayList<FoodOrderVO>();
	}

	public static void main(String[] args) {

		FoodOrderDAO dao = new FoodOrderDAO();
		
//		測試自增主鍵值綁定
		
		FoodOrderVO foodOrderVO = new FoodOrderVO();
		foodOrderVO.setMemPhone("0921842851");
		 foodOrderVO.setStoreId("S000001");
		 foodOrderVO.setFoodOrderTotalPrice(10);
		 foodOrderVO.setFoodOrderNote("10");
		 
		 List<FoodOrderDetailVO> testList = new ArrayList<FoodOrderDetailVO>();
		 
		 FoodOrderDetailVO FoodOrderDetailA = new FoodOrderDetailVO();
		 FoodOrderDetailA.setMenuId("M000024");
		 FoodOrderDetailA.setMenuNum(2);
		 FoodOrderDetailA.setMenuPrice(100);
		 
		 FoodOrderDetailVO FoodOrderDetailB = new FoodOrderDetailVO();
		 FoodOrderDetailB.setMenuId("M000023");
		 FoodOrderDetailB.setMenuNum(5);
		 FoodOrderDetailB.setMenuPrice(200);
		 
		 testList.add(FoodOrderDetailA);
		 testList.add(FoodOrderDetailB);
		 
		 dao.insertWithFoodOrderDetail(foodOrderVO,testList);
		

//		// 新增
//		 FoodOrderVO foodOrderVO = new FoodOrderVO();
//		 foodOrderVO.setMemPhone("0921842851");
//		 foodOrderVO.setStoreId("S000001");
////	 foodOrderVO.setFoodOrderTime(); //有預設default
////	 foodOrderVO.setFoodOrderCompleteTime();//第一次新增時不需要完成時間
//		 foodOrderVO.setFoodOrderTotalPrice(10);
//		 foodOrderVO.setFoodOrderNote("10");
////		 foodOrderVO.setFoodOrderStatus(1);//新增時為開始為製作中
//		 dao.insert(foodOrderVO);

		// 修改
//		FoodOrderVO foodOrderVO2 = new FoodOrderVO();
//		foodOrderVO2.setFoodOrderCompleteTime(java.sql.Timestamp.valueOf("2020-12-10 20:08:24") );
//		foodOrderVO2.setFoodOrderStatus(1);
//		foodOrderVO2.setFoodOrderId("OD000001");
//		dao.update(foodOrderVO2);

//
//		// 刪除
//		dao.delete("OD000011");

//
//		// 查詢單筆
//		FoodOrderVO foodOrderVO3 = new FoodOrderVO();
//		foodOrderVO3 = dao.findByPrimaryKey("OD000001");
//		
//		System.out.print(foodOrderVO3.getFoodOrderId() + ",");
//		System.out.print(foodOrderVO3.getMemPhone() + ",");
//		System.out.print(foodOrderVO3.getStoreId() + ",");
//		System.out.print(foodOrderVO3.getFoodOrderTime() + ",");
//		System.out.print(foodOrderVO3.getFoodOrderCompleteTime() + ",");
//		System.out.print(foodOrderVO3.getFoodOrderTotalPrice() + ",");
//		System.out.print(foodOrderVO3.getFoodOrderNote()+",");
//		System.out.println(foodOrderVO3.getFoodOrderStatus());
//		System.out.println("---------------------");

//		// 查詢全部
//		List<FoodOrderVO> list = dao.getAll();
//		for (FoodOrderVO foodOrderVO : list) {
//			System.out.print(foodOrderVO.getFoodOrderId() + ",");
//			System.out.print(foodOrderVO.getMemPhone() + ",");
//			System.out.print(foodOrderVO.getStoreId() + ",");
//			
//			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String formatDate = df.format(foodOrderVO.getFoodOrderTime());
//			System.out.print(formatDate + ",");
//		
//			System.out.print(foodOrderVO.getFoodOrderCompleteTime() + ",");
//			System.out.print(foodOrderVO.getFoodOrderTotalPrice() + ",");
//			System.out.print(foodOrderVO.getFoodOrderNote() + ",");
//			System.out.println(foodOrderVO.getFoodOrderStatus());
//			System.out.println();
//		}
	}


	@Override
	public void updateOneByFoodOrderId(String foodOrderId) {
		// TODO Auto-generated method stub
		
	}
}
