package pro1041.team_3.service.impl;

import pro1041.team_3.service.LoginService;
import java.util.List;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.repository.NhanVienRepository;

/**
 *
 * @author sonpt_ph19600
 */
public class LoginServiceImpl implements LoginService{
    
    private NhanVienRepository userRepository;

    public LoginServiceImpl() {
        userRepository = new NhanVienRepository();
    }

    @Override
    public NhanVien login(String username, String password) {
        List<NhanVien> lstAccoutn = userRepository.getAll();
        for (NhanVien x : lstAccoutn) {
            System.out.println(x.toString());
            if (username.equals(x.getTenDangNhap()) && 
                    password.equals(x.getMatKhau())) {                
                return x;
            }
        }
        return null;
    }
    
}
