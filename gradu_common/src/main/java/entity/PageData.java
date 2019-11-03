package entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private long total;
    private List<T> rows;


    /**
     * 分页
     * @param rows   列表数据
     * @param total  总记录数
     */
    public PageData(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }
}
