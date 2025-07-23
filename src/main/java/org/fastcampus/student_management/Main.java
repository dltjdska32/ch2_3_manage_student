package org.fastcampus.student_management;

import org.fastcampus.student_management.application.course.CourseService;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;
import org.fastcampus.student_management.repo.StudentRepository;
import org.fastcampus.student_management.ui.course.CourseController;
import org.fastcampus.student_management.ui.course.CoursePresenter;
import org.fastcampus.student_management.ui.student.StudentController;
import org.fastcampus.student_management.ui.student.StudentPresenter;
import org.fastcampus.student_management.ui.UserInputType;

public class Main {

  public static void main(String[] args) {
    StudentRepository studentRepository = new StudentRepository();
    CourseRepository courseRepository = new CourseRepository();

    StudentService studentService = new StudentService(studentRepository);
    CourseService courseService = new CourseService(courseRepository, studentService);

    CoursePresenter coursePresenter = new CoursePresenter();
    StudentPresenter studentPresenter = new StudentPresenter();

    CourseController courseController = new CourseController(coursePresenter, courseService, studentPresenter);
    StudentController studentController = new StudentController(studentPresenter, studentService);

    StudentInfoDto studentInfoDto = new StudentInfoDto("김구라", 20, "영동군");
    StudentInfoDto studentInfoDto1 = new StudentInfoDto("김말자", 20, "황간면");

    CourseInfoDto course = new CourseInfoDto("화학", 200000, DayOfWeek.MONDAY.toString(), "김구라",1000L);
    CourseInfoDto course1 = new CourseInfoDto("수학", 200000, DayOfWeek.MONDAY.toString(), "김말자",1000L);

    studentService.saveStudent(studentInfoDto);
    studentService.saveStudent(studentInfoDto1);

    courseService.registerCourse(course1);
    courseService.registerCourse(course);

    studentPresenter.showMenu();
    UserInputType userInputType = studentController.getUserInput();
    while (userInputType != UserInputType.EXIT) {
      switch (userInputType) {
        case NEW_STUDENT:
          studentController.registerStudent();
          break;
        case NEW_COURSE:
          courseController.registerCourse();
          break;
        case SHOW_COURSE_DAY_OF_WEEK:
          courseController.showCourseDayOfWeek();
          break;
        case ACTIVATE_STUDENT:
          studentController.activateStudent();
          break;
        case DEACTIVATE_STUDENT:
          studentController.deactivateStudent();
          break;
        case CHANGE_FEE:
          courseController.changeFee();
          break;
        default:
          studentPresenter.showErrorMessage();
          break;
      }
      studentPresenter.showMenu();
      userInputType =studentController .getUserInput();
    }
  }
}