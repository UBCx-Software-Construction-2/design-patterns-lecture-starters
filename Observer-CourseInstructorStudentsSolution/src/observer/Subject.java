package observer;

import model.LectureModule;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<LectureObserver> observers = new ArrayList<>();

    public void addObserver(LectureObserver lectureObserver){
        if (!observers.contains(lectureObserver)){
            observers.add(lectureObserver);
        }
    }

    public void notifyObservers(LectureModule lectureModule){
        for (LectureObserver observer : observers){
            observer.update(lectureModule);
        }
    }


}