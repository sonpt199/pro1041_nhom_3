package pro1041.team_3.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Synchronize;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.domainModel.GioHangChiTiet;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.HoaDon;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.repository.DienThoaiKhuyenMaiRepository;
import pro1041.team_3.repository.KhuyenMaiRepository;

/**
 *
 * @author sonpt_ph19600
 */
public class DailyCheckKhuyenMai extends Thread {

//    private KhuyenMaiRepository khuyenMaiRepository;
//    private DienThoaiKhuyenMaiRepository dienThoaiKhuyenMaiRepository;
    private static final SessionFactory FACTORY;
    private Session session;
    private Transaction trans;
    private static Session SESSION;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=quan_ly_ban_dien_thoai");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "create");

//        Thêm bảng cái add thêm zô đây để có thể kết nối với DB
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(DienThoai.class);
        conf.addAnnotatedClass(Hang.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(ChiTietDienThoai.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(DienThoaiKhuyenMai.class);
        conf.addAnnotatedClass(GioHang.class);
        conf.addAnnotatedClass(GioHangChiTiet.class);
        conf.setProperties(properties);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static Session getSession() {
        if (SESSION == null || !SESSION.isConnected()) {
            SESSION = FACTORY.openSession();
        }
        return SESSION;
    }

    @Override
    public synchronized void run() {
        List<KhuyenMai> lstKhuyenMai = getAllKm();
        Date thoiGianHienTai = new Date();
        while (true) {
            for (KhuyenMai km : lstKhuyenMai) {
                Integer check = -1;
                List<DienThoaiKhuyenMai> lstDtkm = findByIdKhuyenMai(km.getId());
                if (km.getNgayBatDau().getTime() <= thoiGianHienTai.getTime() && km.getNgayKetThuc().getTime() >= thoiGianHienTai.getTime()) {
                    check = 1;
                } else if (km.getNgayKetThuc().getTime() < thoiGianHienTai.getTime()) {
                    check = 0;
                } else if (km.getNgayBatDau().getTime() > thoiGianHienTai.getTime()) {
                    check = 2;
                }
                for (DienThoaiKhuyenMai dtkm : lstDtkm) {
                    if (check == 0) {
                        dtkm.setTrangThai(0);
                    } else if (check == 1) {
                        dtkm.setTrangThai(1);
                    } else if (check == 2) {
                        dtkm.setTrangThai(2);
                    }
                    System.out.println(dtkm.getKhuyenMai().getId() + "---------" +  dtkm.getTrangThai());
                    saveOrUpdate(dtkm);
                }

            }
            try {
                Thread.sleep(60000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DienThoaiKhuyenMai saveOrUpdate(DienThoaiKhuyenMai entity) {
        try {
            session = getSession();
            trans = session.beginTransaction();
            session.saveOrUpdate(entity);
            trans.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    public List<KhuyenMai> getAllKm() {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            session = getSession();
            String hql = "SELECT a FROM " + KhuyenMai.class.getName() + " a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<DienThoaiKhuyenMai> findByIdKhuyenMai(UUID idKhuyenMai) {
        List<DienThoaiKhuyenMai> lstDtkm = new ArrayList<>();
        try {
            session = getSession();
            String hql = "SELECT a FROM DienThoaiKhuyenMai a WHERE a.khuyenMai.id = :idKhuyenMai";

            Query query = session.createQuery(hql);
            query.setParameter("idKhuyenMai", idKhuyenMai);
            lstDtkm = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDtkm;
    }

}
