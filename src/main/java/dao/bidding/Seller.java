// default package
// Generated Jan 6, 2023, 12:46:31 PM by Hibernate Tools 6.0.0.Alpha2
package dao.bidding;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Seller generated by hbm2java
 */
@Entity
@Table(name="seller"
    ,catalog="biddingschema"
)
public class Seller  implements java.io.Serializable {


     private int id;
     private User user;
     private String value;
     private Set<Product> products = new HashSet<Product>(0);

    public Seller() {
    }

	
    public Seller(User user, String value) {
        this.user = user;
        this.value = value;
    }
    public Seller(User user, String value, Set<Product> products) {
       this.user = user;
       this.value = value;
       this.products = products;
    }
   
     @GenericGenerator(name="SellerIdGenerator", strategy="foreign", parameters=@Parameter(name="property", value="user"))@Id @GeneratedValue(generator="SellerIdGenerator")

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="value", nullable=false, length=100)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="seller")
    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addSeller(Seller s){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mmd");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(s);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }




}


