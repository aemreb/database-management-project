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
    private JTextField yasValue;
    private JTextField telefonValue;
    private JTextField emergencyValue;
    private JLabel emailValue;
    private JLabel adresValue;
    private JPanel studentInfoPanel;
    private JTextField enterStudentInfoTextField;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton updateAgeButton;
    private JButton updatePhoneButton;
    private JButton updateEmergencyButton;
    private JLabel messageAge;
    private JLabel messagePhone;
    private JLabel messageDelete;
    private JLabel messageEmergency;
    private JFrame frame= new JFrame();
    private String studentInfo;

    private Student std = new Student();

    public StudentInfo() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                studentInfo = enterStudentInfoTextField.getText();
               if( std.LoadWithStdNum(Integer.parseInt(studentInfo))) {
                   isimValue.setText(std.getName());
                   soyisimValue.setText(std.getSurname());
                   tcValue.setText(std.getIdentityNum());
                   yasValue.setText(std.getAge().toString());
                   telefonValue.setText(std.getPhone());
                   emergencyValue.setText(std.getEmergencyPhone());
                   emailValue.setText(std.getEmail());
                   adresValue.setText(std.getAddress());
               }
               else{
                   messageDelete.setText(studentInfo+" nolu öğrenci bulunamadı");
               }

            };
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(std.Delete()){
                    messageDelete.setText(std.getStudentNum() + " numaralı öğrenci silindi.");
                }else{
                    messageDelete.setText("Öğrenci silinemedi.");

                }
            };
        });
        updateAgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                std.setAge(Integer.parseInt(yasValue.getText()));
                if(std.Update())
                    messageAge.setText("Yaş Başarıyla Güncellendi!");
                else
                    messageAge.setText("Güncelleme Gerçekleştirilemedi!");
            }
        });
        updatePhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                std.setPhone(telefonValue.getText());
                if(std.Update())
                    messagePhone.setText("Telefon Başarıyla Güncellendi!");
                else
                    messagePhone.setText("Güncelleme Gerçekleştirilemedi!");

            }
        });
        updateEmergencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                std.setEmergencyPhone(emergencyValue.getText());
                if(std.Update())
                    messageEmergency.setText("Acil Durum Telefonu Başarıyla Güncellendi!");
                else
                    messageEmergency.setText("Güncelleme Gerçekleştirilemedi!");
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





