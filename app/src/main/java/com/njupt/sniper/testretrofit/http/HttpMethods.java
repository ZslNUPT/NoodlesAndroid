package com.njupt.sniper.testretrofit.http;


import com.njupt.sniper.testretrofit.entity.HttpResult;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;
import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.utils.AuthorityUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author：Zsl
 * date：2016/8/23
 */
public class HttpMethods {

    private final String clientId = "stu-app";
    private final String clientSecret = "rSbnsVdrC3o3CjChXUXkPFBdi4qO8cZ8";
    private final String grantTypePassword = "password";
    private final String grantTypeRefreshToken = "refresh_token";

    //构造方法私有
    private HttpMethods() {
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getTokenByPassword(Subscriber<OAuthTokenEntity> subscriber, String username, String password){

        OAuthService movieService = ServiceGenerator.createService(OAuthService.class);

        Observable observable = movieService.getTokenByPassword(clientId,clientSecret,username,password,grantTypePassword);

        toSubscribe(observable, subscriber);
    }

    public void getStatics(Subscriber<StaticsEntity> subscriber){

        TestService testService =ServiceGenerator.createService(TestService.class, AuthorityUtils.getAuthToken().access_token);

        Observable observable = testService.getStatics();

        toSubscribe(observable, subscriber);
    }



    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.error !=null) {
                throw new ApiException(httpResult.error,httpResult.error_description);
            }
            return httpResult.subject;
        }
    }

}
