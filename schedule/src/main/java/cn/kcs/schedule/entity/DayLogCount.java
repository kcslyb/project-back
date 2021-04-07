package cn.kcs.schedule.entity;

public class DayLogCount {
    private Integer count;
    private String reservedKeyOne;
    private String reservedKeyTwo;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getReservedKeyOne() {
        return reservedKeyOne;
    }

    public void setReservedKeyOne(String reservedKeyOne) {
        this.reservedKeyOne = reservedKeyOne;
    }

    public String getReservedKeyTwo() {
        return reservedKeyTwo;
    }

    public void setReservedKeyTwo(String reservedKeyTwo) {
        this.reservedKeyTwo = reservedKeyTwo;
    }
}
