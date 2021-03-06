package br.ufrn.imd.emovie.server.executor;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Exhibition;
import br.ufrn.imd.emovie.model.Movie;
import br.ufrn.imd.emovie.model.Room;
import br.ufrn.imd.emovie.model.Session;
import br.ufrn.imd.emovie.service.ExhibitionService;
import br.ufrn.imd.emovie.service.MovieService;
import br.ufrn.imd.emovie.service.RoomService;
import br.ufrn.imd.emovie.service.SessionService;
import br.ufrn.imd.emovie.service.exception.ServiceException;

/**
 * 
 * @author lucas cristiano
 *
 */
public class ExhibitionServiceExecutor extends ServiceExecutorTemplate {

	private /*@ spec_public nullable @*/ static final Logger LOGGER = Logger.getLogger(ExhibitionServiceExecutor.class.getName());
	
	private /*@ spec_public nullable @*/ ExhibitionService exhibitionService;

	private /*@ spec_public nullable @*/ MovieService movieService;
	private /*@ spec_public nullable @*/ SessionService sessionService;
	private /*@ spec_public nullable @*/ RoomService roomService;

	public ExhibitionServiceExecutor() {
		exhibitionService = ExhibitionService.getInstance();

		movieService = MovieService.getInstance();
		sessionService = SessionService.getInstance();
		roomService = RoomService.getInstance();
	}

	@Override
	public String processGetFindOne(Integer id) throws DaoException {
		Exhibition exhibition = exhibitionService.find(id);
		Gson gson = new Gson();
		String jsonMovie = gson.toJson(exhibition); // returns empty string if exhibition == null
		return jsonMovie;
	}

	@Override
	public String processGetFindAll() throws DaoException {
		List<Exhibition> exhibitions = exhibitionService.listAll();
		Gson gson = new Gson();
		String jsonMovie = gson.toJson(exhibitions); // returns empty string if exhibition == null
		return jsonMovie;
	}

	@Override
	public String processGetOther(HttpExchange httpExchange, List<String> urlParams,
			Map<String, Object> requestParams) {
		if (requestParams.containsKey("id_movie")) {
			Integer idMovie = Integer.parseInt((String) requestParams.get("id_movie"));
			List<Exhibition> exhibitions = exhibitionService.listByMovieId(idMovie);
			Gson gson = new Gson();
			String jsonTicket = gson.toJson(exhibitions);
			return jsonTicket;
		}
		return "";
	}

	@Override
	public String processPostCreate(Map<String, Object> requestParams) {
		Integer idMovie = Integer.parseInt((String) requestParams.get("id_movie"));
		Integer idSession = Integer.parseInt((String) requestParams.get("id_session"));
		Integer idRoom = Integer.parseInt((String) requestParams.get("id_room"));
		float price = Float.parseFloat((String) requestParams.get("price"));

		try {
			Movie movie = movieService.find(idMovie);
			Session session = sessionService.find(idSession);
			Room room = roomService.find(idRoom);

			Exhibition exhibition = new Exhibition(movie, session, room, price);
			exhibitionService.create(exhibition);
			
			String objectJSON = new Gson().toJson(exhibition);
			JsonObject responseJson = new JsonObject();
			responseJson.addProperty("success", true);
			responseJson.addProperty("exhibition", objectJSON);
			return responseJson.toString();
		} catch (ServiceException e) {
			LOGGER.warn(e.getMessage());
			return createErrorJSONResponse(e.getMessage());
		} catch (DaoException e) {
			LOGGER.error(e.getMessage(), e);
			return createErrorJSONResponse(e.getMessage());
		}
	}

	@Override
	public String processPostUpdate(Map<String, Object> requestParams) {
		Integer id = Integer.parseInt((String) requestParams.get("id"));
		Integer idMovie = Integer.parseInt((String) requestParams.get("id_movie"));
		Integer idSession = Integer.parseInt((String) requestParams.get("id_session"));
		Integer idRoom = Integer.parseInt((String) requestParams.get("id_room"));
		float price = Float.parseFloat((String) requestParams.get("price"));

		try {
			Movie movie = movieService.find(idMovie);
			Session session = sessionService.find(idSession);
			Room room = roomService.find(idRoom);

			Exhibition exhibition = new Exhibition(id, movie, session, room, price);
			exhibitionService.update(exhibition);
			
			String objectJSON = new Gson().toJson(exhibition);
			JsonObject responseJson = new JsonObject();
			responseJson.addProperty("success", true);
			responseJson.addProperty("exhibition", objectJSON);
			return responseJson.toString();
		} catch (ServiceException e) {
			LOGGER.warn(e.getMessage());
			return createErrorJSONResponse(e.getMessage());
		} catch (DaoException e) {
			LOGGER.error(e.getMessage(), e);
			return createErrorJSONResponse(e.getMessage());
		}
	}

	@Override
	public String processPostDelete(Map<String, Object> requestParams) {
		Integer id = Integer.parseInt((String) requestParams.get("id"));

		try {
			exhibitionService.delete(id);
			
			JsonObject responseJson = new JsonObject();
			responseJson.addProperty("success", true);
			responseJson.addProperty("id", id);
			return responseJson.toString();
		} catch (DaoException e) {
			LOGGER.error(e.getMessage(), e);
			return createErrorJSONResponse(e.getMessage());
		}
	}

	/*@ also
	  @ ensures \result != null;
	 */
	@Override
	public String processPostOther(HttpExchange httpExchange, List<String> urlParams,
			Map<String, Object> requestParams) {
		LOGGER.warn("Operation not supported");
		return createErrorJSONResponse("Operation not supported");
	}
}
