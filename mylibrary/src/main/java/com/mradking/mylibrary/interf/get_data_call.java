package com.mradking.mylibrary.interf;

import com.mradking.mylibrary.modal.Modal;

import java.util.List;

public interface  get_data_call {

    public void onsusess(List<Modal> list) ;

    public void failed(String message);
}
