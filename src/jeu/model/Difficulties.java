package jeu.model;

import jeu.config.Config;

public enum Difficulties {

    FACILE("FACILE"), MOYEN("MOYEN"), DIFFICILE("DIFFICILE");

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
            case FACILE: return "Facile";
            case MOYEN: return "Moyen";
            case DIFFICILE: return "Difficile";
            default: return "WHAT THE FUCK ARE YOU DOING BRO?";
        }
    }

    public String getPath(){
        switch (this){
            case FACILE: return "FACILE";
            case MOYEN: return "MOYEN";
            case DIFFICILE: return "DIFFICILE";
            default: return "";
        }
    }
}
