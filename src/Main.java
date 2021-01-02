import com.company.AddStudent;
import com.company.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel{
    private JButton kayıtSorgulaButton;
    private JButton öğrenciEkleButton;
    private JPanel firstPanel;


    public Main() {
        kayıtSorgulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentInfo().load();
            }
        });
        öğrenciEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent().load();
            }
        });
    }



    public void load(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new Main().firstPanel);
        frame.pack();
        frame.setVisible(true);

    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().load();
            }
        });
    }
}
