package hr.fer.zemris.java.tecaj_14.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;

/**
 * Razred predstavlja dio domenskog modela aplikacije.
 * Instance razreda reprezentiraju korisnike bloga.
 * @author jelena
 *
 */
@Entity
@Table(name="blog_users")
@NamedQueries({
	@NamedQuery(name="BlogUser.ulist",query="select u from BlogUser as u",
			hints=@QueryHint(name = "org.hibernate.cacheable", value = "true")),
	@NamedQuery(name="BlogUser.byNick",query="select u from BlogUser as u where u.nick=:nick",
		hints=@QueryHint(name = "org.hibernate.cacheable", value = "true"))
})
@Cacheable(true)
public class BlogUser {

	/**
	 * id korisnika
	 */
	private Long id;
	
	/**
	 * Ime korisnika
	 */
	private String firstName;
	
	/**
	 * Prezime korisnika
	 */
	private String lastName;
	
	/**
	 * Korisnicko ime 
	 */
	private String nick;
	
	/**
	 * email korisnika
	 */
	private String email;
	
	/**
	 * Sifra korisnika; digest
	 */
	private String passwordHash;
	
	/**
	 * Unosi korisnika this
	 */
	private List<BlogEntry> entries;
	
	/**
	 * Konstruktor razreda.
	 */
	public BlogUser() {
		this.firstName = "";
		this.lastName = "";
		this.nick = "";
		this.email = "";
		this.entries = new ArrayList<BlogEntry>();
	}
	
	/**
	 * Konstruktor razreda.
	 * @param firstName ime korisnika
	 * @param lastName prezime korisnika
	 * @param nick korisnicko ime
	 * @param email email korisnika
	 * @param passwordHash lozinka; digest
	 */
	public BlogUser(String firstName, String lastName, String nick,
			String email, String passwordHash) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nick = nick;
		this.email = email;
		this.passwordHash = passwordHash;
		this.entries = new ArrayList<BlogEntry>();
	}
	
	/**
	 * Metoda sluzi za dohvat id-ja korisnika.
	 * @return id korisnika
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	/**
	 * Metoda postavlja id korisnika.
	 * @param id id korisnika
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metoda dohvaca ime korisnika.
	 * @return ime korisnika
	 */
	@Column(length=25, nullable=false)
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Metoda postavlja ime korisnika.
	 * @param firstName ime korisnika
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Metoda dohvaca prezime korisnika.
	 * @return prezime korisnika
	 */
	@Column(length=80, nullable=false)
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Metoda postavlja prezime korisnika. 
	 * @param lastName prezime korisnika
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Metoda dohvaca korisnicko ime.
	 * @return korisnicko ime
	 */
	@Column(length=80, nullable=false, unique=true)
	public String getNick() {
		return nick;
	}
	
	/**
	 * Metoda postavlja korisnicko ime.
	 * @param nick korisnicko ime
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Metoda dohvaca email korisnika.
	 * @return email korisnika
	 */
	@Column(length=100, nullable=false)
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metoda postavlja mail korisnika.
	 * @param email mail korisnika
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Metoda dohvaca lozinku korisnika.
	 * @return lozinka korisnika
	 */
	@Column(length=200, nullable=false)
	public String getPasswordHash() {
		return passwordHash;
	}
	
	/**
	 * Metoda postavlja lozinku korisnika.
	 * @param passwordHash lozinka korisnika
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * Metoda dohvaca unose korisnika.
	 * @return unosi bloga korisnika
	 */
	@OneToMany(mappedBy="creator",cascade=CascadeType.PERSIST, orphanRemoval=true)
	public List<BlogEntry> getEntries() {
		return entries;
	}
	
	/**
	 * Metoda potavlja unose korisnika.
	 * @param entries unose korisnika
	 */
	public void setEntries(List<BlogEntry> entries) {
		this.entries = entries;
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
		BlogUser other = (BlogUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
