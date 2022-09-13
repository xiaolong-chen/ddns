package world.xiaolong.ddns.advice;

import cn.hutool.core.date.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import world.xiaolong.ddns.vo.ResultVO;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter(DatePattern.NORM_DATETIME_PATTERN));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<Exception> handler(Exception e) {
        log.error("全局异常打印", e);
        return ResultVO.buildExceptionResult(e);
    }

}
