package lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//===================다이얼로그======================================
class LottoDialog extends JDialog {
	ArrayList<String> list = new ArrayList<String>();
	int number;
	String arr[][] = new String[5][6];
	JButton nbuttons[] = new JButton[7];
	int rand[] = new int[6];
	int num = 0;
	JPanel[] cpanel = new JPanel[5];
	JPanel[][] cpanel2 = new JPanel[5][6];
	JLabel[][] clabel = new JLabel[5][6];
	JButton all;
	JPanel center = new JPanel(new GridLayout(5, 0));
	JPanel mainPnl = new JPanel(new BorderLayout());
	JPanel westGrid = new JPanel(new GridLayout(5, 0));
	JPanel centerGrid = new JPanel(new GridLayout(0, 6));
	JPanel northGrid = new JPanel(new GridLayout(1, 2));
	JPanel eastpanel[] = new JPanel[5];
	JLabel eastlabel[] = new JLabel[5];
	JPanel npanel[] = new JPanel[8];
	int cnt = 1;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image lblimage = toolkit.getImage("rec.png").getScaledInstance(50, 50, 100);
	ImageIcon img = new ImageIcon("bonus.png");
	ImageIcon img2 = new ImageIcon("question2.png");
	ImageIcon img3 = new ImageIcon("all.png");

