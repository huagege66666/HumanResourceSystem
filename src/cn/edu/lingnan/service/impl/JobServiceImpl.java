package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.dao.JobDao;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.JobService;
@Transactional
@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private EmpDao empDao;
	
	
	//增加
	@Override
	public boolean insertJob(Job job) {
		List<Job> list = this.getAllJobList();
		for (Job job2 : list) {
			if(job2.getJname().equals(job.getJname()))
				return false;
		}
		jobDao.save(job);
		return true;
	}
	
	//修改
	@Override
	public boolean alterJob(Job job) {
		List<Job> list = this.getAllJobList();
		for (Job job2 : list) {
			if(job2.getJobid()!=job.getJobid()
					&&job2.getJname().equals(job.getJname()))
				return false;
		}
	 	jobDao.merge(job);
	 		return true;
	}
	
	
	//删除
	@Override
	public boolean deleteJob(Job job) {
		jobDao.delete(job);
		return true;
	}

	//分页查询
	@Override
	public List<Job> queryJobByPage(int pageNo, int pageSize, Job jobExample) {
		List<Object> params = new ArrayList<Object>();
		String hql = "select j from Job j where 1=1";
		if(jobExample!=null){
			if(jobExample.getJobid()!=null){
				hql+=" and j.jobid = ?";
				params.add(jobExample.getJobid());
			}
			if(jobExample.getJname()!=null&&!"".equals(jobExample.getJname())){
				hql+=" and j.jname like ?";
				params.add("%"+jobExample.getJname()+"%");
			}
	
			if(jobExample.getType()!=null&&!"".equals(jobExample.getType())){
				hql+=" and j.type like ?";
				params.add("%"+jobExample.getType()+"%");
			}
		}
		hql+=" order by j.jobid asc";
		return jobDao.queryListObjectAllForPage(pageSize, pageNo, hql,params.toArray());
	}
	
	
	//根据id查找
	@Override
	public Job findJobById(long jobId) {
		return jobDao.findById(jobId);
	}
	
	//查找所有
	@Override
	public List<Job> getAllJobList() {
		List<Job> jobList;
		String hql = "from Job";
		jobList = jobDao.getListByHQL(hql);
		return jobList;
	}
	
	//
	@Override
	public long getAllCounts(Job jobExample) {
		List<Object> params = new ArrayList<Object>();
		String hql = "select count(j) from Job j where 1=1";
		if(jobExample!=null){
			if(jobExample.getJobid()!=null){
				hql+=" and j.jobid = ?";
				params.add(jobExample.getJobid());
			}
			if(jobExample.getJname()!=null&&!"".equals(jobExample.getJname())){
				hql+=" and j.jname like ?";
				params.add("%"+jobExample.getJname()+"%");
			}
	
			if(jobExample.getType()!=null&&!"".equals(jobExample.getType())){
				hql+=" and j.type like ?";
				params.add("%"+jobExample.getType()+"%");
			}
		}
		return  (Long) jobDao.uniqueResult(hql,params.toArray());
	}

	@Override
	public long getJobhumanCount(long jobid) {
		String hql="select count(e.empid) from Emp e where e.job.jobid = "
				+jobid+" and e.worktype != '离职'";
		long row =(Long) empDao.uniqueResult(hql);
		return row;
	}


}
