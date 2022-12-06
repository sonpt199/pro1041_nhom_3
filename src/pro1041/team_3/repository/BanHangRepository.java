package pro1041.team_3.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.GioHangChiTiet;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.dto.HoaDonDto;
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
                    + "(a.id, a.dienThoai.ma, a.mauSac.ten, a.dienThoai.ten, a.hang.ten, "
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
            dtkm = (DienThoaiKhuyenMai) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dtkm;
    }

    public List<GioHangDto> getGioHangByIdSp(UUID idChiTietDienThoai) {
        List<GioHangDto> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new " + GioHangDto.class.getName() + "(b.id, b.ma, c.id, c.ma, c.hoTen, c.sdt, d.ma, b.ngayTao, b.lyDo) "
                    + "FROM " + GioHangChiTiet.class.getName() + " a "
                    + "LEFT JOIN a.gioHang b LEFT JOIN b.khachHang c LEFT JOIN b.nhanVien d WHERE a.chiTietDienThoai.id = :idChiTietDienThoai";
            Query query = session.createQuery(hql);
            query.setParameter("idChiTietDienThoai", idChiTietDienThoai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public HoaDonDto getHoaDonByIdSp(UUID idChiTietDienThoai) {
        HoaDonDto hoaDon = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new " + HoaDonDto.class.getName() + ""
                    + "(b.id, b.ma, c.ma, c.hoTen, c.sdt, d.ma, d.hoTen, "
                    + "b.ngayThanhToan, b.hinhThucThanhToan, b.tienMat, "
                    + "b.nganHang, b.maGiaoDich, b.tongTien) "
                    + "FROM " + HoaDonChiTiet.class.getName() + " a "
                    + "LEFT JOIN a.hoaDon b LEFT JOIN b.khachHang c LEFT JOIN b.nhanVien d WHERE a.chiTietDienThoai.id = :idChiTietDienThoai";
            Query query = session.createQuery(hql);
            query.setParameter("idChiTietDienThoai", idChiTietDienThoai);
            hoaDon = (HoaDonDto) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hoaDon;
    }

    public static void main(String[] args) {
        BanHangRepository x = new BanHangRepository();
        System.out.println(x.getHoaDonByIdSp(UUID.fromString("c758277c-f33d-c54f-98a4-e29c4a094c7a")).toString());;
    }

}
