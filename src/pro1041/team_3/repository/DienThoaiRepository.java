package pro1041.team_3.repository;

import java.util.UUID;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.dto.DienThoaiDto;

/**
 *
 * @author van15
 */
public class DienThoaiRepository extends Repository<DienThoai, UUID, DienThoaiDto> {

    public DienThoaiRepository() {
        className = DienThoai.class.getName();
        resCon = "new pro1041.team_3.dto.DienThoaiDto(a.id, a.ma, a.ten)";
    }

}
