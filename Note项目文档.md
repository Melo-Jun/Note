# 经验

- 如果sql语句是在Base里边写的，就没有办法通过TableName来解决，得根据传进来的对象去获取表名，就不能用业务对象了，所以业务对象就用在select而已吗,这种情况除了sql在各自类中写还有什么解决方法吗
- ?直接自己填充其实也可以的,看到有这样的,**最好不要**
- **最终:前台传最直接的元素过来,service获取id,将id传给Dao,Dao再构造对象传给BaseDao**

# 优化

- 得写判断输入是否有效的父类,可变参数其实是数组,用for each

# 待办

- 都还未进行判空验证

# BaseDao

## select
- queryAll用在查看笔记详情内容，也适用于其他查找所有的吧，obj是业务对象即可

> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210405082055678.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/202104050824063.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NDA1Nzgy,size_16,color_FFFFFF,t_70)
> 这两个好像可以合并，返回结果一样，查找单一和查找所有其实都可以用rsmd那个来实现？

## getId和delete(类似)

delete一定得根据主键吧,才不会误删

- 根据id的话,那还是传对象进来吗,传对象进来的话主要是getId得有一个属性,有id之后又有属性了,填充参数会不会顺序反了,试一下吧先,试一下是哪个先获取到

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

# FolderDao

- showFolderName应该还可以重构一下,生出Jtree那块

  

  > 此处的user只能有id,这样在前台创建会不会太麻烦了,或者封装到service里边
  >
  > 或者此处的select id其实可以用getId方法来实现
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

  > 发现id会比name晚获取到,是因为id在父类的原因

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
 - 记录选中的名称和类型,然后在service中判断要执行哪个方法

 > 需要根据选中的名称生成对象,反获取id后将id传给相应Dao的delete操作,delete中又去生成对象再传给Base的delete
 >
 > 感觉好麻烦阿,总是得对象来对象去的![image-20210405110723894](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405110723894.png)

## NoteTextView

- update

  1. 参数:字段名,属性值,id,表名
  2. 参数:或者都统一为对象,base里边,然后set ?=?(可以这样吗)

  > 怎么操作,根据选中的行数获取他的:前作为字段名,属性值的话呢,感觉得switch一样,应该不同的选择会用到设置不同的set方法,或者都用append就好?根据是根据id,where后边尽量是id,去获取第一行即可,表名还是得传对象,如果是通用的Base都得传对象