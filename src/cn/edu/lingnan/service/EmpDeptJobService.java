package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Emp;

public interface EmpDeptJobService {

	/**
	 * 有条件查询员工部门岗位记录数
	 * @param example
	 * @return
	 */
	public long getEmpDeptJobAllCount(Emp example);
	
	/**
	 * 分页查找员工部门岗位信息
	 * @param pageSize
	 * @param pageNo
	 * @param example
	 * @return
	 */
	public List<Emp> getEmpDeptJobByPage(int pageSize, int pageNo,Emp example);
	
}
