package com.saket.spacex.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="Crew_Table")
public class Crew {

    @PrimaryKey(autoGenerate = true)
    private int crewId;

    @SerializedName("name")
    @ColumnInfo(name="name")
    private String name;

    @SerializedName("agency")
    @ColumnInfo(name="agency")
    private String agency;

    @SerializedName("image")
    @ColumnInfo(name="image")
    private String image;

    @SerializedName("wikipedia")
    @ColumnInfo(name="wikipedia")
    private String wiki;

    @SerializedName("status")
    @ColumnInfo(name ="status")
    private String status;

    @SerializedName("id")
    private String id;

    public Crew(int crewid, String name, String agency, String image, String wiki, String status, String id)
    {
        this.crewId=crewid;
        this.name=name;
        this.agency=agency;
        this.image=image;
        this.wiki=wiki;
        this.status=status;
        this.id=id;
    }

    public Crew(String name, String agency, String image, String wiki, String status, String id)
    {

        this.name=name;
        this.agency=agency;
        this.image=image;
        this.wiki=wiki;
        this.status=status;
        this.id=id;
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewid) {
        this.crewId = crewid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewid=" + crewId +
                ", name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                ", image='" + image + '\'' +
                ", wiki='" + wiki + '\'' +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
