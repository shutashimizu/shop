package model;

import java.util.List;

public class Store {

	private String name;

	private List<Product> listProd;

	public Store(String name, List<Product> listProd) {
		this.name = name;
		this.listProd = listProd;
	}

	public List<Product> getListProd() {
		return listProd;
	}

	public String getName() {
		return name;
	}

	public void add(Product prod) {
		listProd.add(prod);
	}

	//特定の商品を除去する
	public void remove(int index) {
		listProd.remove(index);
	}
	
	//全ての商品を除去する
	public void clear() {
		listProd.clear();
	}

}
