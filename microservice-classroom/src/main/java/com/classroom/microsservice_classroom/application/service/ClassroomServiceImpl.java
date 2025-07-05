package com.classroom.microsservice_classroom.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.classroom.microsservice_classroom.application.dto.Request;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomUseCase {

    private final ClassroomRepositoryPort port;

    @Override
    public Optional<Classroom> getById(int id) {
        return port.findById(id);
    }

    private Classroom create(Request request) {
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
    }

    private Classroom update(Request request, Classroom classroom) {

        classroom.setId(request.getId());
        classroom.setTitle(request.getTitle());
        classroom.setSubtitle(request.getSubtitle());
        classroom.setTheme(request.getTheme());
        classroom.setBackground(request.getBackground());
        classroom.setActive(request.isActive());
        classroom.setUpdateIn(LocalDateTime.now());
        return port.save(classroom);

    }

    private Request mapToResponse(Classroom classroom) {
        return Request.builder()
                .id(classroom.getId())
                .title(classroom.getTitle())
                .subtitle(classroom.getSubtitle())
                .theme(classroom.getTheme())
                .background(classroom.getBackground())
                .active(classroom.isActive())
                .build();
    }

    @Override
    public ResponseEntity<Request> createOrUpdate(Request request) {

        Optional<Classroom> classroom = port.findById(request.getId());
        if (classroom.isPresent()) {
            return new ResponseEntity<>(mapToResponse(update(request, classroom.get())), OK);
        } else if (classroom.isEmpty()) {
            return new ResponseEntity<>(mapToResponse(create(request)), CREATED);
        }
        else {
            return new ResponseEntity<>(BAD_REQUEST);
        }

    }
}
