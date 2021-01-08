package com.empauth.model;
import java.util.List;
public class EmpAuthService {
	private EmpAuthDAO_interface dao;
		
	public EmpAuthService(){
		dao=new EmpAuthDAO();
	}
	
	public EmpAuthVO addEmpAuth(String emp_id,String auth_no) {
		EmpAuthVO eaVO=new EmpAuthVO();
		eaVO.setEmp_id(emp_id);
		eaVO.setAuth_no(auth_no);
		dao.insert(eaVO);
		return eaVO;
	}
	public EmpAuthVO updateEmpAuth(String emp_id,String auth_no) {
		EmpAuthVO eaVO=new EmpAuthVO();
		eaVO.setEmp_id(emp_id);
		eaVO.setAuth_no(auth_no);
		dao.update(eaVO);
		return eaVO;
	}
	public void deleteEmpAuth(String emp_id,String auth_no) {
		dao.delete(emp_id,auth_no);
	}

	public EmpAuthVO getOneEmpAuth(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	public List<EmpAuthVO> getAll(){
		return dao.getAll();
	}
//	public List<EmpAuthVO> getOneEmpid(String emp_id) {
//	return dao.getOneEmpAuth(emp_id);
//}
	}
	

