package world.xiaolong.ddns.controller;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import world.xiaolong.ddns.enums.CodeEnum;
import world.xiaolong.ddns.enums.ResultEnum;
import world.xiaolong.ddns.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class NotFoundController extends BasicErrorController {

    private MappingJackson2JsonView mappingJackson2JsonView;

    public NotFoundController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        return new ResponseEntity<>(BeanUtil.beanToMap(ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.NOT_FOUND, null)), HttpStatus.NOT_FOUND);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView(mappingJackson2JsonView).addObject(ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.NOT_FOUND, null));
    }

    @Autowired
    public void setMappingJackson2JsonView(MappingJackson2JsonView mappingJackson2JsonView) {
        this.mappingJackson2JsonView = mappingJackson2JsonView;
    }

}
