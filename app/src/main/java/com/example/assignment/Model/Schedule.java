package com.example.assignment.Model;

public class Schedule {
    private String subjectName;
    private String roomName;

    public Schedule(String subjectName, String roomName) {
        this.subjectName = subjectName;
        this.roomName = roomName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getRoomName() {
        return roomName;
    }

}
