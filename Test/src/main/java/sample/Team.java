package sample;

import java.util.Comparator;

// 정렬을 위해 Comparable 인터페이스 상속
public class Team{
    private String teamName;
    private int games;
    private int wins;
    private int draws;
    private int losses;
    private double score;
    
    // 기본 생성자
    public Team() {
    }

    // 매개변수를 받는 생성자
    public Team(String teamName, int games, int wins, int draws, int losses,double score) {
        this.teamName = teamName;
        this.games = games;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.score = 0.0;
    }

    // Getter 및 Setter 메서드
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
    
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    // toString 메서드 오버라이드
    @Override
    public String toString() {
        return "2018K리그[" +
                "팀명='" + teamName + '\'' +
                ", 경기수=" + games +
                ", 승=" + wins +
                ", 무승부=" + draws +
                ", 패=" + losses +
                ", 승점=" + score +
                ']';
    }
}
