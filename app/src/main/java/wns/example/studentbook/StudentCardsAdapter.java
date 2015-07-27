package wns.example.studentbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wns349 on 2015-07-22.
 */
public class StudentCardsAdapter extends CardScrollAdapter{
    private List<StudentCard> mCards;
    private Context mContext;

    public StudentCardsAdapter(Context context){
        this(context, null);
    }

    public StudentCardsAdapter(Context context, List<StudentCard> cards){
        this.mContext = context;
        if(cards == null){
            this.mCards = new ArrayList<StudentCard>();
        } else {
            this.mCards = cards;
        }
    }

    @Override
    public int getCount() {
        return this.mCards.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mCards.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CardBuilder builder = new CardBuilder(this.mContext, CardBuilder.Layout.EMBED_INSIDE)
                .setEmbeddedLayout(R.layout.student_card);

        StudentCard studentCard = this.mCards.get(i);

        View v = builder.getView();

        TextView txtStudentName = (TextView) v.findViewById(R.id.txt_student_name);
        txtStudentName.setText(studentCard.getName());

        TextView txtStudentMajor = (TextView) v.findViewById(R.id.txt_student_major);
        txtStudentMajor.setText(studentCard.getMajor());

        ImageView imgStudentPhoto = (ImageView) v.findViewById(R.id.img_student_photo);
        imgStudentPhoto.setImageDrawable(studentCard.getPhoto());

        return v;
    }

    @Override
    public int getPosition(Object o) {
        return this.mCards.indexOf(o);
    }
}
