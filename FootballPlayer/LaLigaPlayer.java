package com.mycompany.footballplayer;

class LaLigaPlayer extends FootballPlayer {
    public LaLigaPlayer(String name, int goals) {
        super(name, goals);
    }

    @Override
    double calculateGoalRate(int matchesPlayed) {
        return (double) goals / matchesPlayed;
    }

    public void printDetails(int matchesPlayed) {
        System.out.println("Name: " + name);
        System.out.println("League: LaLiga");
        System.out.println("Goals: " + goals);
        System.out.println("Goal Rate: " + calculateGoalRate(matchesPlayed));
    }
}
