package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import phuoc.bookstore.dao.ProductsDAO;
import phuoc.bookstore.entity.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductsDAO prDAO;

	@Override
	public Page<Product> findByNameContainingAndAvailable(String name, int available, Pageable pageable) {
		return prDAO.findByNameContainingAndAvailable(name, available, pageable);
	}

	@Override
	public Page<Product> findByAvailable(int activated, Pageable pageable) {
		return prDAO.findByAvailable(activated, pageable);
	}

	@Override
	public List<Product> findByAvailable(int activated) {
		return prDAO.findByAvailable(activated);
	}

	@Override
	public <S extends Product> S save(S entity) {
		return prDAO.save(entity);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return prDAO.findOne(example);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return prDAO.findAll(pageable);
	}

	@Override
	public List<Product> findAll() {
		return prDAO.findAll();
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return prDAO.findAll(sort);
	}

	@Override
	public List<Product> findAllById(Iterable<Integer> ids) {
		return prDAO.findAllById(ids);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return prDAO.saveAll(entities);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return prDAO.findById(id);
	}

	@Override
	public void flush() {
		prDAO.flush();
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return prDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return prDAO.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		prDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return prDAO.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		prDAO.deleteAllInBatch();
	}

	@Override
	public Product getOne(Integer id) {
		return prDAO.getOne(id);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return prDAO.count(example);
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return prDAO.exists(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return prDAO.findAll(example);
	}

	@Override
	public long count() {
		return prDAO.count();
	}

	@Override
	public void deleteById(Integer id) {
		prDAO.deleteById(id);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return prDAO.findAll(example, sort);
	}

	@Override
	public void delete(Product entity) {
		prDAO.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		prDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		prDAO.deleteAll();
	}

	@Override
	public List<Product> getAll(){
		return prDAO.findAll();
	}

	@Override
	public Product findByIdProduct(int idProduct) {
		// TODO Auto-generated method stub
		for (Product product : prDAO.findAll()) {
			if(product.getIdProduct()==idProduct) {
				return product;
			}
		}
		return null;
	}
 
}
