package com.mycompany.footballplayer;

public class foodballerinfo {
    public static void main(String[] args) {
        LaLigaPlayer messi = new LaLigaPlayer("Messi", 100);
        PremierLeaguePlayer ronaldo = new PremierLeaguePlayer("Ronaldo", 100);

        messi.setSalary(2000);  
        ronaldo.setSalary(1800); 

        messi.printDetails(20);     
        System.out.println("------------------");
        ronaldo.printDetails(22);   
    }
}
