package lips.deafcommunication.deaflips;

import android.content.Intent;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatInProgressActivity extends AppCompatActivity{

    private ArrayList<MessageData> data = new ArrayList<MessageData>();
    public static RecyclerView recyclerView;
    private MessageRecycler adapter;

    private TextView recognisedText;
    private SpeechRecognizer sr;
    private ImageView menu_button;
    private GestureDetectorCompat mDetector;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.mDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_in_progress);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        recyclerView = (RecyclerView) findViewById(R.id.messageRecycler);
        adapter = new MessageRecycler(this, data);


        recyclerView.setAdapter(adapter);
        LinearLayoutManager mngr =
                new LinearLayoutManager(this);

        mngr.setReverseLayout(true);
        mngr.setStackFromEnd(true);
        recyclerView.setLayoutManager(mngr);



        //Initializing views
        recognisedText = (TextView) findViewById(R.id.voice_text_view);
        menu_button = (ImageView) findViewById(R.id.menu_icon);

        mDetector = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
//                Log.i("TEST", "ready");
            }

            @Override
            public void onBeginningOfSpeech() {
//                Log.i("TEST", "beggining");
            }

            @Override
            public void onRmsChanged(float rmsdB) {
//                Log.i("TEST", "rmsChanged");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
//                Log.i("TEST", "buffer");
            }

            @Override
            public void onEndOfSpeech() {
//                Log.i("TEST", "end");
            }

            @Override
            public void onError(int error) {
//                Log.i("TEST", "error: " + error);
                startRecognising();
            }

            @Override
            public void onResults(Bundle results) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.add(new MessageData(MessageData.TYPE_VOICE,  recognisedText.getText().toString()), adapter.getItemCount());
                        recognisedText.setText("");
                        startRecognising();
                    }
                }, 2000);
//                Log.i("TEST", "results");
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                ArrayList data = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String word = (String) data.get(data.size() - 1);
                recognisedText.setText(word);

//                Log.i("TEST", "partial_results");
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
//                Log.i("TEST", "event");
            }
        });

        startRecognising();

    }


    private void startRecognising(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        sr.startListening(intent);
    }
}
