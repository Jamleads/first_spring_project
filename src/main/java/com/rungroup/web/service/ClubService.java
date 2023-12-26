package com.rungroup.web.service;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(Club club);

    ClubDto findClubById(long clubId);

    void  updateClub(ClubDto clubDto);

    void delete(long clubId);

    List<ClubDto> searchClubs(String query);
}
