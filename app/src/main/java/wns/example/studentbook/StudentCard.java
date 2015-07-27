package wns.example.studentbook;

import android.graphics.drawable.Drawable;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardBuilder;

import java.io.Serializable;

/**
 * Created by wns349 on 2015-07-22.
 */
public class StudentCard implements Serializable {
    private String name;
    private String major;
    private Drawable photo;

    public StudentCard() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public String getName() {
        return this.name;
    }

    public String getMajor() {
        return this.major;
    }

    public Drawable getPhoto() {
        return this.photo;
    }
}
