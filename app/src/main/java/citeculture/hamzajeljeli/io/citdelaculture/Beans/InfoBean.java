package citeculture.hamzajeljeli.io.citdelaculture.Beans;

import java.io.Serializable;

public class InfoBean implements Serializable {
    private String title;
    private String content;
    private String dt;
    private String banner;

    public InfoBean(String title, String content, String dt, String banner) {
        this.title = title;
        this.content = content;
        this.dt = dt;
        this.banner = banner;
    }

    public InfoBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
