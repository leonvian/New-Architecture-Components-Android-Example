package lvc.com.androidarchcomponents;

import android.app.Application;

import lvc.com.androidarchcomponents.dagger.component.DaggerNetComponent;
import lvc.com.androidarchcomponents.dagger.component.NetComponent;
import lvc.com.androidarchcomponents.dagger.module.AppModule;
import lvc.com.androidarchcomponents.dagger.module.DaoModule;
import lvc.com.androidarchcomponents.dagger.module.NetModule;


public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .daoModule(new DaoModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
