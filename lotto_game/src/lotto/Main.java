package lotto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

class Amount extends JFrame {

   JButton btnConfirm;
   JTextField tfAmount;

   public Amount() {
      super("Lotto");

      JFrame jFrame = new JFrame();
      JPanel pnlNorth = new JPanel();
      pnlNorth.setBackground(Color.WHITE);
      JPanel pnlSouth = new JPanel();
      pnlSouth.setBackground(Color.WHITE);
      JLabel labelAmount = new JLabel("수량 : ");
      labelAmount.setFont(new Font("SanSerif", Font.BOLD, 20));
      JButton btnConfirm = new JButton("확인");
      btnConfirm.setFont(new Font("SanSerif", Font.BOLD, 20));
      JTextField tfAmount = new JTextField(7);
      tfAmount.setFont(new Font("SanSerif", Font.PLAIN, 18));

      tfAmount.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               btnConfirm.doClick();
            }
         }
      });
      // 숫자 1-5 사이 입력하고 확인 누르면 메세지창 뜨게하기
      // 5이상 입력하거나 숫자를 입력하지않고 확인누르면 "다시입력하세요"
      btnConfirm.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            try {
               int number = Integer.parseInt(tfAmount.getText());

               if (number <= 5 && number >= 1) {
                  JOptionPane.showMessageDialog(jFrame, "결제금액은 " + number * 1000 + "원 입니다.");
                  new BorderFrame(number).setVisible(true);
                  setVisible(false);

               } else {
                  JOptionPane.showMessageDialog(jFrame, "1~5의 수량을 입력하세요.", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
                  tfAmount.setText("");
               }

            } catch (NumberFormatException n) {
               JOptionPane.showMessageDialog(jFrame, "1~5의 수량을 입력하세요.", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
               tfAmount.setText("");
            }
         }
      });
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Image image = toolkit.getImage("그림1.png").getScaledInstance(200, 200, Image.SCALE_DEFAULT);
      JLabel imagelabel = new JLabel("", new ImageIcon(image), SwingConstants.CENTER);

      pnlSouth.add(labelAmount, BorderLayout.SOUTH);
      pnlSouth.add(tfAmount, BorderLayout.SOUTH);
      pnlSouth.add(btnConfirm, BorderLayout.SOUTH);
      pnlNorth.add(imagelabel);
      add(pnlSouth, BorderLayout.CENTER);
      add(pnlNorth, BorderLayout.NORTH);
      setSize(500, 350);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
}

public class Main {
   public static void main(String[] args) {
      new Amount().setVisible(true);
   }
}