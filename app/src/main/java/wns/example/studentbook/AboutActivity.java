package wns.example.studentbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

/**
 * Created by wns349 on 2015-07-22.
 */
public class AboutActivity  extends Activity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(createAboutView());
    }

    private View createAboutView(){
        CardBuilder builder = new CardBuilder(this, CardBuilder.Layout.TEXT);
        builder.setText(getResources().getText(R.string.about_info));
        return builder.getView();
    }
}
