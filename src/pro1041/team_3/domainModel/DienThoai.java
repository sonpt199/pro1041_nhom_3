
package pro1041.team_3.domainModel;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author van15
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "dien_thoai")
public class DienThoai {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "thoi_gian_bao_hanh")
    private Integer thoiGianBaoHanh;
}
