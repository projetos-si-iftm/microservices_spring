package com.classroom.microsservice_classroom.application.map;

import java.util.List;

import com.classroom.microsservice_classroom.application.dto.ActivityDTO;
import com.classroom.microsservice_classroom.application.dto.ClassroomDTO;
import com.classroom.microsservice_classroom.application.dto.QuestionDTO;
import com.classroom.microsservice_classroom.application.dto.ScoreDTO;
import com.classroom.microsservice_classroom.application.dto.StudentDTO;
import com.classroom.microsservice_classroom.application.dto.SubjectDTO;
import com.classroom.microsservice_classroom.domain.Activity;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.Question;
import com.classroom.microsservice_classroom.domain.Score;
import com.classroom.microsservice_classroom.domain.Student;
import com.classroom.microsservice_classroom.domain.Subject;

public class MapToRequest {

    public ClassroomDTO mapToClassroomDTO(Classroom classroom) {

        List<StudentDTO> students = classroom.getStudents().stream()
                .map(student -> mapToStudentDTO(student)).toList();

        List<SubjectDTO> subjects = classroom.getSubjects().stream()
                .map(subject -> mapToSubjectDTO(subject)).toList();

        return ClassroomDTO.builder()
                .id(classroom.getId())
                .name(classroom.getName())
                .description(classroom.getDescription())
                .image(classroom.getImage())
                .code(classroom.getCode())
                .students(students)
                .subjects(subjects)
                .createIn(classroom.getCreateIn())
                .updateIn(classroom.getUpdateIn())
                .build();
    }

    public StudentDTO mapToStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .ingress(student.getIngress())
                .updateIn(student.getUpdateIn())
                .build();
    }

    public SubjectDTO mapToSubjectDTO(Subject subject) {

        List<ActivityDTO> activities = subject.getActivities().stream()
                .map(activity -> mapToActivityDTO(activity)).toList();

        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .title(subject.getTitle())
                .colorTheme(subject.getColorTheme())
                .subtitle(subject.getSubtitle())
                .imageUrl(subject.getImageUrl())
                .activities(activities)
                .createIn(subject.getCreateIn())
                .updateIn(subject.getUpdateIn())
                .build();
    }

    public ActivityDTO mapToActivityDTO(Activity activity) {

        List<QuestionDTO> questions = activity.getQuestions().stream()
                .map(question -> mapToQuestionDTO(question)).toList();

        return ActivityDTO.builder()
                .id(activity.getId())
                .type(activity.getType())
                .title(activity.getTitle())
                .description(activity.getDescription())
                .score(mapToScoreDTO(activity.getScore()))
                .questions(questions)
                .createIn(activity.getCreateIn())
                .updateIn(activity.getUpdateIn())
                .build();
    }

    public QuestionDTO mapToQuestionDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .options(question.getOptions())
                .correctOptionIndex(question.getCorrectOptionIndex())
                .build();
    }

    public ScoreDTO mapToScoreDTO(Score score) {
        return ScoreDTO.builder()
                .obtained(score.getObtained())
                .total(score.getTotal())
                .build();
    }

}
