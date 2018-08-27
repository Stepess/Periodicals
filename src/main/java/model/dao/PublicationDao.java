package model.dao;

import model.entity.DTO.PublicationDTO;
import model.entity.Publication;
import model.entity.User;

import java.util.List;
import java.util.Map;

public interface PublicationDao extends GenericDao<Publication>{
    Map<String, Integer> getStatistics();
    List<User> getReport(int id);
    void checkDataUnique(String titleEn, String titleUa);
    List<PublicationDTO> getAllMultiLanguagePublication();
    void insertPublicationDto(PublicationDTO dto);
    List<Publication> search(Map<String, String> searchParameters);
}
