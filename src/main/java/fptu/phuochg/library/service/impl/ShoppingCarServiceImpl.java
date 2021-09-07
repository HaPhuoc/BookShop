package fptu.phuochg.library.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import fptu.phuochg.library.model.CartItemDto;
import fptu.phuochg.library.service.ShoppingCartService;


@Service
@SessionScope
public class ShoppingCarServiceImpl implements ShoppingCartService {
	Map<Long, CartItemDto> maps = new HashMap<>();
	@Override
	public void add (CartItemDto item) {
		CartItemDto cartItemDto = maps.get(item.getBookId());
		if(cartItemDto == null) {
			maps.put(item.getBookId(),item);
		}else {
		cartItemDto.setQuantity(cartItemDto.getQuantity() +1);
		}		
	}
	@Override
	public void remove(Long id) {
		maps.remove(id);
	}
	@Override
	public CartItemDto update(Long bookId,int quantity) {
		CartItemDto cartItemDto = maps.get(bookId);
		cartItemDto.setQuantity(quantity);
		return cartItemDto;
	}
	@Override
	public void clear() {
		maps.clear();
	}
	@Override
	public Collection<CartItemDto> getAllItems(){
		return maps.values();
	}
	@Override
	public int getCount() {
		return maps.values().size();
	}
	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
	}
}
