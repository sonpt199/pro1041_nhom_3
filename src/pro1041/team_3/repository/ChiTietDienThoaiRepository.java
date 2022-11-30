package pro1041.team_3.repository;


import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author trangdttph27721
 */
public class ChiTietDienThoaiRepository extends Repository<ChiTietDienThoai, UUID, ChiTietDienThoaiResponse>{

    public ChiTietDienThoaiRepository() {
        this.className = ChiTietDienThoai.class.getName();
        this.resCon = "new pro1041.team_3.dto.ChiTietDienThoaiResponse (a.id, a.ma, a.mauSac.ten, a.dienThoai.ten, a.hang.ten, a.tinhTrang, "
                + "a.giaBan, a.trangThai, a.hinhAnh, a.imei, a.ram, a.boNho, a.moTa, a.thoiGianBaoHanh)";
    }
    
    public List<ChiTietDienThoaiResponse> findBy(String keyWord) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.ma LIKE CONCAT('%', :keyWord, '%') or a.dienThoai.ten LIKE CONCAT('%', :keyWord, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("keyWord", keyWord);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> sapXep(String chieu) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.tinhTrang = 1 ORDER BY a.giaBan " + chieu;
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> sapXep2(String loai, String chieu) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a ORDER BY a." + loai + " " + chieu;
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietDienThoaiResponse> getAllTrangThai(int trangThai) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = " + trangThai;
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
