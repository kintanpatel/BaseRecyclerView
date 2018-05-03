package in.kintanpatel.customrecylerview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sai on 26/09/2017.
 */

public class StudentBean {
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
       private String studentName;

    public StudentBean(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return title;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
