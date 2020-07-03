package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.MemberDao;
import dto.OrderDto;

public class ChoiceView extends JFrame implements ActionListener {
	
	private JLabel cafe_title;
	
	private JButton priceBtn;
	private JButton orderBtn;
	private JComboBox<String> c_choice;
	private JTextField c_cups;
	private JLabel cLabel;
	
	private JPanel sizePanel;
	private JLabel sizeLabel;
	private JRadioButton[] sizeBtn = new JRadioButton[3];
	private ButtonGroup sizeGroup;
	
	private JPanel syrupPanel;
	private JLabel syrupLabel;
	private JRadioButton[] syrupBtn = new JRadioButton[3];
	private ButtonGroup syrupGroup;
	
	private JPanel etcPanel;
	private JLabel etcLabel;
	private JCheckBox[] etcBtn = new JCheckBox[2];
	
	private Container c;
	
	public ChoiceView() {
		
		super("커피 주문창");
		
		c = getContentPane();
		c.setLayout(null);
		
		// 커피 가게 이름
		cafe_title = new JLabel("Coffee Shop");
		cafe_title.setBounds(35, 15, 180, 30);
		cafe_title.setFont(new Font("Serif", Font.BOLD, 30));
		c.add(cafe_title);
		
		// 가격표 뷰 출력.
		priceBtn = new JButton("가격 보기");
		priceBtn.setBounds(300, 20, 120, 30);
		priceBtn.addActionListener(this);
		c.add(priceBtn);
		
		// 콤보박스의 항목
		String[] coffeeMenu = 
			{"헤이즐넛 카라멜 모카", "카라멜 마끼아또",
			 "화이트 초콜릿 모카", "카라멜 모카",
			 "카페 모카", "카라멜 라떼",
			 "카페 라떼", "카푸치노",
			 "아메리카노", "오늘의 커피"};
		
		// 종류 선택창
		c_choice = new JComboBox<String>(coffeeMenu);
		c_choice.setBounds(35, 70, 200, 30);
		c_choice.setBackground(Color.white);
		c.add(c_choice);
		
		// 사이즈 패널
		sizePanel = new JPanel();
		sizePanel.setLayout(new GridLayout(4, 1));
		sizePanel.setBounds(20, 130, 120, 180);
		sizePanel.setBackground(Color.white);
		// 사이즈 라벨
		sizeLabel = new JLabel("SIZE");
		sizeLabel.setForeground(Color.WHITE);
		sizeLabel.setHorizontalAlignment(JLabel.CENTER);
		sizeLabel.setBackground(Color.black);
		sizeLabel.setOpaque(true);
		sizePanel.add(sizeLabel);
		// 사이즈 라디오 버튼
		String[] c_name = {"Short", "Tall", "Grande"};
		for (int i = 0; i < c_name.length; i++) {
			sizeBtn[i] = new JRadioButton(c_name[i]);
			sizeBtn[i].setBackground(Color.white);
			sizeBtn[i].addActionListener(this);
		}
		sizeBtn[0].setSelected(true);
		sizeGroup = new ButtonGroup();
		for (int i = 0; i < c_name.length; i++) {
			sizeGroup.add(sizeBtn[i]);
		}
		sizePanel.add(sizeBtn[0]);
		sizePanel.add(sizeBtn[1]);
		sizePanel.add(sizeBtn[2]);
		
		// 시럽 패널
		syrupPanel = new JPanel();
		syrupPanel.setLayout(new GridLayout(4, 1));
		syrupPanel.setBounds(165, 130, 120, 180);
		syrupPanel.setBackground(Color.white);
		// 시럽 라벨
		syrupLabel = new JLabel("Syrup");
		syrupLabel.setForeground(Color.WHITE);
		syrupLabel.setHorizontalAlignment(JLabel.CENTER);
		syrupLabel.setBackground(Color.black);
		syrupLabel.setOpaque(true);
		syrupPanel.add(syrupLabel);
		// 시럽 라디오버튼
		String[] s_name = {"바닐라", "캬라멜", "헤이즐넛"};
		for (int i = 0; i < s_name.length; i++) {
			syrupBtn[i] = new JRadioButton(s_name[i]);
			syrupBtn[i].setBackground(Color.white);
			syrupBtn[i].addActionListener(this);
		}	
		syrupBtn[0].setSelected(true);
		syrupGroup = new ButtonGroup();
		for (int i = 0; i < syrupBtn.length; i++) {
			syrupGroup.add(syrupBtn[i]);
		}
		syrupPanel.add(syrupBtn[0]);
		syrupPanel.add(syrupBtn[1]);
		syrupPanel.add(syrupBtn[2]);
		
		// 기타 패널		
		etcPanel = new JPanel();
		etcPanel.setLayout(new GridLayout(3, 1));
		etcPanel.setBounds(310, 130, 120, 130);
		etcPanel.setBackground(Color.white);
		// 기타 라벨
		etcLabel = new JLabel("기타");
		etcLabel.setForeground(Color.WHITE);
		etcLabel.setHorizontalAlignment(JLabel.CENTER);
		etcLabel.setBackground(Color.black);
		etcLabel.setOpaque(true);
		etcPanel.add(etcLabel);
		// 기타 라디오 버튼
		String[] e_name = {"샷 추가", "휘핑 크림"};
		for (int i = 0; i < e_name.length; i++) {
			etcBtn[i] = new JCheckBox(e_name[i]);
			etcBtn[i].setBackground(Color.white);
			etcBtn[i].addActionListener(this);
		}
		etcPanel.add(etcBtn[0]);
		etcPanel.add(etcBtn[1]);
		
		// 잔 수 입력창
		c_cups = new JTextField(5);
		c_cups.setBounds(90, 340, 80, 30);
		c.add(c_cups);
		// 잔 수 라벨
		cLabel = new JLabel("잔");
		cLabel.setBounds(180, 340, 70, 30);
		c.add(cLabel);
		
		// 주문 버튼
		orderBtn = new JButton("주문");
		orderBtn.setBounds(230, 340, 120, 30);
		orderBtn.addActionListener(this);
		c.add(orderBtn);
		
		c.add(sizePanel);
		c.add(syrupPanel);
		c.add(etcPanel);
		
	    c.setBackground(new Color(245, 245, 215));
		setBounds(600, 150, 470, 433);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//커피 가격표
		if(obj == priceBtn) {		
			new PriceView();
		}
		// 주문 버튼
		else if(obj == orderBtn) {
			
			MemberDao md = MemberDao.getInstance();
			// 잔 수 입력창이 비어있는지 확인
			if(c_cups.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "잔 수를 입력하세요.");
				return;
			}
			//1 잔미만을 입력했는지 확인
			if( Integer.parseInt(c_cups.getText()) <= 0){
				JOptionPane.showMessageDialog(null, "최소 한 잔 이상을 입력하세요.");
				return;
			}
			
			String c_type = c_choice.getSelectedItem().toString();
			int c_price = 0;
			String c_size = "";
			String syrup = "";
			String shot = "추가안함";
			String cream = "추가안함";
			
			// 잔 수 확인
			int cups = Integer.parseInt(c_cups.getText());
			int expense = 0;
			
			// 커피 사이즈 확인
			if (sizeBtn[0].isSelected()) {
				c_size = "c_short";
			}
			else if(sizeBtn[1].isSelected()) {
				c_size = "c_tall";
			}
			else if(sizeBtn[2].isSelected()) {
				c_size = "c_grande";
			}
			// 사이즈에 따른 가격	
			c_price = md.priceOut(c_type, c_size);
			
			// 시럽 여부
			for (int i = 0; i < syrupBtn.length; i++) {
				if(syrupBtn[i].isSelected()) {
					syrup = "ok";
				}
			}
			// 샷 여부
			if(etcBtn[0].isSelected()) {
				shot = "추가";
			}
			// 크림 여부
			if(etcBtn[1].isSelected()) {
				cream = "추가";
			}
			
			// 커피 가격 x 잔 수 
			expense = c_price * cups;
			
			// 옵션을 추가 할 때 마다 가격을 더함.
			if(syrup.equals("ok")) {
				expense = expense + 1000;
			}		
			if(shot.equals("추가")) {
				expense = expense + 1000;
			}	
			if(cream.equals("추가")) {
				expense = expense + 1500;
			}
			
			// 컬럼명 - > 원래 명칭
			if(c_size.equals("c_short")) {
				c_size = "short";
			}
			else if(c_size.equals("c_tall")){
				c_size = "tall";
			}
			else if(c_size.equals("c_grande")){
				c_size = "grande";
			}
			
			// 입력된 주문 정보를 dto로 주문 내역뷰에 보냄.
			OrderDto od = new OrderDto(c_type, syrup, c_size, shot, cream, cups, expense);
			
			new OrderView(od);
			this.dispose();
		}
	}
}	
