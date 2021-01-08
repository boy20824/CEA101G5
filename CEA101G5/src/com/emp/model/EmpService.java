package com.emp.model;
import java.util.List;
public class EmpService {

	private EmpDAO_interface dao;
	
	public EmpService() {
		dao=new EmpDAO();
	}
	
	public EmpVO addEmp(String emp_account,String emp_pwd,
			String emp_name,java.sql.Date emp_date,Integer emp_status,byte[] emp_image) {
		EmpVO empVO=new EmpVO();
		empVO.setEmp_name(emp_name);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_password(emp_pwd);
		empVO.setEmp_date(emp_date);
		empVO.setEmp_status(emp_status);
		empVO.setEmp_image(emp_image);
		dao.insert(empVO);
		return empVO;
	}
	
	public EmpVO updateEmp(String emp_id,String emp_account,String emp_pwd,
			String emp_name,java.sql.Date emp_date,Integer emp_status,byte[] emp_image) {
		EmpVO empVO=new EmpVO();
		empVO.setEmp_id(emp_id);
		empVO.setEmp_account(emp_account);
		empVO.setEmp_password(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_date(emp_date);
		empVO.setEmp_status(emp_status);
		empVO.setEmp_image(emp_image);
		dao.update(empVO);
		return empVO;
	}
	
	public void deleteEmp(String emp_id) {
		dao.delete(emp_id);
	}
	public EmpVO getOneEmp(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	public EmpVO getOneAccount(String emp_account) {
		return dao.findByAccount(emp_account);
	}
	public List<EmpVO> getAll() {
		return dao.getAll();
	}

	

	
	
}
