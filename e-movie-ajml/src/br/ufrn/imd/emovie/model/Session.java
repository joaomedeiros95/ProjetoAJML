package br.ufrn.imd.emovie.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Session
 *
 */
@Entity
@Table(schema = "public", name = "session")
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_session")
	private /*@ spec_public nullable @*/ Integer id;
	
	@Column(name = "day_week")
	private /*@ spec_public nullable @*/ int dayWeek;
	private /*@ spec_public nullable @*/ Date hour;

	public Session() {
		super();
	}

	/*@ requires hour != null;
	  @ ensures this.dayWeek == dayWeek && this.hour == hour;
	  @ assignable dayWeek;
	  @ assignable hour;
	 */
	public Session(int dayWeek, Date hour) {
		this();
		this.dayWeek = dayWeek;
		this.hour = hour;
	}
	
	public Session(Integer id, int dayWeek, Date hour) {
		this(dayWeek, hour);
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	//@ ensures this.id == id;
	public void setId(Integer id) {
		this.id = id;
	}

	public int getDayWeek() {
		return dayWeek;
	}

	//@ ensures this.dayWeek == dayWeek;
	public void setDayWeek(int dayWeek) {
		this.dayWeek = dayWeek;
	}

	public Date getHour() {
		return this.hour;
	}

	//@ ensures this.hour == hour;
	public void setHour(Date hour) {
		this.hour = hour;
	}

}
