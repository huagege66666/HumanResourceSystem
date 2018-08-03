package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Leavejob;

public interface EmpService {

	/**
	 * 修改员工信息
	 * @param emp
	 */
	void alterEmpInfo(Emp example);
	void alterEmpInfo(long id,Emp example);
	
	/**
	 * 插入Emp表
	 * @param emp
	 * @param deptid
	 * @param jobid
	 */
	void insertEmp(Emp emp,long deptid,long jobid,long resid);
	/**
	 * 分页查找emp表
	 * @param pagesize
	 * @return
	 */
	List<Emp> findEmpByPage(int pageSize,int pageNo,Emp example);
	
	/**
	 * 根据员工Idc查询对应员工
	 * @param empid
	 * @return
	 */
	Emp findEmpById(long empid);
	
	/**
	 * 查找员工的离职信息
	 * @param empid
	 * @return
	 */
	Leavejob findLeavejobByEmpId(long empid);
	
	/**
	 * 解雇员工
	 * @param emp
	 */
	void fireEmp(Leavejob leavejob);
	
	/**
	 * 获取指定条件员工的数量
	 * @param example
	 * @return
	 */
	long getAllCount(Emp example);
	
}
