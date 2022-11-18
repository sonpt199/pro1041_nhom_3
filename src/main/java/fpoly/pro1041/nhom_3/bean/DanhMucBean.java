/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpoly.pro1041.nhom_3.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author van15
 */
public class DanhMucBean {
    private String loai;
    private JPanel jpn;
    private JLabel jlb;

    public DanhMucBean() {
    }

    public DanhMucBean(String loai, JPanel jpn, JLabel jlb) {
        this.loai = loai;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }
    
    
}
