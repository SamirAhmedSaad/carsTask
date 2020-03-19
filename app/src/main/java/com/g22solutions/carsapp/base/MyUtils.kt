package com.g22solutions.carsapp.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.g22solutions.carsapp.R


var dialog: AlertDialog? = null

fun Activity.myToast(msg: String) {

    if (dialog?.isShowing == true) {
        dialog?.dismiss()
    }

    var toastText: TextView
    var toastBtn: TextView


    var view = LayoutInflater.from(this).inflate(R.layout.toast_dialog, null)
    toastText = view.findViewById(R.id.toastMsg)
    toastBtn = view.findViewById(R.id.toastBtn)


    toastText?.text = msg

    toastBtn?.setOnClickListener {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }


    dialog = AlertDialog.Builder(this)
        .setView(view)
        .show()

    dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)


}

fun Activity.myToastWithClick(msg: String, cancelable : Boolean = false , toastClick: (AlertDialog) -> Unit) {
    var dialog: AlertDialog? = null
    var toastText: TextView
    var toastBtn: TextView

    val view = LayoutInflater.from(this).inflate(R.layout.toast_dialog, null)
    toastText = view.findViewById(R.id.toastMsg)
    toastBtn = view.findViewById(R.id.toastBtn)



    toastText?.text = msg

    toastBtn?.setOnClickListener {
        if (dialog?.isShowing == true) {
            toastClick.invoke(dialog!!)
        }
    }


    dialog = AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(cancelable)
        .show()

    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

}

var alertDialog: AlertDialog? = null

fun Activity.loadingDialog(show: Boolean) {

    if (alertDialog?.ownerActivity != this) {
        alertDialog = AlertDialog.Builder(this)
            .setView(R.layout.progress)
            .setCancelable(false)
            .create()

//        alertDialog?.ownerActivity = this

        alertDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    if (show && alertDialog?.isShowing == false) {
        alertDialog?.show()
    } else if (!show) {
        alertDialog?.dismiss()
    }

}

fun Activity.openImageAndroidImageViewer(imageUrl : String) {

    if(TextUtils.isDigitsOnly(imageUrl)){
        return
    }

    val intent = Intent()
    intent.setDataAndType(Uri.parse(imageUrl),"image/*")
    startActivity(intent)
    overridePendingTransition(R.anim.slide_up,R.anim.no_change)

}

suspend fun <T : Any> safeCall(call: suspend () -> NetworkResult<T>): NetworkResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    e.printStackTrace()
    NetworkResult.Error(e)
}






