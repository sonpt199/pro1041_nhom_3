package pro1041.team_3.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.repository.HoaDonChiTietRepository;
import pro1041.team_3.repository.HoaDonRepository;
import pro1041.team_3.service.HoaDonService;
import pro1041.team_3.util.ExportBill;

/**
 *
 * @author sonpt_ph19600
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonRepository hoaDonRepository;
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public HoaDonServiceImpl() {
        hoaDonRepository = new HoaDonRepository();
        hoaDonChiTietRepository = new HoaDonChiTietRepository();
    }

    @Override
    public List<HoaDonDto> getAllResponse() {
        return hoaDonRepository.getAllResponse();
    }

    @Override
    public List<HoaDonDto> findHoaDonByKhachHang(String maKH) {
        return hoaDonRepository.findHoaDonByKhachHang(maKH);
    }

    @Override
    public List<HoaDonDto> findHoaDon(String key) {
        return hoaDonRepository.findHoaDon(key);
    }

    @Override
    public List<HoaDonDto> locHinhThucThanhToan(Integer httt) {
        return hoaDonRepository.locHinhThucThanhToan(httt);
    }

    @Override
    public List<HoaDonDto> locHoaDonTheoNgay(Date ngay1, Date ngay2) {
        return hoaDonRepository.locHoaDonTheoNgay(ngay1, ngay2);
    }

    @Override
    public List<HoaDonDto> locHoaDonTheoTongTien(BigDecimal tien1, BigDecimal tien2) {
        return hoaDonRepository.locHoaDonTheoTongTien(tien1, tien2);
    }

    @Override
    public Boolean exportPdf(String path, UUID idHoaDon) {
        HoaDonDto hoaDonDone = hoaDonRepository.findResponseById(idHoaDon);
        List<HoaDonChiTietDto> lst = hoaDonChiTietRepository.getAllByIdHoaDon(idHoaDon);
        ExportBill exportBill = new ExportBill();
        if (exportBill.docPDF(hoaDonDone, lst, path, true) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public HoaDonDto findHoaDonForNhanVien(String key) {
        return hoaDonRepository.findHoaDonForNhanVien(key);
    }

}
