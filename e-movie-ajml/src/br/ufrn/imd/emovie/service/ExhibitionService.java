package br.ufrn.imd.emovie.service;

import java.util.List;

import br.ufrn.imd.emovie.dao.DaoExhibition;
import br.ufrn.imd.emovie.dao.IDaoExhibition;
import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Exhibition;
import br.ufrn.imd.emovie.service.exception.ServiceException;

public class ExhibitionService {

	private /*@ spec_public nullable @*/ static ExhibitionService exhibitionService;
	private /*@ spec_public nullable @*/ IDaoExhibition daoExhibition;
	
	/*@ ensures this.daoExhibition != null;
	 */
	private ExhibitionService() {
		this.daoExhibition = new DaoExhibition();
	}
	
	/*@ ensures \result != null;
	 */
	public static ExhibitionService getInstance() {
		if(exhibitionService == null) {
			exhibitionService = new ExhibitionService();
		}
		
		return exhibitionService;
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ Exhibition find(Integer id) throws DaoException {
		return daoExhibition.getById(id);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ List<Exhibition> listAll() throws DaoException {
		return daoExhibition.getAll();
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ List<Exhibition> listByMovieId(Integer idMovie) {
		return daoExhibition.listByMovieId(idMovie);
	}
	
	/*@ assignable \nothing;
	  @ ensures exhibition == \old(exhibition);
	 */
	public /*@ pure @*/ void create(Exhibition exhibition) throws ServiceException, DaoException {
		daoExhibition.create(exhibition);
	}
	
	/*@ assignable \nothing;
	  @ ensures exhibition == \old(exhibition);
	 */
	public /*@ pure @*/ void update(Exhibition exhibition) throws ServiceException, DaoException {
		daoExhibition.update(exhibition);
	}
	
	/*@ assignable \nothing;
	  @ ensures exhibition == \old(exhibition);
	 */
	public /*@ pure @*/ void delete(Exhibition exhibition) throws DaoException {
		daoExhibition.delete(exhibition);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ void delete(Integer id) throws DaoException {
		daoExhibition.delete(id);
	}
	
}