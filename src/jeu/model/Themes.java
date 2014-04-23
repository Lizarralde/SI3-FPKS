package jeu.model;

public enum Themes {

    FRANÇAIS("FRANÇAIS"), GEOGRAPHIE("GEOGRAPHIE"), MATHS("MATHS"), ANGLAIS("ANGLAIS");

    @Override
    public String toString() {
        switch(this){
            case FRANÇAIS: return "Français";
            case GEOGRAPHIE: return "Géographie";
            case MATHS: return "Maths";
            case ANGLAIS: return "Anglais";
            default: return "WHAT THE FUCK ARE YOU DOING?";
        }
    }

    public String getPath(){
        switch(this){
            case FRANÇAIS: return "FRANÇAIS";
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
