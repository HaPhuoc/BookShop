package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import phuoc.bookstore.dao.OrdersDAO;
import phuoc.bookstore.entity.Order;


@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	OrdersDAO orDAO;

	@Override
	public <S extends Order> S save(S entity) {
		return orDAO.save(entity);
	}

	@Override
	public <S extends Order> Optional<S> findOne(Example<S> example) {
		return orDAO.findOne(example);
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orDAO.findAll(pageable);
	}

	@Override
	public List<Order> findAll() {
		return orDAO.findAll();
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return orDAO.findAll(sort);
	}

	@Override
	public List<Order> findAllById(Iterable<Integer> ids) {
		return orDAO.findAllById(ids);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		return orDAO.saveAll(entities);
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return orDAO.findById(id);
	}

	@Override
	public void flush() {
		orDAO.flush();
	}

	@Override
	public <S extends Order> S saveAndFlush(S entity) {
		return orDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return orDAO.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Order> entities) {
		orDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orDAO.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		orDAO.deleteAllInBatch();
	}

	@Override
	public Order getOne(Integer id) {
		return orDAO.getOne(id);
	}

	@Override
	public <S extends Order> long count(Example<S> example) {
		return orDAO.count(example);
	}

	@Override
	public <S extends Order> boolean exists(Example<S> example) {
		return orDAO.exists(example);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example) {
		return orDAO.findAll(example);
	}

	@Override
	public long count() {
		return orDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		orDAO.deleteById(id);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
		return orDAO.findAll(example, sort);
	}

	@Override
	public void delete(Order entity) {
		orDAO.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		orDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orDAO.deleteAll();
	}

	@Override
	public Order findByIdOrder(int idOrder) {
		for (Order order : orDAO.findAll()) {
			if(order.getIdOrder()==idOrder) {
				return order;
			}
		}
		return null;
	}
	
//	public void AddbillDetails(HashMap<Integer, CartItem> carts) {
//		for(Map.Entry<Integer, CartItem> itemCart:carts.entrySet()) {
//			OrderDetail orderDetails=new OrderDetail();
//			orderDetails.setIdOrder(0);
//			orderDetails.setPrice(0);
//			orderDetails.setProduct(null);
//			orderDetails.setQuantity(0);
//		}
//	}

}
