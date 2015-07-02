package hr.fer.zemris.java.tecaj_14.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Razred je dio definicije domenskog modela.
 * Instance razreda reprezentiraju komentar nekog 
 * unosa bloga.
 * @author jelena
 *
 */
@Entity
@Table(name="blog_comments")
@Cacheable(true)
public class BlogComment {

	/**
	 * Id komentara
	 */
	private Long id;
	
	/**
	 * Unos bloga koji komentiramo
	 */
	private BlogEntry blogEntry;
	
	/**
	 * email korisnika koji kreira komentar
	 */
	private String usersEMail;
	
	/**
	 * Sadrzaj komentara
	 */
	private String message;
	
	/**
	 * Vrijeme komentiranja
	 */
	private Date postedOn;
	
	/**
	 * Metoda sluzi za dohvat id-ja komentara.
	 * @return id komentara.
	 */
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	
	/**
	 * Metoda postavlja id na proslijedenu vrijednost.
	 * @param id id komentara
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metoda sluzi za dohvat komentiranog unosa.
	 * @return komentirani unos bloga.
	 */
	@ManyToOne
	@JoinColumn(nullable=false)
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}
	
	/**
	 * Metoda postavlja komentirani unos na proslijedenu 
	 * vrijednost.
	 * @param blogEntry komentirani unos.
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	/**
	 * Metoda dohvaca korisnikov email.
	 * @return korisnikov email
	 */
	@Column(nullable=false, length=256)
	public String getUsersEMail() {
		return usersEMail;
	}

	/**
	 * Metoda postavlja korisnicki email na proslijedenu vrijednost.
	 * @param usersEMail korisnicki mail
	 */
	public void setUsersEMail(String usersEMail) {
		this.usersEMail = usersEMail;
	}

	/**
	 * Metoda dohvaca sadrzaj komentara.
	 * @return sadrzaj komentara
	 */
	@Column(nullable=false, length=4096)
	public String getMessage() {
		return message;
	}
	
	/**
	 * Metoda postavlja sadrzaj komentara na proslijedenu vrijednost.
	 * @param message novi sadrzaj komentara
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Metoda dohvaca trenutak postanja komentara.
	 * @return trenutak postanja komentara
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getPostedOn() {
		return postedOn;
	}

	/**
	 * Metoda postavlja vtijeme postanja komentara.
	 * @param postedOn vrijeme postanja komentara
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
