package pro1041.team_3.service;

import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.MauSacDto;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author vanntph19604
 */
public interface MauSacService {

    List<MauSacDto> getAllResponse();

    String insert(MauSac mausac);

    String update(MauSac mausac);

    String delete(String ma);

    List<MauSac> getAll();
    
    List<MauSacDto> findByName(String keyWord);
    
    public MauSac findMauSacByName(String ten);
}
