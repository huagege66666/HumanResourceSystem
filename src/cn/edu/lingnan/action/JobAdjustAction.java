package cn.edu.lingnan.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Jobadjust;
import cn.edu.lingnan.service.EmpService;
import cn.edu.lingnan.service.JobAdjustService;
import cn.edu.lingnan.service.JobService;

public class JobAdjustAction extends BaseAction {
	
	@Autowired
	private JobAdjustService jobAdjustService;
	@Autowired
	private EmpService empService;
	@Autowired
	private JobService jobService;
	
	//接收过来的岗位对象
	private Jobadjust jobAdjust;
	//接收过来的empid
	private Integer empid;
	//传出去的查找已经调动过岗位的数据
	private List<Jobadjust> list;
	//接收过来要调动的jobid
	private Long jobId;
	
	//跳转到指定的分页
	private int toid;
	
	private Jobadjust example;
	//分页需要的变量
	public static final int PAGESIZE = 10;
	private int pageNo;   //当前页
	private int next;     //下一页
	private int prev;     //前一页
	private int allPages; //所有页
	private int pageNo1;
	
	/**
	 * 员工岗位调动
	 * @return
	 */
	public String adjustEmpJob(){
		
		Emp emp = empService.findEmpById(empid);
		jobAdjust.setEmp(emp);
		jobAdjust.setAdjustdate(new Date());
		toid=2;
		boolean flag = jobAdjustService.adjustEmpJob(jobAdjust);
		if(flag){
			return SUCCESS;
		}
		return ERROR;
	}
	
	/**
	 * 查询所有已经调动过岗位的员工
	 * @return
	 */
	public String getAllAdjustEmp(){
		list = jobAdjustService.allAdjustEmp();
		//System.out.println(list);
		return SUCCESS;
	}
	
	
	public String getJobadjustByPager(){
		setPageNo();
		list=jobAdjustService.getAllAdjustInfo(PAGESIZE, pageNo, example);
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
		int countPage=(int) jobAdjustService.getAllCount(example);
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
	

	public Jobadjust getJobAdjust() {
		return jobAdjust;
	}

	public void setJobAdjust(Jobadjust jobAdjust) {
		this.jobAdjust = jobAdjust;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public List<Jobadjust> getList() {
		return list;
	}

	public void setList(List<Jobadjust> list) {
		this.list = list;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Jobadjust getExample() {
		return example;
	}

	public void setExample(Jobadjust example) {
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

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public int getPageNo1() {
		return pageNo1;
	}

	public void setPageNo1(int pageNo1) {
		this.pageNo1 = pageNo1;
	}
	
}
