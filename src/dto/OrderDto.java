package dto;

import java.io.Serializable;

public class OrderDto implements Serializable {
	/*
	 주문 내역에 출력할 dto
	 */
	// 커피의 종류
	private String c_type;
	// 시럽유무
	private String syrup;
	// 커피의 사이즈
	private String c_size;
	// 샷 유무
	private String shot;
	// 크림 유무
	private String cream;
	// 잔 수
	private int cups;
	// 최종 지불 금액
	private int expence;
	
	
	public OrderDto() {}

	public OrderDto(String c_type, String syrup, String c_size, String shot, String cream, int cups,
			int expence) {
		super();
		this.c_type = c_type;
		this.syrup = syrup;
		this.c_size = c_size;
		this.shot = shot;
		this.cream = cream;
		this.cups = cups;
		this.expence = expence;
	}


	public String getC_type() {
		return c_type;
	}


	public void setC_type(String c_type) {
		this.c_type = c_type;
	}


	public String getSyrup() {
		return syrup;
	}


	public void setSyrup(String syrup) {
		this.syrup = syrup;
	}


	public String getC_size() {
		return c_size;
	}


	public void setC_size(String c_size) {
		this.c_size = c_size;
	}


	public String getShot() {
		return shot;
	}


	public void setShot(String shot) {
		this.shot = shot;
	}


	public String getCream() {
		return cream;
	}


	public void setCream(String cream) {
		this.cream = cream;
	}


	public int getCups() {
		return cups;
	}


	public void setCups(int cups) {
		this.cups = cups;
	}


	public int getExpence() {
		return expence;
	}


	public void setExpence(int expence) {
		this.expence = expence;
	}


	@Override
	public String toString() {
		return "OrderDto [c_type=" + c_type + ", syrup=" + syrup + ", c_size=" + c_size
				+ ", shot=" + shot + ", cream=" + cream + ", cups=" + cups + ", expence=" + expence + "]";
	}
	
	
	
	
	
}
