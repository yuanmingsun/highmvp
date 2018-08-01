
使用方法:
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
Step 2. Add the dependency

dependencies {
        implementation 'com.github.yuanmingsun:highmvp:v1.0.0'
}




使用方法
public class LoginPresenter extends BasePresenter<LoginView> {

    public void login() {
        mView.loginSuccess();
    }
}
public interface LoginView extends BaseMvpView {
    void loginSuccess();
}
public class RegisterPresenter extends BasePresenter<RegisterView> {

    public void register() {
        mView.registerSuccess();
    }
}
public interface RegisterView extends BaseMvpView {
    void registerSuccess();
}
@CreatePresenter(presenter = {LoginPresenter.class, RegisterPresenter.class})
public class ExampleActivity1 extends BaseMvpActivity implements LoginView, RegisterView {

    @PresenterVariable
    private LoginPresenter mLoginPresenter;
    @PresenterVariable
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mLoginPresenter.login();
        mRegisterPresenter.register();
    }

    @Override
    public void loginSuccess() {
        Log.i("ExampleActivity1", "登陆成功");
    }

    @Override
    public void registerSuccess() {
        Log.i("ExampleActivity1", "注册成功");
    }
}
