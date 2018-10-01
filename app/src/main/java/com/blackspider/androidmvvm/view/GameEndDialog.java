package com.blackspider.androidmvvm.view;
/*
 *  ****************************************************************************
 *  * Created by : Arhan Ashik on 10/1/2018 at 1:33 PM.
 *  * Email : ashik.pstu.cse@gmail.com
 *  *
 *  * Last edited by : Arhan Ashik on 10/1/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.blackspider.androidmvvm.R;
import com.blackspider.androidmvvm.databinding.GameEndDialogBinding;

public class GameEndDialog extends DialogFragment {

    private GameEndDialogBinding binding;
    private Context context;
    private String winnerName;

    private GameEndCallback callback;

    public static GameEndDialog newInstance(Context context, String winnerName,
                                            GameEndCallback callback) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.context = context;
        dialog.winnerName = winnerName;
        dialog.callback = callback;

        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(binding.getRoot())
                .setCancelable(false)
                .setPositiveButton(R.string.play_again, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.game_end_dialog, null, false);
        binding.tvWinner.setText(winnerName);
    }

    private void onNewGame() {
        dismiss();
        callback.onGameRestart();
    }

    public interface GameEndCallback{
        void onGameRestart();
    }
}
