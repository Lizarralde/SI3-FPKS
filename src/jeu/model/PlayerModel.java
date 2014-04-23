package jeu.model;

public class PlayerModel {

    private int score;

    private String nickname;

    public int getScore() {

        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }

    public String getNickname() {

        return nickname;
    }

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

    public PlayerModel(String nickname) {

        this.setScore(0);
        this.setNickname(nickname);
    }

    public void score(Integer bonus) {
        this.setScore(this.getScore() + bonus);
    }
}
