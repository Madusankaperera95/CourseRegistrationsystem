package lk.cmjd.coursework.dto;

import jakarta.persistence.Column;

public class PreRequisiteDto {
    private int PreRequisiteId;

    private  String requisiteName;

    public PreRequisiteDto() {
    }

    public PreRequisiteDto(int preRequisiteId, String requisiteName) {
        PreRequisiteId = preRequisiteId;
        this.requisiteName = requisiteName;
    }

    public int getPreRequisiteId() {
        return PreRequisiteId;
    }

    public void setPreRequisiteId(int preRequisiteId) {
        PreRequisiteId = preRequisiteId;
    }

    public String getRequisiteName() {
        return requisiteName;
    }

    public void setRequisiteName(String requisiteName) {
        this.requisiteName = requisiteName;
    }
}
