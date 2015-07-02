package hr.fer.zemris.java.tecaj_14.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Razred predstavlja dio domenskog modela.
 * Instance razreda reprezentiraju jedan unos bloga.
 * @author jelena
 *
 */
@Entity
@Table(name="blog_entries")
@NamedQueries({
	@NamedQuery(name="BlogEntry.upit1",query="select b from BlogComment as b where b.blogEntry=:be and b.postedOn>:when",
			hints=@QueryHint(name = "org.hibernate.cacheable", value = "true")),
	@NamedQuery(name="BlogEntry.userEntry", query="select e from BlogEntry as e where e.creator=:user")

})
@Cacheable(true)
public class BlogEntry {

	/**
	 * Id unosa
	 */
	private Long id;
	
	/**
	 * Lista komentara unosa
	 */
	private List<BlogComment> comments = new ArrayList<>();
	
	/**
	 * Trenutak kreiranja unosa
	 */
	private Date createdAt;
	
	/**
	 * Zadnja promjena unosa
	 */
	private Date lastModifiedAt;
	
	/**
	 * Naslov unosa
	 */
	private String title;
	
	/**
	 * Sadrzaj unosa
	 */
	private String text;
	
	/**
	 * Kreator unosa
	 */
	private BlogUser creator;
	
	/**
	 * Metoda dohvaca kreatora ovog unosa.
	 * @return kreator unosa
	 */
	@ManyToOne
	@JoinColumn(nullable=false)
	public BlogUser getCreator() {
		return creator;
	}
	
	/**
	 * Metoda dohvaca id unosa.
	 * @return id unosa
	 */
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	
	/**
	 * Metoda postavlja id unosa.
	 * @param id id unosa
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metoda dohvaca listu komentara unosa.
	 * @return
	 */
	@OneToMany(mappedBy="blogEntry", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, orphanRemoval=true)
	@OrderBy("postedOn")
	public List<BlogComment> getComments() {
		return comments;
	}
	
	/**
	 * Metoda postavlja komentare na proslijedenu vrijednost.
	 * @param comments komentari unosa
	 */
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	/**
	 * Metoda dohvaca trenutak kreiranja unosa.
	 * @return trenutak kreiranja
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Metoda postavlja trenutak kreiranja.
	 * @param createdAt trenutak kreiranja unosa
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Metoda dohvaca trenutak zadnje modifikacije unosa.
	 * @return zadnja modifikacija unosa
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * Metoda postavlja trenutak zadnje modifikacije unosa.
	 * @param lastModifiedAt trenutak zadnje promjene
	 */
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * Metoda dohvaca naslov unosa.
	 * @return naslov unosa
	 */
	@Column(nullable=false, length=100)
	public String getTitle() {
		return title;
	}

	/**
	 * Metoda postavlja naslov unosa.
	 * @param title naslov unosa
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Metoda dohvaca sadrzaj unosa.
	 * @return sadrzaj unosa
	 */
	@Column(nullable=false, length=4096)
	public String getText() {
		return text;
	}

	/**
	 * Metoda postavlja sadrzaj unosa. 
	 * @param text sadrzaj unosa
	 */
	public void setText(String text) {
		this.text = text;
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
		BlogEntry other = (BlogEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Metoda postavlja kreatora unosa
	 * @param creator kreator unosa
	 */
	public void setCreator(BlogUser creator) {
		this.creator = creator;
	}

}
