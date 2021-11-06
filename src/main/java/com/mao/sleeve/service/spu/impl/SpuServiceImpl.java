package com.mao.sleeve.service.spu.impl;

import com.mao.sleeve.model.Spu;
import com.mao.sleeve.repository.spu.SpuRepository;
import com.mao.sleeve.service.spu.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SpuServiceImpl
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/01/周一 18:29
 * @Version: v1.0
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuRepository spuRepository;

    @Override
    public Spu getSpuById(Long id) {
        return spuRepository.findOneById(id);
    }

    @Override
    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size) {
        // TODO  数据分页 且倒序排列
        // jpa操作的是模型类，所以我们的字段也要写出属性名的那种形式
        PageRequest page = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        // findAll 该方法是jpa默认提供的
        return spuRepository.findAll(page);
    }
}
