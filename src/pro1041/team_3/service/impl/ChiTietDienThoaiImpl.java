/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.repository.ChiTietDienThoaiRepository;
import pro1041.team_3.service.ChiTietDienThoaiService;

/**
 *
 * @author ADMIN
 */
public class ChiTietDienThoaiImpl implements ChiTietDienThoaiService{

    private ChiTietDienThoaiRepository chiTietDienThoaiRepository;
    
    public ChiTietDienThoaiImpl() {
        this.chiTietDienThoaiRepository = new ChiTietDienThoaiRepository();
    }

    
    @Override
    public List<ChiTietDienThoaiResponse> getAllResponse() {
        return this.chiTietDienThoaiRepository.getAllResponse();
    }

    @Override
    public void insert(ChiTietDienThoai chiTietDT) {
        this.chiTietDienThoaiRepository.saveOrUpdate(chiTietDT);
    }

    @Override
    public void update(ChiTietDienThoai chiTietDT) {
        this.chiTietDienThoaiRepository.saveOrUpdate(chiTietDT);
    }

    @Override
    public String delete(String id) {
        ChiTietDienThoai chiTietDT = chiTietDienThoaiRepository.findByMa(id);
        if (chiTietDT == null) {
            return "Chi tiết sản phẩm không tồn tại";
        }
        if (chiTietDienThoaiRepository.detele(chiTietDT)) {
            return "Xóa thành công";
        } else {
            return "Lỗi. Xóa thất bại";
        }
    }

    @Override
    public ChiTietDienThoai getID(String ma) {
        ChiTietDienThoai chiTietDT = chiTietDienThoaiRepository.findByMa(ma);
        return chiTietDT;
    }

    @Override
    public List<ChiTietDienThoaiResponse> findBy(String keyWord) {
        return this.chiTietDienThoaiRepository.findBy(keyWord);
    }

    @Override
    public List<ChiTietDienThoaiResponse> sapXep(String chieu) {
        return this.chiTietDienThoaiRepository.sapXep(chieu);
    }
    
    @Override
    public List<ChiTietDienThoaiResponse> sapXep2(String loai, String chieu) {
        return this.chiTietDienThoaiRepository.sapXep2(loai, chieu);
    }

    @Override
    public ChiTietDienThoai anSanPham(String ma) {
       ChiTietDienThoai chiTietDT = chiTietDienThoaiRepository.findByMa(ma);
       return chiTietDT;
    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllByTinhTrang1() {
        return this.chiTietDienThoaiRepository.getAllByTinhTrang1();
    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllByTinhTrang0() {
        return this.chiTietDienThoaiRepository.getAllByTinhTrang0();
    }
    
    
}
