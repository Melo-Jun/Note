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

showNoteTitle(要根据权限的,后来发现只需要列出所有即可)

```java
/**
 * 根据xxx列出笔记标题
 * @param obj 根据的对象
 * @notice 无根据时则传null
 * @return
 */
@Override
public LinkedList<Object> showNoteTitle(Object obj) {
    StringBuilder sql = new StringBuilder( "select title from "+TABLE_NAME+" where access=? ");
    /**
     * 将对象映射成属性和值(属性会映射为数据库字段名)
     */
    LinkedList<Object> fieldNames = new LinkedList<>();
    LinkedList<Object> fieldValues = new LinkedList<>();
    fieldMapper(obj,fieldNames,fieldValues);
    /**
     * 将字段名填入sql语句
     * 不等于1说明还有其他根据条件,则动态增加条件
     */
    if(fieldValues.size()!=1){
        sql.append(" AND "+fieldNames.getLast()+" ="+"?");
    }
    /**
     * 完成sql注入和执行
     */
    return queryList(sql.toString(),obj);
}
```

# 疑问

已解决: 父类方法和属性是最晚获取到的

- 这样方便了,但是可扩展性不强了(只能根据folderId),还是说各个Dao里边没关系

  > 可在service中进行对象封装的其实

  ![image-20210411110944294](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210411110944294.png)

# 经验

- 如果sql语句是在Base里边写的，就没有办法通过TableName来解决，得根据传进来的对象去获取表名，就不能用业务对象了，所以业务对象就用在select而已吗,这种情况除了sql在各自类中写还有什么解决方法吗

- **最终:前台传最直接的元素过来,service获取id,将id传给Dao,Dao再构造对象传给BaseDao**

    >   要在service去构造对象还是Dao里边构造对象呢,Dao里边吧?
    >
    >   几乎都在service中构造对象,除了fillTable需要一些业务对象的没办法避免

- 解决资源关闭问题,return放在try里边就可以了阿,做完map再return![image-20210406202817792](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406202817792.png)

- 仍然会rs.next,还得再次判空![image-20210411171519048](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210411171519048.png)

    

# 优化

- 展示笔记内容分页,以及显示字数

- 修改笔记封装和增加笔记封装![image-20210411112338118](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210411112338118.png)

- ![image-20210406164454733](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406164454733.png)

- 泛型没优化

    > service中就加泛型,Dao中就不加?因为Dao得return Base的,不能泛型
    >
    > 可写个toxxx,在Dao中就泛型,先获取Base,再return toxxx之后的结果(小麻烦)

- select语句到底要不要动态拼接,还是说要分成不同职能的函数,在特定Dao里边不同职能,只是Base通用即可?

    ![image-20210411105009044](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210411105009044.png)

- 写判空不执行按钮![image-20210410153556119](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210410153556119.png)

- 写个常量类

    > ![image-20210408185551990](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210408185551990.png)

- 前台构造对象最好放在service中传给Dao?或者在Dao里边再构造也可以,setId是在service中

    >   要是多个service都共用一个Dao的话,就在Dao里边构造对象吧

    ![image-20210406205107573](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406205107573.png)

- 传class可以各个Dao里边先定义好常量

- 可以写判断输入是否有效的父类,可变参数其实是数组,用for each

- 此处的生成对象有没有必要封装成service,**都弄成service中即可吧**![image-20210406093435527](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406093435527.png)

- 都还未进行判空验证

- 选择笔记分组时加个提示当前选中的是?

# 待办

- personalservice中的isValid还没改成状态码

- 设置默认笔记分组和知识库不可修改

    > 设置笔记不能修改名称,只调到那个页面即可

- addNoteView可删掉设置分组了,以及UserView中的增加笔记功能可删除

- 修改其他表id为string,**只修改了User**

    > ~~其他应该不用改了~~

    > ![image-20210410084703581](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210410084703581.png)

- ~~写根据id和folderid列出相关名称~~

    >![image-20210407183049724](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210407183049724.png)

    

- ~~修改了id没有自增了,以及likecount为string了~~

    >   likeCount:将getMaxId中的方法弄到StringUtils中即可

-   ~~此处注释掉的应该不用用到吧~~![image-20210406205640002](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406205640002.png)

# BaseDao

## update

- 修改update![image-20210408124219910](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210408124219910.png)

## select

