package jp.co.yamaha_motor.gimac.tools.aop;


import jp.co.yamaha_motor.gimac.tools.aop.annotation.OperationStatus;
import jp.co.yamaha_motor.gimac.tools.context.ThreadLocalContext;
import jp.co.yamaha_motor.gimac.tools.service.OperatingStatusService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class OperationStatusAspect {

    private final OperatingStatusService operatingStatusService;
    ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    @Autowired
    public OperationStatusAspect(OperatingStatusService operatingStatusService) {
        this.operatingStatusService = operatingStatusService;
    }

    @Around("@annotation(jp.co.yamaha_motor.gimac.tools.aop.annotation.OperationStatus)")
    public Object getOperationStatusExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // アノテーションとそのパラメータを取得
            OperationStatus annotation = method.getAnnotation(OperationStatus.class);
            String flag = annotation.flag();

            String operationStatus = operatingStatusService.getOperatingStatusByCode("LA");
            String onlineStatus = operatingStatusService.getOnlineStatusByCode("LA");
            List<String> availableModels = operatingStatusService.getAvailableModelsByID("0000");

            ThreadLocalContext.setOperationStatus(operationStatus);
            ThreadLocalContext.setOnlineStatus(onlineStatus);
            ThreadLocalContext.setAvailableModels(availableModels);

            if ("X".equalsIgnoreCase(operationStatus) && "1".equals(flag)) {
                throw new IllegalStateException("Operation is not allowed when operation status is 'X'.");
            }
            return joinPoint.proceed();
        } finally {
            ThreadLocalContext.clear();
        }
    }
}
