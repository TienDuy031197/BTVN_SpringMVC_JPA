package com.onrugi.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "dictionary")
public class Dictionary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "t_viet")
	private String tiengviet;

	@Column(name = "t_anh")
	private String tienganh;

	public Dictionary() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTiengviet() {
		return tiengviet;
	}

	public void setTiengviet(String tiengviet) {
		this.tiengviet = tiengviet;
	}

	public String getTienganh() {
		return tienganh;
	}

	public void setTienganh(String tienganh) {
		this.tienganh = tienganh;
	}

}
