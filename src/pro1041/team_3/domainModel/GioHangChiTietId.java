package pro1041.team_3.domainModel;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonpt_ph19600
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietId implements Serializable{

    private UUID gioHang;
    
    private UUID chiTietDienThoai;
    
}
