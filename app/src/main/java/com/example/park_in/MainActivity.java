package com.example.park_in;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public class updateUi extends EmptySlotsTask {
        @Override
        protected void onPostExecute(String result) {
            Log.d("SHLOMI", "onPostExecute: update empty slots");
            TextView mTextView = (TextView) findViewById(R.id.counter);
            mTextView.setText(getString(R.string.emptySlots) + result);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateEmptySlots();
        Button button = (Button) findViewById(R.id.CheckForpark);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("SHLOMI", "+onClick");
                updateEmptySlots();
            }
        });
    }

    void updateEmptySlots() {
        new updateUi().execute();
    }




}
