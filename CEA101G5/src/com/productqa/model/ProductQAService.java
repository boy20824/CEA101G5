package com.productqa.model;

import java.util.List;

public class ProductQAService {
	
	private ProductQADAO_Interface dao;
	
	public ProductQAService() {
		dao = new ProductQAJNDIDAO();
	}
	
	public ProductQAVO addQA(String productId, String memPhone, String productQues, String productReply) {
		ProductQAVO productQAVO = new ProductQAVO();
		
		productQAVO.setProductId(productId);
		productQAVO.setMemPhone(memPhone);
		productQAVO.setProductQues(productQues);
		productQAVO.setProductReply(productReply);
		dao.insert(productQAVO);
		
		return productQAVO;
	}
	
	public ProductQAVO updateQA(Integer pqaId, String productId, String memPhone, String productQues, String productReply) {
		ProductQAVO productQAVO = new ProductQAVO();
		
		productQAVO.setPqaId(pqaId);
		productQAVO.setProductId(productId);
		productQAVO.setMemPhone(memPhone);
		productQAVO.setProductQues(productQues);
		productQAVO.setProductReply(productReply);
		dao.update(productQAVO);
		
		return productQAVO;
	}
	
	public ProductQAVO getQAById(Integer pqaId) {
		return dao.getOne(pqaId);
	}
	public void deletePqaId(Integer pqaId) {
		dao.delete(pqaId);
	}
	public List<ProductQAVO> getAllQA() {
		return dao.getAll();
	}
	public List<ProductQAVO> getAllQANull() {
		return dao.getAllNull();
	}
	public List<ProductQAVO> getAllQAByProductId(String productId) {
		return dao.getQAByProductId(productId);
	}
	
	public static void main(String[] args) {
		ProductQAService productQAService = new ProductQAService(); 
		
//		testing : addQA()
//		productQAService.addQA("ENP0008", "0921842859", "SERVICE´ú¸Ő°ÝĂD", "SERVICE´ú¸ŐŚ^ľŞ");
//		System.out.println("Statement Processed...");
		
//		testing : updateQA()
//		productQAService.updateQA(21, "ENP0010", "0921842859", "SERVICE´ú¸Ő°ÝĂD", "SERVICE´ú¸ŐŚ^ľŞ");
//		System.out.println("Statement Processed...");
		
//		testing : getQAById()
		ProductQAVO productQAVO = productQAService.getQAById(35);
		System.out.println("PQA ID: " + productQAVO.getPqaId());
		System.out.println("PRODUCT ID: " + productQAVO.getProductId());
		System.out.println("MEM_PHONE: " + productQAVO.getMemPhone());
		System.out.println("PRODUCT_QUES: " + productQAVO.getProductQues());
		System.out.println("PRODUCT_QUES_TSTAMP: " + productQAVO.getProductQuesTstamp());
		System.out.println("PRODUCT_REPLY: " + productQAVO.getProductReply());
		System.out.println("PRODUCT_REPLY_TSTAMP: " + productQAVO.getProductReplyTstamp());
		
//		testing : getAllQA()
//		List<ProductQAVO> list = productQAService.getAllQA();
//		for (ProductQAVO productQAVO : list) {
//			System.out.println("PQA ID: " + productQAVO.getPqaId());
//			System.out.println("PRODUCT ID: " + productQAVO.getProductId());
//			System.out.println("MEM PHONE: " + productQAVO.getMemPhone());
//			System.out.println("PRODUCT_QUES: " + productQAVO.getProductQues());
//			System.out.println("PRODUCT_QUES TSTAMP: " + productQAVO.getProductQuesTstamp());
//			System.out.println("PRODUCT_REPLY: " + productQAVO.getProductReply());
//			System.out.println("PRODUCT_REPLY_TSTAMP: " + productQAVO.getProductReplyTstamp());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllQAByProductId()
//		List<ProductQAVO> list = productQAService.getAllQAByProductId("ENP0001");
//		for (ProductQAVO productQAVO : list) {
//			System.out.println("PQA ID: " + productQAVO.getPqaId());
//			System.out.println("PRODUCT ID: " + productQAVO.getProductId());
//			System.out.println("MEM PHONE: " + productQAVO.getMemPhone());
//			System.out.println("PRODUCT_QUES: " + productQAVO.getProductQues());
//			System.out.println("PRODUCT_QUES TSTAMP: " + productQAVO.getProductQuesTstamp());
//			System.out.println("PRODUCT_REPLY: " + productQAVO.getProductReply());
//			System.out.println("PRODUCT_REPLY_TSTAMP: " + productQAVO.getProductReplyTstamp());
//			System.out.println("-----------------------------------");
//		}
		
		
	}
	
}
