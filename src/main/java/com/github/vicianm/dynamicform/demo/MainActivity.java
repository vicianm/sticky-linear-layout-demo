package com.github.vicianm.dynamicform.demo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import com.github.vicianm.dynamicform.DynamicFormLayout;

public class MainActivity extends AppCompatActivity {

    private DynamicFormLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = (DynamicFormLayout)findViewById(R.id.activity_main);
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

    /**
     * Called from layout XML file to notify that new form section is active.
     * @param headers List of all section headers of the form (including the active one).
     * @param activeHeaderIndex Index of the active section header in provided <code>formSectionHeaders</code> list.
     * @param previousHeaderIndex Index of the section header which was active in a previous call of this method.
     */
    public void activeSectionChanged(List<View> headers, int activeHeaderIndex, int previousHeaderIndex) {

        for (int i = 0; i<headers.size(); i++) {
            TextView header = (TextView)headers.get(i);
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

}
