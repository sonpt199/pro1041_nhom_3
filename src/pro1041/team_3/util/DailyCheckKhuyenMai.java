package pro1041.team_3.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import org.hibernate.service.ServiceRegistry;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.repository.DienThoaiKhuyenMaiRepository;
import pro1041.team_3.repository.KhuyenMaiRepository;

/**
 *
 * @author sonpt_ph19600
 */
public class DailyCheckKhuyenMai extends Thread {

    private KhuyenMaiRepository khuyenMaiRepository;
    private DienThoaiKhuyenMaiRepository dienThoaiKhuyenMaiRepository;

    public DailyCheckKhuyenMai() {
        khuyenMaiRepository = new KhuyenMaiRepository();
        dienThoaiKhuyenMaiRepository = new DienThoaiKhuyenMaiRepository();
    }

    @Override
    public synchronized void run() {
        List<KhuyenMai> lstKhuyenMai = khuyenMaiRepository.getAllForDailyCheck();
        Date thoiGianHienTai = new Date();
        while (true) {
            for (KhuyenMai km : lstKhuyenMai) {
                Integer check = -1;
                List<DienThoaiKhuyenMai> lstDtkm = dienThoaiKhuyenMaiRepository.findByIdKhuyenMai(km.getId());
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
                    dienThoaiKhuyenMaiRepository.UpdateForThread(dtkm);
                }
            }
            try {
                Thread.sleep(60000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
