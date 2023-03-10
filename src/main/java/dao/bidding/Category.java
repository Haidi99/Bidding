// default package
// Generated Jan 6, 2023, 12:46:31 PM by Hibernate Tools 6.0.0.Alpha2
package dao.bidding;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name="category"
    ,catalog="biddingschema"
)
public class Category  implements java.io.Serializable {


     private Integer id;
     private String value;
     private String description;
     private Set<Product> products = new HashSet<Product>(0);

    public Category() {
    }

	
    public Category(String value) {
        this.value = value;
    }
    public Category(String value, String description, Set<Product> products) {
       this.value = value;
       this.description = description;
       this.products = products;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="value", nullable=false, length=45)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    
    @Column(name="description", length=150)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="product_has_category", catalog="biddingschema", joinColumns = { 
        @JoinColumn(name="category_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="product_id", nullable=false, updatable=false) })
    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addCategory(Category c){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mmd");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
         entityManager.getTransaction().begin();
         entityManager.persist(c);
         entityManager.getTransaction().commit();
         entityManager.clear();
    }




}


