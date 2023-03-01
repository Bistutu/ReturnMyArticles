# Return My Articles

> CSDN 归还我的文章！

一款 Java 程序，可以批量下载自己的CSDN文章，下载的文章将以`MarkDown`格式进行存储。

请勿用于非法用途，遵守相关法律法规！

## 使用步骤

1. 打开 CSDN 个人主页，从主页链接中复制自己的用户 ID，如：`qq_35760825`

   ```shell
   https://blog.csdn.net/qq_35760825?spm=1000.2115.3001.5343
   ```

2. 打开程序，修改方法 `ReturnMyArticles` 中的属性值 `username = 你的用户ID` 

   ```shell
   // 将 username 修改成你的用户ID
   String username = "qq_35760825";
   ```

3. 运行`ReturnMyArticles`方法，程序自动下载文章，并将文章保存在资源目录 `/resources/pages/`

   ```shell
   第 1 次执行任务，文章 128891802 已下载
   第 2 次执行任务，文章 128891707 已下载
   ......
   第 10 次执行任务，文章 127817001 已下载
   
   ===》任务完成，一共 10 篇文章！
   ```

## 感谢

- [helloworld-Co/html2md](https://github.com/helloworld-Co/html2md)

## 参与贡献

目前项目还不是很完善，部分文章在转换时会产生格式错乱，我们非常欢迎你的贡献。