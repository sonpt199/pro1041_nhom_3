package pro1041.team_3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonpt_ph19600
 */
public class HoaDonChiTietRepository extends Repository<HoaDonChiTiet, UUID, HoaDonChiTietDto>{

    public HoaDonChiTietRepository() {
        className = HoaDonChiTiet.class.getName();
        resCon = "new " + HoaDonChiTietDto.class.getName() 
                + "(b.ma, b.mauSac.ten, b.dienThoai.ten, b.hang.ten, b.tinhTrang)";
    }

    public List<HoaDonChiTietDto> getAllByIdHoaDon(UUID idHoaDon) {
        List<HoaDonChiTietDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new " + HoaDonChiTietDto.class.getName() 
                    + "(b.ma, b.mauSac.ten, b.dienThoai.ten, b.hang.ten, "
                    + "b.tinhTrang, a.donGia, a.giaBan, b.imei, b.ram, b.boNho, b.moTa) FROM " 
                    + className + " a LEFT JOIN a.chiTietDienThoai b WHERE a.hoaDon.id = :idHoaDon";
            Query query = session.createQuery(hql);
            query.setParameter("idHoaDon", idHoaDon);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }    
    
}
