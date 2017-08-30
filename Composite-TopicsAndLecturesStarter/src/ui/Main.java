package ui;

import model.Lecture;
import model.Topic;

public class Main {

    public static void main(String[] args) {
        Topic designPatterns = new Topic("Design patterns");
        Topic observer = new Topic("Observer");
        Topic composite = new Topic("Composite");
        Lecture patternsIntro = new Lecture("Intro to Patterns");
        Lecture observerIntro = new Lecture("Intro to Observer");
        designPatterns.addTopic(observer);
        designPatterns.addTopic(composite);
        designPatterns.addLecture(patternsIntro);
        observer.addLecture(observerIntro);
        designPatterns.display("  ");
    }


}