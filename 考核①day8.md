
# 早上
- 9.10图书馆码代码
> 打算不同dao写不同select语句再传给base即可,考虑后续base怎么写
- 思考出解决Jtree的办法!
> 用map存每个知识库有几个分支,然后嵌套for循环即可!(用两个集合存)
- 似乎得反向来,先统计每个group几个为一组,然后再根据located_folder去找folder_name方便一点
> 但是如果没group的话就GG了,找不到知识库了
- 11.06终于成功,中途弄了单元测试还没成功,还是会受其他类影响
> 先得到知识库的id,name的Map,然后获取Map中知识库id,根据id生成group即可!还是不能说完全就抛弃掉原来的方法,要仔细想想!
- queryMap是传obj进来,或许跟queryList这两个都得传object数组进来,可能有多个要填充,不对阿那setparams方法就不对了,还是obj好一点
- 11.27完成了select重新封装并生成Jtree!关键问题暂时解决了
> 不过自己还是缺少总结,很多写过的东西当时适用,后来出现新功能就不适用了,尽管想从宏观上来构思,但还是没那么简单,完成一个功能后很开心,就过去了没去思考与总结
- 14.48修改注册跟登录分开为两个dao,并让注册继承登录的isExist方法
> 还是要将isExist放在Base里边呢
- 感觉很难回顾之前的函数是怎么写的,有点难以思考,先改register吧
- 14.56又发现LoginDao里边只有个isExist,完全可以改成别的
- 15.17将LoginDao和RegisterDao都合并到了UserDao里边
> judgePass和isExisted
- 15.36修改好,注意是isEmpty!包括object数组也是!
# 问题
- 标题Label数组的个数要先开很大吗还是动态增加,或者用链表?
- 打算下午看表格了,treeview也得改一下先
- 结果集映射有更好办法?是指映射为对象吧?我的Map只有一个键值对而已,还是运用反射?
# 下午
- 13.36中午没回宿舍,在图书馆还是会困阿,但是又不想睡觉害
- 睡了20分钟.14.02开始码代码
- 合并了Login和register到userDao
- 修改Login
# 晚上

- 19.44卡在了Jlist
> 一直会获取到两个
- 19.56解决问题
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021040319570181.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
>因为之前是点下去和释放,会得到两次结果
- ~~20.36意识到也许View里边有些得写成函数,如filltable一样~~ 
- ~~20.55发现如果直接new的话会出问题,要不就函数吧~~ 
- 21.18还卡在触发事件后就没有下拉条了
> 很执着不想换方法,又这样了
- **21.23换了个方法居然就可以了!换了一个构造方法(先前弃用的)**
> 	![在这里插入图片描述](https://img-blog.csdnimg.cn/20210403230621796.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

- 22.03对之前的页面做了修改,完成了列出笔记标题功能
> 还差分页查询明早看看能不能解决了,以及查询出来的结果进行封装


### 完成


![在这里插入图片描述](https://img-blog.csdnimg.cn/20210403230703203.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)



## 尚存问题
- 怎么保留点赞状态

# 明日计划
- 分页查询
- 封装结果集为对象?map?数组?
- 手写映射方法?

