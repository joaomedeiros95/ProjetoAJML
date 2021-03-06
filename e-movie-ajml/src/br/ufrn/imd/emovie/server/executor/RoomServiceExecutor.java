package br.ufrn.imd.emovie.server.executor;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Room;
import br.ufrn.imd.emovie.service.RoomService;
import br.ufrn.imd.emovie.service.exception.ServiceException;

/**
 * 
 * @author lucas cristiano
 *
 */
public class RoomServiceExecutor extends ServiceExecutorTemplate {

	private /*@ spec_public nullable @*/ static final Logger LOGGER = Logger.getLogger(RoomServiceExecutor.class.getName());
	
	private /*@ spec_public nullable @*/ RoomService roomService;

	public RoomServiceExecutor() {
		roomService = RoomService.getInstance();
	}

	@Override
	public String processGetFindOne(Integer id) throws DaoException {
		Room room = roomService.find(id);
		Gson gson = new Gson();
		String jsonMovie = gson.toJson(room); // returns empty string if room == null
		return jsonMovie;
	}

	@Override
	public String processGetFindAll() throws DaoException {
		List<Room> rooms = roomService.listAll();
		Gson gson = new Gson();
		String jsonMovie = gson.toJson(rooms); // returns empty string if room == null
		return jsonMovie;
	}

	@Override
	public String processGetOther(HttpExchange httpExchange, List<String> urlParams,
			Map<String, Object> requestParams) {
		return "";
	}

	@Override
	public String processPostCreate(Map<String, Object> requestParams) {
		Integer rows = Integer.parseInt((String) requestParams.get("rows"));

		try {
			Room room = new Room(rows);
			roomService.create(room);
			
			String objectJSON = new Gson().toJson(room);
			JsonObject responseJson = new JsonObject();
			responseJson.addProperty("success", true);
			responseJson.addProperty("room", objectJSON);
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
		int id = Integer.parseInt((String) requestParams.get("id"));
		Integer rows = Integer.parseInt((String) requestParams.get("rows"));

		try {
			Room room = new Room(id, rows);
			roomService.update(room);
			
			String objectJSON = new Gson().toJson(room);
			JsonObject responseJson = new JsonObject();
			responseJson.addProperty("success", true);
			responseJson.addProperty("room", objectJSON);
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
		int id = Integer.parseInt((String) requestParams.get("id"));
		try {
			roomService.delete(id);
			
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
