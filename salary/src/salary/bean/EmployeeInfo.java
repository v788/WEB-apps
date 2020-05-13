package salary.bean;

import java.io.Serializable;

public class EmployeeInfo implements Serializable {


	//アルバイト従業員の氏名、時給、交通費
	private String no;
	private String name;
	private String hourlywage;
	private String carfare;
	private String sphourly;
	private String midnight;
	private String holiday;
	private String overtime;


	public String getMidnight() {
		return midnight;
	}
	public void setMidnight(String midnight) {
		this.midnight = midnight;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getSphourly() {
		return sphourly;
	}
	public void setSphourly(String sphourly) {
		this.sphourly = sphourly;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHourlywage() {
		return hourlywage;
	}
	public void setHourlywage(String hourlywage) {
		this.hourlywage = hourlywage;
	}
	public String getCarfare() {
		return carfare;
	}
	public void setCarfare(String carfare) {
		this.carfare = carfare;
	}
}
