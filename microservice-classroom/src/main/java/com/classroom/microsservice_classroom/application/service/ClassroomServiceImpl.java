package com.classroom.microsservice_classroom.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.classroom.microsservice_classroom.application.dto.Request;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomUseCase {

    private final ClassroomRepositoryPort port;

    @Override
    public Classroom createOrUpdate(Request request) {
        return port.findById(request.getId()).map(classroom -> {
            classroom.setId(request.getId());
            classroom.setTitle(request.getTitle());
            classroom.setSubtitle(request.getSubtitle());
            classroom.setTheme(request.getTheme());
            classroom.setBackground(request.getBackground());
            classroom.setActive(request.isActive());
            classroom.setUpdateIn(LocalDateTime.now());
            return port.save(classroom);
        }).orElseGet(() -> {
            Classroom classroom = Classroom.builder()
                    .id(request.getId())
                    .title(request.getTitle())
                    .subtitle(request.getSubtitle())
                    .theme(request.getTheme())
                    .background(request.getBackground())
                    .active(request.isActive())
                    .createIn(LocalDateTime.now())
                    .build();
            return port.save(classroom);
        });
    }

    @Override
    public Optional<Classroom> getById(int id) {
        return port.findById(id);
    }

}
