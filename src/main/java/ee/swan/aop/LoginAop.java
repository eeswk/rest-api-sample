package ee.swan.aop;

import com.google.common.base.Joiner;
import ee.swan.config.annotation.ApiLoginAuth;
import ee.swan.config.annotation.UserAuth;
import ee.swan.exception.AuthFailureException;
import ee.swan.web.dto.UserDto;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LoginAop {

    private String basePackage = "ee.swan";
    private String annotationName = "ApiLoginAuth";
    private String execution = "@annotation(" + basePackage + ".config.annotation."+ annotationName+ ")";


    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    @Before("@annotation(ee.swan.config.annotation.ApiLoginAuth)")
    public void test(JoinPoint jp) {

        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();





        Map<String, String[]> paramMap = request.getParameterMap();
        String params = "";
        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }
        System.out.println(params);



        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        System.out.println("signature=>" + signature);
        System.out.println("method=>" + method);

        ApiLoginAuth annotation = AnnotationUtils.getAnnotation(method, ApiLoginAuth.class);
        System.out.println("annotation=>" + annotation);

        System.out.println("arg=>" + jp.getArgs());
        System.out.println("target=>" + jp.getTarget());

        ApiLoginAuth annotation1 = jp.getTarget().getClass().getAnnotation(ApiLoginAuth.class);
        System.out.println("annotation1>"+annotation1);


        if (ObjectUtils.isEmpty(annotation)) {
            log.info("auth fail");
            throw new AuthFailureException("유효한 사용자가 아닙니다.1");
        }

        String authCode = request.getHeader(ApiLoginAuth.XAUTHCODE);
        if (authCode == null) {
            log.info("auth fail");
            throw new AuthFailureException("유효한 사용자가 아닙니다.2");
        }

        Object[] signatureArgs = jp.getArgs();
        for (Object obj : signatureArgs) {
            System.out.println("obj=?>"+ obj);
            if (obj instanceof UserDto) {
                UserDto userDto = (UserDto) obj;
                userDto.setUserName(authCode);
            }
        }


    }
}
