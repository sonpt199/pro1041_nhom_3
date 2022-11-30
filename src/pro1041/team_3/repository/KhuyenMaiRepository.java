package pro1041.team_3.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
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

    }

    public List<KhuyenMai> findKhuyenMai(String key) {
        try {
            List<KhuyenMai> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ma LIKE CONCAT('%', :key, '%') or a.ten LIKE CONCAT('%', :key, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KhuyenMai> findNgayTuongLai() {
        try {
            List<KhuyenMai> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ngayBatDau > " + new java.sql.Date(new java.util.Date().getTime());
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
  
}
