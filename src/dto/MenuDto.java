package dto;

import java.io.Serializable;

public class MenuDto implements Serializable {
	/*
	 가격표에 출력할 dto
	 */
	// 커피의 종류
	private String c_type;
	// 작은 사이즈
	private int c_short;
	// 중간 사이즈
	private int c_tall;
	// 큰 사이즈
	private int c_grande;
	
	public MenuDto() {}

	public MenuDto(String c_type, int c_short, int c_tall, int c_grande) {
		super();
		this.c_type = c_type;
		this.c_short = c_short;
		this.c_tall = c_tall;
		this.c_grande = c_grande;
	}

	public String getC_type() {
		return c_type;
	}

	public void setC_type(String c_type) {
		this.c_type = c_type;
	}

	public int getC_short() {
		return c_short;
	}

	public void setC_short(int c_short) {
		this.c_short = c_short;
	}

	public int getC_tall() {
		return c_tall;
	}

	public void setC_tall(int c_tall) {
		this.c_tall = c_tall;
	}

	public int getC_grande() {
		return c_grande;
	}

	public void setC_grande(int c_grande) {
		this.c_grande = c_grande;
	}

	@Override
	public String toString() {
		return "MenuDto [c_type=" + c_type + ", c_short=" + c_short + ", c_tall=" + c_tall + ", c_grande=" + c_grande
				+ "]";
	}
	
	
	
	
	
}
