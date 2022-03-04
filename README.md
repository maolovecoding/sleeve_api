# sleeve项目后台接口API开发

开闭原则（OCP），里氏替换原则，迪米特法则
**开闭原则：** 软件，函数，类等对扩展是开放的，修改是封闭的。

面向抽象编程：interface abstract 接口，抽象类
面向对象三大特性：封装，继承，多态。

### IOC DI 过程
**阶段一：** 接口interface
**阶段二：** 设计模式：工厂模式的出现

**阶段三：** IOC DI 的出现

出现的目的表面上都是面向抽象编程 --》OCP开闭原则。实质性的目的还是为了**实现可维护的代码**。



### 重点理论

1. 单纯的interface可以统一方法的调用，但是它不能统一对象的实例化。可以完成**统一方法的调用**。
2. 面向对象 实例化对象 调用方法（完成业务逻辑）。
3. **只有一段代码中没有new对象的出现，才能保持代码的相对稳定，才能逐步实现OCP。**
4. 上面的这句话只是表象，实质就是一段代码如果想要保持稳定，就不应该负责对象的实例化工作
5. 对象的实例化是不可能消除的，所以我们需要把对象的实例化过程，转移到其他的代码片段中。
6. 代码中总是会存在不稳定，隔离这些不稳定，保证其他的代码是稳定的。
7. 做出来的软件是经常变化的，所以代码也是存在变化的。变化造成了不稳定。
8. 将所有的变化隔离在一个区域，就是IOC容器。**泛型通配符?,实际上是我们传递的参数的实参类型，和String，Integer 等一样。可以理解为所有类的父类。这个通配符就和Object类似，都是所有的类的父类，但是不具备Object类的方法。**
9. 工厂模式 + 反射 不是IOC和DI。没有用到这个IOC和DI 的原理。
10. 变化是不可能消除的，配置文件的变化，**也就是说改动配置文件不违反OCP开闭原则**，配置文件就可以理解为用户需求的变化，用户玩游戏选中的英雄是不同的，这就是带来的变化。这些都是外部的不稳定。



计算机里面的代码，其实是现实世界的规律，业务的映射，投影。



#### 为什么有了工厂+反射还需要IOC和DI





#### IOC，DI，DIP

1. ioc，控制反转。
2. DI 依赖注入
3. DIP 依赖倒置（Dependency Inversion Principle）。
   1. 高层模块不应该依赖底层模块，两个都应该依赖抽象
   2. 抽象不应该依赖细节
   3. 细节应该依赖抽象



### 模式注解 Stereotype Annotation

1. @Component  类的实例化
2. @Service
3. @Controller
4. @Repository
5. @Configuration

从**Spring3.0**，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。

**注意**：@Configuration注解的配置类有如下要求：

1. @Configuration不可以是final类型；
2. @Configuration不可以是匿名类；
3. 嵌套的configuration必须是静态类。

一、用@Configuration加载spring
1.1、@Configuration配置spring并启动spring容器
1.2、@Configuration启动容器+@Bean注册Bean
1.3、@Configuration启动容器+@Component注册Bean
1.4、**使用 AnnotationConfigApplicationContext 注册 AppContext 类的两种方法**

 **1.5、配置Web应用程序(web.xml中配置AnnotationConfigApplicationContext)**

二、组合多个配置类
2.1、在@configuration中引入spring的xml配置文件
2.2、在@configuration中引入其它注解配置
2.3、@configuration嵌套（嵌套的Configuration必须是静态类）
三、@EnableXXX注解
四、@Profile逻辑组配置
五、使用外部变量



### 面向对象中变化的应对方案

1. 制定一个interface，然后用多个类实现同一个interface。其实这就是**策略模式**的一个体现。
2. 一个类，通过更改属性的属性值（读取配置文件来更改等），解决变化



#### spring偏爱配置

使用配置文件，实现OCP，将变化 隔离/反映到配置文件中

**为什么隔离到配置文件**（优势）

1. 配置文件集中性
2. 清晰（没有业务逻辑）


