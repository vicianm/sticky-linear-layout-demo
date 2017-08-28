package com.github.vicianm.dynamicform.demo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.dynamicform.DynamicFormLayout;

import java.util.List;

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
