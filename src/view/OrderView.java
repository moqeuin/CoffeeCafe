package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dto.OrderDto;

public class OrderView extends JFrame implements ActionListener {
	
	private JButton menuBtn;
	private JButton exitBtn;
	private JScrollPane js;	
	private JTable table;
	private JLabel orderLabel;
	private OrderDto _od;
	
	private Container c;
	
	public OrderView(OrderDto od) {
		super("주문내역");
		// 주문한 내역의 dto
		this._od = od;
		
		c = getContentPane();
		c.setLayout(null);
		// 뷰 제목
		orderLabel = new JLabel("주문내역");
		orderLabel.setBounds(390, 15, 120, 30);
		orderLabel.setFont(new Font("HY견고딕", Font.PLAIN, 25));
		c.add(orderLabel);
		// 주문의 내역을 jtable로 출력
		String header[] = {"커피 종류", "시럽", "크기", "샷 추가", "휘핑크림", "잔", "총액"};
		String content[][] = new String[5][7];
		
		content[0][0] = _od.getC_type();
		content[0][1] = _od.getSyrup();
		content[0][2] = _od.getC_size();
		content[0][3] = _od.getShot();
		content[0][4] = _od.getCream();
		content[0][5] = _od.getCups()+"";
		content[0][6] = _od.getExpence()+"";
			
		table = new JTable(content, header);
		js = new JScrollPane(table);
		js.setBounds(7, 60, 870, 103);
		c.add(js);
		
		// 메뉴 버튼
		menuBtn = new JButton("주문화면");
		menuBtn.setBounds(500, 180, 120, 40);
		menuBtn.addActionListener(this);
		c.add(menuBtn);
		// 종료 버튼
		exitBtn = new JButton("종료");
		exitBtn.setBounds(640, 180, 120, 40);
		exitBtn.addActionListener(this);
		c.add(exitBtn);
		
		c.setBackground(new Color(245, 245, 215));
   		setBounds(450, 220, 900, 280);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 메뉴 버튼
		if(obj == menuBtn) {
			new ChoiceView();
			this.dispose();
		}
		// 종료 버튼
		else if( obj == exitBtn) {
			new LoginView();
			this.dispose();
		}	
	}
}