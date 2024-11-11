package com.example.chucknorris.utils

import android.app.AlertDialog
import android.content.Context
import com.example.chucknorris.R

object DialogUtils {
    fun showErrorDialog(context: Context, message: String, titile: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titile)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.accept) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun showConfirmationDialog(
        context: Context, message: String, positiveButtonText: String, negativeButtonText: String,
        onConfirm: () -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.confirm)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            onConfirm()
            dialog.dismiss()
        }
        builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}