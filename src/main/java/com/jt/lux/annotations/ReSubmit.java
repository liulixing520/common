package com.jt.lux.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @������ ��ֹ�ظ��ύ
 * @���ߣ� lux
 * @�������ڣ� 2021-4-20 16:35

 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReSubmit {

}
