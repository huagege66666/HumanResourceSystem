package cn.edu.lingnan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lingnan.dao.DeptDao;
import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.pojo.Dept;
import cn.edu.lingnan.service.DeptService;

@Transactional
@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private EmpDao empDao;
	

	public void insertDept(Dept dept) {
		deptDao.save(dept);
	}

	/**
	 *  ���²�����Ϣ
	 */
	public void alterDept(Dept dept) {
		deptDao.update(dept);
	}

	/**
	 * ɾ������
	 * @throws Exception 
	 */
	public void deleteDept(long deptid) throws Exception {
		String hql="select count(e.empid) from Emp e where e.dept.deptid = "
					+deptid+" and e.worktype != '��ְ'";
		long row =(Long) empDao.uniqueResult(hql);
		if(row <= 0){
			Dept dept=deptDao.findById(deptid);
			deptDao.delete(dept);
		}else{
			throw new Exception("��������Ȼ��Ա��");
		}
	}

	/**
	 * ����id��ѯ����ʵ��
	 */
	public Dept findDeptById(long deptId) {
		return deptDao.findById(deptId);
	}

	/**
	 * ��������ѯ��ҳ��ѯ
	 */
	public List<Dept> queryAllDeptByExample(int pageNo, int pageSize, Dept deptExample) {
		List<Object> params = new ArrayList<Object>();
		String hql = "select d from Dept d where 1=1 ";
		if(deptExample != null){
			if (deptExample.getDname() != null && !"".equals(deptExample.getDname())) {
				hql += " and d.dname like ?";
				params.add("%"+deptExample.getDname()+"%");
			}
		}
		// ����    ������desc ,������asc;
		hql += " order by d.deptid asc";
		return deptDao.queryListObjectAllForPage(pageSize, pageNo, hql, params.toArray());
	}
	/**
	 *  ����ǲ���ҳ��������ѯ
	 * @return
	 
	public List<Dept> findDeptByName(Dept deptExample){
		List<Object> params = new ArrayList<Object>();
		String hql = "select d from Dept d where 1=1 ";
		if (deptExample != null) {
			if (deptExample.getDname() != null && !"".equals(deptExample.getDname())) {
				hql += " and d.dname like ?";
				params.add("%"+deptExample.getDname()+"%");
			}
		}
		// ����
		hql += " order by d.deptid asc";
		return (List<Dept>) deptDao.uniqueResult(hql, params.toArray());
		
	}
	*/
	/**
	 *  ��ѯ�������м�¼
	 */
	@Override
	public long getAllCounts(Dept deptExample) {
		List<Object> params = new ArrayList<Object>();
		String hql = "select count(d) from Dept d where 1=1 ";
		if (deptExample != null) {
			if (deptExample.getDname() != null && !"".equals(deptExample.getDname())) {
				hql += " and d.dname like ?";
				params.add("%"+deptExample.getDname()+"%");
			}
		}
		return (Long) deptDao.uniqueResult(hql, params.toArray());
	}

	/**
	 * �Ƿ�ҳ��ѯ
	 */
	@Override
	public List<Dept> getAllDept() {
		List<Dept> list = deptDao.getListByHQL("from Dept");
		return list;
	}

}
