package br.ufrn.imd.emovie.service;

import java.util.List;

import br.ufrn.imd.emovie.dao.IDaoMovie;
import br.ufrn.imd.emovie.dao.DaoMovie;
import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Movie;
import br.ufrn.imd.emovie.service.exception.ServiceException;

public class MovieService {

	private /*@ spec_public nullable @*/ static MovieService movieService;
	private /*@ spec_public nullable @*/ IDaoMovie daoMovie;
	
	/*@ ensures this.daoMovie != null;
	 */
	private MovieService() {
		this.daoMovie = new DaoMovie();
	}
	
	/*@ ensures \result != null;
	 */
	public static MovieService getInstance() {
		if(movieService == null) {
			movieService = new MovieService();
		}
		
		return movieService;
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ Movie find(Integer id) throws DaoException {
		return daoMovie.getById(id);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ List<Movie> listAll() throws DaoException {
		return daoMovie.getAll();
	}
	
	/*@ assignable \nothing;
	  @ ensures movie == \old(movie);
	 */
	public /*@ pure @*/ void create(Movie movie) throws ServiceException, DaoException {
		daoMovie.create(movie);
	}
	
	/*@ assignable \nothing;
	  @ ensures movie == \old(movie);
	 */
	public /*@ pure @*/ void update(Movie movie) throws ServiceException, DaoException {
		daoMovie.update(movie);
	}
	
	/*@ assignable \nothing;
	  @ ensures movie == \old(movie);
	 */
	public /*@ pure @*/ void delete(Movie movie) throws DaoException {
		daoMovie.delete(movie);
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ void delete(Integer id) throws DaoException {
		daoMovie.delete(id);
	}
	
}