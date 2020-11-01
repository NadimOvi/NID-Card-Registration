package net.simplifiedcoding.retrofitandroidtutorial.NIDScan.parser;

import android.content.Context;

/**
 * NID Data parser base class
 */
public abstract class DataParser {
    private Context context;
    protected String name;
    protected String nidNo;
    protected String dateOfBirth;
    protected String issueDate;
    protected String rawData;

    public DataParser(Context context, String rawData) {
        this.context = context;
        this.rawData = rawData;
    }

    public String getRawData() {
        return rawData;
    }

    public String getName() {
        return name;
    }

    public String getNidNo() {
        return nidNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getString(int resourceId) {
        return context.getResources().getString(resourceId) + " ";
    }
}
