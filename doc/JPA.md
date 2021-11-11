# JPA框架



## BO,VO,DTO,PO,POJO的区别

**BO：**business object 业务对象

>业务对象主要作用是把业务逻辑封装为一个对象。这个对象可以包括一个或多个其它的对象。
>
>比如一个简历，有教育经历、工作经历、社会关系等等。我们可以把教育经历对应一个PO，工作经历对应一个PO，社会关系对应一个PO。
>
>建立一个对应简历的BO对象处理简历，每个BO包含这些PO。
>
>这样处理业务逻辑时，我们就可以针对BO去处理。
>
>封装业务逻辑为一个对象（可以包括多个PO，通常需要将BO转化成PO，才能进行数据的持久化，反之，从DB中得到的PO，需要转化成BO才能在业务层使用）。

关于BO主要有三种概念

1. 只包含业务对象的属性；

2. 只包含业务方法；

3. 两者都包含。

**一般来说，业务对象是业务层进行使用，最后返回给controller层的对象。**

在实际使用中，认为哪一种概念正确并不重要，关键是实际应用中适合自己项目的需要。

****



**VO：**value object 值对象 / view object 表现层对象

1 ．主要对应页面显示（web页面/swt、swing界面）的数据对象。

2 ．可以和表对应，也可以不，这根据业务的需要。

**VO对象一般是我们返回给前端的对象，当然也是会进行JSON化的。VO对象的字段一般是比DAO对象的字段少**

****

**DTO（TO）：**Data Transfer Object 数据传输对象

1. 用在需要跨进程或远程传输时，它不应该包含业务逻辑。

2. 比如一张表有100个字段，那么对应的PO就有100个属性（大多数情况下，DTO内的数据来自多个表）。但view层只需显示10个字段，没有必要把整个PO对象传递到client，这时我们就可以用只有这10个属性的DTO来传输数据到client，这样也不会暴露server端表结构。到达客户端以后，如果用这个对象来对应界面显示，那此时它的身份就转为VO。



****

**PO：**persistent object 持久对象

1. 有时也被称为Data对象，对应数据库中的entity，可以简单认为一个PO对应数据库中的一条记录。

2. 在hibernate持久化框架中与insert/delet操作密切相关。

3. PO中不应该包含任何对数据库的操作。

****

**POJO ：**plain ordinary java object 无规则简单java对象

一个中间对象，可以转化为PO、DTO、VO。

1. POJO持久化之后==〉PO

（在运行期，由Hibernate中的cglib动态把POJO转换为PO，PO相对于POJO会增加一些用来管理数据库entity状态的属性和方法。PO对于programmer来说完全透明，由于是运行期生成PO，所以可以支持增量编译，增量调试。）

2. POJO传输过程中==〉DTO

3. POJO用作表示层==〉VO

**PO 和VO都应该属于它。**

****

**DAO：**data access object 数据访问对象

1 ．主要用来封装对DB的访问（CRUD操作）。

2 ．通过接收Business层的数据，把POJO持久化为PO。



****

## 常用注解



### 实体类注解



#### 实体注解 @Entity

使用 `@Entity`注解标识在一个模型类（也可以说是实体类bean）上，表明当前类是一个数据库模型类了。



#### 主键 @Id

每个模型类都需要一个主键字段，使用`@Id`注解标识该注解字段。



#### @Column 限制长度

该注解标记的属性，可以限制在数据库中生成表字段的长度。

```java
@Column(length = 16)
private String name;
```



#### @Table 标识表名

如果我们想要让生成的表的表名不是我们的类型，我们可以使用该注解进行指定表名。需要指定表中字段的名称我们也可以通过@Id，@Column注解等的name属性进行指定。让我们模型类的属性名和生成的字段名不一致。

```java
package com.mao.sleeve.model_bak;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Banner
 * @Description: banner模型类
 * @Author 毛毛
 * @CreateDate 2021/10/29/周五 15:01
 * @Version: v1.0
 */
@Entity
@Table(name = "banner")
public class Banner {
    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * @Column(length = 16) 限制长度不超过16
     */
    @Column(length = 16, name = "name")
    private String name;
    /**
     * 描述
     */

    private String description;
    private String img;
    private String title;
}

```



#### @Transient 忽略属性

