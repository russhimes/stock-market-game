package com.techelevator.services;


import com.techelevator.dao.GameDao;
import com.techelevator.dao.StockDao;
import com.techelevator.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulingService  {

    @Autowired
    GameDao gameDao;
    @Autowired
    StockDao stockDao;

    TaskScheduler scheduler;

    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public SchedulingService(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void addTaskToScheduler(int id, Runnable task, Date runningDate) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, runningDate);
        jobsMap.put(id, scheduledTask);
    }

    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.remove(id);
        }
    }

    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent(){
        List<Game> games = gameDao.getAllGames();
        for (Game game : games ) {
           LocalDateTime dateTime =  LocalDateTime.of(game.getEnd_date(), game.getEnd_time());
           Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            addTaskToScheduler(game.getId(), new EndGameService(game.getId()), date);
        }
    }

}
