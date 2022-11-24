/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.util;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.domainmodel.TaiKhoan;


/**
 *
 * @author sonpt_ph19600
 */
public class HibernateUtil {
     private static final SessionFactory FACTORY;
    private static Session SESSION;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=quan_ly_ban_dien_thoai");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "create");

//        Thêm bảng cái add thêm zô đây để có thể kết nối với DB
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(TaiKhoan.class);

        conf.setProperties(properties);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }
    
    public static Session getSession(){
        if (SESSION == null || !SESSION.isConnected()) {
            SESSION = FACTORY.openSession();            
        }
        return SESSION;
    }

    public static void main(String[] args) {
        SESSION = FACTORY.openSession();
        SESSION.close();
        System.out.println(SESSION.isConnected());
    }

}
