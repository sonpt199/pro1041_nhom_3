/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hanhlt107
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
