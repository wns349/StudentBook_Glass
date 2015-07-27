package wns.example.studentbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardBuilder;

import java.util.List;
import java.util.Locale;

/**
 * Created by wns349 on 2015-07-23.
 */
public class AddStudentActivity extends Activity implements TextToSpeech.OnInitListener {
    private static final String TAG = AddStudentActivity.class.getSimpleName();

    private final int GET_STUDENT_NAME = 1001;
    private final int GET_STUDENT_MAJOR = 1002;

    private TextToSpeech mTTS;
    private boolean mInitialized = false;

    private StudentCard mStudentCard = new StudentCard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_student);

        this.mTTS = new TextToSpeech(this, this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (mStudentCard.getName() == null) {
                speak(getString(R.string.inst_say_name));
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, GET_STUDENT_NAME);
                return true;
            } else if (mStudentCard.getMajor() == null) {
                speak(getString(R.string.inst_say_major));
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, GET_STUDENT_MAJOR);
                return true;
            } else {
                // Save
                StudentCardHolder.getInstance().addStudentCard(mStudentCard);

                Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_LONG).show();

                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void updateUI(){
        TextView txtStudentName = (TextView)findViewById(R.id.txt_add_new_student_name);
        TextView txtStudentMajor = (TextView)findViewById(R.id.txt_add_new_student_major);

        if(mStudentCard.getName() != null){
            txtStudentName.setText(mStudentCard.getName());
        }

        if(mStudentCard.getMajor() != null){
            txtStudentMajor.setText(mStudentCard.getMajor());
        }

        // Check if ready to save
        if(mStudentCard.getName() != null && mStudentCard.getMajor()!= null){
            TextView txtInst = (TextView)findViewById(R.id.txt_add_new_student_inst);
            txtInst.setText(getString(R.string.inst_tap_to_save));

            mStudentCard.setPhoto(getResources().getDrawable(R.drawable.ic_glass_logo));
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mInitialized = true;
            mTTS.setLanguage(Locale.ENGLISH);
        }

        speak(getString(R.string.inst_tap_when_ready_to_speak));
    }

    @Override
    protected void onDestroy() {
        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_STUDENT_NAME && resultCode == RESULT_OK){
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String studentName = results.get(0);
            Log.d(TAG, "Student name: "+studentName);
            mStudentCard.setName(studentName);
            updateUI();
        } else if (requestCode == GET_STUDENT_MAJOR && resultCode == RESULT_OK){
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String studentMajor = results.get(0);
            Log.d(TAG, "Student major: "+studentMajor);
            mStudentCard.setMajor(studentMajor);
            updateUI();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void speak(String message) {
        if (!mInitialized) {
            return;
        }
        mTTS.stop();
        mTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }
}
