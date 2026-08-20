package com.alejogiraldoo.domain.entities;

public class LevelEntity {

    private Long levelId;
    private String name;
    private Integer chances;

    public LevelEntity(Long levelId, String name, Integer chances) {
        this.levelId = levelId;
        this.name = name;
        this.chances = chances;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChances() {
        return chances;
    }

    public void setChances(Integer chances) {
        this.chances = chances;
    }

    @Override
    public String toString() {
        return "LevelEntity{" +
                "levelId=" + levelId +
                ", name='" + name + '\'' +
                ", chances=" + chances +
                '}';
    }
}
