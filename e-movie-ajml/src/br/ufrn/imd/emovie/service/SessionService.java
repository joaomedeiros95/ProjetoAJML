package br.ufrn.imd.emovie.service;

import java.util.List;

import br.ufrn.imd.emovie.dao.DaoSession;
import br.ufrn.imd.emovie.dao.IDaoSession;
import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Session;
import br.ufrn.imd.emovie.service.exception.ServiceException;

public class SessionService {

	private /*@ spec_public nullable @*/ static SessionService sessionService;
	private /*@ spec_public nullable @*/ IDaoSession daoSession;
	
	/*@ ensures this.daoSession != null;
	 */
	private SessionService() {
		this.daoSession = new DaoSession();
	}
	
	/*@ ensures \result != null;
	 */
	public static SessionService getInstance() {
		if(sessionService == null) {
			sessionService = new SessionService();
		}
		
		return sessionService;
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ Session find(Integer id) throws DaoException {
		return daoSession.getById(id);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ List<Session> listAll() throws DaoException {
		return daoSession.getAll();
	}
	
	/*@ assignable \nothing;
	  @ ensures session == \old(session);
	 */
	public /*@ pure @*/ void create(Session session) throws ServiceException, DaoException {
		daoSession.create(session);
	}
	
	/*@ assignable \nothing;
	  @ ensures session == \old(session);
	 */
	public /*@ pure @*/ void update(Session session) throws ServiceException, DaoException {
		daoSession.update(session);
	}
	
	/*@ assignable \nothing;
	  @ ensures session == \old(session);
	 */
	public /*@ pure @*/ void delete(Session session) throws DaoException {
		daoSession.delete(session);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ void delete(Integer id) throws DaoException {
		daoSession.delete(id);
	}
	
}