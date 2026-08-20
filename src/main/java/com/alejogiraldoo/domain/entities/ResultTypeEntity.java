package com.alejogiraldoo.domain.entities;

public class ResultTypeEntity {

    private Long resultTypeId;
    private String name;

    public ResultTypeEntity(Long resultTypeId, String name) {
        this.resultTypeId = resultTypeId;
        this.name = name;
    }

    public Long getResultTypeId() {
        return resultTypeId;
    }

    public void setResultTypeId(Long resultTypeId) {
        this.resultTypeId = resultTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ResultTypeEntity{" +
                "resultTypeId=" + resultTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
