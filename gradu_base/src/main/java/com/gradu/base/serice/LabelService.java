package com.gradu.base.serice;

import com.gradu.base.dto.LabelDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LabelService {

    List<LabelDTO> getAllLabel();

    LabelDTO getLabelById(String id);

    void addLabel(LabelDTO dto);

    void updateLabel(LabelDTO dto);

    void delLabelById(String id);

    List<LabelDTO> findSearch(LabelDTO dto);

    Page<LabelDTO> getLabelPage(int page, int size, LabelDTO dto);
}
