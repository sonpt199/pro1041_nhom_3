package pro1041.team_3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import pro1041.team_3.domainModel.GioHangChiTiet;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangChiTietDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author sonpt_ph19600
 */
public class GioHangChiTietRepository extends Repository<GioHangChiTiet, UUID, GioHangChiTietDto>{

    public GioHangChiTietRepository() {
        className = GioHangChiTiet.class.getName();
        resCon = " new " + GioHangChiTietDto.class.getName() + "(a.gioHang, a.chiTietDienThoai.id)";
    }        
    
    public List<BhChiTietDienThoaiDto> getAllByIdGioHang(UUID idGioHang) {
        List<BhChiTietDienThoaiDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new " + BhChiTietDienThoaiDto.class.getName() + "(b.id, b.ma, b.mauSac.ten, b.dienThoai.ten, b.hang.ten, b.tinhTrang, b.donGia, b.trangThai, b.imei, b.ram, b.boNho, b.moTa) FROM " + className + " a "
                    + "LEFT JOIN a.chiTietDienThoai b WHERE a.gioHang.id = :idGioHang";
            Query query = session.createQuery(hql);
            query.setParameter("idGioHang", idGioHang);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    } 

    public boolean deteleByIdGioHang(UUID idGioHang) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            String hql = "DELETE " + className + " a WHERE a.gioHang.id = :idGioHang";
            Query query = session.createQuery(hql);
            query.setParameter("idGioHang", idGioHang);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
    }
            
}
