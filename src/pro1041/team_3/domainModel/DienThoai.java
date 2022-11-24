<<<<<<< HEAD
<<<<<<< HEAD

=======
=======
>>>>>>> hanhlt
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<< HEAD
>>>>>>> trangdtt
=======
>>>>>>> hanhlt
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
<<<<<<< HEAD
<<<<<<< HEAD
 * @author van15
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "dien_thoai")
public class DienThoai {
    
=======
 * @author ADMIN
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dien_thoai")
public class DienThoai {
>>>>>>> trangdtt
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
<<<<<<< HEAD
    
=======

>>>>>>> trangdtt
=======
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dien_thoai")
public class DienThoai {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

>>>>>>> hanhlt
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "thoi_gian_bao_hanh")
<<<<<<< HEAD
<<<<<<< HEAD
    private Integer thoiGianBaoHanh;
=======
    private int thoiGianBaoHanh;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    @Override
    public String toString() {
        return ten;
    }
    
    
    
>>>>>>> trangdtt
=======
    private Integer thoiGianBaoHanh;
>>>>>>> hanhlt
}
