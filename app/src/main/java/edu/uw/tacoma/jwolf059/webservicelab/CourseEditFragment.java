package edu.uw.tacoma.jwolf059.webservicelab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;



public class CourseEditFragment extends Fragment {

    private String mCourseIdTextView;
    private EditText mCourseShortDescEditText;
    private EditText mCourseLongDescEditText;
    private EditText mCoursePrereqsEditText;
    private CourseEditListener mListener;
    public final static String COURSE_ITEM_SELECTED = "course_selected";
    private final static String COURSE_ADD_URL = "http://cssgate.insttech.washington.edu/~jwolf059/editCourse.php?";


    public CourseEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCourseIdTextView = getArguments().getString("ID");


        View v = inflater.inflate(R.layout.fragment_course_edit, container, false);

        mCourseIdTextView = getArguments().getString("ID");
        mCourseShortDescEditText = (EditText) v.findViewById(R.id.add_course_short_desc);
        mCourseLongDescEditText = (EditText) v.findViewById(R.id.add_course_long_desc);
        mCoursePrereqsEditText = (EditText) v.findViewById(R.id.add_course_prereqs);


        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();

        Button editCourseButton = (Button) v.findViewById(R.id.submit_button);
        editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = buildCourseURL(v);
                mListener.editCourse(url);
            }
        });
        return v;
    }

    private String buildCourseURL(View v) {


        StringBuilder sb = new StringBuilder(COURSE_ADD_URL);

        try {

            String courseId = mCourseIdTextView;
            sb.append("id=");
            sb.append(courseId);


            String courseShortDesc = mCourseShortDescEditText.getText().toString();
            sb.append("&shortDesc=");
            sb.append(URLEncoder.encode(courseShortDesc, "UTF-8"));


            String courseLongDesc = mCourseLongDescEditText.getText().toString();
            sb.append("&longDesc=");
            sb.append(URLEncoder.encode(courseLongDesc, "UTF-8"));

            String coursePrereqs = mCoursePrereqsEditText.getText().toString();
            sb.append("&prereqs=");
            sb.append(URLEncoder.encode(coursePrereqs, "UTF-8"));

            Log.i("CourseEditFragment", sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CourseEditListener) {
            mListener = (CourseEditListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface CourseEditListener {

        public void editCourse(String url);
    }

}
