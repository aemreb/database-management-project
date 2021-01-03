import com.company.AddStudent;
import com.company.DialogWindow;
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
    private JButton deleteButton;
    private JButton updateButton;
    private JButton updateButton1;
    private JButton updateButton2;
    private JLabel message1;
    private JLabel message2;
    private JLabel message;
    private JLabel message3;
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

            };
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentInfo = enterStudentInfoTextField.getText();
                std.LoadWithStdNum(Integer.parseInt(studentInfo));
                std.Delete();
                if(std.Delete()){
                    message.setText(std.getStudentNum() + " numaralı öğrenci silindi.");
                }else{
                    message.setText("Öğrenci silinemedi.");

                }
            };
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new DialogWindow();



            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new DialogWindow();

                studentInfo = enterStudentInfoTextField.getText(); //DialogWindow içine alınacak
                std.LoadWithStdNum(Integer.parseInt(studentInfo));
                std.setPhone(telefonValue.getText());
                std.Update();
                if(std.Update()){
                    message2.setText("Güncelleme tamamlandı.");
                }else{
                    message2.setText("Güncelleme başarısız.");

                }
            }
        });
        updateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new DialogWindow();

                studentInfo = enterStudentInfoTextField.getText(); //DialogWindow içine alınacak
                std.LoadWithStdNum(Integer.parseInt(studentInfo));
                std.setEmergencyPhone(emergencyValue.getText());
                std.Update();
                if(std.Update()){
                    message3.setText("Güncelleme tamamlandı.");
                }else{
                    message3.setText("Güncelleme başarısız.");

                }
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





