/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.dto;

/**
 *
 * @author ADMIN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String tendangnhap;
    private String hoten;
    private Integer gioitinh;
    private String sdt;
    private String diachi;
    private String email;
    private String matkhau;
    private Integer vaitro;
    
    public Object[] toDataRow() {
        return new Object[]{id, tendangnhap, hoten, gioitinh, sdt, diachi, email, matkhau, vaitro};
    }
}
