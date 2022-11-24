package pro1041.team_3.repository;

import pro1041.team_3.domainmodel.TaiKhoan;
import pro1041.team_3.dto.TaiKhoanDto;
import java.util.UUID;

/**
 *
 * @author sonpt_ph19600
 */
public class UserRepository extends Repository<TaiKhoan, UUID, TaiKhoanDto>{

    public UserRepository() {
        className = TaiKhoan.class.getName();
        resCon = "new fpoly.pro1041.nhom_3.dto.";
    }
    
}