#### 策略模式的变化方案
1. @autowired 自动注入 切换bean 的 name 默认是类型注入，类型有多个匹配的根据属性名称注入 名称不匹配飘红
2. @Qualifier 指定bean的名称
3. 有选择的只注入一个bean，注释掉某个bean上的@Component注解
4. 使用@Primary注解 提高当前bean的优先级


#### 条件注解
@Conditional 满足某个条件才进行注入
**自定义条件注解**：使用注解 @Conditional + 接口 Condition 实现自定义注解

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

public class MyCondition implements Condition {
    public boolean matches(ConditionContext var1, AnnotatedTypeMetadata var2){
        return false;
    }
}

@Configuration
public class MyConfiguration {
   @Bean
   @Conditional(MyCondition.class) // 使用自定义条件注解
   public String str() {
      return new String("");
   }
}
```

##### 内置的成品条件注解
**@ConditionalOnProperty** 根据配置文件的某个属性来决定是否加载bean

```java
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Configuration
public class MyConfiguration {
   @Bean
   // matchIfMissing = true 如果没有找到该属性，则加载这个bean，属性存在但是不匹配，不会加载这个bean
   @ConditionalOnProperty(value = "配置文件中的某个属性名",havingValue = "属性值",matchIfMissing = true)
   public String str() {
      return new String("");
   }
}
```
**@ConditionalOnBean**：IOC容器存在指定的bean时加载这个bean
**@ConditionalOnMissingBean**：IOC容器不存在指定的bean时加载这个bean


### springboot自动装配

#### 原理
SDK：一般指软件开发工具包。
**@EnableAutoConfiguration**:自动装配机制的注解 @Enable开头的注解，可以理解为模块装配



#### 为什么要有自动装配


### 深入理解SPI机制

一、什么是SPI
SPI ，全称为 Service Provider Interface，是一种服务发现机制。它通过在ClassPath路径下的META-INF/services文件夹查找文件，自动加载文件里所定义的类。

这一机制为很多框架扩展提供了可能，比如在Dubbo、JDBC中都使用到了SPI机制。我们先通过一个很简单的例子来看下它是怎么用的。

### 全局异常处理
**@ControllerAdvice**：是Spring3.2提供的新注解,它是一个Controller增强器,可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
**统一异常处理**
需要配合@ExceptionHandler使用。
当将异常抛到controller时,可以对异常进行统一处理,规定返回的json格式或是跳转到一个错误页面

#### 未知异常 已知异常
**未知异常：** 对应前端开发者和用户来说 没有任何意义。 服务端开发者代码逻辑有问题，服务端人员自己了解即可。


#### 出现异常修改请求状态码
@ResponseStatus(code = HttpStatus.BAD_REQUEST) 修改请求状态码为 400 HttpStatus 枚举类型 spring提供的

#### 出现异常 返回值类型使用 ResponseEntity
**ResponseEntity:** 该类型是spring提供的，可以直接返回给前端的类型，可以设置多种属性。
也可以使用泛型。出现异常后我们可以返回该类型的JSON数据给前端，告诉前端出现的错误类型及错误。
泛型数据是我们的响应体body，然后我们还需要提供一个响应头HttpHeaders，以及http状态码statusCode
```java
package com.mao.sleeve.core.exception;

