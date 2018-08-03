package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.JobService;

public class JobAction extends BaseAction {
	@Autowired
	private JobService jobService;
	
	private long jobid;
	private Job job;
	private String jname;
	private Integer count;
	private String type;
	private String message;
	
	private List<Job> jobList;
	private List<Job> expJobList;
	
	//分页参数
	private int pageNo;
	private int next;
	private int prev;
	private int allPages;
	private int allCounts;
	
	//每页记录数
	private static final int PAGESIZE = 10;
		
	//组合查询条件的封装对象
	private Job jobExample;
	
	/*
	 * method
	 * 
	 * 
	 */
	
	//根据id查找job
	public String findJobById(){
		job = jobService.findJobById(jobid);
		return SUCCESS;
	}
	
	//增加job
	public String addJob(){
		if(jobService.insertJob(job)==false){
			this.message="填写的岗位已存在，请重新填写！";
			return ERROR;
		}
		this.message="岗位添加成功！";
		return SUCCESS;
	}
	//增加job的验证器
	/*
	 * 
	public void validateAddJob(){
		if(job.getJname()==null||"".equals(job.getJname())){
			super.addActionError("岗位名称不能为空！");
			this.message="岗位名称不能为空！";
		}
		if(job.getType()==null||"".equals(job.getType())){
			super.addActionError("岗位类型不能为空！");
			this.message="岗位类型不能为空！";
		}
		if(job.getCount()==null||"".equals(job.getCount())){
			super.addActionError("岗位人数编制不能为空！");
			this.message="岗位人数编制不能为空！";
		}
	}
	*/
	//删除job
	public String deleteJob(){
		job=jobService.findJobById(jobid);
		if(jobService.getJobhumanCount(jobid)>0){
			job=null;
			this.message="该岗位上有员工，删除失败！";
			return ERROR;
		}
		jobService.deleteJob(job);
		this.message="岗位删除成功！";
		return SUCCESS;
	}
	
	//修改job
	public String updateJob(){
		if(job==null){
			return ERROR;
		}
		if(jobService.alterJob(job)==false){
			this.message="该岗位已存在，修改失败！";
			return ERROR;
		}
		this.message="岗位修改成功！";
		return SUCCESS;
	}
	
	//分页条件组合查询
	public String loadJob(){
		if(pageNo==0){
			pageNo=1;
		}
		jobList = jobService.queryJobByPage(pageNo, PAGESIZE, job);
		expJobList=jobService.getAllJobList();
		allCounts = (int)jobService.getAllCounts(job);
		allPages = (allCounts % PAGESIZE ==0)?(allCounts / PAGESIZE )
					:(allCounts / PAGESIZE + 1);

		next = pageNo+1;
		prev = pageNo-1;
		if(next>allPages)
			next--;
		if(prev<1)
			prev++;
		return SUCCESS;
	}
	
	//加载所有岗位
		public String showAllJob(){
			this.jobList = jobService.getAllJobList();
			return SUCCESS;
		}

	
	/*
	 * Get and Set
	 * 
	 * 
	 */
	
		
	public long getJobid() {
		return jobid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public int getAllCounts() {
		return allCounts;
	}

	public void setAllCounts(int allCounts) {
		this.allCounts = allCounts;
	}

	public Job getJobExample() {
		return jobExample;
	}

	public void setJobExample(Job jobExample) {
		this.jobExample = jobExample;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Job> getExpJobList() {
		return expJobList;
	}

	public void setExpJobList(List<Job> expJobList) {
		this.expJobList = expJobList;
	}
}
