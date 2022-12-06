package pro1041.team_3.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiReQuestDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author trangdttph27721
 */
public class DienThoaiKhuyenMaiRepository extends Repository<DienThoaiKhuyenMai, UUID, DienThoaiKhuyenMaiDto> {

    public DienThoaiKhuyenMaiRepository() {
        className = DienThoaiKhuyenMai.class.getName();
        resCon = "new pro1041.team_3.dto.DienThoaiKhuyenMaiDto(a.khuyenMai.id, a.chiTietDienThoai.id, a.chiTietDienThoai.dienThoai.ma, a.chiTietDienThoai.dienThoai.ten, "
                + " a.chiTietDienThoai.hang.ten, a.chiTietDienThoai.mauSac.ten, "
                + " a.chiTietDienThoai.imei, a.chiTietDienThoai.donGia, a.giaConLai)";
    }

    public List<DienThoaiKhuyenMaiDto> findDienThoaiKhuyenMaiByIdKM(UUID id) {
        List<DienThoaiKhuyenMaiDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new pro1041.team_3.dto.DienThoaiKhuyenMaiDto "
                    + "(a.khuyenMai.id, a.chiTietDienThoai.id, a.chiTietDienThoai.dienThoai.ma, a.chiTietDienThoai.dienThoai.ten, "
                    + " a.chiTietDienThoai.hang.ten, a.chiTietDienThoai.mauSac.ten, "
                    + " a.chiTietDienThoai.imei, a.chiTietDienThoai.donGia, a.giaConLai) "
                    + "FROM DienThoaiKhuyenMai a WHERE a.khuyenMai.id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DienThoaiKhuyenMaiDto> findDienThoaiKhuyenMai(UUID id, String key) {
        try {
            List<DienThoaiKhuyenMaiDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT  new pro1041.team_3.dto.DienThoaiKhuyenMaiDto "
                    + "(a.khuyenMai.id, a.chiTietDienThoai.id, a.chiTietDienThoai.dienThoai.ma, a.chiTietDienThoai.dienThoai.ten, "
                    + " a.chiTietDienThoai.hang.ten, a.chiTietDienThoai.mauSac.ten, "
                    + " a.chiTietDienThoai.imei, a.chiTietDienThoai.donGia, a.giaConLai) "
                    + "FROM DienThoaiKhuyenMai a WHERE (a.chiTietDienThoai.dienThoai.ma LIKE CONCAT('%', :key, '%') or a.chiTietDienThoai.dienThoai.ten LIKE CONCAT('%', :key, '%')"
                    + " or a.chiTietDienThoai.hang.ten LIKE CONCAT('%', :key, '%') or a.chiTietDienThoai.mauSac.ten LIKE CONCAT('%', :key, '%')"
                    + " or a.chiTietDienThoai.imei LIKE CONCAT('%', :key, '%')) and a.khuyenMai.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            query.setParameter("id", id);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<DienThoaiKhuyenMaiDto> findDTKhuyenMaiKetThuc() {
        List<DienThoaiKhuyenMaiDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + "FROM DienThoaiKhuyenMai a WHERE a.khuyenMai.ngayKetThuc < GETDATE()";

            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DienThoaiKhuyenMaiDto> findDTKhuyenMaiDienRavaTgLai(UUID id) {
        List<DienThoaiKhuyenMaiDto> list = new ArrayList<>();
        try {
//            KhuyenMaiReQuestDto(maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, giaTriPhanTram, giaTriTienMat, chiTietDienThoai, giaBan, giaConLai)
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + "FROM DienThoaiKhuyenMai a WHERE "
                    + "((GETDATE() >= a.khuyenMai.ngayBatDau and GETDATE() <= a.khuyenMai.ngayKetThuc)"
                    + " or a.khuyenMai.ngayBatDau > GETDATE())\n"
                    + "and a.chiTietDienThoai.id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