import com.mao.sleeve.core.configuration.ExceptionCodeConfiguration;
import com.mao.sleeve.core.response.UnifyResponse;
import com.mao.sleeve.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalExceptionAdvice
 * @Description: 全局异常处理  TODO 未知异常的code统一为9999
 * 通过异常的形式 响应给前端消息 响应的数据其实就是json形式 包含状态码和错误信息
 * @Author 毛毛
 * @CreateDate 2021/10/23/周六 13:05
 * @Version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 属性注入 注入异常配置类 用来获取所有已知的异常错误码对应的错误信息
     */
    @Autowired
    private ExceptionCodeConfiguration exceptionCodes;
    /**
     * 全局处理异常 处理任意类型异常
     *
     * @ExceptionHandler 异常处理器
     * @ResponseStatus(code = HttpStatus.BAD_REQUEST) 修改请求状态码为 400 HttpStatus 枚举类型 spring提供的
     * @ExceptionHandler(value = Exception.class) value的值就是当前处理器处理的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public UnifyResponse handleException(HttpServletRequest req, Exception e) {
        // 未知异常的code统一为9999
        String url = req.getRequestURI();
        // 请求方式
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(9999, "未知异常！", method + " " + url);
        // TODO 开发阶段打印异常  记录日志
        System.out.println(e);
        return message;
    }

    /**
     * 处理http异常
     *
     * @param req
     * @param httpException
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException httpException) {
        // 未知异常的code统一为9999
        String url = req.getRequestURI();
        // 请求方式
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(httpException.getCode(), exceptionCodes.getMessage(httpException.getCode()), method + " " + url);
        // 响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置响应头
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // http状态码
        HttpStatus httpStatus = HttpStatus.resolve(httpException.getHttpStatusCode());
        assert httpStatus != null;
        return new ResponseEntity<>(message, httpHeaders, httpStatus);
    }
}

```

### 通过类管理配置文件

#### 使用 @PropertySource 注解 关联类和配置文件
**@ConfigurationProperties(prefix = "mao") // 指定关联的配置文件的属性的前缀**

```java
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: ExceptionCodeConfiguration
 * @Description: 异常错误码code配置类 读取错误码配置文件
 * @Author 毛毛
 * @CreateDate 2021/10/24/周日 21:58
 * @Version: v1.0
 */
@Getter
@PropertySource("classpath:config/exception-code.properties")
@ConfigurationProperties(prefix = "mao")
@Component
@PropertySource("classpath:config/exception-code.properties") // 将类和配置文件进行关联
@ConfigurationProperties(prefix = "mao") // 指定关联的配置文件的属性的前缀
public class ExceptionCodeConfiguration {
   /**
    * mao.codes[40000] mao对应配置文件所有属性的前缀 codes会自动配置该属性
    */
   private Map<Integer, String> codes = new HashMap<>();

