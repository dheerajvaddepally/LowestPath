package com.dheeraj.lowestpath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mButton, buttonClear;
    private EditText mEditText;
    private TextView mSuccessText, mTotalCost, mBestPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initListeners() {
        mButton.setOnClickListener(view -> {
            hideSoftKeyboard(this, mEditText);
            String matrixString = mEditText.getText().toString();
            int[][] matrixContents = Matrix.matrixFromEditText(matrixString);
            if (validContents(matrixContents)) {
                loadMatrix(matrixContents);
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(R.string.invalid_matrix)
                        .setPositiveButton(R.string.ok, null)
                        .show();
            }
        });

        buttonClear.setOnClickListener(view -> {
            mEditText.setText("");
            mSuccessText.setVisibility(View.GONE);
            mTotalCost.setVisibility(View.GONE);
            mBestPath.setVisibility(View.GONE);
            buttonClear.setVisibility(View.GONE);
        });
    }

    private void loadMatrix(int[][] matrixContents) {
        Grid validGrid = new Grid(matrixContents);
        GridVisitor visitor = new GridVisitor(validGrid);
        PathState bestPath = visitor.getBestPathForGrid();
        mSuccessText.setVisibility(View.VISIBLE);
        mTotalCost.setVisibility(View.VISIBLE);
        mBestPath.setVisibility(View.VISIBLE);
        buttonClear.setVisibility(View.VISIBLE);

        if (bestPath.isSuccessful()) {
            mSuccessText.setText(R.string.yes);
        } else {
            mSuccessText.setText(R.string.no);
        }

        mTotalCost.setText(Integer.toString(bestPath.getTotalCost()));
        mBestPath.setText(formatPath(bestPath));
    }

    private String formatPath(PathState bestPath) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = bestPath.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }

    //Initialize views here.
    private void initViews() {
        mButton = findViewById(R.id.button_click);
        mEditText = findViewById(R.id.matrix_edit_text);
        mSuccessText = findViewById(R.id.success_text);
        mTotalCost = findViewById(R.id.total_cost);
        mBestPath = findViewById(R.id.best_path_text);
        buttonClear = findViewById(R.id.button_clear);
    }

    private boolean validContents(int[][] contents) {
        if (contents.length < 1 || contents.length > 10 || contents[0].length < 3 || contents[0].length > 100) {
            return false;
        } else {
            return true;
        }
    }

    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
