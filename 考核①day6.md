
# 早上
- 日常大英体育


# 下午
- 15.10才开始码代码(封装delete方法)
> 运用setParams方法(根据传进来的对象获取属性值然后填充),而delete只需要填充一次，故可运用无参构造器生成对象，再Set相应的属性即可达成效果！！！
- id新增时对象里没有设置，只有数据库中有
> 改成通过name来删除，根据对象获取属性名称，再利用StringUtils工具将其转换为数据库字段名！
- 15.53看service，打算修改一下View层业务逻辑
- 16.49了！
> ~~user怎么弄，通过调用service方法吗还是全局变量~~ 
- 15.04弄好service接口和实现类，修改folderview中的Dao操作为service
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210401155358795.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
- 出现空指针异常
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210401173111206.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

# 晚上

-  将用户存在类static中，后续修改的话顺便set一下也不麻烦
> 不过就不能方便的Text了，不过可以在Text里边直接修改类变量
-  修改成Id关联了
> 遇到：rs只是一列 select什么就只在那一列而已
- 生成树是根据名字，选择的时候也是获取到名字而已，得写个根据名字找到id的方法吗
> 已实现，但不知道有没有更好的办法
- 修改页面service


# 问题
- 还要不要传user过来，还是类？
> 目前是用类static
- 已经选了再次打开设置分组，会保留上一次选择的记录，这个逻辑还没写










### 完成
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210402000216790.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)






# 明日计划
- 封装select语句
- 周末再回归实现一些基本功能或者先着手做了




