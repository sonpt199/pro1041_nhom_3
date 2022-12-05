package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.dto.DienThoaiDto;

/**
 *
 * @author vanntph19604
 */
public interface DienThoaiService {
    
    List<DienThoaiDto> getAllResponse();
    
    String insert(DienThoai dienthoai);
    
    String update(DienThoai dienthoai);
    
    String delete(String ma);
    
    List<DienThoai> getAll();
    
    List<DienThoaiDto> findByName(String keyWord);
    
}
