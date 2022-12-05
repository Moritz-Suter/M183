package ch.bbzbl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "GeneralEntity.findEntityById", query = "select e from GeneralEntity e where e.id = :entityId")
public class GeneralEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_ENTITY_BY_ID = "GeneralEntity.findEntityById";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titel;
	private String beschreibung;
	private int aufwand;
	private Date beendetBis;
	private boolean status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getAufwand() {
		return aufwand;
	}

	public void setAufwand(int aufwand) {
		this.aufwand = aufwand;
	}

	public Date getBeendetBis() {
		return beendetBis;
	}

	public void setBeendetBis(Date beendetBis) {
		this.beendetBis = beendetBis;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GeneralEntity) {
			GeneralEntity generalEntity = (GeneralEntity) obj;
			return generalEntity.getId() == id;
		}

		return false;
	}
}