package com.smartfridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.text.DateFormat;
import java.util.Objects;


@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "user_id")
	private long user_id;

	@Column(name = "url_img")
	private String url_img;

	@Column(name = "category")
	private Category category;

	@Column(name = "expiry_date")
	private Date expiry_date;

	public Food(){
		this.name = "";
		this.url_img = "";
		this.user_id = 0;
		this.expiry_date = null;
		this.category = Category.c;
	}

	public Food(String name, long user_id, String url_img, Date date, Category category) {
		this.name = name;
		this.url_img = url_img;
		this.user_id = user_id;
		this.expiry_date = date;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUrl_img() {
		return this.url_img;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getExpiry_date() {
		return this.expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Food)) return false;
		Food food = (Food) o;
		return id == food.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Food{" +
				"id=" + id +
				", name='" + name + '\'' +
				", user_id=" + user_id +
				", category=" + category +
				", expiry_date=" + expiry_date +
				'}';
	}
}
