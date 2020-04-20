package com.smartfridge.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "user_id")
	private long user_id;

	@Column(name = "url_img")
	private String url_img;

	@Column(name = "year")
	private int year;

	@Column(name = "month")
	private int month;

	@Column(name = "day")
	private int day;

	public Event() {
		this.name = "";
		this.url_img = "";
		this.user_id = 0;
		this.year = 0;
		this.month = 0;
		this.day = 0;
	}

	public Event(String name, long user_id, String url_img, int year, int month, int day) {
		this.name = name;
		this.url_img = url_img;
		this.user_id = user_id;
		this.year = year;
		this.month = month;
		this.day = day;
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

	public void setYear(int expiry_date_year) {
		this.year = expiry_date_year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int expiry_date_month) {
		this.month = expiry_date_month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int expiry_date_day) {
		this.day = expiry_date_day;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Event)) return false;
		Event event = (Event) o;
		return id == event.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + "]";
	}
}
