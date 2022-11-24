<<<<<<< HEAD

package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.dto.DienThoaiDto;

/**
 *
 * @author van15
 */
public interface DienThoaiService {
    
    List<DienThoaiDto> getAllResponse();
    
    String insert(DienThoai dienthoai);
    
    String update(DienThoai dienthoai);
    
    String delete(UUID id);
    
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pro1041.team_3.service;

import java.util.List;
import pro1041.team_3.domainModel.DienThoai;

public interface DienThoaiService {
>>>>>>> trangdtt
    List<DienThoai> getAll();
}
