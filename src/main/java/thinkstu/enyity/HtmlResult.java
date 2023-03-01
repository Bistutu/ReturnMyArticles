package thinkstu.enyity;


/**
 * @author : ThinkStu
 * @since : 2023/3/1, 08:53, 周三
 **/
public class HtmlResult {
    private int code;
    private String title;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    private String html;
}