- **queryAll**

  ```java
  /**
   * 查找所有属性
   * @param sql 查询语句
   * @param obj 用以填充的语句
   * @return LinkedList<Object> values 所有值
   */
  @Override
  public LinkedList queryAll(String sql, Object obj,Class clazz) {
      return (LinkedList) executeQuery(obj,sql,rs -> {
          LinkedList<Object> values = new LinkedList<>();
          ResultSetMetaData rsmd = null;
          try {
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
          } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException throwables) {
              throw new DaoException("映射对象出现异常");
          }
          return values;
  });
  ```

- **executeQuery**

  ```java
  /**
   * 执行一条查询语句,并对结果集进行封装
   *
   * @param obj          对象
   * @param sql          sql语句
   * @param resultMapper 实现不同功能映射的实现类
   * @return 映射结果
   */
  @Override
  public Object executeQuery(Object obj, String sql, ResultMapper resultMapper) {
      Connection conn = getConnection();
      ResultSet rs = null;
      PreparedStatement ps = null;
      try {
          ps = conn.prepareStatement(sql);
          //根据obj注入Sql填充参数
          if (obj != null) {
              setParams(ps, obj);
          }
          rs = ps.executeQuery();
          return resultMapper.doMap(rs);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
          throw new DaoException("预编译查询语句异常：");
      } finally {
          freeConnection(conn);
          close(ps, rs);
      }
  }
  ```

## getId和delete(类似)

> ![在这里插入图片描述](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210412152857130.png)
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
  > new:
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

-   showNoteTitle好像不用用到了

# FolderDao

- showNoteAll

  > 目前只能展示公开的,但是用户要查找的话,是不是access那块要改一下先

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

## 分工

-   设置笔记分组的时候,顺便可以查看自己的笔记,修改自己的笔记,在笔记中去设置笔记分组(仅仅是设置笔记分组而已,修改还是在查看笔记那边笔记方便.因为要根据id去获取text,如果在folderView中可能标题重名就GG了)

    >   **还是得跳出一个类似新增笔记的界面,因为有treeView笔记方便来设置笔记分组,就设置可编辑咯,可以在表格加一个按钮,查看自己的笔记并设置**

-   管理员和查看别人的都用表格,修改用户信息也用表格,查看自己个人信息也用表格?

## TableView

- 列出所有笔记

  old:![image-20210407183405940](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210407183405940.png)

  ```java
  private void fillTable(Object obj){
      //记录列数
      int count=0;
      model= (DefaultTableModel)table.getModel();
      model.setRowCount(0);
      LinkedList<Note> notes = new NoteDaoImpl().showNoteAll(obj);
      for(Note tempNote:notes) {
          Vector<Object> objects = new Vector<>();
          /**
           * 获取对象属性值
           */
          LinkedList<Object> fieldNames = new LinkedList<>();
          LinkedList<Object> fieldValues = new LinkedList<>();
          listNoteTitleService.fieldMapper(tempNote, fieldNames, fieldValues);
          for (Object value : fieldValues) {
              count++;
              /**
               * 过滤掉text内容,另外单独展示
               */
              if (count != 4) {
                  //objects.add(value);
              }
          }
  ```

  new:封装在service中

  ![image-20210408162736381](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210408162736381.png)

 ## FolderView

- 还得增加一个删除笔记的功能,以及此处是根据标题,若改为根据id,也还是会受影响,最好加个不可重名吧

  > ![image-20210408154053453](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210408154053453.png)

 - 记录选中的名称和类型,然后在service中判断要执行哪个方

 > 需要根据选中的名称生成对象,反获取id后将id传给相应Dao的delete操作,delete中又去生成对象再传给Base的delete
 >
 > 感觉好麻烦阿,总是得对象来对象去的![image-20210405110723894](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210405110723894.png)

## NoteTextView

- update

  1. 参数:字段名,属性值,id,表名
  2. ~~参数:或者都统一为对象,base里边,然后set ?=?(可以这样吗)不可以~~

  > 怎么操作,根据选中的行数获取他的:前作为字段名,属性值的话呢,感觉得switch一样,应该不同的选择会用到设置不同的set方法,或者都用append就好?根据是根据id,where后边尽量是id,去获取第一行即可,表名还是得传对象,如果是通用的Base都得传对象

- select

    1.  queryAll返回object链表,然后View层遍历链表将Object取出来后,将属性值加入到Vector里边(用fieldValues吧)

    2.  根据标题不太好,还是根据id吧,还是写成表格吧

    >   ![image-20210406151631927](C:\Users\Jun\AppData\Roaming\Typora\typora-user-images\image-20210406151631927.png)

## 管理员View

- 可以设置用户信息,同时拉黑用户,用户得加个字段,该用户是否有效的字段
- 可以查看笔记信息等,然后物理删除(也用表格实现)