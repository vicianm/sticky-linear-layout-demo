package sk.plaut.dynamicformheader.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    public View createHeaderView(View formView) {
        return null;
    }

    /**
     * Called from layout XML file to request
     * <code>View</code> instance which will be used
     * as a form footer.
     */
    public View createFooterView(View formView) {
        return null;
    }

}
