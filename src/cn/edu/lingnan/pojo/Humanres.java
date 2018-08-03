package cn.edu.lingnan.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Humanres entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HUMANRES", schema = "SSH")
public class Humanres implements java.io.Serializable {

	// Fields

	private Long resid;
	private String ename;
	private String sex;
	private Date birthdate;
	private String identnum;
	private String polstatus;
	private String nation;
	private String navplace;
	private String tel;
	private String email;
	private Double height;
	private String booldtype;
	private String marry;
	private String recschool;
	private String school;
	private String major;
	private Date graddate;
	private Set<Emp> emps = new HashSet<Emp>(0);

	// Constructors

	/** default constructor */
	public Humanres() {
	}

	/** minimal constructor */
	public Humanres(String ename, String sex, Date birthdate, String identnum) {
		this.ename = ename;
		this.sex = sex;
		this.birthdate = birthdate;
		this.identnum = identnum;
	}

	/** full constructor */
	public Humanres(String ename, String sex, Date birthdate, String identnum,
			String polstatus, String nation, String navplace, String tel,
			String email, Double height, String booldtype, String marry,
			String recschool, String school, String major, Date graddate,
			Set<Emp> emps) {
		this.ename = ename;
		this.sex = sex;
		this.birthdate = birthdate;
		this.identnum = identnum;
		this.polstatus = polstatus;
		this.nation = nation;
		this.navplace = navplace;
		this.tel = tel;
		this.email = email;
		this.height = height;
		this.booldtype = booldtype;
		this.marry = marry;
		this.recschool = recschool;
		this.school = school;
		this.major = major;
		this.graddate = graddate;
		this.emps = emps;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_humid",allocationSize=1,sequenceName="seq_humid")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_humid")
	@Column(name = "RESID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getResid() {
		return this.resid;
	}

	public void setResid(Long resid) {
		this.resid = resid;
	}

	@Column(name = "ENAME", nullable = false, length = 10)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "SEX", nullable = false, length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", nullable = false, length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "IDENTNUM", nullable = false, length = 18)
	public String getIdentnum() {
		return this.identnum;
	}

	public void setIdentnum(String identnum) {
		this.identnum = identnum;
	}

	@Column(name = "POLSTATUS", length = 10)
	public String getPolstatus() {
		return this.polstatus;
	}

	public void setPolstatus(String polstatus) {
		this.polstatus = polstatus;
	}

	@Column(name = "NATION", length = 10)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "NAVPLACE", length = 10)
	public String getNavplace() {
		return this.navplace;
	}

	public void setNavplace(String navplace) {
		this.navplace = navplace;
	}

	@Column(name = "TEL", length = 11)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL", length = 15)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "HEIGHT", precision = 5)
	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Column(name = "BOOLDTYPE", length = 5)
	public String getBooldtype() {
		return this.booldtype;
	}

	public void setBooldtype(String booldtype) {
		this.booldtype = booldtype;
	}

	@Column(name = "MARRY", length = 4)
	public String getMarry() {
		return this.marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	@Column(name = "RECSCHOOL", length = 10)
	public String getRecschool() {
		return this.recschool;
	}

	public void setRecschool(String recschool) {
		this.recschool = recschool;
	}

	@Column(name = "SCHOOL", length = 20)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "MAJOR", length = 20)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GRADDATE", length = 7)
	public Date getGraddate() {
		return this.graddate;
	}

	public void setGraddate(Date graddate) {
		this.graddate = graddate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "humanres")
	public Set<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

}