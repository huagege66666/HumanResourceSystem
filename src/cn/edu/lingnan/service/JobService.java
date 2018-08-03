package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Job;

public interface JobService {

	/**
	 * 添加岗位
	 * @param job
	 */
	boolean insertJob(Job job);
	
	/**
	 * 修改岗位
	 * @param job
	 */
	boolean alterJob(Job job);
	
	/**
	 * 删除岗位
	 * @param job
	 */
	boolean deleteJob(Job job);
	
	/**
	 * 分页查询岗位信息
	 * @param pageSize
	 * @param pageNo
	 */
	List<Job> queryJobByPage(int pageSize,int pageNo, Job jobExample);
	
	/**
	 * 根据ID查找指定岗位
	 * @param jobId
	 * @return
	 */
	Job findJobById(long jobId);
	
	/**
	 * 获取所有岗位
	 * @return
	 */
	public List<Job> getAllJobList();
	
	/**
	 * 获取指定条件的岗位数量
	 * @param jobExample
	 * @return
	 */
	public long getAllCounts(Job jobExample);
	
	/**
	 * 获取岗位下非离职员工的数量
	 * @param jobid
	 * @return
	 */
	long getJobhumanCount(long jobid);
}
