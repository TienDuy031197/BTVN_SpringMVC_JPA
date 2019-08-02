package com.onrugi.model;

public class DictionaryDTO {
	private Integer id;
	private String t_viet;
	private String t_anh;

	public DictionaryDTO(Integer id, String t_viet, String t_anh) {
		super();
		this.id = id;
		this.t_viet = t_viet;
		this.t_anh = t_anh;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getT_viet() {
		return t_viet;
	}

	public void setT_viet(String t_viet) {
		this.t_viet = t_viet;
	}

	public String getT_anh() {
		return t_anh;
	}

	public void setT_anh(String t_anh) {
		this.t_anh = t_anh;
	}

}
