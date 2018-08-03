package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Dept;

public interface DeptService {

	/**
	 * 分页查询所有部门
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	//List<Dept> queryAllDeptByPage(int pageSize,int pageNo);
	List<Dept> queryAllDeptByExample(int pageNo, int pageSize, Dept deptExample);
	
	/**
	 * 添加部门
	 * @param dept
	 */
	void insertDept(Dept dept);
	
	/**
	 * 修改部门
	 * @param dept
	 */
	void alterDept(Dept dept);
	
	/**
	 * 删除指定部门
	 * @param dept
	 */
	void deleteDept(long deptid) throws Exception;
	
	/**
	 * 根据Id查找指定部门
	 * @param deptId
	 * @return
	 */
	Dept findDeptById(long deptId);
	
	/**
	 * 获取所有部门
	 * @return
	 */
	List<Dept> getAllDept();
	
	/**
	 * 获得所有部门数量
	 * @param deptExample
	 * @return
	 */
	long getAllCounts(Dept deptExample);
}
