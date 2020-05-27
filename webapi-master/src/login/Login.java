package login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 指示1: 以下のコードを削除し、ログイン画面を表示する処理を追加する。
		
		HttpSession session = request.getSession(true);
				
		
		

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");
		dis.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 指示1: ここでPOSTされたIDとパスワードでログイン処理
		
		HttpSession session = request.getSession(false);
		
		
		String accountId = request.getParameter("loginid");
		String passWord = request.getParameter("password");
		int ok = 0;
		try {
			ok = service.AccountDBAccess.Select(accountId, passWord);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//入力がなかった場合
		if((accountId + passWord).equals("")) {
			
			//ログイン不可
			request.setAttribute("message", "IDとパスワードを入力してください");
			doGet(request, response);
			
		//入力があった場合、ID・パスワードを登録	
		}else if(ok ==1){
			
				session.setAttribute("account", accountId);

				request.getParameter("submit");
				
				response.sendRedirect("Main");
			
		//入力が間違っていた時
		}else {
				request.setAttribute("message", "Idまたはパスワードが違います");
				doGet(request,response);
				return;
			
		}
		
		
		
	
		}
}
