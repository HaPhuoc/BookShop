package phuoc.bookstore.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import phuoc.bookstore.entity.CartItem;

@Service
@SessionScope
public class ShoppingCartImpl implements ShoppingCartService{
	
	Map<Integer, CartItem> maps=new HashMap<Integer, CartItem>();
	
	@Override
	public void add(CartItem item) {
		
		CartItem cartItem=maps.get(item.getIdProduct());
		if(cartItem==null) {
			maps.put(item.getIdProduct(), item);
		}else {
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}
		
	}
	@Override
	public void remove(int id) {
		maps.remove(id);
		
	}
	@Override
	public CartItem update(int proId,int qty) {
		CartItem cartItem=maps.get(proId);
		cartItem.setQuantity(qty);
		return cartItem;
	}
	
	@Override
	public void clear() {
		maps.clear();
	}
	
	@Override
	public Collection<CartItem> getAlCartItems(){
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}

	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item->item.getQuantity()*item.getPrice()).sum();
	}
	
	
	
}
