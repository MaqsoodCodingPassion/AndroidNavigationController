package com.servicenow.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog

val API_KEY = "f5bf443c-160d-11ea-ab7b-c5ee597d34d8"

fun hideKeyboard(activity: Activity) {
    val view = activity.currentFocus
    if (view != null) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun showErrorDialog(activity: Activity, msg: String) {
    val builder = AlertDialog.Builder(activity)
    builder.setMessage(msg)
    val alertDialog = builder.create()
    alertDialog.show()
}
