package model;

import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Operation {

	public boolean loginProc(String userId, String password, jakarta.servlet.http.HttpSession session) {

		// ログイン認証
		boolean result = authenticate(userId, password);

		if (result) {
			// 店舗データの作成セッションに格納
			Store store = makeStore();
			session.setAttribute("store", store);
			
			// カート情報の作成（userId設定・商品リストは空）セッションに格納
			Cart cart = new Cart(userId, new ArrayList<Product>());
			session.setAttribute("cart", cart);
		}

		return result;
	}

	private boolean authenticate(String userId, String password) {

		boolean result = password.equals("pass");

		return result;
	}
	
	private Store makeStore() {

		// 店舗情報作成
		Store store = new Store("PC販売", new ArrayList<Product>());
		
		// 商品追加
		store.add(new Product("A110", "無線マウス", 2000));
		store.add(new Product("A120", "薄型キーボード", 3600));
		store.add(new Product("A130", "Webカメラ", 3900));
		store.add(new Product("A140", "トラックボールマウス", 2900));
		store.add(new Product("A150", "USB接続HDD（外付け）", 9800));
		store.add(new Product("A160", "2m電源タップ５口", 1900));
		store.add(new Product("A170", "USB接続マイク", 3500));
		store.add(new Product("A180", "小型ディスプレイ", 11000));
		store.add(new Product("A190", "LED照明", 4200));
		store.add(new Product("A200", "骨伝導イヤホン", 7800));
		
		return store;
	}
	
	public void logoutProc(HttpSession session) {

		session.invalidate();
		
	}

	public void addProd(int idx, HttpSession session) {
		
		// 店舗情報・カート情報の取得（セッションより）
		Store store = (Store) session.getAttribute("store");
		Cart cart = (Cart) session.getAttribute("cart");

		if ((store != null) && (cart != null)) {
			// カートに指定の商品を追加
			cart.add(store.getListProd().get(idx));

			// セッションに再度格納
			session.setAttribute("cart", cart);
		}

	}

	public void removeProd(int idx, HttpSession session) {
	
		// カート内商品情報の取得（セッションより）
		Cart cart = (Cart) session.getAttribute("cart");
	
		if (cart != null) {
			// カートから指定の商品を削除
			cart.remove(idx);

			// セッションに書き戻す
			session.setAttribute("cart", cart);
		}
			
	}
	
	public void pay(HttpSession session) {

		// カート内商品情報の取得
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart != null) {
			// セッションに格納（精算済みデータ）
			session.setAttribute("pay", cart);

			// カート情報の新規作成⇒セッションに格納
			Cart newCart = new Cart(cart.getUserId(), new ArrayList<Product>());
			session.setAttribute("cart", newCart);
		}

	}

}
