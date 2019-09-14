package service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import service.MPBaseService;

public class MPBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements MPBaseService<T>{

}
