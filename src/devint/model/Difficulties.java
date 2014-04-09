package devint.model;

import devint.config.Config;

public enum Difficulties {

    EASY("EASY"), MEDIUM("MEDIUM"), HARD("HARD");

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

    Difficulties(String path) {

        this.setBonus(Integer.parseInt(Config.getProperty(path+ "_BONUS")));
        this.setMalus(Integer.parseInt(Config.getProperty(path+ "_MALUS")));
    }

    @Override
    public String toString() {
        switch(this){
            case EASY: return "Facile";
            case MEDIUM: return "Moyen";
            case HARD: return "Difficile";
            default: return "WHAT THE FUCK ARE YOU DOING BRO?";
        }
    }

    public String getPath(){
        switch (this){
            case EASY: return "EASY";
            case MEDIUM: return "MEDIUM";
            case HARD: return "HARD";
            default: return "";
        }
    }
}
