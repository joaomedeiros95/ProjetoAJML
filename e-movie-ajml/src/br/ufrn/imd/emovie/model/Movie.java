package br.ufrn.imd.emovie.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity
@Table(schema = "public", name = "movie")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_movie")
	private /*@ spec_public nullable @*/ Integer id;
	private /*@ spec_public nullable @*/ String name;
	
	@Column(length=10000)
	private /*@ spec_public nullable @*/ String synopsis;

	private /*@ spec_public nullable @*/ String advertisement;
	private /*@ spec_public nullable @*/ String image;

	@Column(name = "start_exhibition")
	private /*@ spec_public nullable @*/ Date startExhibition;

	@Column(name = "end_exhibition")
	private /*@ spec_public nullable @*/ Date endExhibition;

	public Movie() {
		super();
	}

	public Movie(String name, String synopsis, String advertisement, String image, Date startExhibition, Date endExhibition) {
		this();
		this.name = name;
		this.synopsis = synopsis;
		this.advertisement = advertisement;
		this.image = image;
		this.startExhibition = startExhibition;
		this.endExhibition = endExhibition;
	}

	public Movie(Integer id, String name, String synopsis, String advertisement, String image, Date startExhibition, Date endExhibition) {
		this(name, synopsis, advertisement, image, startExhibition, endExhibition);
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	//@ ensures this.id == id;
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	//@ ensures this.name == name;
	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getAdvertisement() {
		return advertisement;
	}

	//@ ensures this.advertisement == advertisement;
	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}

	public String getImage() {
		return image;
	}

	//@ ensures this.image == image;
	public void setImage(String image) {
		this.image = image;
	}

	//@ ensures this.synopsis == synopsis;
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Date getEndExhibition() {
		return endExhibition;
	}

	//@ ensures this.endExhibition == endExhibition;
	public void setEndExhibition(Date endExhibition) {
		this.endExhibition = endExhibition;
	}

	public Date getStartExhibition() {
		return startExhibition;
	}

	//@ ensures this.startExhibition == startExhibition;
	public void setStartExhibition(Date startExhibition) {
		this.startExhibition = startExhibition;
	}

}
