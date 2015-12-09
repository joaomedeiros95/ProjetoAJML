package br.ufrn.imd.emovie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Exhibition
 *
 */
@Entity
@Table(schema = "public", name = "exhibition")
public class Exhibition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_exhibition")
	private /*@ spec_public nullable @*/ Integer id;
	
	//@ public invariant 0 <= price;
	
	private /*@ spec_public @*/ float price;
	
	@ManyToOne
	@JoinColumn(name = "id_movie")
	private /*@ spec_public nullable @*/  Movie movie;
	//@ initially movie == null;
	
	@ManyToOne
	@JoinColumn(name = "id_session")
	private /*@ spec_public nullable @*/  Session session;
	//@ initially session == null;
	
	@ManyToOne
	@JoinColumn(name = "id_room")
	private /*@ spec_public nullable @*/  Room room;
	//@ initially room == null;

	public Exhibition() {}
	
	/*@ requires 2.5 < price;
	  @ ensures this.price == price;
	  @ assignable price;
	 */
	public Exhibition(Movie movie, Session session, Room room, float price) {
		this();
		this.movie = movie;
		this.session = session;
		this.room = room;
		this.price = price;
	}

	public Exhibition(Integer id, Movie movie, Session session, Room room, float price) {
		this(movie, session, room, price);
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	//@ ensures this.id == id;
	public void setId(Integer id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	/*@ requires 2.5 < price;
	  @ ensures this.price == price;
	  @ assignable price;
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	public Movie getMovie() {
		return movie;
	}

	//@ ensures this.movie == movie;
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Session getSession() {
		return session;
	}

	//@ ensures this.session == session;
	public void setSession(Session session) {
		this.session = session;
	}

	public Room getRoom() {
		return room;
	}

	//@ ensures this.room == room;
	public void setRoom(Room room) {
		this.room = room;
	}

}
