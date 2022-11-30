package pro1041.team_3.main;

import java.awt.Image;
import pro1041.team_3.component.MenuLayout;
import pro1041.team_3.event.EventMenuSelected;
import pro1041.team_3.form.Form_1;
import pro1041.team_3.form.ViewBanHang;
import pro1041.team_3.form.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import pro1041.team_3.component.Profile;
import pro1041.team_3.form.ViewQuanLyHoaDon;

public class Main extends javax.swing.JFrame {

    private final MigLayout layout;
    private final MainForm main;
    private final MenuLayout menu;
    private final Animator animator;

    public Main() {
        initComponents();
        this.setTitle("Quản lý bán hàng");
        ImageIcon icon = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        this.setIconImage(icon.getImage());
        layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
        main = new MainForm();
        menu = new MenuLayout();
        menu.getMenu().initMoving(Main.this);
        main.initMoving(Main.this);
        mainPanel.setLayer(menu, JLayeredPane.POPUP_LAYER);
        mainPanel.setLayout(layout);
        mainPanel.add(main);
        mainPanel.add(menu, "pos -215 0 100% 100%", 0);
        Profile.user = true;
//        System.out.println(Profile.get());
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float x = (fraction * 215);
                float alpha;
                if (menu.isShow()) {
                    x = -x;
                    alpha = 0.5f - (fraction / 2);
                } else {
                    x -= 215;
                    alpha = fraction / 2;
                }
                layout.setComponentConstraints(menu, "pos " + (int) x + " 0 100% 100%");
                if (alpha < 0) {
                    alpha = 0;
                }
                menu.setAlpha(alpha);
                mainPanel.revalidate();
            }

            @Override
            public void end() {
                menu.setShow(!menu.isShow());
                if (!menu.isShow()) {
                    menu.setVisible(false);
                }
            }

        };
        animator = new Animator(200, target);
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator.isRunning()) {
                        if (menu.isShow()) {
                            animator.start();
                        }
                    }
                }
            }
        });
        main.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    if (!menu.isShow()) {
                        menu.setVisible(true);
                        animator.start();
                    }
                }
            }
        });
        menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    main.show(new Form_1());
                } else if (index == 1) {
                    main.show(new ViewBanHang());
                } else if (index == 6) {
                    main.show(new ViewQuanLyHoaDon());
                } else if (index == 9) {
                    System.exit(0);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(250, 250, 250));
        mainPanel.setOpaque(true);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane mainPanel;
    // End of variables declaration//GEN-END:variables
}
