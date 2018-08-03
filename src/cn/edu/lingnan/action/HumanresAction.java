package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Humanres;
import cn.edu.lingnan.service.HumanresService;

public class HumanresAction extends BaseAction {

	private Humanres humres;    //查询到人才库的一条具体信息
	private long humid;
	private List<Humanres> humresList;
	
	@Autowired
	private HumanresService humresService;
	
	//分页需要的变量
	public static final int PAGESIZE = 10;
	private int pageNo;   //当前页
	private int next;     //下一页
	private int prev;     //前一页
	private int allPages; //所有页
	
	/**
	 * 人才库个人信息
	 * author 赵桂钊
	 * @return
	 */
	public String toHumanresDetial(){
		humres=humresService.findHumanresByID(humid);
		return SUCCESS;
	}
	
	/**
	 * 跳转到人才库列表
	 * @return
	 */
	public String toHumanList(){
		setPageNo();
		//System.out.println(allPages+"\t"+pageNo);
		humresList=humresService.queryHumanresByPage(PAGESIZE, pageNo);
		//System.out.println(humresList.size());
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
		int countPage=(int) humresService.getAllCount(null);
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

	public Humanres getHumres() {
		return humres;
	}

	
	public void setHumres(Humanres humres) {
		this.humres = humres;
	}

	public long getHumid() {
		return humid;
	}

	public void setHumid(long humid) {
		this.humid = humid;
	}

	public List<Humanres> getHumresList() {
		return humresList;
	}

	public void setHumresList(List<Humanres> humresList) {
		this.humresList = humresList;
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
	
}
