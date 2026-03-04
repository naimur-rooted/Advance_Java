package com.mycompany.footballplayer;

abstract class FootballPlayer {
    String name;
    int goals;
    private double salary; // confidential

    public FootballPlayer(String name, int goals) {
        this.name = name;
        this.goals = goals;
    }

    // Abstract method with parameter
    abstract double calculateGoalRate(int matchesPlayed);

    // Setter
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Getter
    public double getSalary() {
        return salary;
    }
}
