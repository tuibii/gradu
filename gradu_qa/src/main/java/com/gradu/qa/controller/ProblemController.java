package com.gradu.qa.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.qa.client.BaseClient;
import com.gradu.qa.dto.ProblemDTO;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import entity.PageData;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    BaseClient baseClient;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/label/{id}")
    public Result findlabel(@PathVariable("id") String id){
        return baseClient.getLabelById(id);
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params,HttpServletRequest request){
        PageData<ProblemEntity> page = problemService.page(params);

        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)){
            try {
                Claims claims = jwtUtil.parseToken(token);
                String id = claims.getId();

                if (StringUtils.isNotEmpty(id)){
                    List<ProblemEntity> rows = page.getRows();
                    List<ProblemDTO> dtoList = new ArrayList<>();
                    for (ProblemEntity entity:rows){
                        ProblemDTO dto = new ProblemDTO();
                        BeanUtils.copyProperties(entity,dto);
                        System.out.println(redisTemplate.opsForSet().members("problem:"+dto.getId()));
                        if (redisTemplate.opsForSet().isMember("problem:"+dto.getId(),id)){
                            dto.setCanThumbup(true);
                        }else {
                            dto.setCanThumbup(false);
                        }
                        dtoList.add(dto);
                    }
                    return new Result(true, StatusCode.OK,"查询成功",new PageData<>(dtoList,page.getTotal()));
                }
            }catch (Exception e){
                return new Result(true, StatusCode.OK,"查询成功",page);
            }
        }

        return new Result(true, StatusCode.OK,"查询成功",page);
    }

    @PostMapping
    public Result add(@RequestBody ProblemEntity problemEntity){

        Claims claims = (Claims) request.getAttribute("token");
        String role = (String) claims.get("role");

        if (StringUtils.isEmpty(role) || !role.equals("user")){
            return new Result(false,StatusCode.ACCESS_ERROR,"权限不足");
        }

        problemService.add(problemEntity);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @GetMapping("/thumbup/{id}")
    public Result thumbup(@PathVariable("id") String id,HttpServletRequest request){
        String token = request.getHeader("token");
        try {
            Claims claims = jwtUtil.parseToken(token);
            String user = claims.getId();
            if (StringUtils.isNotEmpty(user)){
                Long add = redisTemplate.opsForSet().add("problem:" + id, user);
                if (add > 0){
                    ProblemEntity entity = problemService.selectById(id);
                    entity.setThumbup(entity.getThumbup()+1);
                    problemService.update(entity);
                    return new Result(true,StatusCode.OK,"点赞成功");
                }
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ACCESS_ERROR,"请先登录！");
        }
        return new Result(false,StatusCode.FAIL,"点赞失败");
    }

}