	public LottoDialog(BorderFrame resDialog, String[][] arr) {
		// 다이얼로그 결과창
		super(resDialog, "로또 결과창", true);
		int x = resDialog.getX() + resDialog.getWidth();
		int y = resDialog.getY();
		this.number = resDialog.getNumber();
		this.arr = arr;
		setModal(true);
		setLocation(x, y);
		setSize(630, 630);
		makecompoment3();
	}
	void makecompoment3() {
		// 당첨번호 생성
		Random rd = new Random();
		for (int i = 0; i < 6; i++) {
			rand[i] = rd.nextInt(45) + 1;
			for (int j = 0; j < i; j++) {
				if (rand[i] == rand[j]) {
					i--;
				}
			}
		}
		Arrays.sort(rand);
		boolean bo = true;
		num = rd.nextInt(45) + 1;
		for (int i = 0; i < 6; i++) {
			if (num == rand[i]) {
				num = rd.nextInt(45) + 1;
				i = 0;
			}
			break;
		}
		for (int i = 0; i < 8; i++) {
			npanel[i] = new JPanel();
			npanel[i].setBackground(Color.WHITE);
		}
		// 화면 상단 로또 번호 버튼
		Image image = img.getImage();
		Image changeimg = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeimgicon = new ImageIcon(changeimg);
		Image image2 = img2.getImage();
		Image changeimg2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeimgicon2 = new ImageIcon(changeimg2);
		for (int i = 0; i < 7; i++) {
			if (i == 6)
				nbuttons[i] = new JButton(changeimgicon);
			else
				nbuttons[i] = new JButton(changeimgicon2);
			nbuttons[i].setBackground(Color.WHITE);
			nbuttons[i].addActionListener(new MyActionListener(nbuttons));
			npanel[i].add(nbuttons[i]);
			northGrid.add(npanel[i]);
		}
		northGrid.setBackground(Color.WHITE);
		Image image3 = img3.getImage();
		Image changeimg3 = image3.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon changeimgicon3 = new ImageIcon(changeimg3);
		all = new JButton(changeimgicon3);
		all.addActionListener(new MyActionListener(nbuttons));
		all.setBackground(Color.WHITE);
		npanel[7].add(all);
		northGrid.add(npanel[7]);

		// 화면의 좌측
		String[] alpa = { "A", "B", "C", "D", "E" };
		JLabel[] label = new JLabel[5];
		JPanel[] panel = new JPanel[5];
		for (int i = 0; i < number; i++) {
			label[i] = new JLabel();
			label[i].setText(alpa[i]);
			label[i].setFont(label[i].getFont().deriveFont(50.0f));
			panel[i] = new JPanel();
			panel[i].setBackground(Color.WHITE);
			panel[i].add(label[i]);
			westGrid.add(panel[i]);
			westGrid.setBackground(Color.WHITE);
			mainPnl.add(westGrid, "West");

		}

		// 화면 센터 (숫자 넘겨받는곳)
		for (int i = 0; i < number; i++) {
			cpanel[i] = new JPanel();
			cpanel[i].setLayout(new GridLayout(0, 7));
			eastpanel[i] = new JPanel();
			Image lblimageq = toolkit.getImage("question.png").getScaledInstance(50, 50, 100);
			eastlabel[i] = new JLabel(new ImageIcon(lblimageq), SwingConstants.LEFT);
			eastlabel[i].setBackground(Color.WHITE);
			eastpanel[i].setBackground(Color.WHITE);
			for (int j = 0; j < 6; j++) {
				cpanel2[i][j] = new JPanel();
				clabel[i][j] = new JLabel(String.valueOf(arr[i][j]), new ImageIcon(lblimage), SwingConstants.LEFT);
				clabel[i][j].setFont(clabel[i][j].getFont().deriveFont(20.0f));
				clabel[i][j].setVerticalTextPosition(SwingConstants.CENTER);
				clabel[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
				cpanel2[i][j].setBackground(Color.WHITE);
				cpanel2[i][j].add(clabel[i][j]);
				cpanel[i].add(cpanel2[i][j]);
			}
			cpanel[i].setBackground(Color.WHITE);
			eastpanel[i].add(eastlabel[i]);
			cpanel[i].add(eastpanel[i]);
			center.add(cpanel[i]);
		}
		center.setBackground(Color.WHITE);
		mainPnl.add(northGrid, "North"); // 화면 북측
		mainPnl.add(westGrid, "West");// 화면의 좌측
		mainPnl.add(center, "Center");
		add(mainPnl);
		pack();

	}
	// 당첨번호 6자리 처리 함
	void winningnum(int rand) {
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 6; j++) {
				if (rand == Integer.parseInt(arr[i][j])) {
					cpanel2[i][j].removeAll();
					Image lblimagered = toolkit.getImage("circle.png").getScaledInstance(50, 50, 100);
					clabel[i][j] = new JLabel(String.valueOf(arr[i][j]), new ImageIcon(lblimagered),
							SwingConstants.LEFT);
					clabel[i][j].setFont(clabel[i][j].getFont().deriveFont(20.0f));
					clabel[i][j].setVerticalTextPosition(SwingConstants.CENTER);
					clabel[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
					cpanel2[i][j].add(clabel[i][j]);
				}

			}
		}
	}
	// 보너스 번호 처리 함수
	void bonusnum(int rand) {
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 6; j++) {
				if (rand == Integer.parseInt(arr[i][j])) {
					cpanel2[i][j].removeAll();
					Image lblimagered = toolkit.getImage("circumference.png").getScaledInstance(50, 50, 100);
					clabel[i][j] = new JLabel(String.valueOf(arr[i][j]), new ImageIcon(lblimagered),
							SwingConstants.LEFT);
					clabel[i][j].setFont(clabel[i][j].getFont().deriveFont(20.0f));
					clabel[i][j].setVerticalTextPosition(SwingConstants.CENTER);
					clabel[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
					cpanel2[i][j].add(clabel[i][j]);
				}
			}
		}
	}
	// 결과 처리 함수
	void result() {
		int num2 = 0;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					if (rand[k] == Integer.parseInt(arr[i][j])) {
						num2++;
					}
				}

			}
			if (num2 >= 3) {
				eastpanel[i].removeAll();
				Image lblimage5 = toolkit.getImage("5.png").getScaledInstance(50, 50, 100);
				eastlabel[i] = new JLabel(new ImageIcon(lblimage5), SwingConstants.LEFT);
				eastlabel[i].setBackground(Color.WHITE);
				eastpanel[i].setBackground(Color.WHITE);
				eastpanel[i].add(eastlabel[i]);
			} else {
				eastpanel[i].removeAll();
				Image lblimage5 = toolkit.getImage("bang.png").getScaledInstance(50, 50, 100);
				eastlabel[i] = new JLabel(new ImageIcon(lblimage5), SwingConstants.LEFT);
				eastlabel[i].setBackground(Color.WHITE);
				eastpanel[i].setBackground(Color.WHITE);
				eastpanel[i].add(eastlabel[i]);
			}
			num2 = 0;

			eastlabel[i].setFont(eastlabel[i].getFont().deriveFont(30.0f));
		}
	}

	class MyActionListener implements ActionListener {
		JButton[] buttons;

		public MyActionListener(JButton[] buttons) {
			this.buttons = buttons;

		}
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			for (int i = 0; i < 7; i++) {	// 당첨번호 한자리씩 누를때 처리
				if (b == buttons[i]) {
					if (cnt == 7)
						result();
					cnt++;
					if (i == 6) {
						npanel[i].removeAll();
						JLabel nb = new JLabel();
						nb.setText(Integer.toString(num));
						nb.setFont(nb.getFont().deriveFont(15.0f));
						npanel[i].add(nb);
						npanel[i].revalidate();
						npanel[i].repaint();
						bonusnum(num);
						continue;
					}
					npanel[i].removeAll();
					JLabel nb = new JLabel();
					nb.setText(Integer.toString(rand[i]));
					nb.setFont(nb.getFont().deriveFont(15.0f));
					npanel[i].add(nb);
					npanel[i].revalidate();
					npanel[i].repaint();
					winningnum(rand[i]);
				}
			}
			if (b == all) {	// 당첨 번호 전체공개 버튼 처리
				for (int i = 0; i < 7; i++) {
					if (i == 6) {
						npanel[i].removeAll();
						JLabel nb = new JLabel();
						nb.setText(Integer.toString(num));
						nb.setFont(nb.getFont().deriveFont(15.0f));
						npanel[i].add(nb);
						npanel[i].revalidate();
						npanel[i].repaint();
						bonusnum(num);
						continue;
					}
					npanel[i].removeAll();
					JLabel nb = new JLabel();
					nb.setText(Integer.toString(rand[i]));
					nb.setFont(nb.getFont().deriveFont(15.0f));
					npanel[i].add(nb);
					npanel[i].revalidate();
					npanel[i].repaint();
					result();
					winningnum(rand[i]);
				}
			}
		}
	}

}