package me.qiufeng.www.LogicalLayer.DataModule.LocalModule;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by QiuFeng on 2/5/15.
 */

@DatabaseTable(tableName = "News")
public class News {

    @DatabaseField(id = true)
    private int newsId;

    @DatabaseField()
    private String title;

    @DatabaseField()
    private String precontent;

    @DatabaseField()
    private String content;

    @DatabaseField()
    private Date date;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrecontent() {
        return precontent;
    }

    public void setPrecontent(String precontent) {
        this.precontent = precontent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
