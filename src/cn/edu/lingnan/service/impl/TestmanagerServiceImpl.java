package cn.edu.lingnan.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.lingnan.dao.DeptDao;
import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.dao.HumanresDao;
import cn.edu.lingnan.dao.JobDao;
import cn.edu.lingnan.dao.LeavejobDao;
import cn.edu.lingnan.dao.TestinfoDao;
import cn.edu.lingnan.dao.TestmanagerDao;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Humanres;
import cn.edu.lingnan.pojo.Leavejob;
import cn.edu.lingnan.pojo.Testinfo;
import cn.edu.lingnan.pojo.Testmanager;
import cn.edu.lingnan.service.TestmanagerService;

@Transactional
@Service
public class TestmanagerServiceImpl implements TestmanagerService {
	

	@Autowired
	private TestinfoDao testInfoDao;

	@Autowired
	private TestmanagerDao testManagerDao;

	@Autowired
	private EmpDao empDao;

	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private HumanresDao resDao;
	
	@Autowired
	private LeavejobDao leavDao;
	
	
	/**
	 * 试用员工转正
	 */
	public String regularEmp(Testmanager testManager) {
		//1：判断是否已经是正式员工
		if(testManager.getTestinfo().getEmp().getWorktype().equals("正式员工")){
			return "isEmp";
		}
		// 2：否则把数据保存到试用管理表
		testManagerDao.save(testManager);
		// 3：试用信息表时间改为现在时间
		testManager.getTestinfo().setEnddate(new Date());
		
		// 4：把试用员工的员工类型改成正式员工
		
		testManager.getTestinfo().getEmp().setWorktype("正式员工");
		return "successEmp";
	}

	/**
	 * 试用员工延期
	 */
	public String delayEmp(Testmanager testManager) {
		// 1：保存到试用期管理表
		testManagerDao.save(testManager);
		// 2：根据试用信息编号找出试用期信息对象
		Testinfo testInfo = testManager.getTestinfo();
		// 3:将试用期对象的结束时间延长一个月
		Calendar calendar = Calendar.getInstance();
		Date date2 = testInfo.getEnddate();
		calendar.setTime(date2);
		// System.out.println("没延期之前的日期："+date2);
		calendar.add(Calendar.MONTH, 1);// 试用期延长一个月
		Date date1 = calendar.getTime();
		testInfo.setEnddate(date1);
		// 4：更新到试用期信息表
		testInfoDao.update(testInfo);
		return "successDelay";
	}

	/**
	 * 试用员工不予录用
	 */
	public String fireEmp(Testmanager testManager) {
		//1：先判断是否离职
		if(testManager.getTestinfo().getEmp().getWorktype().equals("离职")){
			return "isNotEmp";
		}
		// 2：否则插入数据到试用期管理表
		testManagerDao.save(testManager);
		// 3：将试用信息对象的试用结束时间设置为系统时间
		testManager.getTestinfo().setEnddate(new Date());
		// 4：更新试用员工信息表的试用结束日期
		testInfoDao.update(testManager.getTestinfo());
		// 5:将员工离职
		Leavejob leaveJob01 = new Leavejob();
		leaveJob01.setEmpid(testManager.getTestinfo().getEmp().getEmpid());
		leavaEmp(leaveJob01);
		return "success";
	}

	/**
	 * 员工离职
	 * 
	 * @param emp
	 */
	private void leavaEmp(Leavejob leavejob) {
		// 如果在人才库中没有对应数据，插入到人才库中
		Emp emp = empDao.findById(leavejob.getEmpid());
		if (emp.getHumanres() == null) {
			Humanres res = new Humanres();
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
			long resid = (Long) resDao.save(res);
			emp.setHumanres(resDao.findById(resid));
		}
		emp.setWorktype("离职");
		empDao.update(emp);
		leavejob.setEmpid(emp.getEmpid());
		leavejob.setLeavedate(new Date());
		leavejob.setEmp(emp);
		leavejob.setType("试用期不通过");
		leavDao.save(leavejob);
		//accDao.delete(accDao.findById(emp.getEmpid()));
	}

	/**
	 * 判断是转正,延期,未通过 1.'转正' 2.'延期' 3.'不予录用'
	 * 
	 * @param testManager
	 */
	public String updateTestInfo(Testmanager testManager) {
		if (testManager.getResult() == 1) {// 转正
			return regularEmp(testManager);
		} else if (testManager.getResult() == 2) {// 延期
			return delayEmp(testManager);
		} else if (testManager.getResult() == 3) {// 不予录用
			return fireEmp(testManager);
		} else
		return "false";
	}
}
