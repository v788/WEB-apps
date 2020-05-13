package salary.bean;

import java.io.Serializable;

import javax.servlet.annotation.WebServlet;

@WebServlet("/laborcostInfo")
public class LaborcostInfo implements Serializable {
	//従業員の出勤・日給記録を一時的に保持(DB:labor_cost)
		private String id;
		private String date;
		private String name_id;
		private String hourly;
		private String begin;
		private String finish;
		private String rest;
		private String late;
		private String total_work;
		private String overTimeWork;
		private String attend;
		private int thisMonth;

		public int getThisMonth() {
			return thisMonth;
		}
		public void setThisMonth(int thisMonth) {
			this.thisMonth = thisMonth;
		}
		public String getAttend() {
			return attend;
		}
		public void setAttend(String attend) {
			this.attend = attend;
		}
		public String getOverTimeWork() {
			return overTimeWork;
		}
		public void setOverTimeWork(String overTimeWork) {
			this.overTimeWork = overTimeWork;
		}
		private String fare;
		private String total_cost;
		//name_idを実際のnameと関連図けて表示
		private String name;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getName_id() {
			return name_id;
		}
		public void setName_id(String name_id) {
			this.name_id = name_id;
		}
		public String getHourly() {
			return hourly;
		}
		public void setHourly(String hourly) {
			this.hourly = hourly;
		}
		public String getBegin() {
			return begin;
		}
		public void setBegin(String begin) {
			this.begin = begin;
		}
		public String getFinish() {
			return finish;
		}
		public void setFinish(String finish) {
			this.finish = finish;
		}
		public String getRest() {
			return rest;
		}
		public void setRest(String rest) {
			this.rest = rest;
		}
		public String getLate() {
			return late;
		}
		public void setLate(String late) {
			this.late = late;
		}
		public String getTotal_work() {
			return total_work;
		}
		public void setTotal_work(String total_work) {
			this.total_work = total_work;
		}
		public String getFare() {
			return fare;
		}
		public void setFare(String fare) {
			this.fare = fare;
		}
		public String getTotal_cost() {
			return total_cost;
		}
		public void setTotal_cost(String total_cost) {
			this.total_cost = total_cost;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

}
