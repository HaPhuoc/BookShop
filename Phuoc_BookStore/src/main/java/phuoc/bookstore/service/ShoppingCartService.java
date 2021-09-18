package phuoc.bookstore.service;

import java.util.Collection;

import phuoc.bookstore.entity.CartItem;

public interface ShoppingCartService {
	double getAmount();
	int getCount();
	Collection<CartItem> getAlCartItems();
	void clear();
	CartItem update(int proId, int qty);
	void remove(int id);
	void add(CartItem item);
}
