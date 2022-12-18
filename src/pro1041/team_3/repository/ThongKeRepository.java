package pro1041.team_3.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pro1041.team_3.domainModel.HoaDon;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.DoanhThuCaNhanDto;
import pro1041.team_3.dto.DoanhThuTheoNgayDto;
import pro1041.team_3.dto.ThongKeTheoHang;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonnh
 */
public class ThongKeRepository {

    private Session session;
    private Transaction trans;

    public DoanhThuTheoNgayDto doanhThuTheoNgay(int day, int month, int year) {
        String hql = "SELECT new " + DoanhThuTheoNgayDto.class.getName()
                + "(SUM(b.tongTien), COUNT(a.hoaDon.id), COUNT(a.id), COUNT(b.khachHang.id)) FROM " + HoaDonChiTiet.class.getName() + " a "
                + "RIGHT JOIN a.hoaDon b "
                + "WHERE DAY(b.ngayThanhToan) = :day "
                + "AND MONTH(b.ngayThanhToan) = :month "
                + "AND YEAR(b.ngayThanhToan) = :year "
                + "GROUP BY DAY(b.ngayThanhToan) ";
        DoanhThuTheoNgayDto dttt = new DoanhThuTheoNgayDto();
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            query.setParameter("day", day);
            query.setParameter("month", month);
            query.setParameter("year", year);
            dttt = (DoanhThuTheoNgayDto) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        return dttt;
    }
    
    public DoanhThuTheoNgayDto doanhThuTheoThang(int month, int year) {
        String hql = "SELECT new " + DoanhThuTheoNgayDto.class.getName()
                + "(SUM(b.tongTien), COUNT(a.hoaDon.id), COUNT(a.id), COUNT(b.khachHang.id)) FROM " + HoaDonChiTiet.class.getName() + " a "
                + "RIGHT JOIN a.hoaDon b "
                + "WHERE MONTH(b.ngayThanhToan) = :month "
                + "AND YEAR(b.ngayThanhToan) = :year ";
        DoanhThuTheoNgayDto dttt = new DoanhThuTheoNgayDto();
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            dttt = (DoanhThuTheoNgayDto) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dttt;
    }

    public List<DoanhThuCaNhanDto> getDoanhThuCaNhan(int day, int month, int year) {
        String hql = "SELECT new " + DoanhThuCaNhanDto.class.getName()
                + "(b.ma, SUM(a.tongTien)) FROM " + HoaDon.class.getName() + " a "
                + "LEFT JOIN a.nhanVien b "
                + "WHERE DAY(a.ngayThanhToan) = :day "
                + "AND MONTH(a.ngayThanhToan) = :month "
                + "AND YEAR(a.ngayThanhToan) = :year "
                + "GROUP BY b.ma";
        List<DoanhThuCaNhanDto> list;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            query.setParameter("day", day);
            query.setParameter("month", month);
            query.setParameter("year", year);
            list = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        return list;
    }
    
    public List<DoanhThuCaNhanDto> getDoanhThuCaNhanTheoThang(int month, int year) {
        String hql = "SELECT new " + DoanhThuCaNhanDto.class.getName()
                + "(b.ma, SUM(a.tongTien)) FROM " + HoaDon.class.getName() + " a "
                + "LEFT JOIN a.nhanVien b "
                + "WHERE MONTH(a.ngayThanhToan) = :month "
                + "AND YEAR(a.ngayThanhToan) = :year "
                + "GROUP by b.ma";
        List<DoanhThuCaNhanDto> list;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            query.setParameter("month", month);
            query.setParameter("year", year);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<ThongKeTheoHang> thongKeTheohang(UUID idHang, Date start, Date end) {
        String hql = "SELECT new " + ThongKeTheoHang.class.getName() + "(COUNT(b.dienThoai.ten), b.dienThoai.ten) "
                + "FROM HoaDonChiTiet a LEFT JOIN a.chiTietDienThoai b "
                + "LEFT JOIN a.hoaDon c "
                + "WHERE b.hang.id = :idHang AND c.ngayThanhToan BETWEEN :start AND :end "
                + "GROUP BY b.dienThoai.ten";
        List<ThongKeTheoHang> list;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            query.setParameter("idHang", idHang);
            query.setParameter("start", start);
            query.setParameter("end", end);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        ThongKeRepository x = new ThongKeRepository();
        Date start = new Date("2022/12/04");
        Date end = new Date("2022/12/05");
        for (DoanhThuCaNhanDto z : x.getDoanhThuCaNhanTheoThang(12, 2022)) {
            System.out.println(z.toString());
        }
    }

}
