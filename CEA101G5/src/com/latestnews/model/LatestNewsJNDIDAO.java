package com.latestnews.model;

import java.io.FileInputStream;
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

public class LatestNewsJNDIDAO implements LatestNewsDAO_Interface {
	
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
		"INSERT INTO LATEST_NEWS (NEWS_ID, NEWS_TITLE, NEWS_CONTENT_TXT, NEWS_CONTENT_IMG, NEWS_STATUS) VALUES (SEQ_NEWS_ID.NEXTVAL, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE LATEST_NEWS SET NEWS_TITLE = ?, NEWS_CONTENT_TXT = ?, NEWS_CONTENT_IMG = ?, NEWS_STATUS = ? WHERE NEWS_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM LATEST_NEWS WHERE NEWS_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM LATEST_NEWS ORDER BY NEWS_ID";

	@Override
	public void insert(LatestNewsVO latestNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, latestNewsVO.getNewsTitle());
			pstmt.setString(2, latestNewsVO.getNewsContentTxt());
			pstmt.setBytes(3, latestNewsVO.getNewsContentImg());
			pstmt.setInt(4, latestNewsVO.getNewsStatus());
			
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
	public void update(LatestNewsVO latestNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, latestNewsVO.getNewsTitle());
			pstmt.setString(2,  latestNewsVO.getNewsContentTxt());
			pstmt.setBytes(3, latestNewsVO.getNewsContentImg());
			pstmt.setInt(4, latestNewsVO.getNewsStatus());
			pstmt.setInt(5, latestNewsVO.getNewsId());
			
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
	public void delete(Integer newsId) {
	}

	@Override
	public LatestNewsVO getOne(Integer newsId) {
		LatestNewsVO latestNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, newsId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setNewsTitle(rs.getString("NEWS_TITLE"));
				latestNewsVO.setNewsContentTxt(rs.getString("NEWS_CONTENT_TXT"));
				latestNewsVO.setNewsContentImg(rs.getBytes("NEWS_CONTENT_IMG"));
				latestNewsVO.setNewsStatus(rs.getInt("NEWS_STATUS"));
				latestNewsVO.setNewsTS(rs.getDate("NEWS_TS"));
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
		
		return latestNewsVO;
	}

	@Override
	public List<LatestNewsVO> getAll() {
		List<LatestNewsVO> list = new ArrayList<LatestNewsVO>();
		LatestNewsVO latestNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				latestNewsVO = new LatestNewsVO();
				latestNewsVO.setNewsId(rs.getInt("NEWS_ID"));
				latestNewsVO.setNewsTitle(rs.getString("NEWS_TITLE"));
				latestNewsVO.setNewsContentTxt(rs.getString("NEWS_CONTENT_TXT"));
				latestNewsVO.setNewsContentImg(rs.getBytes("NEWS_CONTENT_IMG"));
				latestNewsVO.setNewsStatus(rs.getInt("NEWS_STATUS"));
				latestNewsVO.setNewsTS(rs.getDate("NEWS_TS"));
				list.add(latestNewsVO);
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
		LatestNewsJNDIDAO dao = new LatestNewsJNDIDAO();
		
//		testing : insert()
//		LatestNewsVO LatestNewsVO = new LatestNewsVO();
//		LatestNewsVO.setNewsTitle("���լ��ʥD�D");
//		LatestNewsVO.setNewsContentTxt("���լ��ʤ��e");
//		try {
//			LatestNewsVO.setNewsContentImg(getPictureByteArray("/Users/jordan/desktop/cat.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		LatestNewsVO.setNewsStatus(1);
//		dao.insert(LatestNewsVO);
//		System.out.println("Statement Processed...");
		
//		testing : update()
//		LatestNewsVO latestNewsVO = new LatestNewsVO();
//		latestNewsVO.setNewsId(6);
//		latestNewsVO.setNewsTitle("���լ��ʥD�D");
//		latestNewsVO.setNewsContentTxt("���լ��ʤ��e");
//		try {
//			latestNewsVO.setNewsContentImg(getPictureByteArray("/Users/jordan/desktop/cat.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		latestNewsVO.setNewsStatus(1);
//		dao.update(latestNewsVO);
//		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		LatestNewsVO latestNewsVO = dao.getOne(7);
//		System.out.println("NEWS_ID: " + latestNewsVO.getNewsId());
//		System.out.println("NEWS_TITLE: " + latestNewsVO.getNewsTitle());
//		System.out.println("NEWS_CONTENT_TXT: " + latestNewsVO.getNewsContentTxt());
//		System.out.println("NEWS_CONTENT_IMG: " + latestNewsVO.getNewsContentImg());
//		System.out.println("NEWS_STATUS: " + latestNewsVO.getNewsStatus());
//		System.out.println("NEWS_TS: " + latestNewsVO.getNewsTS());
		
//		testing : getAll()
//		List<LatestNewsVO> list = dao.getAll();
//		for (LatestNewsVO latestNewsVO : list) {
//			System.out.println("NEWS_ID: " + latestNewsVO.getNewsId());
//			System.out.println("NEWS_TITLE: " + latestNewsVO.getNewsTitle());
//			System.out.println("NEWS_CONTENT_TXT: " + latestNewsVO.getNewsContentTxt());
//			System.out.println("NEWS_CONTENT_IMG: " + latestNewsVO.getNewsContentImg());
//			System.out.println("NEWS_STATUS: " + latestNewsVO.getNewsStatus());
//			System.out.println("NEWS_TS: " + latestNewsVO.getNewsTS());
//			System.out.println("-----------------------------------");
//		}
	}
	
}
