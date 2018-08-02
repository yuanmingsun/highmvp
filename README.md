
### 使用方法


- 步奏1:

``` 
allprojects {
    repositories {
        jcenter()
        url 'https://jitpack.io'
       }
    }
}
``` 

- 步奏2:

``` 
dependencies 
{
 implementation 'com.github.yuanmingsun:highmvp:v1.0.0' 
}
``` 

-----


### 实例

1. 创建presenter

``` 
public class LoginPresenter extends BasePresenter {
    public void login() {
        mView.loginSuccess();
    }
} 

public class RegisterPresenter extends BasePresenter {
    public void register() {
         mView.registerSuccess();
   }
}
``` 
2. 创建view

``` 
public interface LoginView extends BaseMvpView { 
      void loginSuccess();
 }
public interface RegisterView extends BaseMvpView {
     void registerSuccess();
 }
``` 
3. 在activity中使用


``` 
public class ExampleActivity extends BaseMvpActivity implements LoginView, RegisterView {

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
    Log.i("ExampleActivity", "登陆成功");
}

@Override
public void registerSuccess() {
    Log.i("ExampleActivit", "注册成功");
}
}
``` 





