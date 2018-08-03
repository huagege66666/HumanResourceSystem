package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.lingnan.dao.AccountDao;
import cn.edu.lingnan.dao.DeptDao;
import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.dao.HumanresDao;
import cn.edu.lingnan.dao.JobDao;
import cn.edu.lingnan.dao.LeavejobDao;
import cn.edu.lingnan.dao.TestinfoDao;
import cn.edu.lingnan.pojo.Account;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Humanres;
import cn.edu.lingnan.pojo.Leavejob;
import cn.edu.lingnan.pojo.Testinfo;
import cn.edu.lingnan.service.EmpService;
import cn.edu.lingnan.utils.DateCal;

@Transactional
@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private HumanresDao resDao;
	
	@Autowired
	private AccountDao accDao;
	
	@Autowired
	private LeavejobDao leavDao;
	
	@Autowired
	private TestinfoDao testInfoDao;
	
	
	@Override
	public void alterEmpInfo(Emp example) {
		empDao.merge(example);
	}
	
	@Override
	public void alterEmpInfo(long id,Emp example) {
		Emp emp=empDao.findById(id);
		emp.setSex(example.getSex());
		emp.setBirthdate(example.getBirthdate());
		emp.setIdentnum(example.getIdentnum());
		emp.setPolstatus(example.getPolstatus());
		emp.setNation(example.getNation());
		emp.setNavplace(example.getNavplace());
		emp.setTel(example.getTel());
		emp.setEmail(example.getEmail());
		emp.setHeight(example.getHeight());
		emp.setBooldtype(example.getBooldtype());
		emp.setMarry(example.getMarry());
		emp.setRecschool(example.getRecschool());
		emp.setSchool(example.getSchool());
		emp.setGraddate(example.getGraddate());
		empDao.merge(emp);
	}

	@Override
	public void insertEmp(Emp emp,long deptid,long jobid,long resid) {
		Humanres humres=null;
		if(resid!=0){
			humres=resDao.findById(resid);
		}
		emp.setHiredate(new Date());
		emp.setHumanres(humres);
		emp.setDept(deptDao.findById(deptid));
		emp.setJob(jobDao.findById(jobid));
		long id=(Long) empDao.save(emp);
		//创建对应员工账号
		Account account=new Account();
		account.setEmpid(id);
		Emp newemp=empDao.findById(id);
		account.setEmp(newemp);
		account.setPassword("123456");
		account.setPriv(false);
		accDao.save(account);
		if(newemp.getWorktype().equals("临时员工")){
			Testinfo testInfo=new Testinfo();
			testInfo.setEmp(newemp);
			Date date=new Date();
			testInfo.setStartdate(date);
			testInfo.setEnddate(DateCal.afterMonth(3));
			testInfoDao.save(testInfo);
		}
	}

	@Override
	public List<Emp> findEmpByPage(int pageSize, int pageNo,Emp example) {
		String hql="from Emp e where 1 = 1 ";
		List<Object> param=new ArrayList<Object>();
		hql=addCondition(hql,example,param);
		hql+=" order by e.worktype desc,e.empid asc";
		return empDao.queryListObjectAllForPage(pageSize, pageNo, hql, param.toArray());
	}

	@Override
	public Emp findEmpById(long empid) {
		return empDao.findById(empid);
	}
	
	public Leavejob findLeavejobByEmpId(long empid){
		return leavDao.findById(empid);
	}

	@Override
	public void fireEmp(Leavejob leavejob) {
		//如果在人才库中没有对应数据，插入到人才库中
		Emp emp=empDao.findById(leavejob.getEmpid());
		Humanres res;
		if(emp.getHumanres()==null){
			res=new Humanres();
			res.setEname(emp.getEname());
			res.setSex(emp.getSex());
			res.setBirthdate(emp.getBirthdate());
			res.setIdentnum(emp.getIdentnum());
			res.setPolstatus(emp.getPolstatus());
			res.setNation(emp.getNation());
			res.setNavplace(emp.getNavplace());
			res.setTel(emp.getTel());
			res.setEmail(emp.getEmail());
			res.setHeight(emp.getHeight());
			res.setBooldtype(emp.getBooldtype());
			res.setMarry(emp.getMarry());
			res.setRecschool(emp.getRecschool());
			res.setSchool(emp.getSchool());
			res.setMajor(emp.getMajor());
			res.setGraddate(emp.getGraddate());
			long resid=(Long) resDao.save(res);    //获取到id信息
			emp.setHumanres(resDao.findById(resid));  //设置人才库信息
		}
		else{
			//如果存在，则更新数据库
			res=emp.getHumanres();
			res.setEname(emp.getEname());
			res.setSex(emp.getSex());
			res.setBirthdate(emp.getBirthdate());
			res.setIdentnum(emp.getIdentnum());
			res.setPolstatus(emp.getPolstatus());
			res.setNation(emp.getNation());
			res.setNavplace(emp.getNavplace());
			res.setTel(emp.getTel());
			res.setEmail(emp.getEmail());
			res.setHeight(emp.getHeight());
			res.setBooldtype(emp.getBooldtype());
			res.setMarry(emp.getMarry());
			res.setRecschool(emp.getRecschool());
			res.setSchool(emp.getSchool());
			res.setMajor(emp.getMajor());
			res.setGraddate(emp.getGraddate());
			resDao.update(res);
		}
		emp.setWorktype("离职");
		empDao.update(emp);
		leavejob.setLeavedate(new Date());
		leavejob.setEmp(emp);
		leavDao.save(leavejob);
		try{
			//accDao.delete(accDao.findById(emp.getEmpid()));  //删除员工登录账号
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("账号为空");
		}
	}
	
	@Override
	public long getAllCount(Emp example) {
		String hql="select count(e) from Emp e where 1 = 1 ";
		List<Object> param=new ArrayList<Object>();
		hql=addCondition(hql,example,param);
		long count=(Long) empDao.uniqueResult(hql,param.toArray());
		System.out.println("count="+count);
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
