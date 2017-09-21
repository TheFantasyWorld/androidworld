package com.androidworld.app.ui.activity.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;

import com.androidworld.app.R;
import com.labo.kaji.swipeawaydialog.support.v4.SwipeAwayDialogFragment;

import java.util.Calendar;

public class AllDialogFragment extends SwipeAwayDialogFragment {

    private interface DialogBuilder {
        @NonNull
        Dialog create(Context context, AllDialogFragment fragment);
    }

    public enum Type implements DialogBuilder {
        APPCOMPAT() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                return new android.support.v7.app.AlertDialog.Builder(context)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .create();
            }
        },
        APPCOMPAT_LIST() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                return new android.support.v7.app.AlertDialog.Builder(context)
                        .setTitle("Title")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(new String[]{
                                "Item 1",
                                "Item 2",
                                "Item 3",
                                "Item 4",
                                "Item 5",
                                "Item 6",
                                "Item 7",
                        }, null)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .create();
            }
        },
        DEFAULT() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                return new AlertDialog.Builder(context)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .create();
            }
        },
        DEFAULT_LIST() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                return new AlertDialog.Builder(context)
                        .setTitle("Title")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(new String[]{
                                "Item 1",
                                "Item 2",
                                "Item 3",
                                "Item 4",
                                "Item 5",
                                "Item 6",
                                "Item 7",
                        }, null)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .create();
            }
        },
        PROGRESS() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                ProgressDialog dialog = new ProgressDialog(context);
                dialog.setTitle("Title");
                dialog.setMessage("Message");
                return dialog;
            }
        },
        DATE() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(context, null, year, month, day);
            }
        },
        TIME() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                return new TimePickerDialog(context, null, hour, minute, true);
            }
        },
        CUSTOM() {
            @Override
            public @NonNull
            Dialog create(Context context, AllDialogFragment fragment) {
                AppCompatDialog dialog = new AppCompatDialog(context);
                dialog.setContentView(R.layout.dialog_custom);
                return dialog;
            }
        }
    }

    public static AllDialogFragment newInstance(Type type) {
        AllDialogFragment f = new AllDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("type", type);
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Type type = (Type)getArguments().getSerializable("type");
        return type.create(getActivity(), this);
    }

}
