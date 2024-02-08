package com.pwc.sdc.token.service;

import com.pwc.sdc.token.domain.TmGame;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author Young
* @description 针对表【TM_GAME(Game信息表)】的数据库操作Service
* @createDate 2024-02-01 23:32:43
*/
public interface TmGameService extends IService<TmGame> {

    List<TmGame> listAll();

    TmGame checkExist(String url);
}
