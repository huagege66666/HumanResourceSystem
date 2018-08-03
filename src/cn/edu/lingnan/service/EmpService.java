package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Leavejob;

public interface EmpService {

	/**
	 * �޸�Ա����Ϣ
	 * @param emp
	 */
	void alterEmpInfo(Emp example);
	void alterEmpInfo(long id,Emp example);
	
	/**
	 * ����Emp��
	 * @param emp
	 * @param deptid
	 * @param jobid
	 */
	void insertEmp(Emp emp,long deptid,long jobid,long resid);
	/**
	 * ��ҳ����emp��
	 * @param pagesize
	 * @return
	 */
	List<Emp> findEmpByPage(int pageSize,int pageNo,Emp example);
	
	/**
	 * ����Ա��Idc��ѯ��ӦԱ��
	 * @param empid
	 * @return
	 */
	Emp findEmpById(long empid);
	
	/**
	 * ����Ա������ְ��Ϣ
	 * @param empid
	 * @return
	 */
	Leavejob findLeavejobByEmpId(long empid);
	
	/**
	 * ���Ա��
	 * @param emp
	 */
	void fireEmp(Leavejob leavejob);
	
	/**
	 * ��ȡָ������Ա��������
	 * @param example
	 * @return
	 */
	long getAllCount(Emp example);
	
}
