package pro1041.team_3.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.dto.HangDto;

/**
 *
 * @author vanntph19604
 */
public interface HangService {

    ArrayList<HangDto> getAllResponse();
    
    List<Hang> getAll();

    String insert(Hang hang);

    String delete(String ma);

    String update(Hang hang);

    List<HangDto> findHang(String ten);

    Hang findHangByName(String ten);
}
