package com.smartfridge.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shopitem")
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "url_img")
    private String url_img;

    @Column(name = "notes")
    private String notes;

    @Column(name = "automatic_gen")
    private Boolean automatic_gen;

    @Column(name = "delete_callback")
    private String delete_callback;

    @Column(name = "buy_callback")
    private String buy_callback;



    public ShopItem() {
        this.name = "";
        this.url_img = "";
        this.user_id = 0;
        this.notes = "";
        this.automatic_gen = Boolean.TRUE;
        this.delete_callback = "";
        this.buy_callback = "";
    }

    public ShopItem(String name, long user_id, String url_img, String notes, Boolean automatic_gen,  String delete_callback, String buy_callback) {
        this.name = name;
        this.url_img = url_img;
        this.user_id = user_id;
        this.notes = notes;
        this.automatic_gen = automatic_gen;
        this.buy_callback = buy_callback;
        this.delete_callback = delete_callback;
    }

    //Constructor without Notes
    public ShopItem(String name, long user_id, String url_img, Boolean automatic_gen) {
        this.name = name;
        this.url_img = url_img;
        this.user_id = user_id;
        this.automatic_gen = automatic_gen;
        this.buy_callback = buy_callback;
        this.delete_callback = delete_callback;
    }

    public String getDelete_callback() {
        return delete_callback;
    }

    public void setDelete_callback(String delete_callback) {
        this.delete_callback = delete_callback;
    }

    public String getBuy_callback() {
        return buy_callback;
    }

    public void setBuy_callback(String buy_callback) {
        this.buy_callback = buy_callback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopItem)) return false;
        ShopItem shopItem = (ShopItem) o;
        return id == shopItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                ", automatic_gen=" + automatic_gen +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getAutomatic_gen() {
        return automatic_gen;
    }

    public void setAutomatic_gen(Boolean automatic_gen) {
        this.automatic_gen = automatic_gen;
    }

}
