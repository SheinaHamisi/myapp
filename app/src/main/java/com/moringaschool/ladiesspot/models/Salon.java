package com.moringaschool.ladiesspot.models;

import java.util.Objects;

public class Salon {
    private Long id;
    private String name;
    private String image;
    private String location;
    private String phone;
    private Integer rating;
    private Integer workingHours;

    public Salon() {
    }

    public Salon(Long id, String name, String image, String location, String phone, Integer rating, Integer workingHours) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.location = location;
        this.phone = phone;
        this.rating = rating;
        this.workingHours = workingHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salon salon = (Salon) o;
        return Objects.equals(id, salon.id) && Objects.equals(name, salon.name) && Objects.equals(image, salon.image) && Objects.equals(location, salon.location) && Objects.equals(phone, salon.phone) && Objects.equals(rating, salon.rating) && Objects.equals(workingHours, salon.workingHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, location, phone, rating, workingHours);
    }

    @Override
    public String toString() {
        return "Salon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", rating=" + rating +
                ", workingHours=" + workingHours +
                '}';
    }
}
