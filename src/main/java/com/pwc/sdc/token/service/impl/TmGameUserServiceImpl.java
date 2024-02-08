package com.pwc.sdc.token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pwc.sdc.token.common.enums.EnableStatus;
import com.pwc.sdc.token.domain.TmGame;
import com.pwc.sdc.token.domain.TmGameUser;
import com.pwc.sdc.token.domain.vo.GameUserVo;
import com.pwc.sdc.token.mapper.TmGameMapper;
import com.pwc.sdc.token.service.TmGameService;
import com.pwc.sdc.token.service.TmGameUserService;
import com.pwc.sdc.token.mapper.TmGameUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Young
* @description 针对表【TM_GAME_USER(游戏用户表)】的数据库操作Service实现
* @createDate 2024-02-01 23:32:43
*/
@Service
public class TmGameUserServiceImpl extends ServiceImpl<TmGameUserMapper, TmGameUser>
    implements TmGameUserService{
    @Autowired
    private TmGameService gameService;

    @Override
    public boolean checkExist(Long gameId, String token) {
        LambdaQueryWrapper<TmGameUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TmGameUser::getGameId, gameId)
                .eq(TmGameUser::getToken, token);
        return !this.list(queryWrapper).isEmpty();
    }


    @Override
    @Transactional
    public String saveGameUser(GameUserVo gameUserVo) {
        // 判断对应链接是否存在
        TmGame tmGame = gameService.checkExist(gameUserVo.getUrl());
        if (tmGame == null) {
            return gameUserVo.getToken();
        }
        Long gameId = tmGame.getId();
        String token = gameUserVo.getToken();
        // 如果返回的值是error token的，用当前已激活的token代替
        if (tmGame.getErrorToken().equals(token) || token.equals("{}")) {
            TmGameUser enableUser = getEnableUserToken(gameId);
            if (enableUser != null) {
                return enableUser.getToken();
            }
            return token;
        }
        // 判断游戏角色是否存在
        boolean userExist = checkExist(tmGame.getId(), gameUserVo.getToken());
        // 存在则直接返回
        if (userExist) {
            return token;
        }
        // 不存在则存入新用户信息
        TmGameUser tmGameUser = new TmGameUser();
        tmGameUser.setGameId(gameId);
        tmGameUser.setStatus(EnableStatus.ENABLE.value());
        tmGameUser.setToken(token);
        this.save(tmGameUser);
        // 更改状态默认为启用
        changeGameUserStatus(tmGameUser);
        return token;
    }

    @Override
    public boolean changeGameUserStatus(TmGameUser gameUser) {
        // 如果是启用某一user，则先禁用其他gameUser
        if (EnableStatus.ENABLE.value() == gameUser.getStatus()) {
            LambdaUpdateWrapper<TmGameUser> disableWrapper = new LambdaUpdateWrapper<>();
            disableWrapper.eq(TmGameUser::getGameId, gameUser.getGameId())
                    .eq(TmGameUser::getStatus, EnableStatus.ENABLE.value())
                    .set(TmGameUser::getStatus, EnableStatus.DISABLE.value());
            this.update(disableWrapper);
        }
        // 修改当前用户状态
        LambdaUpdateWrapper<TmGameUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(TmGameUser::getGameId, gameUser.getGameId())
                .eq(TmGameUser::getId, gameUser.getId())
                .set(TmGameUser::getStatus, gameUser.getStatus());
        this.update(updateWrapper);

        return false;
    }

    @Override
    public TmGameUser getEnableUserToken(Long gameId) {
        LambdaQueryWrapper<TmGameUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TmGameUser::getGameId, gameId)
                .eq(TmGameUser::getStatus, EnableStatus.ENABLE.value());
        return this.getOne(queryWrapper);
    }
}




