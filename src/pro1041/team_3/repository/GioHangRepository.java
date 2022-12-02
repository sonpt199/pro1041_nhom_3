package pro1041.team_3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.domainModel.GioHangChiTiet;
import pro1041.team_3.dto.GioHangChiTietDto;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonpt_ph19600
 */
public class GioHangRepository extends Repository<GioHang, UUID, GioHangDto>{

    public GioHangRepository() {
        className = GioHang.class.getName();
        resCon = "new " + GioHangDto.class.getName() + "(b.id, b.ma, d.id, d.ma, d.hoTen, d.sdt, e.ma, b.ngayTao, b.lyDo) ";
        join = " left join a.gioHang b left join b.khachHang d left join b.nhanVien e "
                + "GROUP BY b.id, b.ma, d.id, d.ma, d.hoTen, d.sdt, e.ma, b.ngayTao, b.lyDo ORDER BY b.ngayTao DESC";
    }

    @Override
    public List<GioHangDto> getAllResponse() {
        List<GioHangDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + GioHangChiTiet.class.getName() + " a" + join;
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

}
