package pro1041.team_3.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import pro1041.team_3.domainModel.HoaDon;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonpt_ph19600
 */
public class HoaDonRepository extends Repository<HoaDon, UUID, HoaDonDto> {

    public HoaDonRepository() {
        className = HoaDon.class.getName();
        resCon = " new " + HoaDonDto.class.getName() + "(a.id, a.ma, b.ma, "
                + "b.hoTen, b.sdt, c.ma, c.hoTen, a.ngayThanhToan, a.hinhThucThanhToan, "
                + "a.tienMat, a.nganHang, a.maGiaoDich, a.tongTien)";
        join = " LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c ORDER BY a.ngayThanhToan";
    }

    public HoaDonDto findResponseById(UUID id) {
        try {
            HoaDonDto entity;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c WHERE a.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            entity = (HoaDonDto) query.getSingleResult();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

// Truy vấn bằng Native Query
//    @Override
//    public List<HoaDonDto> getAllResponse() {
//        List<HoaDonDto> list = new ArrayList<>();
//        try {
//            session = HibernateUtil.getSession();
//            String hql = "SELECT a.ma AS maHoaDon, b.ma AS maKhachHang, b.ho_ten AS tenKhachHang, b.sdt AS sdtKhachHang, "
//                    + "c.ma AS maNhanVien, c.ho_ten AS tenNhanVien, a.ngay_thanh_toan AS ngayThanhToan, "
//                    + "a.hinh_thuc_thanh_toan as hinhThucThanhToan, a.tien_mat AS tienMat, "
//                    + "a.ngan_hang AS nganHang, a.ma_giao_dich AS maGiaoDich,"
//                    + "(SELECT SUM(d.gia_ban) FROM hoa_don_chi_tiet AS d WHERE d.id_hoa_don = a.id)  AS tongTien "
//                    + "FROM hoa_don AS a LEFT JOIN khach_hang AS b ON a.id_khach_hang = b.id "
//                    + "LEFT JOIN nhan_vien AS c ON a.id_nhan_vien = c.id";
//            SQLQuery query = session.createSQLQuery(hql);
//            query.setResultTransformer(Transformers.aliasToBean(HoaDonDto.class));
//            list = query.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return list;
//    }
    public List<HoaDonDto> findHoaDonByKhachHang(String maKH) {
        List<HoaDonDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a "
                    + "LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c WHERE b.ma = :maKH";

            Query query = session.createQuery(hql);
            query.setParameter("maKH", maKH);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonDto> findHoaDon(String key) {
        List<HoaDonDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a "
                    + "LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c "
                    + "WHERE a.ma LIKE CONCAT('%', :key, '%')"
                    + " or b.sdt LIKE CONCAT('%', :key, '%')";

            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonDto> locHinhThucThanhToan(Integer httt) {
        List<HoaDonDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a "
                    + "LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c "
                    + "WHERE a.hinhThucThanhToan = : httt";

            Query query = session.createQuery(hql);
            query.setParameter("httt", httt);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonDto> locHoaDonTheoNgay(Date ngay1, Date ngay2) {
        List<HoaDonDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a "
                    + "LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c "
                    + "WHERE (a.ngayThanhToan  between :ngay1 and :ngay2)"
                    + " or (a.ngayThanhToan  between :ngay2 and :ngay1)";

            Query query = session.createQuery(hql);
            query.setParameter("ngay1", ngay1);
            query.setParameter("ngay2", ngay2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonDto> locHoaDonTheoTongTien(BigDecimal tien1, BigDecimal tien2) {
        List<HoaDonDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a "
                    + "LEFT JOIN a.khachHang b LEFT JOIN a.nhanVien c "
                    + "WHERE (a.tongTien  between :tien1 and :tien2)"
                    + " or (a.tongTien  between :tien2 and :tien1)";

            Query query = session.createQuery(hql);
            query.setParameter("tien1", tien1);
            query.setParameter("tien2", tien2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
