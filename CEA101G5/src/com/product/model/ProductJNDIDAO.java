package com.product.model;

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

import util.Util;

public class ProductJNDIDAO implements ProductDAO_Interface {

	private static DataSource dataSource = null;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
		private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_MSRP, PRODUCT_PRICE, PRODUCT_QTY_SOLD, CATEGORY_ID, PRODUCT_STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String UPDATE_STMT = 
			"UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_DESCRIPTION = ?, PRODUCT_MSRP = ?, PRODUCT_PRICE = ?, PRODUCT_QTY_SOLD = ?, CATEGORY_ID = ?, PRODUCT_STATUS = ? WHERE PRODUCT_ID = ?";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM PRODUCT ORDER BY PRODUCT_ID";
		
		private static final String GET_ALL_BY_CATEGORYID = 
			"SELECT * FROM PRODUCT WHERE CATEGORY_ID = ? ORDER BY PRODUCT_ID";
		private static final String GET_ALL_BY_QTYSOLD = 
			"SELECT * FROM PRODUCT ORDER BY PRODUCT_QTY_SOLD DESC";
		private static final String GET_ALL_BY_KEYWORDSEARCH = 
			"SELECT * FROM PRODUCT WHERE LOWER(PRODUCT_NAME) LIKE ?";
		private static final String GET_ALL_BY_PRICELTH = 
			"SELECT * FROM PRODUCT ORDER BY PRODUCT_PRICE";
		private static final String GET_ALL_BY_PRICEHTL = 
			"SELECT * FROM PRODUCT ORDER BY PRODUCT_PRICE DESC";
		
