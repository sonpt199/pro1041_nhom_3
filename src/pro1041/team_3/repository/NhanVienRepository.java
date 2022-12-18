package pro1041.team_3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.query.Query;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.ChiTietDienThoaiDto;
import pro1041.team_3.dto.NhanVienDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author trangntph19494
 */
public class NhanVienRepository extends Repository<NhanVien, UUID, NhanVienDto> {

    public NhanVienRepository() {
        className = NhanVien.class.getName();
        resCon = "new pro1041.team_3.dto.NhanVienDto"
                + "(a.id, a.ma, a.tenDangNhap, a.hoTen, a.gioiTinh, a.sdt, a.diaChi, a.email, a.matKhau, a.trangThaiLamViec, a.vaiTro)";
        join = " ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
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

    public NhanVien findSDT(String key) {
        try {
            NhanVien nhanVien = null;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.sdt = :key";
            TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
            query.setParameter("key", key);
            List<NhanVien> lst = query.getResultList();
            if (!lst.isEmpty()) {
                nhanVien = lst.get(0);
            }
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien findEmail(String key) {
        try {
            NhanVien nhanVien = null;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.email = :key";
            TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
            query.setParameter("key", key);
            List<NhanVien> lst = query.getResultList();
            if (!lst.isEmpty()) {
                nhanVien = lst.get(0);
            }
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien findTenDangNhap(String key) {
        try {
            NhanVien nhanVien = null;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.tenDangNhap = :key";
            TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
            query.setParameter("key", key);
            List<NhanVien> lst = query.getResultList();
            if (!lst.isEmpty()) {
                nhanVien = lst.get(0);
            }
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateTrangThai(String ma) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            String hql = "update NhanVien a set a.trangThaiLamViec = 1 where a.ma = :ma";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean khoiPhucTrangThai(String ma) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            String hql = "update NhanVien a set a.trangThaiLamViec = 0 where a.ma = :ma";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            query.executeUpdate();
            trans.commit();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVienDto> getAllByTrangThai0() {
        List<NhanVienDto> lst = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThaiLamViec = 0 ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            Query query = session.createQuery(hql);
            lst = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    public List<NhanVienDto> getAllByTrangThai1() {
        try {
            List<NhanVienDto> lst = new ArrayList<>();
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.trangThaiLamViec = 1 ORDER BY CONVERT(INT, SUBSTRING(a.ma, 3, 10)) DESC";
            Query query = session.createQuery(hql);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVienDto> findNhanVien(String key) {
        try {
            List<NhanVienDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE (a.hoTen LIKE CONCAT('%', :key, '%') or a.ma LIKE CONCAT('%', :key, '%')"
                    + " or a.diaChi LIKE CONCAT('%', :key, '%') or  a.sdt LIKE CONCAT('%', :key, '%') or a.email LIKE CONCAT('%', :key, '%')"
                    + " or a.tenDangNhap LIKE CONCAT('%', :key, '%')) and a.trangThaiLamViec = 0";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVienDto> findNhanVienNghiViec(String key) {
        try {
            List<NhanVienDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE (a.hoTen LIKE CONCAT('%', :key, '%') or a.ma LIKE CONCAT('%', :key, '%')"
                    + " or a.diaChi LIKE CONCAT('%', :key, '%') or  a.sdt LIKE CONCAT('%', :key, '%') or a.email LIKE CONCAT('%', :key, '%')"
                    + " or a.tenDangNhap LIKE CONCAT('%', :key, '%')) and a.trangThaiLamViec = 1";
            Query query = session.createQuery(hql);
            query.setParameter("key", key);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien findNVByUserNameAndMatKhau(String userName, String matKhau) {
        try {
            NhanVien nhanVien = null;
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.tenDangNhap = :userName and a.matKhau = :matKhau";
            TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
            query.setParameter("userName", userName);
            query.setParameter("matKhau", matKhau);
            List<NhanVien> lst = query.getResultList();
            if (!lst.isEmpty()) {
                nhanVien = lst.get(0);
            }
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateMatKhau(UUID id, String matKhau) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            String hql = "update NhanVien a set a.matKhau = :matKhau WHERE a.id = :id";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("matKhau", matKhau);
            query.setParameter("id", id);
            query.executeUpdate();
            trans.commit();
            session.close();
            return true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public long getMaOrderBy() {
        long ma = 0;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT CONVERT(BIGINT, SUBSTRING(a.ma, 3)) FROM " + className + " a ORDER BY CONVERT(BIGINT, SUBSTRING(a.ma, 3))";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            ma = query.getFirstResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }

}
