package pro1041.team_3.domainModel;

import lombok.AllArgsConstructor;
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
public class ResponseObject {

    private Boolean remote;
    
    private String message;
    
}
