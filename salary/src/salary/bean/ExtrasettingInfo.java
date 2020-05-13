package salary.bean;

import java.io.Serializable;

import javax.servlet.annotation.WebServlet;

@WebServlet("/extrasetting")
public class ExtrasettingInfo implements Serializable {

	//割増率の設定
	private String id;
	private String midnight;
	private String holiday;
	private String overtime;

	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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



}
