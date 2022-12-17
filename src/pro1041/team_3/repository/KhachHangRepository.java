package pro1041.team_3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.dto.KhachHangDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author hanhltph27725
 */
public class KhachHangRepository extends Repository<KhachHang, UUID, KhachHangDto> {

    public KhachHangRepository() {
        className = KhachHang.class.getName();
        resCon = "new pro1041.team_3.dto.KhachHangDto(a.id, a.ma, a.hoTen, a.ngaySinh, a.gioiTinh, a.sdt, a.diaChi, a.email)";
    }

    public List<KhachHang> findKhachHang(String key) {
        try {
            List<KhachHang> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.hoTen LIKE CONCAT('%', :key, '%') or a.ma LIKE CONCAT('%', :key, '%')"
                    + " or a.diaChi LIKE CONCAT('%', :key, '%') or  a.sdt LIKE CONCAT('%', :key, '%') or a.email LIKE CONCAT('%', :key, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KhachHang findBySdt(String keyWord) {
        try {
            KhachHang khachHang;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.sdt = :keyWord";
            Query query = session.createQuery(hql);
            query.setParameter("keyWord", keyWord);
            khachHang = (KhachHang) query.getSingleResult();
            return khachHang;
        } catch (Exception e) {
            return null;
        }
    }

    public KhachHang findSDT(String key) {
        try {
            KhachHang khachHang = null;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.sdt = :key";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("key", key);
            List<KhachHang> lst = query.getResultList();
            if (!lst.isEmpty()) {
                khachHang = lst.get(0);
            }
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KhachHang findEmail(String key) {
        try {
            KhachHang khachHang = null;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.email = :key";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("key", key);
            List<KhachHang> lst = query.getResultList();
            if (!lst.isEmpty()) {
                khachHang = lst.get(0);
            }
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<KhachHang> getAllOrderBy() {
        List<KhachHang> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a order by convert(int, substring(a.ma, 3, 10)) desc";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public long getMaOrderBy() {
        long ma = 0;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT count(a.ma) FROM " + className + " a ";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            ma = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }
    
    public static void main(String[] args) {
        KhachHangRepository x = new KhachHangRepository();
        System.out.println(x.getMaOrderBy());
    }

}
