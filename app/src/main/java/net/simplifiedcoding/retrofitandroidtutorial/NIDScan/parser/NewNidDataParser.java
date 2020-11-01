package net.simplifiedcoding.retrofitandroidtutorial.NIDScan.parser;

import android.content.Context;



import net.simplifiedcoding.retrofitandroidtutorial.NIDScan.Utils;

/**
 * Smart card data parser
 */
public class NewNidDataParser extends DataParser {
    private static final int SMART_STRING_PADDING = 2;


    public NewNidDataParser(Context context, String rawData) {
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
        if (rawData.contains("NM") && rawData.contains("NW")) {
            this.name = rawData.substring(rawData.indexOf("NM") + SMART_STRING_PADDING, rawData.indexOf("NW"));
        } else {
            this.name = "N/A";
        }
    }

    private void setNidNo() {
        if (rawData.contains("NW") && rawData.contains("OL")) {
            this.nidNo = rawData.substring(rawData.indexOf("NW") + SMART_STRING_PADDING, rawData.indexOf("OL"));
        } else {
            this.nidNo = "N/A";
        }

    }

    private void setDateOfBirth() {
        if (rawData.contains("BR") && rawData.contains("PE")) {
            this.dateOfBirth = rawData.substring(rawData.indexOf("BR") + SMART_STRING_PADDING, rawData.indexOf("PE"));
            this.dateOfBirth = Utils.formatDate(this.dateOfBirth, "yyyyMMdd");
        } else {
            this.dateOfBirth = "N/A";
        }

    }

    private void setIssueDate() {
        if (rawData.contains("DT") && rawData.contains("PK")) {
            this.issueDate = rawData.substring(rawData.indexOf("BR") + SMART_STRING_PADDING, rawData.indexOf("PE"));
            this.issueDate = Utils.formatDate(this.issueDate, "yyyyMMdd");
        } else {
            this.issueDate = "N/A";
        }

    }
}
