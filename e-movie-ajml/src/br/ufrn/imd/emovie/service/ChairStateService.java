package br.ufrn.imd.emovie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.imd.emovie.dao.DaoExhibition;
import br.ufrn.imd.emovie.dao.DaoTicket;
import br.ufrn.imd.emovie.dao.IDaoExhibition;
import br.ufrn.imd.emovie.dao.IDaoTicket;
import br.ufrn.imd.emovie.dao.exception.DaoException;
import br.ufrn.imd.emovie.model.Exhibition;
import br.ufrn.imd.emovie.model.Ticket;

public class ChairStateService {

	private /*@ spec_public nullable @*/ static final int CHAIRS_PER_ROW = 10;
	private /*@ spec_public nullable @*/ static final List<String> ROW_IDENTIFIERS = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
	
	private /*@ spec_public nullable @*/ static ChairStateService chairStateService;
	
	private /*@ spec_public nullable @*/ IDaoTicket daoTicket;
	private /*@ spec_public nullable @*/ IDaoExhibition daoExhibition;
	
	/*@ ensures this.daoExhibition != null && this.daoTicket != null;
	 */
	private ChairStateService() {
		this.daoTicket = new DaoTicket();
		this.daoExhibition = new DaoExhibition();
	}
	
	/*@ ensures \result != null;
	 */
	public static ChairStateService getInstance() {
		if(chairStateService == null) {
			chairStateService = new ChairStateService();
		}
		
		return chairStateService;
	}
	
	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ Map<String, Integer> findByExhibitionId(Integer idExhibition) throws DaoException {
		Exhibition exhibition = daoExhibition.getById(idExhibition);
		if(exhibition != null) {
			Map<String, Integer> chairState = getExhibitionChairState(exhibition);
			return chairState;
		}
		
		return null;
	}
	
	/*@ assignable \nothing;
	 */
	public /*@ pure @*/ List<Map<String, Integer>> listAll() throws DaoException {
		List<Exhibition> exhibitions = daoExhibition.getAll();
		
		List<Map<String, Integer>> chairStates = new ArrayList<>();
		for (Exhibition exhibition : exhibitions) {
			Map<String, Integer> chairState = getExhibitionChairState(exhibition);
			chairStates.add(chairState);
		}
		
		return chairStates;
	}

	/*@ assignable \nothing;
	  @ ensures \result != null;
	 */
	public /*@ pure @*/ Map<String, Integer> getExhibitionChairState(Exhibition exhibition) {
		Integer roomRows = exhibition.getRoom().getRows();
		
		if(roomRows > ROW_IDENTIFIERS.size()) {
			throw new IllegalArgumentException("Número de fileiras muito grande. Valor recebido: " + ROW_IDENTIFIERS.size());
		}
		
		Map<String, Integer> chairState = new LinkedHashMap<>();
		for(int i = 0; i < roomRows; i++) {
			for(int j = 1; j <= CHAIRS_PER_ROW; j++) {
				String chairNumber = String.format("%s%02d", ROW_IDENTIFIERS.get(i), j);  
				chairState.put(chairNumber, 0);
			}
		}
		
		List<Ticket> tickets = daoTicket.listByExhibitionId(exhibition.getId());
		for (Ticket ticket : tickets) {
			String chairNumber = ticket.getChairNumber();
			chairState.put(chairNumber, 1);
		}

		return chairState;
	}
}