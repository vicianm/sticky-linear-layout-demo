package sk.plaut.dynamicformheader.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public View onCreateHeaderTest(View v){
        Button button = new Button(getApplicationContext());
        if(v instanceof Button) {
            button.setText(((Button) v).getText());
        }
        return button;
    }

    public View onCreateFooterTest(View v){
        Button button = new Button(getApplicationContext());
        if(v instanceof Button) {
            button.setText(((Button) v).getText());
        }
        return button;
    }

}
