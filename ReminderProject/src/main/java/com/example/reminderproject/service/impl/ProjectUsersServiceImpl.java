package com.example.reminderproject.service.impl;

import com.example.reminderproject.model.ProjectUsers;
import com.example.reminderproject.repository.ProjectUsersRepository;
import com.example.reminderproject.service.ProjectUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectUsersServiceImpl implements ProjectUsersService {

    private final ProjectUsersRepository projectUsersRepository;

    @Override
    public List<ProjectUsers> getProjUsersByProjId(Long projId) {
        return projectUsersRepository.getProjectUsersByProjectId(projId);
    }
}
