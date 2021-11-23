package com.springsource.pizzashop.domain;

import javax.persistence.Entity;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import com.springsource.pizzashop.domain.Base;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import java.util.List;
import java.util.Set;
import com.springsource.pizzashop.domain.Topping;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooEntity
public class Pizza {

    @NotNull
    @Size(min = 2)
    private String name;

    @Min(0L)
    private double price;

    @NotNull
    @ManyToOne(targetEntity = Base.class)
    @JoinColumn
    private Base base;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Topping> toppings = new HashSet<Topping>();

	@PersistenceContext
    transient EntityManager entityManager;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Pizza attached = this.entityManager.find(Pizza.class, this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Pizza merged = this.entityManager.merge(this);
        this.entityManager.flush();
        this.id = merged.getId();
    }

	public static final EntityManager entityManager() {
        EntityManager em = new Pizza().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPizzas() {
        return (Long) entityManager().createQuery("select count(o) from Pizza o").getSingleResult();
    }

	public static List<Pizza> findAllPizzas() {
        return entityManager().createQuery("select o from Pizza o").getResultList();
    }

	public static Pizza findPizza(Long id) {
        if (id == null) return null;
        return entityManager().find(Pizza.class, id);
    }

	public static List<Pizza> findPizzaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Pizza o").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public double getPrice() {
        return this.price;
    }

	public void setPrice(double price) {
        this.price = price;
    }

	public Base getBase() {
        return this.base;
    }

	public void setBase(Base base) {
        this.base = base;
    }

	public Set<Topping> getToppings() {
        return this.toppings;
    }

	public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("Base: ").append(getBase()).append(", ");
        sb.append("Toppings: ").append(getToppings() == null ? "null" : getToppings().size());
        return sb.toString();
    }
}
