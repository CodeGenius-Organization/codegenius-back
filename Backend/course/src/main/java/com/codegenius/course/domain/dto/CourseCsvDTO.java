package com.codegenius.course.domain.dto;

public class CourseCsvDTO {

    private String title;

    private String courseDescription;

    private String contentDescription;

    private Boolean available;

    public CourseCsvDTO(String title, String courseDescription, String contentDescription, Boolean available) {
        this.title = title;
        this.courseDescription = courseDescription;
        this.contentDescription = contentDescription;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public Boolean getAvailable() {
        return available;
    }
}
