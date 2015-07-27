package wns.example.studentbook;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wns349 on 2015-07-23.
 */
public class StudentCardHolder {
    private static StudentCardHolder instance = null;

    private List<StudentCard> mStudentCards;
    private Context mContext;

    public synchronized static StudentCardHolder getInstance() {
        if (instance == null) {
            instance = new StudentCardHolder();
        }

        return instance;
    }

    public void setContext(Context context){
        this.mContext = context;
    }

    private StudentCardHolder(){
        this.mStudentCards = new ArrayList<StudentCard>();
    }


    public void prepareStudentCards() {
        this.mStudentCards = new ArrayList<StudentCard>();

        // Add test cards
        StudentCard studentA = new StudentCard();
        studentA.setName("Ross Geller");
        studentA.setMajor("Computer Science");
        studentA.setPhoto(mContext.getResources().getDrawable(R.drawable.ic_glass_logo));

        StudentCard studentB = new StudentCard();
        studentB.setName("Rachel Green");
        studentB.setMajor("Chemistry");
        studentB.setPhoto(mContext.getResources().getDrawable(R.drawable.ic_glass_logo));

        StudentCard studentC = new StudentCard();
        studentC.setName("Chandler Bing");
        studentC.setMajor("Mathematics");
        studentC.setPhoto(mContext.getResources().getDrawable(R.drawable.ic_glass_logo));

        this.mStudentCards.add(studentA);
        this.mStudentCards.add(studentB);
        this.mStudentCards.add(studentC);
    }

    public void addStudentCard(StudentCard studentCard){
        if(studentCard!=null){
            this.mStudentCards.add(studentCard);
        }
    }

    public List<StudentCard> getStudentCards(){
        return this.mStudentCards;
    }
}
