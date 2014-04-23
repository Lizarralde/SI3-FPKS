package jeu.model;

public enum Themes {

    FRANCAIS("FRANCAIS"), GEOGRAPHIE("GEOGRAPHIE"), MATHS("MATHS"), ANGLAIS("ANGLAIS");

    @Override
    public String toString() {
        switch(this){
            case FRANCAIS: return "Français";
            case GEOGRAPHIE: return "Geographie";
            case MATHS: return "Maths";
            case ANGLAIS: return "Anglais";
            default: return "WHAT THE FUCK ARE YOU DOING?";
        }
    }

    public String getPath(){
        switch(this){
            case FRANCAIS: return "FRANCAIS";
            case GEOGRAPHIE: return "GEOGRAPHIE";
            case MATHS: return "MATHS";
            case ANGLAIS: return "ANGLAIS";
            default: return "";
        }
    }

    private String enumValue;

    Themes(String value) {
        this.enumValue = value;
    }
}
