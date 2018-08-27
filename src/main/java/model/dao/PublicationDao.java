package model.dao;

import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.entity.User;

import java.util.List;
import java.util.Map;

public interface PublicationDao extends GenericDao<PublicationDto>{
    Map<String, Integer> getStatistics();
    List<User> getReport(int id);
    void checkDataUnique(String titleEn, String titleUa);
    List<PublicationDto> getAllMultiLanguagePublication();
    void insertPublicationDto(PublicationDto dto);
    List<PublicationDto> search(Map<String, String> searchParameters);
}
