package thinkstu;

import com.alibaba.fastjson2.*;
import com.vladsch.flexmark.html2md.converter.*;
import okhttp3.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import thinkstu.enyity.*;

import java.io.*;
import java.util.*;


/**
 * @author : ThinkStu
 * @since : 2023/3/1, 08:51, 周三
 **/
public class ReturnMyArticles {
    public static void main(String[] args) throws IOException {
        // 将 username 修改成你的用户ID，启动程序开始下载，文件将会保存在资源目录 /resources/pages/
        String username = "qq_35760825";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // CSDN 规定每页最多返回 100 条数据
        Integer pageSize = 1;
        Integer count    = 1;
        while (true) {
            Request request = new Request.Builder()
                    .url("https://blog.csdn.net/community/home-api/v1/get-business-list" +
                            "?page=" + pageSize++ + "&size=100&businessType=blog&noMore=false&username=" + username)
                    .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                    .build();
            Response                               response = client.newCall(request).execute();
            HomePageResult                         homePage = JSON.parseObject(response.body().string(), HomePageResult.class);
            List<HomePageResult.DataBean.ListBean> articles = homePage.getData().getList();
            // 当 articles 为空时，表示主页遍历完成（已无文章）
            if (articles == null || articles.isEmpty()) {
                System.out.println("\n===》任务完成，一共 " + (count - 1) + " 篇文章！");
                return;
            }
            Iterator<HomePageResult.DataBean.ListBean> iterator = articles.iterator();
            // 循环遍历获取全部文章
            while (iterator.hasNext()) {
                HomePageResult.DataBean.ListBean next  = iterator.next();
                String                           title = next.getTitle().trim();
                title = title.replaceAll("(/|\\\\)", "_");
                Integer    articleId  = next.getArticleId();
                HtmlResult htmlResult = null;
                try {
                    htmlResult = JSON.parseObject(client.newCall(new Request.Builder()
                            .url("https://www.helloworld.net/getUrlHtml?url=https://blog.csdn.net/"
                                    + username + "/article/details/" + articleId)
                            .build()).execute().body().string(), HtmlResult.class);
                } catch (IOException e) {
                    System.out.println("===》文章 " + articleId + " 下载失败");
                }
                Document document = Jsoup.parse(htmlResult.getHtml());
                // 移除 [TOC] 目录
                document.select("div[class=toc]").remove();
                String data = document.toString();
                // 获得原始 html 字符集，利用 Flexmark 框架将其转换成 md 文档
                FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();
                data = converter.convert(data);

                // 清除一些多余的格式内容
                data = data.replaceAll("\\[.*]\\(https://blog.csdn.net/" + username + "/article/details/\\d{9}\\)\\{#.*?}(.*)", "$1")
                        .replaceAll("\\{#content_views}", "")
                        .replaceAll("本文转自.*，如有侵权，请联系删除。", "")
                        .replaceAll("prism language-(.*)", "$1");
                // 将文章保存到 /resources/pages/
                File pageFile = new File("src/main/resources/pages/");
                // 如果目录不存在则新建
                if (!pageFile.exists()) {
                    pageFile.mkdirs();
                }
                File file = new File(pageFile + File.separator + title + ".md");
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(data);
                } catch (Exception e) {
                    System.out.println("===》文章 " + articleId + " 失败");
                }
                System.out.println("第 " + count++ + " 次执行任务，文章 " + articleId + " 已下载");
            }
        }
    }
}
