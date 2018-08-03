package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.dao.JobadjustDao;
import cn.edu.lingnan.dao.JobDao;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Jobadjust;
import cn.edu.lingnan.service.JobAdjustService;

@Transactional
@Service
public class JobAdjustServiceImpl implements JobAdjustService{

	@Autowired
	private JobadjustDao jobAdjustDao;
	
	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private JobDao jobDao;

	/**
	 * 调动某个员工岗位
	 */
	public boolean adjustEmpJob(Jobadjust jobAdjust) {
		//1:检测岗位编制人数是否满人
		Job job = jobDao.findById(jobAdjust.getJobid());
		List<Emp> list = empDao.getListByHQL("from Emp where jobid=?", jobAdjust.getJobid());
		//现有岗位人数大于或等于岗位编制人数，不予调动员工岗位
		if(job.getCount()<list.size()||job.getCount()==list.size()){
			return false;
		}
		//2:如果没有满人，插入数据到岗位调动管理表
		jobAdjustDao.save(jobAdjust);
		//3:更改员工岗位属性
		Emp emp = jobAdjust.getEmp();
		emp.setJob(job);
		empDao.update(emp);
		return true;
	}

	/**
	 * 查询所有已经调动岗位的员工
	 */
	public List<Jobadjust> allAdjustEmp() {
		List<Jobadjust> list = jobAdjustDao.getListByHQL("from JobAdjust");
		return list;
	}

	@Override
	public List<Jobadjust> getAllAdjustInfo(int pageSize, int pageNo,
			Jobadjust example) {
		String hql="from Jobadjust j where 1 = 1 ";
		List<Object> param=new ArrayList<Object>();
		//hql=addCondition(hql,example,param);
		hql+=" order by j.adjustid desc";
		return jobAdjustDao.queryListObjectAllForPage(pageSize, pageNo, hql, param.toArray());
	}

	@Override
	public long getAllCount(Jobadjust example) {
		String hql="select count(j) from Jobadjust j where 1 = 1 ";
		List<Object> param=new ArrayList<Object>();
		//hql=addCondition(hql,example,param);
		long count=(Long) jobAdjustDao.uniqueResult(hql);
		//System.out.println(count);
		return count;
	}
	
	private String addCondition(String hql,Jobadjust example,List<Object> condList){
//		if(example!=null){
//			if(example.getEname()!=null&&!example.getEname().equals("")){
//				hql+=" and e.ename like ?";
//				condList.add("%"+example.getEname()+"%");
//			}
//			if(example.getEmpid()!=null){
//				hql+=" and e.empid = ?";
//				condList.add(example.getEmpid());
//			}
//			if(example.getHiredate()!=null){
//				hql+=" and e.hiredate = ?";
//				condList.add(example.getHiredate());
//			}
//			if(example.getBirthdate()!=null){
//				hql+=" and e.birthdate = ?";
//				condList.add(example.getBirthdate());
//			}
//			if(example.getDept()!=null&&example.getDept().getDname()!=null&&!example.getDept().getDname().equals("")){
//				hql+=" and e.deptid = ?";
//				condList.add(example.getDept().getDeptid());
//			}
//			if(example.getJob()!=null&&example.getJob().getJname()!=null&&!example.getJob().getJname().equals("")){
//				hql+=" and e.deptid = ?";
//				condList.add(example.getJob().getJobid());
//			}
//			if(example.getWorktype()!=null&&example.getWorktype().equals("")){
//				hql+=" and e.worktype = ?";
//				condList.add(example.getWorktype());
//			}
//		}
		return hql;
	}

}
