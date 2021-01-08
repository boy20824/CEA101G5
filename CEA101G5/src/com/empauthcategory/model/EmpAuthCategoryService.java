package com.empauthcategory.model;
import java.util.List;
public class EmpAuthCategoryService {

	private EmpAuthCategoryDAO_interface dao;
	
	public EmpAuthCategoryService() {
		dao=new EmpAuthCategoryDAO();
	}
		
		
		public EmpAuthCategoryVO addEmpAuthCategory(String auth_no,String auth_name) {
			EmpAuthCategoryVO eacVO=new EmpAuthCategoryVO();
			eacVO.setAuth_no(auth_no);
			eacVO.setAuth_name(auth_name);
			dao.insert(eacVO);
			return eacVO;
				}
		public EmpAuthCategoryVO updateEmpAuthCategory(String auth_no,String auth_name) {
			EmpAuthCategoryVO eacVO=new EmpAuthCategoryVO();
			eacVO.setAuth_no(auth_no);
			eacVO.setAuth_name(auth_name);
			dao.update(eacVO);
			return eacVO;
		}
		public void deleteEmpAuthCategory(String auth_no) {
			dao.delete(auth_no);
			
		}
		public EmpAuthCategoryVO getOneEmpAuthCategory(String auth_no) {
			return dao.findByPrimaryKey(auth_no);
			
		}
		public List<EmpAuthCategoryVO> getAll(){
			return dao.getAll();
			
		}
		
	}