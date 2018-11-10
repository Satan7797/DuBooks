package dunation.tk.dubooks;

import org.json.JSONArray;

public class LinkList {
    private String mSemester;
    private String mUrl;
    private String mCourse;
    private String mSubject;
    private JSONArray mJsonArray;

    LinkList(String semester,String subject,String url){
        mSemester=semester;
        mSubject=subject;
        mUrl=url;
    }
    LinkList(String course,JSONArray jsonArray){
        mCourse=course;
        mJsonArray=jsonArray;
    }

    public String getSemester(){
        return mSemester;
    }

    public String getCourse(){
        return mCourse;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getSubject(){
        return mSubject;
    }

    JSONArray getJsonArray(){
        return mJsonArray;
    }
}
