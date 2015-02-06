package me.qiufeng.www.LogicalLayer.DataModule.LocalModule;

import java.util.List;

import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVCompetition;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by QiuFeng on 2015/1/10.
 */

@DatabaseTable(tableName = "Competition")
public class Competition {

    @DatabaseField(id = true)
    private int competitionId;

    @DatabaseField()
    private int type;

    @DatabaseField()
    private int isStart;

    @DatabaseField(defaultValue = "")
    private String name;

    @DatabaseField()
    private int number;


    private List<Match> Matches;

    private List<Player> players;

    public Competition() {

    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Match> getMatches() {
        return Matches;
    }

    public void setMatches(List<Match> matches) {
        Matches = matches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    static  public Competition updateCompetitionWithAVCompetition(AVCompetition competition) {
        return  null;
    }
}
