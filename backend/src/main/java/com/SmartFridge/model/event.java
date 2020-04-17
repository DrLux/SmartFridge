package com.smartfridge.model;

import javax.persistence.*;
import java.sql.Date;
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

	@Column(name = "expiry_date")
	private Date expiry_date;

	public Event() {
	}

	public Event(String url_img, String name) {
		this.name = name;
		this.url_img = url_img;
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

	public Date getExpiry_date() {
		return this.expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Event)) return false;
		Event event = (Event) o;
		return user_id == event.user_id &&
				name.equals(event.name) &&
				expiry_date.equals(event.expiry_date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, user_id, expiry_date);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + "]";
	}
}
