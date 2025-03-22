package com.brainnotfound.employeeassessmentbe.DTO;

public class AssessmentDto {

    private Long userId;
    private Long criteriaId;
    private Integer score;
    private String comment;

    public AssessmentDto() {
    }

    public AssessmentDto(Long userId, Long criteriaId, Integer score, String comment) {
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "AssessmentDto{" +
                "userId=" + userId +
                ", criteriaId=" + criteriaId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}

