import model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.*;

public class HibernateTest {
    public static void main(String[] args) {
        Customer customer = createCustomer();

        SSN ssn = createSSN();
        customer.setSsn(ssn);
        ssn.setCustomer(customer);

        List<Vehicle> vehicles = createVehicleList();
        customer.setVehicles(vehicles);
        vehicles.get(0).setCustomer(customer);
        vehicles.get(1).setCustomer(customer);

        List<Food> foods = createfoodList();
        customer.setFoods(foods);
        foods.get(0).getCustomers().add(customer);
        foods.get(1).getCustomers().add(customer);

        Property property = new Property(1, "chirala");
        Property building = createBuilding();
        Property land = createLand();

//        customer.setProperty(property);
//        customer.setBuilding(building);
//        customer.setLand(land);

        //Persist inside DB
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(customer);
        session.save(property);
        session.save(building);
        session.save(land);
        session.getTransaction().commit();
        session.close();

        //Query from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        customer = session.get(Customer.class, createUserId());
        System.out.println(customer);
        session.close();

        //Update in DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        property = session.get(Property.class, 6);
        property.setPlace("NEW_CHIRALA");
        session.getTransaction().commit();
        session.close();

        //Delete from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        property = session.get(Property.class, 6);
        session.delete(property);
        session.getTransaction().commit();
        session.close();

        //HQL Query from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select new map(Place,cost) from Property");
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<Map<String, Integer>> properties = query.list();
        for (Map<String, Integer> p : properties) {
            System.out.println(p);
        }
        session.close();

        //HQL Query from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        query = session.createQuery("from Property where Place = :place");
        query.setString("place", "Banglore");
        List<Property> propertiesList = query.list();
        for (Property p : propertiesList) {
            System.out.println(p);
        }
        session.close();

        //HQL named Query from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        query = session.getNamedQuery("by.Place"); //or
//        query = session.getNamedNativeQuery("nativeBy.Place");
        query.setString("place", "Tenali");
        propertiesList = query.list();
        for (Property p : propertiesList) {
            System.out.println(p);
        }
        session.close();

        //HQL Query by Criteria with projections order and restrictions from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Property.class)
                .addOrder(Order.desc("propertyId"))
                .setProjection(Projections.property("Place"));

        criteria.add(Restrictions.or(Restrictions.like("Place", "%Bang%"),
                Restrictions.ge("propertyId", 7)));
        List<String> singlePropertiesList = criteria.list();
        for (String p : singlePropertiesList) {
            System.out.println(p);
        }
        session.close();

        //HQL Query by Criteria with Example from DB
        session = sessionFactory.openSession();
        session.beginTransaction();
        Example exampleProperty = Example.create(new Property(300, "%e%"))
                .enableLike()
                .excludeProperty("cost");
        criteria = session.createCriteria(Property.class).add(exampleProperty);
        propertiesList = criteria.list();
        for (Property p : propertiesList) {
            System.out.println(p);
        }
        session.close();

        //HQL Query for checking how caching works
        session = sessionFactory.openSession();
        session.beginTransaction();
        Property property1 = session.get(Property.class, 8);
        property1.setCost(900);
        System.out.println(session.get(Property.class, 8));
        session.getTransaction().commit();
        session.close();
    }

    private static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(createUserId());
        customer.setAge(22);
        customer.setName("Suhas");
        customer.setHomeAddress(createAddress());
        customer.setEnrolledDate(new Date());
        customer.setDescription("hello");
        customer.setListOfMistakes(createMistakesList());
        return customer;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setStreet("13300 morris rd");
        address.setHouseNum(75);
        address.setCity("Alpharetta");
        address.setState("georgia");
        address.setCountry("usa");
        address.setZipCode(30004);
        return address;
    }

    private static UserId createUserId() {
        UserId userId = new UserId();
        userId.setMainId("Suhas.Jaladi");
        return userId;
    }

    private static Collection<Mistake> createMistakesList() {
        Collection<Mistake> mistakes = new ArrayList<Mistake>();
        Mistake mistake = new Mistake();
        mistake.setMistakeName("a");
        Mistake mistake2 = new Mistake();
        mistake2.setMistakeName("b");
        mistakes.add(mistake);
        mistakes.add(mistake2);
        return mistakes;
    }

    private static SSN createSSN() {
        SSN ssn = new SSN();
        ssn.setIssuedDate(new Date());
        return ssn;
    }

    private static List<Vehicle> createVehicleList() {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("car"));
        vehicles.add(new Vehicle("bike"));
        return vehicles;
    }

    private static List<Food> createfoodList() {
        List<Food> foods = new ArrayList<Food>();
        foods.add(new Food("indian"));
        foods.add(new Food("american"));
        return foods;
    }

    private static Building createBuilding() {
        Building building = new Building();
        building.setNoOfRooms(2);
        building.setCost(200);
        building.setPlace("Tenali");
        return building;
    }

    private static Land createLand() {
        Land land = new Land();
        land.setSquareFeet(10);
        land.setCost(100);
        land.setPlace("Banglore");
        return land;
    }
}
