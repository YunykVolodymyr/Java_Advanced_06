package ua.yunyk.service.impl;

import java.util.List;

import ua.yunyk.dao.UserDao;
import ua.yunyk.dao.impl.UserDaoImpl;
import ua.yunyk.domain.User;
import ua.yunyk.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public User create(User t) {
		return userDao.create(t);
	}

	@Override
	public User read(Integer id) {
		return userDao.read(id);
	}

	@Override
	public User update(User t) {
		return userDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public List<User> readAll() {
		return userDao.readAll();
	}

	@Override
	public User readByParameter(String parameter) {
		return userDao.readByParameter(parameter);
	}

}
