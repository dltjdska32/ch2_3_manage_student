package org.fastcampus.student_management.domain;

import java.util.List;

///  1급 컬렉션
/// 1급 컬렉션은 컬렉션을 래핑하여 단 하나의 멤버 변수만을 가지는 객체를 의미
/// 왜냐하면 이러한 구조는 컬렉션에 대한 조작을 통제하고 비즈니스 로직을 캡슐화하는 데 유용
/// 장점 -> 로직을 한눈에 파악하기 쉬움 메서드명으로만  쉽게 로직파악 가능
///     -> 재사용이 가능하다
///     -> 테스트가 쉬워진다.
public class CourseList {

    private final List<Course> course;

    public CourseList(List<Course> course) {
        this.course = course;
    }

    public void changeAllCourseFee(int fee) {
        for (Course c : course) {
            if(c.isSameDay(DayOfWeek.SATURDAY) || c.isSameDay(DayOfWeek.SUNDAY)) {
                c.changeFee((int) (fee * 1.5));
            }
            c.changeFee(fee);
        }
    }
}
