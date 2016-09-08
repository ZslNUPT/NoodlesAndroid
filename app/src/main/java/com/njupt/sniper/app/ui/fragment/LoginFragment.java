package com.njupt.sniper.app.ui.fragment;

import android.widget.EditText;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.fragment.BaseFragment;
import com.njupt.sniper.app.ui.presenter.LoginPresenter;
import com.njupt.sniper.app.ui.viewInterface.LoginView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView {
    @Bind(R.id.login_username)
    EditText userName;

    @Bind(R.id.login_password)
    EditText password;

    @Override
    protected void baseInit() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected LoginPresenter getChildPresenter() {
        return new LoginPresenter(this,getActivity());
    }

    @OnClick(R.id.to_login)
    void login() {
        mPresenter.executeTask();
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }
}
