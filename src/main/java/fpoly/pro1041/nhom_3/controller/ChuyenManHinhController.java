/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpoly.pro1041.nhom_3.controller;

import fpoly.pro1041.nhom_3.domainModel.DanhMucBean;
import fpoly.pro1041.nhom_3.views.BanHangJPanel;
import fpoly.pro1041.nhom_3.views.HoaDonJPanel;
import fpoly.pro1041.nhom_3.views.KhachHangJPanel;
import fpoly.pro1041.nhom_3.views.KhuyenMaiJPanel;
import fpoly.pro1041.nhom_3.views.SanPhanJPanel;
import fpoly.pro1041.nhom_3.views.TaiKhoanJPanel;
import fpoly.pro1041.nhom_3.views.ThongKeJPanel;
import fpoly.pro1041.nhom_3.views.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author van15
 */
public class ChuyenManHinhController {

    private JPanel root;
    private String loaiSelected = "";
    
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel root) {
        this.root = root;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        loaiSelected = "TrangChu";
        jpnItem.setBackground(new Color(1, 163, 231));
        jlbItem.setBackground(new Color(1, 163, 231));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getLoai(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;

        private String loai;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String loai, JPanel jpnItem, JLabel jpbItem) {
            this.loai = loai;
            this.jpnItem = jpnItem;
            this.jlbItem = jpbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (loai) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "SanPham":
                    node = new SanPhanJPanel();
                    break;
                case "BanHang":
                    node = new BanHangJPanel();
                    break;
                case "HoaDon":
                    node = new HoaDonJPanel();
                    break;
                case "KhuyenMai":
                    node = new KhuyenMaiJPanel();
                    break;
                case "TaiKhoan":
                    node = new TaiKhoanJPanel();
                    break;
                case "KhachHang":
                    node = new KhachHangJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                default:
                    node = new TrangChuJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(loai);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            loaiSelected = loai;
            jpnItem.setBackground(new Color(1, 163, 231));
            jlbItem.setBackground(new Color(1, 163, 231));

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(1, 163, 231));
            jlbItem.setBackground(new Color(1, 163, 231));

        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (loaiSelected.equalsIgnoreCase(loai)) {
                jpnItem.setBackground(new Color(1, 187, 203));
                jlbItem.setBackground(new Color(1, 187, 203));
            }

        }

    }
    
    private void setChangeBackground(String loai){
        for (DanhMucBean item : listItem) {
            if (item.getLoai().equalsIgnoreCase(loai)) {
                item.getJpn().setBackground(new Color(1, 163, 231));
                item.getJlb().setBackground(new Color(1, 163, 231));
            } else {
                item.getJpn().setBackground(new Color(1, 187, 203));
                item.getJlb().setBackground(new Color(1, 187, 203));
            }
        }
    }

}
