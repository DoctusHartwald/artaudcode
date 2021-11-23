package com.voleo.entity.user;

import java.util.Collection;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Historique;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.forum.Forum;
import com.voleo.entity.forum.NotificationForum;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.forum.Reponse;
import com.voleo.entity.forum.WallToWall;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String login;
	
	private String password;
	
	//fait le lien avec la table contact mappé par user, 1 utilisateur pr plusieurs contacts
	@OneToMany(mappedBy = "user")
	private Set<Contact> contacts;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
	private Set<Commentaire> commentaire;
	
	@OneToMany(mappedBy = "user")
	private Set<ContactGroup> contactGroups;
	
/*@OneToMany( mappedBy = "user",cascade = CascadeType.PERSIST)  
	@JoinTable(name = "USER_DOCUMENT")
	*/
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private Set<Document> documents;

	
	@OneToMany(mappedBy = "userOrigine", cascade = CascadeType.PERSIST)
	private Set<NotificationWallToWall> notificationWallToWallUserOrigine;
	
	@OneToMany(mappedBy = "userDestination", cascade = CascadeType.PERSIST)
	private Set<NotificationWallToWall> notificationWallToWallUserDestination;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="user", 
			   cascade = CascadeType.PERSIST)
	private Set<NotificationCommentaire> notificationCommentaire;
	
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	private String pseudo;
	
	private String notation;
	
	private int docCount;
	
	private int commentaireCount;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="user", 
			   cascade = CascadeType.PERSIST)
	private Set<Historique> historique;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="userOrigine", 
			   cascade = CascadeType.PERSIST)
	private Set<Historique> historiqueOrigine;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="user", 
			   cascade = CascadeType.PERSIST)
	private Set<Forum> forum;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade = CascadeType.PERSIST)
	private Set<Reponse> reponse;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userOrigineForum",cascade = CascadeType.PERSIST)
	private Set<Reponse> reponseOrigine;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userOrigine",cascade = CascadeType.PERSIST)
	private Set<WallToWall> WallToWallOrigine;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userDestination",cascade = CascadeType.PERSIST)
	private Set<WallToWall> WallToWallDestination;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="userOrigine",cascade = CascadeType.PERSIST)
	private Set<NotificationForum> notificationForumUserOrigine;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="userDestination",cascade = CascadeType.PERSIST)
	private Set<NotificationForum> notificationForumUserDestination;
	
	/* Ce qui concerne la notation */
	
	@ManyToMany(mappedBy = "userWhoHasVoted", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Collection<Document> documentsVote;
	
	@ManyToMany(mappedBy = "userWhoHasVoted", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Collection<Commentaire> commentairesVote;
	
	private Long hitComment = (long)0;
	private Double meanComment = (double)0;
	private Long hitArticle = (long)0;
	private Double meanArticle = (double)0;
	private Long nbArticle = (long)0;
	private Long nbComment = (long)0;
	private Long nbAbusSignales = (long)0;
	private Double noteGenerale = (double)0;
	
	public Double getNoteGenerale() {
		return noteGenerale;
	}

	public void setNoteGenerale(Double noteGenerale) {
		this.noteGenerale = noteGenerale;
	}

	public Collection<Document> getDocumentsVote() {
		return documentsVote;
	}

	public void setDocumentsVote(Collection<Document> documentsVote) {
		this.documentsVote = documentsVote;
	}

	public Long getHitComment() {
		return hitComment;
	}

	public void setHitComment(Long hitComment) {
		this.hitComment = hitComment;
	}

	public Double getMeanComment() {
		return meanComment;
	}

	public void setMeanComment(Double meanComment) {
		this.meanComment = meanComment;
	}

	public Long getHitArticle() {
		return hitArticle;
	}

	public void setHitArticle(Long hitArticle) {
		this.hitArticle = hitArticle;
	}

	public Double getMeanArticle() {
		return meanArticle;
	}

	public void setMeanArticle(Double meanArticle) {
		this.meanArticle = meanArticle;
	}

	public Long getNbArticle() {
		return nbArticle;
	}

	public void setNbArticle(Long nbArticle) {
		this.nbArticle = nbArticle;
	}

	public Long getNbComment() {
		return nbComment;
	}

	public void setNbComment(Long nbComment) {
		this.nbComment = nbComment;
	}

	public Long getNbAbusSignales() {
		return nbAbusSignales;
	}

	public void setNbAbusSignales(Long nbAbusSignales) {
		this.nbAbusSignales = nbAbusSignales;
	}

	/* Fin de ce qui concerne la notation */
	
	
	
	/*public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}*/

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Set<Commentaire> getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Set<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}

	public Set<Historique> getHistorique() {
		return historique;
	}

	public void setHistorique(Set<Historique> historique) {
		this.historique = historique;
	}

	public Set<Historique> getHistoriqueOrigine() {
		return historiqueOrigine;
	}

	public void setHistoriqueOrigine(Set<Historique> historiqueOrigine) {
		this.historiqueOrigine = historiqueOrigine;
	}

	public int getDocCount() {
		return docCount;
	}

	public void setDocCount(int docCount) {
		this.docCount = docCount;
	}

	public int getCommentaireCount() {
		return commentaireCount;
	}

	public void setCommentaireCount(int commentaireCount) {
		this.commentaireCount = commentaireCount;
	}

	public Set<Forum> getForum() {
		return forum;
	}

	public void setForum(Set<Forum> forum) {
		this.forum = forum;
	}

	public Collection<Commentaire> getCommentairesVote() {
		return commentairesVote;
	}
	
	public void setCommentairesVote(Collection<Commentaire> commentairesVote) {
		this.commentairesVote = commentairesVote;
	}
	
	public Set<Reponse> getReponse() {
		return reponse;
	}

	public void setReponse(Set<Reponse> reponse) {
		this.reponse = reponse;
	}

	public Set<Reponse> getReponseOrigine() {
		return reponseOrigine;
	}

	public void setReponseOrigine(Set<Reponse> reponseOrigine) {
		this.reponseOrigine = reponseOrigine;
	}

	public Set<WallToWall> getWallToWallOrigine() {
		return WallToWallOrigine;
	}

	public void setWallToWallOrigine(Set<WallToWall> wallToWallOrigine) {
		WallToWallOrigine = wallToWallOrigine;
	}

	public Set<WallToWall> getWallToWallDestination() {
		return WallToWallDestination;
	}

	public void setWallToWallDestination(Set<WallToWall> wallToWallDestination) {
		WallToWallDestination = wallToWallDestination;
	}

	public Set<NotificationForum> getNotificationForumUserOrigine() {
		return notificationForumUserOrigine;
	}

	public void setNotificationForumUserOrigine(
			Set<NotificationForum> notificationForumUserOrigine) {
		this.notificationForumUserOrigine = notificationForumUserOrigine;
	}

	public Set<NotificationForum> getNotificationForumUserDestination() {
		return notificationForumUserDestination;
	}

	public void setNotificationForumUserDestination(
			Set<NotificationForum> notificationForumUserDestination) {
		this.notificationForumUserDestination = notificationForumUserDestination;
	}

	public Set<NotificationWallToWall> getNotificationWallToWallUserOrigine() {
		return notificationWallToWallUserOrigine;
	}

	public void setNotificationWallToWallUserOrigine(
			Set<NotificationWallToWall> notificationWallToWallUserOrigine) {
		this.notificationWallToWallUserOrigine = notificationWallToWallUserOrigine;
	}

	public Set<NotificationWallToWall> getNotificationWallToWallUserDestination() {
		return notificationWallToWallUserDestination;
	}

	public void setNotificationWallToWallUserDestination(
			Set<NotificationWallToWall> notificationWallToWallUserDestination) {
		this.notificationWallToWallUserDestination = notificationWallToWallUserDestination;
	}

	public Set<NotificationCommentaire> getNotificationCommentaire() {
		return notificationCommentaire;
	}

	public void setNotificationCommentaire(
			Set<NotificationCommentaire> notificationCommentaire) {
		this.notificationCommentaire = notificationCommentaire;
	}

	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}









	
}
