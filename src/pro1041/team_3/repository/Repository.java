package pro1041.team_3.repository;

import pro1041.team_3.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sonpt_ph19600
 */
public abstract class Repository<Entity, Id, Response> {

    protected Session session;
    protected Transaction trans;
    protected String className;
    protected String resCon;

    public List<Response> getAllResponse() {
        List<Response> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<Entity> getAll() {
        List<Entity> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean saveAll(List<Entity> list) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            for (Entity entity : list) {
                session.saveOrUpdate(entity);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Entity saveOrUpdate(Entity entity) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.saveOrUpdate(entity);
            trans.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    public boolean detele(Entity entity) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.delete(entity);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Entity findById(Id id) {
        try {
            Entity entity;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            entity = (Entity) query.getSingleResult();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Entity findByMa(String ma) {
        try {
            Entity entity;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            entity = (Entity) query.getSingleResult();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

}
