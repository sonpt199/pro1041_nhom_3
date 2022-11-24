package pro1041.team_3.service.impl;

import pro1041.team_3.service.LoginService;
import pro1041.team_3.domainmodel.TaiKhoan;
import pro1041.team_3.repository.UserRepository;
import java.util.List;

/**
 *
 * @author sonpt_ph19600
 */
public class LoginServiceImpl implements LoginService{
    
    private UserRepository userRepository;

    public LoginServiceImpl() {
        userRepository = new UserRepository();
    }

    @Override
    public int login(String username, String password) {
        List<TaiKhoan> lstAccoutn = userRepository.getAll();
        for (TaiKhoan x : lstAccoutn) {
            if (username.equals(x.getTenDangNhap()) && 
                    password.equals(x.getMatkhau())) {
                return x.getVaitro();
            }
        }
        return -1;
    }
    
}
