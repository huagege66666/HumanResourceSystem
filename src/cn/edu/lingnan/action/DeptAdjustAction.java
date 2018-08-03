package cn.edu.lingnan.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.dao.DeptadjustDao;
import cn.edu.lingnan.pojo.Dept;
import cn.edu.lingnan.pojo.Deptadjust;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.service.DeptAdjustService;
import cn.edu.lingnan.service.DeptService;
import cn.edu.lingnan.service.EmpService;

public class DeptAdjustAction extends BaseAction{

	@Autowired
	private DeptAdjustService deptAdjustService;
	
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private EmpService empService;
	
	//接受的调动员工部门的请求对象
	private Deptadjust deptAdjust;
	
	//所有已调动部门的员工集合
	private List<Emp> listAdjustEmp;
	private List<Deptadjust> listAdjustdept;
	
	//接收要调动的部门，然后找出此部门
	private Long deptId;
	
	//接受的员工id
	private Long empId;
	
	private Deptadjust example;
	//分页需要的变量
	public static final int PAGESIZE = 10;
	private int pageNo;   //当前页
	private int next;     //下一页
	private int prev;     //前一页
	private int allPages; //所有页
	
	private int toid;
	private int pageNo1;
	
	/**
	 * 员工部门调动
	 * @return
	 */
	public String deptAdjust(){
		/*deptAdjust = deptAdjustDao.findById(1);
		DeptAdjust deptAdjust02 = new DeptAdjust();
		deptAdjust02.setAdjustdate(new Date());
		deptAdjust02.setEmp(deptAdjust.getEmp());
		Dept dept = new Dept();
		dept.setDeptid(4L);
		deptAdjust02.setDept(dept);
		deptAdjust02.setType(deptAdjust.getType());*/
		toid=2;
		Emp emp = empService.findEmpById(empId);
		Dept dept = deptService.findDeptById(deptId);
		deptAdjust.setDept(dept);
		deptAdjust.setEmp(emp);
		deptAdjust.setAdjustdate(new Date());
		deptAdjustService.deptAdjust(deptAdjust);
		return SUCCESS;
	}
	
	/**
	 * 已调动部门员工信息查询
	 * @return
	 */
	public String getAllAdjustEmp(){
		listAdjustEmp = deptAdjustService.getAllAdjustEmp();
		//System.out.println(listAdjustEmp);
		return SUCCESS;
	}
	
	public String getDeptadjustByPager(){
		setPageNo();
		listAdjustdept=deptAdjustService.getAllAdjustInfo(PAGESIZE, pageNo, example);
		return SUCCESS;
	}
	
	/**
	 * 设置当前页码
	 * author 赵桂钊
	 */
	private void setPageNo(){
		if(pageNo==0){
			pageNo=1;
		}
		int countPage=(int) deptAdjustService.getAllCount(example);
		if(countPage%PAGESIZE==0){
			allPages=countPage/PAGESIZE;
		}
		else{
			allPages=(countPage/PAGESIZE)+1;
		}
		next=pageNo;
		prev=pageNo;
		if(allPages==1){
			next=1;
			prev=1;
			return;
		}
		if(pageNo<=1){
			next++;
			prev=1;
		}
		else{
			if(pageNo>=allPages){
				prev--;
				next=allPages;
			}
			else{
				next++;
				prev--;
			}
		}
	}
	

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public Deptadjust getDeptAdjust() {
		return deptAdjust;
	}


	public void setDeptAdjust(Deptadjust deptAdjust) {
		this.deptAdjust = deptAdjust;
	}

	public List<Emp> getListAdjustEmp() {
		return listAdjustEmp;
	}

	public void setListAdjustEmp(List<Emp> listAdjustEmp) {
		this.listAdjustEmp = listAdjustEmp;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public List<Deptadjust> getListAdjustdept() {
		return listAdjustdept;
	}

	public void setListAdjustdept(List<Deptadjust> listAdjustdept) {
		this.listAdjustdept = listAdjustdept;
	}

	public Deptadjust getExample() {
		return example;
	}

	public void setExample(Deptadjust example) {
		this.example = example;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getAllPages() {
		return allPages;
	}

	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}

	public int getPageNo1() {
		return pageNo1;
	}

	public void setPageNo1(int pageNo1) {
		this.pageNo1 = pageNo1;
	}
	
}
