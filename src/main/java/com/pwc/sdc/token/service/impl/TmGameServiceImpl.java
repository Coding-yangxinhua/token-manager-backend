package com.pwc.sdc.token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pwc.sdc.token.domain.TmGame;
import com.pwc.sdc.token.service.TmGameService;
import com.pwc.sdc.token.mapper.TmGameMapper;
import com.pwc.sdc.token.service.TmGameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author Young
* @description 针对表【TM_GAME(Game信息表)】的数据库操作Service实现
* @createDate 2024-02-01 23:32:43
*/
@Service
public class TmGameServiceImpl extends ServiceImpl<TmGameMapper, TmGame>
    implements TmGameService{
    
    @Autowired
    private TmGameService gameService;

    @Override
    @Cacheable(cacheNames = "GAME")
    public List<TmGame> listAll() {
        return this.list();
    }

    @Override
    public TmGame checkExist(String url) {
        Optional<TmGame> any = gameService.listAll().stream().filter(i -> i.getDeleted() == 0 && url.contains(i.getUrl())).findAny();
        return any.orElse(null);
    }
}