使用该注解标记的属性，在创建表的时候，会忽略该属性，不和表的字段进行对应。也就是说模型类中有该属性，但是生成的表中不会有该字段。





#### JSON序列化 @JsonIgnore 忽略该属性

在进行json序列化返回给前端数据的时候，如果某些属性我们不想返回给前端，那么在序列化的时候，我们需要使用该注解@JsonIgnore标记在不被序列化的属性上。

```java
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * 三个基础字段 且这三个字段 不应该返回给前端 也就是不应该序列化
     *
     */
    @JsonIgnore
    protected Date createTime;
    @JsonIgnore
    protected Date updateTime;
    @JsonIgnore
    protected Date deleteTime;
}
```







#### @OneToMany 一对多

**使用该注解标记的属性，也叫做导航属性。**通过当前所在的模型类可以找到这个关联的多方的数据。



该注解用来生成一对多的关系。一个banner对应多个bannerItem。表示两个表之间的关系是一对多。当前所在模型是一，标记的BannerItem类型是多方。

**默认情况下，使用了该注解以后，会生成三个表。其中的banner和banner_item表是模型类对应的表模型，还有一张表名称是当前所在模型类对应的名称加上我们的一对多的该属性的名称结合，生成的新表banner_banner_items。默认情况下，这个表有两个属性，是其他两张表的主键构成的。**

```java
@Entity
@Table(name = "banner_item")
public class BannerItem {
    @Id
    private Long id;
    private String name;
    private String img;
    /**
     * 关键字 跳转到spu的时候，需要携带spu的id 跳转到专题的时候，需要携带的是专题的标识
     */
    private String keyword;
    /**
     * TODO 点击这个广告，应该跳转到spu商品页面，还是专题页面，主题页面等
     */
    private Short type;

    private Long banner_id;
}
```



```java
@OneToMany
private List<BannerItem> bannerItems;
```

如果想要消除第三张表的产生：我们需要在BannerItem实体类中，建立和banner表的联系。

之所以生成第三张表，是因为我们jpa不知道banner和BannerItem之间的联系和关系。所以我们需要在BannerItem中添加一个外键属性，表明当前的BannerItem是属于那个Banner的。

这样就不会有第三张表的出现了。



#### @JoinColumn 指定关联的外键

该注解指定当前一对多的关系中，当前表的主键去作为多方的表的外键。

```java
@OneToMany
@JoinColumn(name = "bannerId")
private List<BannerItem> bannerItems;
```

```java
@Entity
@Table(name = "banner_item")
public class BannerItem {
    @Id
    private Long id;
    private String name;
    private String img;
    /**
     * 关键字 跳转到spu的时候，需要携带spu的id 跳转到专题的时候，需要携带的是专题的标识
     */
    private String keyword;
    /**
     * TODO 点击这个广告，应该跳转到spu商品页面，还是专题页面，主题页面等
     */
    private Short type;
    /**
     * 外键 属于那个banner
     */
    private Long bannerId;
}
```

**当我们使用该注解以后，就会生成数据库中两张表之间的物理外键。这种情况下，我们应该当被关联表先创建出来。所以我们可以在模型类上加上注解@Proxy**



#### @Proxy

实现模型类创建出表的时候，是懒创建还是直接创建。**注意：这个注解是hibernate提供的。**

```java
@Table
@Proxy(lazy = false)
```





#### @GeneratedValue 生成策略

如果我们想要让主键自增，主键唯一等，可以使用该注解标注在主键上。

```java
@Id
// strategy 策略
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

`strategy = GenerationType.AUTO`这个不是主键自增，而是将表交给JPA管理，会生成新的表来管理数据。

`strategy = GenerationType.IDENTITY`表示主键自增。



#### @MappedSuperclass 映射的基类

当我们在一个类上标注了该注解以后，表明当前类是一个可以被模型类继承的基类。会拥有当前类的属性，且可以映射到数据库表中。

使用该注解标记一个基类，是为了把某些公共的属性抽取出来，但不想让当前类成为一个模型类。



```java
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * 三个基础字段 且这三个字段 不应该返回给前端 也就是不应该序列化
     */
    protected Date createTime;
    protected Date updateTime;
    protected Date deleteTime;
}
```







### JPA操作读写数据
定义接口（仓储模式）继承JpaRepository接口，实现数据库的读写操作

```java
package com.mao.sleeve.repository.banner;

