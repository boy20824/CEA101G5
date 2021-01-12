package com.ordermaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.MemVO;
import com.ordermaster.model.*;
import com.product.model.*;
import com.promotiondetail.model.*;

public class OrderMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderMasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		Vector<ProductVO> buyList = (Vector<ProductVO>)session.getAttribute("buyList"); 
		MemVO memVO = (MemVO)session.getAttribute("memLogin");
		
		// Add To Cart
		if ("addToCart".equals(action)) {
			Boolean match = false;
			
			ProductVO productVO = getProductVO(req);
			
			if (buyList == null) {
				buyList = new Vector<ProductVO>();
				buyList.add(productVO);
			} else {
				for (int i = 0; i < buyList.size(); i++) {
					ProductVO productVOInBuyList = buyList.get(i);
					
					if (productVOInBuyList.getProductId().equals(productVO.getProductId())) {
						productVOInBuyList.setProductQty(productVOInBuyList.getProductQty() + productVO.getProductQty());
						buyList.setElementAt(productVOInBuyList, i);
						match = true;
					}
				}
				
				if (!match) {
					buyList.add(productVO);
				}
			}
			
			session.setAttribute("buyList", buyList);

			Gson gson = new Gson();
			String buyListToJson = gson.toJson(buyList);
			PrintWriter out = res.getWriter();
			out.write(buyListToJson);
		}
		
		// Delete Item From Cart
		if ("deleteFromCart".equals(action)) {
			String productId = req.getParameter("productId");
			
			for (int i = 0; i < buyList.size(); i++) {
				ProductVO productVOInBuyList = buyList.get(i);
				
				if (productVOInBuyList.getProductId().equals(productId)) {
					buyList.remove(productVOInBuyList);
				}
			}
			
			session.setAttribute("buyList", buyList);
		}
		
		// Add To Cart & CheckOut
		if ("addToCartAndCheckOut".equals(action)) {
			Boolean match = false;
			
			ProductVO productVO = getProductVO(req);
			
			if (buyList == null) {
				buyList = new Vector<ProductVO>();
				buyList.add(productVO);
			} else {
				for (int i = 0; i < buyList.size(); i++) {
					ProductVO productVOInBuyList = buyList.get(i);
					
					if (productVOInBuyList.getProductId().equals(productVO.getProductId())) {
						productVOInBuyList.setProductQty(productVOInBuyList.getProductQty() + productVO.getProductQty());
						buyList.setElementAt(productVOInBuyList, i);
						match = true;
					}
				}
				
				if (!match) {
					buyList.add(productVO);
				}
			}
			
			session.setAttribute("buyList", buyList);
			String url = req.getContextPath() + "/front-end/shopCheckOut.jsp";
			res.sendRedirect(url);
		}
		
		// Update Cart
		if ("updateCart".equals(action)) {
			Boolean match = false;
			
			ProductVO productVO = getProductVO(req);
			
			if (buyList == null) {
				buyList = new Vector<ProductVO>();
				buyList.add(productVO);
			} else {
				for (int i = 0; i < buyList.size(); i++) {
					ProductVO productVOInBuyList = buyList.get(i);
					
					if (productVOInBuyList.getProductId().equals(productVO.getProductId())) {
						if (productVOInBuyList.getProductQty() != productVO.getProductQty()) {
							productVOInBuyList.setProductQty(productVO.getProductQty());
							buyList.setElementAt(productVOInBuyList, i);
							match = true;
						}
					}
				}
				
				if (!match) {
					buyList.add(productVO);
				}
			}
		}
		
		// Check Out
		if ("checkOut".equals(action)) {
			
			OrderMasterVO orderMasterVO = getOrderMasterVO(req);
			OrderMasterJDBCDAO orderMasterJDBCDAO = new OrderMasterJDBCDAO();
			orderMasterJDBCDAO.insertWithOrderDetail(orderMasterVO, buyList);
			
			buyList.clear();
			session.setAttribute("buyList", buyList);
			
			String url = req.getContextPath() + "/shop/ordermaster.do?action=orderHistoryQuery";
			res.sendRedirect(url);
		}
		
