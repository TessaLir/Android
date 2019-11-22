package ru.vetukov.spec.ex_3_allsaints;

public class Saint implements Comparable<Saint> {

    private String name;
    private String dob;
    private String dod;
    private float rating = 0f;

    public Saint(String name, String dob, String dod, float rating) {
        this.name = name;
        this.dob = dob;
        this.dod = dod;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(Saint saint) {
        return getName().compareTo(saint.getName());
    }
}
