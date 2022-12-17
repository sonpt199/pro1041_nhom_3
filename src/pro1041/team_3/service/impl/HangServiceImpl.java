package pro1041.team_3.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.dto.ChiTietDienThoaiDto;
import pro1041.team_3.dto.HangDto;
import pro1041.team_3.repository.HangRepository;
import pro1041.team_3.service.HangService;

/**
 *
 * @author vanntph19604
 */
public class HangServiceImpl implements HangService {

    private HangRepository hangRepository;
    private ArrayList<HangDto> _lstHang;

    public HangServiceImpl() {
        hangRepository = new HangRepository();
        _lstHang = new ArrayList<>();
    }

    @Override
    public List<Hang> getAll() {
        return this.hangRepository.getAll();
    }

    @Override
    public ArrayList<HangDto> getAllResponse() {
        _lstHang = new ArrayList<>();
        List<Hang> listHang = hangRepository.getAll();
        for (Hang x : listHang) {
            _lstHang.add(new HangDto(x.getId(), x.getMa(), x.getTen()));
        }
        return _lstHang;

    }

    @Override
    public String insert(Hang hangResponse) {
        List<Hang> list = hangRepository.getAll();
        Integer ma = 0;
        for (Hang x : list) {
            String maSoStr = x.getMa().substring(2);
            Integer maSo = Integer.parseInt(maSoStr);
            if (ma < maSo) {
                ma = maSo;
            }
        }

        hangResponse.setId(null);
        Integer maMau = ma + 1;
        hangResponse.setMa("H0" + maMau);
        if (hangResponse.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        Hang hangDT = hangRepository.findByMa(hangResponse.getMa());
        if (hangDT != null) {
            return "Mã không được trùng";
        }
        hangResponse = hangRepository.saveOrUpdate(hangResponse);
        if (hangResponse != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String delete(String ma) {
        Hang hangDT = hangRepository.findByMa(ma);
        ChiTietDienThoaiDto chiTietDienThoaiDto = hangRepository.checkHangTrongCtdt(hangDT.getId());
        if (hangDT == null) {
            return "Không tìm thấy";
        }
        if (chiTietDienThoaiDto != null) {
            return "Hãng đã tồn tại trong bảng điện thoại";
        }
        boolean delete = hangRepository.detele(hangDT);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String update(Hang hangResponse) {
        Hang hangByID = hangRepository.findByMa(hangResponse.getMa());
        if (hangByID == null) {
            return "Không tìm thấy";
        }
        if (hangResponse.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        hangByID.setMa(hangResponse.getMa());
        hangByID.setTen(hangResponse.getTen());
        hangResponse = hangRepository.saveOrUpdate(hangByID);
        if (hangResponse != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<HangDto> findHang(String ten) {
        _lstHang = new ArrayList<>();
        List<Hang> listHang = hangRepository.findHang(ten);
        for (Hang x : listHang) {
            _lstHang.add(new HangDto(x.getId(), x.getMa(), x.getTen()));
        }
        return _lstHang;
    }

    @Override
    public Hang findHangByName(String ten) {
        return hangRepository.findHangByName(ten);
    }
}
