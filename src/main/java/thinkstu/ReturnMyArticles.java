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
        // TODO username 为用户ID
        String username = "qq_35760825";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // 每页最多 100 条数据
        Integer page  = 1;
        Integer count = 1;
        while (true) {
            Request request = new Request.Builder()
                    .url("https://blog.csdn.net/community/home-api/v1/get-business-list" +
                            "?page=" + page++ + "&size=100&businessType=blog&noMore=false&username=" + username)
                    .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                    .build();
            Response                               response       = client.newCall(request).execute();
            HomePageResult                         homePageResult = JSON.parseObject(response.body().string(), HomePageResult.class);
            List<HomePageResult.DataBean.ListBean> articles       = homePageResult.getData().getList();
            // 当 articles 为空时，主页遍历完成（已无文章）
            if (articles == null || articles.isEmpty()) {
                System.out.println("任务完成，一共 ===》" + count + " 篇文章！");
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
//
//            if (articleId != 128251533) {
//                continue;
//            }
                try {
                    htmlResult = JSON.parseObject(client.newCall(new Request.Builder()
                            .url("https://www.helloworld.net/getUrlHtml?url=https://blog.csdn.net/" + username + "/article/details/" + articleId)
                            .build()).execute().body().string(), HtmlResult.class);
                } catch (IOException e) {
                    System.out.println("===》文章 " + articleId + " 失败");
                }
                Document document = Jsoup.parse(htmlResult.getHtml());
                // 移除 [TOC] 目录
                document.select("div[class=toc]").remove();
                String data = document.toString();

                FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();
                data = converter.convert(data);

                // 清除格式上的 bug
                data = data.replaceAll("\\[.*]\\(https://blog.csdn.net/" + username + "/article/details/\\d{9}"
                        + "\\)\\{#.*?}(.*)", "$1");
                data = data.replaceAll("\\{#content_views}", "");
                data = data.replaceAll("本文转自.*，如有侵权，请联系删除。", "");
                data = data.replaceAll("### 文章目录.*?/>", "");
                data = data.replaceAll("prism language-(.*)", "$1");

                // 将文章保存到
                File pageFile = new File("src/main/resources/pages/");
                // 如果不存在则新建
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
