/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.service.impl;

/**
 *
 * @author ADMIN
 */
public class UserServiceImpl implements UserService{
    
    private UsersRepository repos;

    public UserServiceImpl() {
        repos = new UsersRepository();
    }
    
    @Override
    public List<UserDto> getAllReponse() {
        return repos.getAllReponse();
    }

    @Override
    public String insert(User user) {
        if (user.getTendangnhap().trim().isEmpty()) {
            return "Tên đăng nhập không được để trống";
        }
        else if (user.getHoten().trim().isEmpty()) {
            return "Họ và tên không được để trống";
        }
        else if (user.getDiachi().trim().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        else if (user.getEmail().trim().isEmpty()) {
            return "Email không được để trống";
        }
        else if (user.getSdt().trim().isEmpty()) {
            return "SĐT không được để trống";
        }
        user = repos.saveOrUpdate(user);
        if (user != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String delete(UUID id) {
        User userFind = repos.findById(id);
        if (userFind == null) {
            return "Không tìm thấy";
        }
        boolean delete = repos.detele(userFind);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<User> getAll() {
        return repos.getAll();
    }

    @Override
    public String update(User user) {
       User userFindById = repos.findById(user.getId());
        if (userFindById == null) {
            return "Không tìm thấy";
        }
        if (user.getHoten().trim().isEmpty()) {
            return "Họ và tên không được trống";
        }
        if (user.getMatkhau().trim().isEmpty()) {
            return "Mật khẩu không được trống";
        }
        if (user.getDiachi().trim().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (user.getTendangnhap().trim().isEmpty()) {
            return "Tên đăng nhập không được trống";
        }
        
        if (user.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được trống";
        }
        
        
        
        userFindById.setTendangnhap(user.getTendangnhap());
        userFindById.setHoten(user.getHoten());
        userFindById.setEmail(user.getEmail());
        userFindById.setSdt(user.getSdt());
        userFindById.setMatkhau(user.getMatkhau());
        userFindById.setGioitinh(user.getGioitinh());
        userFindById.setVaitro(user.getVaitro());
        userFindById.setId(user.getId());
        user = repos.saveOrUpdate(userFindById);
        if (user != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<UserDto> findByTenDangNhap(String keyWord) {
        return repos.findByTenDangNhap(keyWord);
    }
}
