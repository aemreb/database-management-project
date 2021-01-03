import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel{
    private JButton ogrenciSorgulaButton;
    private JButton ogrenciEkleButton;
    private JButton uyelikSorgulaButton;
    private JPanel firstPanel;
    private JButton addMembershipButton;
    private JButton raporlamaButton;
    private JButton calışanMesaiListesiButton;
    private JButton üyeMaaşlarıHesaplaButton;

    public Main() {
        ogrenciSorgulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new StudentInfo().load();
            }
        });
        ogrenciEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddStudent().load();
            }
        });

        uyelikSorgulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new MembershipInfo().load();
            }
        });

        addMembershipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMembership().load();
            }
        });

        raporlamaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Raporlama().load();
            }
        });

        calışanMesaiListesiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowEmployee().load();

            }
        });

        üyeMaaşlarıHesaplaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IncomeforMember().load();
            }
        });
    }



    public void load(){
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new Main().firstPanel);
        frame.pack();
        frame.setVisible(true);
        Student st = new Student();
    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().load();
            }
        });
    }
}
