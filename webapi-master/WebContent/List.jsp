<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/base_style.css">
		<link rel="stylesheet" href="css/main.css">
		<title>スガサーチ</title>
	</head>
	<body>
		<header>
			<div>
				<!-- ヘッダー -->
				<ul class="menu">
					<li>お腹がすきましたね <c:out value="${account}"></c:out> さん</li>
					<li>
						<form action="List2" method="post">
							<input name="action" type="submit" value="戻る" />
							<input name="action" type="submit" value="お気に入り" />
							<input name="action" type="submit" value="ログアウト" formaction="./Logout" />
						</form>
					</li>
				</ul>
			</div>
		
			<!-- タイトル -->
			<h1 class="headline">
				<a>Sugasearch</a>
				<img src="./images/アイコン改2.png">
			</h1>

			<!-- 検索ボックス -->
			
			
		</header>
		<!-- ここから検索結果の表示 -->
		<div class="fav-sort">
				<form action="List2" method="post">
					<select name="order">
						<option value="4">おすすめ順</option>
						<option value="1">かな順</option>
						<option value="2">ジャンル順</option>	
					</select>
					<input name = "action" type ="submit" value="ソート条件" />
				</form>
				</div>
		<div class="outer">
			<div class = "inner">
				<!-- 現在表示されている検索結果のステータス -->
				<c:if test="${!empty status}">
					<div class="status">
					<div class="status-color"></div>
						<p>${status}</p>
					</div>
				</c:if>
				${error}
				<!-- 店舗リストをループして、各店舗情報を表示 -->
				<c:forEach var="obj" items="${shopList}" varStatus="status">
					<div class="inputbox">
							<!-- 店舗のロゴ画像 -->
							<div class="img">
								<img src="${obj.logoImage}"/>
							</div>
							<!-- お気に入りボタン -->
							<div class="favorite">
								<c:set var="fav_css" value="nofav_icon" />
								<c:if test="${obj.favorite}">
									<c:set var="fav_css" value="fav_icon" />
								</c:if>
								<iframe src="favorite.jsp?id=${obj.id}&favorite=${obj.favorite}&css=${fav_css}">

								</iframe>
							</div>
							
							<!-- ここから店舗の情報 -->
							<div class="logo">
								<!-- 店舗の名前 -->
								<a href="${obj.shopUrl}" target=”_blank”><c:out value="${obj.name}"/></a><br />
								<!-- キャッチコピー -->
								<p class="catch"><c:out value="${obj.shopCatch}"/></p>
							</div>
							<div class="detail">
								<!-- 住所 -->
								<p class="tag">住所</p><c:out value="${obj.address}"/><br />
								<!-- ジャンル -->
								<p class="tag">ジャンル</p><c:out value="${obj.genre}"/><br />
								<!-- 指示4  ここに上記を参考に店舗情報を追加 -->
							</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>