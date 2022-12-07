package pro1041.team_3.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.DoanhThuTheoThangDto;
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
    public List<DoanhThuTheoThangDto> theoThang(int month, int year) {
        List<DoanhThuTheoThangDto> lst = new ArrayList<>();
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            for (int i = 1; i <= 31; i++) {
                DoanhThuTheoThangDto doanhThu = thongKeRepository.findHdctInterval(i, month, year);
                if (doanhThu == null) {
                    doanhThu = new DoanhThuTheoThangDto(i, BigDecimal.ZERO,0l);
                }
                lst.add(doanhThu);
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            for (int i = 1; i <= 30; i++) {
                DoanhThuTheoThangDto doanhThu = thongKeRepository.findHdctInterval(i, month, year);
                if (doanhThu == null) {
                    doanhThu = new DoanhThuTheoThangDto(i, BigDecimal.ZERO,0l);
                }
                lst.add(doanhThu);
            }
        } else if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                for (int i = 1; i <= 29; i++) {
                    DoanhThuTheoThangDto doanhThu = thongKeRepository.findHdctInterval(i, month, year);
                    if (doanhThu == null) {
                        doanhThu = new DoanhThuTheoThangDto(i, BigDecimal.ZERO,0l);
                    }
                    lst.add(doanhThu);
                }
            } else {
                for (int i = 1; i <= 28; i++) {
                    DoanhThuTheoThangDto doanhThu = thongKeRepository.findHdctInterval(i, month, year);
                    if (doanhThu == null) {
                        doanhThu = new DoanhThuTheoThangDto(i, BigDecimal.ZERO,0l);
                    }
                    lst.add(doanhThu);
                }
            }
        } else {
            return null;
        }
        return lst;
    }

    @Override
    public DoanhThuTheoThangDto theoNgay(int day, int month, int year) {
        return thongKeRepository.findHdctInterval(day, month, year);
    }

}
