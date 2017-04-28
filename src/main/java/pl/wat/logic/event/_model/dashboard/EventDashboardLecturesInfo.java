package pl.wat.logic.event._model.dashboard;

import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.logic.event._model.view.EventViewLecture;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech on 2017-04-26.
 */
public class EventDashboardLecturesInfo {
    private int idEvent;
    private List<EventViewLecture> lectures;

    public EventDashboardLecturesInfo() {
    }

    public EventDashboardLecturesInfo(int idEvent, List<Lecture> lectures) {
        this.lectures = new LinkedList<>();
        for(Lecture lecture : lectures) {
            this.lectures.add(new EventViewLecture(lecture));
        }

        this.idEvent = idEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public List<EventViewLecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<EventViewLecture> lectures) {
        this.lectures = lectures;
    }
}
