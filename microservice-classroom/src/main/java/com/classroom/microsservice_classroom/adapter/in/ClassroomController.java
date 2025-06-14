package com.classroom.microsservice_classroom.adapter.in;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.CREATED;
import com.classroom.microsservice_classroom.application.dto.Request;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Classroom;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomUseCase useCase;

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

    @ResponseStatus(CREATED)
    @PostMapping("/save")
    public Request saveMethodOrUpdate(@RequestBody Request request) {
        Classroom classroom = useCase.createOrUpdate(request);
        return mapToResponse(classroom);
    }

}