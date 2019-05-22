package compparts.dao;

import compparts.controller.FilterType;
import compparts.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartsDAOImpl implements PartsDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
    }

    @Override
    public void delete(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(part);
    }


    @Override
    public void edit(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    @Override
    public Part getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Part.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Part> allParts() {
        Session session  = sessionFactory.getCurrentSession();
        return session.createQuery("from Part").list();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Part> allParts(int page, FilterType filterType) {
        Session session = sessionFactory.getCurrentSession();
        String sortQuery;
        switch (filterType) {
            case OPTIONAL: {
                sortQuery = " where p.necessary = false ";
                break;
            }
            case NECESSARY: {
                sortQuery = " where p.necessary = true ";
                break;
            }
            default: {
                sortQuery = "";
            }
        }
        return session.createQuery("from Part p " + sortQuery + " order by p.title").
                setFirstResult(10 * (page - 1)).
                setMaxResults(10).
                list();
    }

    public int partsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Part", Number.class).
                getSingleResult().intValue();
    }

    @SuppressWarnings(value = "unchecked")
    public int compsCount(){
        Session session = sessionFactory.getCurrentSession();
        List<Part> necessaryParts = session.createQuery("from Part p where p.necessary = true").list();
        return necessaryParts.stream().mapToInt(Part::getCount).min().orElse(0);
    }
}
