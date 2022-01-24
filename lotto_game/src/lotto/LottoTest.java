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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
//=========================메인===================================

class BorderFrame extends JFrame {
	ArrayList<String> list = new ArrayList<String>();
	int rand[] = new int[7];
	JButton btn[] = new JButton[5];
	JButton btn2[] = new JButton[5];
	int number;
	int cnt = 0;
	int cnt2 = 0;
	String arr[][] = new String[5][6];
	LottoManual lm;
	JPanel cpanel[] = new JPanel[5];
	JPanel mainPnl = new JPanel(new BorderLayout());
	JPanel centerGrid = new JPanel(new GridLayout(5, 0)); // 기존꺼
	JButton buttons[] = new JButton[6];
	JFrame jFrame;
	String str = "수동";
	JPanel westGrid = new JPanel(new GridLayout(5, 0)); // 기존 서쪽 5열0행
	JLabel[] label = new JLabel[5];
	JPanel[] panel = new JPanel[5];
	String alpa[] = { "A", "B", "C", "D", "E" };
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	ImageIcon img2 = new ImageIcon("start.png");
	ImageIcon img3 = new ImageIcon("edit.png");
	JLabel label2[] = new JLabel[5]; // 버튼대신 나오는 라벨
	JCheckBox[] box = new JCheckBox[45];
	JButton[] num = new JButton[6];
	JLabel[] addlbl = new JLabel[6];
	JButton result = new JButton("결과보기");