import com.mao.sleeve.model_bak.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: BannerRepository
 * @Description: Repository 仓储模式 使用jpa操作数据库，读写
 * JpaRepository<Banner,Long>
 * 泛型一： 是我们操作的表对应的模型的类型
 * 泛型二： 是操作表的主键的类型
 * @Author 毛毛
 * @CreateDate 2021/10/29/周五 23:54
 * @Version: v1.0
 */
public interface BannerRepository extends JpaRepository<Banner, Long> {
    /**
     * 根据id查询相应的banner
     *
     * @param id
     * @return
     */
    Banner findOneById(Long id);

    /**
     * 根据name查询相应的banner
     *
     * @param name
     * @return
     */
    Banner findOneByName(String name);
}

```





#### 懒加载

默认情况下，JPA查询某张表中的数据的时候，如果有某个属性是关联其他表的字段，JPA并不会直接把关联的数据一起查询出，只有在我们用到了关联数据的时候才会进行查询，这就是JPA的懒加载。

如果我们想实现一次性加载完所有数据，其实也很简单。

```java
@OneToMany(fetch = FetchType.EAGER)
```



#### 双向一对多配置

**双向一对一（@OneToOne），类似双向一对多，mappedBy设置在关系被维护端，外键由关系维护端维护；**

我们可以在模型类banner中配置当前banner所存在的所有子项bannerItems，那么肯定也可以在bannerItem模型类中配置当前子项所属的banner。在banner中是一对多个item，那么在item中就是多个item对应一个banner。

**维护双向一对多的关系中，**我们也叫做关系维护端和关系被维护端。

关系维护端：我们把多方的那一端叫做维护端。

关系被维护端：一方

**在双向一对多的模型中，我们使用的@JoinColumn注解是打在多端维护关系的属性上面的。**

在一方的那个维护的属性上，是不需要使用该属性了。

然后还需要在一方（这里的banner）模型类中的维护多方关系的导航属性上使用的注解**`@OneToMany`**里面设置一个属性mappedBy的属性值为多方模型中维护关系的导航属性的属性名。

**Many端，为关系维护端，负责外键的更新（删除时候注意先删除Many端再删除One端）；**
**One端，为关系被维护端，不能更新外键；**

```java
@Entity
@Table(name = "banner")
@Proxy(lazy = false)
@Getter
public class Banner {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * @Column(length = 16) 限制长度不超过16
     */
    @Column(length = 16, name = "name")
    private String name;
    /**
     * 描述
     *
     * @Transient: 该注解实现 标注的属性不在表中体现
     */
    @Transient
    private String description;
    /**
     * 广告板块首图
     */
    private String img;
    private String title;
    /**
     * 一个banner拥有多个item，一对多
     * mappedBy = "banner" 该属性的值就是多端维护关系的属性的属性名，也就是导航属性名
     */
    @OneToMany(mappedBy = "banner")
    //@JoinColumn(name = "banner_id")
    private List<BannerItem> bannerItems;
}
```

```java
@Entity
@Table(name = "banner_item")
public class BannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    /**
     * 关键字 跳转到spu的时候，需要携带spu的id 跳转到专题的时候，需要携带的是专题的标识
     */
    private String keyword;
    /**
     * TODO 点击这个广告，应该跳转到spu商品页面，还是专题页面，主题页面等
     */
    private Short type;
    /**
     * 外键 属于那个banner jpa自动生成，不能显示的写出来了
     */
    // private Long bannerId;
    /**
     * 当前banner子项属于的banner
     */
    @ManyToOne
    // @JoinColumn(name = "banner_id") jpa会维护该外键字段
    private Banner banner;
}

