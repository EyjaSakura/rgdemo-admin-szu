package com.szu.admin.aspect;

import com.szu.admin.common.annotation.RequireSelf;
import com.szu.admin.common.context.AdminContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class RequireSelfAspect {

    @Before("@annotation(requireSelf)")
    public void checkSelf(JoinPoint joinPoint, RequireSelf requireSelf) {

        // 获取传入参数(dto)
        Object[] args = joinPoint.getArgs();

        Integer loginId = AdminContext.getUserId();
        Integer rootPriv = AdminContext.getRootPriv();

        if (rootPriv == 1) return;

        String fieldName = requireSelf.value();
        for (Object arg: args) {
            try {
                Field field = arg.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object targetId = field.get(arg);

                if ((targetId != null) && !loginId.equals(targetId)) {
                    throw new RuntimeException("只能修改自己的信息");
                }
            } catch (NoSuchFieldException ignored) {
                // 参数中没有该字段,跳过
            } catch (IllegalAccessException e) {
                throw new RuntimeException("反射读取字段失败", e);
            }
        }

    }
}
