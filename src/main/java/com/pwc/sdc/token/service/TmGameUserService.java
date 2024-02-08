package com.pwc.sdc.token.service;

import com.pwc.sdc.token.domain.TmGameUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pwc.sdc.token.domain.vo.GameUserVo;

/**
* @author Young
* @description 针对表【TM_GAME_USER(游戏用户表)】的数据库操作Service
* @createDate 2024-02-01 23:32:43
*/
public interface TmGameUserService extends IService<TmGameUser> {

    boolean checkExist(Long gameId, String token);
    String saveGameUser(GameUserVo gameUserVo);

    boolean changeGameUserStatus(TmGameUser gameUser);

    TmGameUser getEnableUserToken(Long gameId);


}
