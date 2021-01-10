package com.productphoto.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class ProductPhotoJNDIDAO implements ProductPhotoDAO_Interface {
	
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
		"INSERT INTO PRODUCT_PHOTO (PRODUCT_PHOTO_ID, PRODUCT_ID, PRODUCT_PHOTO) VALUES (SEQ_PRODUCT_PHOTO_ID.NEXTVAL, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE PRODUCT_PHOTO SET PRODUCT_ID = ?, PRODUCT_PHOTO = ? WHERE PRODUCT_PHOTO_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM PRODUCT_PHOTO WHERE PRODUCT_PHOTO_ID = ? AND PRODUCT_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM PRODUCT_PHOTO WHERE PRODUCT_ID = ? ORDER BY PRODUCT_PHOTO_ID";
	
	@Override
	public void insert(ProductPhotoVO productPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productPhotoVO.getProductId());
			pstmt.setBytes(2, productPhotoVO.getProductPhoto());
			
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
	public void update(ProductPhotoVO productPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, productPhotoVO.getProductId());
			pstmt.setBytes(2, productPhotoVO.getProductPhoto());
			pstmt.setInt(3, productPhotoVO.getProductPhotoId());
			
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
	public void delete(Integer productPhotoId) {	
	}
	
	@Override
	public ProductPhotoVO getOne(Integer productPhotoId, String productId) {
		ProductPhotoVO productPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, productPhotoId);
			pstmt.setString(2, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productPhotoVO = new ProductPhotoVO();
				productPhotoVO.setProductPhotoId(rs.getInt("PRODUCT_PHOTO_ID"));
				productPhotoVO.setProductId(rs.getString("PRODUCT_ID"));
				productPhotoVO.setProductPhoto(rs.getBytes("PRODUCT_PHOTO"));
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
		
		return productPhotoVO;
	}
	
	@Override
	public List<ProductPhotoVO> getAll(String productId) {
		List<ProductPhotoVO> list = new ArrayList<ProductPhotoVO>();
		ProductPhotoVO productPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productPhotoVO = new ProductPhotoVO();
				productPhotoVO.setProductPhotoId(rs.getInt("PRODUCT_PHOTO_ID"));
				productPhotoVO.setProductId(rs.getString("PRODUCT_ID"));
				productPhotoVO.setProductPhoto(rs.getBytes("PRODUCT_PHOTO"));
				list.add(productPhotoVO);
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
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		ProductPhotoJNDIDAO dao = new ProductPhotoJNDIDAO();
		
//		testing : insert()
//		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
//		productPhotoVO.setProductId("ENP0001");
//		try {
//			productPhotoVO.setProductPhoto(getPictureByteArray("/Users/jordan/desktop/cat.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		dao.insert(productPhotoVO);
//		System.out.println("Statement Processed...");

//		testing : update()
		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
		productPhotoVO.setProductPhotoId(25);
		productPhotoVO.setProductId("ENP0010");
		try {
			productPhotoVO.setProductPhoto(getPictureByteArray
				("/Users/jordan/desktop/EatNAK_Shop_Dummy/ENP0010/8243289_R.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao.update(productPhotoVO);
		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		ProductPhotoVO productPhotoVO = dao.getOne(1, "ENP0001");
//		System.out.println("PRODUCT_PHOTO_ID: " + productPhotoVO.getProductPhotoId());
//		System.out.println("PRODUCT_ID: " + productPhotoVO.getProductId());
//		System.out.println("PRODUCT_PHOTO: " + productPhotoVO.getProductPhoto());
		
//		testing : getAll()
//		List<ProductPhotoVO> list = dao.getAll("ENP0007");
//		for (ProductPhotoVO productPhotoVO : list) {
//			System.out.println("PRODUCT_PHOTO_ID: " + productPhotoVO.getProductPhotoId());
//			System.out.println("PRODUCT_ID: " + productPhotoVO.getProductId());
//			System.out.println("PRODUCT_PHOTO: " + productPhotoVO.getProductPhoto());
//			System.out.println("-----------------------------------");
//		}
	}
}
