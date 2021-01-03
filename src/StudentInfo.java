import com.company.AddStudent;
import com.company.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInfo extends JFrame{
    private JLabel isimTitle;
    private JLabel soyisimTitle;
    private JLabel tcTitle;
    private JLabel yasTitle;
    private JLabel telefonTitle;
    private JLabel emergencyTitle;
    private JLabel emailTitle;
    private JLabel adresTitle;
    private JLabel isimValue;
    private JLabel soyisimValue;
    private JLabel tcValue;
    private JLabel yasValue;
    private JLabel telefonValue;
    private JLabel emergencyValue;
    private JLabel emailValue;
    private JLabel adresValue;
    private JPanel studentInfoPanel;
    private JTextField enterStudentInfoTextField;
    private JButton searchButton;
    private JFrame frame= new JFrame();
    private String studentInfo;

    private Student std = new Student();

    public StudentInfo() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                studentInfo = enterStudentInfoTextField.getText();
                std.LoadWithStdNum(Integer.parseInt(studentInfo));
                isimValue.setText(std.getName());
                soyisimValue.setText(std.getSurname());
                tcValue.setText(std.getIdentityNum());
                yasValue.setText(std.getAge().toString());
                telefonValue.setText(std.getPhone());
                emergencyValue.setText(std.getEmergencyPhone());
                emailValue.setText(std.getEmail());
                adresValue.setText(std.getAddress());

            }
        });
    }

    public void load(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new StudentInfo().studentInfoPanel);
        frame.pack();
        frame.setVisible(true);


    }
}



