package pro1041.team_3.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.domainModel.GioHangChiTiet;
import pro1041.team_3.domainModel.HoaDon;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.dto.GioHangRequest;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.dto.HoaDonRequest;
import pro1041.team_3.repository.BanHangRepository;
import pro1041.team_3.repository.ChiTietDienThoaiRepository;
import pro1041.team_3.repository.GioHangChiTietRepository;
import pro1041.team_3.repository.GioHangRepository;
import pro1041.team_3.repository.HoaDonChiTietRepository;
import pro1041.team_3.repository.HoaDonRepository;
import pro1041.team_3.service.BanHangService;
import pro1041.team_3.util.ExportBill;

/**
 *
 * @author sonpt_ph19600
 */
public class BanHangServiceImpl implements BanHangService {

    private HoaDonRepository hoaDonRepository;
    private HoaDonChiTietRepository hoaDonChiTietRepository;
//    private ChiTietDienThoaiRepository chiTietDienThoaiRepository;
    private BanHangRepository banHangRepository;
    private GioHangRepository gioHangRepository;
    private GioHangChiTietRepository gioHangChiTietRepository;

    public BanHangServiceImpl() {
        hoaDonRepository = new HoaDonRepository();
        hoaDonChiTietRepository = new HoaDonChiTietRepository();
        banHangRepository = new BanHangRepository();
        gioHangRepository = new GioHangRepository();
        gioHangChiTietRepository = new GioHangChiTietRepository();
    }

    @Override
    public String thanhToan(List<HoaDonRequest> lstSp, String path) {
        List<HoaDonChiTiet> lstHoaDonChiTiet = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        String maHd = "HD" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHd);
        hoaDon.setNhanVien(lstSp.get(0).getNhanVien());
        hoaDon.setNgayThanhToan(new Date());
        hoaDon.setKhachHang(lstSp.get(0).getKhachHang());
        hoaDon.setNhanVien(lstSp.get(0).getNhanVien());
        hoaDon.setTongTien(lstSp.get(0).getTongTien());
        hoaDon.setTienMat(lstSp.get(0).getTienMat());
        hoaDon.setNganHang(lstSp.get(0).getNganHang());
        hoaDon.setMaGiaoDich(lstSp.get(0).getMaGiaoDich());
        hoaDon.setHinhThucThanhToan(lstSp.get(0).getHinhThucThanhToan());
        if (hoaDonRepository.saveOrUpdate(hoaDon) == null) {
            return "Lỗi hệ thống. Không thể tạo hóa đơn";
        }
        for (HoaDonRequest x : lstSp) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            ChiTietDienThoai ctdt = new ChiTietDienThoai();
            ctdt.setId(x.getIdChiTietDienThoai());
            hdct.setChiTietDienThoai(ctdt);
            hdct.setDonGia(x.getDonGia());
            hdct.setGiaBan(x.getGiaBan());
            lstHoaDonChiTiet.add(hdct);
            if (!banHangRepository.updateTrangThai(x.getIdChiTietDienThoai(), 1)) {
                return "Lỗi hệ thống. Không thể thay đổi trạng thái của sản phẩm";
            }
        }
        if (!hoaDonChiTietRepository.saveAll(lstHoaDonChiTiet)) {
            return "Lỗi hệ thống. Không thể thêm sản phẩm vào hóa đơn";
        }
        if (path != null) {
            HoaDonDto hoaDonDone = hoaDonRepository.findResponseById(hoaDon.getId());
            List<HoaDonChiTietDto> lst = hoaDonChiTietRepository.getAllByIdHoaDon(hoaDon.getId());
            ExportBill pdf = new ExportBill();
            pdf.docPDF(hoaDonDone, lst, path);
        }
        return "Thanh toán thành công";
    }

    @Override
    public BhChiTietDienThoaiDto bhFindByImei(String keyWord) {
        return banHangRepository.bhFindByImei(keyWord);
    }

    @Override
    public DienThoaiKhuyenMai getGiamGia(UUID idChiTietSanPham) {
        return banHangRepository.getGiamGia(idChiTietSanPham);
    }

    @Override
    public String treoHoaDon(List<GioHangRequest> lstSp) {
        List<GioHangChiTiet> lstGioHangChiTiet = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        String maGh = "GH" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
        GioHang gioHang = new GioHang();
        gioHang.setMa(maGh);
        gioHang.setKhachHang(lstSp.get(0).getKhachHang());
        gioHang.setNhanVien(lstSp.get(0).getNhanVien());
        gioHang.setNgayTao(new Date());
        gioHang.setLyDo(lstSp.get(0).getLyDo());
        if (gioHangRepository.saveOrUpdate(gioHang) == null) {
            return "Lỗi hệ thống. Không thể treo giỏ hàng";
        }
        for (GioHangRequest x : lstSp) {
            GioHangChiTiet ghct = new GioHangChiTiet();
            ghct.setGioHang(gioHang);
            ChiTietDienThoai ctdt = new ChiTietDienThoai();
            ctdt.setId(x.getIdChiTietDienThoai());
            ghct.setChiTietDienThoai(ctdt);
            lstGioHangChiTiet.add(ghct);
        }
        if (!gioHangChiTietRepository.saveAll(lstGioHangChiTiet)) {
            return "Lỗi hệ thống. Không thể thêm sản phẩm vào giỏ hàng treo";
        }
        return "Treo giỏ hàng thành công";
    }

    public void test() {
//        HoaDonDto hoaDon = hoaDonRepository.findResponseById(UUID.fromString("8c64f3cc-7c8e-4027-a8ec-f8221e415903"));
//        List<HoaDonChiTietDto> lst = hoaDonChiTietRepository.getAllByIdHoaDon(UUID.fromString("8c64f3cc-7c8e-4027-a8ec-f8221e415903"));
//        ExportBill pdf = new ExportBill();
//        pdf.docPDF(hoaDon, lst);
    }

    @Override
    public List<GioHangDto> getGioHangByIdSp(UUID idChiTietDienThoai) {
        return banHangRepository.getGioHangByIdSp(idChiTietDienThoai);
    }

    @Override
    public HoaDonDto getHoaDonByIdSp(UUID idChiTietDienThoai) {
        return banHangRepository.getHoaDonByIdSp(idChiTietDienThoai);
    }
}
