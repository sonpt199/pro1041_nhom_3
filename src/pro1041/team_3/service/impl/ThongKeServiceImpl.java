package pro1041.team_3.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.DoanhThuCaNhanDto;
import pro1041.team_3.dto.DoanhThuTheoNgayDto;
import pro1041.team_3.dto.ThongKeTheoHang;
import pro1041.team_3.repository.ThongKeRepository;
import pro1041.team_3.service.ThongKeService;

/**
 *
 * @author sonpt_ph19600
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeRepository thongKeRepository;

    public ThongKeServiceImpl() {
        thongKeRepository = new ThongKeRepository();
    }

    @Override
    public List<DoanhThuTheoNgayDto> theoKhoangThoiGian(Date start, Date end) {
        List<DoanhThuTheoNgayDto> lst = new ArrayList<>();
        LocalDateTime local = LocalDateTime.ofInstant(start.toInstant(), ZoneId.of("Asia/Ho_Chi_Minh"));
            Integer ngay = local.getDayOfMonth();
            Integer thang = local.getMonthValue();
            Integer nam = local.getYear();
            DoanhThuTheoNgayDto doanhThu = thongKeRepository.doanhThuTheoNgay(ngay, thang, nam);
            if (doanhThu == null) {
                doanhThu = new DoanhThuTheoNgayDto(ngay, thang, nam, BigDecimal.ZERO, 0l, 0l, 0l);
            } else {
                doanhThu.setNgay(ngay);
                doanhThu.setThang(thang);
                doanhThu.setNam(nam);
            }
            lst.add(doanhThu);
        while (true) {
            Calendar day = Calendar.getInstance();
            day.setTime(start);
            day.add(Calendar.DATE, 1);
            start = day.getTime();
            if (start.compareTo(end) == 1) {
                break;
            }
            local = LocalDateTime.ofInstant(start.toInstant(), ZoneId.of("Asia/Ho_Chi_Minh"));
            ngay = local.getDayOfMonth();
            thang = local.getMonthValue();
            nam = local.getYear();
            doanhThu = thongKeRepository.doanhThuTheoNgay(ngay, thang, nam);
            if (doanhThu == null) {
                doanhThu = new DoanhThuTheoNgayDto(ngay, thang, nam, BigDecimal.ZERO, 0l, 0l, 0l);
            } else {
                doanhThu.setNgay(ngay);
                doanhThu.setThang(thang);
                doanhThu.setNam(nam);
            }
            lst.add(doanhThu);
        }
        return lst;
    }

    @Override
    public DoanhThuTheoNgayDto theoNgay(int day, int month, int year) {        
        return thongKeRepository.doanhThuTheoNgay(day, month, year);
    }

    @Override
    public List<DoanhThuCaNhanDto> getDoanhThuCaNhanTheoNgay(int day, int month, int year) {
        return thongKeRepository.getDoanhThuCaNhan(day, month, year);
    }

    @Override
    public List<ThongKeTheoHang> thongKeTheohang(UUID idHang, Date start, Date end) {
        return thongKeRepository.thongKeTheohang(idHang, start, end);
    }

    @Override
    public DoanhThuTheoNgayDto theoThang(int month, int year) {
        DoanhThuTheoNgayDto doanhThu = thongKeRepository.doanhThuTheoThang(month, year);
        doanhThu.setThang(month);
        doanhThu.setNam(year);
        return doanhThu;
    }

    @Override
    public List<DoanhThuCaNhanDto> getDoanhThuCaNhanTheoThang(int month, int year) {
        return thongKeRepository.getDoanhThuCaNhanTheoThang(month, year);
    }

}
