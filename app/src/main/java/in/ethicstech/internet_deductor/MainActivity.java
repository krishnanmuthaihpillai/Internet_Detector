package in.ethicstech.internet_deductor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_check = (Button) findViewById(R.id.button1);
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Method to call async task
                check_internet(getApplicationContext(), 3000);//give your own time Interval to check.
            }
        });
    }

    private void check_internet(final Context context, final int time_interval) {
        InternetChecker asyncTask = new InternetChecker(new Result_Updater() {
            @Override
            public void processFinish(boolean output) {
                if (output) {
                    Toast_it(context, "INTERNET IS AVAILABLE");
                } else {
                    Toast_it(context, "INTERNET IS NOT-AVAILABLE");
                }
            }

        }, context, time_interval);
        asyncTask.execute();


    }

    private void Toast_it(final Context context, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();

            }
        });

    }
}