//		for (ProductVO productVO : buyList) {
//			System.out.println(productVO.getProductId());
//			System.out.println(productVO.getProductPrice());
//			System.out.println(productVO.getProductQty());
//			System.out.println(buyList.size());
//		}
		
		if ("updateom".equals(action)) {
			Integer orderId = new Integer (req.getParameter("orderId"));
			Integer orderStatus = new Integer (req.getParameter("orderStatus"));
			
			//因為原本的update要給他一個VO  所以所有屬性都要set才會過
			OrderMasterService omSvc = new OrderMasterService();
			omSvc.updateOM(orderStatus,orderId);
			
			String searchyn = req.getParameter("searchyn");
			if ("yes".equals(searchyn)) {
				String url = "/back-end/shopOrderMasterListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			else {
				String url = "/back-end/shopOrderMasterListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("orderHistoryQuery".equals(action)) {
			String memPhone = memVO.getMemPhone();
			
			OrderMasterService orderMasterService = new OrderMasterService();
			List<OrderMasterVO> orderMasterVOList = orderMasterService.getAllByMemPhone(memPhone);
			
			req.setAttribute("orderMasterVOList", orderMasterVOList);
			
			String url = "/front-end/shopOrderHistory.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}
		
	}
	
	private ProductVO getProductVO(HttpServletRequest req) {
		String productId = req.getParameter("productId");
		Integer productQty = Integer.parseInt(req.getParameter("productQty"));
		
		ProductVO productVO = new ProductVO();
		ProductService productService = new ProductService();
		productVO = productService.getProductById(productId);
		productVO.setProductQty(productQty);
		
		PromotionDetailService promotionDetailService = new PromotionDetailService();
		List<PromotionDetailVO> promotionDetailVOList = promotionDetailService.getAllPromotionDetail(1);
		for (PromotionDetailVO promotionDetailVO : promotionDetailVOList) {
			if (promotionDetailVO.getProductId().equals(productId)) {
				productVO.setProductPrice(promotionDetailVO.getProductPrice());
			}
		}
		return productVO;
	}
	
	private OrderMasterVO getOrderMasterVO(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Vector<ProductVO> buyList = (Vector<ProductVO>)session.getAttribute("buyList"); 
		
		String memPhone = req.getParameter("memPhone");
		String recipientName =  req.getParameter("recipientName");
		String recipientMobNumber = req.getParameter("recipientMobNumber");
		String recipientTelNumber = req.getParameter("recipientTelNumber");
		String recipientEmail = req.getParameter("recipientEmail");
		String businessNumber = req.getParameter("businessNumber");
		Integer deliveryMethod = 0;
		String deliveryAddress = req.getParameter("deliveryAddress");
		String orderMemo = req.getParameter("orderMemo");
		
		Integer invoicePrice = 0;
		for (ProductVO productVO : buyList) {
			invoicePrice += productVO.getProductPrice() * productVO.getProductQty();
		};
		Integer orderStatus = 1;
		
		OrderMasterVO orderMasterVO = new OrderMasterVO();
		orderMasterVO.setMemPhone(memPhone);
		orderMasterVO.setRecipientName(recipientName);
		orderMasterVO.setRecipientMobNumber(recipientMobNumber);
		orderMasterVO.setRecipientTelNumber(recipientTelNumber);
		orderMasterVO.setRecipientEmail(recipientEmail);
		orderMasterVO.setBusinessNumber(businessNumber);
		orderMasterVO.setDeliveryMethod(deliveryMethod);
		orderMasterVO.setDeliveryAddress(deliveryAddress);
		orderMasterVO.setOrderMemo(orderMemo);
		orderMasterVO.setInvoicePrice(String.valueOf(invoicePrice));
		orderMasterVO.setOrderStatus(orderStatus);

		return orderMasterVO;
		
	}
	
}
