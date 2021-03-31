
# 早上
- 日常高数离散


# 下午
- 工程训练+跑步

# 晚上

- 根据选中的节点配合按钮
1. ~~修改名字简单~~
2. ~~增加也简单~~
3. 笔记换地方的话还得一堆业务逻辑来判断
~~> 只需要获取路径距离即可以判断是什么身份，但获取路径距离是另一个监听器，各种监听器混用还是有点难以实现阿~~ 
***通过getPathCount实现***
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210331201426860.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

- 19.42完成动态生成，差业务逻辑整合
- 20.34终于弄好监听器，其实之前是因为没有弄成全局变量
> 害，还是得深钻不能浅尝辄止，之前轻易就放弃了该方法
- 完成修改名称、删除等功能
> 还差修改分组,只能通过输入咯，然后一系列Dao操作麻烦

- 21.24打算写delete语句，根据传进来的属性能怎么操作
> 删除一个对象，通用操作该怎么写
### 完成
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210330232539682.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

# 改进
- 最好将表与表的关联通过主键来实现(因为不可改变),然后多表连接
> 本来是通过名字的，若改了名字那会出现很多问题



# 明日计划
- 改进表与表之间的关系
- 改善Jtree等View中的service


