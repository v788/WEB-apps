package labor_cost;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salary.bean.DataAccessBean;
import salary.bean.LaborcostInfo;
import salary.bean.RepositoryServlet;
@SuppressWarnings("serial")
@WebServlet(name = "SalaryCalcServlet",urlPatterns = "/salarycalc")
public class SalaryCalcServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
										throws ServletException, IOException {


			try {
				//メソッドを置いてあるサーブレットのインスタンスを作成
				RepositoryServlet rps = new RepositoryServlet();

				//laborcost_regist.jspの人件費入力フォームの各データをdoGetで取得
				String date = request.getParameter("date");
				String hourly_ = request.getParameter("hourly");
				String name_id = request.getParameter("name_id");
				String begin = request.getParameter("begin");
				String finish = request.getParameter("finish");
				String rest = request.getParameter("rest");
				String hourlywage = request.getParameter("hourlywage");
				String sphourly = request.getParameter("sphourly");
				String car_fare = request.getParameter("carfare");
				String mid_night = request.getParameter("midnight");
				String holiday = request.getParameter("holiday");
				String over_time = request.getParameter("overtime");
				String attend_ = request.getParameter("attend");

				int attended = Integer.parseInt(attend_);
				int hourly = Integer.parseInt(hourly_);
				int carfare = Integer.parseInt(car_fare);
				float overtime = Float.parseFloat(over_time);
				float midnight = Float.parseFloat(mid_night);
				/**入力された時間データから残業代や深夜勤務を含めた時給の算出を行います。
				int pay			   :  時給
				float attending	   :  休日出勤割増の有無
				int workTime       :  1日の実労働時間
				int dayTime_work;  :  日中実労働時間(分)
				int nightTime_work ;  深夜実労働時間(分)
				String OverTimeWork:  残業時間(時換算)
				float basicPay     :  基本給
				int overPay        :  残業給(日中分)
				float overtime     :  残業時間の割増率
				int carfare        :  日額交通費
				*/
				//時給の選択(基本時給もしくは特別時給)
				int pay;
				if (hourly == 1) {

					pay = Integer.parseInt(hourlywage);
				} else {
					pay = Integer.parseInt(sphourly);
				}

				//出勤区分の選択(通常出勤もしくは休日出勤)
				float attending;
				if (attended == 1) {
					attending = 1;
				} else {
					attending = Float.parseFloat(holiday);
				}
				//入力された出勤・退勤・休憩時間textデータをLocalTimeに変換
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime beginTime = LocalTime.parse(begin, dtf);
				LocalTime finishTime = LocalTime.parse(finish, dtf);
				LocalTime restTime = LocalTime.parse(rest, dtf);

				//日中時間帯と深夜時間帯,日付変更じこくの境界設定
				LocalTime midTime1 = LocalTime.of(23, 59);
				LocalTime midTime2 = LocalTime.of(00, 00);
				LocalTime nightTime = LocalTime.of(22, 00);
				LocalTime regularTime = LocalTime.of(05, 00);

				//休憩時間をint型にキャストして分単位に変換
				int hour = restTime.getHour();
				int minute = restTime.getMinute();
				int restTime_min = (hour * (int) 60) + minute;

				/*1日の実労働時間(勤務時間ー休憩時間)分単位で算出
				   24時を跨ぐ場合をif文で選択
				   int stay : 一日の会社滞在時間
				   int workTime : 一日の実労働時間*/
				int stay;
				if (beginTime.isAfter(finishTime)) {
					stay = rps.TimeToMinute(beginTime, midTime1)
							+ rps.TimeToMinute(midTime2, finishTime) + 1;
				} else {
					stay = rps.TimeToMinute(beginTime, finishTime);
				}
				int workTime = stay - restTime_min;

				//日中労働時間(5:00～22:00)の算出
				int day_stay = 0; //日中会社滞在時間(分)

				//日付を跨いでない場合
				if (beginTime.isBefore(finishTime)) { //深夜～深夜の場合
					if (beginTime.isAfter(midTime2) && beginTime.isBefore(regularTime)) {
						if (finishTime.isAfter(midTime2) && finishTime.isBefore(regularTime)) {
							day_stay = 0;
						} else if (finishTime.isAfter(regularTime) && finishTime.isBefore(nightTime)) { //深夜～日中の場合
							day_stay = rps.TimeToMinute(regularTime, finishTime);
						} else { //深夜～日中～深夜の場合
							day_stay = 480;
						}
					}
					if (beginTime.isAfter(regularTime) && beginTime.isBefore(nightTime)) { //日中～日中の場合
						if (finishTime.isAfter(regularTime) && finishTime.isBefore(nightTime)) {
							day_stay = rps.TimeToMinute(beginTime, finishTime);
						} else {
							day_stay = rps.TimeToMinute(beginTime, nightTime); //日中～深夜の場合
						}
					}
				} else {
					day_stay = 0; //深夜～深夜の場合
				}

				//日付をまたいだ場合
				if (beginTime.isAfter(finishTime)) {
					if (beginTime.isAfter(midTime2) && beginTime.isBefore(regularTime)) { //深夜から深夜まで日中通しの場合
						day_stay = 480;
					} else if (beginTime.isAfter(regularTime) && beginTime.isBefore(nightTime)) { //日中勤務時間帯から始まった場合
						if (finishTime.isBefore(regularTime)) { //日中～深夜の場合
							day_stay = rps.TimeToMinute(beginTime, nightTime);
						} else if (finishTime.isAfter(regularTime) && finishTime.isBefore(nightTime)) {
							int dayTime1 = rps.TimeToMinute(beginTime, nightTime); //日中～深夜～日中の場合
							int dayTime2 = rps.TimeToMinute(regularTime, finishTime);
							day_stay = dayTime1 + dayTime2;
						}
					} else if (beginTime.isAfter(nightTime)) { //深夜～深夜の場合
						if (finishTime.isBefore(regularTime)) {
							day_stay = 0;
						} else if (finishTime.isAfter(regularTime) && finishTime.isBefore(nightTime)) {
							day_stay = rps.TimeToMinute(regularTime, finishTime); //深夜～日中の場合
						} else if (finishTime.isAfter(nightTime)) { //深夜～日中～深夜の場合
							day_stay = 480;
						}
					}
				}

				//深夜労働時間の算出(一日の滞在時間1 - 日中滞在時間)
				int night_stay;//深夜滞在時間
				night_stay = stay - day_stay;

				//休憩時間を差し引いた日中、深夜の実労働時間の算出
				//(休憩時間は日中滞在時間から優先して差し引きます)
				int dayTime_work; //日中実労働時間(分)
				int nightTime_work; //深夜実労働時間(分)
				if (restTime_min == 0) {
					dayTime_work = day_stay;
					nightTime_work = night_stay;
				} else if (restTime_min < day_stay) {
					dayTime_work = day_stay - restTime_min;
					nightTime_work = night_stay;
				} else if (day_stay == 0) {
					nightTime_work = night_stay - restTime_min;
					dayTime_work = 0;
				} else {
					nightTime_work = night_stay - (day_stay - restTime_min);
					dayTime_work = 0;
				}

				/*日当計算メソッド
				 float overtimt     	残業割増率*/
				double basicPay = 0; //基本給(8時間までの賃金)
				double nightPay = 0; //深夜給(22:00～5:00分)
				double overPay = 0; //残業給(8時間超過分の賃金)
				int salaryPay = 0; //日当(1日当たりの総額　※交通費含む)

				/*残業発生時間のパターンに応じた残業代の算出
				 * 残業在りの場合と無しの場合)*/
				if (workTime > 480) {
					//残業が日中だけの場合
					if (nightTime_work == 0) {
						basicPay = (480 / 60) * pay;
						overPay = ((dayTime_work - 480) * 100 / 60) * pay / 100 * overtime;
						//残業が日中と深夜時間帯にまたがる場合
					} else if (dayTime_work != 0 && nightTime_work != 0) {
						basicPay = (dayTime_work * pay) / 60;
						overPay = (workTime - 480) * 100 / 60 * pay * midnight * overtime / 100;
						nightPay = ((((nightTime_work - (workTime - 480)) * 100 / 60) * pay) / 100) * midnight;
						//残業が深夜のみの場合
					} else if (dayTime_work < 480) {
						basicPay = (dayTime_work * pay) / 60;
						nightPay = ((480 - dayTime_work) * pay / 60) * midnight;
						overPay = ((workTime - 480) / 60) * pay * midnight * overtime;
					}
				} else { //残業無し
					basicPay = ((dayTime_work * 100 / 60) * pay) / 100;//基本給の計算
					nightPay = ((nightTime_work * 100 * pay / 60) * midnight) / 100;//深夜給の計算
					overPay = 0;
				}

				//日当総額の算出(基本給+深夜給+残業給に休日出勤の場合は休日割増をかけて交通費を加算)
				salaryPay = (int) ((basicPay + nightPay + overPay) * attending + carfare);


				System.out.println(workTime + "  " + dayTime_work + " " + nightTime_work + "  " + day_stay);
				System.out.println(basicPay + "  " + nightPay + " " + overPay + "  " + attend_);


				//上記時間表記(String)変換メソッドを使って勤務時間、勤務時間、深夜勤務時間を変換
				String WorkTime = rps.MinutesFormat(workTime);
				String night_TimeWork = rps.MinutesFormat(nightTime_work);

				//残業時間があれば算出する
				String OverTimeWork = "0:00";
				if (workTime >= 480) {
					OverTimeWork = rps.MinutesFormat((workTime - 480));
				}
				//LaborCostInfoオブジェクトに入力値を詰め込む為、各値をStringに形成
				String Pay = String.valueOf(pay);
				String SalaryPay = String.valueOf(salaryPay);
				String BeginTime = String.valueOf(beginTime);
				String FinishTime = String.valueOf(finishTime);
				String Rest = String.valueOf(rest);

				LaborcostInfo laborcostInfo = new LaborcostInfo();
				laborcostInfo.setDate(date);
				laborcostInfo.setName_id(name_id);
				laborcostInfo.setHourly(Pay);
				laborcostInfo.setBegin(BeginTime);
				laborcostInfo.setFinish(FinishTime);
				laborcostInfo.setRest(Rest);
				laborcostInfo.setLate(night_TimeWork);
				laborcostInfo.setTotal_work(WorkTime);
				laborcostInfo.setOverTimeWork(OverTimeWork);
				laborcostInfo.setFare(car_fare);
				laborcostInfo.setTotal_cost(SalaryPay);
				laborcostInfo.setAttend(attend_);

				DataAccessBean dab = new DataAccessBean();
				dab.registLaborCostInfo(laborcostInfo);
				response.sendRedirect(request.getContextPath() + "/findtodaylaborcost");

			} catch (SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			}


	}

}
