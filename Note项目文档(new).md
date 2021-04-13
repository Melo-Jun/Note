# 常用方法/策略
- dao中增删查改统一运用反射，参数设置为对象，根据传进来的对象，获取属性名和属性值，动态填充sql语句
- 统一前台传

# 配置文件
- db.properties

> 实体类对应数据库表名

- factory.properties

> BeanFactory中Dao类和Service类配置路径

- jdbc.properties

> 数据库配置和数据库连接池配置

# 代码性能

## 安全性

- 数据库密码运用MD5工具类加密

- PrepareStatement防止sql注入

- 每个Service都继承BaseService的notNull方法,防止空输入或无效输入

  > 检测是否为空或全为空字符

## 封装性

- Service和Dao都有相应的接口,只暴露接口而不关心内部如何实现
- Entity层只有get和set方法

## 可维护性

- 类,方法,静态变量都提供了注释
- 使用javadoc注释
- 单一职责:每个Service负责相应业务,并有对应的Dao,两者可以独立完成职责

## 数据出入口

- 所有资源获取与释放,获取数据库连接都统一在executeUpdate和executeQuery两个函数中
- 前端界面返回结果统一封装在Result中

## 通用性/复用性

- 各Dao都继承了BaseDao,BaseDao中封装有通用的增删查改方法
- 各Entity类都继承BaseEntity
- 各Service都继承BaseService
- 对结果集的处理采用lamboda表达式,可根据不同情况设置不同的映射方法
- 采用策略模式&模板模式优化代码,抽取重复代码重构

## 性能

- 手写数据库连接池，提高访问数据库性能

  > 方法上加了同步锁,防止并发写问题

# 工具类

## 反射

- 反射工具类
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404204852899.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

- 将对象映射成属性和属性值

> **usage**:主要用在根据传进来的对象填充sql
> **advantage**:只添加不为null的值,故使得填充sql具有通用性,只需要生产不同的业务对象即可(bean)
> **notice**:会将属性值转化为数据库对应列名(手写StringUtils)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404205539757.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404222515599.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/2021040422400561.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70#pic_center)


## BeanFactory&&枚举类

- utils包
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404204020866.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
- 配置文件![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404204052399.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
- 调用方法	![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404204039132.png)

> **advantage**:方便了各种实现类的生产,符合工厂模式的设计思想,需要什么产品只需要从工厂中拿,新增商品只需要修改配置文件添加商品,符合开闭原则
>
> **disadvantage**:若项目过大,有过多业务和Dao需要实现,则每新增一个业务,都需要修改配置文件,新增枚举量

## JdbcUtils

![在这里插入图片描述](https://img-blog.csdnimg.cn/2021040422460484.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)![在这里插入图片描述](https://img-blog.csdnimg.cn/2021040422461381.png)

 **usage:**将对象映射成属性和方法时用到,提取属性的时候调用转化为数据库字段名存在fieldNames链表中![image-20210412160619537](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210412160619537.png)

**usage:** 将结果集映射为对象时用到,根据列名,将列名转化为属性名后提取set方法来映射对象![image-20210412160630957](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210412160630957.png)

**usage:**点赞与获取最大id后+1

![image-20210412160643590](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210412160643590.png)

## Md5Utils

**usage:对数据库中密码加密,增强安全性![在这里插入图片描述](https://img-blog.csdnimg.cn/20210404224714753.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)

# 视图

## JTree

- 实现知识库,笔记分组,笔记多级嵌套

  > 同时满足设置修改知识库,笔记分组,笔记功能,并能直接在该界面选中笔记分组新增笔记

## JTable

- 实现笔记查看与搜索,点赞,功能

  >  表格可以根据用户需求实现不同排序方式,选中表格行还会展示用户和笔记分组信息
  >
  > 搜索可根据笔记标题(模糊查询)或作者id
  >
  > 查看笔记文本内容会根据字数进行分页展示,并显示全文字数

- 管理员展示设置用户信息(可以进行列入黑名单操作),查看用户笔记详情信息

  > 