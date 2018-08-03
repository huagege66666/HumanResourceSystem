package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Jobadjust;

public interface JobAdjustService {
	
	/**
	 * 调动某个员工岗位
	 */
	public boolean adjustEmpJob(Jobadjust jobAdjust);
	
	/**
	 * 查询所有以调动岗位的员工
	 */
	
	public List<Jobadjust> allAdjustEmp();
	
	/**
	 * 分页查询信息
	 * @param example
	 * @return
	 */
	public List<Jobadjust> getAllAdjustInfo(int pageSize, int pageNo,Jobadjust example);
	
	/**
	 * 获取所有数量
	 * @param example
	 * @return
	 */
	public long getAllCount(Jobadjust example);
	
}
