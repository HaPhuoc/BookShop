package fptu.phuochg.library.service;

import java.util.Collection;

import fptu.phuochg.library.model.CartItemDto;

public interface ShoppingCartService {

	double getAmount();

	int getCount();

	Collection<CartItemDto> getAllItems();

	void clear();

	CartItemDto update(Long bookId, int quantity);

	void remove(Long id);

	void add(CartItemDto item);

}
