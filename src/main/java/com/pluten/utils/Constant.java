package com.pluten.utils;

public enum Constant {

    STATE_IS_NOT_VARIBALE("状态不可变"),
    ARGUMENT_EXCEPTION("参数异常"),
    QUESTION_NOT_ENOUGH("题库中数量不够");
    private String code ;
    private String Explanation ;

    private Constant(String Explanation){
        this.Explanation = Explanation;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }
}
