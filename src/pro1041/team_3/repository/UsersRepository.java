/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.repository;

/**
 *
 * @author ADMIN
 */
public class UsersRepository extends Repository<User, UUID, UserDto>{

    public UsersRepository() {
        className = User.class.getName();
        resCon = "new pro1041.team_3.dto.UserDto"
                + "(a.id, a.tendangnhap, a.hoten, a.gioitinh, a.sdt, a.diachi, a.email, a.matkhau, a.vaitro)"; 
    }
    
    public List<UserDto> findByTenDangNhap(String keyWord) {
        try {
            List<UserDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.tendangnhap LIKE CONCAT('%', :keyWord, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("keyWord", keyWord);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