   /**
    * 根据错误码code获取错误消息
    *
    * @param code
    * @return
    */
   public String getMessage(int code) {
      return codes.get(code);
   }
}
```





### 自动补全controller路由前缀

**使用spring提供的RequestMappingHandlerMapping类，实现在每个controller类上使用@RequestMapping(“/v1/banner”)注解补全路径时，自动补全路径前缀v1，且是将文件夹名称作为路由前缀。**



#### DTO 

DTO，数据传输对象。（Data Transfer Object）。是一种设计模式之间传输数据的软件应用系统。数据传输目标往往是数据访问对象从数据库中检索数据。本项目中，该包下的类是用来传输前后端数据所使用的。



#### 浅析VO、DTO、DO、PO的概念、区别和用处

本篇文章主要讨论一下我们经常会用到的一些对象：VO、DTO、DO和PO。

由于不同的项目和开发人员有不同的命名习惯，这里我首先对上述的概念进行一个简单描述，名字只是个标识，我们重点关注其概念：

**概念：**

**VO****（View Object）：**视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。

**DTO****（Data Transfer Object****）：**数据传输对象，这个概念来源于J2EE的设计模式，原来的目的是为了EJB的分布式应用提供粗粒度的数据实体，以减少分布式调用的次数，从而提高分布式调用的性能和降低网络负载，但在这里，我泛指用于展示层与服务层之间的数据传输对象。

**DO****（**Domain Object**）： **领域对象，就是从现实世界中抽象出来的有形或无形的业务实体。

**PO****（**Persistent Object**）：**持久化对象，它跟持久层（通常是关系型数据库）的数据结构形成一一对应的映射关系，如果持久层是关系型数据库，那么，数据表中的每个字段（或若干个）就对应PO的一个（或若干个）属性。

**模型：**

​    下面以一个时序图建立简单模型来描述上述对象在三层架构应用中的位置

 

 

l     用户发出请求（可能是填写表单），表单的数据在展示层被匹配为VO。

l     展示层把VO转换为服务层对应方法所要求的DTO，传送给服务层。

l     服务层首先根据DTO的数据构造（或重建）一个DO，调用DO的业务方法完成具体业务。

l     服务层把DO转换为持久层对应的PO（可以使用ORM工具，也可以不用），调用持久层的持久化方法，把PO传递给它，完成持久化操作。

l     对于一个逆向操作，如读取数据，也是用类似的方式转换和传递，略。

 

**VO****与DTO****的区别**

​    大家可能会有个疑问（在笔者参与的项目中，很多程序员也有相同的疑惑）：既然DTO是展示层与服务层之间传递数据的对象，为什么还需要一个VO呢？对！对于绝大部分的应用场景来说，DTO和VO的属性值基本是一致的，而且他们通常都是POJO，因此没必要多此一举，但不要忘记这是实现层面的思维，对于设计层面来说，概念上还是应该存在VO和DTO，因为两者有着本质的区别，DTO代表服务层需要接收的数据和返回的数据，而VO代表展示层需要显示的数据。

​    用一个例子来说明可能会比较容易理解：例如服务层有一个getUser的方法返回一个系统用户，其中有一个属性是gender(性别)，对于服务层来说，它只从语义上定义：1-男性，2-女性，0-未指定，而对于展示层来说，它可能需要用“帅哥”代表男性，用“美女”代表女性，用“秘密”代表未指定。说到这里，可能你还会反驳，在服务层直接就返回“帅哥美女”不就行了吗？对于大部分应用来说，这不是问题，但设想一下，如果需求允许客户可以定制风格，而不同风格对于“性别”的表现方式不一样，又或者这个服务同时供多个客户端使用（不同门户），而不同的客户端对于表现层的要求有所不同，那么，问题就来了。再者，回到设计层面上分析，从职责单一原则来看，服务层只负责业务，与具体的表现形式无关，因此，它返回的DTO，不应该出现与表现形式的耦合。

​    理论归理论，这到底还是分析设计层面的思维，是否在实现层面必须这样做呢？一刀切的做法往往会得不偿失，下面我马上会分析应用中如何做出正确的选择。

 

**VO****与DTO****的应用**

​    上面只是用了一个简单的例子来说明VO与DTO在概念上的区别，本节将会告诉你如何在应用中做出正确的选择。

​    在以下才场景中，我们可以考虑把VO与DTO二合为一（注意：是实现层面）：

l     当需求非常清晰稳定，而且客户端很明确只有一个的时候，没有必要把VO和DTO区分开来，这时候VO可以退隐，用一个DTO即可，为什么是VO退隐而不是DTO？回到设计层面，服务层的职责依然不应该与展示层耦合，所以，对于前面的例子，你很容易理解，DTO对于“性别”来说，依然不能用“帅哥美女”，这个转换应该依赖于页面的脚本（如JavaScript）或其他机制（JSTL、EL、CSS）

l     即使客户端可以进行定制，或者存在多个不同的客户端，如果客户端能够用某种技术（脚本或其他机制）实现转换，同样可以让VO退隐

 

以下场景需要优先考虑VO、DTO并存：

l     上述场景的反面场景

l     因为某种技术原因，比如某个框架（如Flex）提供自动把POJO转换为UI中某些Field时，可以考虑在实现层面定义出VO，这个权衡完全取决于使用框架的自动转换能力带来的开发和维护效率提升与设计多一个VO所多做的事情带来的开发和维护效率的下降之间的比对。

l     如果页面出现一个“大视图”，而组成这个大视图的所有数据需要调用多个服务，返回多个DTO来组装（当然，这同样可以通过服务层提供一次性返回一个大视图的DTO来取代，但在服务层提供一个这样的方法是否合适，需要在设计层面进行权衡）。

 

**DTO****与DO****的区别**

​    首先是概念上的区别，DTO是展示层和服务层之间的数据传输对象（可以认为是两者之间的协议），而DO是对现实世界各种业务角色的抽象，这就引出了两者在数据上的区别，例如UserInfo和User（对于DTO和DO的命名规则，请参见笔者前面的一篇博文），对于一个getUser方法来说，本质上它永远不应该返回用户的密码，因此UserInfo至少比User少一个password的数据。而在领域驱动设计中，正如第一篇系列文章所说，DO不是简单的POJO，它具有领域业务逻辑。

 

**DTO****与DO****的应用**

​    从上一节的例子中，细心的读者可能会发现问题：既然getUser方法返回的UserInfo不应该包含password，那么就不应该存在password这个属性定义，但如果同时有一个createUser的方法，传入的UserInfo需要包含用户的password，怎么办？在设计层面，展示层向服务层传递的DTO与服务层返回给展示层的DTO在概念上是不同的，但在实现层面，我们通常很少会这样做（定义两个UserInfo，甚至更多），因为这样做并不见得很明智，我们完全可以设计一个完全兼容的DTO，在服务层接收数据的时候，不该由展示层设置的属性（如订单的总价应该由其单价、数量、折扣等决定），无论展示层是否设置，服务层都一概忽略，而在服务层返回数据时，不该返回的数据（如用户密码），就不设置对应的属性。

​    对于DO来说，还有一点需要说明：为什么不在服务层中直接返回DO呢？这样可以省去DTO的编码和转换工作，原因如下：

l     两者在本质上的区别可能导致彼此并不一一对应，一个DTO可能对应多个DO，反之亦然，甚至两者存在多对多的关系。

l     DO具有一些不应该让展示层知道的数据

l     DO具有业务方法，如果直接把DO传递给展示层，展示层的代码就可以绕过服务层直接调用它不应该访问的操作，对于基于AOP拦截服务层来进行访问控制的机制来说，这问题尤为突出，而在展示层调用DO的业务方法也会因为事务的问题，让事务难以控制。

l     对于某些ORM框架（如Hibernate）来说，通常会使用“延迟加载”技术，如果直接把DO暴露给展示层，对于大部分情况，展示层不在事务范围之内（Open session in view在大部分情况下不是一种值得推崇的设计），如果其尝试在Session关闭的情况下获取一个未加载的关联对象，会出现运行时异常（对于Hibernate来说，就是LazyInitiliaztionException）。

l     从设计层面来说，展示层依赖于服务层，服务层依赖于领域层，如果把DO暴露出去，就会导致展示层直接依赖于领域层，这虽然依然是单向依赖，但这种跨层依赖会导致不必要的耦合。

 

对于DTO来说，也有一点必须进行说明，就是DTO应该是一个“扁平的二维对象”，举个例子来说明：如果User会关联若干个其他实体（例如Address、Account、Region等），那么getUser()返回的UserInfo，是否就需要把其关联的对象的DTO都一并返回呢？如果这样的话，必然导致数据传输量的大增，对于分布式应用来说，由于涉及数据在网络上的传输、序列化和反序列化，这种设计更不可接受。如果getUser除了要返回User的基本信息外，还需要返回一个AccountId、AccountName、RegionId、RegionName，那么，请把这些属性定义到UserInfo中，把一个“立体”的对象树“压扁”成一个“扁平的二维对象”，笔者目前参与的项目是一个分布式系统，该系统不管三七二十一，把一个对象的所有关联对象都转换为相同结构的DTO对象树并返回，导致性能非常的慢。

 

 

**DO****与PO****的区别**

​    DO和PO在绝大部分情况下是一一对应的，PO是只含有get/set方法的POJO，但某些场景还是能反映出两者在概念上存在本质的区别：

l     DO在某些场景下不需要进行显式的持久化，例如利用策略模式设计的商品折扣策略，会衍生出折扣策略的接口和不同折扣策略实现类，这些折扣策略实现类可以算是DO，但它们只驻留在静态内存，不需要持久化到持久层，因此，这类DO是不存在对应的PO的。

l     同样的道理，某些场景下，PO也没有对应的DO，例如老师Teacher和学生Student存在多对多的关系，在关系数据库中，这种关系需要表现为一个中间表，也就对应有一个TeacherAndStudentPO的PO，但这个PO在业务领域没有任何现实的意义，它完全不能与任何DO对应上。这里要特别声明，并不是所有多对多关系都没有业务含义，这跟具体业务场景有关，例如：两个PO之间的关系会影响具体业务，并且这种关系存在多种类型，那么这种多对多关系也应该表现为一个DO，又如：“角色”与“资源”之间存在多对多关系，而这种关系很明显会表现为一个DO——“权限”。

l     某些情况下，为了某种持久化策略或者性能的考虑，一个PO可能对应多个DO，反之亦然。例如客户Customer有其联系信息Contacts，这里是两个一对一关系的DO，但可能出于性能的考虑（极端情况，权作举例），为了减少数据库的连接查询操作，把Customer和Contacts两个DO数据合并到一张数据表中。反过来，如果一本图书Book，有一个属性是封面cover，但该属性是一副图片的二进制数据，而某些查询操作不希望把cover一并加载，从而减轻磁盘IO开销，同时假设ORM框架不支持属性级别的延迟加载，那么就需要考虑把cover独立到一张数据表中去，这样就形成一个DO对应对个PO的情况。

l     PO的某些属性值对于DO没有任何意义，这些属性值可能是为了解决某些持久化策略而存在的数据，例如为了实现“乐观锁”，PO存在一个version的属性，这个version对于DO来说是没有任何业务意义的，它不应该在DO中存在。同理，DO中也可能存在不需要持久化的属性。

 

**DO ****与PO** **的应用**

​    由于ORM框架的功能非常强大而大行其道，而且JavaEE也推出了JPA规范，现在的业务应用开发，基本上不需要区分DO与PO，PO完全可以通过JPA，Hibernate Annotations/hbm隐藏在DO之中。虽然如此，但有些问题我们还必须注意：

l     对于DO中不需要持久化的属性，需要通过ORM显式的声明，如：在JPA中，可以利用@Transient声明。

l     对于PO中为了某种持久化策略而存在的属性，例如version，由于DO、PO合并了，必须在DO中声明，但由于这个属性对DO是没有任何业务意义的，需要让该属性对外隐藏起来，最常见的做法是把该属性的get/set方法私有化，甚至不提供get/set方法，但对于Hibernate来说，这需要特别注意，由于Hibernate从数据库读取数据转换为DO时，是利用反射机制先调用DO的空参数构造函数构造DO实例，然后再利用JavaBean的规范反射出set方法来为每个属性设值，如果不显式声明set方法，或把set方法设置为private，都会导致Hibernate无法初始化DO，从而出现运行时异常，可行的做法是把属性的set方法设置为protected。

l     对于一个DO对应多个PO，或者一个PO对应多个DO的场景，以及属性级别的延迟加载，Hibernate都提供了很好的支持，请参考Hibnate的相关资料。







#### Lombok

注解@NonNull ： 属性值不能为空。

@RequiredArgsConstructor：该注解为所有用@NonNull注解标记的属性生成构造函数。



**@Builder**注解：可以以链式的方式构建一个对象。

```java
@Builder
class Person{
    private String name;
    private Integer age;
}

