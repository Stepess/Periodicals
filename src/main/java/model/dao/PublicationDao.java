package model.dao;

import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.entity.User;

import java.util.List;
import java.util.Map;

public interface PublicationDao extends GenericDao<PublicationDto>{
    Map<PublicationDto, Integer> getStatistics();
    List<User> getReport(int id);
    void checkDataUnique(String titleEn, String titleUa);
/*    List<PublicationDto> getAllMultiLanguagePublication();
    void insertPublicationDto(PublicationDto dto);*/
    List<PublicationDto> search(Map<String, String> searchParameters);
    int getNumberOfPublications();
    List<PublicationDto> getPaginatedList(int start, int recordsPerPage);
    int getNumberOfSearchedPublications(Map<String, String> searchParameters);
    List<PublicationDto> getPaginatedSearchList(Map<String, String> searchParameters, int start, int recordsPerPage);
}
