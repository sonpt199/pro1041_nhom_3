package pro1041.team_3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.dto.ChiTietDienThoaiDto;
import pro1041.team_3.dto.HangDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author hanhltph27725
 */
public class HangRepository extends Repository<Hang, UUID, HangDto> {

    public HangRepository() {
        className = Hang.class.getName();
        resCon = "new pro1041.team_3.dto.HangDto(a.id, a.ma, a.ten)";
        join = " order by CONVERT(INT, SUBSTRING(a.ma, 2, 10)) desc";
    }

    public List<Hang> findHang(String ten) {
        try {
            List<Hang> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ten LIKE CONCAT('%', :ten, '%') or a.ma LIKE CONCAT('%', :ten, '%') " + join;
            Query query = session.createQuery(hql);
            query.setParameter("ten", ten);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Hang> findByName(String ten) {
        try {
            List<Hang> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ten LIKE CONCAT('%', :ten, '%')" + join;
            Query query = session.createQuery(hql);
            query.setParameter("ten", ten);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ChiTietDienThoaiDto checkHangTrongCtdt(UUID id) {
        ChiTietDienThoaiDto chiTietDienThoaiDto = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "select new pro1041.team_3.dto.ChiTietDienThoaiDto(a.id, a.hang.id, a.mauSac.id, a.dienThoai.id) from ChiTietDienThoai a "
                    + " where a.hang.id = :id ";
            TypedQuery<ChiTietDienThoaiDto> query = session.createQuery(hql, ChiTietDienThoaiDto.class);
            query.setParameter("id", id);
            List<ChiTietDienThoaiDto> lst = query.getResultList();
            if (!lst.isEmpty()) {
                chiTietDienThoaiDto = lst.get(0);
            }
            return chiTietDienThoaiDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Hang findHangByName(String ten) {
        Hang entity = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ten = :ten";
            TypedQuery<Hang> query = session.createQuery(hql, Hang.class);
            query.setParameter("ten", ten);
            List<Hang> lst = query.getResultList();
            if (!lst.isEmpty()) {
                entity = lst.get(0);
            }
//            query.setParameter("ten", ten);
//            if (query.getSingleResult() != null) {
//                entity = (Hang) query.getSingleResult();
//            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
