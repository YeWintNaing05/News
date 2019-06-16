package com.ywn.common.base.mapper;

import androidx.annotation.IntDef;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;


import static android.view.View.*;

@IntDef({VISIBLE, INVISIBLE, GONE})
@Retention(AnnotationRetention.SOURCE)
public @interface Visibility {
}