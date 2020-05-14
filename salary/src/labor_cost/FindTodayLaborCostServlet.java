package labor_cost;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salary.bean.DataAccessBean;
import salary.bean.EmployeeInfo;
import salary.bean.ExtrasettingInfo;
import salary.bean.LaborcostInfo;

@SuppressWarnings("serial")
@WebServlet(name = "FindTodayLaborCostServlet", urlPatterns = "/findtodaylaborcost")
public class FindTodayLaborCostServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DataAccessBean dab = new DataAccessBean();
			//laborcost_list(従業員出退勤入力画面)へBeanから一覧等リストオブジェクトを取得、送信

			//今月の給料総支払額検索結果のリストオブジェクト
			int thisMonthCost = dab.thisMonthLaborCostInfo();

			//従業員の個人情報リストオブジェクト
			Collection<EmployeeInfo> employeeInfoList = dab.findallLaborCostInfo();

			//時給割増情報のリストオブジェクト
			Collection<ExtrasettingInfo> extrasettingInfoList = dab.extrasettingInfo();

			//今月の従業員の出勤履リストオブジェクト
			Collection<LaborcostInfo> alllaborcostInfoList = dab.attendanceList();

			request.setAttribute("thisMonthCost", thisMonthCost);
			request.setAttribute("employeeInfoList", employeeInfoList);
			request.setAttribute("extrasettingInfoList", extrasettingInfoList);
			request.setAttribute("laborcostInfoList", alllaborcostInfoList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/laborcost_list.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);

		}

	}
}
