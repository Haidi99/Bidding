package dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Statement;

//import com.mysql.cj.Query;

import dao.bidding.*;
import jakarta.persistence.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class App 
{
    public static void main( String[] args )
    {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mmd");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        Date date = new Date();

        // create products
        Product product1 = new Product("Laptop", "this is product1", date, 50, date, date);
        entityManager.getTransaction().begin();
        entityManager.persist(product1);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Product product2 = new Product("Headphones", "this is product2", date, 50, date, date);
        entityManager.getTransaction().begin();
        entityManager.persist(product2);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Product product3 = new Product("TV", "this is product3", date, 50, date, date);
        entityManager.getTransaction().begin();
        entityManager.persist(product3);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Product product4 = new Product("Smart Phone", "this is product4", date, 50, date, date);
        entityManager.getTransaction().begin();
        entityManager.persist(product4);
        entityManager.getTransaction().commit();
        entityManager.clear();


        // declare set of products

         Set<Product> products = new HashSet<Product>();
         products.add(product1);
         products.add(product2);
         products.add(product3);
         products.add(product4);

        // declare category with set of products

        Category category = new Category("CATEGORY", "this is my test category", products);
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.clear();

         //declare and add user to db

         User usr = new User("haidi.hany48@gmail.com", "Cairo, Eg",date, "Haidi", "123450");
         usr.setDateOfBirth(new Date(04-04-1999));
         usr.setFullName("Haidi Aly");
         usr.setMobile("085000");
         usr.setPhone("022222");
         entityManager.getTransaction().begin();
         entityManager.persist(usr);
         entityManager.getTransaction().commit();
         entityManager.clear();
         System.out.println("User Record Successfully Inserted In The Database");

         // //declare buyer

         Buyer buyer1 = new Buyer(usr, "buyer1");
         entityManager.getTransaction().begin();
         entityManager.persist(buyer1);
         entityManager.getTransaction().commit();
         entityManager.clear();
         System.out.println("Buyer Record Successfully Inserted In The Database");

         // // declare seller

         Seller seller1 = new Seller(usr, "seller1");
         seller1.setProducts(products);
         entityManager.getTransaction().begin();
         entityManager.persist(seller1);
         entityManager.getTransaction().commit();
         entityManager.clear();
         System.out.println("Seller Record Successfully Inserted In The Database");

        
         // // BuyerBidProduct relationship

         BuyerBidProductId bidProductId = new BuyerBidProductId(1, 2);
         BuyerBidProduct bidProduct = new BuyerBidProduct(bidProductId, buyer1, product1, date, 9, 5);
         entityManager.getTransaction().begin();
         entityManager.persist(bidProduct);
         entityManager.getTransaction().commit();
         entityManager.clear();
         System.out.println("BuyerBidProduct Record Successfully Inserted In The Database");


         // // BuyerBuyProduct relationship

         BuyerBuyProductId buyProductId = new BuyerBuyProductId(1, 2);
         BuyerBuyProduct buyProduct = new BuyerBuyProduct(buyProductId, buyer1, product3, date, 5, 10);
         entityManager.getTransaction().begin();
         entityManager.persist(buyProduct);
         entityManager.getTransaction().commit();
         entityManager.clear();
         System.out.println("BuyerBuyProduct Record Successfully Inserted In The Database");

         Set<Category>categories = new HashSet<Category>();
         categories.add(category);

         Set<BuyerBidProduct> bidProducts = new HashSet<BuyerBidProduct>();
         bidProducts.add(bidProduct);

         Set<BuyerBuyProduct>buyProducts = new HashSet<BuyerBuyProduct>();
         buyProducts.add(buyProduct);
        
        
         Product product5 = new Product(seller1, "headphones", "headset", "headphones", date, date, 125, date, date, categories, bidProducts, buyProducts);
         entityManager.getTransaction().begin();
         entityManager.persist(product5);
         entityManager.getTransaction().commit();
         entityManager.clear();


        // String sq = "select * from Product p join Category c on p.Id =c.id where c.id =1";
        // Query q = entityManager.createQuery(sq);

        // List<Product> res = q.getResultList();
        // for (Product product : res) {
        //     System.out.println(product.getName());
        // }

    //   Query query = entityManager.createQuery("from Product p");
    //   List<Product> list = query.getResultList();

    //   for(Product e:list) {
    //      System.out.println("PRODUCT NAME :"+e.getName());
    //   }

/////////////////////////////////////////////////// JPQL ////////////////////////////////////////////////////////

//Select all Products under specific Category

//    List<Product> list = entityManager.createQuery(
//	"select distinct pr " +
//	"from Product pr " +
//	"join pr.categories ct " +
//	"where ct.id = :categoryId",
//	Product.class)
//.setParameter("categoryId", 1)
//.getResultList();
//
//    for(Product p:list) {
//        System.out.println("PRODUCT NAME :"+p.getName());
//
//    }
//
//    //Select all bids for specific product (BuyerBidProduct table)
//    List<BuyerBidProduct> buyerBidProductsList = entityManager.createQuery(
//	"from BuyerBidProduct bid " +
//	"where bid.id.productId= :product",
//	BuyerBidProduct.class)
//.setParameter("product", 2)
//.getResultList();
//
//for(BuyerBidProduct bid:buyerBidProductsList) {
//    System.out.println("PRODUCT AMOUNT :"+bid.getAmount());
//
//}
//
////Select all bids for specific product by JOIN
//// List<BuyerBidProduct> buyerBidProductsList2 = entityManager.createQuery(
////     "from BuyerBidProduct bid " +
////     "inner join Product p " +
////     "on p.id=:id",
////     BuyerBidProduct.class)
//// .setParameter("id",2).getResultList();
//// for(BuyerBidProduct bidd:buyerBidProductsList2) {
////     System.out.println("PRODUCT AMOUNT (by JOIN claus) :"+bidd.getAmount());
//
//// }
//
////Total amount of buy operations on specific product (BuyerBuyProduct table)
//
//List<BuyerBuyProduct> buyProductsList = entityManager.createQuery(
//	"from BuyerBuyProduct buy " +
//	"where buy.id.productId= :product",
//	BuyerBuyProduct.class)
//.setParameter("product", 2)
//.getResultList();
//
//for(BuyerBuyProduct buy:buyProductsList) {
//    System.out.println("PRODUCT AMOUNT :"+buy.getAmount());
//
//}
//
///////////////////////////////////////////////////Criteria API///////////////////////////////////////////////////////////////
////Select all Products under specific Category
//
//// List<Product> list2  = q.select(p).where(cb.gt (p.<Category>get("categories"),1));
////q.select(p).where(cb.like(cb.lower(p.<String>get("name")),"%p%"));
////q.select(p).where(p.get("categories").<Integer>get("id"),1);
//
//CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
//CriteriaQuery<Product> q = cb.createQuery(Product.class);
//
//Root<Product> p = q.from(Product.class);
//Join<Product,Category> pc = p.join("categories", JoinType.INNER);
//pc.on(cb.gt(p.<Integer>get("id"),1));
//
//List<Product> productsList = entityManager.createQuery(q).getResultList();
//for(Product pr:productsList) {
//            System.out.println("PRODUCT NAME :"+pr.getName());
//
//         }
//
////Select all bids for specific product (BuyerBidProduct table)
//CriteriaQuery<BuyerBidProduct> q2 = cb.createQuery(BuyerBidProduct.class);
//Root<BuyerBidProduct> bRoot = q2.from(BuyerBidProduct.class);
//q2.select(bRoot).where(cb.gt(bRoot.<BuyerBidProduct>get("id").<Integer>get("productId"),1));
//List<BuyerBidProduct> bblist = entityManager.createQuery(q2).getResultList();
//for(BuyerBidProduct bb:bblist) {
//            System.out.println("Amount :"+bb.getAmount());
//
//         }
//
////Total amount of buy operations on specific product (BuyerBuyProduct table)
//
//CriteriaQuery<BuyerBuyProduct> q3 = cb.createQuery(BuyerBuyProduct.class);
//Root<BuyerBuyProduct> bRoot2 = q3.from(BuyerBuyProduct.class);
//q3.select(bRoot2).where(cb.gt(bRoot2.<BuyerBidProduct>get("id").<Integer>get("productId"),1));
//List<BuyerBuyProduct> bblist2 = entityManager.createQuery(q3).getResultList();
//for(BuyerBuyProduct bb:bblist2) {
//            System.out.println("Amount :"+bb.getAmount());
//
//         }


    }
    }

