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
	 *  更新部门信息
	 */
	public void alterDept(Dept dept) {
		deptDao.update(dept);
	}

	/**
	 * 删除部门
	 * @throws Exception 
	 */
	public void deleteDept(long deptid) throws Exception {
		String hql="select count(e.empid) from Emp e where e.dept.deptid = "
					+deptid+" and e.worktype != '离职'";
		long row =(Long) empDao.uniqueResult(hql);
		if(row <= 0){
			Dept dept=deptDao.findById(deptid);
			deptDao.delete(dept);
		}else{
			throw new Exception("部门下仍然有员工");
		}
	}

	/**
	 * 根据id查询部门实体
	 */
	public Dept findDeptById(long deptId) {
		return deptDao.findById(deptId);
	}

	/**
	 * 带条件查询分页查询
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
		// 排序    降序用desc ,升序用asc;
		hql += " order by d.deptid asc";
		return deptDao.queryListObjectAllForPage(pageSize, pageNo, hql, params.toArray());
	}
	/**
	 *  这个是不分页有条件查询
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
		// 排序
		hql += " order by d.deptid asc";
		return (List<Dept>) deptDao.uniqueResult(hql, params.toArray());
		
	}
	*/
	/**
	 *  查询部门所有记录
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
	 * 非分页查询
	 */
	@Override
	public List<Dept> getAllDept() {
		List<Dept> list = deptDao.getListByHQL("from Dept");
		return list;
	}

}
