package com.gradu.qa.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.qa.client.BaseClient;
import com.gradu.qa.dto.ProblemDTO;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import com.gradu.qa.service.ProblemUserService;
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

/**
 * @author wuyiliang
 */
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

    @Autowired
    ProblemUserService problemUserService;

    @GetMapping("/label/{id}")
    public Result findlabel(@PathVariable("id") String id){
        return baseClient.getLabelById(id);
    }

    @GetMapping("page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<ProblemEntity> page = problemService.page(params);

        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null){
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
        }


        return new Result(true, StatusCode.OK,"查询成功",page);
    }

    @GetMapping("list")
    public Result list(@RequestParam Map<String, Object> params) {
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null) {
            params.put("userid", claims.getId());
        }

        List<ProblemEntity> list = problemService.list(params);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") String id){
        Claims claims = (Claims) request.getAttribute("claims");
        ProblemEntity entity = problemService.selectById(id);
        if (claims != null){
            String userid = claims.getId();
            if (StringUtils.isNotEmpty(userid)){
                ProblemDTO dto = new ProblemDTO();
                BeanUtils.copyProperties(entity,dto);
                dto.setFocus(problemUserService.focus(userid,id));
                return new Result(true,StatusCode.OK,"查询成功",dto);
            }
        }
        return new Result(true,StatusCode.OK,"查询成功",entity);
    }

    @PostMapping
    public Result add(@RequestBody ProblemEntity problemEntity){

        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null) {
            String id = claims.getId();
            problemEntity.setUserid(id);
            problemService.add(problemEntity);
            return new Result(true, StatusCode.OK, "添加成功");
        }

        return new Result(false,StatusCode.FAIL,"请先登录");
    }

    @GetMapping("/thumbup/{id}")
    public Result thumbup(@PathVariable("id") String id){
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims != null){
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
        }

        return new Result(false,StatusCode.FAIL,"点赞失败");
    }

    @GetMapping("/focus/{id}")
    public Result focus(@PathVariable("id") String id){
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims != null){
            String user = claims.getId();
            if (StringUtils.isNotEmpty(user)){
                problemService.focus(id,user);
                return new Result(true,StatusCode.OK,"关注成功");
            }
        }

        return new Result(false,StatusCode.FAIL,"关注失败");
    }

}
