/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.domainModel;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hanhltph27725
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DienThoaiKhuyenMaiId implements Serializable{
    private UUID khuyenMai;
    
    private UUID chiTietDienThoai;
}
