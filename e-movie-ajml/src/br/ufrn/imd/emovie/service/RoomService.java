package br.ufrn.imd.emovie.service;

import java.util.List;

import br.ufrn.imd.emovie.dao.DaoRoom;
import br.ufrn.imd.emovie.dao.IDaoRoom;
import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Room;
import br.ufrn.imd.emovie.service.exception.ServiceException;

public class RoomService {
	
	private /*@ spec_public nullable @*/ static RoomService roomService = new RoomService();
	private /*@ spec_public nullable @*/ IDaoRoom daoRoom;
	
	/*@ ensures this.daoRoom != null;
	 */
	private RoomService() {
		this.daoRoom = new DaoRoom();
	}
	
	/*@ ensures \result != null;
	 */
	public static RoomService getInstance() {
		if(roomService == null) {
			roomService = new RoomService();
		}
		
		return roomService;
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ Room find(Integer id) throws DaoException {
		return daoRoom.getById(id);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ List<Room> listAll() throws DaoException {
		return daoRoom.getAll();
	}
	
	/*@ assignable \nothing;
	  @ ensures room == \old(room);
	 */
	public /*@ pure @*/ void create(Room room) throws ServiceException, DaoException {
		daoRoom.create(room);
	}
	
	/*@ assignable \nothing;
	  @ ensures room == \old(room);
	 */
	public /*@ pure @*/ void update(Room room) throws ServiceException, DaoException {
		daoRoom.update(room);
	}
	
	/*@ assignable \nothing;
	  @ ensures room == \old(room);
	 */
	public /*@ pure @*/ void delete(Room room) throws DaoException {
		daoRoom.delete(room);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ void delete(Integer id) throws DaoException {
		daoRoom.delete(id);
	}
	
}