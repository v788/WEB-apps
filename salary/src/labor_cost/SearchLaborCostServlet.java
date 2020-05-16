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
import salary.bean.LaborcostInfo;

@WebServlet(name = "SearchLaborCostServlet", urlPatterns = "/searchlaborcost")
public class SearchLaborCostServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//HOME画面の勤務履歴検索条件を受け取ってBeanの検索メソッドに値を渡す
		try {
			String search_month = request.getParameter("search_month");//勤務月
			String search_name = request.getParameter("search_name");//従業員名


			LaborcostInfo laborcostInfo = new LaborcostInfo();
			laborcostInfo.setDate(search_month);
			laborcostInfo.setName_id(search_name);


			DataAccessBean dab = new DataAccessBean();

			//検索指定月の従業員の検索結果オブジェクト
			Collection<LaborcostInfo> searchLaborCostInfoList = dab.searchLaborCostInfo(laborcostInfo);

			//検索した従業員の月間総人件費オブジェクト
			Collection<LaborcostInfo>personalCost = dab.personalMonthLaborCostInfo(laborcostInfo);
			request.setAttribute("searchCostInfoList", searchLaborCostInfoList);

			//検索結果をリクエストスコープで送信
			request.setAttribute("personalCost", personalCost);
			request.setAttribute("search_name", search_name);
			request.setAttribute("search_month", search_month);


			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/laborcost_search.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

}
