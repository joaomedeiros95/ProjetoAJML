package br.ufrn.imd.emovie.server.executor;

import java.util.List;

import com.sun.net.httpserver.HttpExchange;

/**
 * 
 * @author lucas cristiano
 *
 */
public interface IServiceExecutorTemplate {

	void execute(HttpExchange httpExchange);
	void execute(HttpExchange httpExchange, List<String> urlParams);
	
}
