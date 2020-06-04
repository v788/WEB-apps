package login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import salary.bean.DataAccessBean;
import salary.bean.LoginInfo;
@SuppressWarnings("serial")
@WebServlet(name = "CheckLogin",urlPatterns = "/checklogin")

public class CheckLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//login.jspから受け取ったログイン情報に適合しない文字がないかチェックする
		try {

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			//ログイン用emailの入力チェック(nullと文字数チェック)
			if(email == null || email.length() < 5 ) {
				request.setAttribute("message", "入力内容を確認してください");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
				rd.forward(request, response);
				return;
			}
			//script等の不正な文字チェック
			if(email.contains("<") || email.contains(">") || email.contains("&") || email.contains("#")|| email.contains("'")) {
				request.setAttribute("message", "< > & # 'は入力できません");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
				rd.forward(request, response);
				return;
			}
			//ログイン用passwordの入力チェック(nullと文字数チェック)
			if(email == null || email.length() < 3 ) {
				request.setAttribute("message", "入力内容を確認してください");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
				rd.forward(request, response);
				return;
			}
			//script等の不正な文字チェック
			if(password.contains("<") || password.contains(">") || password.contains("&") || password.contains("#")|| password.contains("'")) {
				request.setAttribute("message", "< > & # 'は入力できません");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
				rd.forward(request, response);
				return;
			}
			//ログイン情報をLoginInfoオブジェクトに詰め込みます
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setEmail(email);
			loginInfo.setPassword(password);

			//ログイン情報をデータベースに問い合わせてユーザーネームが帰ってきたらホーム画面へ
			//帰ってきたユーザーネームをセッションスコープに格納してheader.jspで表示
			DataAccessBean dab = new DataAccessBean();

			//DataAccessBeanに入力されたemailとpasswordをリストで送る
			String user_Name = dab.loginCheckInfo(loginInfo);

			//データベースと照合して結果がnullならログインできません
			if(user_Name == null) {
				request.setAttribute("message", "入力内容を確認してください");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
				rd.forward(request, response);
				return;
			}

			//帰ってきたユーザー名をセッションスコープに格納してホーム画面へ遷移
			 HttpSession session = request.getSession(true);
			 session.setAttribute("user_Name", user_Name);
			response.sendRedirect(request.getContextPath() + "/findtodaylaborcost");

			//SQLの例外はログインページへ遷移します
			}catch(SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			}
		}
	}


