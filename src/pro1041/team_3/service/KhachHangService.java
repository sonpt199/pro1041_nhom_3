package pro1041.team_3.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.dto.KhachHangDto;

/**
 *
 * @author hanhltph27725
 */
public interface KhachHangService {

    ArrayList<KhachHangDto> getAll();

    String insert(KhachHang khachHang);

    String delete(UUID id);

    String update(KhachHang khachHang);

    List<KhachHangDto> findKhachHang(String key);
}
