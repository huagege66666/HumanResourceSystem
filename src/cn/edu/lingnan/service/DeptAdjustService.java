package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Deptadjust;
import cn.edu.lingnan.pojo.Emp;

public interface DeptAdjustService {

	/**
	 * 员工部门调动
	 */
	public void deptAdjust(Deptadjust deptAdjust);
	
	
	/**
	 * 已调动部门员工信息查询
	 */
	public List<Emp> getAllAdjustEmp();
	
	/**
	 * 分页查询信息
	 * @param example
	 * @return
	 */
	public List<Deptadjust> getAllAdjustInfo(int pageSize, int pageNo,Deptadjust example);
	
	/**
	 * 获取所有数量
	 * @param example
	 * @return
	 */
	public long getAllCount(Deptadjust example);
}
