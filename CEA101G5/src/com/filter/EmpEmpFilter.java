package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpVO;
import com.empauth.model.EmpAuthVO;

public class EmpEmpFilter implements Filter{
	
	ServletContext context=null;
	@Override
	public void init(FilterConfig config) {
	context = config.getServletContext();
	}

	@Override
	public void destroy() {
		context = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		EmpAuthVO empEmp=(EmpAuthVO)session.getAttribute("empEmp");
		if(empEmp==null) {
			session.setAttribute("location",req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/back-end/emp/EmpNoAuth.jsp");
			return ;
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
