/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.domainModel;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "[user]")
public class User implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ten_dang_nhap")
    private String tendangnhap;
    
    @Column(name = "ho_ten")
    private String hoten;
    
    @Column(name = "gioi_tinh")
    private Integer gioitinh;
    
    @Column(name = "sdt")
    private String sdt;
    
    @Column(name = "dia_chi")
    private String diachi;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "mat_khau")
    private String matkhau;
    
    @Column(name = "vai_tro")
    private Integer vaitro;
}
