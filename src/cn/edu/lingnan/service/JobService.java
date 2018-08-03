package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Job;

public interface JobService {

	/**
	 * ��Ӹ�λ
	 * @param job
	 */
	boolean insertJob(Job job);
	
	/**
	 * �޸ĸ�λ
	 * @param job
	 */
	boolean alterJob(Job job);
	
	/**
	 * ɾ����λ
	 * @param job
	 */
	boolean deleteJob(Job job);
	
	/**
	 * ��ҳ��ѯ��λ��Ϣ
	 * @param pageSize
	 * @param pageNo
	 */
	List<Job> queryJobByPage(int pageSize,int pageNo, Job jobExample);
	
	/**
	 * ����ID����ָ����λ
	 * @param jobId
	 * @return
	 */
	Job findJobById(long jobId);
	
	/**
	 * ��ȡ���и�λ
	 * @return
	 */
	public List<Job> getAllJobList();
	
	/**
	 * ��ȡָ�������ĸ�λ����
	 * @param jobExample
	 * @return
	 */
	public long getAllCounts(Job jobExample);
	
	/**
	 * ��ȡ��λ�·���ְԱ��������
	 * @param jobid
	 * @return
	 */
	long getJobhumanCount(long jobid);
}
