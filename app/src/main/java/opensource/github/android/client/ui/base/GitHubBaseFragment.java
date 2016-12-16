package opensource.github.android.client.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * Created by Rajan Maurya on 16/12/16.
 */
public class GitHubBaseFragment extends Fragment {

    private InputMethodManager mInputMethod;
    private GitHubProgressBarHandler mConvokeProgressBarHandler;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
        mInputMethod = (InputMethodManager) getActivity().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        mConvokeProgressBarHandler = new GitHubProgressBarHandler(getActivity());
    }

    public void hideKeyboard(View view) {
        mInputMethod.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                .HIDE_IMPLICIT_ONLY);
    }

    public void showKeyboard(View view) {
        mInputMethod.showSoftInputFromInputMethod(view.getWindowToken(),
                InputMethodManager.SHOW_IMPLICIT);
    }

    protected void showConvokeProgressBar() {
        mConvokeProgressBarHandler.show();
    }

    protected void hideConvokeProgressBar() {
        mConvokeProgressBarHandler.hide();
    }

    protected void hideConvokeProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showConvokeProgressDialog(String title) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(title);
        mProgressDialog.show();
    }
}
