package list2;

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
 * Servlet implementation class List2
 */
@WebServlet("/List2")
public class List2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public List2 () {
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得
				HttpSession session = request.getSession();
				
				// お気に入りのリスト取得
				ArrayList<String> favoriteShopList = (ArrayList<String>)session.getAttribute("favoriteList");

				ArrayList<Shop> shopList = ShopAPI.shopIdSearch(favoriteShopList);
				if(shopList != null) {
					 //店舗リスト内の各店舗クラスにお気に入りフラグを設定
					shopList.forEach(x -> x.setFavorite(favoriteShopList.contains(x.getId())));
					// 検索した店舗のリストをリクエストへ設定
					request.setAttribute("shopList", shopList);
					
					// ステータスを表示
					String status = Constans.STATUS_FAVORITE;//わからん
					request.setAttribute("status", status);
				}
				else request.setAttribute("error", Constans.MSG_GET_SHOP_ERROR); // エラーメッセージをリクエストに設定
				
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/List.jsp");
				dis.forward(request, response);

			}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションを取得
			HttpSession session = request.getSession();
			// 押されたボタンを取得
			String action = request.getParameter("action");
			String PrmOrder = request.getParameter("order");
			ArrayList<String> favoriteShopList = (ArrayList<String>)session.getAttribute("favoriteList");
			
			
			if(action.equals("戻る")) {
				response.sendRedirect("Main");
			}else if(action.equals("お気に入り")) {
				
				doGet(request, response);
			
			}else if (action.equals("ソート条件")) {
				ArrayList<Shop> shopList = ShopAPI.shopIdSearch(favoriteShopList, PrmOrder);
				if(shopList != null) {
					 //店舗リスト内の各店舗クラスにお気に入りフラグを設定
					shopList.forEach(x -> x.setFavorite(favoriteShopList.contains(x.getId())));
					// 検索した店舗のリストをリクエストへ設定
					request.setAttribute("shopList", shopList);
					
					// ステータスを表示
					String status = Constans.STATUS_FAVORITE;//わからん
					request.setAttribute("status", status);
				}
				else request.setAttribute("error", Constans.MSG_GET_SHOP_ERROR); // エラーメッセージをリクエストに設定
				
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/List.jsp");
				dis.forward(request, response);
				
			}
	}

}
