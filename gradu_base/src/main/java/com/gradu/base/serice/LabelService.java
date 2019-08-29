package com.gradu.base.serice;

import com.gradu.base.dto.LabelDTO;

import java.util.List;

public interface LabelService {

    List<LabelDTO> getAllLabel();

    LabelDTO getLabelById(String id);

    void addLabel(LabelDTO dto);

    void updateLabel(LabelDTO dto);

    void delLabelById(String id);


}
