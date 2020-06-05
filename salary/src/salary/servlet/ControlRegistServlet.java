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
@WebServlet(name = "ControlRegistServlet", urlPatterns = { "/controlregist" })
public class ControlRegistServlet extends HttpServlet {

			//このServletで従業員登録画面（employee_regist.jap）へ修正用の従業員、割増率情報を送ります

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			DataAccessBean dab = new DataAccessBean();

			//データベースから現在の従業員のリスト情報
			Collection<EmployeeInfo> employeeInfoList = dab.findallemployeeInfo();

			//データベースから現在の割増率リスト
			Collection<ExtrasettingInfo> extrasettingInfoList = dab.extrasettingInfo();

			//リクエストスコープへ格納
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
