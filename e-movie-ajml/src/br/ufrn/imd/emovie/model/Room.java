package br.ufrn.imd.emovie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Room
 *
 */
@Entity
@Table(schema = "public", name = "room")
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@ public invariant 0 <= rows;
	@Id
	@GeneratedValue
	@Column(name = "id_room")
	private /*@ spec_public nullable @*/ Integer id;
	
	private /*@ spec_public nullable @*/ int rows;
	//@ initially rows == 0;

	public Room() {
		super();
	}
	
	/*@ requires 2 < rows;
	  @ ensures this.rows == rows;
	 */
	public Room(int rows) {
		this();
		this.rows = rows;
	}
	
	/*@ requires 2 < rows;
	  @ ensures this.rows == rows;
	 */
	public Room(Integer id, int rows) {
		this();
		this.id = id;
		this.rows = rows;
	}

	public Integer getId() {
		return this.id;
	}

	//@ ensures this.id == id;
	public void setId(Integer id) {
		this.id = id;
	}

	public int getRows() {
		return rows;
	}

	/*@ requires 1 < rows;
	  @ ensures this.rows == rows;
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

}
