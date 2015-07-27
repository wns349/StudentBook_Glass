package wns.example.studentbook;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link Activity} showing a tuggable "Hello World!" card.
 * <p>
 * The main content view is composed of a one-card {@link CardScrollView} that provides tugging
 * feedback to the user when swipe gestures are detected.
 * If your Glassware intends to intercept swipe gestures, you should set the content view directly
 * and use a {@link com.google.android.glass.touchpad.GestureDetector}.
 *
 * @see <a href="https://developers.google.com/glass/develop/gdk/touch">GDK Developer Guide</a>
 */
public class MainActivity extends Activity {
    public static final String KEY_STUDENT = "KeyStudent";

    private CardScrollView mCardScroller;
    private Context mContext;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        this.mContext = this;

        StudentCardHolder.getInstance().setContext(this);
        StudentCardHolder.getInstance().prepareStudentCards();

        this.mCardScroller = new CardScrollView(this);
        StudentCardsAdapter adapter = new StudentCardsAdapter(this.mContext, StudentCardHolder.getInstance().getStudentCards());
        this.mCardScroller.setAdapter(adapter);
        this.mCardScroller.activate();
        setContentView(this.mCardScroller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        this.mCardScroller.deactivate();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
            openOptionsMenu();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.menu_add_new_student:
                intent = new Intent(this, AddStudentActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_delete_student:
                Toast.makeText(this, getText(R.string.to_do), Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
