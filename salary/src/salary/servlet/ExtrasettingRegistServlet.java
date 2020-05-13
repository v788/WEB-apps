package salary.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salary.bean.DataAccessBean;
import salary.bean.ExtrasettingInfo;


@WebServlet("/extrasettingregist")
public class ExtrasettingRegistServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {

		try {
			String midnight = request.getParameter("ex_mid");
			String holiday = request.getParameter("ex_holiday");
			String overtime = request.getParameter("ex_overtime");

			ExtrasettingInfo extrasettingInfo = new ExtrasettingInfo();
			extrasettingInfo.setMidnight(midnight);
			extrasettingInfo.setHoliday(holiday);
			extrasettingInfo.setOvertime(overtime);

			DataAccessBean dab = new DataAccessBean();
			dab.updateExtrasettingInfo(extrasettingInfo);
			response.sendRedirect(request.getContextPath() + "/employeefindall");
		}catch(SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
