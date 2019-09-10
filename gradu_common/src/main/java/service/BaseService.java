package service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *  基础服务接口
 */
public interface BaseService<T, D> {
    /**
     * 通过id获取实体
     *
     * @param id
     * @return T 范型
     */
    T get(Serializable id) throws Exception;

    /**
     * 获取唯一实体
     *
     * @param params
     * @return T 范型
     */
    T getUnique(T params) throws Exception;

    /**
     * 通过id获取实体(逻辑删除)
     *
     * @param id
     * @return
     * @throws Exception
     */
    T getByLogic(Serializable id) throws Exception;

    /**
     * 获取唯一实体(逻辑删除)
     *
     * @param params
     * @return T 范型
     * @author: Guoyaochun
     * @date: 2018年12月22日
     */
    T getUniqueByLogic(T params) throws Exception;

    /**
     * 通过id获取DTO
     *
     * @param id
     * @return D 范型
     */
    D getDTO(Serializable id) throws Exception;

    /**
     * 获取唯一DTO
     *
     * @param params
     * @return D 范型
     */
    D getDTOUnique(D params) throws Exception;

    /**
     * 通过id获取DTO(逻辑删除)
     *
     * @param id
     * @return D 范型
     */
    D getDTOByLogic(Serializable id) throws Exception;

    /**
     * 获取唯一DTO(逻辑删除)
     *
     * @param params
     * @return D 范型
     */
    D getDTOUniqueByLogic(D params) throws Exception;

    /**
     * 保存实体
     *
     * @param model
     * @return T 范型
     */
    T save(T model) throws Exception;

    /**
     * 保存DTO
     *
     * @param model
     * @return D 范型
     */
    D saveDTO(D model) throws Exception;

    /**
     * 更新实体
     *
     * @param model
     * @return T 范型
     */
    T update(T model) throws Exception;

    /**
     * 更新DTO
     *
     * @param model
     * @return D 范型
     */
    D updateDTO(D model) throws Exception;

    /**
     * 通过id删除实体
     *
     * @param id
     * @return Integer
     */
    Integer deleteById(Serializable id) throws Exception;

    /**
     * 删除实体
     *
     * @param model
     * @return Integer
     */
    Integer delete(T model) throws Exception;

    /**
     * 删除DTO
     *
     * @param model
     * @return Integer
     */
    Integer deleteDTO(D model) throws Exception;

    /**
     * 通过id删除实体(逻辑删除)
     *
     * @param id
     * @return Integer
     */
    Integer deleteByLogicId(Serializable id) throws Exception;

    /**
     * 删除实体(逻辑删除)
     *
     * @param model
     * @return Integer
     */
    Integer deleteByLogic(T model) throws Exception;

    /**
     * 删除DTO(逻辑删除)
     *
     * @param model
     * @return Integer
     */
    Integer deleteDTOByLogic(D model) throws Exception;

    /**
     * 获取实体总数量
     *
     * @param entity
     * @return Long
     */
    Long countAll(T entity) throws Exception;

    /**
     * 获取实体总数量(逻辑)
     *
     * @param entity
     * @return Long
     */
    Long countAllByLogic(T entity) throws Exception;

    /**
     * 获取实体列表(全部)
     *
     * @return List<T> 范型
     */
    List<T> findAll() throws Exception;

    /**
     * 获取实体列表(全部)
     *
     * @return List<T> 范型
     */
    List<T> findAllByLogic() throws Exception;

    /**
     * 获取DTO列表(全部)
     *
     * @return List<D> 范型
     */
    List<D> findAllDTO() throws Exception;

    /**
     * 获取DTO列表(全部)
     *
     * @return List<D> 范型
     */
    List<D> findAllDTOByLogic() throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params 条件参数
     */
    List<T> findByParams(T params) throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<T> findByParams(Map<String, Object> params) throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<T> findByParams(T params, String orderBy) throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<T> findByParams(Map<String, Object> params, String orderBy) throws Exception;


    /**
     * 获取DTO列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByParams(D params) throws Exception;


    /**
     * 获取DTO列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByParams(Map<String, Object> params) throws Exception;

    /**
     * 获取DTO列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByParams(D params, String orderBy) throws Exception;

    /**
     * 获取DTO列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByParams(Map<String, Object> params, String orderBy) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<T> 分页列表
     */
    PageList<T> findPageData(T params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<T> 分页列表
     */
    PageList<T> findPageData(T params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<T> 分页列表
     */
    PageList<T> findPageData(Map<String, Object> params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<T> 分页列表
     */
    PageList<T> findPageData(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<D> 分页列表
     */
    PageList<D> findPageDataDTO(D params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<D> 分页列表
     */
    PageList<D> findPageDataDTO(D params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<D> 分页列表
     */
    PageList<D> findPageDataDTO(Map<String, Object> params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<D> 分页列表
     */
    PageList<D> findPageDataDTO(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<T> findByLogicParams(T params) throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<T> findByLogicParams(Map<String, Object> params) throws Exception;

    /**
     * 获取实体列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<T> findByLogicParams(T params, String orderBy) throws Exception;


    /**
     * 获取实体列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<T> findByLogicParams(Map<String, Object> params, String orderBy) throws Exception;

    /**
     * 获取DTO列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByLogicParams(D params) throws Exception;

    /**
     * 获取DTO列表(条件)
     *
     * @param params 条件参数
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByLogicParams(Map<String, Object> params) throws Exception;

    /**
     * 获取DTO列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByLogicParams(D params, String orderBy) throws Exception;

    /**
     * 获取DTO列表(条件)
     *
     * @param params  条件参数
     * @param orderBy 排序
     * @return List<D>(返回值说明)
     */
    List<D> findDTOByLogicParams(Map<String, Object> params, String orderBy) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<T> 分页列表
     */
    PageList<T> findByLogicPageData(T params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<T> 分页列表
     */
    PageList<T> findByLogicPageData(T params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<T> 分页列表
     */
    PageList<T> findByLogicPageData(Map<String, Object> params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取实体分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<T> 分页列表
     */
    PageList<T> findByLogicPageData(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<D> 分页列表
     */
    PageList<D> findByLogicPageDataDTO(D params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<D> 分页列表
     */
    PageList<D> findByLogicPageDataDTO(D params, int pageNum, int pageSize, String orderBy) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @return PageList<D> 分页列表
     */
    PageList<D> findByLogicPageDataDTO(Map<String, Object> params, int pageNum, int pageSize) throws Exception;

    /**
     * 获取DTO分页列表
     *
     * @param params   查询参数
     * @param pageNum  页码
     * @param pageSize 数量
     * @param orderBy  排序
     * @return PageList<D> 分页列表
     */
    PageList<D> findByLogicPageDataDTO(Map<String, Object> params, int pageNum, int pageSize, String orderBy) throws Exception;
}
