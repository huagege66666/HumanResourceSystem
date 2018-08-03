package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Testinfo;


public interface TestInfoService {

	/**
	 * 查找所有已转正的员工信息
	 */
	public List<Emp> getAllRegularEmp();
	
	/**
	 * 查询某一条试用信息数据
	 */
	public Testinfo getTestInfoById(Long id);
	
	/**
	 * 找出所有员工试用信息
	 * @return
	 */
	public List<Testinfo> getAllTestInfo();
	
	/**
	 * 有条件查找全部试用员工信息记录数
	 */
	public long getTestInfoAllCount(Testinfo example);
	
	/**
	 * 条件分页查找试用信息
	 */
	public List<Testinfo> getTestInfoByPage(int pageSize, int pageNo,Testinfo example);
}
