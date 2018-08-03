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
	 * ����Ա��ת��
	 */
	public String regularEmp(Testmanager testManager) {
		//1���ж��Ƿ��Ѿ�����ʽԱ��
		if(testManager.getTestinfo().getEmp().getWorktype().equals("��ʽԱ��")){
			return "isEmp";
		}
		// 2����������ݱ��浽���ù����
		testManagerDao.save(testManager);
		// 3��������Ϣ��ʱ���Ϊ����ʱ��
		testManager.getTestinfo().setEnddate(new Date());
		
		// 4��������Ա����Ա�����͸ĳ���ʽԱ��
		
		testManager.getTestinfo().getEmp().setWorktype("��ʽԱ��");
		return "successEmp";
	}

	/**
	 * ����Ա������
	 */
	public String delayEmp(Testmanager testManager) {
		// 1�����浽�����ڹ����
		testManagerDao.save(testManager);
		// 2������������Ϣ����ҳ���������Ϣ����
		Testinfo testInfo = testManager.getTestinfo();
		// 3:�������ڶ���Ľ���ʱ���ӳ�һ����
		Calendar calendar = Calendar.getInstance();
		Date date2 = testInfo.getEnddate();
		calendar.setTime(date2);
		// System.out.println("û����֮ǰ�����ڣ�"+date2);
		calendar.add(Calendar.MONTH, 1);// �������ӳ�һ����
		Date date1 = calendar.getTime();
		testInfo.setEnddate(date1);
		// 4�����µ���������Ϣ��
		testInfoDao.update(testInfo);
		return "successDelay";
	}

	/**
	 * ����Ա������¼��
	 */
	public String fireEmp(Testmanager testManager) {
		//1�����ж��Ƿ���ְ
		if(testManager.getTestinfo().getEmp().getWorktype().equals("��ְ")){
			return "isNotEmp";
		}
		// 2������������ݵ������ڹ����
		testManagerDao.save(testManager);
		// 3����������Ϣ��������ý���ʱ������Ϊϵͳʱ��
		testManager.getTestinfo().setEnddate(new Date());
		// 4����������Ա����Ϣ������ý�������
		testInfoDao.update(testManager.getTestinfo());
		// 5:��Ա����ְ
		Leavejob leaveJob01 = new Leavejob();
		leaveJob01.setEmpid(testManager.getTestinfo().getEmp().getEmpid());
		leavaEmp(leaveJob01);
		return "success";
	}

	/**
	 * Ա����ְ
	 * 
	 * @param emp
	 */
	private void leavaEmp(Leavejob leavejob) {
		// ������˲ſ���û�ж�Ӧ���ݣ����뵽�˲ſ���
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
		emp.setWorktype("��ְ");
		empDao.update(emp);
		leavejob.setEmpid(emp.getEmpid());
		leavejob.setLeavedate(new Date());
		leavejob.setEmp(emp);
		leavejob.setType("�����ڲ�ͨ��");
		leavDao.save(leavejob);
		//accDao.delete(accDao.findById(emp.getEmpid()));
	}

	/**
	 * �ж���ת��,����,δͨ�� 1.'ת��' 2.'����' 3.'����¼��'
	 * 
	 * @param testManager
	 */
	public String updateTestInfo(Testmanager testManager) {
		if (testManager.getResult() == 1) {// ת��
			return regularEmp(testManager);
		} else if (testManager.getResult() == 2) {// ����
			return delayEmp(testManager);
		} else if (testManager.getResult() == 3) {// ����¼��
			return fireEmp(testManager);
		} else
		return "false";
	}
}
