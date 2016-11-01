package edu.uw.tacoma.jwolf059.webservicelab;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.uw.tacoma.jwolf059.webservicelab.course.Course;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseDetailFragment extends Fragment {

    private TextView mCourseIdTextView;
    private TextView mCourseShortDescTextView;
    private TextView mCourseLongDescTextView;
    private TextView mCoursePrereqsTextView;
    public final static String COURSE_ITEM_SELECTED = "course_selected";

    public CourseDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View view =  inflater.inflate(R.layout.fragment_course_detail, container, false);
            mCourseIdTextView = (TextView) view.findViewById(R.id.course_item_id);
            mCourseShortDescTextView = (TextView) view.findViewById(R.id.course_short_desc);
            mCourseLongDescTextView = (TextView) view.findViewById(R.id.course_long_desc);
            mCoursePrereqsTextView = (TextView) view.findViewById(R.id.course_prereqs);

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.show();

        Button editCourseButton = (Button) view.findViewById(R.id.edit_course_button);
        editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("ID", mCourseIdTextView.getText().toString());
                Fragment courseEdit = new CourseEditFragment();
                courseEdit.setArguments(args);
                ft.replace(R.id.courseDetail_frag, courseEdit);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

            return view;
        }

    public void updateView(Course course) {
        if (course != null) {
            mCourseIdTextView.setText(course.getCourseId());
            mCourseShortDescTextView.setText(course.getShortDescription());
            mCourseLongDescTextView.setText(course.getLongDescription());
            mCoursePrereqsTextView.setText(course.getPrereqs());
        }
    }

        @Override
        public void onStart() {
            super.onStart();

            // During startup, check if there are arguments passed to the fragment.
            // onStart is a good place to do this because the layout has already been
            // applied to the fragment at this point so we can safely call the method
            // below that sets the article text.
            Bundle args = getArguments();
            if (args != null) {
                // Set article based on argument passed in
                updateView((Course) args.getSerializable(COURSE_ITEM_SELECTED));
            }
        }
}


