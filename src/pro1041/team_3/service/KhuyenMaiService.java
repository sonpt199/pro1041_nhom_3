package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.dto.KhuyenMaiDto;

/**
 *
 * @author trangdttph27721
 */
public interface KhuyenMaiService {

    public List<KhuyenMaiDto> getAllResponse();

    public List<KhuyenMaiDto> findKhuyenMai(String key);

    public List<KhuyenMaiDto> findNgayTuongLai();
    
    List<KhuyenMaiDto> findKMDangDienRa();
    
    List<KhuyenMaiDto> findKMKetThuc();

    String insert(KhuyenMai khuyenMai);

    String update(KhuyenMai khuyenMai);
    
    KhuyenMai findById(UUID id);
}
