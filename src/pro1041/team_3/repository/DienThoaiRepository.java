package pro1041.team_3.repository;

import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiDto;
import pro1041.team_3.dto.DienThoaiDto;
import pro1041.team_3.util.HibernateUtil;

/**
 *
 * @author vanntph19604
 */
public class DienThoaiRepository extends Repository<DienThoai, UUID, DienThoaiDto> {

    public DienThoaiRepository() {
        className = DienThoai.class.getName();
        resCon = "new pro1041.team_3.dto.DienThoaiDto(a.id, a.ma, a.ten)";
        join = " order by CONVERT(INT, SUBSTRING(a.ma, 3, 10)) desc";
    }
    
    public List<DienThoaiDto> findByName(String keyWord){
        try {
            List<DienThoaiDto> lst;
            session = HibernateUtil.getSession();
            String hql = "SELECT " + resCon + " FROM " + className + " a WHERE a.ten LIKE CONCAT('%', :keyWord, '%') " + join;
            Query query = session.createQuery(hql);
            query.setParameter("keyWord", keyWord);
            lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ChiTietDienThoaiDto checkDTTrongCtdt(UUID id) {
        ChiTietDienThoaiDto chiTietDienThoaiDto = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "select new pro1041.team_3.dto.ChiTietDienThoaiDto(a.id, a.hang.id, a.mauSac.id, a.dienThoai.id) from ChiTietDienThoai a "
                    + " where a.dienThoai.id = :id ";
            TypedQuery<ChiTietDienThoaiDto> query = session.createQuery(hql, ChiTietDienThoaiDto.class);
            query.setParameter("id", id);
            List<ChiTietDienThoaiDto> lst = query.getResultList();
            if (!lst.isEmpty()) {
                chiTietDienThoaiDto = lst.get(0);
            }
            return chiTietDienThoaiDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public DienThoai findDienThoaiByName(String ten) {
        DienThoai entity = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a WHERE a.ten = :ten";
            TypedQuery<DienThoai> query = session.createQuery(hql, DienThoai.class);
            query.setParameter("ten", ten);
            List<DienThoai> lst = query.getResultList();
            if (!lst.isEmpty()) {
                entity = lst.get(0);
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
