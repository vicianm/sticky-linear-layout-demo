package com.github.vicianm.dynamicform.demo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.dynamicform.DynamicFormLayout;
import com.github.vicianm.dynamicform.SectionData;
import com.github.vicianm.dynamicform.demo.data.AddressData;
import com.github.vicianm.dynamicform.demo.data.FormData;
import com.github.vicianm.dynamicform.demo.data.UserData;

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

        bindUser(getFormData().getUser01(), R.id.user01_section);
        bindAddress(getFormData().getAddress01(), R.id.address01_section);
        bindUser(getFormData().getUser02(), R.id.user02_section);
        bindAddress(getFormData().getAddress02(), R.id.address02_section);
        bindUser(getFormData().getUser03(), R.id.user03_section);
        bindAddress(getFormData().getAddress03(), R.id.address03_section);
    }

    protected void bindUser(UserData data, int userSectionId) {
        View userView = findViewById(userSectionId);
        bindUiData(userView, R.id.user_name, data, "name");
        bindUiData(userView, R.id.user_surname, data, "surname");
        bindUiData(userView, R.id.user_id, data, "id");
    }

    protected void bindAddress(AddressData data, int addressSectionId) {
        View addressView = findViewById(addressSectionId);
        bindUiData(addressView, R.id.address_country, data, "country");
        bindUiData(addressView, R.id.address_city, data, "city");
        bindUiData(addressView, R.id.address_street, data, "street");
        bindUiData(addressView, R.id.address_street_no, data, "streetNo");
        bindUiData(addressView, R.id.address_zip, data, "zip");
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
