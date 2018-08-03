package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Humanres;
import cn.edu.lingnan.service.HumanresService;

public class HumanresAction extends BaseAction {

	private Humanres humres;    //��ѯ���˲ſ��һ��������Ϣ
	private long humid;
	private List<Humanres> humresList;
	
	@Autowired
	private HumanresService humresService;
	
	//��ҳ��Ҫ�ı���
	public static final int PAGESIZE = 10;
	private int pageNo;   //��ǰҳ
	private int next;     //��һҳ
	private int prev;     //ǰһҳ
	private int allPages; //����ҳ
	
	/**
	 * �˲ſ������Ϣ
	 * author �Թ���
	 * @return
	 */
	public String toHumanresDetial(){
		humres=humresService.findHumanresByID(humid);
		return SUCCESS;
	}
	
	/**
	 * ��ת���˲ſ��б�
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
	 * ���õ�ǰҳ��
	 * author �Թ���
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
