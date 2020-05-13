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
@WebServlet(name = "EmployeeFindAllServlet", urlPatterns = "/employeefindall")

public class EmployeeFindAllServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException {

	try {
			DataAccessBean dab = new DataAccessBean();

			Collection<EmployeeInfo> employeeInfoList = dab.findallemployeeInfo();
			Collection<ExtrasettingInfo> extrasettingInfoList = dab.extrasettingInfo();
			req.setAttribute("employeeInfoList", employeeInfoList);
			req.setAttribute("extra", extrasettingInfoList);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/employeelist.jsp");
			rd.forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
		}

	}

}