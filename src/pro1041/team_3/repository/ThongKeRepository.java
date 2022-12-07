package pro1041.team_3.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.DoanhThuTheoThangDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonnh
 */
public class ThongKeRepository {

    private Session session;
    private Transaction trans;

    public DoanhThuTheoThangDto findHdctInterval(int day, int month, int year) {
        String hql = "SELECT new " + DoanhThuTheoThangDto.class.getName()
                + "(DAY(b.ngayThanhToan), SUM(b.tongTien), COUNT(a.hoaDon.id)) FROM " + HoaDonChiTiet.class.getName() + " a "
                + "RIGHT JOIN a.hoaDon b "
                + "WHERE DAY(b.ngayThanhToan) = :day "
                + "AND MONTH(b.ngayThanhToan) = :month "
                + "AND YEAR(b.ngayThanhToan) = :year "
                + "GROUP BY DAY(b.ngayThanhToan) ";
        DoanhThuTheoThangDto list = new DoanhThuTheoThangDto();
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            query.setParameter("day", day);
            query.setParameter("month", month);
            query.setParameter("year", year);
            list = (DoanhThuTheoThangDto) query.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
        ThongKeRepository x = new ThongKeRepository();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm");
        Date start = sdf.parse("03/12/2022 13:16");
        Date end = sdf.parse("05/12/2022 01:10");
        System.out.println(x.findHdctInterval(3, 12, 2022).toString());

    }

}
