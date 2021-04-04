## 早上
- 10.10完全根据xxx搜索单个值的封装
> 如根据作者id显示标题,根据标题显示id,但作者id如果传对象进去是传作者,有点不行,映射后是id

```java
利用了authorBean,来根据不同的数据要求设置不同的构造方法?这样规范吗?
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404152241134.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

```java
update:属于业务对象,是规范的嘻嘻(4.4)
```

## 下午
- 14.19思考出分页查询的方法
> 记录currentPage,通过点击按钮执行相应select语句即可,那得new窗口阿还是?直接setText看看能不能改变吧
- 14.21思考结果集要封装成什么,得数组吧不能链表,因为要遍历循环得有索引
- 15.11解决权限问题
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404162440821.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

> 本项目select标题有三种情况,只根据权限,权限以及作者id,权限以及标题

- 16.57 getid似乎可以写到baseDao,很多都得用到
## 晚上
- 修改了一下页面开完会写周记笔记

## 完成
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404225828492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

