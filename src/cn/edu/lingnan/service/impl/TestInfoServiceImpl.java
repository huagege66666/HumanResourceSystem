package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.dao.TestinfoDao;
import cn.edu.lingnan.dao.TestmanagerDao;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Testinfo;
import cn.edu.lingnan.pojo.Testmanager;
import cn.edu.lingnan.service.TestInfoService;

@Transactional
@Service
public class TestInfoServiceImpl implements TestInfoService {

	@Autowired
	private TestinfoDao testInfoDao;
	@Autowired
	private TestmanagerDao testManagerDao;
	@Autowired
	private EmpDao empDao;
	
	/**
	 * 查询所有已经转正的员工
	 */
	public List<Emp> getAllRegularEmp() {
		List<Emp> empList = new ArrayList<Emp>();
		List<Testmanager> list = testManagerDao.getListByHQL("from TestManager where result = ?",1);
		for (int i = 0; i < list.size(); i++) {
			
			empList.add(list.get(i).getTestinfo().getEmp());
		}
		return empList;
	}
	
	/**
	 * 查询某一条员工试用信息数据
	 */
	public Testinfo getTestInfoById(Long id){
		Testinfo testInfo = testInfoDao.findById(id);
		//System.out.println(id+"--"+testInfo);
		return testInfo;
	}

	/**
	 * 找出所有员工试用信息
	 * @return
	 */
	public List<Testinfo> getAllTestInfo() {
		List<Testinfo> list = testInfoDao.getListByHQL("from TestInfo");
		List<Testinfo> list01 = new ArrayList<Testinfo>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getEmp().getWorktype().equals("临时员工")){
				list01.add(list.get(i));
			}
		}
		return list01;
	}
	
	/**
	 * 分页查找试用员工信息
	 * @param pageSize
	 * @param pageNo
	 * @param example
	 * @return
	 */
	public List<Testinfo> getTestInfoByPage(int pageSize, int pageNo,Testinfo example) {
		String hql="select t from Testinfo t ,Emp e ,Dept d, Job j where 1 = 1 and t.emp.empid = e.empid and e.dept.deptid = d.deptid and e.job.jobid = j.jobid ";
		List<Object> param=new ArrayList<Object>();
		hql=addCondition(hql,example,param);
		List<Testinfo> list = testInfoDao.queryListObjectAllForPage(pageSize, pageNo, hql, param.toArray());
		//只输出是临时员工
		List<Testinfo> list01 = new ArrayList<Testinfo>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getEmp().getWorktype().equals("临时员工")){
				list01.add(list.get(i));
			}
		}
		return list01;
	}
	
	/**
	 * 有条件查询试用员工记录数
	 * @param example
	 * @return
	 */
	public long getTestInfoAllCount(Testinfo example) {
		//select count(t) from TestInfo t join fetch t.emp e  join fetch e.dept d  join fetch e.job j  where 1 = 1 and e.empid = 1
		String hql="select count(t.id) from Testinfo t ,Emp e ,Dept d, Job j where 1 = 1 and t.emp.empid = e.empid and e.dept.deptid = d.deptid and e.job.jobid = j.jobid ";
		List<Object> param=new ArrayList<Object>();
		hql=addCondition(hql,example,param);
		long count=(Long) testInfoDao.uniqueResult(hql,param.toArray());
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
	private String addCondition(String hql,Testinfo example,List<Object> condList){
		if(example!=null){
			if(example.getEmp().getEmpid()!=null){
				hql+=" and e.empid = ?";
				condList.add(example.getEmp().getEmpid());
				System.out.println(example.getEmp().getEmpid());
			}
			if(example.getEmp().getEname()!=null&&!example.getEmp().getEname().equals("")){
				hql+=" and e.ename like ?";
				condList.add("%"+example.getEmp().getEname()+"%");
				System.out.println("%"+example.getEmp().getEname()+"%");
			}
			if(example.getEmp().getDept().getDname()!=null&&!example.getEmp().getDept().getDname().equals("")){
				hql+=" and d.dname like ?";
				condList.add("%"+example.getEmp().getDept().getDname()+"%");
				System.out.println("%"+example.getEmp().getDept().getDname()+"%");
			}
			if(example.getEmp().getJob().getJname()!=null&&!example.getEmp().getJob().getJname().equals("")){
				hql+=" and j.jname like ?";
				condList.add("%"+example.getEmp().getJob().getJname()+"%");
				System.out.println("%"+example.getEmp().getJob().getJname()+"%");
			}
			if(example.getStartdate()!=null){
				hql+=" and t.startdate = ?";
				condList.add(example.getStartdate());
				System.out.println(example.getStartdate());
				
			}
			if(example.getEnddate()!=null){
				hql+=" and t.enddate = ?";
				condList.add(example.getEnddate());
				System.out.println(example.getEnddate());
			}
		}
		hql+=" and e.worktype = '临时员工' ";
		hql+=" order by e.empid";
		return hql;
	}

}
