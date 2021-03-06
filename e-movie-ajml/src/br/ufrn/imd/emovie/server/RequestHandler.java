package br.ufrn.imd.emovie.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.ufrn.imd.emovie.server.executor.ChairServiceExecutor;
import br.ufrn.imd.emovie.server.executor.ExhibitionServiceExecutor;
import br.ufrn.imd.emovie.server.executor.IServiceExecutorTemplate;
import br.ufrn.imd.emovie.server.executor.MovieServiceExecutor;
import br.ufrn.imd.emovie.server.executor.RoomServiceExecutor;
import br.ufrn.imd.emovie.server.executor.SessionServiceExecutor;
import br.ufrn.imd.emovie.server.executor.TicketServiceExecutor;
import br.ufrn.imd.emovie.server.executor.UserServiceExecutor;

/**
 * 
 * @author lucas cristiano
 *
 */
public class RequestHandler implements HttpHandler {

	private /*@ spec_public nullable @*/ static final Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());
	
	private static long REQUEST_NUMBER = 1;
	private /*@ spec_public nullable @*/ Map<String, IServiceExecutorTemplate> serviceExecutors;

	public RequestHandler() {
		this.serviceExecutors = new HashMap<>();
		this.serviceExecutors.put("rooms", new RoomServiceExecutor());
		this.serviceExecutors.put("movies", new MovieServiceExecutor());
		this.serviceExecutors.put("sessions", new SessionServiceExecutor());
		this.serviceExecutors.put("exhibitions", new ExhibitionServiceExecutor());
		this.serviceExecutors.put("chairs", new ChairServiceExecutor());
		this.serviceExecutors.put("users", new UserServiceExecutor());
		this.serviceExecutors.put("tickets", new TicketServiceExecutor());
	}

	@Override
	public void handle(final HttpExchange httpExchange) throws IOException {		
		URI requestURI = httpExchange.getRequestURI();
		String contextPath = httpExchange.getHttpContext().getPath();
		
		String path = requestURI.getPath();
		path = path.replaceFirst(contextPath, "");

		LOGGER.info("Processing request #" + REQUEST_NUMBER + ": " + httpExchange.getRequestMethod() + " " + path);
		REQUEST_NUMBER++;
		
		String[] splittedPath = path.split("/");
		final List<String> filteredSplittedPath = new ArrayList<>();
		for (int i = 0; i < splittedPath.length; i++) {
			String item = splittedPath[i];
			if(!item.equals("")) {
				filteredSplittedPath.add(item);
			}
		}
		
		boolean validOperation = false;
		if (filteredSplittedPath.size() > 0) {
			String rootCommand = filteredSplittedPath.get(0);
			final IServiceExecutorTemplate serviceExecutor = this.serviceExecutors.get(rootCommand);

			//Removes first url part from list			
			filteredSplittedPath.remove(0);
			
			if (serviceExecutor != null) {
				validOperation = true;		
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						serviceExecutor.execute(httpExchange, filteredSplittedPath);
					}
				});
				thread.start();
			}
		}

		if (!validOperation) {
			String response = "404 (Not Found)\n";
			httpExchange.sendResponseHeaders(404, response.length());

			OutputStream os = httpExchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}
