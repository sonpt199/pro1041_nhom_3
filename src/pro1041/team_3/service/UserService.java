/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pro1041.team_3.service;

/**
 *
 * @author ADMIN
 */
public interface UserService {
    List<UserDto> getAllReponse();
    
    String insert(User user);
    
    String update(User user);
    
    String delete(UUID id);
    
    List<User> getAll();
    
    List<UserDto> findByTenDangNhap(String keyWord);
}
