package pro1041.team_3.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.query.Query;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.NhanVienDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author trangntph19494
 */
public class NhanVienRepository extends Repository<NhanVien, UUID, NhanVienDto>{

    public NhanVienRepository() {
        className = NhanVien.class.getName();
        resCon = "new pro1041.team_3.dto.NhanVienDto"
                + "(a.id, a.tenDangNhap, a.hoTen, a.gioiTinh, a.sdt, a.diaChi, a.email, a.matKhau, a.vaiTro)"; 
    }
    
    public List<NhanVienDto> findByTenDangNhap(String keyWord) {
        try {
            List<NhanVienDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.tendangnhap LIKE CONCAT('%', :keyWord, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("keyWord", keyWord);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
