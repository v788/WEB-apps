package labor_cost;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salary.bean.DataAccessBean;

@WebServlet(name = "DeleteLaborCostServlet", urlPatterns = "/deletelaborcost")
public class DeleteLaborCostServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				//従業員の出勤履歴IDを受けっとってデータベースから削除するメソッドの呼び出し
			try {
				String id = request.getParameter("id");
				DataAccessBean dab = new DataAccessBean();
				dab.deleteLaborCostInfo(id);
				response.sendRedirect(request.getContextPath() + "/findtodaylaborcost");

			}catch(SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			}
		}

	}
