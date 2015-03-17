package me.qiufeng.www.LogicalLayer.DataModule.DataManager;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.qiufeng.www.AppDelegate.AppDelegate;
import me.qiufeng.www.Const.AppConst;
import me.qiufeng.www.LogicalLayer.DataModule.AVModule.AVNews;
import me.qiufeng.www.LogicalLayer.DataModule.LocalModule.News;

/**
 * Created by QiuFeng on 2/5/15.
 */

public class NewsManager {

    private RuntimeExceptionDao<News,Integer> newsDao = null;
    static public NewsManager sharedNewsManager = null;

    static public NewsManager sharedNewsManager() {
        if (sharedNewsManager == null) {
            sharedNewsManager = new NewsManager();
        }
        return  sharedNewsManager;
    }

    private NewsManager() {
        newsDao = DatabaseHelper.getHelper().getNewsRuntimeDao();
    }

    public void updateNewsToRead(News news) {
        news.setIsRead(true);
        newsDao.update(news);
    }

    private ArrayList<News> newsSort(List<News> result) {
        ArrayList<News> newses = new ArrayList<>();
        for (News news : result) {
            newses.add(news);
        }
        Collections.sort(newses, new Comparator<News>() {
            @Override
            public int compare(News lhs, News rhs) {
                return rhs.getNewsId() - lhs.getNewsId();
            }
        });

        return newses;
    }

    //for debug
    public ArrayList<News> getAllNewsFromDatabase() {
        List<News> result = newsDao.queryForAll();
        return newsSort(result);
    }


    public void description(List<News> newses) {
        for (News news : newses) {
            Log.i("news",news.getNewsId() + ":" + news.getTitle());
        }
    }

    //end for debug

    public void getEarlierDataFromNetwork(int limit,int lessThan, final FinishCallBack<News> callBack) {
        getNewsesFromNetwork(limit,lessThan,callBack);
    }

    public void getLastestDataFromNetwork(int limit, final FinishCallBack<News> callBack) {
        getNewsesFromNetwork(limit, AppConst.maxInt, callBack);
    }

    private void getNewsesFromNetwork(int limit, int lessThan, final FinishCallBack<News> callBack) {
        AVQuery<AVNews> query = AVObject.getQuery(AVNews.class);
        query.limit(limit);
        query.whereLessThan("newsId", lessThan);
        query.orderByDescending("newsId");

        query.findInBackground(new FindCallback<AVNews>() {
            @Override
            public void done(List<AVNews> avNewses, AVException e) {
                if (e != null) {
                    if (callBack != null) {
                        callBack.done(null, e);
                    }
                } else {
                    ArrayList<News> result = insertNewseWithAVNews(avNewses);
                    if (callBack != null) {
                        callBack.done(result, null);
                    }
                }
            }
        });
    }

    public ArrayList<News> insertNewseWithAVNews(List<AVNews> avNewses) {
        ArrayList<News> result = new ArrayList<News>();
        for (AVNews avNews : avNewses) {
            News news = new News();
            news.setNewsId(avNews.getNewsId());
            News oldNews = newsDao.queryForId(avNews.getNewsId());
            if (oldNews != null) {
                news.setIsRead(oldNews.getIsRead());
            }
            news.setTitle(avNews.getTitle());
            news.setPrecontent(avNews.getPrecontent());
            news.setContent(avNews.getContent());
            news.setDate(avNews.getDate());
            newsDao.createOrUpdate(news);
            result.add(news);
        }

        return newsSort(result);
    }
}
