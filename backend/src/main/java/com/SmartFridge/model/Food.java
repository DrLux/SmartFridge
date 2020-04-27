package com.smartfridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
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
	private String category;

	@Column(name = "year")
	private int year;

	@Column(name = "month")
	private int month;

	@Column(name = "day")
	private int day;

	public Food(){
		this.name = "";
		this.url_img = "";
		this.user_id = 0;
		this.year = 0;
		this.month = 0;
		this.day = 0;
		this.category = "";
	}

	public Food(String name, long user_id, String url_img, int year, int month, int day, String category) {
		this.name = name;
		this.url_img = url_img;
		this.user_id = user_id;
		this.year = year;
		this.month = month;
		this.day = day;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
				'}';
	}
}
