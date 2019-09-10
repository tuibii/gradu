package service.impl;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.BaseService;

import java.io.Serializable;

public abstract class BaseServiceImpl<M extends Mapper<T>, T, D> implements BaseService<T, D> {

    protected static final String ID = "id";

    protected static final String ISDELETE = "isDelete";

    protected static final String CREATETIME = "createTime";

    protected static final String UPDATETIME = "updateTime";

    @Autowired
    protected M mapper;

    @Override
    public T get(Serializable id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T getUnique(T params) throws Exception {
        return mapper.selectOne(params);
    }

    @Override
    public T getByLogic(Serializable id) throws Exception {
        T entity = mapper.selectByPrimaryKey(id);
        if (entity != null) {
            boolean isDelete = (boolean) reflexGetProperty(entity, ISDELETE);
            if (isDelete) {
                return null;
            }
        }
        return entity;
    }

    @Override
    public T getUniqueByLogic(T params) throws Exception {
        reflexSetProperty(params, false, ISDELETE, Boolean.class);
        return getUnique(params);
    }

    @Override
    public D getDTO(Serializable id) throws Exception {
        T entity = mapper.selectByPrimaryKey(id);
        return transToDTO(entity);
    }

    @Override
    public D getDTOUnique(D params) throws Exception {
        T entity = mapper.selectOne(transToEntity(params));
        return transToDTO(entity);
    }

    @Override
    public D getDTOByLogic(Serializable id) throws Exception {
        D dto = transToDTO(mapper.selectByPrimaryKey(id));
        if (dto != null) {
            boolean isDelete = (boolean) reflexGetProperty(dto, ISDELETE);
            if (isDelete) {
                return null;
            }
        }
        return dto;
    }

    @Override
    public D getDTOUniqueByLogic(D params) throws Exception {
        reflexSetProperty(params, false, ISDELETE, Boolean.class);
        return getDTOUnique(params);
    }

    @Override
    public T save(T model) throws Exception {
        Long id = IdWorker.getInstance().nextId();
        reflexSetProperty(model, id, "id", Long.class);
        reflexSetProperty(model, false, ISDELETE, Boolean.class);
        reflexSetProperty(model, new Date(), CREATETIME, Date.class);
        reflexSetProperty(model, new Date(), UPDATETIME, Date.class);
        mapper.insert(model);
        return model;
    }

    @Override
    public D saveDTO(D model) throws Exception {
        Long id = IdWorker.getInstance().nextId();
        reflexSetProperty(model, id, "id", Long.class);
        reflexSetProperty(model, false, ISDELETE, Boolean.class);
        reflexSetProperty(model, new Date(), CREATETIME, Date.class);
        reflexSetProperty(model, new Date(), UPDATETIME, Date.class);
        T entity = transToEntity(model);
        mapper.insert(entity);
        return model;
    }

    @Override
    public T update(T model) throws Exception {
        reflexSetProperty(model, false, ISDELETE, Boolean.class);
        reflexSetProperty(model, new Date(), UPDATETIME, Date.class);
        mapper.updateByPrimaryKeySelective(model);
        return model;
    }

    @Override
    public D updateDTO(D model) throws Exception {
        T entity = transToEntity(model);
        reflexSetProperty(entity, false, ISDELETE, Boolean.class);
        reflexSetProperty(entity, new Date(), UPDATETIME, Date.class);
        mapper.updateByPrimaryKeySelective(entity);
        return model;
    }

    @Override
    public Integer delete(T entity) throws Exception {
        if (entity != null) {
            return mapper.delete(entity);
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteDTO(D model) throws Exception {
        T entity = transToEntity(model);
        if (entity != null) {
            return mapper.delete(transToEntity(model));
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteById(Serializable id) throws Exception {
        if (id != null) {
            return mapper.deleteByPrimaryKey(id);
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteByLogicId(Serializable id) throws Exception {
        T entity = get(id);
        if (entity != null) {
            reflexSetProperty(entity, true, ISDELETE, Boolean.class);
            return mapper.updateByPrimaryKeySelective(entity);
        }
        return 0;
    }

    @Override
    public Integer deleteByLogic(T model) throws Exception {
        if (model != null) {
            reflexSetProperty(model, true, ISDELETE, Boolean.class);
            return mapper.updateByPrimaryKeySelective(model);
        }
        return 0;
    }

    @Override
    public Integer deleteDTOByLogic(D model) throws Exception {
        if (model != null) {
            reflexSetProperty(model, true, ISDELETE, Boolean.class);
            T entity = transToEntity(model);
            return mapper.updateByPrimaryKeySelective(entity);
        }
        return 0;
    }

    @Override
    public Long countAll(T entity) throws Exception {
        return new Long(mapper.selectCount(entity));
    }

    @Override
    public Long countAllByLogic(T entity) throws Exception {
        Map<String, Object> map = reflexObjectToMap(entity);
        return selectCountByLogic(map);
    }

    @Override
    public List<T> findAll() throws Exception {
        return mapper.selectAll();
    }

    @Override
    public List<T> findAllByLogic() throws Exception {
        return selectListByLogic(null, null);
    }

    @Override
    public List<D> findAllDTO() throws Exception {
        List<T> entityList = mapper.selectAll();
        return transToDTOList(entityList);
    }

    @Override
    public List<D> findAllDTOByLogic() throws Exception {
        List<T> entityList = selectListByLogic(null, null);
        return transToDTOList(entityList);
    }

    @Override
    public List<T> findByParams(T params) throws Exception {
        return findByParams(params, null);
    }

    @Override
    public List<T> findByParams(Map<String, Object> params) throws Exception {
        return findByParams(params, null);
    }

    @Override
    public List<T> findByParams(T params, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(params);
        return findByParams(map, orderBy);
    }

    @Override
    public List<T> findByParams(Map<String, Object> params, String orderBy) throws Exception {
        return selectList(params, orderBy);
    }

    @Override
    public List<D> findDTOByParams(D params) throws Exception {
        return findDTOByParams(params, null);
    }

    @Override
    public List<D> findDTOByParams(Map<String, Object> params) throws Exception {
        return findDTOByParams(params, null);
    }

    @Override
    public List<D> findDTOByParams(D params, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(transToEntity(params));
        return findDTOByParams(map, orderBy);
    }

    @Override
    public List<D> findDTOByParams(Map<String, Object> params, String orderBy) throws Exception {
        return transToDTOList(selectList(params, orderBy));
    }

    @Override
    public PageList<T> findPageData(T params, int pageNum, int pageSize) throws Exception {
        return findPageData(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<T> findPageData(T params, int pageNum, int pageSize, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(params);
        return findPageData(map, pageNum, pageSize, null);
    }

    @Override
    public PageList<T> findPageData(Map<String, Object> params, int pageNum, int pageSize) throws Exception {
        return findPageData(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<T> findPageData(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception {
        Page<Object> result = PageHelper.startPage(pageNum, pageSize);
        List<T> list = selectList(params, orderBy);
        Paginator paginator = new Paginator(pageNum, pageSize, (int) result.getTotal());
        PageList<T> pageList = new PageList<T>(paginator);
        pageList.setData(list);
        return pageList;
    }

    @Override
    public PageList<D> findPageDataDTO(D params, int pageNum, int pageSize) throws Exception {
        Map<String, Object> map = reflexObjectToMap(transToEntity(params));
        return findPageDataDTO(map, pageNum, pageSize, null);
    }

    @Override
    public PageList<D> findPageDataDTO(D params, int pageNum, int pageSize, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(transToEntity(params));
        return findPageDataDTO(map, pageNum, pageSize, orderBy);
    }

    @Override
    public PageList<D> findPageDataDTO(Map<String, Object> params, int pageNum, int pageSize) throws Exception {
        return findPageDataDTO(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<D> findPageDataDTO(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception {
        Page<Object> result = PageHelper.startPage(pageNum, pageSize);
        List<T> list = selectList(params, orderBy);
        Paginator paginator = new Paginator(pageNum, pageSize, (int) result.getTotal());
        PageList<D> pageList = new PageList<D>(paginator);
        pageList.setData(transToDTOList(list));
        return pageList;
    }

    @Override
    public List<T> findByLogicParams(T params) throws Exception {
        return findByLogicParams(params, null);
    }

    @Override
    public List<T> findByLogicParams(Map<String, Object> params) throws Exception {
        return findByLogicParams(params, null);
    }

    @Override
    public List<T> findByLogicParams(T params, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(params);
        return findByLogicParams(map, orderBy);
    }

    @Override
    public List<T> findByLogicParams(Map<String, Object> params, String orderBy) throws Exception {
        return selectListByLogic(params, orderBy);
    }

    @Override
    public List<D> findDTOByLogicParams(D params) throws Exception {
        return findDTOByLogicParams(params, null);
    }

    @Override
    public List<D> findDTOByLogicParams(Map<String, Object> params) throws Exception {
        return findDTOByLogicParams(params, null);
    }

    @Override
    public List<D> findDTOByLogicParams(D params, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(transToEntity(params));
        return findDTOByLogicParams(map, orderBy);
    }

    @Override
    public List<D> findDTOByLogicParams(Map<String, Object> params, String orderBy) throws Exception {
        return transToDTOList(selectListByLogic(params, orderBy));
    }

    @Override
    public PageList<T> findByLogicPageData(T params, int pageNum, int pageSize) throws Exception {
        return findByLogicPageData(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<T> findByLogicPageData(T params, int pageNum, int pageSize, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(params);
        return findByLogicPageData(map, pageNum, pageSize, null);
    }

    @Override
    public PageList<T> findByLogicPageData(Map<String, Object> params, int pageNum, int pageSize) throws Exception {
        return findByLogicPageData(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<T> findByLogicPageData(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception {
        Page<Object> result = PageHelper.startPage(pageNum, pageSize);
        List<T> list = selectListByLogic(params, orderBy);
        Paginator paginator = new Paginator(pageNum, pageSize, (int) result.getTotal());
        PageList<T> pageList = new PageList<T>(paginator);
        pageList.setData(list);
        return pageList;
    }

    @Override
    public PageList<D> findByLogicPageDataDTO(D params, int pageNum, int pageSize) throws Exception {
        return findByLogicPageDataDTO(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<D> findByLogicPageDataDTO(D params, int pageNum, int pageSize, String orderBy) throws Exception {
        Map<String, Object> map = reflexObjectToMap(transToEntity(params));
        return findByLogicPageDataDTO(map, pageNum, pageSize, orderBy);
    }

    @Override
    public PageList<D> findByLogicPageDataDTO(Map<String, Object> params, int pageNum, int pageSize) throws Exception {
        return findByLogicPageDataDTO(params, pageNum, pageSize, null);
    }

    @Override
    public PageList<D> findByLogicPageDataDTO(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception {
        Page<Object> result = PageHelper.startPage(pageNum, pageSize);
        List<T> list = selectListByLogic(params, orderBy);
        Paginator paginator = new Paginator(pageNum, pageSize, (int) result.getTotal());
        PageList<D> pageList = new PageList<D>(paginator);
        pageList.setData(transToDTOList(list));
        return pageList;
    }

    @SuppressWarnings("unchecked")
    protected List<T> selectList(Map<String, Object> params, String orderBy) throws Exception {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if (params != null && params.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getValue() instanceof String) {
                        if (!entry.getValue().equals("")) {
                            criteria.andEqualTo(entry.getKey(), entry.getValue());
                        }
                    } else if (entry.getValue() instanceof Collection) {
                        Example.Criteria collectionCriteria = example.createCriteria();
                        Collection<Object> collection = (Collection) entry.getValue();
                        for (Object value : collection) {
                            collectionCriteria.orEqualTo(entry.getKey(), value);
                        }
                        example.and(collectionCriteria);
                    } else if (entry.getValue().getClass().isArray()) {
                        Example.Criteria arrayCriteria = example.createCriteria();
                        Object[] array = (Object[]) entry.getValue();
                        for (Object value : array) {
                            criteria.orEqualTo(entry.getKey(), value);
                        }
                        example.and(arrayCriteria);
                    } else {
                        criteria.andEqualTo(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        if (orderBy != null && !orderBy.equals("")) {
            example.setOrderByClause(orderBy.toString()); // 参数名 + ASC 或 DESC, 多个用","隔开
        }

        if (example.getOredCriteria().size() > 0) {
            for (int i = 0; i < example.getOredCriteria().size(); i++) {
                if (example.getOredCriteria().get(i).getCriteria().size() == 0) {
                    example.getOredCriteria().remove(i);
                }
            }
        }
        List<T> list = mapper.selectByExample(example);
        return list;
    }

    protected List<T> selectListByLogic(Map<String, Object> params, String orderBy) throws Exception {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(ISDELETE, false);
        if (params != null && params.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    if (entry.getValue() != null) {
                        if (entry.getValue() instanceof String) {
                            if (!entry.getValue().equals("")) {
                                criteria.andEqualTo(entry.getKey(), entry.getValue());
                            }
                        } else if (entry.getValue() instanceof Collection) {
                            Example.Criteria collectionCriteria = example.createCriteria();
                            Collection<Object> collection = (Collection) entry.getValue();
                            for (Object value : collection) {
                                collectionCriteria.orEqualTo(entry.getKey(), value);
                            }
                            example.and(collectionCriteria);
                        } else if (entry.getValue().getClass().isArray()) {
                            Example.Criteria arrayCriteria = example.createCriteria();
                            Object[] array = (Object[]) entry.getValue();
                            for (Object value : array) {
                                criteria.orEqualTo(entry.getKey(), value);
                            }
                            example.and(arrayCriteria);
                        } else {
                            criteria.andEqualTo(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        }
        if (orderBy != null && !orderBy.equals("")) {
            example.setOrderByClause(orderBy.toString()); // 参数名 + ASC 或 DESC, 多个用","隔开
        }
        if (example.getOredCriteria().size() > 0) {
            for (int i = 0; i < example.getOredCriteria().size(); i++) {
                if (example.getOredCriteria().get(i).getCriteria().size() == 0) {
                    example.getOredCriteria().remove(i);
                }
            }
        }
        List<T> list = mapper.selectByExample(example);
        return list;
    }

    protected Long selectCountByLogic(Map<String, Object> params) throws Exception {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(ISDELETE, false);
        if (params != null && params.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getValue() != null) {
                        if (entry.getValue() instanceof String) {
                            if (!entry.getValue().equals("")) {
                                criteria.andEqualTo(entry.getKey(), entry.getValue());
                            }
                        } else if (entry.getValue() instanceof Collection) {
                            Example.Criteria collectionCriteria = example.createCriteria();
                            Collection<Object> collection = (Collection) entry.getValue();
                            for (Object value : collection) {
                                collectionCriteria.orEqualTo(entry.getKey(), value);
                            }
                            example.and(collectionCriteria);
                        } else if (entry.getValue().getClass().isArray()) {
                            Example.Criteria arrayCriteria = example.createCriteria();
                            Object[] array = (Object[]) entry.getValue();
                            for (Object value : array) {
                                criteria.orEqualTo(entry.getKey(), value);
                            }
                            example.and(arrayCriteria);
                        } else {
                            criteria.andEqualTo(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        }
        if (example.getOredCriteria().size() > 0) {
            for (int i = 0; i < example.getOredCriteria().size(); i++) {
                if (example.getOredCriteria().get(i).getCriteria().size() == 0) {
                    example.getOredCriteria().remove(i);
                }
            }
        }
        Long count = new Long(mapper.selectCountByExample(example));
        return count;
    }

    public List<T> transToEntityList(List<D> dtoList) throws Exception {
        List<T> entityList = new ArrayList<T>();
        if (dtoList != null && dtoList.size() > 0) {
            for (D dto : dtoList) {
                T entity = transToEntity(dto);
                entityList.add(entity);
            }
        }
        return entityList;
    }

    @SuppressWarnings("unchecked")
    public T transToEntity(D dto) throws Exception {
        if (dto == null) {
            return null;
        }
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]; // 获取T的类型
        T entity = clazz.newInstance();
        initConvertUtils();
        BeanUtils.copyProperties(entity, dto);
        return entity;
    }

    public List<D> transToDTOList(List<T> entityList) throws Exception {
        List<D> dtoList = new ArrayList<D>();
        if (entityList != null && entityList.size() > 0) {
            for (T entity : entityList) {
                D dto = transToDTO(entity);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    @SuppressWarnings("unchecked")
    public D transToDTO(T entity) throws Exception {
        if (entity == null) {
            return null;
        }
        Class<D> clazz = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2]; // 获取D的类型
        D dto = clazz.newInstance();
        initConvertUtils();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

    /**
     * 反射转换实体为Map, 用于方法入参(排序使用)
     *
     * @param params 实体
     * @return Map
     * @throws Exception
     * @author Guoyaochun 2018年8月29日
     */
    protected Map<String, Object> reflexObjectToMap(Object params) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (params != null) {
            Class<? extends Object> clazz = params.getClass();
            Field[] fs = clazz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true);
                Object val = new Object();
                val = f.get(params);
                if (!f.getName().equals("serialVersionUID") && val != null) {
                    resultMap.put(f.getName(), val);
                }
            }
        }
        return resultMap;
    }

    /**
     * 反射获取property的属性值
     *
     * @param entity   实体
     * @param property 属性名称
     * @return Object 属性值
     * @throws Exception
     * @author Guoyaochun 2018年8月29日
     */
    protected Object reflexGetProperty(Object entity, String property) throws Exception {
        if (entity != null) {
            Class<? extends Object> clazz = entity.getClass();
            Field[] fs = clazz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true);
                if (f.getName().equals(property)) {
                    Object val = new Object();
                    val = f.get(entity);
                    return val;
                }
            }
        } else {
            throw new BaseException(BaseErrorConstants.BASE_ERROR, "Entity can not be null");
        }
        return null;
    }

    /**
     * 反射设置获取property的属性值
     *
     * @param entity   实体
     * @param value    属性值
     * @param property 属性名称
     * @throws Exception
     * @author Guoyaochun 2018年8月29日
     */
    protected void reflexSetProperty(Object entity, Object value, String property, Class<?> type) throws Exception {
        if (entity != null) {
            Class<? extends Object> clazz = entity.getClass();
            Field[] fs = clazz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true);
                if (f.getName().equals(property)) {
                    String methodName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
                    Method setMethod = entity.getClass().getMethod(methodName, type);
                    setMethod.invoke(entity, value);
                }
            }
        } else {
            throw new BaseException(BaseErrorConstants.BASE_ERROR, "Entity can not be null");
        }
    }

    /**
     * 所有trans方法转换值都为空值，不设默认值
     *
     * @return void(返回值说明)
     * @description(设定参数)
     * @author Guoyaochun 2018年8月29日
     */
    protected void initConvertUtils() {
        ConvertUtils.register(new DateConverter(null), Date.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BooleanConverter(null), Boolean.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
    }

}


