package pro1041.team_3.dto;

import java.util.UUID;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hanhltph27725
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HangDto {

    private UUID id;
    private String ma;
    private String ten;
    
    public Object[] toDataRow(int index) {
        return new Object[]{
            index, ma, ten
        };
    }
}
