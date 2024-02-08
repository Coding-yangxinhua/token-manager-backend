package com.pwc.sdc.token.controller.api;

import com.pwc.sdc.token.domain.vo.GameUserVo;
import com.pwc.sdc.token.service.TmGameUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xinhua X Yang
 */
@Api("api接口")
@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    private TmGameUserService gameUserService;

    @PostMapping("/getOrSave")
    @ApiOperation(value = "判断并保存游戏角色token", httpMethod = "POST")
    public String saveOrUpdate(@RequestParam("url") String url, @RequestBody(required = false) String response) {
        GameUserVo gameUserVo = new GameUserVo();
        gameUserVo.setUrl(url);
        gameUserVo.setToken(response);
        return gameUserService.saveGameUser(gameUserVo);
    }


}
