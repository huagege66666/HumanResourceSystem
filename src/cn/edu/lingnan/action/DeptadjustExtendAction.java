package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Jobadjust;
import cn.edu.lingnan.service.EmpService;
import cn.edu.lingnan.service.JobAdjustService;
import cn.edu.lingnan.service.JobService;

public class DeptadjustExtendAction extends DeptAdjustAction {

	@Autowired
	private JobAdjustService jobAdjustService;
	@Autowired
	private EmpService empService;
	@Autowired
	private JobService jobService;
	
	//传出去的查找已经调动过岗位的数据
	private List<Jobadjust> list1;
	
	private Jobadjust example1;
	//分页需要的变量
	public static final int PAGESIZE = 10;
	private int pageNo1;   //当前页
	private int next1;     //下一页
	private int prev1;     //前一页
	private int allPages1; //所有页
	
	private int toid;
	
	public void getJobadjustByPager(){
		setPageNo();
		list1=jobAdjustService.getAllAdjustInfo(PAGESIZE, pageNo1, example1);
	}
	
	/**
	 * 设置当前页码
	 * author 赵桂钊
	 */
	private void setPageNo(){
		if(pageNo1==0){
			pageNo1=1;
		}
		int countPage=(int) jobAdjustService.getAllCount(example1);
		if(countPage%PAGESIZE==0){
			allPages1=countPage/PAGESIZE;
		}
		else{
			allPages1=(countPage/PAGESIZE)+1;
		}
		next1=pageNo1;
		prev1=pageNo1;
		if(allPages1==1){
			next1=1;
			prev1=1;
			return;
		}
		if(pageNo1<=1){
			next1++;
			prev1=1;
		}
		else{
			if(pageNo1>=allPages1){
				prev1--;
				next1=allPages1;
			}
			else{
				next1++;
				prev1--;
			}
		}
	}
	
	public String getAllAdjustInfo(){
		super.getDeptadjustByPager();
		getJobadjustByPager();
		return SUCCESS;
	}

	public List<Jobadjust> getList1() {
		return list1;
	}

	public void setList1(List<Jobadjust> list1) {
		this.list1 = list1;
	}

	public Jobadjust getExample1() {
		return example1;
	}

	public void setExample1(Jobadjust example1) {
		this.example1 = example1;
	}

	public int getPageNo1() {
		return pageNo1;
	}

	public void setPageNo1(int pageNo1) {
		this.pageNo1 = pageNo1;
	}

	public int getNext1() {
		return next1;
	}

	public void setNext1(int next1) {
		this.next1 = next1;
	}

	public int getPrev1() {
		return prev1;
	}

	public void setPrev1(int prev1) {
		this.prev1 = prev1;
	}

	public int getAllPages1() {
		return allPages1;
	}

	public void setAllPages1(int allPages1) {
		this.allPages1 = allPages1;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}
	
}