class Test{
    void test(){
        Person person = Person.builder().name("毛毛").age(21).build();
    }
}
```

##### lombok工具注解的一些坑

1. 对某个bean使用了@builder注解以后，就不能通过new关键字来构建一个空对象了（new Person() ），也不能使用set和get方法了，只能使用build的方式来构建。原因是因为：**使用了@Builder注解以后，会给这个类生成一个私有的无参构造函数，如果想要还使用new关键字实例化一个对象，则还需要使用@NoArgsConstructor注解生成一个无参public的构造函数**
2. 使用@Builder注解来构建的实体bean对象，返回给前端，为了能够序列化，一定是需要get方法的。所以别忘了加上@Getter注解





### JSR

JSR（Java Specification Request）就是组织内的成员针对 Java 的发展提出的一些需求，通过审核之后即会融入到新版本的 Java 功能中成为 Java 的一项特性或功能，不同的发行版本和虚拟机都会遵守这些约定。



##### lombok实现的规范

lombok实现的规范实际上是JSR-269规范



##### JSR-303

该规范是java专门用于bean校验的规范。





### 参数校验

需要使用springboot提供的@Validated注解标识在类上，然后使用其他实际的参数校验注解结合，进行参数的校验。**这里参数的校验是结合框架hibernate的validator校验器使用的**

```java
@Validated
class Controller{
    @GetMapping("/test")
    // @Max注解 这里标识某个参数的最大值是多少
    public String test(@Max(10) Integer age){
        
    }
}
```

spring提供的校验注解也很多，比如@Email，@Min，@Max，@Range（指定参数的范围）等等

##### 级联验证

 **如果想要验证某个类的属性是引用类型的情况，且引用类型的某些属性也需要验证，那么需要在当前的类的引用类型属性上使用@Valid注解，进行级联的验证**



##### @Validated和@Valid注解的关系

- @Validated就像一个开关，使用了该注解，就会开启参数的校验功能。

- @Valid注解一般情况下是用来做级联校验的。

- 但是：@Valid注解不仅仅是可以用来做级联校验的。很多情况下该注解是可以替换@Validated注解的。

- @Validated是spring提供的，其实也是最@Valid做增强的。





### 注解

#### java的元注解

**@Target** 用来定义你的注解将应用于什么地方（例如是一个方法或者一个域）。可能的值在枚举类 **ElemenetType** 中：

     ElemenetType.CONSTRUCTOR-----------------------------构造器声明 
     ElemenetType.FIELD ----------------------------------域声明（包括 enum 实例） 
     ElemenetType.LOCAL_VARIABLE------------------------- 局部变量声明 
     ElemenetType.METHOD ---------------------------------方法声明 
     ElemenetType.PACKAGE --------------------------------包声明 
     ElemenetType.PARAMETER ------------------------------参数声明 
     ElemenetType.TYPE----------------------------------- 类，接口（包括注解类型）或enum声明 


**@Retention** 用来定义该注解在哪一个级别可用，在源代码中（SOURCE）、类文件中（CLASS）或者运行时（RUNTIME）。

**@Retention** 表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括：

     RetentionPolicy.SOURCE-------------注解将被编译器丢弃 
     RetentionPolicy.CLASS -------------注解在class文件中可用，但会被VM丢弃 
     RetentionPolicy.RUNTIME ---------VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。

**@Documented** 将此注解包含在 javadoc 中 ，它代表着此注解会被javadoc工具提取成文档。在doc文档中的内容会因为此注解的信息内容不同而不同。相当与@see,@param 等。

**@Inherited** 允许子类继承父类中的注解。



#### 自定义校验注解

```java
package com.mao.sleeve.dto.validators;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName: PasswordEqual
 * @Description: 比较两个密码是否相等
 * @Author 毛毛
 * @CreateDate 2021/10/26/周二 20:37
 * @Version: v1.0
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordEqual {
    String message() default "password are not equals";

    /**
     * 自定义校验注解的模板方法
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 自定义校验注解的模板方法
     * @return
     */
    Class<? extends Payload[]>[] payload() default {};
}


package com.mao.sleeve.dto.validators.validator;

        import com.mao.sleeve.validators.anno.PasswordEqual;

        import javax.validation.ConstraintValidator;
        import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: PasswordValidator
 * @Description: 自定义校验注解的关联类 需要实现接口
 * ConstraintValidator<关联的自定义校验注解的类型, 自定义注解修饰的目标的类型（如果是修饰类，就是类的类型，如果是属性，就是属性的类型）>
 * @Author 毛毛
 * @CreateDate 2021/10/26/周二 20:50
 * @Version: v1.0
 */
public class PasswordValidator implements ConstraintValidator<PasswordEqual, Object> {
    /**
     * 拿到使用注解时  传入的参数
     * constraintAnnotation.min()  constraintAnnotation.max()
     * @param constraintAnnotation
     */
    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}

```