		private static final String TESTU = 
				"UPDATE PRODUCT SET PRODUCT_NAME=?, PRODUCT_DESCRIPTION = ?, PRODUCT_MSRP = ?, PRODUCT_PRICE = ?,CATEGORY_ID=?, PRODUCT_STATUS = ? WHERE PRODUCT_ID = ?";
		private static final String ADD = 
				"INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_MSRP, PRODUCT_PRICE, PRODUCT_QTY_SOLD, CATEGORY_ID, PRODUCT_STATUS) VALUES ('ENP' || LPAD(SEQ_PRODUCT_ID.NEXTVAL,4,'0'), ?, ?, ?, ?, ?, ?, ?)";
		private static final String GETPID ="SELECT LAST_NUMBER -1 AS QQ FROM ALL_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_PRODUCT_ID'";
		
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productVO.getProductId());
			pstmt.setString(2, productVO.getProductName());
			pstmt.setString(3, productVO.getProductDescription());
			pstmt.setInt(4, productVO.getProductMSRP());
			pstmt.setInt(5, productVO.getProductPrice());
			pstmt.setInt(6, productVO.getProductQtySold());
			pstmt.setInt(7, productVO.getCategoryId());
			pstmt.setInt(8, productVO.getProductStatus());
			
			pstmt.executeUpdate();
			
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, productVO.getProductName());
			pstmt.setString(2, productVO.getProductDescription());
			pstmt.setInt(3, productVO.getProductMSRP());
			pstmt.setInt(4, productVO.getProductPrice());
			pstmt.setInt(5, productVO.getProductQtySold());
			pstmt.setInt(6, productVO.getCategoryId());
			pstmt.setInt(7, productVO.getProductStatus());
			pstmt.setString(8, productVO.getProductId());
			
			pstmt.executeUpdate();
			
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
	public void delete(String productId) {
	}

	@Override
	public ProductVO getOne(String productId) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
			}
			
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
		
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				list.add(productVO);
			}
			
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
	public List<ProductVO> getAllByCategoryId(Integer categoryId) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_CATEGORYID);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				list.add(productVO);
			}
			
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
	public List<ProductVO> getAllByProductQtySold() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_QTYSOLD);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				list.add(productVO);
			}
			
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
	public List<ProductVO> getAllByKeywordSearch(String keyword) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_KEYWORDSEARCH);
			String processedKeyword = keyword.toLowerCase().trim();
			pstmt.setString(1, "%" + processedKeyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				list.add(productVO);
			}
			
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
	public List<ProductVO> getAllByPriceLTH() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PRICELTH);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				list.add(productVO);
			}
			
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
	public List<ProductVO> getAllByPriceHTL() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PRICEHTL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getString("PRODUCT_ID"));
				productVO.setProductName(rs.getNString("PRODUCT_NAME"));
				productVO.setProductDescription(rs.getNString("PRODUCT_DESCRIPTION"));
				productVO.setProductMSRP(rs.getInt("PRODUCT_MSRP"));
				productVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				productVO.setProductQtySold(rs.getInt("PRODUCT_QTY_SOLD"));
				productVO.setCategoryId(rs.getInt("CATEGORY_ID"));
				productVO.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				list.add(productVO);
			}
			
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
	public void testU(String productId,String productName, String productDescription, Integer productMSRP, Integer productPrice,Integer categoryId ,Integer productStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(TESTU);
			pstmt.setString(1, productName);
			pstmt.setString(2, productDescription);
			pstmt.setInt(3, productMSRP);
			pstmt.setInt(4, productPrice);
			pstmt.setInt(5, categoryId);
			pstmt.setInt(6, productStatus);
			pstmt.setString(7, productId);
			
			pstmt.executeUpdate();
			
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
	public void add(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ADD);
			
			pstmt.setString(1, productVO.getProductName());
			pstmt.setString(2, productVO.getProductDescription());
			pstmt.setInt(3, productVO.getProductMSRP());
			pstmt.setInt(4, productVO.getProductPrice());
			pstmt.setInt(5, productVO.getProductQtySold());
			pstmt.setInt(6, productVO.getCategoryId());
			pstmt.setInt(7, productVO.getProductStatus());
			
			pstmt.executeUpdate();
			
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
	public String getPID() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String seq = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GETPID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				seq = rs.getString("QQ");
			}
			
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
		
		return seq;
	}
	
	
	public static void main(String[] args) {
		ProductJNDIDAO dao = new ProductJNDIDAO();
		
//		testing : insert()
//		ProductVO product = new ProductVO();
//		product.setProductId("ENP0012");
//		product.setProductName("���ղ��~");
//		product.setProductDescription("���ղ��~�ԭz");
//		product.setProductMSRP(111);
//		product.setProductPrice(222);
//		product.setProductQtySold(333);
//		product.setCategoryId(1);
//		product.setProductStatus(1);
//		dao.insert(product);
//		System.out.println("Statement Processed...");
		
//		testing : update()
//		ProductVO product = new ProductVO();
//		product.setProductId("ENP0012");
//		product.setProductName("���ղ��~");
//		product.setProductDescription("���ղ��~�ԭz");
//		product.setProductMSRP(555);
//		product.setProductPrice(666);
//		product.setProductQtySold(777);
//		product.setCategoryId(1);
//		product.setProductStatus(1);
//		dao.update(product);
//		System.out.println("Statement Processed...");
		
//		testing : getOne()
		String product = new String("ENP0001");
		ProductVO productVO = dao.getOne(product);
		System.out.println("PRODUCT ID: " + productVO.getProductId());
		System.out.println("PRODUCT NAME: " + productVO.getProductName());
		System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
		System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
		System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
		System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
		System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
		System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
		
//		testing : getAll()
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByCategoryId()
//		List<ProductVO> list = dao.getAllByCategoryId(1);
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByCategoryId()
//		List<ProductVO> list = dao.getAllByProductQtySold();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByKeywordSearch()
//		List<ProductVO> list = dao.getAllByNameSearch("    100          ");
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByPriceLTH()
//		List<ProductVO> list = dao.getAllByPriceLTH();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByPriceHTML()
//		List<ProductVO> list = dao.getAllByPriceHTL();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
	}
}
