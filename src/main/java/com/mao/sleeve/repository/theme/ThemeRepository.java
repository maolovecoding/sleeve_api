package com.mao.sleeve.repository.theme;

import com.mao.sleeve.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: ThemeRepository
 * @Description: 主题持久层接口
 * @Author 毛毛
 * @CreateDate 2021/11/13/周六 20:27
 * @Version: v1.0
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    /**
     * 根据主题名称 查询所有的主题
     * 这里使用的是 JPQL -》java persistence Query Language java持久化查询语言
     * jpql是操作模型的，而不是sql一样操作数据库的
     * 如果想使用原生的sql查询数据库，需要让@Query注解的nativeQuery属性的值设置为true
     * 这里的Theme 就是Theme实体 这里的t也是这个模型，别名 t.name 就是模型下的属性
     * @param names 主题名称
     * @return 查询的主题
     */
    @Query("select t from Theme t where t.name in (:names)")
    // 也可以使用命名来查询 findByNameInNames
    List<Theme> findByNames(@Param("names") List<String> names);

    /**
     * optional 可以解决空指针
     * @param name 主题名称
     * @return
     */
    Optional<Theme> findByName(String name);
}
