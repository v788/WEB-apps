package salary.bean;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;

import javax.servlet.http.HttpServlet;

public class RepositoryServlet extends HttpServlet {


	//時間差(LocalTime型)を分単位(int型)に変換するメソッド
		public int TimeToMinute(LocalTime a, LocalTime b) {
			Duration duration = Duration.between(a, b);
			int x = (int) duration.toMinutes();
			return x;
		}

		//分換算の引数 min を一般的な時刻表記(00:00)(String)に変換するメソッド
		public String MinutesFormat(int min) {
			int a = Math.abs(min / 60);
			int b = Math.abs(min % 60);
			String x = a + ":" + new DecimalFormat("00").format(b);
			return x;
		}

}
