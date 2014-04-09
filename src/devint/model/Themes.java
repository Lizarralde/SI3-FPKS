package devint.model;

public enum Themes {

    FRANCAIS, GEOGRAPHY, MATH, ENGLISH;

    @Override
    public String toString() {
        switch(this){
            case FRANCAIS: return "Français";
            case GEOGRAPHY: return "Géographie";
            case MATH: return "Maths";
            case ENGLISH: return "Anglais";
            default: return "WHAT THE FUCK ARE YOU DOING?";
        }
    }

    public String getPath(){
        switch(this){
            case FRANCAIS: return "FRANCAIS";
            case GEOGRAPHY: return "GEOGRAPHY";
            case MATH: return "MATH";
            case ENGLISH: return "ENGLISH";
            default: return "";
        }
    }
}
