package net.simplifiedcoding.retrofitandroidtutorial.NIDScan.parser;

import android.content.Context;

import net.simplifiedcoding.retrofitandroidtutorial.NIDScan.Utils;
import net.simplifiedcoding.retrofitandroidtutorial.R;

/**
 * Old NID card data parser
 */
public class OldNidDataParser extends DataParser {
    private static final int OLD_NAME_PADDING = 6;
    private static final int OLD_NID_NO_PADDING = 5;
    private static final int OLD_DOB_PADDING = 5;

    public OldNidDataParser(Context context, String rawData) {
        super(context, rawData);
        parse();
    }

    /**
     * Parse all info
     */
    private void parse() {
        setName();
        setNidNo();
        setDateOfBirth();
        setIssueDate();
    }

    private void setName() {
        if (rawData.contains("<name>") && rawData.contains("</name>")) {
            this.name = rawData.substring(rawData.indexOf("<name>") + OLD_NAME_PADDING, rawData.indexOf("</name>"));
        } else {
            this.name = "N/A";
        }
    }

    private void setNidNo() {
        if (rawData.contains("<pin>") && rawData.contains("</pin>")) {
            this.nidNo = rawData.substring(rawData.indexOf("<pin>") + OLD_NID_NO_PADDING, rawData.indexOf("</pin>"));
        } else {
            this.nidNo = "N/A";
        }

    }

    private void setDateOfBirth() {
        if (rawData.contains("<DOB>") && rawData.contains("</DOB>")) {
            this.dateOfBirth = rawData.substring(rawData.indexOf("<DOB>") + OLD_DOB_PADDING, rawData.indexOf("</DOB>"));
            this.dateOfBirth = Utils.formatDate(this.dateOfBirth, "dd MMM yyyy");
        } else {
            this.dateOfBirth = "N/A";
        }

    }

    private void setIssueDate() {
        this.issueDate = getString(R.string.issue_date) + "N/A";
    }
}
