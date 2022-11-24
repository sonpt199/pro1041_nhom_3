package pro1041.team_3.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author sonpt_ph19600
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MauSacDto {

    private UUID id;
    
    private String ma;
    
    private String ten;

    public Object[] toDataRow(int index) {
        return new Object[]{index, ma, ten};
    }

    @Override
    public String toString() {
        return ten;
    }
    
}
