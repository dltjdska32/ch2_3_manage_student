package org.fastcampus.student_management.domain;

///  VO (value object)
///  -> 한개 또는 한개이상의 원시타입(프리미티브 타입 - ex -> int, long, double 등)을 묶어서 특정값을 나타내는 객체
public class CourseFee {
    private int fee;

    public CourseFee(int fee) {
        this.fee = fee;
    }

    public void changeFee(int fee) {
        this.fee = fee;
    }

    public void checkFee(int fee) {
        if(fee < 0){
            throw new IllegalArgumentException("수강료는 0원 이상이어야 합니다.");
        }
    }

    public int getFee() {
        return this.fee;
    }
}
