package com.njupt.sniper.testretrofit.http;

///**
// * author：Zsl
// * date：2016/8/25
// */
//public  class TokenAuthenticator implements Authenticator {
//
//    @Override
//    public Request authenticate(Route route, Response response) throws IOException {
//        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//        OAuthTokenEntity authTokenEntity=HttpMethods.getInstance().refreshOAuthToken(AuthorityUtils.getAuthToken().refresh_token);
//        AuthorityUtils.setAuthToken(authTokenEntity);
//
//        return response.request().newBuilder()
//                .header("Authorization", "Bearer "+authTokenEntity.access_token)
//                .build();
//    }
//}