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