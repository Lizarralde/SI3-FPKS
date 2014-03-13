package devint.model;

import devint.config.Config;

public enum Difficulties {

    EASY, MEDIUM, HARD;

    private Integer bonus;

    public Integer getBonus() {

        return bonus;
    }

    public void setBonus(Integer bonus) {

        this.bonus = bonus;
    }

    Difficulties() {

        this.setBonus(Integer.parseInt(Config.getProperty(this.toString())));
    }
}
