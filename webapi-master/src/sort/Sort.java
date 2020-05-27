package sort;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.Constans;
import dto.Shop;
import service.ShopAPI;

/**
 * Servlet implementation class Sort
 */
@WebServlet("/Sort")
public class Sort extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public Sort() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得
				HttpSession session = request.getSession();
				ArrayList<String> favoriteShopList = (ArrayList<String>)session.getAttribute("favoriteList");
				// 検索キーワード
				String prmKeyword = request.getParameter("keyword");
				String prmGenre = request.getParameter("genre");
				String prmOrder = request.getParameter("order");
				System.out.println(prmGenre);
				// リクエストに検索キーワードを設定
				request.setAttribute("search", prmKeyword);
				//request.setAttribute("")
				// 店舗リストを新規取得
				ArrayList<Shop> shopList = ShopAPI.keywordSearch(prmKeyword, prmGenre, prmOrder);
				
				if(shopList != null) {
					// 店舗リスト内の各店舗クラスにお気に入りフラグを設定
					shopList.forEach(x -> x.setFavorite(favoriteShopList.contains(x.getId())));
					// 検索した店舗のリストをリクエストへ設定
					request.setAttribute("shopList", shopList);
					
					// ステータスを表示
					String status = String.format(Constans.STATUS_SEARCH, prmKeyword);
					request.setAttribute("status", status);
				}
				else request.setAttribute("error", Constans.MSG_GET_SHOP_ERROR); // エラーメッセージをリクエストに設定

				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/main.jsp");
				dis.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得
				HttpSession session = request.getSession();
		
		//ソートの処理を追加

		
		doGet(request, response);
	}

}
