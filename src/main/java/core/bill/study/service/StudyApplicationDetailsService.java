package core.bill.study.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.study.model.StudyApplicationDetailsDTO;
import core.bill.study.model.StudyApplicationSearch;

public interface StudyApplicationDetailsService extends GenericService<StudyApplicationDetailsDTO, StudyApplicationSearch> {
public void deleteList(List<StudyApplicationDetailsDTO> model) throws SegesaServiceException;
}
