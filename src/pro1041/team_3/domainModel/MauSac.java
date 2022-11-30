package pro1041.team_3.domainModel;

import java.io.Serializable;
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
 * @author sonpt_ph19600
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mau_sac")
public class MauSac implements Serializable{
        
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
    
    @Column(name = "ten")
    private String ten;

}
