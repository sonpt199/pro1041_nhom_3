package pro1041.team_3.repository;


import java.util.ArrayList;
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
        this.resCon = "new " + ChiTietDienThoaiResponse.class.getName() +"(a.id, a.ma, a.mauSac.ten, a.dienThoai.ten, a.hang.ten, a.tinhTrang, "
                + "a.donGia, a.trangThai, a.hinhAnh, a.imei, a.ram, a.boNho, a.moTa, a.thoiGianBaoHanh)";
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
    
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByDienThoai(String tenDienThoai){
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and a.dienThoai.ten =: ten";
            Query query = session.createQuery(hql);
            query.setParameter("ten", tenDienThoai);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByHang(String tenHang){
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and a.hang.ten =: ten";
            Query query = session.createQuery(hql);
            query.setParameter("ten", tenHang);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByMauSac(String tenMauSac){
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and a.mauSac.ten =: ten";
            Query query = session.createQuery(hql);
            query.setParameter("ten", tenMauSac);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByTinhTrang(int tinhTrang){
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and a.tinhTrang =: tinhTrang";
            Query query = session.createQuery(hql);
            query.setParameter("tinhTrang", tinhTrang);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public List<ChiTietDienThoaiResponse> getAllDienThoaiNotInKM(UUID id) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and a.id not in (select b.chiTietDienThoai.id from DienThoaiKhuyenMai b where b.khuyenMai.id =: id)";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByDienThoai(UUID id, String tenDienThoai) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and (a.id not in (select b.chiTietDienThoai.id from DienThoaiKhuyenMai b where b.khuyenMai.id =: id)) "
                    + "and a.dienThoai.ten =: ten";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("ten", tenDienThoai);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByHang(UUID id, String tenHang) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and (a.id not in (select b.chiTietDienThoai.id from DienThoaiKhuyenMai b where b.khuyenMai.id =: id)) "
                    + "and a.hang.ten =: ten";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("ten", tenHang);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByMauSac(UUID id, String tenMauSac) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and (a.id not in (select b.chiTietDienThoai.id from DienThoaiKhuyenMai b where b.khuyenMai.id =: id)) "
                    + "and a.mauSac.ten =: ten";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("ten", tenMauSac);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByTinhTrang(UUID id, int tinhTrang) {
        try {
            List<ChiTietDienThoaiResponse> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThai = 0 "
                    + "and (a.id not in (select b.chiTietDienThoai.id from DienThoaiKhuyenMai b where b.khuyenMai.id =: id)) "
                    + "and a.tinhTrang =: tinhTrang";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("tinhTrang", tinhTrang);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ChiTietDienThoaiResponse getResponseById(UUID id) {
        ChiTietDienThoaiResponse ctdt = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.id = :id" ;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            ctdt = (ChiTietDienThoaiResponse) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ctdt;
    }
    
}
