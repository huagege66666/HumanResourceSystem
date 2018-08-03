package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Dept;

public interface DeptService {

	/**
	 * ��ҳ��ѯ���в���
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	//List<Dept> queryAllDeptByPage(int pageSize,int pageNo);
	List<Dept> queryAllDeptByExample(int pageNo, int pageSize, Dept deptExample);
	
	/**
	 * ��Ӳ���
	 * @param dept
	 */
	void insertDept(Dept dept);
	
	/**
	 * �޸Ĳ���
	 * @param dept
	 */
	void alterDept(Dept dept);
	
	/**
	 * ɾ��ָ������
	 * @param dept
	 */
	void deleteDept(long deptid) throws Exception;
	
	/**
	 * ����Id����ָ������
	 * @param deptId
	 * @return
	 */
	Dept findDeptById(long deptId);
	
	/**
	 * ��ȡ���в���
	 * @return
	 */
	List<Dept> getAllDept();
	
	/**
	 * ������в�������
	 * @param deptExample
	 * @return
	 */
	long getAllCounts(Dept deptExample);
}
