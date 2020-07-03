package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import dao.MemberDao;
import dto.MenuDto;

public class PriceView extends JFrame {

	private JScrollPane js;	
	private JTable table;
	private List<MenuDto> list;
	private JLabel priceLabel;
	
	private Container c;
	
	public PriceView() {
		
		super("가격표");
		
		c = getContentPane();
		c.setLayout(null);
		// 뷰 제목
		priceLabel = new JLabel("가격표");
		priceLabel.setBounds(204, 5, 120, 30);
		priceLabel.setFont(new Font("HY견고딕", Font.PLAIN, 25));
		c.add(priceLabel);
		// db에서 가져온 가격테이블의 데이터를 저장할 리스트
		list = new ArrayList<MenuDto>();
		MemberDao dao = MemberDao.getInstance();
		// db에서 가격테이블의 데이터 출력.
		list = dao.coffeeMenu();
		
		String header[] = {"커피 종류", "Short", "Tall", "Grande"};
		String content[][] = new String[10][4];
		
		for (int i = 0; i < list.size(); i++) {
			content[i][0] = list.get(i).getC_type();
			content[i][1] = list.get(i).getC_short()+"";
			content[i][2] = list.get(i).getC_tall()+"";
			content[i][3] = list.get(i).getC_grande()+"";	
		}
		// 가격테이블의 데이터를 저장할 jtable
		table = new JTable(content, header);
		js = new JScrollPane(table);
		js.setBounds(10, 45, 470, 183);
		c.add(js);
			
	    c.setBackground(new Color(245, 245, 215));
   		setBounds(1060, 150, 505, 280);
		setVisible(true);
		
	}
	
}