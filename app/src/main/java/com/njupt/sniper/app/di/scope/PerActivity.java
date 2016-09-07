package com.njupt.sniper.app.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * author：Zsl
 * date：2016/9/6
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
