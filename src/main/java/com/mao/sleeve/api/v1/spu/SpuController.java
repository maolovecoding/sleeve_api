package com.mao.sleeve.api.v1.spu;

import com.mao.sleeve.bo.PageCounter;
import com.mao.sleeve.exception.http.NotFoundException;
import com.mao.sleeve.model.Spu;
import com.mao.sleeve.service.spu.SpuService;
import com.mao.sleeve.utils.BeanUtil;
import com.mao.sleeve.utils.CommonUtil;
import com.mao.sleeve.vo.PagingDozer;
import com.mao.sleeve.vo.SpuSimplifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SpuController
 * @Description: spu接口
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 18:22
 * @Version: v1.0
 * TODO @Validated // 开启数据校验
 */
@RestController
@Validated // 开启数据校验
public class SpuController {
    @Autowired
    private SpuService spuService;

    /**
     * 获取商品spu的详情数据
     *
     * @param id
     * @return 商品详细数据
     * @Positive 参数必须为正数
     */
    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        Spu spu = spuService.getSpuById(id);
        if (spu == null) {
            // 商品信息不存在异常
            throw new NotFoundException(30003);
        }
        return spu;
    }

    /**
     * 获取一组spu，且最新的数据是排在最前面的
     *
     * @param start 数据分页起始数
     * @param count 当前分页数据总数
     * @return
     */
    @GetMapping("/latest")
    public PagingDozer<Spu, SpuSimplifyVO> getLatestSpuList(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "10") Integer count) {
        // 将 start和count 转为 pageNum 和 size 的形式
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        Page<Spu> page = spuService.getLatestPagingSpu(pageCounter.getPageNum(), pageCounter.getSize());
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }
}
