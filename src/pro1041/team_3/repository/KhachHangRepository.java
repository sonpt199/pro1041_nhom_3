package pro1041.team_3.repository;

import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
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

    public KhachHang findSDT(String key) {
        try {
            KhachHang khachHang;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.sdt = :key";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            khachHang = (KhachHang) query.getSingleResult();
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public KhachHang findEmail(String key) {
        try {
            KhachHang khachHang;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.email = :key";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            khachHang = (KhachHang) query.getSingleResult();
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
