package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.service.EmpDeptJobService;

@Transactional
@Service
public class EmpDeptJobServiceImpl implements EmpDeptJobService{
	
	@Autowired
	private EmpDao empDao;
	/**
	 * 有条件查询员工部门岗位记录数
	 * @param example
	 * @return
	 */
	public long getEmpDeptJobAllCount(Emp example) {
		String hql="select count(e.id) from Emp e ,Dept d, Job j where 1 = 1 and e.dept.deptid = d.deptid and e.job.jobid = j.jobid ";
		List<Object> param=new ArrayList<Object>();
		hql=addCondition(hql,example,param);
		long count=(Long) empDao.uniqueResult(hql,param.toArray());
		//System.out.println(count);
		return count;
	}

	/**
	 * 分页查找员工部门岗位信息
	 * @param pageSize
	 * @param pageNo
	 * @param example
	 * @return
	 */
	public List<Emp> getEmpDeptJobByPage(int pageSize, int pageNo,
			Emp example) {
		String hql="select e from Emp e ,Dept d, Job j where 1 = 1 and e.dept.deptid = d.deptid and e.job.jobid = j.jobid ";
		List<Object> param=new ArrayList<Object>();
		hql=addCondition(hql,example,param);
		List<Emp> list = empDao.queryListObjectAllForPage(pageSize, pageNo, hql, param.toArray());
		return list;
	}
	
	
	/**
	 * 在查询中插入条件
	 * @param hql
	 * @param example
	 * @param condList
	 * @return
	 */
	private String addCondition(String hql,Emp example,List<Object> condList){
		if(example!=null){
			if(example.getEmpid()!=null){
				hql+=" and e.empid = ?";
				condList.add(example.getEmpid());
			}
			if(example.getEname()!=null&&!example.getEname().equals("")){
				hql+=" and e.ename like ?";
				condList.add("%"+example.getEname()+"%");
			}
			if(example.getDept().getDname()!=null&&!example.getDept().getDname().equals("")){
				hql+=" and d.dname like ?";
				condList.add("%"+example.getDept().getDname()+"%");
			}
			if(example.getJob().getJname()!=null&&!example.getJob().getJname().equals("")){
				hql+=" and j.jname like ?";
				condList.add("%"+example.getJob().getJname()+"%");
				System.out.println("%"+example.getJob().getJname()+"%");
			}
		}
		hql+=" and e.worktype<>'离职' ";
		hql+=" order by e.empid";
		return hql;
	}
	

}
