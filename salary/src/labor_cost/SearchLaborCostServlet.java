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

		try {
			String search_month = request.getParameter("search_month");
			String search_name = request.getParameter("search_name");


			LaborcostInfo laborcostInfo = new LaborcostInfo();
			laborcostInfo.setDate(search_month);
			laborcostInfo.setName_id(search_name);

			System.out.println(search_month + "  " + search_name);

			DataAccessBean dab = new DataAccessBean();
			Collection<LaborcostInfo> searchLaborCostInfoList = dab.searchLaborCostInfo(laborcostInfo);
			int personalCost = dab.personalMonthLaborCostInfo(search_name);
			request.setAttribute("searchCostInfoList", searchLaborCostInfoList);
			request.setAttribute("personalCost", personalCost);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/laborcost_search.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

}
