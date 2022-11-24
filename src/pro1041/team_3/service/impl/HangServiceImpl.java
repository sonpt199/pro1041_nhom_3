/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.service.impl;

import java.util.List;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.repository.HangRepository;
import pro1041.team_3.service.HangService;

/**
 *
 * @author ADMIN
 */
public class HangServiceImpl implements HangService{

    private HangRepository hangRepository;
    public HangServiceImpl() {
        this.hangRepository = new HangRepository();
    }

    @Override
    public List<Hang> getAll() {
        return this.hangRepository.getAll();
    }
    
}
