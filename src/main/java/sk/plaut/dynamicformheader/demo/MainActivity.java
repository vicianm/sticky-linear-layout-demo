package sk.plaut.dynamicformheader.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called from layout XML file to request
     * <code>View</code> instance which will be used
     * as a form header.
     */
    public View createHeader(View formView) {
        TextView formTextView = (TextView)formView;
        TextView headerTextView = (TextView)getLayoutInflater().inflate(R.layout.section_title, null);
        headerTextView.setText(formTextView.getText());
        return headerTextView;
    }

    /**
     * Called from layout XML file to request
     * <code>View</code> instance which will be used
     * as a form footer.
     */
    public View createFooter(View formView) {
        TextView formTextView = (TextView)formView;
        TextView footerTextView = (TextView)getLayoutInflater().inflate(R.layout.section_title, null);
        footerTextView.setText(formTextView.getText());
        return footerTextView;
    }

}
