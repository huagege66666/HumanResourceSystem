package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lingnan.dao.DeptadjustDao;
import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.pojo.Deptadjust;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.service.DeptAdjustService;

@Service
@Transactional
public class DeptAdjustServiceImpl implements DeptAdjustService{

	@Autowired
	private DeptadjustDao deptadjustDao;
	
	@Autowired
	private EmpDao empDao;
	
	/**
	 * 员工部门调动
	 */
	public void deptAdjust(Deptadjust Deptadjust) {
		//1：插入部门调动数据到部门调动管理表
		deptadjustDao.save(Deptadjust);
		//2：更新员工的部门
		Emp emp=Deptadjust.getEmp();
		emp.setDept(Deptadjust.getDept());
		empDao.update(emp);
	}
	
	/**
	 * 已调动部门员工信息查询
	 */
	public List<Emp> getAllAdjustEmp(){
		List<Deptadjust> list= deptadjustDao.getListByHQL("from Deptadjust");
		List<Emp> listEmp = new ArrayList<Emp>();
		for (int i = 0; i < list.size(); i++) {
			listEmp.add(list.get(i).getEmp());
		}
		return listEmp;
	}

	@Override
	public List<Deptadjust> getAllAdjustInfo(int pageSize, int pageNo,Deptadjust example) {
		String hql="from Deptadjust d where 1 = 1 ";
		List<Object> param=new ArrayList<Object>();
		//hql=addCondition(hql,example,param);
		hql+=" order by d.adjustid desc";
		return deptadjustDao.queryListObjectAllForPage(pageSize, pageNo, hql, param.toArray());
	}
	
	@Override
	public long getAllCount(Deptadjust example) {
		String hql="select count(d) from Deptadjust d where 1 = 1 ";
		List<Object> param=new ArrayList<Object>();
		//hql=addCondition(hql,example,param);
		long count=(Long) deptadjustDao.uniqueResult(hql);
		//System.out.println(count);
		return count;
	}
	
	/**
	 * 在查询中插入条件
	 * @param hql
	 * @param example
	 * @param condList
	 * @return
	 */
	private String addCondition(String hql,Deptadjust example,List<Object> condList){
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
