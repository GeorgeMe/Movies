package com.is.movies.dagger.scopes;
import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by George on 2015/8/13.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