```

**或者：**

```java
@Entity
@Table(name = "banner_item")
public class BannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    /**
     * 关键字 跳转到spu的时候，需要携带spu的id 跳转到专题的时候，需要携带的是专题的标识
     */
    private String keyword;
    /**
     * TODO 点击这个广告，应该跳转到spu商品页面，还是专题页面，主题页面等
     */
    private Short type;
    /**
     * 外键 属于那个banner
     * TODO JPA会自动帮我们创建，不需要显示手写，也不能定义
     * 如果想要显示的定义该属性，需要在下面的导航属性上的@joinColumn注解上配置一些属性
     */
    private Long bannerId;
    /**
     * 当前banner子项属于的banner
     */
    @ManyToOne
    @JoinColumn(name = "banner_id", insertable = false,updatable = false)
    private Banner banner;
}
```



### 多对多@ManyToMany

#### 单向多对多

表之间多对多的关系，其实是双向的。一般都需要三种表，需要中间表来维护两张表之间的关系的。

但是很多时候，我们在模型类中只需要体现出单向的多对多即可，有时候不需要另一方也使用导航属性来关联。



#### 多对多生成第三张表的表名

默认情况下，我们生成的一对多或者是多对多的第三张中间表的名称是当前模型类对应的表名和关联的导航属性的名称的结合。如果我们想指定生成的中间表的表名，可以使用 **@JoinTable ** 注解来显示的指定。还可以通过该注解的`joinColumns`属性来指定中间表中关联的两个外键的名称

```java
@Entity
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String name;
    @ManyToMany
    @JoinTable(name = "theme_spu",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "spu_id"))
    private List<Spu> spuList;
}
```



双向多对多（@ManyToMany），注意关系维护端的定义；所有关系定义都是在关系维护端来定义的；
**@JoinTable(name="关联表名称", inverseJoinColumns=@JoinColumn(name="被维护端外键"),**

**joinColumns=@JoinColumn(name="维护端外键"))**


注意，在关系维护端对被维护端的集合进行增加、删除操作时候，需要重载hashCode与equals方法，通过ID来判

断是否相同，而不是内存引用地址；
对关系被维护端进行级联删除时候，需要先解除关系，然后删除；



#### JPA 级联更新去孤子(了解)

> 在是使用JPA(hibernate)配置实体关系中，经常会遇到通过父实体对象级联批量更新子对象的需求，而且每次更新必须采用合并方式，即原来的所有子删除，只保留本次保存的所有子实体对象。
>
> 例子：
>
> A为父对象，持有一个B的集合，A与B的关系为oneToMany. 
>
> 第一次保存：设置A下的B集合成员为：1,2,3, 保存A后，B对象对应的数据表为:1,2,3
>
> 第二次保存：设置A下的B集合成员为：2,3,4, 保存A后，B对象对应的数据表为:2,3,4 (自动删除无效数据1)
>
> 这个就是自动去孤子功能。

```java
class A{  
       private Set<B> bs = new HashSet<B>();  
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flowNode", orphanRemoval = true)  
    @OrderBy(clause = "id")  
    public Set<B> getBs() {  
        return bs;  
    }  
} 
```









## 外键

### 物理外键和逻辑外键

我们使用JPA框架的时候，通过模型类创建数据库表，相关联的字段都会生成物理外键。实际上我们应该不使用或者少使用物理外键在数据库表中。应该是在模型类中体现这些逻辑外键，在数据库中不建立物理外键。

使用hibernate的一个被废弃的注解可以实现不生成物理外键

```java
/**
* 关系被维护端
* 忽略物理外键
*/
@ManyToMany(mappedBy = "spuList")
@org.hibernate.annotations.ForeignKey(name = "null")
private List<Theme> themeList;
```

也可以使用@JoinColumn注解中新增的属性foreign属性来禁止生成物理外键

```java
@Entity
@Table(name = "spu")
public class Spu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 主标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 关系被维护端
     * 忽略物理外键
     */
    @ManyToMany(mappedBy = "spuList")
    //@org.hibernate.annotations.ForeignKey(name = "null")
    // 上面是被废弃的方式，下面的方式是新增的，也是用来禁止生成物理外键的，设置模式是无限制
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Theme> themeList;
}
```



## JPA常用功能

### JPA实现分页

我们实现分页，不需要像使用`mybatis`一样，需要使用分页插件`pageHelper`。在`spring data`里面内置的有分页的实现。

需要使用`PageRequest`类的`of`方法。

**返回给控制层的对象是Page**

```java
public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size) {
        // TODO  数据分页 且倒序排列
        // jpa操作的是模型类，所以我们的字段也要写出属性名的那种形式
        PageRequest page = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        // findAll 该方法是jpa默认提供的
        return spuRepository.findAll(page);
    }
```



