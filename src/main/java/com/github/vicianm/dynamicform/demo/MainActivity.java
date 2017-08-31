package com.github.vicianm.dynamicform.demo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.vicianm.dynamicform.DynamicFormLayout;
import com.github.vicianm.dynamicform.SectionData;
import com.github.vicianm.dynamicform.demo.data.AddressData;
import com.github.vicianm.dynamicform.demo.data.FormData;
import com.github.vicianm.dynamicform.demo.data.UserData;
import com.github.vicianm.dynamicform.demo.validation.AddressValidator;
import com.github.vicianm.dynamicform.demo.validation.UserValidator;
import com.github.vicianm.dynamicform.demo.validation.ValidationCallback;
import com.github.vicianm.dynamicform.demo.validation.ValidationResult;

import java.util.Collection;
import java.util.List;

public class MainActivity extends DataBindingActivity {

    private DynamicFormLayout dynamicFormLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dynamicFormLayout = (DynamicFormLayout)findViewById(R.id.activity_main);

        bindUiData();
        validateForm(); // this resets/initializes validation icons
    }

    protected void bindUiData() {

        // TODO remove the necessity of section ordering
        bindUser    (0, getFormData().getUser01(), R.id.user01_section);
        bindAddress (1, getFormData().getAddress01(), R.id.address01_section);
        bindUser    (2, getFormData().getUser02(), R.id.user02_section);
        bindAddress (3, getFormData().getAddress02(), R.id.address02_section);
        bindUser    (4, getFormData().getUser03(), R.id.user03_section);
        bindAddress (5, getFormData().getAddress03(), R.id.address03_section);
    }

    protected void bindUser(int sectionOrderNo, UserData data, int userSectionId) {

        View userView = findViewById(userSectionId);

        // Prepare validator
        List<SectionData> sectionsData = dynamicFormLayout.getSectionsData();
        SectionData sectionData = sectionsData.get(sectionOrderNo);
        ValidationCallbackImpl validationCallback = new ValidationCallbackImpl(sectionData);
        UserValidator userValidator = new UserValidator(validationCallback, data, userView);

        // Bind
        bindUiData(userView, R.id.user_name, data, "name", userValidator);
        bindUiData(userView, R.id.user_surname, data, "surname", userValidator);
        bindUiData(userView, R.id.user_id, data, "id", userValidator);
    }

    protected void bindAddress(int sectionOrderNo, AddressData data, int addressSectionId) {

        View addressView = findViewById(addressSectionId);

        // Prepare validator
        List<SectionData> sectionsData = dynamicFormLayout.getSectionsData();
        SectionData sectionData = sectionsData.get(sectionOrderNo);
        ValidationCallbackImpl validationCallback = new ValidationCallbackImpl(sectionData);
        AddressValidator addressValidator = new AddressValidator(validationCallback, data, addressView);

        // Bind
        bindUiData(addressView, R.id.address_country, data, "country", addressValidator);
        bindUiData(addressView, R.id.address_city, data, "city", addressValidator);
        bindUiData(addressView, R.id.address_street, data, "street", addressValidator);
        bindUiData(addressView, R.id.address_street_no, data, "streetNo", addressValidator);
        bindUiData(addressView, R.id.address_zip, data, "zip", addressValidator);
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
        TextView formTextView = (TextView)formView.findViewById(R.id.title);
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
        TextView formTextView = (TextView)formView.findViewById(R.id.title);
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
//        for (int i = 0; i<sectionData.size(); i++) {
//            SectionData section = sectionData.get(i);
//
//            View header = section.getUnpinnedHeader();
//
//            int childLayoutIndex = dynamicFormLayout.getFormLayout().indexOfChild(header);
//            View sectionContent = dynamicFormLayout.getFormLayout().getChildAt(childLayoutIndex + 1);
//
//            TransitionDrawable headerTransition = (TransitionDrawable)header.getBackground();
//            TransitionDrawable contentTransition = (TransitionDrawable)sectionContent.getBackground();
//
//            if (i == activeHeaderIndex) {
//                headerTransition.startTransition(200);
//                contentTransition.startTransition(200);
//            } else if (i == previousHeaderIndex) {
//                headerTransition.reverseTransition(200);
//                contentTransition.reverseTransition(200);
//            }
//        }
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

    /**
     * Updates header/footer/section validation icons each time
     * validation cycle is triggered.
     */
    class ValidationCallbackImpl implements ValidationCallback {

        protected SectionData sectionData;

        public ValidationCallbackImpl(SectionData sectionData) {
            this.sectionData = sectionData;
        }

        /**
         * Address data has been validated.
         */
        @Override
        public void validated(Collection<ValidationResult> results) {

            boolean errorFound = false;
            boolean allValid = true;
            for (ValidationResult result : results) {
                errorFound |= result.getValidity() == ValidationResult.Validity.ERROR;
                allValid &= result.getValidity() == ValidationResult.Validity.VALID;
            }

            // Find UI components we will update
            View pinnedUpHeader = sectionData.getPinnedUpHeader();
            ImageView pinnedUpValidationImage = (ImageView)pinnedUpHeader.findViewById(R.id.img_validation_result);
            View unpinnedHeader = sectionData.getUnpinnedHeader();
            ImageView unpinnedValidationImage = (ImageView)unpinnedHeader.findViewById(R.id.img_validation_result);
            View pinnedDownHeader = sectionData.getPinnedDownHeader();
            ImageView pinnedDownValidationImage = (ImageView)pinnedDownHeader.findViewById(R.id.img_validation_result);

            if (allValid) {
                // All fields of form section are valid.
                // Show green checkbox.
                pinnedUpValidationImage.setVisibility(View.VISIBLE);
                unpinnedValidationImage.setVisibility(View.VISIBLE);
                pinnedDownValidationImage.setVisibility(View.VISIBLE);
                pinnedUpValidationImage.setImageResource(R.drawable.ic_checkmark);
                unpinnedValidationImage.setImageResource(R.drawable.ic_checkmark);
                pinnedDownValidationImage.setImageResource(R.drawable.ic_checkmark);
            } else if (errorFound) {
                // Form section contains validation error.
                // Show red cross.
                pinnedUpValidationImage.setVisibility(View.VISIBLE);
                unpinnedValidationImage.setVisibility(View.VISIBLE);
                pinnedDownValidationImage.setVisibility(View.VISIBLE);
                pinnedUpValidationImage.setImageResource(R.drawable.ic_cross);
                unpinnedValidationImage.setImageResource(R.drawable.ic_cross);
                pinnedDownValidationImage.setImageResource(R.drawable.ic_cross);
            } else {
                // Form section is incomplete.
                // Hide any validation icons.
                pinnedUpValidationImage.setVisibility(View.INVISIBLE);
                unpinnedValidationImage.setVisibility(View.INVISIBLE);
                unpinnedValidationImage.setVisibility(View.INVISIBLE);
                pinnedDownValidationImage.setVisibility(View.INVISIBLE);
            }

        }

    }

}
