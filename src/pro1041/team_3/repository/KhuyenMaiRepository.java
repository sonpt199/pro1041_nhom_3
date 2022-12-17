package pro1041.team_3.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.dto.KhuyenMaiDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author trangdttph27721
 */
public class KhuyenMaiRepository extends Repository<KhuyenMai, UUID, KhuyenMaiDto> {

    public KhuyenMaiRepository() {
        className = KhuyenMai.class.getName();
        resCon = "new pro1041.team_3.dto.KhuyenMaiDto (a.id, a.ma, a.ten, a.ngayBatDau, "
                + "a.ngayKetThuc, a.giaTriPhanTram, a.giaTriTienMat)";
        join = " ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
    }

    public List<KhuyenMai> findKhuyenMai(String key) {
        try {
            List<KhuyenMai> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ma LIKE CONCAT('%', :key, '%') or a.ten LIKE CONCAT('%', :key, '%') ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMaiDto> findNgayTuongLai() {
        try {
            List<KhuyenMaiDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE GETDATE() < a.ngayBatDau ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMaiDto> findKMDangDienRa() {
        try {
            List<KhuyenMaiDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.ngayBatDau <= GETDATE() and GETDATE() <= a.ngayKetThuc ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMaiDto> findKMByLoai(int loai) {
        try {
            List<KhuyenMaiDto> lst;
            session = HibernateUtil.getSession();
            String hql = "";
            if (loai == 0) {
                hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.giaTriTienMat IS NOT NULL ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            } else {
                hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.giaTriPhanTram IS NOT NULL ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            }
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMaiDto> findKMKetThuc() {
        try {
            List<KhuyenMaiDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE GETDATE() > a.ngayKetThuc ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMai> getAllForDailyCheck() {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            Session sessionForThread = HibernateUtil.getSessionForThread();
            String hql = "SELECT a FROM " + className + " a";
            Query query = sessionForThread.createQuery(hql);
            list = query.getResultList();
            sessionForThread.close();
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

}
