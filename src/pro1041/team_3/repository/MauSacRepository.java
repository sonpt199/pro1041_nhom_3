package pro1041.team_3.repository;

import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.MauSacDto;
import java.util.UUID;

/**
 *
 * @author sonpt_ph19600
 */
public class MauSacRepository extends Repository<MauSac, UUID, MauSacDto> {

    public MauSacRepository() {
        className = MauSac.class.getName();
        resCon = "new pro1041.team_3.dto.MauSacDto(a.id, a.ma, a.ten)";
    }
    
}
