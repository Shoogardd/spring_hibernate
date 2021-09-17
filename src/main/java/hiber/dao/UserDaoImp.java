package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User userByCar(String model, int series) {
      TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery("FROM User user where" +
              " car.model = :setModel " +
              "and car.series = :setSeries");
      typedQuery.setParameter("setModel", model);
      typedQuery.setParameter("setSeries", series);
      return typedQuery.getSingleResult();
   }

}
