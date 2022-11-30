package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.dto.GioHangDto;

/**
 *
 * @author sonpt_ph19600
 */
public interface GioHangService {

    List<GioHangDto> getAllResponse();

    String delete(UUID idGioHang);    
}
