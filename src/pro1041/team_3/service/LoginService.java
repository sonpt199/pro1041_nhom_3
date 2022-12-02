package pro1041.team_3.service;

import pro1041.team_3.domainModel.NhanVien;

/**
 *
 * @author sonpt_ph19600
 */
public interface LoginService {
    
    NhanVien login(String username, String password);
    
}
