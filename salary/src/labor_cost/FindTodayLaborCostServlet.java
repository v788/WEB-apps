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

			int thisMonthCost = dab.thisMonthLaborCostInfo();
			Collection<EmployeeInfo> employeeInfoList = dab.findallLaborCostInfo();
			Collection<ExtrasettingInfo> extrasettingInfoList = dab.extrasettingInfo();
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
