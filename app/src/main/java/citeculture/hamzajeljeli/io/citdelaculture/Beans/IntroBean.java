package citeculture.hamzajeljeli.io.citdelaculture.Beans;

public class IntroBean {
    private String banner;
    private String content;


    // Getter Methods

    public IntroBean(String banner, String content) {
        this.banner = banner;
        this.content = content;
    }

    public IntroBean() {
    }

    // Setter Methods

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
