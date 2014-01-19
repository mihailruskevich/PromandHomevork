package com.promand.models;

public class TableRecord implements Record{

    private String leftColumnText;
    private String rightColumnText;

    public String getLeftColumnText() {
        return leftColumnText;
    }
    public void setLeftColumnText(String leftColumnText) {
        this.leftColumnText = leftColumnText;
    }

    public String getRightColumnText() {
        return rightColumnText;
    }
    public void setRightColumnText(String rightColumnText) {
        this.rightColumnText = rightColumnText;
    }

    @Override
    public boolean isEmpty() {
        return leftColumnText == "" && rightColumnText == "";
    }
}
