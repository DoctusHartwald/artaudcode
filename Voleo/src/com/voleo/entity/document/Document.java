package com.voleo.entity.document;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.voleo.entity.bridge.UserStringBridge;
import com.voleo.entity.bridge.TagCollectionStringBridge;
import com.voleo.entity.user.User;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Indexed(index = "testIndex")
public class Document {
	
	public static final String CLASS_FIELD = "_hibernate_class";
	public static final String ID_FIELD = "id";
	public static final String NAME_FIELD = "name";
	public static final String USER_FIELD = "user";
	public static final String TAGS_FIELD = "tags";
	public static final String CREATE_DATE_FIELD = "createDate";
	public static final String UPDATE_DATE_FIELD = "updateDate";
	
	//Identifiant est unique et autoincrémenté
	@Id
	@DocumentId(name = ID_FIELD)
	@GeneratedValue
	private Long id;
	
	
	@Field(name = NAME_FIELD, index = Index.TOKENIZED, store = Store.COMPRESS)
	@Boost(2f)
	private String name;
	
	//Fait le lien avec la table Tags Le lien avec cette table est de type Plusieurs à Plusieurs
	@Field(name = TAGS_FIELD, index = Index.TOKENIZED, store = Store.COMPRESS)
	@FieldBridge(impl = TagCollectionStringBridge.class)
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({
		org.hibernate.annotations.CascadeType.PERSIST})
	@JoinTable(name = "DOCUMENT_TAG")
	@Boost(3f)
	private Set<Tag> tags = new HashSet<Tag>();

	//fait le lien avec la table User, 1 utilisateur pr plusieurs documents
	@Field(name = USER_FIELD, index = Index.UN_TOKENIZED, store = Store.YES)
	@FieldBridge(impl = UserStringBridge.class)
	@ManyToOne
	@Boost(1.5f)
	private User user;
	
	//Annotation de type temporelle 
	@Field(name = CREATE_DATE_FIELD, index = Index.UN_TOKENIZED, store = Store.COMPRESS)
	@DateBridge(resolution = Resolution.MINUTE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Field(name = UPDATE_DATE_FIELD, index = Index.UN_TOKENIZED, store = Store.COMPRESS)
	@DateBridge(resolution = Resolution.MINUTE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Enumerated(EnumType.STRING)
	private DocumentStatus status;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="document", 
			   cascade = CascadeType.PERSIST)
	private Set<Commentaire> commentaires;
	
	@OneToMany(mappedBy ="document", 
			   cascade = CascadeType.PERSIST)
	private Set<Historique> historique;
	
	private int compteur;
	
	private String categories;

	@Transient
	protected Modification modification;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="document", 
			   cascade = CascadeType.PERSIST)
	private Set<NotificationCommentaire> notificationCommentaire;
	
	
	
	private int countCommentaire;
	
	private String dateFormat;
	
	/* modifié */
	/* Début notation */
	@ManyToMany
	@JoinTable(name="USERS_VOTE_DOCS")
	private Collection<User> userWhoHasVoted;
	
	private Double mean = (double)0;
	private Long nbVote = (long)0;

	public Collection<User> getUserWhoHasVoted() {
		return userWhoHasVoted;
	}

	public void setUserWhoHasVoted(Collection<User> userWhoHasVoted) {
		this.userWhoHasVoted = userWhoHasVoted;
	}

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}

	public Long getNbVote() {
		return nbVote;
	}

	public void setNbVote(Long nbVote) {
		this.nbVote = nbVote;
	}
	
	/* Fin notation */
	/* fin modifié */
	
	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (modification != null && isDifferent(this.name, name)) {
				modification.setName(name);
		}
		this.name = name;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		if (modification != null && isDifferent(this.tags, tags)) {
			modification.setTags(tags);
		}
		this.tags = tags;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}


	public String getCategories() {
		return categories;
	}

	protected boolean isDifferent(Object o1, Object o2) {
		return (   (o1 != null && !o1.equals(o2))
				|| (o2 != null && !o2.equals(o1)));
	}
	
	
	public void setCategories(String categories) {
		if (modification != null && isDifferent(this.categories, categories)) {
			modification.setCategories(categories);
		}
		this.categories = categories;
	}

	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Modification getModification() {
		return modification;
	}

	public void setModification(Modification modification) {
		this.modification = modification;
	}

	public Set<Historique> getHistorique() {
		return historique;
	}

	public void setHistorique(Set<Historique> historique) {
		this.historique = historique;
	}

	public int getCountCommentaire() {
		return countCommentaire;
	}

	public void setCountCommentaire(int countCommentaire) {
		this.countCommentaire = countCommentaire;
	}

	public Set<NotificationCommentaire> getNotificationCommentaire() {
		return notificationCommentaire;
	}

	public void setNotificationCommentaire(
			Set<NotificationCommentaire> notificationCommentaire) {
		this.notificationCommentaire = notificationCommentaire;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Transient
	public String getType() { return ""; }

}
