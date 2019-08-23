package com.smile.shirosmiledemo.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Spring上下文套件类
 * </p>
 * @author Luffy
 * @since 2019-07-30 16:01:24
 */
@SuppressWarnings("unused")
@Component
public class SpringKit implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * <p>
     *  初始化注入上下文实例
     * </p>
     * @author Luffy
     * @since 2019-07-30 16:01:33
     * @param applicationContext 上下文对象
     */
    @Override
    public void setApplicationContext(@SuppressWarnings("NullableProblems") ApplicationContext applicationContext) throws BeansException {
        SpringKit.applicationContext = applicationContext;
    }

    /**
     * <p>
     *   根据Bean名称获取Bean实例
     *   适用于目标Bean只存在一种的情况下
     * </p>
     * @author Luffy
     * @date 2019-03-10 22:44:59
     * @param name Bean名称
     * @return java.lang.Object
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * <p>
     *     根据Bean类型获取Bean实例
     *     适用于目标Bean只存在一种的情况下
     * </p>
     *
     * @author Luffy
     * @date 2019-03-10 22:44:30
     * @param tClass 目标类型
     * @return T
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    /**
     * <p>
     *     从容器中获取Bean实例
     * </p>
     * @author Luffy
     * @date 2019-03-10 22:44:08
     * @param name Bean名称
     * @param requiredType Bean类型
     * @return T
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * <p>
     *     上下文中是否存在该Bean
     * </p>
     * @author Luffy
     * @date 2019-03-10 22:43:47
     * @param name Bean名称
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * <p>
     *     该Bean是否是单例
     * </p>
     * @author Luffy
     * @date 2019-03-10 22:43:19
     * @param name Bean名称
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * <p>
     *     根据Bean名称获取Bean类型
     * </p>
     * @author Luffy
     * @date 2019-03-10 22:45:26
     * @param name Bean名称
     * @return java.lang.Class<? extends java.lang.Object>
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }
}