/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.repository;

import java.util.UUID;
import pro1041.team_3.domainModel.Hang;

/**
 *
 * @author ADMIN
 */
public class HangRepository extends Repository<Hang, UUID, Hang>{

    public HangRepository() {
        this.className = Hang.class.getName();
        this.resCon = "new fpoly.pro1041.nhom_3.domainmodel.Hang(a.id, a.ma, a.ten)";
    }
    
}
