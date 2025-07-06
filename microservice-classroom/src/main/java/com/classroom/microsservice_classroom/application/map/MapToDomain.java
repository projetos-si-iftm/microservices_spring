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

public class MapToDomain {

    public Classroom mapToClassroom(ClassroomDTO classroomDTO) {

        List<Student> students = classroomDTO.getStudents().stream()
                .map(student -> mapToStudent(student)).toList();

        List<Subject> subjects = classroomDTO.getSubjects().stream()
                .map(subject -> mapToSubject(subject)).toList();

        return Classroom.builder()
                .id(classroomDTO.getId())
                .name(classroomDTO.getName())
                .description(classroomDTO.getDescription())
                .image(classroomDTO.getImage())
                .code(classroomDTO.getCode())
                .students(students)
                .subjects(subjects)
                .createIn(classroomDTO.getCreateIn())
                .updateIn(classroomDTO.getUpdateIn())
                .build();
    }

    public Student mapToStudent(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.getId())
                .name(studentDTO.getName())
                .email(studentDTO.getEmail())
                .ingress(studentDTO.getIngress())
                .updateIn(studentDTO.getUpdateIn())
                .build();
    }

    public Subject mapToSubject(SubjectDTO subjectDTO) {

        List<Activity> activities = subjectDTO.getActivities().stream()
                .map(activity -> mapToActivity(activity)).toList();

        return Subject.builder()
                .id(subjectDTO.getId())
                .name(subjectDTO.getName())
                .title(subjectDTO.getTitle())
                .colorTheme(subjectDTO.getColorTheme())
                .subtitle(subjectDTO.getSubtitle())
                .imageUrl(subjectDTO.getImageUrl())
                .activities(activities)
                .createIn(subjectDTO.getCreateIn())
                .updateIn(subjectDTO.getUpdateIn())
                .build();
    }

    public Activity mapToActivity(ActivityDTO activityDTO) {

        List<Question> questions = activityDTO.getQuestions().stream()
                .map(question -> mapToQuestion(question)).toList();

        return Activity.builder()
                .id(activityDTO.getId())
                .type(activityDTO.getType())
                .title(activityDTO.getTitle())
                .description(activityDTO.getDescription())
                .score(mapToScore(activityDTO.getScore()))
                .questions(questions)
                .createIn(activityDTO.getCreateIn())
                .updateIn(activityDTO.getUpdateIn())
                .build();
    }

    public Question mapToQuestion(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .questionText(questionDTO.getQuestionText())
                .options(questionDTO.getOptions())
                .correctOptionIndex(questionDTO.getCorrectOptionIndex())
                .build();
    }

    public Score mapToScore(ScoreDTO score) {
        return Score.builder()
                .obtained(score.getObtained())
                .total(score.getTotal())
                .build();
    }

}
