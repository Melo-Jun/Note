
# 早上
- 日常大物离散


# 下午
- 高数课
- 17.00构思select语句
> 似乎可以联想delete的方法，主要是要根据什么来搜索是可变的
- 17.08发现跟delete一样用无参构造器，要根据什么就再set相应属性即可！妙蛙
>　因为手写的方法只添加不为ｎｕｌｌ的值
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210402170919231.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

---
~~- 17.15思考select后边直接用*吗，主要是basedao将查询用到的字段存储起来不现实，继承子类才能这样做,以及查询的结果~~ 

- ~~17.19思考用不用将结果集封装，总是返回结果集是不是不太好，view里边得rs.next~~ 
> 若封装成链表，直接for each即可，不过Jtree那边还是没办法，因为一对多
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210402172211224.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

- 17.39发现select的sql语句还是得写在各个类里边，因为from的表不一样
> 打算设置成两个参数(根据的对象，要查询的对象)
- 17.56完成select封装，运用如上所述的方法，并在basedao的search方法返回结果集到特定dao类中，dao类再将Rs封装成链表传递给View
> 不过资源的关闭还有问题今晚再解决

# 晚上

- 19.39才开始码代码
>　7点图书馆才开的门
- 没什么状态跑了步,22.02才洗完澡开始
> 发现根本就不需要有两个对象!都只是在一个表内操作的而已,已经有关联了
- ~~可不可以就是还是两个对象,根据的对象来获取类名然后末尾加个_id,或者直接获取类名其实就可以了,转化为小写就可以~~ 
> 其实也只是在各个Dao中再补充sql语句就好了吧?自己想做得太完美了?
- 打算改成将sql传给base去执行然后封装起来
> 主要是不返回结果集,Tree怎么生成(关键问题)
- 遇到瓶颈了emmm



### 完成
今日打码时间太少了,遇到瓶颈了
> 主要都在思考,动代码不多

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210402234507309.png)



# 明日计划
- 改善select语句
- 回归实现一些基本功能




