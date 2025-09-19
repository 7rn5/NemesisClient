package nemesis.event.bus;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;

public class Listener {
    private final Class<?> subscriber;
    private Consumer<Object> consumer;
    private final int priority;
    
    public Listener(Class<?> klass, Object object, java.lang.reflect.Method method) {
        subscriber = method.getParameters()[0].getType();
        priority = method.getAnnotation(Subscribe.class).priority();
        
        try {
            MethodType type = MethodType.methodType(void.class, method.getParameters()[0].getType());
            MethodHandle handle = MethodHandles.lookup().findVirtual(klass, method.getName(), type);
            MethodType invokedType = MethodType.methodType(Consumer.class, klass);
            
            consumer = (Consumer<Object>) LambdaMetafactory.metafactory(
                    MethodHandles.lookup(),
                    "accept",
                    invokedType,
                    MethodType.methodType(void.class, Object.class),
                    handle,
                    type
            ).getTarget().invoke(object);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    
    public Class<?> getSubscriber() {
        return subscriber;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void invoke(Object event) {
        consumer.accept(event);
    }
}