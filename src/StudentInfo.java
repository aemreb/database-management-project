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
    private JButton silButton;
    private JButton guncelle1Button;
    private JButton guncelle2Button;
    private JButton guncelle3Button;
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;
    private JLabel message4;



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

                silButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        studentInfo = enterStudentInfoTextField.getText();
                        std.LoadWithStdNum(Integer.parseInt(studentInfo));
                        std.Delete();
                        if(std.Delete()){
                            message1.setText(std.getStudentNum() + " numaralı öğrenci silindi.");
                        }else{
                            message1.setText("Öğrenci silinemedi.");
                        }
                    }
                });
                guncelle1Button.addActionListener((new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        studentInfo = enterStudentInfoTextField.getText();
                        std.LoadWithStdNum(Integer.parseInt(studentInfo));
                        std.setAge(Integer.parseInt(yasValue.getText()));
                        std.Update();
                        if(std.Update()){
                            message2.setText("Güncelleme tamamlandı.");
                        }else{
                            message2.setText("Güncelleme yapılamadı.");
                        }

                    }
                }));
                guncelle2Button.addActionListener((new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        studentInfo = enterStudentInfoTextField.getText();
                        std.LoadWithStdNum(Integer.parseInt(studentInfo));
                        std.setPhone(telefonValue.getText());
                        std.Update();
                        if(std.Update()){
                            message3.setText("Güncelleme tamamlandı.");
                        }else{
                            message3.setText("Güncelleme yapılamadı.");
                        }
                    }
                }));
                guncelle3Button.addActionListener((new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        studentInfo = enterStudentInfoTextField.getText();
                        std.LoadWithStdNum(Integer.parseInt(studentInfo));
                        std.setAddress(adresValue.getText());
                        std.Update();
                        if(std.Update()){
                            message4.setText("Güncelleme tamamlandı.");
                        }else{
                            message4.setText("Güncelleme yapılamadı.");
                        }
                    }
                }));


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



