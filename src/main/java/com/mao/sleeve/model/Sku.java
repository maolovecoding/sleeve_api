package com.mao.sleeve.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mao.sleeve.bean.Spec;
import com.mao.sleeve.utils.GenericAndJsonUtil;
import com.mao.sleeve.utils.ListToJsonUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Sku
 * @Description: sku单品 spu = sku + 规格
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 20:44
 * @Version: v1.0
 */
@Entity
@Getter
@Setter
public class Sku extends BaseEntity {
    @Id
    private Long id;
    /**
     * 原价
     */
    private BigDecimal price;
    /**
     * 折扣价
     */
    private BigDecimal discountPrice;
    /**
     * 商品是否还上线 也就是是否还在售卖
     */
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    /**
     * 规格 这里是一组规格值 在数据库中是以json的形式存储的
     * 既然是json，肯定是key:value的形式 我们知道value是任意基本类型
     * 在数据库中，存储的是一个数组形式的json [{},{},{}]   List<Map<String, ?>>
     * 单一规格spec {}   Map<String, ?>
     * 所有规格集合 [{}]  List<?>
     *
     * @Convert(converter = MapToJsonUtil.class) 转换器
     * 对该模型进行序列化或者反序列化的时候，该字段将会进行我们定义好的规则进行转换
     */
    //@Convert(converter = ListToJsonUtil.class)
    //private List<?> specs;
    private String specs;
    /**
     *
     */
    private String code;
    /**
     * 库存量
     */
    private Long stock;
    //private Object categoryId;
    //private Object rootCategoryId;

    /**
     * 说实话，这种方式没有前面的方式好，凑活吧！
     *
     * @return
     */
    public List<Spec> getSpecs() {
        if (specs == null) {
            return Collections.emptyList();
        }
        // TODO 就是把List<Spec> 当做我们原类型中的泛型 T 而已  我是不喜欢这种传递类型的方式 需要new一个匿名类
        return GenericAndJsonUtil.jsonToList(specs, new TypeReference<List<Spec>>() {
        });
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = GenericAndJsonUtil.objectToJson(specs);
    }
}
