package pro1041.team_3.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author trang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietDienThoaiDto {

    private UUID id;
    private UUID idHang;
    private UUID idMauSac;
    private UUID idDienThoai;
}
