package phuoc.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import phuoc.bookstore.dao.AcountsDAO;
import phuoc.bookstore.entity.Acount;

@Service
public class AcountServiceimpl implements AcountService {

	@Autowired
	AcountsDAO acDAO;

	@Override
	public List<Acount> findByActivatedContaining(String username) {
		return acDAO.findByActivatedContaining(username);
	}

	@Override
	public Page<Acount> findByUsernameContainingAndActivated(String username, int activated, Pageable pageable) {
		return acDAO.findByUsernameContainingAndActivated(username, activated, pageable);
	}

	@Override
	public Page<Acount> findAllByActivated(int activated, Pageable pageable) {
		return acDAO.findAllByActivated(activated, pageable);
	}

	@Override
	public <S extends Acount> S save(S entity) {
		return acDAO.save(entity);
	}

	@Override
	public <S extends Acount> Optional<S> findOne(Example<S> example) {
		return acDAO.findOne(example);
	}

	@Override
	public Page<Acount> findAll(Pageable pageable) {
		return acDAO.findAll(pageable);
	}

	@Override
	public List<Acount> findAll() {
		return acDAO.findAll();
	}

	@Override
	public List<Acount> findAll(Sort sort) {
		return acDAO.findAll(sort);
	}

	@Override
	public List<Acount> findAllById(Iterable<String> ids) {
		return acDAO.findAllById(ids);
	}

	@Override
	public <S extends Acount> List<S> saveAll(Iterable<S> entities) {
		return acDAO.saveAll(entities);
	}

	@Override
	public Optional<Acount> findById(String id) {
		return acDAO.findById(id);
	}
	
	@Override
	public Optional<Acount> findByIdAndActivated(String id,int activated) {
		return acDAO.findByActivatedAndUsername(activated,id);
	}

	@Override
	public void flush() {
		acDAO.flush();
	}

	@Override
	public <S extends Acount> S saveAndFlush(S entity) {
		return acDAO.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return acDAO.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Acount> entities) {
		acDAO.deleteInBatch(entities);
	}

	@Override
	public <S extends Acount> Page<S> findAll(Example<S> example, Pageable pageable) {
		return acDAO.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		acDAO.deleteAllInBatch();
	}

	@Override
	public Acount getOne(String id) {
		return acDAO.getOne(id);
	}

	@Override
	public <S extends Acount> long count(Example<S> example) {
		return acDAO.count(example);
	}

	@Override
	public <S extends Acount> boolean exists(Example<S> example) {
		return acDAO.exists(example);
	}

	@Override
	public <S extends Acount> List<S> findAll(Example<S> example) {
		return acDAO.findAll(example);
	}

	@Override
	public long count() {
		return acDAO.count();
	}

	@Override
	public void deleteById(String id) {
		acDAO.deleteById(id);
	}

	@Override
	public <S extends Acount> List<S> findAll(Example<S> example, Sort sort) {
		return acDAO.findAll(example, sort);
	}

	@Override
	public void delete(Acount entity) {
		acDAO.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Acount> entities) {
		acDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		acDAO.deleteAll();
	}
	
	@Override
	public boolean checkLogin(String username,String password) {
		
		Optional<Acount> optionalUser=findById(username);
		if(optionalUser.isPresent()&&optionalUser.get().getPassword().equals(password)&&optionalUser.get().getAdmin()==0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean checkLogin1(String username,String password) {
		
		Optional<Acount> optionalUser=findById(username);
		if(optionalUser.isPresent()&&optionalUser.get().getPassword().equals(password)&&optionalUser.get().getAdmin()==1) {
			return true;
		}
		return false;
	}

	@Override
	public Acount findByUsername(String username) {
		for (Acount acount : acDAO.findAll()) {
			if(acount.getUsername()==username) {
				return acount;
			}
		}
		return null;
	}

	
}
