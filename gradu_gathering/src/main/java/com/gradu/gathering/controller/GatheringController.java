package com.gradu.gathering.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.gradu.gathering.client.UserClient;
import com.gradu.gathering.dto.GatheringDTO;
import com.gradu.gathering.entity.DynamicEntity;
import com.gradu.gathering.entity.GatheringEntity;
import com.gradu.gathering.entity.UserEntity;
import com.gradu.gathering.entity.UserGathEntity;
import com.gradu.gathering.excel.UserExcel;
import com.gradu.gathering.service.GatheringService;
import com.gradu.gathering.service.UserGathService;
import com.gradu.gathering.util.ExcelUtils;
import entity.PageData;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.ConvertUtil;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    GatheringService gatheringService;

    @Autowired
    UserGathService userGathService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    IdWorker idWorker;

    @Autowired
    UserClient userClient;

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims != null) {
            params.put("userId", claims.getId());
        }
        List<GatheringDTO> dtoList = gatheringService.dtoList(params);
        return new Result(true, StatusCode.OK,"查询成功", dtoList);
    }

    @GetMapping("/mygathering")
    public Result myGathering(){
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims != null) {
            String userId = claims.getId();
            Map<String, Object> params = new HashMap<>(4);
            params.put("creator", userId);
            List<GatheringEntity> list = gatheringService.list(params);
            return new Result(true, StatusCode.OK, "查询成功", list);
        }
        return new Result(false, StatusCode.FAIL, "未登录");
    }

    @GetMapping("/myjoin")
    public Result myjoin(){
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims != null) {
            String userId = claims.getId();
            Map<String, Object> params = new HashMap<>(4);
            params.put("userid", userId);
            List<UserGathEntity> list = userGathService.list(params);

            List<GatheringEntity> dtoList = new ArrayList<>();

            if (CollectionUtil.isNotEmpty(list)) {
                for (UserGathEntity userGathEntity : list) {
                    GatheringEntity gatheringEntity = gatheringService.selectById(userGathEntity.getGathid());
                    dtoList.add(gatheringEntity);
                }
            }

            return new Result(true, StatusCode.OK, "查询成功", dtoList);
        }
        return new Result(false, StatusCode.FAIL, "未登录");
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") String id){
        Claims claims = (Claims) request.getAttribute("claims");
        GatheringEntity gatheringEntity = gatheringService.selectById(id);
        GatheringDTO gatheringDTO = ConvertUtil.sourceToTarget(gatheringEntity, GatheringDTO.class);
        if (claims != null) {
            Map<String, Object> params = new HashMap<>(4);
            params.put("userid", claims.getId());
            params.put("gathid", gatheringDTO.getId());
            List<UserGathEntity> userGathEntityList = userGathService.list(params);
            if (CollectionUtil.isNotEmpty(userGathEntityList)) {
                gatheringDTO.setJoin(true);
            } else {
                gatheringDTO.setJoin(false);
            }
        }
        return new Result(true, StatusCode.OK,"查询成功", gatheringDTO);
    }

    @PostMapping
    public Result save (@RequestBody GatheringEntity entity) {
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESS_ERROR, "未登录");
        }
        entity.setCreator(claims.getId());
        gatheringService.add(entity);

        DynamicEntity dynamicEntity = new DynamicEntity();
        dynamicEntity.setUserid(claims.getId());
        dynamicEntity.setCreateDate(new Date());
        dynamicEntity.setAction("发布了一个活动");
        dynamicEntity.setContent(entity.getName());
        dynamicEntity.setExternalUrl("/gathering/" + entity.getId());
        userClient.save(dynamicEntity);

        return new Result(true, StatusCode.OK, "发布成功");
    }

    @GetMapping("usergath/{gatheringId}")
    public Result join(@PathVariable("gatheringId") String gatheringId) {
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESS_ERROR, "未登录");
        }
        GatheringEntity gatheringEntity = gatheringService.selectById(gatheringId);
        if(gatheringEntity == null) {
            return new Result(false, StatusCode.FAIL, "活动已失效");
        }
        UserGathEntity userGathEntity = new UserGathEntity();
        userGathEntity.setGathid(gatheringId);
        userGathEntity.setUserid(claims.getId());
        userGathEntity.setExetime(new Date());
        userGathEntity.setId(String.valueOf(idWorker.nextId()));
        userGathService.insert(userGathEntity);

        DynamicEntity dynamicEntity = new DynamicEntity();
        dynamicEntity.setUserid(claims.getId());
        dynamicEntity.setCreateDate(new Date());
        dynamicEntity.setAction("报名参加了活动");
        dynamicEntity.setContent(gatheringEntity.getName());
        dynamicEntity.setExternalUrl("/gathering/" + gatheringId);
        userClient.save(dynamicEntity);

        return new Result(true, StatusCode.OK, "报名成功");
    }

    @GetMapping("joinList/{id}")
    public Result joinList(@PathVariable("id") String id) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("gathid", id);
        List<UserGathEntity> list = userGathService.list(params);
        List<UserEntity> userList = userClient.userList(list.stream().map(UserGathEntity::getUserid).collect(Collectors.toList()));
        return new Result(true, StatusCode.OK, "查询成功", userList);
    }

    @GetMapping("export/{id}")
    public void export(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<>(4);
        params.put("gathid", id);
        List<UserGathEntity> list = userGathService.list(params);
        List<UserEntity> userList = userClient.userList(list.stream().map(UserGathEntity::getUserid).collect(Collectors.toList()));

        ExcelUtils.exportExcelToTarget(response, "活动报名名单", userList, UserExcel.class);
    }
}

