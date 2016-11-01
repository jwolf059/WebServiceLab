package edu.uw.tacoma.jwolf059.webservicelab.course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JeremyWolf on 10/24/16.
 */

public class Course implements Serializable {

    public String mCourseId;
    public String mShortDescription;
    public String mLongDescription;
    public String mPrereqs;
    public static final String ID = "id";
    public static final String SHORT_DESC = "shortDesc";
    public static final String LONG_DESC = "longDesc";
    public static final String PRE_REQS = "prereqs";

    public Course(String theCourseId, String theShortDescription, String theLongDescription, String thePrereqs) {
        mCourseId = theCourseId;
        mShortDescription = theShortDescription;
        mLongDescription = theLongDescription;
        mPrereqs = thePrereqs;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Course item);
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if success.
     * @param courseJSON
     * @return reason or null if successful.
     */
    public static String parseCourseJSON(String courseJSON, List<Course> courseList) {
        String reason = null;
        if (courseJSON != null) {
            try {
                JSONArray arr = new JSONArray(courseJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Course course = new Course(obj.getString(Course.ID), obj.getString(Course.SHORT_DESC)
                            , obj.getString(Course.LONG_DESC), obj.getString(Course.PRE_REQS));
                    courseList.add(course);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }

        }
        return reason;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public String getPrereqs() {
        return mPrereqs;
    }

    public void setCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public void setShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public void setLongDescription(String mLongDescription) {
        this.mLongDescription = mLongDescription;
    }

    public void setPrereqs(String mPrereqs) {
        this.mPrereqs = mPrereqs;
    }
}
