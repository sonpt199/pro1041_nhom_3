package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.NhanVienDto;

/**
 *
 * @author trangntph19494
 */
public interface NhanVienService {
    List<NhanVienDto> getAllReponse();
    
    String insert(NhanVien user);
    
    String update(NhanVien user);
    
    String delete(UUID id);
    
    List<NhanVien> getAll();
    
    List<NhanVienDto> findByTenDangNhap(String keyWord);
}
