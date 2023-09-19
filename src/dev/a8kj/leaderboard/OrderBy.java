package dev.a8kj.leaderboard;

import lombok.Getter;
import lombok.Setter;

public enum OrderBy {
    
    KILLS("clan_kills") , DEATHS("clan_deaths");

    @Getter @Setter
    private String type;


    OrderBy(String type){
        setType(type);
    }
}
