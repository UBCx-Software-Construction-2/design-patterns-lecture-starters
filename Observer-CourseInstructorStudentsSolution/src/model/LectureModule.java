package model;

public class LectureModule {

    private String moduleName;
    private String contents;

    public LectureModule(String moduleName, String contents){
        this.moduleName = moduleName;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "LectureModule {" +
                "\n\tmoduleName='" + moduleName + '\'' +
                ", \n\tcontents='" + contents + '\'' +
                '}';
    }


}