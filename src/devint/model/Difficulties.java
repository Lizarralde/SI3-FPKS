package devint.model;

import devint.config.Config;

public enum Difficulties {

    EASY, MEDIUM, HARD;

    private Integer bonus;
    private Integer malus;

    public Integer getBonus() {

        return bonus;
    }

    public void setBonus(Integer bonus) {

        this.bonus = bonus;
    }

    public Integer getMalus() {

        return malus;
    }

    public void setMalus(Integer malus) {

        this.malus = malus;
    }

    Difficulties() {

        this.setBonus(Integer.parseInt(Config.getProperty(this.toString() + "_BONUS")));
        this.setBonus(Integer.parseInt(Config.getProperty(this.toString() + "_MALUS")));
    }
}
