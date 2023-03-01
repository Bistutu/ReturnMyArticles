package thinkstu.enyity;


import java.util.*;

/**
 * @author : ThinkStu
 * @since : 2023/3/1, 09:45, 周三
 **/
public class HomePageResult {

    /**
     * code : 200
     * message : success
     * traceId : c507b2c9-b7d1-4288-8fd2-d0a8be06ba95
     * data : {"list":[{"articleId":129259952,"title":"服务器厂商自检测Java程序","description":"以腾讯云服务器举例","url":"https://thinkstu.blog.csdn.net/article/details/129259952","type":1,"top":false,"forcePlan":false,"viewCount":22,"commentCount":0,"editUrl":"https://editor.csdn.net/md?articleId=129259952","postTime":"2023-02-28 13:41:39","diggCount":0,"formatTime":"20 小时前"},{"articleId":129207437,"title":"SpringBoot外部资源配置","description":"今天发现了一种\u201c新奇\u201d的写法，可以将配置文件与/static静态文件目录外置。","url":"https://thinkstu.blog.csdn.net/article/details/129207437","type":1,"top":false,"forcePlan":false,"viewCount":4695,"commentCount":0,"editUrl":"https://editor.csdn.net/md?articleId=129207437","postTime":"2023-02-24 19:52:14","diggCount":0,"formatTime":"2023.02.24"}],"total":165}
     */

    private int code;
    private String message;
    private String traceId;
    private DataBean data;

    public int getCode() {return code;}

    public void setCode(int code) {this.code = code;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public String getTraceId() {return traceId;}

    public void setTraceId(String traceId) {this.traceId = traceId;}

    public DataBean getData() {return data;}

    public void setData(DataBean data) {this.data = data;}

    public static class DataBean {
        /**
         * list : [{"articleId":129259952,"title":"服务器厂商自检测Java程序","description":"以腾讯云服务器举例","url":"https://thinkstu.blog.csdn.net/article/details/129259952","type":1,"top":false,"forcePlan":false,"viewCount":22,"commentCount":0,"editUrl":"https://editor.csdn.net/md?articleId=129259952","postTime":"2023-02-28 13:41:39","diggCount":0,"formatTime":"20 小时前"},{"articleId":129207437,"title":"SpringBoot外部资源配置","description":"今天发现了一种\u201c新奇\u201d的写法，可以将配置文件与/static静态文件目录外置。","url":"https://thinkstu.blog.csdn.net/article/details/129207437","type":1,"top":false,"forcePlan":false,"viewCount":4695,"commentCount":0,"editUrl":"https://editor.csdn.net/md?articleId=129207437","postTime":"2023-02-24 19:52:14","diggCount":0,"formatTime":"2023.02.24"}]
         * total : 165
         */

        private int total;
        private List<ListBean> list;

        public int getTotal() {return total;}

        public void setTotal(int total) {this.total = total;}

        public List<ListBean> getList() {return list;}

        public void setList(List<ListBean> list) {this.list = list;}

        public static class ListBean {
            /**
             * articleId : 129259952
             * title : 服务器厂商自检测Java程序
             * description : 以腾讯云服务器举例
             * url : https://thinkstu.blog.csdn.net/article/details/129259952
             * type : 1
             * top : false
             * forcePlan : false
             * viewCount : 22
             * commentCount : 0
             * editUrl : https://editor.csdn.net/md?articleId=129259952
             * postTime : 2023-02-28 13:41:39
             * diggCount : 0
             * formatTime : 20 小时前
             */

            private int articleId;
            private String title;
            private String description;
            private String url;
            private int type;
            private boolean top;
            private boolean forcePlan;
            private int viewCount;
            private int commentCount;
            private String editUrl;
            private String postTime;
            private int diggCount;
            private String formatTime;

            public int getArticleId() {return articleId;}

            public void setArticleId(int articleId) {this.articleId = articleId;}

            public String getTitle() {return title;}

            public void setTitle(String title) {this.title = title;}

            public String getDescription() {return description;}

            public void setDescription(String description) {this.description = description;}

            public String getUrl() {return url;}

            public void setUrl(String url) {this.url = url;}

            public int getType() {return type;}

            public void setType(int type) {this.type = type;}

            public boolean isTop() {return top;}

            public void setTop(boolean top) {this.top = top;}

            public boolean isForcePlan() {return forcePlan;}

            public void setForcePlan(boolean forcePlan) {this.forcePlan = forcePlan;}

            public int getViewCount() {return viewCount;}

            public void setViewCount(int viewCount) {this.viewCount = viewCount;}

            public int getCommentCount() {return commentCount;}

            public void setCommentCount(int commentCount) {this.commentCount = commentCount;}

            public String getEditUrl() {return editUrl;}

            public void setEditUrl(String editUrl) {this.editUrl = editUrl;}

            public String getPostTime() {return postTime;}

            public void setPostTime(String postTime) {this.postTime = postTime;}

            public int getDiggCount() {return diggCount;}

            public void setDiggCount(int diggCount) {this.diggCount = diggCount;}

            public String getFormatTime() {return formatTime;}

            public void setFormatTime(String formatTime) {this.formatTime = formatTime;}
        }
    }
}
