package br.ufrn.imd.emovie.server.executor;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.service.ChairStateService;

/**
 * 
 * @author lucas cristiano
 *
 */
public class ChairServiceExecutor extends ServiceExecutorTemplate {

	private /*@ spec_public nullable @*/ static final Logger LOGGER = Logger.getLogger(TicketServiceExecutor.class.getName());
	
	private /*@ spec_public nullable @*/ ChairStateService chairStateService;

	public ChairServiceExecutor() {
		chairStateService = ChairStateService.getInstance();
	}

	@Override
	public String processGetFindOne(Integer id) throws DaoException {
		return "";
	}

	@Override
	public String processGetFindAll() throws DaoException {
		List<Map<String, Integer>> chairStates = chairStateService.listAll();
		Gson gson = new Gson();
		String jsonMovie = gson.toJson(chairStates); // returns empty string if chairStates == null
		return jsonMovie;
	}

	@Override
	public String processGetOther(HttpExchange httpExchange, List<String> urlParams,
			Map<String, Object> requestParams) {
		if (requestParams.containsKey("id_exhibition")) {
			try {
				Integer idExhibition = Integer.parseInt((String) requestParams.get("id_exhibition"));
				Map<String, Integer> chairState = chairStateService.findByExhibitionId(idExhibition);

				Gson gson = new Gson();
				String jsonTicket = gson.toJson(chairState); // returns empty string if chairState == null
				return jsonTicket;
			} catch (DaoException e) {
				LOGGER.error(e.getMessage(), e);
				return createErrorJSONResponse(e.getMessage());
			}
		}

		return "";
	}

	/*@ also
	  @ ensures \result != null;
	 */
	@Override
	public String processPostCreate(Map<String, Object> requestParams) {
		LOGGER.warn("Operation not supported");
		return createErrorJSONResponse("Operation not supported");
	}

	/*@ also 
	  @ ensures \result != null;
	 */
	@Override
	public String processPostUpdate(Map<String, Object> requestParams) {
		LOGGER.warn("Operation not supported");
		return createErrorJSONResponse("Operation not supported");
	}

	/*@ also
	  @ ensures \result != null;
	 */
	@Override
	public String processPostDelete(Map<String, Object> requestParams) {
		LOGGER.warn("Operation not supported");
		return createErrorJSONResponse("Operation not supported");
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
