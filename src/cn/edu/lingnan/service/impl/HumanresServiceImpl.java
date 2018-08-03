package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.lingnan.dao.HumanresDao;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Humanres;
import cn.edu.lingnan.service.HumanresService;

@Transactional
@Service
public class HumanresServiceImpl implements HumanresService {

	@Autowired
	HumanresDao humresDao;
	
	@Override
	public Humanres findHumanresByID(long id) {
		// TODO Auto-generated method stub
		return humresDao.findById(id);
	}
	
	public List<Humanres> queryHumanresByPage(int pageSize, int pageNo,
			Humanres humanres) {
		String hql="from Humanres h where h.identnum not in (select e.identnum from Emp e where e.worktype <> '离职')";
		List<Object> param=new ArrayList<Object>();
		//hql=addCondition(hql,example,param);
		hql+="order by resid asc";
		return humresDao.queryListObjectAllForPage(pageSize, pageNo, hql, param.toArray());
	}

	@Override
	public List<Humanres> queryHumanresByPage(int pageSize, int pageNo) {
		return queryHumanresByPage(pageSize, pageNo,null);
	}

	@Override
	public long getAllCount(Humanres example) {
		String hql="select count(h) from Humanres h where h.identnum not in (select e.identnum from Emp e where e.worktype <> '离职')";
		//List<Object> param=new ArrayList<Object>();
		//hql=addCondition(hql,example,param);
		long count=(Long) humresDao.uniqueResult(hql);
		//System.out.println(count+"----count");
		return count;
	}
	
	/**
	 * 在查询中插入条件
	 * 使用到的方法：findEmpByPage
	 * @param hql
	 * @param example
	 * @param condList
	 * @return
	 */
	private String addCondition(String hql,Emp example,List<Object> condList){
		if(example!=null){
			if(example.getEname()!=null&&!example.getEname().equals("")){
				hql+=" and e.ename like ?";
				condList.add("%"+example.getEname()+"%");
			}
			if(example.getEmpid()!=null){
				hql+=" and e.empid = ?";
				condList.add(example.getEmpid());
			}
			if(example.getHiredate()!=null){
				hql+=" and e.hiredate = ?";
				condList.add(example.getHiredate());
			}
			if(example.getBirthdate()!=null){
				hql+=" and e.birthdate = ?";
				condList.add(example.getBirthdate());
			}
			if(example.getDept()!=null&&example.getDept().getDname()!=null&&!example.getDept().getDname().equals("")){
				hql+=" and e.dept.deptid = ?";
				condList.add(example.getDept().getDeptid());
			}
			if(example.getJob()!=null&&example.getJob().getJname()!=null&&!example.getJob().getJname().equals("")){
				hql+=" and e.job.jobid = ?";
				condList.add(example.getJob().getJobid());
			}
			if(example.getWorktype()!=null&&!example.getWorktype().equals("")){
				hql+=" and e.worktype = ?";
				condList.add(example.getWorktype());
			}
		}
		return hql;
	}

}
