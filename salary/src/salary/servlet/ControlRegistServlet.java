package salary.servlet;

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
@SuppressWarnings("serial")
@WebServlet(name = "ControlRegist.jspServlet", urlPatterns = { "/controlregist" })
public class ControlRegistServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
;			DataAccessBean dab = new DataAccessBean();

			Collection<EmployeeInfo> employeeInfoList = dab.findallemployeeInfo();
			Collection<ExtrasettingInfo> extrasettingInfoList = dab.extrasettingInfo();
			request.setAttribute("employeeInfoList", employeeInfoList);
			request.setAttribute("extra", extrasettingInfoList);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/employee_regist.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
