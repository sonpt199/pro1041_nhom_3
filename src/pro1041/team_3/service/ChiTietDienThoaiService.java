package pro1041.team_3.service;


import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;

/**
 *
 * @author trangdttph27721
 */
public interface ChiTietDienThoaiService {

    List<ChiTietDienThoaiResponse> getAllResponse();

    List<ChiTietDienThoaiResponse> getAllByTinhTrang1();

    List<ChiTietDienThoaiResponse> getAllByTinhTrang0();

    void insert(ChiTietDienThoai chiTietSP);

    void update(ChiTietDienThoai chiTietSP);

    String delete(String ma);

    ChiTietDienThoai getID(String ma);

    List<ChiTietDienThoaiResponse> findBy(String keyWord);

    List<ChiTietDienThoaiResponse> sapXep(String chieu);

    List<ChiTietDienThoaiResponse> sapXep2(String loai, String chieu);

    ChiTietDienThoai anSanPham(String ma);
}
