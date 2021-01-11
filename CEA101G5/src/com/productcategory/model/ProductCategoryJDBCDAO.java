package com.productcategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.Util;

public class ProductCategoryJDBCDAO implements ProductCategoryDAO_Interface {
 
	private static final String INSERT_STMT = 
		"INSERT INTO PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_STATUS) VALUES (SEQ_PRODUCT_CATEGORY_ID.NEXTVAL, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE PRODUCT_CATEGORY SET CATEGORY_NAME = ?, CATEGORY_STATUS = ? WHERE CATEGORY_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM PRODUCT_CATEGORY WHERE CATEGORY_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM PRODUCT_CATEGORY ORDER BY CATEGORY_ID";
	
	private static final String ADD = 
		"INSERT INTO PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_STATUS) VALUES (SEQ_PRODUCT_CATEGORY_ID.NEXTVAL, ?, 1)";
	
	@Override
	public void insert(ProductCategoryVO productCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productCategoryVO.getCategoryName());
			pstmt.setInt(2, productCategoryVO.getCategoryStatus());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(ProductCategoryVO productCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, productCategoryVO.getCategoryName());
			pstmt.setInt(2, productCategoryVO.getCategoryStatus());
			pstmt.setInt(3, productCategoryVO.getCategoryId());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer categoryId) {
	}

	@Override
	public ProductCategoryVO getOne(Integer categoryId) {
		ProductCategoryVO productCategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productCategoryVO = new ProductCategoryVO();
				productCategoryVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productCategoryVO.setCategoryName(rs.getNString("CATEGORY_NAME"));
				productCategoryVO.setCategoryStatus(rs.getInt("CATEGORY_STATUS"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
				rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return productCategoryVO;
	}

	@Override
	public List<ProductCategoryVO> getAll() {
		List<ProductCategoryVO> list = new ArrayList<ProductCategoryVO>();
		ProductCategoryVO productCategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productCategoryVO = new ProductCategoryVO();
				productCategoryVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productCategoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));
				productCategoryVO.setCategoryStatus(rs.getInt("CATEGORY_STATUS"));
				list.add(productCategoryVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	@Override
	public void add(ProductCategoryVO productCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(ADD);
			
			pstmt.setString(1, productCategoryVO.getCategoryName());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ProductCategoryJDBCDAO dao = new ProductCategoryJDBCDAO();
		
//		testing : insert()
//		ProductCategoryVO exampleCategory = new ProductCategoryVO();
//		exampleCategory.setCategoryName("�������O");
//		exampleCategory.setCategoryStatus(1);
//		dao.insert(exampleCategory);
//		System.out.println("Statement Processed...");
		
//		testing : update()
//		ProductCategoryVO exampleCategory = new ProductCategoryVO();
//		exampleCategory.setCategoryId(3);
//		exampleCategory.setCategoryName("�������O");
//		exampleCategory.setCategoryStatus(1);
//		dao.update(exampleCategory);
//		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		Integer exampleCategory = new Integer(1);
//		ProductCategoryVO productCategoryVO = dao.getOne(exampleCategory);
//		System.out.println("CATEGORY ID: " + productCategoryVO.getCategoryId());
//		System.out.println("CATEGORY NAME: " + productCategoryVO.getCategoryName());
//		System.out.println("CATEGORY STATUS: " + productCategoryVO.getCategoryStatus());
		
//		testing : getAll()
//		List<ProductCategoryVO> list = dao.getAll();
//		for (ProductCategoryVO productCategoryVO : list) {
//			System.out.println("CATEGORY ID: " + productCategoryVO.getCategoryId());
//			System.out.println("CATEGORY NAME: " + productCategoryVO.getCategoryName());
//			System.out.println("CATEGORY STATUS: " + productCategoryVO.getCategoryStatus());
//			System.out.println("-----------------------------------");
//		}
		
	}
}
