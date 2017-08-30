package com.github.vicianm.dynamicform.demo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.dynamicform.DynamicFormLayout;
import com.github.vicianm.dynamicform.SectionData;
import com.github.vicianm.dynamicform.demo.data.FormData;

import java.util.List;

public class MainActivity extends DataBindingActivity {

    private DynamicFormLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rootView = (DynamicFormLayout)findViewById(R.id.activity_main);

        bindUiData();
    }

    protected void bindUiData() {

        FormData formData = getFormData();

        bindUiData(R.id.user01_name, formData.getUser01(), "name");
        bindUiData(R.id.user01_surname, formData.getUser01(), "surname");
        bindUiData(R.id.user01_id, formData.getUser01(), "id");
        bindUiData(R.id.address01_country, formData.getAddress01(), "country");
        bindUiData(R.id.address01_city, formData.getAddress01(), "city");
        bindUiData(R.id.address01_street, formData.getAddress01(), "street");
        bindUiData(R.id.address01_street_no, formData.getAddress01(), "streetNo");
        bindUiData(R.id.address01_zip, formData.getAddress01(), "zip");

        bindUiData(R.id.user02_name, formData.getUser02(), "name");
        bindUiData(R.id.user02_surname, formData.getUser02(), "surname");
        bindUiData(R.id.user02_id, formData.getUser02(), "id");
        bindUiData(R.id.address02_country, formData.getAddress02(), "country");
        bindUiData(R.id.address02_city, formData.getAddress02(), "city");
        bindUiData(R.id.address02_street, formData.getAddress02(), "street");
        bindUiData(R.id.address02_street_no, formData.getAddress02(), "streetNo");
        bindUiData(R.id.address02_zip, formData.getAddress02(), "zip");

        bindUiData(R.id.user03_name, formData.getUser03(), "name");
        bindUiData(R.id.user03_surname, formData.getUser03(), "surname");
        bindUiData(R.id.user03_id, formData.getUser03(), "id");
        bindUiData(R.id.address03_country, formData.getAddress03(), "country");
        bindUiData(R.id.address03_city, formData.getAddress03(), "city");
        bindUiData(R.id.address03_street, formData.getAddress03(), "street");
        bindUiData(R.id.address03_street_no, formData.getAddress03(), "streetNo");
        bindUiData(R.id.address03_zip, formData.getAddress03(), "zip");
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
        updateSectionBackground(sectionData, activeHeaderIndex, previousHeaderIndex);
        updateHeaderDescription(sectionData);
    }

    protected void updateSectionBackground(List<SectionData> sectionData, int activeHeaderIndex, int previousHeaderIndex) {
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
        }
    }

    protected void updateHeaderDescription(List<SectionData> sectionData) {

        FormData formData = getFormData();

        for (int i = 0; i<sectionData.size(); i++) {
            SectionData section = sectionData.get(i);
            TextView up = (TextView)section.getPinnedUpHeader().findViewById(R.id.description);
            TextView down = (TextView)section.getPinnedDownHeader().findViewById(R.id.description);
            switch (i) {
                case 0: updateHeaderDescription(up, down, formData.getUser01().toString()); break;
                case 1: updateHeaderDescription(up, down, formData.getAddress01().toString()); break;
                case 2: updateHeaderDescription(up, down, formData.getUser02().toString()); break;
                case 3: updateHeaderDescription(up, down, formData.getAddress02().toString()); break;
                case 4: updateHeaderDescription(up, down, formData.getUser03().toString()); break;
                case 5: updateHeaderDescription(up, down, formData.getAddress03().toString()); break;
            }
        }

    }

    protected void updateHeaderDescription(TextView header, TextView footer, String text) {
        header.setText(text);
        footer.setText(text);
    }

}
