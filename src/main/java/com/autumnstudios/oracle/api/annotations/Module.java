package com.autumnstudios.oracle.api.annotations;

public @interface Module {
    String name();
    String permission() default "";
}
