package pro1041.team_3.repository;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonpt_ph19600
 */
public class BanHangRepository {

    private Session session;
    private Transaction trans;

    public BhChiTietDienThoaiDto bhFindByImei(String keyWord) {
        BhChiTietDienThoaiDto ctdt = null;
        try {
            String dto = "new pro1041.team_3.dto.BhChiTietDienThoaiDto"
                    + "(a.id, a.ma, a.mauSac.ten, a.dienThoai.ten, a.hang.ten, "
                    + "a.tinhTrang, a.donGia, a.trangThai, a.imei, "
                    + "a.ram, a.boNho, a.moTa)";
            session = HibernateUtil.getSession();
            String hql = "SELECT " + dto + " FROM " + ChiTietDienThoai.class.getName() + " a WHERE a.imei = :keyWord";
            Query query = session.createQuery(hql);
            query.setParameter("keyWord", keyWord);
            ctdt = (BhChiTietDienThoaiDto) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ctdt;
    }

    public Boolean updateTrangThai(UUID id, Integer trangThai) {
        try {
            String hql = "UPDATE ChiTietDienThoai SET trang_thai = :trangThai WHERE id = :id";
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("trangThai", trangThai);
            query.setParameter("id", id);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public DienThoaiKhuyenMai getGiamGia(UUID idChiTietSanPham) {
        DienThoaiKhuyenMai dtkm = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "FROM " + DienThoaiKhuyenMai.class.getName() + " a WHERE a.chiTietDienThoai.id = :id AND a.trangThai = 1";
            Query query = session.createQuery(hql);
            query.setParameter("id", idChiTietSanPham);
             dtkm =  (DienThoaiKhuyenMai) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dtkm;
    }

}
