package pro1041.team_3.service.impl;

import java.util.List;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.repository.HoaDonRepository;
import pro1041.team_3.service.HoaDonService;

/**
 *
 * @author sonpt_ph19600
 */
public class HoaDonServiceImpl implements HoaDonService{
    
    private HoaDonRepository hoaDonRepository;

    public HoaDonServiceImpl() {
        hoaDonRepository = new HoaDonRepository();
    }
        
    @Override
    public List<HoaDonDto> getAllResponse() {
        return hoaDonRepository.getAllResponse();
    }
    
}