	BorderFrame(int number) {
		this.number = number;
		setTitle("로또 진행창");
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makecompoment2();
		setVisible(true);
	}
	int getNumber() {
		return number;
	}
	void makecompoment2() {
		// 메인 진행창
		// //새로 해볼 레이아웃
		JPanel eastGrid = new JPanel(new GridLayout(5, 0));
		JPanel southFlow = new JPanel();
		JPanel eastpanel[] = new JPanel[5];

		// 버튼
		result.addActionListener(new MyActionListener());
		result.setEnabled(false);

		JButton back = new JButton("뒤로가기");
		Image image2 = img2.getImage();
		Image changeimg2 = image2.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon changeimg = new ImageIcon(changeimg2);

		Image image3 = img3.getImage();
		Image changeimg3 = image3.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changeimg4 = new ImageIcon(changeimg3);
		for (int i = 0; i < number; i++) {
			btn[i] = new JButton(changeimg);
			btn2[i] = new JButton(changeimg4);
			eastpanel[i] = new JPanel();
			eastpanel[i].setBackground(Color.WHITE);
			btn2[i].setEnabled(false);
			btn[i].setBackground(Color.WHITE);
			btn2[i].setBackground(Color.WHITE);
			btn[i].setBorderPainted(false);
			btn2[i].setBorderPainted(false);
			btn2[i].addActionListener(new MyActionListener(buttons, box));
			btn[i].addActionListener(new MyActionListener());
			btn[i].setContentAreaFilled(true);
			btn2[i].setFont(new Font("고딕", Font.PLAIN, 15));
			btn[i].setFont(new Font("고딕", Font.PLAIN, 15));
			eastpanel[i].add(btn2[i]);
			eastpanel[i].add(btn[i]);
			eastGrid.add(eastpanel[i]);
		}
		// 뒤로가기버튼 누를시 (맨 처음 화면)
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Amount().setVisible(true);
				setVisible(false);
			}
		});
		// 화면 북측;
		ImageIcon icon = new ImageIcon("LottoImage.jpg");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(385, 131, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel lotto = new JLabel(changeIcon);
		lotto.setOpaque(true);
		lotto.setBackground(Color.WHITE);

		// 화면의 좌측
		for (int i = 0; i < number; i++) {
			label[i] = new JLabel();
			label[i].setText(alpa[i]);
			label[i].setFont(label[i].getFont().deriveFont(30.0f));
			panel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel[i].add(label[i]);
			westGrid.add(label[i]);

		}
		// 화면의 센터
		for (int i = 0; i < 5; i++) {
			cpanel[i] = new JPanel(new GridLayout(1, 0, 8, 0));
			cpanel[i].setBackground(Color.WHITE);
			centerGrid.add(cpanel[i]);
			label2[i] = new JLabel();
		}

		// 화면 남측
		southFlow.add(result);
		southFlow.add(back);

		eastGrid.setBackground(Color.WHITE);
		southFlow.setBackground(Color.WHITE);
		westGrid.setBackground(Color.WHITE);
		centerGrid.setBackground(Color.WHITE);

		// 메인(보더레이아웃)pnl
		mainPnl.add(lotto, "North"); // 화면 북측
		mainPnl.add(eastGrid, "East"); // 화면의 우측
		mainPnl.add(westGrid, "West");// 화면의 좌측
		mainPnl.add(southFlow, "South"); // 화면 남측
		mainPnl.add(centerGrid, "Center");

		add(mainPnl);

	}
	//	입력한 값 센터에 넣는 함
	void setcenter() {
		for (int i = 0; i < list.size(); i++) {
			arr[cnt2][i] = list.get(i);
		}
		label[cnt2].setText(alpa[cnt2] + str);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image lblimage = toolkit.getImage("rec.png").getScaledInstance(50, 50, 100);
		cpanel[cnt2].removeAll();
		for (int i = 0; i < 6; i++) {
			label2[cnt2] = new JLabel(String.valueOf(list.get(i)), new ImageIcon(lblimage), SwingConstants.LEFT);
			label2[cnt2].setFont(label2[cnt2].getFont().deriveFont(20.0f));
			label2[cnt2].setVerticalTextPosition(SwingConstants.CENTER);
			label2[cnt2].setHorizontalTextPosition(SwingConstants.CENTER);
			cpanel[cnt2].add(label2[cnt2]);
		}
		cpanel[cnt2].revalidate();
		cpanel[cnt2].repaint();
		cpanel[cnt2].setBackground(Color.WHITE);// 패널 배경색

		centerGrid.setBackground(Color.WHITE);
		mainPnl.revalidate();
		mainPnl.repaint();
		add(mainPnl);
		list.clear();
		btn2[cnt2].setEnabled(true);
		cnt++;
		if (cnt == number)
			result.setEnabled(true);
		str = "수동";

	}
	//String 리스트 정렬 함수
	ArrayList<String> sortlist(ArrayList<String> list) {
		ArrayList<Integer> intlist = new ArrayList<>();
		ArrayList<String> strlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			intlist.add(Integer.parseInt(list.get(i)));
		}
		Collections.sort(intlist);
		for (int i = 0; i < list.size(); i++) {
			strlist.add(Integer.toString(intlist.get(i)));
		}
		return strlist;
	}

	public class LottoManual extends JDialog implements ItemListener {

		LottoManual(BorderFrame selDialog) {
			super(selDialog, "로또 번호 선택창", true);
			int x = selDialog.getX() + selDialog.getWidth();
			int y = selDialog.getY();
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);// x아이콘 누르면 다꺼지는거 제거
			setSize(400, 300);
			setLocation(685, 0);
			makeCompoment();
		}
		void makeCompoment() {
			JPanel nPanel = new JPanel();
			JPanel cPanel = new JPanel();
			JPanel sPanel = new JPanel();
			nPanel.setBackground(Color.WHITE);
			cPanel.setBackground(Color.WHITE);
			sPanel.setBackground(Color.WHITE);
			// 상단======여기 건의사항======
			nPanel.setLayout(new FlowLayout());
			Image lblimagered = toolkit.getImage("hula.png").getScaledInstance(50, 50, 100);
			for (int i = 0; i < num.length; i++) {
				addlbl[i] = new JLabel(" ", new ImageIcon(lblimagered), SwingConstants.LEFT);
				addlbl[i].setFont(addlbl[i].getFont().deriveFont(20.0f));
				addlbl[i].setVerticalTextPosition(SwingConstants.CENTER);
				addlbl[i].setHorizontalTextPosition(SwingConstants.CENTER);
				addlbl[i].setBackground(Color.WHITE);
				nPanel.add(addlbl[i]);
			}
			// 중간
			int[] number = new int[45];
			for (int i = 1; i <= 45; i++) {
				number[i - 1] = i;
				box[i - 1] = new JCheckBox(Integer.toString(number[i - 1]));
				cPanel.add(box[i - 1]);
				box[i - 1].setBackground(Color.WHITE);
				box[i - 1].addItemListener(this);
			}
			cPanel.setLayout(new GridLayout(9, 0));
			// 하단
			JButton btn = new JButton("확인");
			JButton btn2 = new JButton("reset");
			JButton btn3 = new JButton("자동");
			btn.addActionListener(new MyActionListener());
			btn2.addActionListener(new MyActionListener(num, box));
			btn3.addActionListener(new MyActionListener(num, box));

			sPanel.add(btn);
			sPanel.add(btn2);
			sPanel.add(btn3);

			add(nPanel, BorderLayout.NORTH);
			add(cPanel, BorderLayout.CENTER);
			add(sPanel, BorderLayout.SOUTH);
		}
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getItemSelectable();
			for (int i = 0; i < 45; i++) {
				//
				if (source == box[i]) {
					if (e.getStateChange() == ItemEvent.SELECTED) {	// 체크박스 선택
						list.add(box[i].getText());
						list = sortlist(list);

						for (int n = 0; n < list.size(); n++) {
							addlbl[n].setText(list.get(n));
						}
						if (list.size() > 5) {
							for (int l = 0; l < 45; l++) {
								if (!box[l].isSelected()) {
									box[l].setEnabled(false);
								}
							}
						}
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {	// 체크박스 해제
						for (int j = 0; j < 6; j++) {
							list.remove(box[i].getText());
							list = sortlist(list);
							addlbl[j].setText(" ");
							for (int u = 0; u < 45; u++) {
								box[u].setEnabled(true);
							}

						}
						for (int k = 0; k < list.size(); k++) {
							addlbl[k].setText(list.get(k));
						}
					}
				}
			}
		}
	}
	class MyActionListener implements ActionListener {
		JButton[] buttons;
		JCheckBox[] box;

		public MyActionListener() {

		}
		public MyActionListener(JButton[] buttons, JCheckBox[] box) {
			this.buttons = buttons;
			this.box = box;

		}
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("reset")) {	// 리셋
				for (int k = 0; k < 45; k++) {

					box[k].setSelected(false);

				}
				for (int i = 0; i < 6; i++) {
					addlbl[i].setText(" ");
				}
				list.clear();

			}
			if (b.getText().equals("자동")) {		// 자동
				int a[] = new int[6];
				Random rd = new Random();
				for (int i = 0; i < 6; i++) {
					a[i] = rd.nextInt(45) + 1;
					for (int j = 0; j < i; j++) {
						if (a[i] == a[j] || list.contains(Integer.toString(a[i]))) {
							i--;
						}
					}
				}
				Arrays.sort(a);
				if (list.size() == 0)
					str = "자동";
				else if(list.size()>0&&list.size()<5)
					str = "반자동";
				for (int i = list.size(); i < 6; i++) {
					addlbl[i].setText(Integer.toString(a[i]));
					box[a[i] - 1].setSelected(true);

				}
				for (int k = 0; k < 45; k++) {
					if (!box[k].isSelected()) {
						box[k].setEnabled(false);
					}
				}

			}
			for (int i = 0; i < 5; i++) {
				if (e.getSource() == btn[i]) {
					cnt2 = i;
					lm = new LottoManual(BorderFrame.this);
					lm.setVisible(true);
					btn[cnt2].setEnabled(false);
				}
			}
			if (b.getText().equals("확인")) {		// 확인
				if (list.size() < 6) {
					JOptionPane.showMessageDialog(jFrame, "숫자 6개를 입력해주세요.");
				} else {
					lm.setVisible(false);
					setcenter();
				}
			}
			if (b.getText().equals("결과보기")) {	
				LottoDialog dialog = new LottoDialog(BorderFrame.this, arr);
				dialog.setVisible(true);
			}
			for (int i = 0; i < number; i++) {
				if (b == btn2[i]) {
					list.clear();
					for (int k = 0; k < 45; k++) {
						box[k].setSelected(false);
					}
					for (int j = 0; j < 6; j++) {
						box[Integer.parseInt(arr[i][j]) - 1].setSelected(true);
					}
					cnt2 = i;
					cnt--;
					lm.setVisible(true);
				}
			}
		}
	}
}