package com.iit.flagquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


public class ResultDialog extends DialogFragment {

    private static ResultDialog mResultDialog;
    private static int mScore;
    private static ResultDialogListener mResultDialogListener;


    public static ResultDialog newInstance(int score, ResultDialogListener resultDialogListener) {
        mResultDialog = new ResultDialog();
        mScore = score;
        mResultDialogListener = resultDialogListener;
        return mResultDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder alertDialogBilder = new AlertDialog.Builder(context);
        alertDialogBilder.setTitle("result")
                .setMessage(
                        String.format(context.getResources().getString(R.string.score_label), mScore))
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mResultDialogListener != null) {
                            mResultDialogListener.onReplay();
                        }
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mResultDialogListener != null) {
                            mResultDialogListener.onQuit();
                        }
                    }
                });

        return alertDialogBilder.create();
    }


    public interface ResultDialogListener {
        public void onReplay();

        public void onQuit();
    }
}
