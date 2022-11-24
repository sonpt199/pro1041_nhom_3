/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pro1041.team_3.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.dto.HangDto;

/**
 *
 * @author Admin
 */
public interface IHangService {

    ArrayList<HangDto> getAll();

    String insert(Hang hang);

    String delete(UUID id);

    String update(Hang hang);

    List<HangDto> findHang(String ten);

}
