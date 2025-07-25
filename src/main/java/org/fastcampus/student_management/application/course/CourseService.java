package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    List<Course> courseDayOfWeek = courseRepository.getCourseDayOfWeek(dayOfWeek);
    //List<CourseInfoDto> courseInfoDtoList = convertToCourseInfoDto(courseDayOfWeek);
    // 람다식
    //Course객체를 .map(CourseInfoDto::new)을 통해서 CourseInfoDto 생성자 호출하여 변환.
    return courseDayOfWeek.stream().map(CourseInfoDto::new).toList();
  }

  public void changeFee(String studentName, int fee) {
    List<Course> courses = courseRepository.getCourseListByStudent(studentName);
    CourseList courseList = new CourseList(courses);
    courseList.changeAllCourseFee(fee);
  }


}


/*  private List<CourseInfoDto> convertToCourseInfoDto(List<Course> courseDayOfWeek) {
    List<CourseInfoDto> courseInfoDtoList = new ArrayList<>();
    for(Course course : courseDayOfWeek) {
      CourseInfoDto courseInfoDto = new CourseInfoDto(course);
      courseInfoDtoList.add(courseInfoDto);
    }
    return courseInfoDtoList;
  }*/