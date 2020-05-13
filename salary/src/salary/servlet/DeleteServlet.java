package salary.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salary.bean.DataAccessBean;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("delete");

			DataAccessBean dab = new DataAccessBean();
			dab.deleteEmployeeInfo(id);
			response.sendRedirect(request.getContextPath() + "/controlregist.jsp");
				}catch(SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}

}
