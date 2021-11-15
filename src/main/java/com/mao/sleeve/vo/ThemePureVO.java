package com.mao.sleeve.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: ThemePureVO
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 20:30
 * @Version: v1.0
 */
@Getter
@Setter
public class ThemePureVO {
    private Long id;
    private String title;
    private String description;
    private String name;
    private String extend;
    private String entranceImg;
    /**
     * 主题是否有对整个主题的描述图片，比如每周上新图片 需要在主题的上方
     */
    private String internalTopImg;
    private Boolean online;
    private String titleImg;
    /**
     * 指定当前主题 在前端应用什么模板 模板名称
     * 在小程序端可以创建多个模板，然后通过我们服务器传递过去的模板名称，
     * 可以动态的决定主题使用哪个模板，不需要前端修改代码
     * 数据会填充到指定的模板里面
     */
    private String tplName;
}
