# 备份

queryAll

```java
LinkedList<Object> values = new LinkedList<>();
Connection conn=getConnection();
PreparedStatement ps=null;
ResultSetMetaData rsmd = null;
ResultSet rs=null;
try {
    ps=conn.prepareStatement(sql);
    setParams(ps,obj);
    rs=ps.executeQuery();
    rsmd = rs.getMetaData();
    LinkedList<Method> methods = getMethods(clazz.newInstance());
    //存储set方法
    LinkedList<Method> setMethods = new LinkedList<>();
    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        /**
         * 将列名转化为实体类属性名
         */
        String columnName = rsmd.getColumnName(i);
        String fieldName = toEntityField(columnName);
        /**
         * 获取与列名有关的set方法,且会一一对应保证顺序
         */
        for (Method method : methods) {
            if (method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(fieldName)) {
                setMethods.add(method);
            }
        }
    }
    /**
     * 调用invoke执行set方法,映射成对象加入链表中
     */
    while (rs.next()) {
        Object newInstance = clazz.newInstance();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            setMethods.get(i - 1).invoke(newInstance, rs.getObject(i));
        }
        values.add(newInstance);
    }
} catch (SQLException | InstantiationException | IllegalAccessException throwables) {
    throwables.printStackTrace();
} catch (InvocationTargetException e) {
    e.printStackTrace();
}
return values;
```

# 疑问

-   父类的方法是最早获取到的??那那个是怎么实现的,access那个![image-20210406165842766](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406165842766.png)

# 经验

- 如果sql语句是在Base里边写的，就没有办法通过TableName来解决，得根据传进来的对象去获取表名，就不能用业务对象了，所以业务对象就用在select而已吗,这种情况除了sql在各自类中写还有什么解决方法吗

- **最终:前台传最直接的元素过来,service获取id,将id传给Dao,Dao再构造对象传给BaseDao**

    >   要在service去构造对象还是Dao里边构造对象呢,Dao里边吧

- 解决资源关闭问题,return放在try里边就可以了阿,做完map再return![image-20210406202817792](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406202817792.png)

# 优化

- 前台构造对象最好放在service中传给Dao?或者在Dao里边再构造也可以,setId是在service中

    >   要是多个service都共用一个Dao的话,就在Dao里边构造对象吧

    ![image-20210406205107573](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406205107573.png)

- 传class可以各个Dao里边先定义好常量

- 可以写判断输入是否有效的父类,可变参数其实是数组,用for each

- 此处的生成对象有没有必要封装成service![image-20210406093435527](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406093435527.png)

- 此处点击author_id可以查看用户详情信息会好一点,等完成设置用户信息了再

    点击点赞like_count会++,然后不可再点赞了,下次也不可以了得设置一个来源,那会把那个也查出来

    用配置文件优化,显示出来是中文?

    ![image-20210406144733124](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406144733124.png)
    
    -   此处将信息封装还待优化
    
    ![image-20210406164454733](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406164454733.png)

- 都还未进行判空验证

- 选择笔记分组时加个提示当前选中的是?

# 待办

- executeQuery封装起来,还是不封装了,全部都写成queryAll?然后各个Dao再去进行处理掉了
- 修改了id没有自增了,以及likecount为string了
- 展示笔记详情内容,在表格中实现
- 将结果集的对象再变成vector
- ![image-20210406183002226](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406183002226.png)


-   此处注释掉的应该不用用到吧![image-20210406205640002](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406205640002.png)

# BaseDao

## select
- queryAll用在查看笔记详情内容，也适用于其他查找所有的吧，obj是业务对象即可

> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210405082055678.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/202104050824063.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> 这两个好像可以合并，返回结果一样，查找单一和查找所有其实都可以用rsmd那个来实现？

### 封装

---

#### 思路

先遍历一次列名,将列名转化为属性名?然后就取出所有方法看setxxx中的xxx跟属性名一不一样

## getId和delete(类似)

delete一定得根据主键吧,才不会误删

- ~~根据id的话,那还是传对象进来吗,传对象进来的话主要是getId得有一个属性,有id之后又有属性了,填充参数会不会顺序反了,试一下吧先,试一下是哪个先获取到~~

  > id会比较晚获取到,因为是在父类

> 不行,属性值的话会多于?

> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210405091855726.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210405091922857.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> 都需要去取出属性名和属性值然后作为根据条件和根据值
- 打算修改delete,不传对象进来了,直接传id和类

  > old: 因为根据的字段不一样,所以得动态构造对象
  >
  > ```java
  > /**
  >  * 删除记录
  >  *
  >  * @param obj 与删除有关的对象
  >  * @return int 影响的数据库记录数
  >  */
  > @Override
  > public int delete(Object obj) {
  >     /**
  >      * 根据删除依据的字段名构造对象，取出对应数据库字段名和值
  >      */
  >     LinkedList<Object> fieldNames = new LinkedList<>();
  >     LinkedList<Object> fieldValues = new LinkedList<>();
  >     fieldMapper(obj,fieldNames,fieldValues);
  >     StringBuilder sql = new StringBuilder("delete from " + getTableName(obj) + " where id =?");
  >     return executeUpdate(obj,sql.toString());
  > }	
  > ```

  > 
  >
  > new:打算直接传id和类来获取表名,但是sql注入问题又..,所以还是obj吧,
  >
  > **最终:前台传最直接的元素过来,service获取id,将id传给Dao,Dao再构造对象传给BaseDao**
  >
  > ```java
  > /**
  >  * 删除记录
  >  *
  >  * @param obj 与删除有关的对象
  >  * @return int 影响的数据库记录数
  >  */
  > @Override
  > public int delete(Object obj) {
  >     String sql = "delete from " + getTableName(obj) + " where id =?";
  >     return executeUpdate(obj,sql);
  > }
  >  	/**
  >      * 根据知识库id删除知识库
  >      *
  >      * @param folderId 知识库id
  >      * @return int 影响的行数
  >      */
  >     @Override
  >     public int deleteFolder(String folderId) {
  >         Folder folder = new Folder();
  >         folder.setId(folderId);
  >         return delete(folder);
  >     }
  > 	/**
  >      * 根据传入类名删除对应类对象
  >      *
  >      * @param selectedName      对象名称
  >      * @param selectedType 对应类型
  >      * @return int 影响的行数
  >      */
  >     @Override
  >     public int delete(String selectedName, String selectedType) {
  >         /**
  >          * 没有正常选择要删除的节点
  >          */
  >         if(selectedClassName.isEmpty()||selectedName.isEmpty()){
  >             return 0;
  >         }
  >         if(selectedClassName.equals(FOLDER)){
  >             Folder folder = new Folder();
  >             folder.setFolderName(selectedName);
  >             String folderId =getId(folder);
  >             return folderDao.deleteFolder(folderId);
  >         }
  > ```

# NoteDao

-   ~~待修改~~![image-20210406193605499](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406193605499.png)

# FolderDao

- showFolderName应该还可以重构一下,生出Jtree那块

  

  > 此处的user只能有id,这样在前台创建会不会太麻烦了,或者封装到service里边
  >
  > 或者此处的select id其实可以用getId方法来实现
  >
  > 
  >
  > ![](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405163734276.png)

# FolderService

## delete

- 还可以再封装起来,留到最后吧

  >![image-20210405145540524](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405145540524.png)
  >
  >新增知识库和笔记分组(待封装)感觉getId好麻烦,总是得新开对象,很多都得新开对象
  >
  >![image-20210405170124937](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405170124937.png)

## update

- 前台传选中的名字,和要更新为的名字,先用oldName获取id,再用newName覆盖掉之前的name同时设置id

  > 发现id会比name晚获取到,是因为id在父类的原因?

  ####  problem :只能修改Name,或者Dao里边特定功能特定函数即可,Base都是传对象

![image-20210405151111806](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405151111806.png)

```java
/**
 * 更新记录
 * @param obj 与更新有关的对象
 * @return int 影响的数据库记录数
 */
@Override
public int update(Object obj) {
    /**
     * 根据更新依据的字段名构造对象，取出对应数据库字段名和值
     * 此处id是在父类里边,故fieldNames.getFirst不是id
     */
    LinkedList<Object> fieldNames = new LinkedList<>();
    LinkedList<Object> fieldValues = new LinkedList<>();
    fieldMapper(obj,fieldNames,fieldValues);
    StringBuilder sql = new StringBuilder
        ("update "+getTableName(obj)+" set "+fieldNames.getFirst()+" =?" +" where id=?");
    System.out.println(sql.toString());
    return executeUpdate(obj,sql.toString());
}
```

 # View

 ## FolderView
 - 生成知识库,笔记分组,笔记,前两个都用到了queryMap

   ---

 - 记录选中的名称和类型,然后在service中判断要执行哪个方法

 > 需要根据选中的名称生成对象,反获取id后将id传给相应Dao的delete操作,delete中又去生成对象再传给Base的delete
 >
 > 感觉好麻烦阿,总是得对象来对象去的![image-20210405110723894](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405110723894.png)

## NoteTextView

- update

  1. 参数:字段名,属性值,id,表名
  2. 参数:或者都统一为对象,base里边,然后set ?=?(可以这样吗)

  > 怎么操作,根据选中的行数获取他的:前作为字段名,属性值的话呢,感觉得switch一样,应该不同的选择会用到设置不同的set方法,或者都用append就好?根据是根据id,where后边尽量是id,去获取第一行即可,表名还是得传对象,如果是通用的Base都得传对象

- select

    1.  queryAll返回object链表,然后View层遍历链表将Object取出来后,将属性值加入到Vector里边(用fieldValues吧)

    2.  根据标题不太好,还是根据id吧,还是写成表格吧

    >   ![image-20210406151631927](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406151631927.png)

## 管理员View

- 可以设置用户信息,同时拉黑用户,用户得加个字段,该用户是否有效的字段
- 可以查看笔记信息等,然后物理删除(也用表格实现)