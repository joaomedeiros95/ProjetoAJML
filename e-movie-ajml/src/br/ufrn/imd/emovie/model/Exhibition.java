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
	
	private /*@ spec_public @*/ float price;
	
	@ManyToOne
	@JoinColumn(name = "id_movie")
	private /*@ spec_public nullable @*/  Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "id_session")
	private /*@ spec_public nullable @*/  Session session;
	
	@ManyToOne
	@JoinColumn(name = "id_room")
	private /*@ spec_public nullable @*/  Room room;

	public Exhibition() {}
	
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

	public void setId(Integer id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}