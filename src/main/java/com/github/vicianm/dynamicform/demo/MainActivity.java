package com.github.vicianm.dynamicform.demo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.dynamicform.DynamicFormLayout;
import com.github.vicianm.dynamicform.SectionData;
import com.github.vicianm.dynamicform.demo.data.FormData;

import java.lang.reflect.Field;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DynamicFormLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rootView = (DynamicFormLayout)findViewById(R.id.activity_main);

        DemoApplication app = (DemoApplication) getApplication();
        FormData formData = app.getFormData();

        bind(R.id.user01_name, formData.getUser01(), "name");
        bind(R.id.user01_surname, formData.getUser01(), "surname");
        bind(R.id.user01_id, formData.getUser01(), "id");
        bind(R.id.address01_country, formData.getAddress01(), "country");
        bind(R.id.address01_city, formData.getAddress01(), "city");
        bind(R.id.address01_street, formData.getAddress01(), "street");
        bind(R.id.address01_street_no, formData.getAddress01(), "streetNo");
        bind(R.id.address01_zip, formData.getAddress01(), "zip");

        bind(R.id.user02_name, formData.getUser02(), "name");
        bind(R.id.user02_surname, formData.getUser02(), "surname");
        bind(R.id.user02_id, formData.getUser02(), "id");
        bind(R.id.address02_country, formData.getAddress02(), "country");
        bind(R.id.address02_city, formData.getAddress02(), "city");
        bind(R.id.address02_street, formData.getAddress02(), "street");
        bind(R.id.address02_street_no, formData.getAddress02(), "streetNo");
        bind(R.id.address02_zip, formData.getAddress02(), "zip");

        bind(R.id.user03_name, formData.getUser03(), "name");
        bind(R.id.user03_surname, formData.getUser03(), "surname");
        bind(R.id.user03_id, formData.getUser03(), "id");
        bind(R.id.address03_country, formData.getAddress03(), "country");
        bind(R.id.address03_city, formData.getAddress03(), "city");
        bind(R.id.address03_street, formData.getAddress03(), "street");
        bind(R.id.address03_street_no, formData.getAddress03(), "streetNo");
        bind(R.id.address03_zip, formData.getAddress03(), "zip");
    }

    protected void bind(int fieldId, final Object dataObject, final String fieldName) {
        TextView textView = (TextView)findViewById(fieldId);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Field declaredField = dataObject.getClass().getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    declaredField.set(dataObject, s.toString());
                } catch (Throwable t) {
                    Log.e("MainActivity", "Data binding failed", t);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected FormData getFormData() {
        DemoApplication demoApp = (DemoApplication) getApplication();
        return demoApp.getFormData();
    }

    /**
     * Called from layout XML file to request
     * <code>View</code> instance which will be used
     * as a form header.
     */
    public View createHeader(View formView) {
        TextView formTextView = (TextView)formView;
        View header = getLayoutInflater().inflate(R.layout.section_title, null);
        TextView title = (TextView)header.findViewById(R.id.title);
        title.setText(formTextView.getText());
        return header;
    }

    /**
     * Called from layout XML file to request
     * <code>View</code> instance which will be used
     * as a form footer.
     */
    public View createFooter(View formView) {
        TextView formTextView = (TextView)formView;
        View footer = getLayoutInflater().inflate(R.layout.section_title, null);
        TextView title = (TextView)footer.findViewById(R.id.title);
        title.setText(formTextView.getText());
        return footer;
    }

    /**
     * Called from layout XML file to notify that new form section is active.
     * @param sectionData Data of form sections - header views, footer views, etc.
     * @param activeHeaderIndex Index of the active section header in provided <code>formSectionHeaders</code> list.
     * @param previousHeaderIndex Index of the section header which was active in a previous call of this method.
     */
    public void activeSectionChanged(List<SectionData> sectionData, int activeHeaderIndex, int previousHeaderIndex) {

        for (int i = 0; i<sectionData.size(); i++) {
            SectionData section = sectionData.get(i);

            TextView header = (TextView) section.getUnpinnedHeader();

            int childLayoutIndex = rootView.getFormLayout().indexOfChild(header);
            View sectionContent = rootView.getFormLayout().getChildAt(childLayoutIndex + 1);

            TransitionDrawable headerTransition = (TransitionDrawable)header.getBackground();
            TransitionDrawable contentTransition = (TransitionDrawable)sectionContent.getBackground();

            if (i == activeHeaderIndex) {
                headerTransition.startTransition(200);
                contentTransition.startTransition(200);
            } else if (i == previousHeaderIndex) {
                headerTransition.reverseTransition(200);
                contentTransition.reverseTransition(200);
            }

            // Update header/footer description texts
            TextView up = (TextView)section.getPinnedUpHeader().findViewById(R.id.description);
            TextView down = (TextView)section.getPinnedDownHeader().findViewById(R.id.description);
            switch (i) {
                case 0: setText(up, down, getFormData().getUser01().toString()); break;
                case 1: setText(up, down, getFormData().getAddress01().toString()); break;
                case 2: setText(up, down, getFormData().getUser02().toString()); break;
                case 3: setText(up, down, getFormData().getAddress02().toString()); break;
                case 4: setText(up, down, getFormData().getUser03().toString()); break;
                case 5: setText(up, down, getFormData().getAddress03().toString()); break;
            }
        }
    }

    protected void setText(TextView header, TextView footer, String text) {
        header.setText(text);
        footer.setText(text);
    }

}
