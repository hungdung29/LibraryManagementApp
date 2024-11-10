package org.app.DataBase;

public class FindBook extends DataBaseAccessor {
    public static final int BY_TITLE = 1;
    public static final int BY_AUTHOR = 2;
    public static final int BY_PUBLISHER = 3;
    public static final int BY_IMAGE = 4;

    protected String conditionStatement;

    // show book in database have tile = title
    public FindBook(String str, int byFeature) {
        createConditionStatement(str, byFeature);
        // select * from conditionStatement;
    }

    protected void createConditionStatement(String str, int byFeature) {
        conditionStatement = "where ";
        switch (byFeature) {
            case BY_TITLE:       conditionStatement += "title = " + str;      break;
            case BY_AUTHOR:      conditionStatement += "author = " + str;     break;
            case BY_PUBLISHER:   conditionStatement += "publisher = " + str;  break;
            default:
                conditionStatement = "";
        }
    }

    public static void  FindBookByImage() {
        // This feature will appear in version 2.0
        // of Triple Wolves Library Application
    }
}
