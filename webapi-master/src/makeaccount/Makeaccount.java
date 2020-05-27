package makeaccount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Makeaccount
 */
@WebServlet("/Makeaccount")
public class Makeaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Makeaccount() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		
		
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/Makeaccount.jsp");
		dis.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String accountId = request.getParameter("loginid");
		String passWord = request.getParameter("password");
		
		//アカウントID・パスワードをデータベースへの接続処理を行う
		
		
		//doGet(request, response);
	}

}
