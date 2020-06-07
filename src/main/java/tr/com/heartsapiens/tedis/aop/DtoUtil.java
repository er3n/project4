package tr.com.heartsapiens.tedis.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import tr.com.heartsapiens.tedis.dto.IBaseDto;

/**
 * Created by ersin on 7.06.2020.
 */
@Aspect
@Component
public class DtoUtil {


    @Before(value = "execution(* tr.com.heartsapiens.tedis.api.*.create(..)) && args(entity) ")
    public void beforeCreate(JoinPoint joinPoint, IBaseDto entity) {
        System.out.println(entity);
        this.setDurumIfNull(entity);
    }

    @Before(value = "execution(* tr.com.heartsapiens.tedis.api.*.update(..)) && args(id,entity) ")
    public void beforeUpdate(JoinPoint joinPoint, Long id,IBaseDto entity) {

        this.setDurumIfNull(entity);
    }

    private void setDurumIfNull(IBaseDto entity) {

        if(entity.getDurum() == null){
            entity.setDurum(true);
        }
    }

}
