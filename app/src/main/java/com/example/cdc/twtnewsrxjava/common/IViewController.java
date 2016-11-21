package com.example.cdc.twtnewsrxjava.common;

public interface IViewController {
    void toastMessage(String message);
    void showLoadingDialog();
    void showLoadingDialog(String message);
    void dismissLoadingDialog();
}
