package br.ufrn.imd.emovie.service;

import java.util.List;
import java.util.Random;

import br.ufrn.imd.emovie.dao.DaoUser;
import br.ufrn.imd.emovie.dao.IDaoUser;
import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.User;
import br.ufrn.imd.emovie.service.exception.ServiceException;

public class UserService {

	private /*@ spec_public nullable @*/ static UserService userService;
	private /*@ spec_public nullable @*/ IDaoUser daoUser;
	
	/*@ ensures this.daoUser != null;
	 */
	private UserService() {
		this.daoUser = new DaoUser();
	}
	
	/*@ ensures \result != null;
	 */
	public static UserService getInstance() {
		if(userService == null) {
			userService = new UserService();
		}
		
		return userService;
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ User find(Integer id) throws DaoException {
		return daoUser.getById(id);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ List<User> listAll() throws DaoException {
		return daoUser.getAll();
	}
	
	/*@ assignable \nothing;
	  @ ensures user == \old(user);
	 */
	public /*@ pure @*/ void create(User user) throws ServiceException, DaoException {
		daoUser.create(user);
	}
	
	/*@ assignable \nothing;
	  @ ensures user == \old(user);
	 */
	public /*@ pure @*/ void update(User user) throws ServiceException, DaoException {
		daoUser.update(user);
	}
	
	/*@ assignable \nothing;
	  @ ensures user == \old(user);
	 */
	public /*@ pure @*/ void delete(User user) throws DaoException {
		daoUser.delete(user);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ void delete(Integer id) throws DaoException {
		daoUser.delete(id);
	}
	
	/*@ assignable \nothing;
	  @ ensures user == \old(user);
	 */
	public /*@ pure @*/ User checkLogin(User user) throws DaoException {
		return daoUser.checkLogin(user);
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ String generatePassword() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
}