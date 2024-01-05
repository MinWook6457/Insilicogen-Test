package sample;

class Team {
    private String name;
    private int points;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    

    public int getPoints() {
        return points;
    }
    
    public void setTeamName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
